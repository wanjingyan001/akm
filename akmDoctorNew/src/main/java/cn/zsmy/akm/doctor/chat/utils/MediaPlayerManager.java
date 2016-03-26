package cn.zsmy.akm.doctor.chat.utils;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class MediaPlayerManager implements OnCompletionListener {
	private final String TAG = UPlayer.class.getName();
	private static MediaPlayerManager mInstance;
	private MediaPlayer mPlayer;

	public interface OnPlayListnener {
		void onCompletion();
	}

	private OnPlayListnener listnener;

	private MediaPlayerManager() {
		mPlayer = new MediaPlayer();
		mPlayer.setOnCompletionListener(this);
	}

	public static MediaPlayerManager getInstance() {
		if (mInstance == null) {
			mInstance = new MediaPlayerManager();
		}
		return mInstance;
	}

	public void start(String path, OnPlayListnener listnener) {
		this.listnener = listnener;
		try {
			if (mPlayer.isPlaying())
				mPlayer.reset();
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
				mInstance=null;
				System.gc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		mp.reset();
		if (listnener != null) {
			listnener.onCompletion();
		}
	}
}
