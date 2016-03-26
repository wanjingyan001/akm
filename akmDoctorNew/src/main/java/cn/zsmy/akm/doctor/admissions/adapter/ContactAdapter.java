package cn.zsmy.akm.doctor.admissions.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Objects;

import cn.wei.library.widget.BadgeView;
import cn.zsmy.akm.doctor.admissions.bean.Contact;
import cn.zsmy.akm.doctor.chat.utils.StringUtils;
import cn.zsmy.akm.doctor.utils.DateUtils;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sanz on 2015/12/17 9:29
 */
public class ContactAdapter extends BaseAdapter {
    private List<Contact.DataEntity> datas;
    private Context context;

    public ContactAdapter(List<Contact.DataEntity> datas, Context context) {
        this.datas = datas;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_office, null);
            holdView.initializeView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.initializeData(position);
        return convertView;
    }

    class HoldView {
        private TextView name_tex, contact_num, content_tex;
        private CircleImageView head_pic;
        private TextView contactTime;

        public void initializeView(View view) {
            name_tex = (TextView) view.findViewById(R.id.contact_name);
            contact_num = (TextView) view.findViewById(R.id.contact_chatInfoNum);
            head_pic = (CircleImageView) view.findViewById(R.id.head_pic);
            content_tex = (TextView) view.findViewById(R.id.contact_content);
            contactTime = ((TextView) view.findViewById(R.id.contact_time));
        }

        public void initializeData(int position) {
            Contact.DataEntity dataEntity = datas.get(position);
            if (dataEntity.getPatient() != null) {
                String name = dataEntity.getPatient().getName();
                int num = dataEntity.getPatient().getCaseNum();
                if(dataEntity.getInquirer()!=null){
                    if(dataEntity.getInquirer().getNickname()!=null){
                        name_tex.setText(dataEntity.getInquirer().getNickname());
                    }else if(dataEntity.getInquirer().getPhone()!=null){
                        name_tex.setText( StringUtils.getPhone(dataEntity.getInquirer().getPhone()));
                    }
                }else{
                    name_tex.setText(null);
                }
                contactTime.setText(DateUtils.getDateToString(dataEntity.getModifyTime(), 2));
                initBageViewConfig(contact_num, num);
                    if(null!=dataEntity.getContent()){
                        content_tex.setText(dataEntity.getContent());
                    }


                if(dataEntity.getInquirer() instanceof Object){

                }else{
                    if(null!=dataEntity.getInquirer()){
                        if(dataEntity.getInquirer().getAvatar()!=null){
                            ImageLoader.getInstance().displayImage(UrlHelper.IP+dataEntity.getInquirer().getAvatar(), head_pic);
                        }
                    }

                }

            }
        }
    }

    public void initBageViewConfig(TextView contact_num, int num) {
        BadgeView badgeView = new BadgeView(context);
        badgeView.setTargetView(contact_num);
        badgeView.setBadgeGravity(Gravity.CENTER);
        badgeView.setBadgeCount(num);
    }
}
