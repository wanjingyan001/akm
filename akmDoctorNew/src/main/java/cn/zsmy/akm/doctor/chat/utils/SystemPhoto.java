package cn.zsmy.akm.doctor.chat.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SystemPhoto {
	private Context context;
	public static final int FILE_IMAGE_CODE = 0; // 这里的IMAGE_CODE是自己任意定义的
	public static final int CAMERA_IMAGE_CODE = 1;
	public static final int SYSTEM_CROP = 2;
	public Map<String, SoftReference<Bitmap>> bitMapCache=new HashMap<String, SoftReference<Bitmap>>();
	public SystemPhoto(Context context) {
		this.context = context;
	}

	/**
	 * 打开系统相册
	 * 
	 * @param activity
	 */
	public Intent getOpenPicIntent() {
		Intent getPicAction = new Intent(Intent.ACTION_GET_CONTENT);
		getPicAction.setAction(Intent.ACTION_PICK);
		getPicAction.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		return getPicAction;
	}

	public Intent getPhotoZoomIntent(Uri uri, String path) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/jepg");
		intent.putExtra("crop", "true");
		intent.putExtra("outputX", 200);//输出图片大小 
        intent.putExtra("outputY", 200); 
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("output", Uri.fromFile(new File(path)));
		intent.putExtra("outputFormat", "jepg");// 返回格式
		return intent;
	}

	
	
	
	
	
	@SuppressLint("NewApi") public static String getFilePathFromUri(Context context, Uri uri) {
		 final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;  
		  
		    // DocumentProvider  
		    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {  
		        // ExternalStorageProvider  
		        if (isExternalStorageDocument(uri)) {  
		            final String docId = DocumentsContract.getDocumentId(uri);  
		            final String[] split = docId.split(":");  
		            final String type = split[0];  
		  
		            if ("primary".equalsIgnoreCase(type)) {  
		                return Environment.getExternalStorageDirectory() + "/" + split[1];  
		            }  
		  
		            // TODO handle non-primary volumes  
		        }  
		        // DownloadsProvider  
		        else if (isDownloadsDocument(uri)) {  
		  
		            final String id = DocumentsContract.getDocumentId(uri);  
		            final Uri contentUri = ContentUris.withAppendedId(  
		                    Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));  
		  
		            return getDataColumn(context, contentUri, null, null);  
		        }  
		        // MediaProvider  
		        else if (isMediaDocument(uri)) {  
		            final String docId = DocumentsContract.getDocumentId(uri);  
		            final String[] split = docId.split(":");  
		            final String type = split[0];  
		  
		            Uri contentUri = null;  
		            if ("image".equals(type)) {  
		                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;  
		            } else if ("video".equals(type)) {  
		                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;  
		            } else if ("audio".equals(type)) {  
		                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;  
		            }  
		  
		            final String selection = "_id=?";  
		            final String[] selectionArgs = new String[] {  
		                    split[1]  
		            };  
		  
		            return getDataColumn(context, contentUri, selection, selectionArgs);  
		        }  
		    }  
		    // MediaStore (and general)  
		    else if ("content".equalsIgnoreCase(uri.getScheme())) {  
		        return getDataColumn(context, uri, null, null);  
		    }
		    // File  
		    else if ("file".equalsIgnoreCase(uri.getScheme())) {  
		        return uri.getPath();  
		    }  
		return null;
