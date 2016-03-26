package cn.zsmy.akm.utils;

/**
 * Created by qinwei on 2015/10/30 23:46
 * email:qinwei_it@163.com
 */
public class UrlHelpper {
//    public static final String FILE_IP = "http://192.168.1.188/";
//    public static final String ROOT = "http://192.168.1.188:8080/mobile/";

    public static final String ROOT = "http://api.zsmy.cn:8080/mobile2/";
    public static final String FILE_IP = "http://file.zsmy.cn/";

    public static final String REGISTER = ROOT + "register.json";//注册接口
    public static final String SEND_REGCODE = ROOT + "register_sms.json";//发送验证码接口
    public static final String LOGIN = ROOT + "login.json";//登录

    public static final String DOMAIN = "http://api.playsm.com/index.php";
    public static final String FIND_MIMA_CODE_URL = ROOT + "reset_pwd_sms.json";//找回密码获取验证码
    public static final String EDIT_PASSWORD = ROOT + "editpassword.json";//重置密码
    public static final String READ_HISTORICAL_RECORDS = ROOT + "notice/notices.json";//默认消息列表
    public static final String USER_NOTICE = ROOT + "userNotice/user_notice.json";//用户消息
    public static final String DELETE_NOTICE = ROOT + "userNotice/del_notice.json";//删除消息
    public static final String MODIFY_NOTICE = ROOT + "userNotice/read_notice.json";//修改消息状态
    public static final String ALL_READED = ROOT + "userNotice/batch_edit_usernotice.json";//全部已读
    public static final String ALL_DELETE = ROOT + "userNotice/del_notice";//全部删除


    public static final String VERIFICATION = ROOT + "reg_check_code.json";//注册验证验证码
    public static final String RESET_PWD_VERIFICATION = ROOT + "resetpwd_check_code.json";//找回密码验证验证码
    public static final String GET_SECTION_LIST = ROOT + "dept/depts.json";//获取科室列表
    public static final String GET_PROVINCE = ROOT + "area/getProvinceList.json";//获取省份列表
    public static final String GET_CITIES = ROOT + "area/getCityList.json";//获取城市列表
    public static final String GET_EVENT_LIST = ROOT + "user/user_activity.json?activityId=bf3d920b-08b1-462e-8cd6-52cabf0f7351";//获取活动列表
    public static final String DEFULT_EVENT_LIST = ROOT + "activity/list.json?";//默认活动列表
    public static final String RECOMMEND_ACTIVITY = ROOT + "activity/list_recomme.json";//置顶活动
    public static final String SEARCH_DOCTORY = ROOT + "doctor/doctors.json";//医生搜索
    public static final String FAMOUS_DOCTOR = ROOT + "famously/famouslys.json";//名医列表
    public static final String DOCTOR_SEARCH_WITH_NAME = ROOT + "doctor/doctorname.json";//根据名字搜索医生
    public static final String DOCTOR_DETAILS = ROOT + "doctor/doctor_detail.json";//医生详情
    public static final String DOCTOR_EVALUATION = ROOT + "doctor/getDoctorEvaluates.json";//获取医生评价列表
    public static final String DRUG_DETAIL = ROOT + "medicine/getDetail.json";//药品详情
    //    http://192.168.1.116:8080/mobile/case/case_patient.json?patientId=bf3d920b-08b1-462e-8cd6-52cabf0f7351
//    public static final String GET_EVENT_LIST = ROOT + "user/user_activity.json?activityId=1111";//获取活动列表


    public static final String INSPECT_DETAIL = ROOT + "inspectionItem/inspection_item_detail.json";//检验详情
    public static final String ADD_CASE = ROOT + "case/addCase.json";//创建病例（post 参数：doctorId,patientID,zType,content,casePic,offsetX,offsetY,offset,memo,evaluateScore,evaluate,evaluateTime,status）
    public static final String CHOOSE_HOSPITAL = ROOT + "hospital/hospital_case_dept.json?caseId=8d8f03fb-2e04-4fd3-9dc7-73647cb22639&deptId=bca3ac1c-fd57-4eab-8b7f-7a24c9eaf06f";//选择医院（按患者位置、科室）
    public static final String SEARCH_TEXT_ITEMS = ROOT + "inspectionItem/inspectionItemNames.json";//检验项目搜索
    public static final String SEARCH_HOSPITAL = ROOT + "hospital/select_hospital.json";//医院搜索
    //    public static final String CURRENT_ILLNESS = ROOT + "case/current_case.json";//问诊人当前病例（参数需要问疹人ID:id   病历状态：status）
    public static final String CURRENT_ILLNESS = ROOT + "case/detail.json";
    public static final String CASE_HISTORY = ROOT + "case/history_case.json";//问诊人历史病例
    public static final String VIEW_DOCTOR_SUGGEST = ROOT + "case/detail.json";//就医建议查看
    public static final String CHAT_LIST_URL = ROOT + "chat/list.json";//聊天历史记录
    public static final String CHAT_SEND_PHOTO_URL = ROOT + "chat/sendMediaMsg.json";//发送聊天图片

