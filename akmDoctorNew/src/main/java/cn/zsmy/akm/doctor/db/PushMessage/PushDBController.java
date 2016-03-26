package cn.zsmy.akm.doctor.db.PushMessage;


import cn.zsmy.akm.doctor.home.MyApplication;

/**
 * Created by Administrator on 2016/2/25.
 */
public class PushDBController {
    private static PushDBController instance;
    private PushMsgDBHelper mDBhelper;

    private PushDBController() {
        mDBhelper = PushMsgDBHelper.getDbHelper(MyApplication.gContext);
        mDBhelper.getWritableDatabase();
    }

    public static PushDBController getInstance() {
        if (instance == null) {
            instance = new PushDBController();
        }
        return instance;
    }

    public PushMsgDBHelper getDB() {
        return mDBhelper;
    }


}
