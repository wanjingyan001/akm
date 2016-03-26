package cn.zsmy.akm.doctor.chat.im;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 病例列表
 * @author Administrator
 *
 */
public class Conversation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TIMESTAMP = "timestamp";
	private String _id;
	
	private int unReadNumber;
	
	private ArrayList<String> images;
	
	
	private String voiceUrl;
	
	private String content;

}
