package cn.zsmy.akm.utils;

public class Constants {

    public static final String KEY_TITLE = "key_title";

    public static final String KEY_PROTECT_APP = "key_protect_app";

    public static final String KEY_CURRENT_TAB_INDEX = "key_current_tab_index";
    public static final String KEY_TAB_ENTITIES = "key_tab_entities";
    public static final int KEY_FROM_LOGIN = 1;
    public static final int KEY_FROM_REGISTER = 0;
    public static final String KEY_MESSAGE_ENTITIES = "key_message_entities";
    public static final int LOADMORE = 0;
    public static final int PAGECOUNT = 30;

    /***
     * 填写问诊信息所需要的入参
     * **/
    public static final String KEY_DOCTOR_ID = "key_doctor_id";
    public static final String KEY_TYPE_ID = "KEY_TYPE_ID";
    public static final String KEY_FLAG_ID = "KEY_FLAG_ID";

    /***
     *问诊类型（1图文问诊 2电话问诊3视频问诊
     * **/
    public static final String CHAT_FLAG_OF_CONSTACTS_PHOTO_INFORMATION="1";
    public static final String CHAT_FLAG_OF_CONSTACTS_PHONE_INFORMATION="2";
    public static final String CHAT_FLAG_OF_CONSTACTS_VIDEO_INFORMATION="3";
    /***
     * 病历类型(0:普通病历、1：vip)
     * **/
    public static final String CASE_TYPES_OF_COMMON="0";
    public static final String CHAT_TYPES_OF_VIP="1";


    public static final String LOGIN_INFO = "login";
    public static final int SEND_SMS_SECURITY_CODE = 1;//获取短信验证码
    public static final int SEND_VOICE_SECURITY_CODE = 2;//获取语音验证码
    public static final int GET_PHOTO_FROM_GALLERY = 3;//从相册获取图片
    public static final int GET_PHOT_FROM_CAMERA = 4;//从相机获取图片
    public static final String CLOSE_REPLY_NOTICE = "close_reply_notice";//关闭回复通知
    public static final String OPEN_REPLY_NOTICE = "open_reply_notice";//开启回复通知
    public static final String CLOSE_ADVISORY_NOTICE = "close_advisory_notice";//关闭咨询通知
    public static final String OPEN_ADVISORY_NOTICE = "open_advisory_notice";//开启咨询通知
    public static final String CLOSE_SPREAD_NOTICE = "close_spread_notice";//关闭推广通知
    public static final String OPEN_SPREAD_NOTICE = "open_spread_notice";//开启推广通知
    public static final String RECEIVE_NOTIFICATION="Receive_notification_of_control";

    /**
     * 从哪一个医生列表搜索
     */
    public static final int FROM_RECOMMEND = 0;
    public static final int FROM_FAMOUS = 1;

    /**
     * 添加问诊人的名字
     */
    public static final String KEY_ADD_PAT_NAME = "key_add_pat_name";


    /**
     * 添加问诊人的名字
     */
    public static final String KEY_PATIENT = "PATIENT";//问诊人实体类KEY标志
    public static final String KEY_MARK_TYPE = "MARK_TYPE";
    public static final int VALUS_ADD_PERSON = 0;
    public static final int VALUS_EDIT_PERSON = 1;
    public static final String KEY_ENTITY = "ENTITY";
    /**
     * 帖子详情所需要的常理
     */

    public static final String KEY_POST_ID = "POST_ID";//帖子ID

    /***
     *页面标记
     * **/
    public static final String HISTORY_CASE_VALUSE = "HISTORY_CASE";//帖子ID


}
