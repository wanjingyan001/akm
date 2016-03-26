package cn.zsmy.akm.doctor.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.zsmy.akm.doctor.chat.utils.ProgressDialogUtils;

/**
 * 就医建议上传
 * Created by Administrator on 2016/1/17.
 */
public class LoadAsyncTask extends AsyncTask<String, Void, String> {
    private Context context;
    private Map<String, String> durgEntity;
    private String caseId;


    public LoadAsyncTask(Context context, Map<String, String> durgEntity,String caseId) {
        this.context = context;
        this.durgEntity = durgEntity;
        this.caseId = caseId;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ProgressDialogUtils.showProgressDialog(context, "上传中");
    }

    @Override
    protected String doInBackground(String... params) {
        String submit = submit(params[0]);
        return submit;
    }


    private String submit(String path) {
        String result = "";
        List<String> ids = new ArrayList<>();
        List<String> nums = new ArrayList<>();
        for (Map.Entry<String, String> entry : durgEntity.entrySet()) {
            ids.add(entry.getKey());
            nums.add(entry.getValue());
        }
//        byte[] bytes = getRequestData(durgEntity, "UTF-8").toString().getBytes();
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String caseId = URLEncoder.encode(this.caseId, "UTF-8");
            String id = URLEncoder.encode(ids.toString(), "UTF-8");
            String num = URLEncoder.encode(nums.toString(), "UTF-8");
            String data = "caseId"+ caseId +"medicineId"+id+"num"+num;
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data.getBytes());
            outputStream.flush();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = httpURLConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                is.close();
                baos.close();
                result = new String(baos.toByteArray());
            }
            return getMessage(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String getMessage(String msg) {
        String message = "";
        try {
            JSONObject object = new JSONObject(msg);
            message = object.getString("message");
            return message;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }


//    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
//
//        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
//        try {
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                stringBuffer.append("")
//                        .append("=")
//                        .append(URLEncoder.encode(entry.getValue(), encode))
//                        .append("&");
//            }
//            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return stringBuffer;
//    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ProgressDialogUtils.closeProgressDialog(context);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

    }
}
