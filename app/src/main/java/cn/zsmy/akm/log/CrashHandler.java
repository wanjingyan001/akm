package cn.zsmy.akm.log;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;

/**
 * 全局捕获异常
 * 当程序发生Uncaught异常的时候,有该类来接管程序,并记录错误日志
 *
 * @author yutaotao
 * @time 2016/2/26
 */
@SuppressLint("SimpleDateFormat")
public class CrashHandler implements UncaughtExceptionHandler {

    public static String TAG = "AkmPatient";
    // 系统默认的UncaughtException处理类
    private UncaughtExceptionHandler mDefaultHandler;

    private static CrashHandler instance = new CrashHandler();
    private Context mContext;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return instance;
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
//        autoClear(5);
        uploadExceptionToServer();
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
//            SystemClock.sleep(3000);
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex 崩溃异常
     * @return true:如果处理了该异常信息; 否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null)
            return false;
        try {
            // 保存日志数据
            saveCrashInfoFile(ex);
            //向服务器上传异常数据
            uploadExceptionToServer();
            // 重启应用（按需要添加是否重启应用）
//            Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            mContext.startActivity(intent);
//            SystemClock.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 保存错误信息到本地数据库
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private void saveCrashInfoFile(Throwable ex) throws Exception {

        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                PackageManager.GET_ACTIVITIES);
        if (pi != null) {
            //客户端系统 1安卓系统
            String client_os = "1";
            //客户端版本
            String client_ver = Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT;
            //应用端类型
            String app_type = "2";//阿克曼皮肤医生
            //应用端版本
            String app_ver = pi.versionName + "_" + pi.versionCode;
            //机型
            String model = Build.MANUFACTURER + "_" + Build.MODEL;
            //屏幕分辨率
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            String screen_resolution = display.getWidth() + "*" + display.getHeight();
            //用户ID
            String user_id = MyApplication.getProfile().getUserId();
            //日志时间
            long current = System.currentTimeMillis();
            Date log_time = new Date(current);

            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.flush();
            printWriter.close();
            //获取到崩溃错误内容
            String result = writer.toString();
            //写入数据
            LogError logError = new LogError();
            logError.setUser_id(user_id);
            logError.setScreen_resolution(screen_resolution);
            logError.setApp_type(app_type);
            logError.setClient_ver(client_ver);
            logError.setApp_ver(app_ver);
            logError.setModel(model);
            logError.setClient_os(client_os);
            logError.setLog_time(log_time);
            logError.setLog_info(result);
            logError.setIndex("0");
            String info = logError.toString();
            //写入本地数据库
            LogController.addOrUpdate(logError);
        }
    }

    public static void setTag(String tag) {
        TAG = tag;
    }


    /**
     * 上传服务器
     */
    private void uploadExceptionToServer() {

    }

}
