package cn.zsmy.akm.doctor.learning.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.zsmy.akm.doctor.learning.bean.PatientCase;
import cn.zsmy.akm.doctor.utils.DateUtils;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sanz on 2015/12/19 14:28
 */
public class ScholarshipAdapter extends BaseAdapter {
    private List<PatientCase.DataEntity> datas;
    private Context context;

    public ScholarshipAdapter(Context context, List<PatientCase.DataEntity> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_scholarship, null);
            holdView.initializeView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.initializeData(position);
        return convertView;
    }

    public class HoldView {
        private TextView title_tex, name_tex, num_tex, time_text;
        private CircleImageView head_pic;
        private GridView gridView;

        public void initializeView(View view) {
            title_tex = (TextView) view.findViewById(R.id.title);
            name_tex = (TextView) view.findViewById(R.id.name);
            num_tex = (TextView) view.findViewById(R.id.reply_num);
            time_text = (TextView) view.findViewById(R.id.time);
            head_pic = (CircleImageView) view.findViewById(R.id.head_pic);
            head_pic.setBorderWidth(1);
            head_pic.setBorderColor(context.getResources().getColor(R.color.gray));
        }

        public void initializeData(int position) {
            PatientCase.DataEntity dataEntity = datas.get(position);
            String title = dataEntity.getTitle();
            String name = (String) dataEntity.getNickname();
            String time = DateUtils.getDateToString(dataEntity.getCreateTime(), 2);
            int supportNum = 0;
            if (TextUtils.isEmpty(dataEntity.getSupportNum())) {

            } else {
                supportNum = Integer.valueOf(dataEntity.getSupportNum());
            }
            int visitNum = 0;
            if (TextUtils.isEmpty(dataEntity.getVisitNum())) {

            } else {
                visitNum = Integer.valueOf(dataEntity.getVisitNum());
            }
            title_tex.setText(title);
            if (name != null) {
                name_tex.setText(name);
            }
            time_text.setText(time);
            num_tex.setText(supportNum + "/" + visitNum);
            if (dataEntity.getAvatar() != null) {
                String pic_url = dataEntity.getAvatar().toString();
                ImageLoader.getInstance().displayImage(UrlHelper.IP + pic_url, head_pic);
            }

        }
    }
}
