package cn.zsmy.akm.doctor.profile.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2016/1/4 16:51
 */
public class ProfileInfoTopChooseView extends LinearLayout {
    private Context context;
    private List<String>datas;
    private ChooseListener listener;
    public ProfileInfoTopChooseView(Context context,List<String>  datas) {
        super(context);
        this.context=context;
        this.datas=datas;
        setOrientation(HORIZONTAL);
        initializeView();
        setBackgroundColor(getResources().getColor(R.color.white));
        setPadding(20, 0, 20, 0);
    }

    public ProfileInfoTopChooseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfileInfoTopChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public ProfileInfoTopChooseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void initializeView(){
        for(int i=0;i<datas.size();i++){
            ChooseView chooseView=new ChooseView(context);
            chooseView.setId(i);
            chooseView.setTag(i);
            chooseView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnChooseListener(v.getId());

                }
            });
            chooseView.initializeData(datas.get(i));
            addView(chooseView);
        }
    }
    public void selectView(int curposition){
        for(int i=0;i<datas.size();i++){
            if(curposition==i){
                ChooseView choose = (ChooseView)getChildAt(curposition);
                choose.setChooseBackgroud(true);
            }else{
                ChooseView choose = (ChooseView)getChildAt(i);
                choose.setChooseBackgroud(false);
            }

        }
        }
    public void setOnChooseListener(ChooseListener listener){
               this.listener=listener;
    }
   public interface ChooseListener{
           public void OnChooseListener(int id);
    }
    public class ChooseView extends LinearLayout {
        private ImageView choose_img;
        private TextView  title_name;
        private RelativeLayout relativeLayout;
        public ChooseView(Context context) {
            super(context);
            initializeView();
        }

        public ChooseView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

//        public ChooseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//            super(context, attrs, defStyleAttr, defStyleRes);
//        }
        private void initializeView(){
                View view = LayoutInflater.from(context).inflate(R.layout.view_profile_info_top_selector, this);
                relativeLayout=(RelativeLayout)view.findViewById(R.id.profile_info_choose_group);
                choose_img=(ImageView)view.findViewById(R.id.profile_info_paint);
                title_name=(TextView)view.findViewById(R.id.profile_info_title);
                setGroupWidth();
        }
        public  void initializeData(String title){
            title_name.setText(title);
        }

        private void setChooseBackgroud(boolean flag){
            if(flag){
                choose_img.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.icon_profile_select_button_true));
                title_name.setTextColor(context.getResources().getColor(R.color.app_main_color));
            }else{
                choose_img.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.icon_profile_select_button_false));
                title_name.setTextColor(context.getResources().getColor(R.color.black));
            }
        }
        private void setGroupWidth(){
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int num= datas.size();
//            int w=dip2px(context,20);
            int width = (dm.widthPixels-40)/num;//宽度height = dm.heightPixels ;//高度
            LayoutParams params=new LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            relativeLayout.setLayoutParams(params);

        }
        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        public  int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }
    }
}
