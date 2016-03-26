package cn.zsmy.akm.doctor.admissions.fragemnt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.akm.doctor.admissions.SelectHospitalActivity;
import cn.zsmy.akm.doctor.admissions.SendResult;
import cn.zsmy.akm.doctor.admissions.adapter.HospitalAdapter;
import cn.zsmy.akm.doctor.admissions.bean.HospitalList;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * 选择医院（有数据，未能显示）
 * Created by sanz on 2015/12/17 14:07
 */
public class SelecHospitalFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private PullToRefreshListView mPulltoRefreshLsv;
    private int name;
    private SendResult sengResult = null;
    private List<String> datas = new ArrayList<>();
    private HospitalAdapter adapter;
    //    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//                    showContent();
//                    break;
//                case 1:
//                    showContent(EmptyView.State.empty);
//                    break;
//            }
//        }
//    };
    private String cacheName;
    private String caseId;
    private List<HospitalList.DataEntity> data;
    private int type;


    public static SelecHospitalFragment newInstance(String caseId, int type) {
        SelecHospitalFragment fragment = new SelecHospitalFragment();
        Bundle bundle = new Bundle();
        bundle.putString("caseId", caseId);
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        sengResult = ((SelectHospitalActivity) activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_local_hospital;
    }

    @Override
    protected void initializeView(View v) {
        mPulltoRefreshLsv = ((PullToRefreshListView) v.findViewById(R.id.generalPullToRefreshLsv));
        mPulltoRefreshLsv.setOnItemClickListener(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            caseId = arguments.getString("caseId");
            type = arguments.getInt("type", -1);
        }
        mPulltoRefreshLsv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                seacherHospital(caseId, type);
            }
        });
    }


    /**
     * 患者所在城市需要传两个参数，全部城市则不用传
     * caseId:病历Id
     * deptId:科室Id
     */
    public void seacherHospital(String caseId, int type) {
        String url = null;
        if (type == 1) {
            url = UrlHelper.SELECT_HOSPITAL + "&caseId=" + caseId+"&flag=1";
        } else {
            url = UrlHelper.SELECT_HOSPITAL+"&flag=2";
        }
        Request request = new Request(url, getActivity());
        Log.d("TAG", url);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", result);
                datas.clear();
                HospitalList hospitalList = JsonParser.deserializeFromJson(result, HospitalList.class);
                data = hospitalList.getData();
                for (HospitalList.DataEntity hos : data) {
                    datas.add(hos.getName());
                    adapter = new HospitalAdapter(datas, getActivity(), 1);
                    adapter.notifyDataSetChanged();
                    mPulltoRefreshLsv.setAdapter(adapter);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onResume() {
        super.onResume();
        seacherHospital(caseId, type);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String hospitalName = datas.get(position - 1);
        String hosID = data.get(position - 1).getId();
        sengResult.result(hospitalName, hosID);
    }


}
