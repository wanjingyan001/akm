package cn.zsmy.akm.doctor.profile.edit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.doctor.R;

/**
 * 视频预览
 * Created by Administrator on 2016/3/18.
 */
public class PreviewActivity extends Activity implements View.OnClickListener {
    private TextView mVideoAgain, mVideoSure;//重拍，确定控件
    private SurfaceView mPlayView;//视频播放控件
    private MediaPlayer mMediaPlayer;
    private int position = 0;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_movie_preview);
        initView();
        initData();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initData() {
        uri = getIntent().getStringExtra("video");
        System.out.println("视频地址:" + uri);
//        Bitmap bitmap = VideoRecordActivity.getBitmap(uri);
//        Drawable drawable=new BitmapDrawable(bitmap);
//        mPlayView.setBackground(drawable);
    }

    private void initView() {
        mPlayView = (SurfaceView) findViewById(R.id.movie_check);
        mVideoAgain = (TextView) findViewById(R.id.video_again);
        mVideoSure = (TextView) findViewById(R.id.video_sure);

        mVideoAgain.setOnClickListener(this);
        mVideoSure.setOnClickListener(this);
        mPlayView.getHolder().setFixedSize(320, 240);//设置分辨率
        mPlayView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//设置surfaceview不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到用户面前
        if (uri != null) {
            mPlayView.getHolder().addCallback(new SurceCallBack());//对surface对象的状态进行监听
        }
        mMediaPlayer = new MediaPlayer();
        mPlayView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_again:
                Intent intent = new Intent(this, VideoRecordActivity.class);
                intent.putExtra("CONTEXT", Constants.UPLOAD_RECORD_VALUES);
                startActivity(intent);
                break;

            case R.id.video_sure:
                finish();
                break;

            case R.id.movie_check:
                play();
                break;
        }

    }


    private final class SurceCallBack implements SurfaceHolder.Callback {
        /**
         * 画面创建
         */
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (position > 0 || uri != null) {
                play();
                mMediaPlayer.seekTo(position);
                position = 0;
            }
        }

        /**
         * 画面修改
         */
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


        }

        /**
         * 画面销毁
         */
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mMediaPlayer.isPlaying()) {
                position = mMediaPlayer.getCurrentPosition();
                mMediaPlayer.stop();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void play() {
        try {
            mMediaPlayer.reset();//重置为初始状态
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置音乐流的类型
            mMediaPlayer.setDisplay(mPlayView.getHolder());//设置video影片以surfaceviewholder播放
            mMediaPlayer.setDataSource(uri);//设置路径
            mMediaPlayer.prepare();//缓冲
            mMediaPlayer.start();//播放
            mPlayView.setBackground(null);
        } catch (IOException e) {
            System.out.println("视频异常=" + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
