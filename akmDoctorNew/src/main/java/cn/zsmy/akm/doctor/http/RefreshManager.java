package cn.zsmy.akm.doctor.http;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求刷新机制
 * Created by qinwei on 2015/11/20 16:55
 */
public class RefreshManager {
    private static RefreshManager mInstance;
    private Map<String,Long> refreshCache=new HashMap<>();
    public static RefreshManager getInstance() {
        if (mInstance == null) {
            mInstance = new RefreshManager();
        }
        return mInstance;
    }

//    public boolean isCanRefresh(String clazz){
//
//    };
}
