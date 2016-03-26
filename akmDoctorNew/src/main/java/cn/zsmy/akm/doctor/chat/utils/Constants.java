package cn.zsmy.akm.doctor.chat.utils;

public class Constants  {
	public static final String KEY_MESSAGE_ENTITIES = "key_message_entities";
	public static final String KEY_TITLE = "key_title";
	public static final String KEY_IMAGEENTITIES = "key_imageentities";
	public static final String KEY_SELECTED_POSITION = "key_selected_position";
	public static final String KEY_DEPTBEAN = "key_deptbean";
	public static final String KEY_TOKEN = "key_token";
	public static final String KEY_USER_ID = "key_user_id";
	public static final String KEY_ACCOUNT = "key_account";
	public static final String KEY_DRUG_ID = "key_drug_id";
	public static final String KEY_PATIENT_ENTITIES = "key_patient_entities";
	public static final String KEY_DISEASECOMMIT_ENTITIES = "key_diseasecommit_entities";
	public static final String KEY_DEPT_ID = "key_dept_id";
	public static final String KEY_CURRENT_TAB_INDEX = "key_current_tab_index";
	public static final String KEY_CASE_ID = "key_case_id";
	public static final String KEY_CHECK_ID = "key_check_id";
	public static final String KEY_DOCTOR_ENTITIES = "key_doctor_entities";
	public static final String KEY_USERBEAN_ENTITIES = "key_userbean_entities";
	public static final int LOADMORE = 0;
	public static final int REFRESH = 1;
	public static final Long PAGECOUNT = 20L;
	public static final String KEY_PROFILE = "key_profile";
	public static final String KEY_PASSWORD = "key_password";
	public static final String KEY_LOGIN_OUT = "key_login_out";
	public static final String KEY_IMAGE_URL = "key_image_url";
	public static final String KEY_IMAGE_PATH = "key_image_path";
	public static final String KEY_IMAGE_PICS = "key_image_pics";
	public static final String KEY_IMAGE_INDEX="key_image_index";
	public static final String KEY_DOCTOR_ID = "key_doctor_id";
	public static final String KEY_PERSON_INFO = "key_person_info";
	public static final String KEY_FORUM_ID = "key_forum_id";
	public static final String KEY_IS_UPDATE_PICTURE_LIST = "key_is_update_picture_list";
	public static final String KEY_OPEN_CLASS = "key_open_class";
	public static final String KEY_DEPTBEANS = "key_deptbeans";
	public static final String KEY_FORUM_COMMENT_ENTITIES = "key_forum_comment_entities";
	public static final int VERIFY_CODE_REGISTER = 1;//注册验证吗类型
	public static final int VERIFY_CODE_FIND_PASSWORD = 2;//找回密码验证吗类型
	public static final String KEY_CASE_ENTITIES = "key_case_entities";
	public static final String KEY_START_TIME_BY_INT = "key_start_time_by_int";
	public static final String KEY_END_TIME_BY_INT = "key_end_time_by_int";
	public static final String KEY_CLOSE_MESSAGE = "key_close_message";
	public static final String KEY_OPEN_MESSAGE = "key_open_message";
	public static final String KEY_FLAG_IS_FIRST_OPEN_APP = "key_flag_is_first_open_app";
	public static final String KEY_QUIET_STYLE = "quietStyle";
	public static final String KEY_GO_CHOOSE_PAT = "key_go_choose_pat";
	public static final String KEY_IS_HOLD = "key_is_hold";
	/**
	 * 当前页面不停留 意味着 登陆之后还需要跳转到别的activity
	 */
	public static final String KEY_NOT_HOLD = "key_not_hold";
	/**
	 * 表示停留在当前页面 登陆之后只需要finish()登陆页面即可
	 */
	public static final String KEY_KEEP_HOLD = "key_keep_hold";
	/**
	 * 判断是否提示过.
	 */
	protected static final String KEY_IS_IMPROMT = "key_is_impromt";
	public static final String KEY_DOCTOR_ENTITY = "key_doctor_entity";
	/**
	 * 添加问诊人的名字
	 */
	public static final String KEY_ADD_PAT_NAME = "key_add_pat_name";
	/**
	 * 重新登录之后的广播
	 */
	public static final String KEY_RELOGIN = "key_relogin";
	/**
	 * 医生部门
	 */
	public static final String KEY_IS_DOC_HAS_DEPT = "docHasDept";
	/**
	 * 是从哪跳入名医搜索页面的
	 */
	public static final String KEY_FROM_MINGYITUIJIAN = "key_from_mingyituijian";
	/**
	 * 判断是从找回密码页面跳转到登录的
	 */
	public static final String KEY_IS_FROM_RETRIEVE_PWD = "key_is_from_retrieve_pwd";
	/**
	 * 用来当做从找回密码页面跳转的intent extra的key
	 */
	public static final String KEY_FROM_RETRIEVE_PWD = "key_from_retrieve_pwd";
}
