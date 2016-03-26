package cn.zsmy.akm.salon.activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.R;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * Created by Administrator on 2016/1/21.
 */
public class PostAdapter extends BaseAdapter {
    private String[] picPaths;
    private Context context;

    public PostAdapter(Context context, String[] picPaths) {
        this.context = context;
        this.picPaths = picPaths;
    }

    @Override
    public int getCount() {
        return picPaths.length;
    }

    @Override
    public String getItem(int position) {
        return picPaths[position];
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_post_detail_image, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }


    public class Holder extends QBaseViewHolder {
        private ImageView detailImage;

        @Override
        public void initializeView(View view) {
            detailImage = ((ImageView) view.findViewById(R.id.gridview_item_img));
        }

        /**
         * @param position
         */
        @Override
        public void initializeData(int position) {
            if (picPaths.length != 0) {
                String uri = UrlHelpper.FILE_IP + picPaths[position];
                Log.d("TAG", uri + ">>>>>>>>");
                detailImage.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(uri, detailImage);
            } else {
                detailImage.setVisibility(View.GONE);
            }
        }
    }
}
