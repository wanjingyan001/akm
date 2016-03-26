package cn.zsmy.akm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.zsmy.akm.entity.Profile;
import cn.zsmy.akm.entity.ProfileDetails;
import cn.zsmy.akm.entity.UserInfo;
import cn.zsmy.akm.home.HomeActivity;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;

/**
 * 提供登录功能
 * Created by wanjingyan
 * on 2015/12/11 16:31.
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
        Request request = new Request(UrlHelpper.LOGIN, Request.RequestMethod.POST, context);
        try {
            JSONObject obj = new JSONObject();
            obj.put("username", phone);
            obj.put("password", pwd);
            obj.put("platform", "akeman");
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
                if (MyApplication.getInstance().getmList().size() == 0) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                }
                getUserInfo(context);
                saveLoginInfo(context, phone, pwd);
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


    private static void getUserInfo(final Context context) {
        Request request = new Request(UrlHelpper.USER_INFO, context);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                UserInfo userInfo = JsonParser.deserializeFromJson(result, UserInfo.class);
                if (userInfo.getData() != null) {
                    MyApplication.setUserInfo(userInfo.getData());
                    String avatar = MyApplication.getUserInfo().getAvatar();
                    String nickname = MyApplication.getUserInfo().getNickname();
                    Log.d("TAG", avatar + ";" + nickname);
                    getProfile(context);

                }
            }
        });
        RequestManager.getInstance().execute(context.toString(), request);
    }

    private static void getProfile(final Context context) {
        Request request = new Request(UrlHelpper.PERSON_PROFILE, Request.RequestMethod.GET, context);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", "个人中心" + result);
                ProfileDetails profileDetails = JsonParser.deserializeFromJson(result, ProfileDetails.class);
                ProfileDetails.DataEntity profileDate = profileDetails.getData();
                if (profileDate != null) {
                    MyApplication.setProfileDetails(profileDate);
                }
                ((Activity) context).finish();
            }
        });
        RequestManager.getInstance().execute(context.toString(), request);
    }


}
