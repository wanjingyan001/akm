package cn.zsmy.akm.doctor.utils;

/**
 * Created by Administrator on 2015/12/23.
 */
public class UrlHelper {
//    public static final String ROOT = "http://192.168.1.188:8080/mobile/";
//    public static final String IP = "http://192.168.1.188/";
    public static final String ROOT = "http://api.zsmy.cn:8080/mobile2/";
    public static final String IP = "http://file.zsmy.cn/";

    /**
     * 登录注册
     */
    public static final String LOGIN = ROOT + "login.json";//登录
    public static final String REGISTER = ROOT + "register.json?";//注册接口
    public static final String SEND_REGCODE = ROOT + "register_sms.json?username=";//发送验证码接口
    public static final String FIND_MIMA_CODE_URL = ROOT + "reset_pwd_sms.json?username=";//找回密码获取验证码
    public static final String VERIFICATION = ROOT + "reg_check_code.json?username=";//注册验证验证码
    public static final String RESET_PWD_VERIFICATION = ROOT + "resetpwd_check_code.json?username=";//找回密码验证验证码
    public static final String RESET_PWD = ROOT + "reset_pwd_sms.json?username=";//修改密码获取验证码
    public static final String EDIT_PASSWORD = ROOT + "editpassword.json";//重置密码
    /**
     * 个人中心
     */
    public static final String DOCTOR_PERSONAL_CENTER = ROOT + "doctor/doctor_center.json";//医生个人中心
    public static final String DOCTOR_PERSONAL_INFO = ROOT + "doctor/doctor.json";//医生个人信息
    public static final String EDIT_DOCTOR_INFO = ROOT + "doctor/edit_doctor.json";//修改医生个人信息
    public static final String EDIT_HEAD_IMAGE = ROOT + "doctor/doctorpic.json";//修改头像
    public static final String JOB_TITLE_LIST = ROOT + "dict/professional_title.json?parentId=e9a89f80-1c5b-4c31-80ec-a0fdd7a80422";//职称列表
    public static final String USER_INTEGRAL = ROOT + "user/record_change.json";//积分详情
    public static final String ACCOUNT_DETAILS = ROOT + "user/user_financials.json";//账户明细
    public static final String PRICE_LIST = ROOT + "price/doctor_price.json";//医生服务价格
    public static final String LOOK_OVER_SERVICE_PRICE = ROOT + "price/select_doctor_price.json";//医生查看服务价格和时间
    public static final String ADD_PRICE = ROOT + "price/add_price.json";//增加服务价格选项（post）
    public static final String EDIT_PRICE = ROOT + "price/edit_price.json";//修改服务价格（post）
    public static final String DELETE_PRICE = ROOT + "case/clinical_reception.json";//删除价格（post）

    /**
     * 病例库
     */
    public static final String MY_PATIENT = ROOT + "case/doctor_patient.json";//我的病人
    public static final String PATIENT_CASE = ROOT + "case/history_doccase.json?creator=";//病人的病历

    /**
     * 接诊室
     */
    public static final String ORDINARY_ADMISSIONS = ROOT + "case/ordinary_case.json";//普通接诊室
    public static final String VIP_CONTACT_OFFICE = ROOT + "case/vipcase.json";//VIP接诊室
    public static final String ADMISSIONS = ROOT + "case/clinical_reception.json";//确认接诊
    public static final String IGNORE_CASE = ROOT + "case/neglect_reception.json";//忽略接诊
    public static final String CURRENT_CASE = ROOT + "case/detail.json";//病例详情
    public static final String CHAT_LIST_URL = ROOT + "chat/list.json?caseId=";//聊天记录查询
    public static final String CHAT_SEND_PHOTO_URL = ROOT + "chat/sendMediaMsg.json";//发送聊天图片

