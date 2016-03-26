package cn.zsmy.akm.doctor.chat.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import cn.zsmy.akm.doctor.chat.utils.FileUtil;
import cn.zsmy.doctor.R;


public class AudioRecorderButton extends Button implements AudioManager.AudioStateListener {

	private static final int STATE_NORMAL = 1;
	private static final int STATE_RECORDING = 2;
	private static final int STATE_WANT_TO_CANCEL = 3;

	private int mCurrentState = STATE_NORMAL;
	private boolean isRecording = false;//

	private static final int DISTANCE_Y_CANCEL = 50;

	private DialogManager mDialogManager;
	private AudioManager mAudioManager;

	private float mTime;
	private boolean mReady;

	private static final int MSG_AUDIO_PREPARED = 0x110;
	private static final int MSG_VOICE_CHANGED = 0x111;
	private static final int MSG_DIALOG_DIMISS = 0x112;

	/*
	 */
	private Runnable mGetVoiceLevelRunnable = new Runnable() {

		public void run() {
			while (isRecording) {
				try {
					Thread.sleep(100);
					mTime += 0.1f;
					mHandler.sendEmptyMessage(MSG_VOICE_CHANGED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_AUDIO_PREPARED:
				mDialogManager.showRecordingDialog();
				isRecording = true;
				new Thread(mGetVoiceLevelRunnable).start();
				break;

			case MSG_VOICE_CHANGED:
				mDialogManager.updateVoiceLevel(mAudioManager.getVoiceLevel(7));
				break;

			case MSG_DIALOG_DIMISS:
				mDialogManager.dimissDialog();
				break;

			}

			super.handleMessage(msg);
		}
	};

	public AudioRecorderButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDialogManager = new DialogManager(context);
		mAudioManager = AudioManager.getInstance(FileUtil.getVoiceDir());
		mAudioManager.setOnAudioStateListener(this);

		setOnLongClickListener(new OnLongClickListener() {

			public boolean onLongClick(View v) {
				mReady = true;
				mAudioManager.prepareAudio();

				return false;
			}
		});
	}

	@Override
	public void wellPrepared() {
		mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
	}

	public AudioRecorderButton(Context context) {
		this(context, null);
	}

	public interface AudioFinishRecorderListener {
		void onFinish(float seconds, String filePath);
	}

	private AudioFinishRecorderListener audioFinishRecorderListener;

	public void setAudioFinishRecorderListener(AudioFinishRecorderListener listener) {
		audioFinishRecorderListener = listener;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			changeState(STATE_RECORDING);
			break;
		case MotionEvent.ACTION_MOVE:

			if (isRecording) {
				if (wantToCancle(x, y)) {
					changeState(STATE_WANT_TO_CANCEL);
				} else {
					changeState(STATE_RECORDING);
				}
			}

			break;
		case MotionEvent.ACTION_UP:
			if (!mReady) {
				reset();
				return super.onTouchEvent(event);
			}
			if (!isRecording || mTime < 1.0f) {
				mDialogManager.tooShort();
				mAudioManager.cancel();
				mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1000);//
			} else if (mCurrentState == STATE_RECORDING) { //
				mDialogManager.dimissDialog();
				mAudioManager.release();
				if (audioFinishRecorderListener != null) {
					audioFinishRecorderListener.onFinish(mTime, mAudioManager.getCurrentFilePath());
				}

			} else if (mCurrentState == STATE_WANT_TO_CANCEL) { //
				mDialogManager.dimissDialog();
				mAudioManager.cancel();
			}
			reset();
			break;
		case MotionEvent.ACTION_CANCEL:
			mDialogManager.dimissDialog();
			mAudioManager.cancel();
			reset();
			break;

		}
		return super.onTouchEvent(event);
	}

	private void reset() {
		isRecording = false;
		mTime = 0;
		mReady = false;
		changeState(STATE_NORMAL);
	}

	private boolean wantToCancle(int x, int y) {
		if (x < 0 || x > getWidth()) {
			return true;
		}
		if (y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL) {
			return true;
		}

		return false;
	}

	private void changeState(int state) {
		if (mCurrentState != state) {
			mCurrentState = state;
			switch (state) {
			case STATE_NORMAL:
				setBackgroundResource(R.drawable.btn_recorder_normal);
				setText(R.string.str_recorder_normal);
				break;

			case STATE_RECORDING:
				setBackgroundResource(R.drawable.btn_recording);
				setText(R.string.str_recorder_recording);
				if (isRecording) {
					mDialogManager.recording();
				}
				break;

			case STATE_WANT_TO_CANCEL:
				setBackgroundResource(R.drawable.btn_recording);
				setText(R.string.str_recorder_want_cancel);
				mDialogManager.wantToCancel();
				break;
			}
		}
	}

}