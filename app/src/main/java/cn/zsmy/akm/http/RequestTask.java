package cn.zsmy.akm.http;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.HttpURLConnection;

import cn.zsmy.akm.doctor.DoctorListActivity;
import cn.zsmy.akm.doctor.FamousDoctorListActivity;
import cn.zsmy.akm.home.HomeActivity;
import cn.zsmy.akm.home.LoginActivity;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.home.SplashActivity;
import cn.zsmy.akm.interrogation.InterrogationInputActivity;
import cn.zsmy.akm.salon.CommunityList;
import cn.zsmy.akm.salon.activity.PlatformListActivity;
import cn.zsmy.akm.salon.activity.PostDetailActivity;
import cn.zsmy.akm.widget.dialog.ChooseDialog;
import cn.zsmy.akm.widget.dialog.ProgressDialogUtils;

/**
 * Created by qinwei on 2015/10/6. email:qinwei_it@163.com
 */
public class RequestTask extends AsyncTask<Void, Long, Object> {
    private ChooseDialog dialog;
    private Request request;
    private Context context;


    public RequestTask(Request request, Context context) {
        this.request = request;
        this.context = context;
        String name = context.getClass().getSimpleName();
        if (name.equals(InterrogationInputActivity.class.getSimpleName())) {
            ProgressDialogUtils.showProgressDialog(context, "上传中");
        } else if (name.equals(HomeActivity.class.getSimpleName())) {
        } else if (name.equals(PlatformListActivity.class.getSimpleName())) {
        } else if (name.equals(SplashActivity.class.getSimpleName())) {
        } else if (name.equals(DoctorListActivity.class.getSimpleName()) || name.equals(FamousDoctorListActivity.class.getSimpleName())) {
        } else if (name.equals(PostDetailActivity.class.getSimpleName())) {
        } else if (name.equals(CommunityList.class.getSimpleName())) {
        } else {
            if (context instanceof Activity) {
                ProgressDialogUtils.showProgressDialog(context, "正在加载");
            }
        }
    }

    public RequestTask(Context context, ChooseDialog dialog, Request request) {
        this(request, context);
        this.dialog = dialog;
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
                    ProgressDialogUtils.closeProgressDialog();
//					request.callback.onFailure(e);
                }
            } else {
//				request.callback.onFailure(e);
                e.printStackTrace();
                if (context instanceof SplashActivity) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                    ((SplashActivity) context).finish();
                }
                ProgressDialogUtils.closeProgressDialog();
            }
        } else {
            try {
                String json = (String) o;
                if (json.contains("{")) {
//                    json = json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1);
//                    json = json.replaceAll("\\\\", "");
                    JSONObject obj = new JSONObject(json);
                    String result = obj.optString("code");
                    Log.i("TAG", context.getClass() + ">>>>>>>>>>" + json);
                    if (!result.equals("SUCC")) {
                        String mes = obj.optString("message");
                        if (result.equals("unLogin") && !context.getClass().getSimpleName().equals(HomeActivity.class.getSimpleName())) {
                            if (dialog != null) {
                                dialog.show();
                            }
//                            if (context.getClass().getSimpleName().equals(ConversationFragment.class.getSimpleName())){
//                                request.callback.onSuccess(o);
//                            }
                        }
                        if (result.equals("user.login.unlogin")) {
                            MyApplication.getInstance().exit();
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                        }
                        if (!result.equals("NO_DATA")) {
                            if (context instanceof SplashActivity) {
                                Intent intent = new Intent(context, HomeActivity.class);
                                context.startActivity(intent);
                                ((SplashActivity) context).finish();
                            }

                            if (context instanceof HomeActivity) {
                            } else {
                                Toast.makeText(context, mes, Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (result.equals("NO_DATA")) {
                            request.callback.onSuccess(o);
                        }
                    } else {
                        if (result.equals("SUCC")) {
                            Log.i("TAG", context.getClass() + ">>>>>>>>>>" + json);
                        }
                        request.callback.onSuccess(o);
                    }
                }
            } catch (Exception e) {
                ProgressDialogUtils.closeProgressDialog();
                e.printStackTrace();
            }
        }
        ProgressDialogUtils.closeProgressDialog();
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        request.callback.onProgressUpdated(values[0], values[1]);
    }
}
