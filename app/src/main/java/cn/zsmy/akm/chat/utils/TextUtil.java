package cn.zsmy.akm.chat.utils;

import java.util.ArrayList;

/**
 * @author Stay
 * @version create time：Sep 15, 2014 12:27:31 PM
 */
public class TextUtil {
	public static boolean isValidate(String content) {
		if (content != null && !"".equals(content.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isValidate(ArrayList<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
}