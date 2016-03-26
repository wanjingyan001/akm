package cn.zsmy.akm.utils;

import android.content.Context;
import android.util.Log;

import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.widget.dialog.ChooseDialog;

/**
 * Created by qinwei on 2015/12/12 14:21
 */
public class Is_Login {
    public static boolean getLoginStatus(Context context){
        if(MyApplication.getProfile()!=null&&MyApplication.getProfile().getUserId()!=null){
            Log.i("TAGgetLoginStatus",MyApplication.getProfile().getCode()+">>>>>>>"+MyApplication.getProfile().getUserId());
         return  true;
        }else{
            ChooseDialog dialog=new ChooseDialog(context,0);
            Log.i("TAGgetLoginStatus",dialog.isShowing()+"'");
            if(dialog.isShowing()){

            }else{
                dialog.show();
            }

            return false;
        }
    }
}
