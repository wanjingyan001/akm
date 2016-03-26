package cn.zsmy.akm.salon.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.salon.bean.Bimp;
import cn.zsmy.akm.salon.bean.ImageModule;

public class ImageGridActivity extends BasePictureGridViewActivity implements OnClickListener {
    public static final String EXTRA_IMAGE_LIST = "imagelist";

    private ArrayList<String> cahceImages = new ArrayList<String>();
    Button bt;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(ImageGridActivity.this, "最多选择" + Bimp.SELECT_MAX_SIZE + "张图片", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    public interface TextCallback {
        public void onListen(int count);
    }

    private TextCallback textcallback = null;
    private int selectTotal = 0;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_image_grid);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    public void initializeView() {
        super.initializeView();
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void initializeData() {
        setTitle("相册");
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.bg_transparent)
                .showImageForEmptyUri(R.drawable.bg_transparent).showImageOnFail(R.drawable.bg_transparent).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        textcallback = (new TextCallback() {
            public void onListen(int count) {
                bt.setText("完成" + "(" + count + ")");
            }
        });
        modules.addAll((List<ImageModule>) getIntent().getSerializableExtra(EXTRA_IMAGE_LIST));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(ImageGridActivity.this).inflate(R.layout.item_image_grid, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

    class Holder implements OnClickListener {
        private ImageView iv;
        private ImageView selected;
        private TextView text;
        private ImageModule item;

        public void initializeView(View v) {
            iv = (ImageView) v.findViewById(R.id.mPhotoAlbumImg);
            selected = (ImageView) v.findViewById(R.id.mPhotoAlbumIsSelected);
            text = (TextView) v.findViewById(R.id.item_image_grid_text);
        }

        @SuppressWarnings("deprecation")
        public void initializeData(int position) {
            item = (ImageModule) modules.get(position);
            iv.setTag(item.imagePath);
            String uri = null;
            if (item.thumbnailPath != null && new File(item.thumbnailPath).exists()) {
                uri = item.thumbnailPath;
            } else {
                uri = item.imagePath;
            }
            imageLoader.displayImage("file://" + uri, iv, options, animateFirstListener);
            if (item.isSelected) {
                selected.setVisibility(View.VISIBLE);
                selected.setImageResource(R.drawable.icon_data_select);
                text.setBackgroundResource(R.drawable.bgd_relatly_line);
            } else {
                selected.setVisibility(View.INVISIBLE);
                text.setBackgroundColor(0x00000000);
            }
            iv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if ((Bimp.drr.size() + selectTotal) < Bimp.SELECT_MAX_SIZE) {
                item.isSelected = !item.isSelected;
                if (item.isSelected) {
                    selectTotal++;
                    cahceImages.add(item.imagePath);
                    if (textcallback != null)
                        textcallback.onListen(selectTotal);

                } else if (!item.isSelected) {
                    selectTotal--;
                    cahceImages.remove(item.imagePath);
                    if (textcallback != null)
                        textcallback.onListen(selectTotal);
                }
            } else if ((Bimp.drr.size() + selectTotal) >= Bimp.SELECT_MAX_SIZE) {
                if (item.isSelected == true) {
                    item.isSelected = !item.isSelected;
                    selected.setImageResource(-1);
                    selectTotal--;
                } else {
                    Message message = Message.obtain(mHandler, 0);
                    message.sendToTarget();
                }
            }
            mAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < cahceImages.size(); i++) {
            if (Bimp.drr.size() < Bimp.SELECT_MAX_SIZE) {
                Bimp.drr.add(cahceImages.get(i));
            }
        }
        Intent intent = new Intent(BasePublishedActivity.ACTION_UPDATE_PICTURE_SHOW);
        intent.putExtra("pthots", (Serializable) Bimp.drr);
        // Class<?> clazz = (Class<?>)
        // getIntent().getSerializableExtra(Constants.KEY_OPEN_CLASS);
        // Intent intent = new Intent(ImageGridActivity.this, clazz);
        // intent.putExtra(Constants.KEY_IS_UPDATE_PICTURE_LIST, true);
        // startActivity(intent);
//        setResult(Activity.RESULT_OK,intent);
        sendBroadcast(intent);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.notifyDataSetChanged();
    }
}
