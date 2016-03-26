package cn.zsmy.akm.doctor.chat.view;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AudioManager {
	private MediaRecorder mMediaRecorder;
	private String mDir;
	private String mCurrentFilePath;

	private static AudioManager mInstance;

	private boolean isPrepare;

	private AudioManager(String dir) {
		mDir = dir;
	}

	public static AudioManager getInstance(String dir) {
		if (mInstance == null) {
			synchronized (AudioManager.class) {
				if (mInstance == null) {
					mInstance = new AudioManager(dir);
				}
			}
		}
		return mInstance;
	}

	/**
	 * ʹ�ýӿ� ���ڻص�
	 */
	public interface AudioStateListener {
		void wellPrepared();
	}

	public AudioStateListener mAudioStateListener;

	/**
	 * �ص�����
	 */
	public void setOnAudioStateListener(AudioStateListener listener) {
		mAudioStateListener = listener;
	}

	public void prepareAudio() {
		try {
			isPrepare = false;
			File dir = new File(mDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String fileName = generateFileName();
			File file = new File(dir, fileName);

			mCurrentFilePath = file.getAbsolutePath();

			mMediaRecorder = new MediaRecorder();
			mMediaRecorder.setOutputFile(file.getAbsolutePath());
			mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
			mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

			mMediaRecorder.prepare();
			mMediaRecorder.start();
			isPrepare = true;
			if (mAudioStateListener != null) {
				mAudioStateListener.wellPrepared();
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String generateFileName() {
		return UUID.randomUUID().toString() + ".amr";
	}

	public int getVoiceLevel(int maxlevel) {
		if (isPrepare) {
			try {
				// mMediaRecorder.getMaxAmplitude() 1~32767
				return maxlevel * mMediaRecorder.getMaxAmplitude() / 32768 + 1;
			} catch (Exception e) {
			}
		}
		return 1;
	}

	public void release() {
		if (mMediaRecorder != null) {
			mMediaRecorder.stop();
			mMediaRecorder.reset();
			mMediaRecorder = null;
		}
	}

	public void cancel() {
		release();
		if (mCurrentFilePath != null) {
			File file = new File(mCurrentFilePath);
			file.delete();
			mCurrentFilePath = null;
		}

	}

	public String getCurrentFilePath() {

		return mCurrentFilePath;
	}
}