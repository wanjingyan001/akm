package cn.zsmy.akm.home;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import cn.zsmy.akm.chat.im.IMPushManager;
import cn.zsmy.akm.entity.Profile;
import cn.zsmy.akm.entity.ProfileDetails;
import cn.zsmy.akm.entity.UserInfo;
import cn.zsmy.akm.log.CrashHandler;

public class MyApplication extends Application {
    public static final int APP_STATE_STARTED = 1;//app 开启
    public static final int APP_STATE_LOGINED = 2;//app 登陆
    public static Context gContext;
    private int app_state;
    private static Profile profile;
    private static ProfileDetails.DataEntity profileDetails;
    private static UserInfo.DataEntity userInfo;
    private static MyApplication mInstance;
    public static boolean isNetConnected = true;
    private List<Activity> mList = new LinkedList<Activity>();

    public static MyApplication getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
//        SDKInitializer.initialize(getApplicationContext());
        super.onCreate();
        app_state = -1;//-1 app未开启 或者 被强杀导致
        mInstance = this;
        gContext = getApplicationContext();
        initializeImageloader();
        /**
         * 修改人 yutaotao
         * 修改时间 2016/2/29
         * 修改内容 添加全局异常处理记录
         */
        //为应用设置异常处理程序，程序才能捕获未处理的异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        if (profile != null) {
            if (profile.getUserId() != null) {
                IMPushManager.getInstance(getApplicationContext()).startPush();
                IMPushManager.getInstance(getApplicationContext()).setTags(
                        profile.getUserId());
            }
        }
    }

    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mList.clear();
            IMPushManager.getInstance(getApplicationContext()).stopPush();
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    public List<Activity> getmList() {

        return mList;
    }

    public void setmList(List<Activity> mList) {
        this.mList = mList;
    }

    public static void setProfile(Profile tmp) {
        profile = tmp;
    }

    public static Profile getProfile() {
        return profile;
    }

    public void clearProfile() {
        profile = null;
    }

    public int getAppState() {
        return app_state;
    }

    public void setAppState(int state) {
        this.app_state = state;
    }


    public static ProfileDetails.DataEntity getProfileDetails() {
        return profileDetails;
    }

    public static void setProfileDetails(ProfileDetails.DataEntity profileDetails) {
        MyApplication.profileDetails = profileDetails;
    }

    public static UserInfo.DataEntity getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(UserInfo.DataEntity userInfo) {
        MyApplication.userInfo = userInfo;
    }

    public void initializeImageloader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        String cacheDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "akeman" + File.separator + "cache";
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(getApplicationContext());
        config.defaultDisplayImageOptions(options);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.memoryCache(new FIFOLimitedMemoryCache(2 * 1024 * 1024));
        config.memoryCacheSize(2 * 1024 * 1024);
        config.diskCache(new UnlimitedDiskCache(new File(cacheDir)));
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
//        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
