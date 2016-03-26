package cn.zsmy.akm.doctor.http;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.HttpURLConnection;

import cn.zsmy.akm.doctor.admissions.DoctorSuggestActivity;
import cn.zsmy.akm.doctor.admissions.DoctorsRecommend;
import cn.zsmy.akm.doctor.admissions.SelectHospitalActivity;
import cn.zsmy.akm.doctor.chat.ChatActivity;
import cn.zsmy.akm.doctor.chat.utils.ProgressDialogUtils;
import cn.zsmy.akm.doctor.conversation.MyPatientActivity;
import cn.zsmy.akm.doctor.conversation.PatientDetailActivity;
import cn.zsmy.akm.doctor.conversation.PostDetailActivity;
import cn.zsmy.akm.doctor.conversation.PublishedActivity;
import cn.zsmy.akm.doctor.conversation.ReplyPostActivity;
import cn.zsmy.akm.doctor.home.HomeActivity;
import cn.zsmy.akm.doctor.home.MainActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.learning.CaseListActivity;
import cn.zsmy.akm.doctor.learning.ScholarshipActivity;
import cn.zsmy.akm.doctor.login.activity.LoginActivity;
import cn.zsmy.akm.doctor.profile.MyServiceActivity;
import cn.zsmy.akm.doctor.profile.ProfileInfoActivity;
import cn.zsmy.akm.doctor.widget.dialog.ChooseDialog;

/**
 * Created by qinwei on 2015/10/6. email:qinwei_it@163.com
 */
public class RequestTask extends AsyncTask<Void, Long, Object> {
    private Request request;
    private Context context;

    public RequestTask(Request request, Context context) {
        this.request = request;
        this.context = context;
        String name = context.getClass().getSimpleName();
        if (name.equals(ProfileInfoActivity.class.getSimpleName())) {
            ProgressDialogUtils.showProgressDialog(context, "上传中");
        }
        if ((name.equals(ChatActivity.class.getSimpleName()) || name.equals(PostDetailActivity.class.getSimpleName()))
                && request.method == Request.RequestMethod.POST) {
            ProgressDialogUtils.showProgressDialog(context, "请等候");
        }
        if (name.equals(MyServiceActivity.class.getSimpleName()) && request.method == Request.RequestMethod.POST) {
            ProgressDialogUtils.showProgressDialog(context, "提交中");
        } else if (name.equals(ScholarshipActivity.class.getSimpleName())) {
        } else if (name.equals(MainActivity.class.getSimpleName())) {
        } else if (name.equals(CaseListActivity.class.getSimpleName())) {
        } else if (name.equals(ProfileInfoActivity.class.getSimpleName())) {
        } else if (name.equals(DoctorSuggestActivity.class.getSimpleName())) {
        } else if (name.equals(ChatActivity.class.getSimpleName())) {
        } else if (name.equals(PatientDetailActivity.class.getSimpleName())) {
        } else if (name.equals(ReplyPostActivity.class.getSimpleName())) {
        } else if (name.equals(PostDetailActivity.class.getSimpleName())) {
        } else if (name.equals(SelectHospitalActivity.class.getSimpleName())) {
        } else if (name.equals(MyPatientActivity.class.getSimpleName())) {
        } else if (name.equals(PublishedActivity.class.getSimpleName())) {
        } else {
            if (context instanceof Activity) {
                ProgressDialogUtils.showProgressDialog(context, "正在加载");
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... params) {

        if (request.delayTime > 0) {
            try {
                Thread.sleep(request.delayTime);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Object o = request.callback.preRequest(request.url);
        if (o != null) {
            return o;
        }
        return doRequest(0);
    }

    public Object doRequest(int retry) {
        try {
            HttpURLConnection connection = HttpURLConnectionUtil.execute(request);
            if (request.isEnableProgressUpdated()) {
                return request.callback.parse(connection, new OnProgressUpdateListener() {
                    @Override
                    public void onProgressUpdated(long curPos, long contentLength) {
                        publishProgress(curPos, contentLength);
                    }
                });
            } else {
                return request.callback.parse(connection, null);
            }

        } catch (AppException e) {
//            if (e.type == AppException.ErrorType.TIMEOUT) {
//                if (retry < request.maxRetryCount)
//                    retry++;
//                doRequest(retry);
//            }
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof AppException) {
            AppException e = (AppException) o;
            if (e.type == AppException.ErrorType.TIMEOUT) {
                Toast.makeText(context, "连接超时，请检查网络", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "网络错误，请重试", Toast.LENGTH_LONG).show();
            }

            if (request.onGlobalExceptionListener != null) {
                if (!request.onGlobalExceptionListener.handleException(e)) {
                    e.printStackTrace();
                    ProgressDialogUtils.closeProgressDialog(context);
//					request.callback.onFailure(e);
                }
            } else {
//				request.callback.onFailure(e);
                e.printStackTrace();
                ProgressDialogUtils.closeProgressDialog(context);
                if (context instanceof MainActivity) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                    ((MainActivity) context).finish();
                }
                if (context instanceof DoctorsRecommend) {
                    ProgressDialogUtils.closeProgressDialog(context);
                    Toast.makeText(context, "数据获取失败，请重试", Toast.LENGTH_SHORT).show();
                    ((DoctorsRecommend) context).finish();
                }
            }

        } else {
            try {
                String json = (String) o;
                if (json.contains("{")) {
                    json = json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1);
                    json = json.replaceAll("\\\\", "");
                    JSONObject obj = new JSONObject(json);
                    String result = obj.optString("code");
                    if (!result.equals("SUCC")) {
                        Log.i("TAG", context.getClass() + ">>>>>>>>>>" + json);
                        String mes = obj.optString("message");
                        if (result.equals("unLogin")) {
                            ChooseDialog dialog = new ChooseDialog(context, 0);
                            dialog.show();
                        }
                        if (result.equals("user.login.unlogin")) {
                            Toast.makeText(context, mes, Toast.LENGTH_LONG).show();
                            MyApplication.getInstance().exit();
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                        }
                        if (!result.equals("NO_DATA")) {
                            Toast.makeText(context, mes, Toast.LENGTH_LONG).show();
                        }
                        if (result.equals("NO_DATA")) {
                            request.callback.onSuccess(o);
                        }
                    } else {
                        Log.i("TAG", context.getClass() + ">>>>>>>>>>" + json);
                        request.callback.onSuccess(o);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ProgressDialogUtils.closeProgressDialog(context);

    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        request.callback.onProgressUpdated(values[0], values[1]);
    }
}
