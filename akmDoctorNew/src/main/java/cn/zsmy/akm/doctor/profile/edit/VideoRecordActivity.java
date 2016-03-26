package cn.zsmy.akm.doctor.profile.edit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.zsmy.akm.doctor.profile.view.MovieRecorderView;
import cn.zsmy.doctor.R;

/**
 * 视频录制
 * Created by Administrator on 2016/3/3.
 */
public class VideoRecordActivity extends Activity implements View.OnClickListener {
    private MovieRecorderView mRecorderView;//视频录制控件
    private Button mShootBtn;//视频开始录制按钮
    private boolean isFinish = true;
    private boolean success = false;//防止录制完成后出现多次跳转事件
    public static String uri;//视频URI
    private String flag;

    public static String mImgUri;//视频缩略图URI

    private boolean hasMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_movierecorder);
        initView();
        flag = getIntent().getStringExtra("CONTEXT");
        if (flag == null) {
            return;
        }
    }

    private void initView() {
        findViewById(R.id.profile_video_cancel).setOnClickListener(this);


        mRecorderView = (MovieRecorderView) findViewById(R.id.movierecroder);
        mShootBtn = (Button) findViewById(R.id.start);

        //用户长按事件监听
        mShootBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //用户按下拍摄按钮
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    mRecorderView.record(new MovieRecorderView.OnRecordFinishListener() {

                        @Override
                        public void onRecordFinish() {
                            //判断用户按下时间是否小于10秒
                            if (!success && mRecorderView.getTimeCount() < 10) {
                                success = true;
                                handler.sendEmptyMessage(1);
                            }
                        }
                    });
                }
                //用户抬起拍摄按钮
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //判断用户按下时间是否大于3秒
                    if (mRecorderView.getTimeCount() > 3) {
                        if (!success) {
                            success = true;
                            handler.sendEmptyMessage(1);
                        }
                    } else {
                        success = false;
                        if (mRecorderView.getmVecordFile() != null)
                            mRecorderView.getmVecordFile().delete();//删除录制的过短视频
                        mRecorderView.stop();//停止录制
                        Toast.makeText(getApplicationContext(), "视频录制时间太短", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
        ViewTreeObserver vto = mRecorderView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (hasMeasure == false) {
                    int width = mRecorderView.getMeasuredWidth();
                    int height = mRecorderView.getMeasuredHeight();
                    System.out.println("视频界面的宽="+width+";视频界面的高="+height);
                    hasMeasure = true;
                }
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        isFinish = true;
        if (mRecorderView.getmVecordFile() != null)
            mRecorderView.getmVecordFile().delete();//视频使用后删除
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isFinish = false;
        success = false;
        mRecorderView.stop();//停止录制
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (success) {
                finishActivity();
            }
        }
    };

    //视频录制结束后，跳转的函数
    private void finishActivity() {
        if (isFinish) {
            mRecorderView.stop();
            uri = mRecorderView.getmVecordFile().toString();
            System.out.println("视频文件：" + uri);
            Intent intent = new Intent(this, PreviewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("video", uri);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        success = false;
    }


    /**
     * 获取视频的缩略图
     *
     * @param imagePath
     * @return
     */
    public static Bitmap getBitmap(String imagePath) {
        if (getAndroidSDKVersion() >= 0) {
            Bitmap bp = ThumbnailUtils.createVideoThumbnail(imagePath,
                    MediaStore.Video.Thumbnails.MINI_KIND);
            return bp;
        } else {
            return null;
        }

    }

    /**
     * 获取当前系统的版本号
     *
     * @return 系统版本号
     */
    public static int getAndroidSDKVersion() {
        int version;
        try {
            version = Integer.valueOf(Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            return -1;
        }
        return version;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_video_cancel:
                VideoRecordActivity.this.finish();
                break;
        }

    }

    /**
     * 保存视频缩略图
     *
     * @param bitmap  bitmap缩略图
     * @param bitName 缩略图文件名
     * @throws IOException
     */
    public static void saveBitMap(Bitmap bitmap, String bitName) throws IOException {
        String path = Environment.getExternalStorageDirectory() + File.separator + "akmdoctor/video/";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File bitmapFile = new File(path + bitName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(bitmapFile));
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bos);
        bos.flush();
        bos.close();
        mImgUri = bitmapFile.getPath();
        System.out.println("filepath:" + bitmapFile.getPath() + ";file大小:" + bitmapFile.length());
    }

}
