package cn.zsmy.akm.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by sanz on 2015/12/14 13:55
 */
public class StringUtils {
    public static String getString(String[] str) {
        StringBuffer buffer = new StringBuffer();
        int lenght = str.length;
        for (int i = 0; i < lenght; i++) {
            buffer.append(str[i]);
        }
        return buffer.toString();
    }

    public static String getPhone(String phone) {
        String maskNumber = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
        return maskNumber;
    }

    public static String getFilePath(Context context, Intent data) {
        if (data != null && data.getData() != null) {
            Uri uri = data.getData();
            String uriHead = StringUtils.getUriHeadString(uri.toString());
            String mImageStr = null;
            if (uriHead.equals("content")) {
                Cursor cursor = context.getContentResolver().query(uri, null, null,
                        null, null);
                cursor.moveToFirst();
                int idx = cursor
                        .getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                mImageStr = cursor.getString(idx);
                cursor.close();
            } else if (uriHead.equals("file")) {
                mImageStr = uri.toString().substring(7);
            }
            return mImageStr;
        }
        return null;
    }

    public static String getUriHeadString(String str) {
        if (str == null) {
            return null;
        }
        String[] s = str.split(":");
        for (String s1 : s) {
            Log.i("info", s1);
        }
        return s[0];
    }

}
