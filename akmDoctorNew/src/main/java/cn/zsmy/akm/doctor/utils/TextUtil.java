package cn.zsmy.akm.doctor.utils;

import java.util.ArrayList;
import java.util.Map;

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
	
	public static boolean isValidate(String... contents) {
		for (int i = 0; i < contents.length; i++) {
			if (contents[i] == null || "".equals(contents[i].trim())) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidate(ArrayList<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public static boolean isValidate(Map<?,?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
}
