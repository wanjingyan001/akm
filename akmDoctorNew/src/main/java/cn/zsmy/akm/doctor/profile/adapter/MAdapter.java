package cn.zsmy.akm.doctor.profile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.doctor.R;

/**
 * 更多界面adapter
 * Created by Administrator on 2015/12/28.
 */
public class MAdapter extends BaseAdapter {
    private List<Map<String, Integer>> list;
    private Context context;

    public MAdapter(Context context, List<Map<String, Integer>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holdView;
        if (convertView == null || convertView.getTag() == null) {
            holdView = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_more, null);
            holdView.initializeView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (Holder) convertView.getTag();
        }
        holdView.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private ImageView mOperatingImg;
        private TextView mOperatingTitle;

        @Override
        public void initializeView(View view) {
            mOperatingImg = ((ImageView) view.findViewById(R.id.service_image));
            mOperatingTitle = ((TextView) view.findViewById(R.id.my_service));
        }


        @Override
        public void initializeData(int position) {
            Map<String, Integer> map = list.get(position);
            Set<Map.Entry<String, Integer>> entries = map.entrySet();
            Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, Integer> next = iterator.next();
                mOperatingTitle.setText(next.getKey());
                mOperatingImg.setImageResource(next.getValue());
            }
        }
    }
}
