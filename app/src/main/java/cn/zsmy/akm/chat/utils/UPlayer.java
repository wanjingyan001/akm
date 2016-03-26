package cn.zsmy.akm.chat.utils;

import android.media.MediaPlayer;
import android.util.Log;

public class UPlayer {

	private final String TAG = UPlayer.class.getName();

	private MediaPlayer mPlayer;

	public UPlayer() {
	}

	public void start(String path) {
		releaseMedia();
		mPlayer = new MediaPlayer();
		try {
			if (mPlayer.isPlaying()) {
				mPlayer.reset();
			}
			// 设置要播放的文件
			mPlayer.setDataSource(path);
			mPlayer.prepare();
			// 播放
			mPlayer.start();
		} catch (Exception e) {
			Log.e(TAG, "prepare() failed");
		}

	}

	public void stop() {
		if (mPlayer != null && mPlayer.isPlaying()) {
			mPlayer.stop();
		}
	}

	public void releaseMedia() { // 这里是释放mediaPlayer播放对象
		if (mPlayer != null) {
			try {
				mPlayer.release();
				mPlayer = null;
				System.gc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}