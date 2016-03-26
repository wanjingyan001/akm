package cn.zsmy.akm.doctor;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import cn.zsmy.akm.entity.FamousDoctor;
import cn.zsmy.akm.entity.Province;
import cn.zsmy.akm.entity.Sections;
import cn.zsmy.akm.home.MyApplication;
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
 * 名医馆
 * Created by sanz on 2015/11/18 9:41
 */
public class FamousDoctorListActivity extends BaseTitleListActivity implements View.OnClickListener, PopWin.GetPosition {
    private List<Cityies.DataEntity> city;
    private List<Sections.DataEntity> section;
    private List<Object> strings = new ArrayList<>();
    private View mRecommendLine;
    private TextView mCityChoose;
    private TextView mSectionsChoose;
    private ImageView cityArrow;
    private ImageView sectionArrow;
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
    private List<FamousDoctor.DataEntity> infos = new ArrayList<>();
    private String cityId;
    private String sectionId;
    private PopWin popWin;
    private PopWin win;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_famous_doctor_list);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFamousList(cityId, sectionId);
    }

    @Override
    protected void initializeData() {
        setTitle(getString(R.string.mFamousDoctorTitleLabel));
    }

    private void loadFamousList(String cityId, String sectionId) {
        String url;
        if (sectionId == null && cityId == null) {
            url = UrlHelpper.FAMOUS_DOCTOR;
        } else if (sectionId != null && cityId == null) {
            url = UrlHelpper.FAMOUS_DOCTOR + "?deptId=" + sectionId;
        } else if (sectionId == null && cityId != null) {
            url = UrlHelpper.FAMOUS_DOCTOR + "?cityId=" + cityId;
        } else {
            url = UrlHelpper.FAMOUS_DOCTOR + "?deptId=" + sectionId + "&cityId=" + cityId;
        }
        Request request = new Request(url, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    String code = object.getString("code");
                    if (code.equals("NO_DATA")) {
                        mHandler.sendEmptyMessageDelayed(1, 300);
                    } else {
                        FamousDoctor famousDoctor = JsonParser.deserializeFromJson(result, FamousDoctor.class);
                        infos = famousDoctor.getData();
                        modules.clear();
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

    //获取跳转至该界面的intent
    public static Intent getIntent(Context context) {
        return new Intent(context, FamousDoctorListActivity.class);
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);

        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadFamousList(cityId, sectionId);
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
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
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_famous_item, null);
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
        loadFamousList(cityId, sectionId);
    }


    class Holder extends QBaseViewHolder implements View.OnClickListener {
        private CircleImageView mDoctorImg;//头像
        private TextView mExperience;//医生经验
        private TextView mDoctorName;//医生名字
        private TextView mDoctorPost;//医生职称
        private TextView mHospital;//所属医院
        private TextView mGoodAt;//擅长
        private TextView mPrice;//问诊价格
        private TextView mClinic;//今天义诊
        private int curPosition;

        @Override
        public void initializeView(View view) {
            mDoctorImg = ((CircleImageView) view.findViewById(R.id.doctoe_head_portrait));
            mExperience = ((TextView) view.findViewById(R.id.experience));
            mDoctorName = ((TextView) view.findViewById(R.id.doctor_name));
            mDoctorPost = ((TextView) view.findViewById(R.id.doctor_post));
            mHospital = ((TextView) view.findViewById(R.id.hospital));
            mGoodAt = ((TextView) view.findViewById(R.id.doctor_good_at));
            mPrice = ((TextView) view.findViewById(R.id.price));
            mClinic = ((TextView) view.findViewById(R.id.clinic));
            mDoctorImg.setBorderColor(getResources().getColor(R.color.edit_stroke));
            mDoctorImg.setBorderWidth(1);
            view.setOnClickListener(this);

        }

        @Override
        public void initializeData(int position) {
            this.curPosition = position;
            FamousDoctor.DataEntity doctorInfo = (FamousDoctor.DataEntity) modules.get(position);
            mDoctorName.setText(doctorInfo.getDoctor().getName());
            System.out.println("医生姓名:" + doctorInfo.getDoctor().getName());
            FamousDoctor.DataEntity.DoctorEntity doctor = doctorInfo.getDoctor();
            mDoctorPost.setText(doctor.getProfessionalTitle());
            if (TextUtils.isEmpty(doctor.getWorking())) {
                mExperience.setText("1年经验");
            } else {
                mExperience.setText(doctor.getWorking() + "经验");
            }

            mHospital.setText(doctor.getHospital());
            mGoodAt.setText(doctor.getSpecialty());
            List<FamousDoctor.DataEntity.DoctorEntity.PriceEntity> price = doctor.getPrice();
            if (price != null && price.size() != 0) {
                String amt = price.get(0).getAmt();
                String s = amt.replace(amt.substring(amt.indexOf('.'), amt.length()), "元");
                mPrice.setText(s + "/" + price.get(0).getUnit());
                if (s.equals("0元")) {
                    mPrice.setVisibility(View.INVISIBLE);
                    mClinic.setVisibility(View.VISIBLE);
                } else {
                    mClinic.setVisibility(View.INVISIBLE);
                }
            } else {
                mPrice.setVisibility(View.INVISIBLE);
            }
            ImageLoader imageLoader = ImageLoader.getInstance();
            //修改内容:名医馆 列表item头像和医生简介头像无法对应上
            //修改人：yutaotao
            //修改时间：2016/3/1
            // imageLoader.displayImage(UrlHelpper.FILE_IP + doctor.getDoctorAuth().get(0).getAuthPic(), mDoctorImg);
            imageLoader.displayImage(UrlHelpper.FILE_IP + doctor.getAvatar(), mDoctorImg);
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(FamousDoctorListActivity.this, DoctorDetailActivity.class);
            FamousDoctor.DataEntity dataEntity = infos.get(curPosition);
            intent.putExtra("doctorId", String.valueOf(dataEntity.getId()));
            Log.i("TAG", "医生id" + String.valueOf(dataEntity.getId()));
            startActivity(intent);
        }
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
            intent.putExtra("from", Constants.FROM_FAMOUS);
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
    protected void onDestroy() {
        super.onDestroy();
    }
}