    //    public static final String SEARCH_DOCTORY = ROOT + "doctor/doctors.json?areaCode=123";//医生搜索
//    http://192.168.1.116:8080/mobile/case/case_patient.json?patientId=bf3d920b-08b1-462e-8cd6-52cabf0f7351
    public static final String CASE_HISTORY_URL = ROOT + "case/case_patient.json?patientId=";//病历
    public static final String CASE_LIST_URL = ROOT + "notice/case_notices.json?";//病历

    public static final String SUBMIT_EVALUATE = ROOT + "case/add_evaluate.json";  //用户评价

    //    http://192.168.1.101:8080/mobile/forumTopic/getDetail.json?id=373bc0ae-bd35-488f-b095-bec195e6b5df
    public static final String POST_DETAIL_URL = ROOT + "forum_topic/detail.json";//帖子详情
    public static final String POST_REPLY_LIST = ROOT+"forum_topic/toplic_comments.json";//帖子回复列表
    public static final String CIRCLE_URL = ROOT + "forum_plate/list.json";//圈子
    public static final String POST_LIST = ROOT + "forum_topic/list.json?";//帖子列表
    public static final String COMMINT_POST = ROOT + "forum_topic/add.json";//发新帖
    public static final String ADD_POST_PIC = ROOT + "forum_topic/add_pic.json?";//帖子添加图片


    public static final String INQUIRY_PERSON_URL = ROOT + "patient/patient_inquirerId.json?inquirerID=";//问诊人列表
    public static final String ADD_PERSON_URL = ROOT + "patient/add_patient.json";//添加问诊人
    public static final String EDIT_PERSON_URL = ROOT + "patient/edit_patient.json";//编辑问诊人
    public static final String DEL_PERSON_URL = ROOT + "patient/del_patient.json";//删除问诊人
    public static final String CREAT_CASE_URL = ROOT + "case/add_case.json?";//创建生成病例
    public static final String UPLOAD_IMAGE = ROOT + "case/add_pic.json?";//上传图片
    public static final String CEART_CHAT_URL = ROOT + "case/eidt_case.json";//提交病例
    public static final String SUBMIT_REPLY = ROOT + "forum_topic/add_reply.json";//回复帖子
    public static final String POST_LIKE = ROOT + "forum_topic/support.json";//点赞


    public static final String PERSON_PROFILE = ROOT + "patient/inquirer_center.json";//个人中心
    public static final String USER_INFO = ROOT + "patient/inquirer.json";//用户个人信息
    public static final String SUBMIT_USER_INFO = ROOT + "patient/edit_inquirer.json";//提交用户信息修改
    public static final String SUBMIT_USER_HEAD = ROOT + "patient/edit_avatar.json";//提交用户头像
    public static final String MY_INTEGRAL_URL = ROOT + "user/record_change.json";//我的积分
    public static final String MY_DOCTORS = ROOT + "patient/mydoctors.json?id=";//我的医生（no data）
    public static final String INTERROGATION_LIST = ROOT + "patient/patient_inquirer.json";//健康档案
    public static final String PATIENT_CASE_LIST = ROOT + "case/record_detail.json";//患者的病历（健康档案下）
    //    public static final String PATIENT_CASE_LIST = ROOT + "case/detail.json";//患者的病历（健康档案下）
    public static final String ACCOUNT_DETAILS = ROOT + "user/user_financials.json";//账户明细
    public static final String FEED_BACK = ROOT + "advice/add.json?";//意见反馈
    public static final String RECHARGE = ROOT + "activity/deposits.json";//充值活动
    public static final String USER_RECHARGE = ROOT + "api/payInfo.json?id=";//用户充值
//    public static final String USER_RECHARGE = "http://www.jskyme.cn:888/91wow/api/payInfo.do";//用户充值
    public static final String VERSION_CODE = ROOT + "version/getVersion.json?code=akm";//获取版本号
    public static final String TEXT_ITEMS_LIST = ROOT + "inspectionItem/inspection_items.json";//检验项目列表

    /**
     * ·
     * 查看资讯列表
     */
    public static final String ACTION_FIND_INFORMATION_LIST = "?r=message/list";

    public static final String ACTION_GET_INFORMATION_DETAIL = "?r=message/detail";
    public static final String SEND_MEDIAS = "";
    public static final String SEND_MSG = ROOT + "chat/sendMsg.json";


    public static String getDomain() {
        return DOMAIN;
    }

    public static String loadInformationList() {
        return getDomain() + ACTION_FIND_INFORMATION_LIST + "&size=" + 10;
    }

    public static String loadInformationDetail(String informationId) {
        return getDomain() + ACTION_GET_INFORMATION_DETAIL + "&id=" + informationId;
    }

    public static String showImage(String uri) {
        return getDomain() + uri;
    }


    public static final String DRUG_LIST = ROOT + "medicine/list.json?size=10";//药品列表(药品搜索共用)

}
