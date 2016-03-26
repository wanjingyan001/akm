package cn.zsmy.akm.widget.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.R;

/**
 * Created by qinwei on 2015/11/25 10:13
 */
public class ExampleListPopWindow extends BaseListPopWindow {
    public ExampleListPopWindow(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_pop_window_list_example;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.fragment_conversation_item,null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        }else{
            holder= (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }
    class Holder extends QBaseViewHolder {

        @Override
        public void initializeView(View view) {

        }

        @Override
        public void initializeData(int position) {

        }
    }
}
