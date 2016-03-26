package cn.zsmy.akm.doctor.chat.utils;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Stay
 * @version create time：Mar 24, 2015 10:47:15 AM
 */
public class FileUtil {
	public static String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
	public final static String APP = "zhangshangyi";
	public final static String TMP = "tmp";
	public final static String IMG = "image";
	public final static String VOICE = "voice";

	public static String getAppRoot() {
		File file = new File(ROOT, APP);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}

	public static String getTmpDir() {
		File file = new File(getAppRoot(), TMP);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}

	public static String createTmpFile() {
		return getTmpDir() + File.separator + System.currentTimeMillis() + ".tmp";
	}

	public static String createTmpFile(String name) {
		return getTmpDir() + File.separator + name;
	}

	public static String getDownloadDir() {
		String dir = getAppRoot() + File.separator + "download";
		return checkDir(dir);
	}

	public static String getImgDir() {
		String dir = getAppRoot() + File.separator + "img";
		return checkDir(dir);
	}
	public static String getVoiceDir() {
		String dir = getAppRoot() + File.separator + "voice";
		return checkDir(dir);
	}

	public static String getEmoDir() {
		String dir = getAppRoot() + File.separator + "emo";
		return checkDir(dir);
	}

	private static String checkDir(String dir) {
		File directory = new File(dir);
		if (!directory.exists() || !directory.isDirectory()) {
			directory.mkdirs();
		}
		return dir;
	}

	public static String getDownloadPath(String name) {
		return getDownloadDir() + File.separator + name;
	}

	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	public static String getFilePathByUri(Activity activity, Uri uri) {
		String path = null;
		Cursor cursor = activity.managedQuery(uri, new String[] { MediaStore.Images.Media.DATA }, null, null, null);
		if (cursor.moveToFirst()) {
			path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
		}
		// cursor will be automatically closed above 4.0
		// cursor.close();
		cursor = null;
		return path;
	}

	/***
	 * 将InputStream里面的数据写入到SD卡的文件中
	 * 
	 * @param path
	 *            文件的路径
	 * @param fileNameString
	 *            文件名
	 * @param inputStream
	 *            数据流
	 * @return
	 */
	public static String write2SDFromSting(String filePath, String content) {
		File file = null;
		try {
			file = new File(filePath);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.getBytes());
			fos.close();
			Trace.d("写入成功："+file.toURI().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file.toURI().toString();

	}
	
	public static String  saveBitmap(Bitmap bm, String picName) {
		if(bm==null){
			return null;
		}
		try {
			File f = new File(getImgDir(), picName + ".JPEG"); 
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			Log.e("", "已经保存");
			Log.i("IMpushService","file现在的大小是---->"+Math.ceil(f.length()));
			return f.getAbsolutePath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	
}
