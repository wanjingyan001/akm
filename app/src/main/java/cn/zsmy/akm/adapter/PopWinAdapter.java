package cn.zsmy.akm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Cityies;
import cn.zsmy.akm.entity.Province;
import cn.zsmy.akm.entity.Sections;

/**
 * Created by Administrator on 2015/12/23.
 */
public class PopWinAdapter extends BaseAdapter {
    private Context context;
    private List<Object> objects;

    public PopWinAdapter(Context context, List<Object> objects) {
        this.context = context;
        this.objects = objects;
    }


    @Override
    public int getCount() {
        return objects.size();
    }


    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_doctor_popwin, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private TextView popTitle;

        @Override
        public void initializeView(View view) {
            popTitle = ((TextView) view.findViewById(R.id.popwin_title));
        }

        /**
         * @param position
         */
        @Override
        public void initializeData(int position) {
            Object o = objects.get(position);
            if (o instanceof Cityies.DataEntity) {
                String name = ((Cityies.DataEntity) o).getName();
                popTitle.setText(name);
            } else if (o instanceof Sections.DataEntity) {
                String name = ((Sections.DataEntity) o).getName();
                popTitle.setText(name);
            } else if (o instanceof Province.DataEntity) {
                String name = ((Province.DataEntity) o).getName();
                popTitle.setText(name);
            } else if (o instanceof Province.DataEntity) {
                String name = ((Province.DataEntity) o).getName();
                popTitle.setText(name);
            } else if (o instanceof Sections.DataEntity.SubsEntity) {
                String name = ((Sections.DataEntity.SubsEntity) o).getName();
                popTitle.setText(name);
            }
        }
    }
}
