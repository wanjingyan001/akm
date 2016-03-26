package cn.zsmy.akm.http;

import android.util.Log;
import android.webkit.URLUtil;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import cn.zsmy.akm.utils.TextUtil;


/**
 * Created by qinwei on 2015/10/6. email:qinwei_it@163.com
 */
public class HttpURLConnectionUtil {
    private static final int CONNECT_TIME_OUT = 8 * 1000;
    private static final int READ_TIME_OUT = 8 * 1000;

    public static HttpURLConnection execute(Request request) throws AppException {
        if (!URLUtil.isNetworkUrl(request.url)) {
            throw new AppException(AppException.ErrorType.URL_NOT_VALID, "the url:" + request.url + " is not valid");
        }
        switch (request.method) {
            case GET:
            case DELETE:
                return get(request);
            case POST:
            case PUT:
                return post(request);
        }
        return null;
    }

    public static HttpURLConnection get(Request request) throws AppException {
        request.checkIfCancelled();
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(request.url).openConnection();

            connection.setRequestMethod(Request.RequestMethod.GET.name());// 提交
            connection.setConnectTimeout(CONNECT_TIME_OUT);
            connection.setReadTimeout(READ_TIME_OUT);
            addHeaders(connection, request.headers);
            request.checkIfCancelled();
            return connection;
        } catch (InterruptedIOException e) {
            throw new AppException(AppException.ErrorType.TIMEOUT, e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.IO, e.getMessage());
        }
    }

    public static HttpURLConnection post(Request request) throws AppException {
        String  boundary = "ahhjifeohiawf";
        request.checkIfCancelled();

        HttpURLConnection connection = null;
        OutputStream os = null;
        try {
            connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(Request.RequestMethod.POST.name());
            connection.setConnectTimeout(CONNECT_TIME_OUT);
            connection.setReadTimeout(READ_TIME_OUT);
            connection.setDoOutput(true);
            request.addHeader("connection", "keep-alive");
            request.addHeader("Charsert", "UTF-8");
            if (request.callback != null && request.callback instanceof FileCallback) {
                request.addHeader("Content-Type", "multipart/form-data" + ";boundary=7d4a6d158c9");
            }
            addHeaders(connection, request.headers);
            os = connection.getOutputStream();
            if (TextUtil.isValidate(request.getListParameters()) && request.fileEntities == null) {
                Log.d("WJY-------", getListParams(request.getListParameters()));
                os.write(getListParams(request.getListParameters()).getBytes());
            } else  if (TextUtil.isValidate(request.getUrlParameters())&&request.fileEntities == null) {
                os.write(getParams(request.getUrlParameters()).getBytes());
                Log.i("TAG>>>>>>>>>>>>>>>>1", getParams(request.getUrlParameters()) + "" + request.fileEntities);
            } else if (request.filePath != null) {
                UploadUtil.upload(os, request.filePath);
            } else if (request.fileEntities != null&&request.postContent!=null) {
                UploadUtil.upload(os, request.postContent, request.fileEntities);
            } else if (request.fileEntities != null&&TextUtil.isValidate(request.getUrlParameters())) {
                Log.i("TAG<<<<<<<<<",getParams(request.getUrlParameters()) +""+request.fileEntities.get(0).getFileType());
                UploadUtil.upload(os, getParams(request.getUrlParameters()), request.fileEntities);
            } else if (request.postContent != null) {
                os.write(request.postContent.getBytes());// 提交post的数据
            } else {
                throw new AppException(AppException.ErrorType.MUNAL, "the post request has no post content");
            }
            request.checkIfCancelled();
        } catch (InterruptedIOException e) {
            throw new AppException(AppException.ErrorType.TIMEOUT, e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.IO, e.getMessage());
        } finally {
            try {
                if(os!=null){
                    os.flush();
                    os.close();
                }

            } catch (IOException e) {
                throw new AppException(AppException.ErrorType.IO, "the post outputstream can't be closed");
            }
        }
        return connection;
    }

    private static void addHeaders(HttpURLConnection connection, Map<String, String> headers) {
        if (headers == null || headers.size() == 0)
            return;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private static String getParams(Map<String, String> urlParameters) {
        String params = "";
        int i = 0;
        for (Map.Entry<String, String> e : urlParameters.entrySet()) {
            params += e.getKey() + "=" + e.getValue();
            if (i != urlParameters.size() - 1) {
                params += "&";
            }
            i++;
        }
        return params;
    }


    private static String getListParams(Map<String, List<? extends Object>> urlParameters) {
        String params = "";
        int i = 0;
        for (Map.Entry<String, List<? extends Object>> e : urlParameters.entrySet()) {
            params += e.getKey() + "=" + e.getValue();
            if (i != urlParameters.size() - 1) {
                params += "&";
            }
            i++;
        }
        return params;
    }
}
