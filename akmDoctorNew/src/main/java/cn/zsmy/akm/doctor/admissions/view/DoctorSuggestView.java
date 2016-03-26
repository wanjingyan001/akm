package cn.zsmy.akm.doctor.admissions.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.zsmy.akm.doctor.admissions.SelectHospitalActivity;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/17 11:09
 * 就医建议里边的就诊内容 指定我就诊和指定医院
 */
public class DoctorSuggestView extends LinearLayout implements View.OnClickListener {
    private Context context;
    private RelativeLayout hospital_rel;
    private TextView hospitalName;
    private String cacheName;

    public DoctorSuggestView(Context context) {
        super(context);
        this.context = context;
        initializeView();
        setOnClickListener(this);
    }

    public DoctorSuggestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoctorSuggestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeView() {
        LayoutInflater.from(context).inflate(R.layout.view_doctor_suggest, this);
        hospital_rel = (RelativeLayout) findViewById(R.id.doctor_suggest_select_hospital);
    }

    public void setText(String name) {
        hospitalName.setText(name);
    }


    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public void onClick(View v) {
        //选择医院
        Intent intent = new Intent();
        Log.d("TAG", "前往医院选择");
        intent.putExtra("cacheName",cacheName);
        intent.setClass(context, SelectHospitalActivity.class);
        context.startActivity(intent);
    }
}
