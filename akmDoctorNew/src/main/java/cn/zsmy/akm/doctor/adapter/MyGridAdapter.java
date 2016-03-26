package cn.zsmy.akm.doctor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;

import cn.zsmy.akm.doctor.chat.utils.ImageUtils;
import cn.zsmy.akm.doctor.conversation.choosePhoto.Bimp;
import cn.zsmy.akm.doctor.conversation.choosePhoto.ImageItem;
import cn.zsmy.akm.doctor.conversation.choosePhoto.PublicWay;
import cn.zsmy.akm.doctor.utils.FileUtils;
import cn.zsmy.doctor.R;

/**
 * 发帖界面的gridView适配器
 * Created by Administrator on 2015/12/21.
 */
public class MyGridAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private boolean shape;
    private Context context;

    public boolean isShape() {
        return shape;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public MyGridAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void update() {
        loading();
    }

    public int getCount() {
        if (Bimp.tempSelectBitmap.size() == 9) {
            return 9;
        }
        return (Bimp.tempSelectBitmap.size() + 1);
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida,
                    parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView
                    .findViewById(R.id.item_grida_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == Bimp.tempSelectBitmap.size()) {
            holder.image.setImageBitmap(BitmapFactory.decodeResource(
                    context.getResources(), R.mipmap.ic_add_photo));
            if (position == 9) {
                holder.image.setVisibility(View.GONE);
            }
        } else {
            Bitmap bitmap = Bimp.tempSelectBitmap.get(position).getBitmap();
            if (bitmap != null) {
                holder.image.setImageBitmap(bitmap);
            } else {
                holder.image.setImageBitmap(
                        ImageUtils.loadBitmap(Bimp.tempSelectBitmap.get(position).getImagePath(),
                                holder.image.getWidth(),
                                holder.image.getHeight()));
            }
        }

        return convertView;
    }

    public ImageView getImg() {
        return new ViewHolder().image;
    }

    public class ViewHolder {
        public ImageView image;
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void loading() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (Bimp.max == Bimp.tempSelectBitmap.size()) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        break;
                    } else {
                        try {
                            ImageItem imageItem = Bimp.tempSelectBitmap.get(Bimp.max);
                            String path = imageItem.getImagePath();
                            Bitmap bm = Bimp.revitionImageSize(path);
                            imageItem.setBitmap(bm);
                            String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
//                                String newStr = String.valueOf(System.currentTimeMillis());
                            FileUtils.saveBitmap(bm, "" + newStr);
                            Bimp.max += 1;
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            if (Bimp.max > PublicWay.num) {
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
