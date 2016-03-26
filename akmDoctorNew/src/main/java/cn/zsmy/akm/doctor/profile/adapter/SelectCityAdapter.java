package cn.zsmy.akm.doctor.profile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.platform.comapi.map.E;

import java.util.List;

import cn.zsmy.akm.doctor.profile.bean.CityName;
import cn.zsmy.akm.doctor.profile.bean.HospitalName;
import cn.zsmy.akm.doctor.profile.bean.JobTitle;
import cn.zsmy.akm.doctor.profile.bean.OfficeName;
import cn.zsmy.akm.doctor.profile.bean.ProvinceName;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2016/1/12 17:34
 */
public class SelectCityAdapter extends BaseAdapter {
    private List<Object> datas;
    private Context context;

    public SelectCityAdapter(List<Object> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        if (convertView == null || convertView.getTag() == null) {
            holdView = new HoldView();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_city, null);
            holdView.initializeView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.initializeData(position);
        return convertView;
    }

    class HoldView {
        private TextView title;

        public void initializeView(View view) {
            title = (TextView) view.findViewById(R.id.city_name);

        }

        public void initializeData(int position) {
            if(datas.get(position) instanceof CityName.DataEntity){
                CityName.DataEntity city =(CityName.DataEntity) datas.get(position);
                title.setText(city.getName());
            }else if(datas.get(position) instanceof JobTitle.DataEntity){
                JobTitle.DataEntity job =(JobTitle.DataEntity) datas.get(position);
                title.setText(job.getName());
            }
            else if(datas.get(position) instanceof HospitalName.DataEntity){
                HospitalName.DataEntity hos =(HospitalName.DataEntity) datas.get(position);
                title.setText(hos.getName());
            }
            else if(datas.get(position) instanceof OfficeName.DataEntity){
                OfficeName.DataEntity officeName =(OfficeName.DataEntity) datas.get(position);
                title.setText(officeName.getName());
            }
            else if(datas.get(position) instanceof ProvinceName.DataEntity){
                ProvinceName.DataEntity province_name =(ProvinceName.DataEntity) datas.get(position);
                title.setText(province_name.getName());
            }

        }
    }
}
