package cn.zsmy.akm.doctor.log;

import cn.zsmy.akm.doctor.home.MyApplication;

/**
 * @author yutaotao
 * @time 2016/2/26.
 */
public class LogDBController {
    private static LogDBController instance;
    private LogDBHelper mLogDBHelper;


    private LogDBController() {
        mLogDBHelper = new LogDBHelper(MyApplication.gContext);
        mLogDBHelper.getWritableDatabase();
    }

    public static LogDBController getInstance() {
        if (instance == null) {
            instance = new LogDBController();
        }
        return instance;
    }

    public LogDBHelper getDB(){
        return mLogDBHelper;
    }

}
