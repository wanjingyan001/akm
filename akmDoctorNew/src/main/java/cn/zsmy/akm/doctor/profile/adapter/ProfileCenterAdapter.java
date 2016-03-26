package cn.zsmy.akm.doctor.profile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.zsmy.doctor.R;


/**
 * Created by zzz on 15/12/21.
 */
public class ProfileCenterAdapter extends BaseAdapter {
    private List<String> datas;
    private Context context;
    private List<Integer> drawableDatas;

    public ProfileCenterAdapter(List<String> datas, List<Integer> drawbleDatas, Context context) {
        this.datas = datas;
        this.context = context;
        this.drawableDatas = drawbleDatas;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_profile_listview, null);
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
            title = (TextView) view.findViewById(R.id.title);
            pic = (ImageView) view.findViewById(R.id.pic);

        }
        public void initializeData(int position) {
            String name = datas.get(position);
            title.setText(name);
            pic.setImageResource(drawableDatas.get(position));
        }
    }
}
