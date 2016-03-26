package cn.zsmy.akm.doctor.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.zsmy.akm.doctor.bean.DoctorCenter;
import cn.zsmy.akm.doctor.bean.Profile;
import cn.zsmy.akm.doctor.home.HomeActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;


/**
 * 提供登录功能(13612155695  123456)
 * Created by wanjingyan
 * on 2015/12/11 16:31.
 * 13761577422  123456
 */
public class LoginUtils {
    /**
     * 登录 ，成功就回到之前的界面
     *
     * @param context
     * @param phone
     * @param pwd
     */
    public static void login(final Context context, final String phone, final String pwd) {
        Request request = new Request(UrlHelper.LOGIN, Request.RequestMethod.POST, context);
        try {
            JSONObject obj = new JSONObject();
            obj.put("username", phone);
            obj.put("password", pwd);
            obj.put("platform", "doctor");
            request.postContent = obj.toString();
            Log.i("TAG", request.postContent);
            request.addHeader("Content-Type", "application/json;charset=UTF-8");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Profile profile = JsonParser.deserializeFromJson(result, Profile.class);
                MyApplication.setProfile(profile);
                getProfileInfo(context);
                saveLoginInfo(context, phone, pwd);
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
            }
        });
        RequestManager.getInstance().execute(context.toString(), request);
    }

    /**
     * 登录成功将用户的账号密码存入本地，供下次自动登录使用
     *
     * @param context
     * @param phone
     * @param pwd
     */
    public static void saveLoginInfo(Context context, String phone, String pwd) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.LOGIN_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("userName", phone);
        edit.putString("passWord", pwd);
        edit.commit();
    }

    /**
     * 获取医生个人中心数据
     */
    private static void getProfileInfo(final Context context) {
        Request request = new Request(UrlHelper.DOCTOR_PERSONAL_CENTER, context);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DoctorCenter doctorCenter = JsonParser.deserializeFromJson(result, DoctorCenter.class);
                MyApplication.setEntity(doctorCenter.getData());

                ((Activity) context).finish();
            }
        });
        RequestManager.getInstance().execute(context.toString(), request);
    }


}
