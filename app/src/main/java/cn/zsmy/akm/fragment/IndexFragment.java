package cn.zsmy.akm.fragment;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.akm.R;
import cn.zsmy.akm.doctor.DoctorListActivity;
import cn.zsmy.akm.doctor.FamousDoctorListActivity;
import cn.zsmy.akm.entity.ProfileDetails;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.interrogation.InterrogationInputActivity;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.RippleBackground;

/**
 * 首页
 */
public class IndexFragment extends BaseFragment implements
        OnClickListener {

    private RippleBackground mIndexRequestQuestionRb;
    private boolean hidden=false;
    @Override
    public int getFragmentLayoutId() {

        return R.layout.fragment_index;
    }

    @Override
    public void initializeView(View view) {
        mIndexRequestQuestionRb = (RippleBackground) view
                .findViewById(R.id.mIndexRequestQuestionRb);
        mIndexRequestQuestionRb.startRippleAnimation();
        view.findViewById(R.id.mIndexGoQuestionImg).setOnClickListener(this);
        view.findViewById(R.id.mIndexGoDoctorsLayout).setOnClickListener(this);
        view.findViewById(R.id.mIndexMingLayout).setOnClickListener(this);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(this.getClass().getSimpleName(),hidden+"");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIndexGoQuestionImg:
                startActivity(InterrogationInputActivity.getIntent(getActivity(), 0,null, Constants.CHAT_FLAG_OF_CONSTACTS_PHOTO_INFORMATION,Constants.CASE_TYPES_OF_COMMON));
                break;
            case R.id.mIndexGoDoctorsLayout:
                startActivity(DoctorListActivity.getIntent(getContext()));
                break;
            case R.id.mIndexMingLayout:
                startActivity(FamousDoctorListActivity.getIntent(getContext()));
                break;
            default:
                break;
        }
    }

}
