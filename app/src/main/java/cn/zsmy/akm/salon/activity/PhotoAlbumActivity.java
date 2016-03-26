package cn.zsmy.akm.salon.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zsmy.akm.R;
import cn.zsmy.akm.chat.utils.Constants;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.salon.bean.PhotoAlbum;
import cn.zsmy.akm.utils.AlbumHelper;


public class PhotoAlbumActivity extends BasePictureGridViewActivity {
    public static final String EXTRA_IMAGE_LIST = "imagelist";

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_photo_album);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    public void initializeData() {
        setTitle("相册");
        modules.addAll(AlbumHelper.getInstance(getApplicationContext()).getImagesBucketList(false));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(PhotoAlbumActivity.this, ImageGridActivity.class);
        PhotoAlbum imgs = (PhotoAlbum) modules.get(position);
        intent.putExtra(Constants.KEY_OPEN_CLASS, getIntent().getSerializableExtra(Constants.KEY_OPEN_CLASS));
        intent.putExtra(EXTRA_IMAGE_LIST, imgs.imageList);
        startActivity(intent);
        finish();
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(PhotoAlbumActivity.this, R.layout.layout_photo_album_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder {
        private ImageView mPhotoAlbumImg;
        private ImageView mPhotoAlbumIsSelected;
        private TextView mPhotoAlbumNameLabel;
        private TextView mPhotoAlbumCountLabel;

        public void initializeView(View v) {
            mPhotoAlbumImg = (ImageView) v.findViewById(R.id.mPhotoAlbumImg);
            mPhotoAlbumIsSelected = (ImageView) v.findViewById(R.id.mPhotoAlbumIsSelected);
            mPhotoAlbumNameLabel = (TextView) v.findViewById(R.id.mPhotoAlbumNameLabel);
            mPhotoAlbumCountLabel = (TextView) v.findViewById(R.id.mPhotoAlbumCountLabel);
        }

        public void initializeData(int position) {
            PhotoAlbum item = (PhotoAlbum) modules.get(position);
            mPhotoAlbumCountLabel.setText("" + item.count);
            mPhotoAlbumNameLabel.setText(item.bucketName);
            mPhotoAlbumIsSelected.setVisibility(View.GONE);
            if (item.imageList != null && item.imageList.size() > 0) {
                String thumbPath = item.imageList.get(0).thumbnailPath;
                String sourcePath = item.imageList.get(0).imagePath;
                mPhotoAlbumImg.setTag(sourcePath);
                imageLoader.displayImage("file://"+sourcePath, mPhotoAlbumImg, options);
            } else {
                mPhotoAlbumImg.setImageBitmap(null);
                trace("no images in bucket " + item.bucketName);
            }
        }
    }
}
