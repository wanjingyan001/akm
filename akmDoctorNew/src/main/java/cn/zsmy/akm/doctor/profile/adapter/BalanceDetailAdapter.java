package cn.zsmy.akm.doctor.profile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/22 16:45
 * 收支明细和我的积分 适配器
 */
public class BalanceDetailAdapter extends BaseAdapter {
    private Context context;
    private List<String> datas;

    public BalanceDetailAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_profile_integral, null);
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
        private ImageView pic;

        public void initializeView(View view) {
            title = (TextView) view.findViewById(R.id.consumption_type);
            pic = (ImageView) view.findViewById(R.id.all_money_pic);

        }

        public void initializeData(int position) {
            String name = datas.get(position);
            title.setText(name);
        }
    }
}
