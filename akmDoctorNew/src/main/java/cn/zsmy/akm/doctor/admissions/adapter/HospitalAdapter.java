package cn.zsmy.akm.doctor.admissions.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/17 15:38
 */
public class HospitalAdapter extends BaseAdapter {
    private List<String> datas;
    private Context context;
    private int type;

    public HospitalAdapter(List<String> datas, Context context, int type) {
        this.datas = datas;
        this.context = context;
        this.type = type;
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
        HoldView holdView = null;
        if (convertView == null || convertView.getTag() == null) {
            holdView = new HoldView();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_hospital, null);
            convertView.setTag(holdView);
            holdView.initializeView(convertView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.initializeData(position);
        if (type == 0) {
            if (position == selectItem) {
                convertView.setBackgroundColor(Color.WHITE);
            } else {
                convertView.setBackgroundColor(context.getResources().getColor(R.color.bg_lightgray_select));
            }
        }
        return convertView;

    }

    private int selectItem;

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    public class HoldView {
        private TextView hosName;
        private LinearLayout group;

        public void initializeView(View v) {
            group = (LinearLayout) v.findViewById(R.id.hospital_group);
            hosName = (TextView) v.findViewById(R.id.hospital_name_tex);
        }

        public void initializeData(int position) {
            String name = datas.get(position);
            hosName.setText(name);
            if (type == 0) {
                group.setBackgroundColor(context.getResources().getColor(R.color.windowBackground));
            } else if (type == 1) {
                group.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }
    }
}
