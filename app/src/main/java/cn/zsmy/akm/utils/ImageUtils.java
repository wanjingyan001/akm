package cn.zsmy.akm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.zsmy.akm.R;
import cn.zsmy.akm.http.Trace;


/**
 * 图片加载工具类(UML版)
 * Created by qinwei on 2015/11/2 10:55
 * email:qinwei_it@163.com
 */
public class ImageUtils {
    public static final int DEFAULT_WIDTH = 480;
    public static final int DEFAULT_HEIGHT = 640;

    /**
     * 保持长宽比缩小Bitmap
     *
     * @param bitmap
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int maxWidth, int maxHeight) {
        int originWidth = bitmap.getWidth();
        int originHeight = bitmap.getHeight();
        // no need to resize
        if (originWidth < maxWidth && originHeight < maxHeight) {
            return bitmap;
        }
        int width = originWidth;
        int height = originHeight;
        if (originWidth > maxWidth) {
            width = maxWidth;
            double i = originWidth * 1.0 / maxWidth;
            height = (int) Math.floor(originHeight / i);
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        }
        if (height > maxHeight) {
            height = maxHeight;
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height);
        }
        return bitmap;
    }

    public static Bitmap loadBitmap(String path, int mWidth, int mHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = null;
        int be = 1;
        try {
            options.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(path),
                    null, options);
            be = (int) Math.max(options.outWidth / mWidth, options.outHeight
                    / mHeight);
            if (be <= 0) {
                be = 1;
            }
            options.inSampleSize = be;
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(path),
                    null, options);
        } catch (OutOfMemoryError e) {
            Trace.d(e.toString());
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
            options.inSampleSize = be * 2;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(path),
                        null, options);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            Trace.e(e.toString());
        }
        return bitmap;
    }

    public static BitmapDrawable loadDrawable(Context context, String path) {
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeFile(path));
    }

    public static Bitmap loadBitmap(String path, int mWidth, int mHeight,
                                    boolean isNeedToRotate) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = null;
        int be = 1;
        int needToRotate = 0;
        try {
            if (isNeedToRotate) {
                ExifInterface exif = new ExifInterface(path);
                int orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, -1);
                if (orientation != -1) {
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            needToRotate = 90;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            needToRotate = 180;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            needToRotate = 270;
                            break;
                    }
                }
            }
            options.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(path),
                    null, options);
            be = (int) Math.max(options.outWidth / mWidth, options.outHeight
                    / mHeight);
            if (be <= 0) {
                be = 1;
            }
            options.inSampleSize = be;
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(path),
                    null, options);
        } catch (OutOfMemoryError e) {
            Trace.d(e.toString());
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
            options.inSampleSize = be * 2;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(path),
                        null, options);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            Trace.e(e.toString());
        }
        if (bitmap != null && isNeedToRotate && needToRotate != 0) {
            Matrix tempMatrix = new Matrix();
            tempMatrix.postRotate(needToRotate);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), tempMatrix, false);
        }
        return bitmap;
    }

    public static void compressBitmap(String path, Bitmap bitmap, int quality, long attachmentMaxSize) {
        BufferedOutputStream bos = null;
        File mFile = new File(path);
        try {
            if (mFile.exists()) {
                mFile.delete();
                mFile.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(path));
            if (bitmap != null && bos != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                if (mFile.exists() && mFile.length() > attachmentMaxSize) {
                    compressBitmap(path, bitmap, quality / 2, attachmentMaxSize);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int[] getBitmapScale(String path) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(path), null,
                    options);
            return new int[]{options.outWidth, options.outHeight};
        } catch (FileNotFoundException e) {
            return new int[]{0, 0};
        }
    }

    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


    public static DisplayImageOptions getUserIconOptions() {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.showImageOnLoading(R.drawable.ic_launcher);
        builder.showImageForEmptyUri(R.drawable.ic_launcher);
        builder.showImageOnFail(R.drawable.ic_launcher);
        builder.cacheInMemory(true);
        builder.cacheOnDisk(true);
        builder.considerExifParams(true);
        return builder.build();
    }

    /**
     * 显示图片
     *
     * @param uri       图片的uri
     * @param imageView 图片控件
     * @param options   图片的加载配置
     */
    public static void displayImage(String uri, ImageView imageView, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(uri, imageView, options);
    }

    public static void displayImage(String uri, ImageView imageView, DisplayImageOptions options, ImageLoadingListener listener) {
        ImageLoader.getInstance().displayImage(uri, imageView, options, listener);
    }
    public static void clearMemoryCache() {
        ImageLoader.getInstance().clearMemoryCache();
    }
    public static Drawable resizePhoto(Context context,String uri) {
        if(uri==null){
          return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(uri, options);
        double ratio = Math.max(options.outWidth * 1.0d /1024f,
                options.outHeight * 1.0d /1024f);
        options.inSampleSize = (int) Math.ceil(ratio);
        Log.i(">>>>>>>>>>>>>>", Math.ceil(ratio) + "");
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        Bitmap Photo = BitmapFactory.decodeFile(uri, options);
        if(Photo==null){
            return null;
        }
        Drawable drawable=new BitmapDrawable(Photo);
        Photo=null;
        return drawable;

    }
    public static void releaseImageResouce(ImageView imageView) {
        if (imageView == null) return;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                imageView.setImageResource(0);
                imageView.setBackgroundResource(0);
                imageView=null;
                bitmap=null;
            }
        }
    }
    public static Bitmap zoomDrawable(Drawable drawable, int w, int h) {
        if(drawable==null){
          return null;
        }
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // drawable转换成bitmap
        Bitmap oldbmp = drawableToBitmap(drawable);
        // 创建操作图片用的Matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放比例
        // 设置缩放比例
        if(height<h){
            float sy =(float) 2;
            if(width<w){
                float sx =(float) 1.5;
                matrix.postScale(sx, sy);
            }
        }

        if(height>h){
            float sy =(float) h/height;
            if(width>w){
                float sx =(float) w/width;
                sx=sx-2*sy;
                matrix.postScale(sx, sy);
            }
        }
        // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return newbmp;
    }
    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }
}
