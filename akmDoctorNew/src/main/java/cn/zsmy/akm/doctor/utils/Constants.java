package cn.zsmy.akm.doctor.utils;

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
    public static final String KEY_DOCTOR_ID = "key_doctor_id";
    public static final String LOGIN_INFO = "doctorLogin";
    public static final String CHAT_CASE_ID = "CASE_ID";
    public static final int SEND_SMS_SECURITY_CODE = 1;//获取短信验证码
    public static final int SEND_VOICE_SECURITY_CODE = 2;//获取语音验证码
    public static final String SHARE_FILE_NAME = "suggest_info";//就医建议的缓存文件名
    public static final String CACHE_SERVICE_PRICE = "service_price";//服务价格


    /*
    *
    * 修改个人信息
    * **/
    public static final int RESULT_CDDE_EDIT_PERSON_NAME = 50;//到编辑名字界面返回码
    public static final int RESULT_CDDE_SELECT_PROVINCE = 51;//选择城市界面返回码
    public  static final int RESULT_CDDE_SELECT_HOSPITAL = 52;//选择医院返回码
    public  static final int RESULT_CDDE_SELECT_JOB_TITLE = 53;//选择职称返回码
    public  static final int RESULT_CDDE_SELECT_OFFICE = 55;//选择职称返回码
    public static final int  RESULT_CDDE_EDIT_OFFICE_PHONE_NAME = 54;//到编辑科室电话界面返回码

    public static final String EDIT_PERSON_NAME_VALUES = "EDIT_NAME";//编辑姓名标记
    public static final String EDIT_OFFICE_PHONE_NAME_VALUES = "EDIT_OFFICE_PHONE";//编辑科室电话标记
    public static final String SELECT_HOSPITAL_VALUES = "SELECT_HOSPITAL";//选择医院标记
    public static final String SELECT_PROVINCE_VALUES = "SELECT_PROVINCE";//选择省份标记
    public static final String SELECT_CITY_VALUES = "SELECT_CITY";//选择城市标记
    public static final String SELECT_JOB_TITLE_VALUES = "SELECT_JOB_TITLE";//选择职称标记
    public static final String SELECT_OFFICE_VALUES = "SELECT_OFFICE_TITLE";//选择科室标记
    public static final String UPLOAD_RECORD_VALUES = "UPLOAD_RECORD";//上传录制标记



    public  static final int RESULT_CDDE_SREACH_HOSPITAL = 10;//选择医院返回码
    public static final int SREACH__TESTITEM = 11;//搜索检验项目
    public static final int SREACH_DRUG = 12;//搜索药品
    public static final int return_Hospital = 13;//返回医院
    public static final int return_testItem = 14;//返回检验项目
    public static final int return_drug = 15;//返回药品


    public static final String SREACH_HOSPITAL_VALUES = "sreach_hospital";//搜索医院
    public static final String SREACH_TESTITEM_VALUES = "sreach_testItem";//搜索检验项目
    public static final String SREACH_DRUG_VALUES = "search_drug";//搜索药品


    /**
     * 添加问诊人的名字
     */
    public static final String KEY_ADD_PAT_NAME = "key_add_pat_name";

    public static final String[] CITIES = {"北京", "上海", "广州", "深圳", "成都", "武汉"};
    public static final String[] SECTIONS = {"皮肤科", "神经科", "内科", "耳鼻喉科", "外科", "妇科"};


    public static final String CLOSE_REPLY_NOTICE = "close_reply_notice";//关闭回复通知
    public static final String OPEN_REPLY_NOTICE = "open_reply_notice";//开启回复通知
    public static final String CLOSE_STUDY_NOTICE = "close_study_notice";//关闭系统通知
    public static final String OPEN_STUDY_NOTICE = "open_study_notice";//开启系统通知
    public static final String RECEIVE_NOTIFICATION="Receive_notification_of_control";
}
