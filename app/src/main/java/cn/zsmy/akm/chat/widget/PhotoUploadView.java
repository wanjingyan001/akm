package cn.zsmy.akm.chat.widget;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.zsmy.akm.R;
import cn.zsmy.akm.chat.utils.SystemPhoto;

/**
 * 九宫格存放待处理的图片
 * 
 * @author qinwei
 * 
 */
public class PhotoUploadView extends LinearLayout implements OnItemClickListener {
	private Context context;
	private ArrayList<String> imgs = new ArrayList<String>();
	private GridView mGridview;
	private PhotoAdapter photoAdapter;
	private SystemPhoto systemPhoto;
	private OnPhotoAddListener listener;

	public static interface OnPhotoAddListener {
		void onPhotoAdd();
	}

	public PhotoUploadView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView(context);
	}

	public PhotoUploadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView(context);
	}

	public PhotoUploadView(Context context) {
		super(context);
		initializeView(context);
	}

	public void setOnPhotoAddListener(OnPhotoAddListener listener) {
		this.listener = listener;

	}

	private void initializeView(Context context) {
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.widget_photo_upload, this);
		systemPhoto = new SystemPhoto(context);
		mGridview = (GridView) findViewById(R.id.mGridview);
		mGridview.setOnItemClickListener(this);
		photoAdapter = new PhotoAdapter();
		mGridview.setAdapter(photoAdapter);
		imgs.add("");
		photoAdapter.notifyDataSetChanged();
	}

	public void addImage(String path){
		imgs.add(0, path);
		photoAdapter.notifyDataSetChanged();
	}
	public void notifyDataSetChanged() {
		photoAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (position == imgs.size() - 1 && listener != null) {
			listener.onPhotoAdd();
		}
	}

	class PhotoAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imgs.size();
		}

		@Override
		public Object getItem(int position) {
			return imgs.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if (convertView == null) {
				holder = new Holder();
				convertView = LayoutInflater.from(context).inflate(R.layout.widget_adapter_photo_item, null);
				holder.mPhotoItem = (ImageView) convertView.findViewById(R.id.mPhotoItem);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			if (position == imgs.size() - 1) {
				holder.mPhotoItem.setImageResource(R.drawable.add_1);
			} else {
				holder.mPhotoItem.setImageBitmap(systemPhoto.getImageThumbnail(imgs.get(position), 200, 200));
			}
			return convertView;
		}

		class Holder {
			public ImageView mPhotoItem;
		}
	}



	public ArrayList<String> getImages() {
		ArrayList<String> images = new ArrayList<String>();
		if (imgs.size() == 1) {
			return images;
		}
		for (int i = 0; i < imgs.size()-1; i++) {
			images.add(imgs.get(i));
		}
		return images;
	}

}