    /**
     * 问诊
     */
    public static final String SEARCH_HOSPITAL = ROOT + "hospital/hospital_name.json";//搜索医院
    public static final String INSPECTIONITEM = ROOT + "inspectionItem/inspection_items.json";//检验项目搜索(错误)
    public static final String SELECT_HOSPITAL = ROOT + "hospital/hospital_case_dept.json?code=10010";//选择医院（按患者位置、按科室）10010对应皮肤科
    public static final String DRUG_LIST = ROOT + "medicine/medicines.json";//药品列表(药品搜索共用)
    public static final String DRUG_DETAIL = ROOT + "medicine/getDetail.json?id=";//药品详情
    public static final String INSPECT_DETAIL = ROOT + "inspectionItem/inspection_item_detail.json";//检验详情
    public static final String TEXT_ITEMS_LIST = ROOT + "inspectionItem/inspection_items.json";//检验项目列表
    public static final String ADD_CASE_ADVICE = ROOT + "case/add_case_advice.json";//添加就医建议（post）
    public static final String ADD_CASE_CHECK = ROOT + "case/add_case_check.json";//添加检验项目（post）
    public static final String ADD_CASE_MEDICINE = ROOT + "case/add_case_medicine.json";//添加用药建议（post）
    public static final String VIEW_DOCTOR_SUGGEST = ROOT + "case/medical_advice.json";//就医建议查看
    public static final String GET_SECTIONS_LIST = ROOT + "dept/depts.json";//获得科室列表
    public static final String GET_SECTIONS_DRUG_LIST = ROOT + "deptmedicine/dept_medicine.json";//获得科室用药列表
    public static final String GET_DISEASE = ROOT + "symptom/symptoms.json";//获取病症用药


    /**
     * 互动学习
     */
    public static final String ALL_COLLEC_PLATE = ROOT + "forum_plate/listFavorite.json";//获取收藏板块(data为空)
    public static final String ADD_COLLEC_PLATE = ROOT + "forum_plate/addFavorite.json";//添加收藏板块(post)
    public static final String DELETE_COLLECT_PLATE = ROOT + "forum_plate/delFavorite.json";//删除收藏板块
    public static final String GET_POST_LIST = ROOT + "forum_topic/list.json";//获取帖子列表
    public static final String POST_DETAIL = ROOT + "forum_topic/detail.json";//帖子详情
    public static final String COMMINT_POST = ROOT + "forum_topic/add.json";//发新帖
    public static final String ADD_POST_PIC = ROOT + "forum_topic/add_pic.json?";//帖子添加图片
    public static final String ALL_AREA = ROOT + "forum_plate/list.json";//所有版块
    public static final String SUBMIT_REPLY = ROOT + "forum_topic/add_reply.json";//回复帖子
    public static final String POST_DETAIL_URL = ROOT + "forum_topic/detail.json?id=";//帖子详情
    public static final String POST_LIKE = ROOT + "forum_topic/support.json";//点赞
    public static final String POST_REPLY_LIST = ROOT+"forum_topic/toplic_comments.json";//帖子回复列表

    /**
     * 消息中心
     */
    public static final String FEED_BACK = ROOT + "advice/add.json?";//意见反馈
    public static final String READ_HISTORICAL_RECORDS = ROOT + "notice/notices.json";//消息列表
    public static final String DELETE_NOTICE = ROOT + "userNotice/del_notice.json";//删除消息
    public static final String CASE_LIST_URL = ROOT + "notice/case_notices.json";


    public static final String SEND_MSG = ROOT + "chat/sendMsg.json";
    public static final String CREAT_CASE_URL = ROOT + "case/add_case.json";//创建生成病例

    public static final String VERSION_CODE = ROOT + "version/getVersion.json?code=akm";//获取版本号


    /**
     * 个人基本信息编辑
     */
    public static final String GET_PROVINCE_URL = ROOT + "area/getProvinceList.json?";//获取省份列表
    public static final String GET_CITIES_URL = ROOT + "area/getCityList.json?";//获取城市列表
    public static final String GET_HOSPITAL_URL = ROOT + "hospital/hospital_case_dept.json?";//获取医院列表
    public static final String GET_JOB_TITLE_URL = ROOT + "dict/professional_title.json?parentId=e9a89f80-1c5b-4c31-80ec-a0fdd7a80422";//获取职称列表
    public static final String GET_OFFICE_NAME_URL = ROOT + "dept/depts.json?";//获取科室列表
    public static final String EDIT_DOCTOR_INFO_IMAGE = ROOT + "doctorAuth/doctor_auth.json?";//上传医生信息图片
    public static final String SELECT_HOSPITAL_URL = ROOT + "hospital/hospital_name.json?";//搜索医院

}
