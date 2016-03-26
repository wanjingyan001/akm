package cn.zsmy.akm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.wei.library.adapter.QBaseAdapter;

/**
 * Created by Administrator on 2015/11/25.
 */
public class NewPatientAdapter extends QBaseAdapter{
private Context context;
private List<String> datas;
    public NewPatientAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }
    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        
        return null;
    }

}
