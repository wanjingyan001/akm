package cn.zsmy.akm.doctor.http;

import android.content.Context;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import cn.zsmy.akm.doctor.home.MyApplication;


/**
 * Created by qinwei on 2015/10/6. email:qinwei_it@163.com
 */
public class Request {

    public RequestTask task;

    public enum RequestMethod {
        GET, POST, PUT, DELETE
    }

    public String url;
    public String postContent;
    public ArrayList<FileEntity> fileEntities;
    public RequestMethod method;
    public ICallback callback;
    public Map<String, String> headers;
    private Map<String, String> lists;
    private Map<String, List<? extends Object>> strings;
    private boolean enableProgressUpdated;
    public OnGlobalExceptionListener onGlobalExceptionListener;

    public volatile boolean isCancelled;
    public int maxRetryCount = 3;
    public int delayTime;
    public String tag;
    public String filePath;
    private Context context;

    public Request(String url, RequestMethod method, Context context) {
        this.method = method;
        this.url = url;
        this.context = context;
        if (MyApplication.getProfile() != null && MyApplication.getProfile().getAccessToken() != null) {
            addHeader("Cookie", "JSESSIONID=" + MyApplication.getProfile().getAccessToken());
        }
    }

    public Request(String url, Context context) {
        this.method = RequestMethod.GET;
        this.url = url;
        this.context = context;
        if (MyApplication.getProfile() != null && MyApplication.getProfile().getAccessToken() != null) {
            addHeader("Cookie", "JSESSIONID=" + MyApplication.getProfile().getAccessToken());
        }
    }

    public void setCallback(ICallback callback) {
        this.callback = callback;
        if (MyApplication.getProfile() != null && MyApplication.getProfile().getAccessToken() != null) {
            addHeader("Cookie", "JSESSIONID=" + MyApplication.getProfile().getAccessToken());
        }
//        addHeader("Cookie", "JSESSIONID=" + MyApplication.getProfile().getAccessToken());
    }

    public void addHeader(String key, String value) {
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put(key, value);
    }

    public void put(String key, String value) {
        if (lists == null) {
            lists = new HashMap<>();
        }
        lists.put(key, value);
    }

    public void put(String key, List<? extends Object> value) {
        if (strings == null) {
            strings = new HashMap<>();
        }
        strings.put(key, value);
    }


    public void setOnGlobalExceptionListener(OnGlobalExceptionListener listener) {
        this.onGlobalExceptionListener = listener;
    }

    public void setEnableProgressUpdated(boolean enableProgressUpdated) {
        this.enableProgressUpdated = enableProgressUpdated;
    }

    public boolean isEnableProgressUpdated() {
        return enableProgressUpdated;
    }

    public void checkIfCancelled() throws AppException {
        if (isCancelled) {
            throw new AppException(AppException.ErrorType.CANCEL, "the request has been cancelled");
        }
    }

    public void execute(ExecutorService mExecutors) {
        task = new RequestTask(this, context);
        if (Build.VERSION.SDK_INT > 11) {
            task.executeOnExecutor(mExecutors);
        } else {
            task.execute();
        }
    }

    public void cancel(boolean force) {
        isCancelled = true;
        callback.cancel();
        if (force && task != null) {
            task.cancel(true);
        }
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 描述
     *
     * @return
     */
    public Map<String, String> getUrlParameters() {
        return lists;
    }

    public Map<String, List<? extends Object>> getListParameters() {
        return strings;
    }
}
