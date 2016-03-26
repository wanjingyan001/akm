package cn.zsmy.akm.doctor.learning;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.conversation.PublishedActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.learning.bean.Area;
import cn.zsmy.akm.doctor.learning.bean.AreaList;
import cn.zsmy.akm.doctor.learning.bean.CollectionArea;
import cn.zsmy.akm.doctor.learning.view.SorllTabView;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/18 15:47
 * 学术圈
 */
public class ScholarshipActivity extends BaseTitleActivity implements View.OnClickListener, SorllTabView.OnClickStartCollectionArea {
    private ImageView search;
    private LinearLayout scroll_Layout;
    private ArrayList<Area> datas;
    private final int RESULT_CDDE = 50;
    private SorllTabView sorllTabView;
    private ImageView edit;
    private ArrayList<Area>all_datas;
    private ArrayList<Area>collection_datas;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ScholarshipActivity.class);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_scholarship);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        all_datas=new ArrayList<>();
        collection_datas=new ArrayList<>();
        search = (ImageView) findViewById(R.id.seacrh_area);
        edit = ((ImageView) findViewById(R.id.edit_are));
        scroll_Layout = (LinearLayout) findViewById(R.id.scroll_layout);
        search.setOnClickListener(this);
        edit.setOnClickListener(this);
        sorllTabView = new SorllTabView(this);
        sorllTabView.setOnClickStartCollectionAreaListener(this);
        String[] title = {"全部","热帖","精华"};
        datas = new ArrayList<>();
        Area area=null;
        for (int i=0;i<title.length; i++) {
            area=new Area();
            area.setAreaName(title[i]);
                area.setStatus(i + 1);
            datas.add(area);
        }
        sorllTabView.setDatas(datas, getSupportFragmentManager());
        scroll_Layout.addView(sorllTabView);
    }

    @Override
    protected void initializeData() {
        setTitle("学术圈");
        loadAreaName();
        loadCollectionAreaName();
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.seacrh_area:
                startActivity(SearchAreaActivity.getIntent(this));
                break;
            case R.id.edit_are:
                startActivity(PublishedActivity.getIntent(this,"from_scholarship"));
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_CDDE:
                    ArrayList<Area> rdatas = (ArrayList)data.getExtras().getSerializable("COLLECTION");
                    ArrayList<Area> adatas = (ArrayList)data.getExtras().getSerializable("ALL");
                    collection_datas=rdatas;
                    all_datas=adatas;
                    for(int i=datas.size()-1;i>=3;i--){
                        datas.remove(i);
                    }
                    for (int i = 0; i < rdatas.size(); i++) {
                        datas.add(rdatas.get(i));

                    }
//                    for (int i=datas.size()-1;i>=0;i--) {
//                        for (int k = rdatas.size()-1; k >=0; k--) {
//                                if(rdatas.get(k).getAreaName().equals(datas.get(i).getAreaName())){
//                                    rdatas.remove(k);
//                                }
//                        }
//                    }
//                    for (int i = 0; i < rdatas.size(); i++) {
//                        datas.add(rdatas.get(i));
//                        collection_datas.add(rdatas.get(i));
//                     }
                    sorllTabView.setDatas(datas, getSupportFragmentManager());
                    break;
                default:
                    break;
            }
        }
    }
    private void loadAreaName() {
        Request request = new Request(UrlHelper.ALL_AREA +"?flag=1", this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                AreaList areaList=JsonParser.deserializeFromJson(result,AreaList.class);
                List<AreaList.DataEntity> dataEntity=areaList.getData();
                Area area=null;
                for(int i=0;i<dataEntity.size();i++){
                    area=new Area();
                    area.setAreaName(dataEntity.get(i).getTitle());
                    area.setId(dataEntity.get(i).getId());
                    all_datas.add(area);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
    private void loadCollectionAreaName() {
        Request request = new Request(UrlHelper.ALL_COLLEC_PLATE +"?zType=1", this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                CollectionArea collectionArea=JsonParser.deserializeFromJson(result,CollectionArea.class);
                List<CollectionArea.DataEntity> dataEntity=collectionArea.getData();
                Area area=null;
                for(int i=0;i<dataEntity.size();i++){
                    area=new Area();
                    area.setAreaName(dataEntity.get(i).getName());
                    area.setId(dataEntity.get(i).getItemId());
                    datas.add(area);
                    collection_datas.add(area);
                }
                sorllTabView.setDatas(datas, getSupportFragmentManager());

            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onClickCollectionAreaListenter() {
        for(int i=collection_datas.size()-1;i>=0;i--){
            for(int k=all_datas.size()-1;k>=0;k--){
                  if(collection_datas.get(i).getAreaName().equals(all_datas.get(k).getAreaName())){
                         all_datas.remove(k);
                  }
            }
        }

        startActivityForResult(CollectionAreaActivity.getIntent(this, collection_datas,all_datas), RESULT_CDDE);
    }
}
