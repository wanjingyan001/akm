package cn.zsmy.akm.doctor.learning.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import cn.zsmy.akm.doctor.conversation.PostDetailActivity;
import cn.zsmy.akm.doctor.learning.bean.PatientCase;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/18 16:17
 */
public class CaseAdapter extends BaseAdapter {

    private ArrayList<PatientCase.DataEntity> datas;
    private Context context;

    public CaseAdapter(Context context, ArrayList<PatientCase.DataEntity> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_case, null);
            holdView.initializeView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.initializeData(position);
        return convertView;
    }

    public class HoldView implements View.OnClickListener {
        private TextView title_tex, name_tex, num_tex, content_tex;
        private int position;
        private ImageView caseImage;

        public void initializeView(View view) {
            title_tex = (TextView) view.findViewById(R.id.case_title);
            name_tex = (TextView) view.findViewById(R.id.case_doctor_name);
            num_tex = (TextView) view.findViewById(R.id.case_creat_time);
            content_tex = (TextView) view.findViewById(R.id.case_content);
            caseImage = ((ImageView) view.findViewById(R.id.case_post_image));
            view.setOnClickListener(this);
        }

        public void initializeData(int position) {
            this.position = position;
            PatientCase.DataEntity dataEntity = datas.get(position);
            title_tex.setText(dataEntity.getTitle());
            content_tex.setText(dataEntity.getContent());
            String pics = dataEntity.getPics();
            if (!TextUtils.isEmpty(pics)) {
                caseImage.setVisibility(View.VISIBLE);
                String[] split = pics.split(",");
                Log.d("TAG", split[0]);
                ImageLoader.getInstance().displayImage(UrlHelper.IP + split[0], caseImage);
            } else {
                caseImage.setVisibility(View.GONE);
            }
            caseImage.requestLayout();
            if (dataEntity.getNickname() != null) {
                name_tex.setText(dataEntity.getNickname().toString());
            } else {
                name_tex.setText("匿名");
            }
            String supportNum = null;
            String visitNum = null;
            if (dataEntity.getSupportNum() != null) {
                supportNum = String.valueOf(dataEntity.getSupportNum());
            } else {
                supportNum = "0";
            }
            if (dataEntity.getVisitNum() != null) {
                visitNum = String.valueOf(dataEntity.getVisitNum());
            } else {
                visitNum = "0";
            }
            num_tex.setText(Integer.valueOf(supportNum) + "/" + Integer.valueOf(visitNum));

        }


        @Override
        public void onClick(View v) {
            PatientCase.DataEntity dataEntity = datas.get(position);
            context.startActivity(PostDetailActivity.getIntent(context,dataEntity));
        }
    }
}