//		
//		String[] proj = { MediaStore.Images.Media.DATA };
//		// 好像是android多媒体数据库的封装接口，具体的看Android文档
//		@SuppressWarnings("deprecation")
//		Cursor cursor = activity.managedQuery(uri, proj, null, null, null);
//		// 按我个人理解 这个是获得用户选择的图片的索引值
//		if(cursor==null){
//			return null;
//		}
//		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//		// 将光标移至开头 ，这个很重要，不小心很容易引起越界
//		cursor.moveToFirst();
//		// 最后根据索引值获取图片路径
//		return cursor.getString(column_index);
	}
	
	/** 
	 * Get the value of the data column for this Uri. This is useful for 
	 * MediaStore Uris, and other file-based ContentProviders. 
	 * 
	 * @param context The context. 
	 * @param uri The Uri to query. 
	 * @param selection (Optional) Filter used in the query. 
	 * @param selectionArgs (Optional) Selection arguments used in the query. 
	 * @return The value of the _data column, which is typically a file path. 
	 */  
	public static String getDataColumn(Context context, Uri uri, String selection,  
	        String[] selectionArgs) {  
	  
	    Cursor cursor = null;  
	    final String column = "_data";  
	    final String[] projection = {  
	            column  
	    };  
	  
	    try {  
	        cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,  
	                null);  
	        if (cursor != null && cursor.moveToFirst()) {  
	            final int column_index = cursor.getColumnIndexOrThrow(column);  
	            return cursor.getString(column_index);  
	        }  
	    } finally {  
	        if (cursor != null)  
	            cursor.close();  
	    }  
	    return null;  
	}  
	  
	
	/** 
	 * @param uri The Uri to check. 
	 * @return Whether the Uri authority is ExternalStorageProvider. 
	 */  
	public static  boolean isExternalStorageDocument(Uri uri) {  
	    return "com.android.externalstorage.documents".equals(uri.getAuthority());  
	}  
	  
	/** 
	 * @param uri The Uri to check. 
	 * @return Whether the Uri authority is DownloadsProvider. 
	 */  
	public static boolean isDownloadsDocument(Uri uri) {  
	    return "com.android.providers.downloads.documents".equals(uri.getAuthority());  
	}  
	  
	/** 
	 * @param uri The Uri to check. 
	 * @return Whether the Uri authority is MediaProvider. 
	 */  
	public static boolean isMediaDocument(Uri uri) {  
	    return "com.android.providers.media.documents".equals(uri.getAuthority());  
	}  
	 /** 
     * 根据指定的图像路径和大小来获取缩略图 
     * 此方法有两点好处： 
     *     1. 使用较小的内存空间，第一次获取的bitmap实际上为null，只是为了读取宽度和高度， 
     *        第二次读取的bitmap是根据比例压缩过的图像，第三次读取的bitmap是所要的缩略图。 
     *     2. 缩略图对于原图像来讲没有拉伸，这里使用了2.2版本的新工具ThumbnailUtils，使 
     *        用这个工具生成的图像不会被拉伸。 
     * @param imagePath 图像的路径 
     * @param width 指定输出图像的宽度 
     * @param height 指定输出图像的高度 
     * @return 生成的缩略图 
     */  
    public Bitmap getImageThumbnail(String imagePath, int width, int height) {  
    	if(bitMapCache.containsKey(imagePath)&&bitMapCache.get(imagePath)!=null){
    		return bitMapCache.get(imagePath).get();
    	}
        Bitmap bitmap = null;  
        BitmapFactory.Options options = new BitmapFactory.Options();  
        options.inJustDecodeBounds = true;  
        // 获取这个图片的宽和高，注意此处的bitmap为null  
        bitmap = BitmapFactory.decodeFile(imagePath, options);  
        options.inJustDecodeBounds = false; // 设为 false  
        // 计算缩放比  
        int h = options.outHeight;  
        int w = options.outWidth;  
        int beWidth = w / width;  
        int beHeight = h / height;  
        int be = 1;  
        if (beWidth < beHeight) {  
            be = beWidth;  
        } else {  
            be = beHeight;  
        }  
        if (be <= 0) {  
            be = 1;  
        }  
        options.inSampleSize = be;  
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false  
        bitmap = BitmapFactory.decodeFile(imagePath, options);  
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象  
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,  
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);  
        bitMapCache.put(imagePath, new SoftReference<Bitmap>(bitmap));
        return bitmap;  
    }  
}
