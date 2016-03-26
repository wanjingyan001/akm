package cn.zsmy.akm.chat.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.graphics.Bitmap;

public final class IOUtilities {

	public static final int IO_BUFFER_SIZE = 4 * 1024;

	/**
	 * Copy the content of the input stream into the output stream, using a
	 * temporary byte array buffer whose size is defined by
	 * {@link #IO_BUFFER_SIZE}.
	 * 
	 * @param in
	 *            The input stream to copy from.
	 * @param out
	 *            The output stream to copy to.
	 * 
	 * @throws IOException
	 *             If any error occurs during the copy.
	 */
	public static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] b = new byte[IO_BUFFER_SIZE];
		int read;
		while ((read = in.read(b)) != -1) {
			out.write(b, 0, read);
		}
	}

	public static String readStreamToMemory(InputStream in) {
		if (in == null) {
			return null;
		}
		ByteArrayOutputStream out = null;
		byte[] result = null;
		try {
			byte[] buffer = new byte[1024];
			int len = -1;
			out = new ByteArrayOutputStream();
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			result = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new String(result);
	}

	public static void copyFile(String from, String dest) throws IOException {
		FileInputStream fis = new FileInputStream(new File(from));
		FileOutputStream fos = new FileOutputStream(new File(dest));
		copy(fis, fos);
	}

	public static String copyAttachmentToPackage(String path, String attachmentType) {
		try {
			String packagePath = ImageLoader.getInstance().getDiscCache().get(path).getAbsolutePath();
			if (Utils.isImage(attachmentType)) {
				Bitmap bitmap = BitmapUtil.loadBitmap(path, 480, 640, true);
				if (bitmap == null) {
					return path;
				} else {
					BitmapUtil.compressBitmap(packagePath, bitmap, 50, 2 * 1024 * 1024l);
				}
			} else {
				// copy to
				if (packagePath.equals(path)) {
					return path;
				}
				copyFile(path, packagePath);
			}
			return packagePath;
		} catch (IOException e) {
			e.printStackTrace();
			Trace.e(e.toString());
			return path;
		}
	}

	/**
	 * 得到amr的时长
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static long getAmrDuration(File file) throws IOException {
		long duration = -1;
		int[] packedSize = { 12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0, 0, 0 };
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			long length = file.length();// 文件的长度
			int pos = 6;// 设置初始位置
			int frameCount = 0;// 初始帧数
			int packedPos = -1;
			// ///////////////////////////////////////////////////
			byte[] datas = new byte[1];// 初始数据值
			while (pos <= length) {
				randomAccessFile.seek(pos);
				if (randomAccessFile.read(datas, 0, 1) != 1) {
					duration = length > 0 ? ((length - 6) / 650) : 0;
					break;
				}
				packedPos = (datas[0] >> 3) & 0x0F;
				pos += packedSize[packedPos] + 1;
				frameCount++;
			}
			// ///////////////////////////////////////////////////
			duration += frameCount * 20;// 帧数*20
		} finally {
			if (randomAccessFile != null) {
				randomAccessFile.close();
			}
		}
		return duration;
	}

}
