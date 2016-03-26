package cn.zsmy.akm.doctor.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.login.activity.LoginActivity;
import cn.zsmy.doctor.R;


/**
 * Created by sanz on 2015/12/1 16:49
 * 此对话框样式是属于选择类样式
 */
public class ChooseDialog extends Dialog implements View.OnClickListener{
    private View v;
    private int index;
    private TextView titleText;
    private Context context;
    /****
     * 是否登录 0;
     * ***/
    private final int IS_LOGIN=0;
    /****
     * 确定退出账号 1;
     * ***/
    private final int IS_EXIT=1;
    /****
     * 确定退出 2;
     * ***/
    private final int IS_EXIT_APP=2;


    protected ChooseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    /**
     * @param context 上下文
     * @param index 设置标题并对话框的作用
     */
    public ChooseDialog(Context context,int index) {
        super(context, R.style.ChooseDialog);
        this.index=index;
        this.context=context;
        showDiag(context);
        setContentView(v);
        setCanceledOnTouchOutside(false);
        setTitleText();
        setDialogWidth();
    }
    /**
     * 初始化UI视图
     */
    private void showDiag(Context context) {
        v = LayoutInflater.from(context).inflate(R.layout.layout_choose_dialog, null);
        titleText=(TextView)v.findViewById(R.id.mDialogMessageLabel);
        v.findViewById(R.id.mDialogConfirmBtn).setOnClickListener(this);
        v.findViewById(R.id.mDialogCancelBtn).setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.mDialogConfirmBtn:
                  effectBtn();
                  break;
              case R.id.mDialogCancelBtn:
                  dismiss();
                  break;
          }
    }
    /*
    *
    * 点击右边Btn在不同情况下的作用
    * **/
    private void effectBtn(){
        switch (index){
            case  IS_LOGIN:
                Intent intent=new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                break;
            case  IS_EXIT:
                MyApplication.getInstance().exit();
                context.startActivity(LoginActivity.getIntent(context));
                break;
            case  IS_EXIT_APP:
                MyApplication.getInstance().exit();
                break;
        }
    }
    /**
     * 设置标题
     * **/
    private void setTitleText(){
        switch (index){
            case  IS_LOGIN:
                titleText.setText("你还没登录请登录");
                break;
            case  IS_EXIT:
                titleText.setText("你确定退出当前账号吗？");
                break;
            case  IS_EXIT_APP:
                titleText.setText("你确定退出吗？");
                break;
        }
    }
    /**
     *根据屏幕大小适应宽度
     * **/
    private void setDialogWidth(){
                DisplayMetrics metric = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(width-width/3, ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(params);
    }
}
