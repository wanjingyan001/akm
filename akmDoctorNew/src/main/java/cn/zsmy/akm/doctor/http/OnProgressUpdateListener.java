package cn.zsmy.akm.doctor.http;

/**
 * Created by qinwei on 2015/10/6.
 * email:qinwei_it@163.com
 */
public interface OnProgressUpdateListener {
    void onProgressUpdated(long curPos, long contentLength);
}
