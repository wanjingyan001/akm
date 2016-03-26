package cn.zsmy.akm.doctor;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Cityies;
import cn.zsmy.akm.entity.Doctors;
import cn.zsmy.akm.entity.Province;
import cn.zsmy.akm.entity.Sections;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.PopWin;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.dialog.ProgressDialogUtils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 找医生
 * Created by qinwei on 2015/11/18 9:41
 *
 * @param
 */
public class DoctorListActivity extends BaseTitleListActivity implements View.OnClickListener, PopWin.GetPosition {
    private List<Cityies.DataEntity> city;
    private List<Sections.DataEntity> section;
    private ArrayList<Object> strings = new ArrayList<>();
    private static String TAG = "MyApplication";
    private List<Doctors.DataEntity> infos = new ArrayList<>();
    private View mRecommendLine;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showContent(EmptyView.State.ing, 1);
                    break;
                case 1:
                    showContent(EmptyView.State.empty, 1);
                    break;
                default:
            }
        }
    };
    private TextView mCityChoose;
    private TextView mSectionsChoose;
    private ImageView cityArrow;
    private ImageView sectionArrow;
    private Doctors.DataEntity doctorInfo;
    private String cityId;
    private String sectionId;
    private PopWin popWin;
    private PopWin win;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_doctor_list);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        findViewById(R.id.city_choose).setOnClickListener(this);
        findViewById(R.id.sections_choose).setOnClickListener(this);
        mCityChoose = ((TextView) findViewById(R.id.city_choose_title));
        mSectionsChoose = ((TextView) findViewById(R.id.sections_choose_title));
        cityArrow = ((ImageView) findViewById(R.id.city_choose_arrow));
        sectionArrow = ((ImageView) findViewById(R.id.sections_choose_arrow));
        mRecommendLine = findViewById(R.id.recommend_line);
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.BOTH);

    }

    @Override
    protected void initializeData() {
        setTitle(getString(R.string.mDoctorListTitleLabel));
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadDoctorInfo(sectionId, cityId);
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        loadDoctorInfo(sectionId, cityId);
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    private void loadDoctorInfo(String sectionId, String cityId) {
        String url;
        if (sectionId == null && cityId == null) {
            url = UrlHelpper.SEARCH_DOCTORY;
        } else if (sectionId != null && cityId == null) {
            url = UrlHelpper.SEARCH_DOCTORY + "?deptId=" + sectionId;
        } else if (sectionId == null && cityId != null) {
            url = UrlHelpper.SEARCH_DOCTORY + "?cityId=" + cityId;
        } else {
            ProgressDialogUtils.showProgressDialog(this, "正在加载");
            url = UrlHelpper.SEARCH_DOCTORY + "?deptId=" + sectionId + "&cityId=" + cityId;
        }
        Request request = new Request(url, this);
        Log.d("TAG", url);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", result);
                try {
                    JSONObject object = new JSONObject(result);
                    String code = object.getString("code");
                    if (code.equals("NO_DATA")) {
                        mHandler.sendEmptyMessageDelayed(1, 300);
                    } else {
                        modules.clear();
                        Doctors doctors = JsonParser.deserializeFromJson(result, Doctors.class);
                        infos = doctors.getData();
                        modules.addAll(infos);
                        adapter.notifyDataSetChanged();
                        mHandler.sendEmptyMessageDelayed(0, 1000);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_dector_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }


    //点击popwindow的列表项的回调
    @Override
    public void clickItem(int position, Object o) {
        if (o instanceof Cityies.DataEntity) {
            if (position == 0) {
                mCityChoose.setText("全部");
                cityId = null;
            } else {
                String name = ((Cityies.DataEntity) o).getName();
                cityId = ((Cityies.DataEntity) o).getId();
                mCityChoose.setText(name);
            }
        } else if (o instanceof Sections.DataEntity) {
            if (position == 0) {
                mSectionsChoose.setText("全部");
                sectionId = null;
            } else {
                String name = ((Sections.DataEntity) o).getName();
                mSectionsChoose.setText(name);
                sectionId = ((Sections.DataEntity) o).getId();
            }
        } else if (o instanceof Province.DataEntity) {
            if (position == 0) {
                //获取所有城市
                getCities(UrlHelpper.GET_CITIES);
            } else {
                String id = ((Province.DataEntity) o).getId();
                String url = UrlHelpper.GET_CITIES + "?parentId=" + id;
                getCities(url);
            }
        } else if (o instanceof Sections.DataEntity.SubsEntity) {
            if (position == 0) {
                mSectionsChoose.setText("全部");
                sectionId = null;
            } else {
                String name = ((Sections.DataEntity.SubsEntity) o).getName();
                mSectionsChoose.setText(name);
                sectionId = ((Sections.DataEntity.SubsEntity) o).getId();
            }
        }
        loadDoctorInfo(sectionId, cityId);
    }


    class Holder extends QBaseViewHolder {
        private CircleImageView mDoctorImg;//医生头像
        private TextView mDoctorPost;//医生职称
        private TextView mDoctorName;//医生名字
        private TextView mSpecificPost;//医生具体职称
        private TextView mHospital;//医院
        private TextView mGoodAt;//擅长
        private TextView mRecommendNumber;//推荐指数

        @Override
        public void initializeView(View view) {
            mDoctorImg = ((CircleImageView) view.findViewById(R.id.doctoe_head_portrait));
            mDoctorPost = ((TextView) view.findViewById(R.id.post));
            mDoctorName = ((TextView) view.findViewById(R.id.doctor_name));
            mSpecificPost = ((TextView) view.findViewById(R.id.doctor_post));
            mHospital = ((TextView) view.findViewById(R.id.hospital));
            mGoodAt = ((TextView) view.findViewById(R.id.doctor_good_at));
            mRecommendNumber = ((TextView) view.findViewById(R.id.recommended_number));
            mDoctorImg.setBorderWidth(1);
            mDoctorImg.setBorderColor(getResources().getColor(R.color.edit_stroke));
        }

        @Override
        public void initializeData(int position) {
            doctorInfo = ((Doctors.DataEntity) modules.get(position));
            List<Doctors.DataEntity.DoctorAuthEntity> doctorAuth = doctorInfo.getDoctorAuth();
            if (doctorAuth != null && doctorAuth.size() > 0) {
                for (Doctors.DataEntity.DoctorAuthEntity auth : doctorAuth) {
                    if (auth.getZType().equals("4")) {
                        if (auth.getAuthPic() != null) {
                            ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + auth.getAuthPic(), mDoctorImg);
                        }
                    }
                }
            }
            mDoctorName.setText(doctorInfo.getName());
            mDoctorPost.setText(doctorInfo.getProfessionalTitle());
            mSpecificPost.setText(doctorInfo.getDept());
            mHospital.setText(doctorInfo.getHospital());
            mGoodAt.setText("擅长：" + doctorInfo.getSpecialty());
            mRecommendNumber.setText(String.valueOf(doctorInfo.getChooseIndex()));
        }
    }


    public static Intent getIntent(Context context) {
        return new Intent(context, DoctorListActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_doctor_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(this, DoctorSearchActivity.class);
            intent.putExtra("from", Constants.FROM_RECOMMEND);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_choose:
                popWin = new PopWin(this, this, cityArrow);
                getProvinces();
                break;
            case R.id.sections_choose:
                win = new PopWin(this, this, sectionArrow);
                getSectionLisies();
                break;
        }

    }


    public void getSectionLisies() {
        //获取科室列表
        Request request = new Request(UrlHelpper.GET_SECTION_LIST, this);
        ProgressDialogUtils.showProgressDialog(this, "正在加载");
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                strings.clear();
                Sections sections = JsonParser.deserializeFromJson(result, Sections.class);
                section = sections.getData();
                for (Sections.DataEntity sec : section) {
                    strings.add(sec);
                    List<Sections.DataEntity.SubsEntity> subs = sec.getSubs();
                    if (subs != null && subs.size() != 0) {
                        for (Sections.DataEntity.SubsEntity sub : subs) {
                            strings.add(sub);
                            strings.remove(sec);
                        }
                    }
                }
                win.initData(strings);
                win.show(mRecommendLine);
                ProgressDialogUtils.closeProgressDialog();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    //获得省份列表
    public void getProvinces() {
        Request request = new Request(UrlHelpper.GET_PROVINCE, this);
        ProgressDialogUtils.showProgressDialog(this, "正在加载");
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                strings.clear();
                Province province = JsonParser.deserializeFromJson(result, Province.class);
                List<Province.DataEntity> data = province.getData();
                strings.addAll(data);
                popWin.initData(strings);
                popWin.show(mRecommendLine);
                ProgressDialogUtils.closeProgressDialog();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    //获取城市列表
    public void getCities(String url) {
        Request request = new Request(url, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                strings.clear();
                Cityies cityies = JsonParser.deserializeFromJson(result, Cityies.class);
                city = cityies.getData();
                strings.addAll(city);
                popWin.initData(strings);
                popWin.show(mRecommendLine);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Intent intent = new Intent(DoctorListActivity.this, DoctorDetailActivity.class);
        Doctors.DataEntity dataEntity = infos.get(position - 1);
        intent.putExtra("doctorId", String.valueOf(dataEntity.getId()));
        startActivity(intent);
    }
}
