package cn.zsmy.akm.doctor.http;

import java.net.HttpURLConnection;

/**
 * Created by qinwei on 2015/10/6.
 * email:qinwei_it@163.com
 */
public interface ICallback<T> {
    void onSuccess(T result);

//    void onFailure(AppException e);

    T preRequest(String url);

    T parse(HttpURLConnection connection, OnProgressUpdateListener listener) throws AppException;

    T postRequest(T t);

    void onProgressUpdated(long curPos, long contentLength);

    void cancel();


}
