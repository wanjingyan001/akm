package cn.zsmy.akm.doctor.profile.edit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import cn.zsmy.akm.doctor.utils.DateUtils;
import cn.zsmy.doctor.R;


public class RecordingActivity extends Activity implements SurfaceHolder.Callback {
    private Button start;
    private Button stop;
    private MediaRecorder mediarecorder;
    private SurfaceView surfaceview;
    private SurfaceHolder surfaceHolder;
    private String flag;
    public static String uri;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_recording);
        init();
    }

    private void init() {
        flag = getIntent().getStringExtra("CONTEXT");
        if (flag == null) {
            return;
        }
        start = (Button) this.findViewById(R.id.start);
        stop = (Button) this.findViewById(R.id.stop);
        start.setOnClickListener(new TestVideoListener());
        stop.setOnClickListener(new TestVideoListener());
        surfaceview = (SurfaceView) this.findViewById(R.id.surfaceview);
        SurfaceHolder holder = surfaceview.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        Toast.makeText(this, "请不要拒绝摄像头权限，否则视屏将无法录制", Toast.LENGTH_LONG).show();
    }

    class TestVideoListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            if (v == start) {
                try {
                    start.setClickable(false);
                    mediarecorder = new MediaRecorder();
                    mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H263);
                    mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    mediarecorder.setVideoSize(352, 288);
                    mediarecorder.setAudioSamplingRate(60);
                    mediarecorder.setAudioChannels(1);
                    mediarecorder.setAudioEncodingBitRate(100);
//                    mediarecorder.setVideoFrameRate(20);
                    mediarecorder.setPreviewDisplay(surfaceHolder.getSurface());
                    File file = new File(Environment.getExternalStorageDirectory()
                            + File.separator + "akmdoctor/video/");//"/sdcard/akmdoctor/video");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    uri = file.getPath() + "/" + DateUtils.getCurrentTime() + ".mp4";
                    System.out.println("视频文件2：" + uri);
                    mediarecorder.setOutputFile(uri);
                    try {
                        mediarecorder.prepare();
                        mediarecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (v == stop) {
                start.setClickable(true);
                if (mediarecorder != null) {
                    mediarecorder.stop();
                    mediarecorder.release();
                    mediarecorder = null;
                    finish();
//                        setIntentActivtyResult();

                }
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        surfaceHolder = holder;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceview = null;
        surfaceHolder = null;
        mediarecorder = null;
    }

    public void setIntentActivtyResult() {
        Intent intent = new Intent();
        intent.setAction(uri);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}