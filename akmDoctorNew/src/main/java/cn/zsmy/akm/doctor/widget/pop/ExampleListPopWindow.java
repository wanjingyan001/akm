package cn.zsmy.akm.doctor.widget.pop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.doctor.R;

/**
 * Created by qinwei on 2015/11/25 10:13
 */
public class ExampleListPopWindow extends BaseListPopWindow {
    public ExampleListPopWindow(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.pop_select_drug_sections;
    }

    @Override
    public void initializeData() {
        for (int i = 0; i <10 ; i++) {
            modules.add("");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView==null||convertView.getTag()==null){
            holder=new Holder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_select_hospital,null);
            Log.i("TAG", convertView + "");
            holder.initializeView(convertView);
            convertView.setTag(holder);
        }else{
            holder= (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }
    class Holder extends QBaseViewHolder {
        private TextView hosName;
        @Override
        public void initializeView(View view) {
            hosName=(TextView)view.findViewById(R.id.hospital_name_tex);
        }

        @Override
        public void initializeData(int position) {
//            String name=(String)modules.get(position);
//            Log.i("TAG", name + "");
//            hosName.setText(name);
        }
    }
}
