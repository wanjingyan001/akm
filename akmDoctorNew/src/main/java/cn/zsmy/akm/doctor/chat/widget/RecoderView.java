package cn.zsmy.akm.doctor.chat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import cn.zsmy.akm.doctor.chat.utils.FileUtil;
import cn.zsmy.akm.doctor.chat.utils.RecordManager;
import cn.zsmy.akm.doctor.chat.utils.Trace;
import cn.zsmy.akm.doctor.chat.utils.UPlayer;
import cn.zsmy.doctor.R;


public class RecoderView extends LinearLayout implements OnClickListener, OnTouchListener {
	private ImageView mDiseaseVoiceRecoderImg;
	private ImageView mDiseaseVoicePlayImg;
	private ImageView mDiseaseVoiceDeleteImg;
	private LinearLayout mDiseaseVoiceContainer;
	private LinearLayout mDiseaseVoiceCompletedContainer;
	private TextView mRecordLabel;
	private String recorderPath;
	private RecordManager recordManager;
	private UPlayer uPlayer;

	public RecoderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView(context);
	}

	public RecoderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView(context);
	}

	public RecoderView(Context context) {
		super(context);
		initializeView(context);
	}

	private void initializeView(Context context) {
		LayoutInflater.from(context).inflate(R.layout.widget_recorder, this);
		mRecordLabel = (TextView) findViewById(R.id.mRecordLabel);
		mDiseaseVoiceRecoderImg = (ImageView) findViewById(R.id.mDiseaseVoiceRecoderImg);
		mDiseaseVoicePlayImg = (ImageView) findViewById(R.id.mDiseaseVoicePlayImg);
		mDiseaseVoiceDeleteImg = (ImageView) findViewById(R.id.mDiseaseVoiceDeleteImg);
		// mDiseaseVoiceRecoderImg.setOnClickListener(this);
		mDiseaseVoiceRecoderImg.setOnTouchListener(this);
		mDiseaseVoicePlayImg.setOnClickListener(this);
		mDiseaseVoiceDeleteImg.setOnClickListener(this);

		mDiseaseVoiceContainer = (LinearLayout) findViewById(R.id.mDiseaseVoiceContainer);
		mDiseaseVoiceCompletedContainer = (LinearLayout) findViewById(R.id.mDiseaseVoiceCompletedContainer);

		String voiceDir = FileUtil.getVoiceDir();
		recorderPath = voiceDir + File.separator + System.currentTimeMillis() + ".amr";
		recordManager = new RecordManager(new File(recorderPath));
		uPlayer = new UPlayer();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mDiseaseVoiceRecoderImg:
			// TODO do recoder voice
			mDiseaseVoiceCompletedContainer.setVisibility(View.VISIBLE);
			mDiseaseVoiceContainer.setVisibility(View.GONE);
			break;
		case R.id.mDiseaseVoicePlayImg:
			// TODO do play voice
			// showToastShort("play");
			uPlayer.start(recorderPath);
			break;
		case R.id.mDiseaseVoiceDeleteImg:
			// TODO delete recoder voice file
			uPlayer.releaseMedia();
			Trace.d("删除录音文件:" + recorderPath + new File(recorderPath).delete());
			mDiseaseVoiceCompletedContainer.setVisibility(View.GONE);
			mDiseaseVoiceContainer.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	long pressedTime = 0;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pressedTime = System.currentTimeMillis();
			mRecordLabel.setText("松手录音完成");
			recordManager.startRecord();
			break;
		case MotionEvent.ACTION_UP:
			if (System.currentTimeMillis() - pressedTime <= 2000) {
				Toast.makeText(getContext(), "录音时间太短", Toast.LENGTH_SHORT).show();
				mRecordLabel.setText("长按开始录音");
				recordManager.stopRecord();
				Trace.d("删除录音文件:" + recorderPath + new File(recorderPath).delete());
				pressedTime = System.currentTimeMillis();
			} else {
				mDiseaseVoiceCompletedContainer.setVisibility(View.VISIBLE);
				mDiseaseVoiceContainer.setVisibility(View.GONE);
				recordManager.stopRecord();
			}
		case MotionEvent.ACTION_CANCEL:
			mDiseaseVoiceCompletedContainer.setVisibility(View.VISIBLE);
			mDiseaseVoiceContainer.setVisibility(View.GONE);
			recordManager.stopRecord();
			break;
		default:
			break;
		}
		return true;
	}

	public String getRecorderVoice() {
		if (new File(recorderPath).exists()) {
			return recorderPath;
		}
		return null;
	}

}
