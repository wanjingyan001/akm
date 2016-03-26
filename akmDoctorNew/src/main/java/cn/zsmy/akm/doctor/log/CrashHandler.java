package cn.zsmy.akm.doctor.log;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import android.view.Display;
import android.view.WindowManager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

import cn.zsmy.akm.doctor.home.MyApplication;

/**
 * 全局捕获异常
 * <p/>
 * 当程序发生Uncaught异常的时候,有该类来接管程序,并记录错误日志
 *
 * @author yutaotao
 * @time 2016/2/26
 * <p/>
 * 修改时间 2016/2/29  修改内容将数据写入数据库
 */
@SuppressLint("SimpleDateFormat")
public class CrashHandler implements UncaughtExceptionHandler {

    public static String TAG = "AkmDoctor";
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private static CrashHandler instance = new CrashHandler();
    private Context mContext;

    private LogError mLogError;


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
            SystemClock.sleep(3000);
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
     * @param ex 崩溃错误
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
            String app_type = "1";//掌尚名医工作室

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
            long log_time = System.currentTimeMillis();

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
            String log_info = writer.toString();
            mLogError = new LogError();
            mLogError.setUser_id(user_id);
            mLogError.setScreen_resolution(screen_resolution);
            mLogError.setApp_type(app_type);
            mLogError.setClient_ver(client_ver);
            mLogError.setApp_ver(app_ver);
            mLogError.setModel(model);
            mLogError.setClient_os(client_os);
            mLogError.setLog_time(log_time);
            mLogError.setLog_info(log_info);
            mLogError.setLog_type("1");
            mLogError.setIndex("0");
            LogController.addOrUpdate(mLogError);
            uploadExceptionToServer(mLogError);
        }
    }

    public static void setTag(String tag) {
        TAG = tag;
    }

    /**
     * 上传服务器
     *
     * @param logError
     */
    private void uploadExceptionToServer(final LogError logError) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//        String logTime = sdf.format(new Date(logError.getLog_time()));
//        Request request = new Request(UrlHelper.LOG_UPLOAD, Request.RequestMethod.POST, mContext);
//        request.put("clientOs", logError.getClient_os());
//        request.put("clientVer", logError.getClient_ver());
//        request.put("appType", logError.getApp_type());
//        request.put("appVer", logError.getApp_ver());
//        request.put("model", logError.getModel());
//        request.put("screenResolution", logError.getScreen_resolution());
//        request.put("userId", logError.getUser_id());
//        request.put("logTime", logTime);
//        request.put("logInfo", logError.getLog_info());
//        request.put("logType", logError.getLog_type());
//        request.setCallback(new StringCallback() {
//            @Override
//            public void onSuccess(String result) {
//                Toast.makeText(mContext,"上传日志信息成功",Toast.LENGTH_LONG).show();
//
//            }
//        });
//        RequestManager.getInstance().execute(mContext.toString(), request);

    }

}
