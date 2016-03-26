package cn.zsmy.akm.widget.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.zsmy.akm.R;
import cn.zsmy.akm.utils.FileUtil;
import cn.zsmy.akm.utils.UrlHelpper;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sanz on 2015/12/29 11:08
 */
public class PlayVideoView extends LinearLayout implements View.OnClickListener, MediaPlayer.OnCompletionListener {
    private Context context;
    public static final String TAG = "VideoPlayer";
    private VideoView mVideoView;
    private Uri mUri;
    private int mPositionWhenPaused = -1;
    private MediaController mMediaController;
    private CircleImageView play_view;
    private OnStopPlayListerner stopPlayListerner;
    private String url,previewPath;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setVideoFile();
        }
    };
    private ImageView mPreviewImg;

    public PlayVideoView(Context context) {
        super(context);
        this.context = context;
        initializeView();
    }

    public PlayVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //    public PlayVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    private void initializeView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_doctor_detail_video, this);
        mVideoView = (VideoView) view.findViewById(R.id.video_view);
        play_view = (CircleImageView) view.findViewById(R.id.play_sub);
        mPreviewImg = ((ImageView) view.findViewById(R.id.preview_image));
        play_view.setOnClickListener(this);
        mPreviewImg.setOnClickListener(this);
        //Create media controller
        mMediaController = new MediaController(context);
        mMediaController.setVisibility(View.INVISIBLE);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setOnCompletionListener(this);
    }

    public void restartPlay() {
        if (mPositionWhenPaused >= 0) {
            Log.d(TAG, "restartPlay: mPositionWhenPaused = " + mPositionWhenPaused);
            mVideoView.resume();
//            mVideoView.seekTo(5);
            mPositionWhenPaused = -1;
            mVideoView.start();
        }
    }

    public void pausePlay() {
        mPositionWhenPaused = mVideoView.getCurrentPosition();
        mVideoView.pause();
        Log.d(TAG, "OnStop: mPositionWhenPaused = " + mPositionWhenPaused);
        Log.d(TAG, "OnStop: getDuration  = " + mVideoView.getDuration());
    }

    public void startPlay() {
        if (mUri != null) {
            mVideoView.setVideoURI(mUri);
            mVideoView.start();
        }
    }

    @Override
    public void onClick(View v) {
        mPreviewImg.setVisibility(GONE);
        setVideoFile();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        play_view.setVisibility(View.VISIBLE);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
        ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + previewPath, mPreviewImg);
    }

    public void setVideoFile() {
        Log.i("TAG", url + ">>>>>>>>>>>>");
        FileUtil fileUtil = new FileUtil();
        String filePath = fileUtil.getVoiceDir();
        File directory = new File(filePath);
        File[] files = directory.listFiles();
        if (files.length == 0) {
            if (url != null) {
                URL u = null;
                try {
                    u = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                videoDownding(u, new File(filePath + "/" + getVideoName(url)));
            } else {
                Toast.makeText(context, "无视频", Toast.LENGTH_LONG).show();
            }
        } else {
            File f_new = null;
            File f_old = null;
            for (int i = 0; i < files.length; i++) {
                f_new = files[i];
                Log.i("TAG", f_new.getName());
                if (url == null) {
                    Toast.makeText(context, "无视频", Toast.LENGTH_LONG).show();
                } else {
                    if (getVideoName(url).equals(f_new.getName())) {
                        mUri = Uri.parse(f_new.getAbsolutePath());
                    } else {
                        f_new.delete();
                        try {
                            URL u = new URL(url);
                            videoDownding(u, new File(filePath + "/" + getVideoName(url)));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                    }
                    play_view.setVisibility(View.GONE);
                    startPlay();
                }
            }
        }
    }

    private String getVideoName(String url) {
        String str[] = url.split("/");
        String videoName = str[str.length - 1];
        return videoName;
    }

    public interface OnStopPlayListerner {
        public void stopPlayListerner(MediaPlayer mp);
    }


    private void videoDownding(final URL downloadUrl, final File file) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedInputStream bis = null;
                RandomAccessFile raf = null;

                try {
                    HttpURLConnection conn = (HttpURLConnection) downloadUrl.openConnection();
                    conn.setAllowUserInteraction(true);
                    byte[] buffer = new byte[1024];
                    bis = new BufferedInputStream(conn.getInputStream());
                    raf = new RandomAccessFile(file, "rwd");
                    int len;
                    while ((len = bis.read(buffer, 0, 1024)) != -1) {
                        raf.write(buffer, 0, len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (raf != null) {
                        try {
                            raf.close();
                            handler.sendEmptyMessage(0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }
}
