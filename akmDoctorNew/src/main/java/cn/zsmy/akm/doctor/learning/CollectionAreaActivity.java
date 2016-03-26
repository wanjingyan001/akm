package cn.zsmy.akm.doctor.learning;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.learning.bean.Area;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.layout.FlowLayout;
import cn.zsmy.doctor.R;

/**
 * 收藏板块
 * Created by sanz on 2015/12/19 14:45
 */
public class CollectionAreaActivity extends BaseTitleActivity implements View.OnClickListener {
    private FlowLayout all_layout, collection_layout;
    private List<Area> cDatas;
    private TextView submit;
    private TextView all_btn, collection_btn;
    private ArrayList<Area> aDatas;
    private String url;

    public static Intent getIntent(Context context, ArrayList<Area> collection_Datas,ArrayList<Area> all_Datas) {
        Intent intent = new Intent(context, CollectionAreaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("COLLECTION_AREA", collection_Datas);
        bundle.putSerializable("ALL_AREA", all_Datas);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_collection_area);
        MyApplication.getInstance().addActivity(this);

    }

    @Override
    protected void initializeView() {
        super.initializeView();
        all_layout = (FlowLayout) findViewById(R.id.all_flow_layout);
        collection_layout = (FlowLayout) findViewById(R.id.collection_flow_layout);
        submit = (TextView) findViewById(R.id.collection_area_submit);
        submit.setOnClickListener(this);


    }

    @Override
    protected void initializeData() {
        setTitle("收藏板块");
        cDatas = (ArrayList) getIntent().getExtras().get("COLLECTION_AREA");
        aDatas=(ArrayList)getIntent().getExtras().get("ALL_AREA");
        setCollectionArea();
        setAllArea();
    }
    /**
     * 提交收藏板块
     */
    private void getCollecPlate() {

        Request request = new Request(UrlHelper.ADD_COLLEC_PLATE,Request.RequestMethod.POST ,this);
        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<cDatas.size();i++){
            buffer.append(cDatas.get(i).getId() + ",");
            if(i==cDatas.size()-1){
                buffer.append(cDatas.get(i).getId());
            }
        }
        request.put("itemIds",buffer.toString());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Intent intent = new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("COLLECTION", (Serializable)cDatas);
                bundle.putSerializable("ALL", (Serializable)aDatas);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collection_area_submit:
                if (cDatas.size()>0){
                    getCollecPlate();
                }else {
                    Toast.makeText(this, "至少选择一个版块", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void setCollectionArea() {
        collection_layout.removeAllViews();
        for (int i = 0; i < cDatas.size(); i++) {
            collection_btn = new TextView(this);
            collection_btn.setText(cDatas.get(i).getAreaName());
            collection_btn.setTag(i);
            collection_btn.setId(i);
            collection_btn.setGravity(Gravity.CENTER);
            collection_btn.setTextColor(getResources().getColor(R.color.app_main_color));
            collection_btn.setPadding(32,16,32,16);
            collection_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.learning_collection_area_tag_bg));
            collection_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Area s = cDatas.get(v.getId());
                    cDatas.remove(v.getId());
                    aDatas.add(s);
                    setCollectionArea();
                    setAllArea();
                }
            });
            collection_layout.addView(collection_btn);
        }
    }

    private void setAllArea() {
        all_layout.removeAllViews();
        for (int i = 0; i < aDatas.size(); i++) {
            all_btn = new TextView(this);
            all_btn.setText(aDatas.get(i).getAreaName());
            all_btn.setTag(i);
            all_btn.setId(i);
            all_btn.setGravity(Gravity.CENTER);
            all_btn.setTextColor(getResources().getColor(R.color.app_main_color));
            all_btn.setPadding(32,16,32,16);
            all_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.learning_collection_area_tag_bg));
            all_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Area s = aDatas.get(v.getId());
                    aDatas.remove(v.getId());
                    cDatas.add(s);
                    setCollectionArea();
                    setAllArea();
                }
            });
            all_layout.addView(all_btn);
        }
    }
}
