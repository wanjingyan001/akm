package cn.zsmy.akm.doctor.admissions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.doctor.admissions.bean.DrugSuggest;
import cn.zsmy.doctor.R;

/**
 * Created by Administrator on 2016/1/13.
 */
public class MedicationAdapter extends BaseAdapter {
    private List<DrugSuggest> maps;
    private Context context;
    private String Name;
    private String Num;

    public MedicationAdapter(Context context, List<DrugSuggest> maps) {
        this.context = context;
        this.maps = maps;
    }

    @Override
    public int getCount() {
        return maps.size();
    }


    @Override
    public Object getItem(int position) {
        return maps.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_doc_suggest_drug, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.initializeData(position);
        if (Name != null && Num != null) {
            holder.drugName.setText(Name);
            holder.drugNum.setText(Num);
        }
        return convertView;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    class ViewHolder extends QBaseViewHolder {

        private TextView drugName;
        private TextView drugNum;

        @Override
        public void initializeView(View view) {
            drugName = ((TextView) view.findViewById(R.id.connact_name));
            drugNum = ((TextView) view.findViewById(R.id.connact_num));
        }

        @Override
        public void initializeData(int position) {
            DrugSuggest drugSuggest = maps.get(position);
            drugName.setText(drugSuggest.getDrugName());
            drugNum.setText("每日" + drugSuggest.getUsageAmount() + "次");
        }
    }
}
