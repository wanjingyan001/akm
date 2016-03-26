package cn.zsmy.akm.doctor;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Doctors;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.UrlHelpper;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sanz on 2015/11/24 13:12
 */
public class DoctorSearchActivity extends BaseTitleListActivity implements View.OnClickListener {
    private int from;
    private EditText search_edit;
    private ImageView cancel_img;
    private TextView move_back;
    private String name;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                showContent(EmptyView.State.empty, 1);
            } else {
                showContent(EmptyView.State.ing, 1);
            }

        }
    };

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_doctor_search);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeData() {
        setTitle("医生搜索");
        from = getIntent().getIntExtra("from", -1);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        search_edit = (EditText) findViewById(R.id.search_doctor_EditID);
        cancel_img = (ImageView) findViewById(R.id.search_doctor_cancel);
        move_back = (TextView) findViewById(R.id.move_back);
        cancel_img.setOnClickListener(this);
        move_back.setOnClickListener(this);
        initEdit();
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    private void loadSearchDoctorInfo(String name) {
        String s = null;
        try {
            s = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Request request = new Request(UrlHelpper.DOCTOR_SEARCH_WITH_NAME + "?name=" + s, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Doctors doctors = JsonParser.deserializeFromJson(result, Doctors.class);
                modules.clear();
                modules.addAll(doctors.getData());
                if (modules.size() == 0) {
                    handler.sendEmptyMessage(0);
                } else{
                    handler.sendEmptyMessageDelayed(1, 1000);
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
            switch (from) {
                case Constants.FROM_RECOMMEND:
                    convertView = LayoutInflater.from(this).inflate(R.layout.layout_dector_item, null);
                    break;
                case Constants.FROM_FAMOUS:
                    convertView = LayoutInflater.from(this).inflate(R.layout.layout_famous_item, null);
                    break;
            }
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_doctor_cancel:
                search_edit.setText(null);
                break;
            case R.id.move_back:
                finish();
                break;
        }
    }

    class Holder extends QBaseViewHolder implements View.OnClickListener {
        CircleImageView general_head, famous_head;
        TextView job_title, doctor_name, doctor_post, hospital, doctor_good_at, recommended_number;
        TextView experience, famous_doctor_name, famous_doctor_post, famous_hospital, famous_doctor_good_at, price, clinic;
        int position;

        @Override
        public void initializeView(View view) {
            setInitViewType(view);
        }

        @Override
        public void initializeData(int position) {
            setInitDataType(position);
        }

        private void setInitViewType(View view) {
            switch (from) {
                case Constants.FROM_RECOMMEND:
                    setGeneralDoctorSearchItem(view);
                    break;
                case Constants.FROM_FAMOUS:
                    setFamousDoctorSearchItem(view);
                    break;
            }
        }

        private void setInitDataType(int position) {
            switch (from) {
                case Constants.FROM_RECOMMEND:
                    setGeneralDoctorSearchItemData(position);
                    break;
                case Constants.FROM_FAMOUS:
                    setFamousDoctorSearchItemData(position);
                    break;
            }
        }

        private void setGeneralDoctorSearchItem(View view) {
            general_head = (CircleImageView) view.findViewById(R.id.doctoe_head_portrait);
            job_title = (TextView) view.findViewById(R.id.post);
            doctor_name = (TextView) view.findViewById(R.id.doctor_name);
            doctor_post = (TextView) view.findViewById(R.id.doctor_post);
            hospital = (TextView) view.findViewById(R.id.hospital);
            doctor_good_at = (TextView) view.findViewById(R.id.doctor_good_at);
            recommended_number = (TextView) view.findViewById(R.id.recommended_number);
            view.setOnClickListener(this);
        }

        private void setFamousDoctorSearchItem(View view) {
            famous_head = (CircleImageView) view.findViewById(R.id.doctoe_head_portrait);
            experience = (TextView) view.findViewById(R.id.experience);
            famous_doctor_name = (TextView) view.findViewById(R.id.doctor_name);
            famous_doctor_post = (TextView) view.findViewById(R.id.doctor_post);
            famous_hospital = (TextView) view.findViewById(R.id.hospital);
            famous_doctor_good_at = (TextView) view.findViewById(R.id.doctor_good_at);
            price = (TextView) view.findViewById(R.id.price);
            clinic = (TextView) view.findViewById(R.id.clinic);
            view.setOnClickListener(this);
        }

        private void setGeneralDoctorSearchItemData(int position) {
            this.position = position;
            Doctors.DataEntity dataEntity = (Doctors.DataEntity) modules.get(position);
            job_title.setText(dataEntity.getProfessionalTitle());
            doctor_name.setText(dataEntity.getName());
            doctor_post.setText(dataEntity.getDept());
            hospital.setText(dataEntity.getHospital());
            doctor_good_at.setText(dataEntity.getSpecialty());
            recommended_number.setText(String.valueOf(dataEntity.getReceptionNum()));
            ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + dataEntity.getDoctorAuth().get(0).getAuthPic(), general_head);
        }

        private void setFamousDoctorSearchItemData(int position) {
            this.position = position;
            Doctors.DataEntity dataEntity = (Doctors.DataEntity) modules.get(position);
            experience.setText(dataEntity.getWorking() + "经验");
            famous_doctor_name.setText(dataEntity.getName());
            famous_doctor_post.setText(dataEntity.getDept());
            famous_hospital.setText(dataEntity.getHospital());
            famous_doctor_good_at.setText(dataEntity.getSpecialty());
            List<Doctors.DataEntity.PriceEntity> prc = dataEntity.getPrice();
            if (prc != null) {
                String amt = prc.get(0).getAmt();
                String s = amt.replace(amt.substring(amt.indexOf('.'), amt.length()), "元");
                price.setText(s + "/" + prc.get(0).getUnit());
                if (s.equals("0元")) {
                    price.setVisibility(View.INVISIBLE);
                    clinic.setVisibility(View.VISIBLE);
                } else {
                    clinic.setVisibility(View.INVISIBLE);
                }
            }
            ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + dataEntity.getDoctorAuth().get(0).getAuthPic(), famous_head);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Doctors.DataEntity dataEntity = (Doctors.DataEntity) modules.get(position);
            String id = dataEntity.getId();
            startActivity(DoctorDetailActivity.getIntent(DoctorSearchActivity.this, id));
        }
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_doctor_search_view, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_search) {
//        }
//        return super.onOptionsItemSelected(item);
//    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Doctors.DataEntity dataEntity = (Doctors.DataEntity) modules.get(position - 1);
        Intent intent = DoctorDetailActivity.getIntent(this, dataEntity.getId());
        startActivity(intent);
    }

    public void initEdit() {
        TextWatcher watcher = new TextWatcher() {
            @SuppressWarnings({"unchecked", "rawtypes"})
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(search_edit.getText().toString().equals("")){
//                    adapter=new AddressAdapter(BankAndCityActivity.this,datas_other,3);
//                    adapter.notifyDataSetChanged();
//                }else{
//                    other.clear();
//                    for(int i=0;i<datas_other.size();i++){
//                        if(datas_other.get(i).getKid_bank_name().contains(s)){
//                            other.add(datas_other.get(i));
//                        }
//                    }
//                    adapter=new AddressAdapter(BankAndCityActivity.this,other,3);
//                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = search_edit.getText().toString();
                Log.i("TAG", name);
                if (!TextUtils.isEmpty(name)) {
                    Log.i("TAG", ">>>>>>>>" + name);
                    loadSearchDoctorInfo(name);
                }

            }
        };
        search_edit.addTextChangedListener(watcher);
    }
}
