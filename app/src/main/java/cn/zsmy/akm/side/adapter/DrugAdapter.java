package cn.zsmy.akm.side.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.zsmy.akm.R;
import cn.zsmy.akm.side.bean.Drugs;
import cn.zsmy.akm.utils.UrlHelpper;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sanz on 2015/12/18 10:25
 */
public class DrugAdapter extends BaseAdapter {
    private Context context;
    private List<Drugs.DataEntity> datas;

    public DrugAdapter(Context context, List<Drugs.DataEntity> datas) {
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
        if (convertView == null) {
            holdView = new HoldView();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_drug_list_item, null);
            holdView.initializeView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.initializeData(position);
        return convertView;
    }

    public class HoldView {
        private TextView drugName;
        private CircleImageView drugImg;
        private TextView drugContent;

        public void initializeView(View v) {
            drugName = (TextView) v.findViewById(R.id.drug_name);
            drugImg = ((CircleImageView) v.findViewById(R.id.drug_pic));
            drugContent = ((TextView) v.findViewById(R.id.drug_content));
        }

        public void initializeData(int position) {
            Drugs.DataEntity dataEntity = datas.get(position);
            if (dataEntity != null) {
                drugName.setText(dataEntity.getName());
                if (dataEntity.getStandard()!=null){
                    drugContent.setText(dataEntity.getStandard());
                }
                if (dataEntity.getIcon() != null) {
                    ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + dataEntity.getIcon(), drugImg);
                }
            }
        }
    }
}
