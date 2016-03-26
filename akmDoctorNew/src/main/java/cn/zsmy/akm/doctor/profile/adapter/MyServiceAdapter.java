package cn.zsmy.akm.doctor.profile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.doctor.R;

/**
 * 我的服务
 * Created by Administrator on 2015/12/28.
 */
public class MyServiceAdapter extends BaseAdapter {
    private List<String> strings;
    private Context context;

    public MyServiceAdapter(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }


    @Override
    public Object getItem(int position) {
        return strings.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_service, null);
            holdView.initializeView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (Holder) convertView.getTag();
        }
        holdView.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private TextView myService;

        @Override
        public void initializeView(View view) {
            myService = ((TextView) view.findViewById(R.id.my_service));
        }

        @Override
        public void initializeData(int position) {
            String s = strings.get(position);
            myService.setText(s);
        }
    }
}
