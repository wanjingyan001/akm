package cn.zsmy.akm.doctor.chat.call;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;

import cn.zsmy.akm.doctor.chat.ChatActivity;
import cn.zsmy.akm.doctor.chat.bean.Message;
import cn.zsmy.akm.doctor.chat.bean.MessageStatus;
import cn.zsmy.akm.doctor.chat.bean.MessageType;
import cn.zsmy.akm.doctor.chat.im.IMPushManager;
import cn.zsmy.akm.doctor.chat.utils.FileUtil;
import cn.zsmy.akm.doctor.chat.utils.IDHelper;
import cn.zsmy.akm.doctor.chat.utils.SystemPhoto;
import cn.zsmy.akm.doctor.conversation.PublishedActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;


/**
 * @param
 * @author sanz
 * @since 2015.11.
 * Created by sanz on 2015/11/26.
 */
public class CallChange extends FrameLayout implements CallEndView.OnItemClickListener, ChatPlugView.OnChatPlugListener {
    private int type;
    private Context context;
    private Uri tempUri;
    private String targetId, caseID, casestatus;
    /**
     * 正在连线状态
     **/
    private final int CONNECTING_STATUS = 0;
    /**
     * 正在通话状态
     */
    private final int CALLING_STATUS = 1;
    /**
     * 通话结束状态
     */
    private final int CALL_END_STATUS = 2;
    /**
     * 病例状态（不可通信）
     */
    public final int CASE_LIBRARY = 3;
    private ChatPlugView chat;
    private CallEndView chatEnd;
    private ChatConnecting connecting;
    private CaseLibrary caseLibrary;

    public CallChange(Context context) {
        super(context);
        this.context = context;
        setBackgroundColor(getResources().getColor(R.color.widgets_general_row_normal));
        connecting = new ChatConnecting(context);
        chat = new ChatPlugView(context);
        chatEnd = new CallEndView(context);
        caseLibrary = new CaseLibrary(context);
    }


    public void setView(int status) {
        initializeView(status);
    }

    public CallChange(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CallChange(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initializeView(int status) {
        switch (status) {
            case CONNECTING_STATUS:
                initializeConnectingView();
                setHide(status);
                break;
            case CALLING_STATUS:
                initializeCallingView();
//                setHide(status);
                break;
            case CALL_END_STATUS:
                initializeCallEndView();
                setHide(status);
                break;
            case CASE_LIBRARY:
                initializeCaseLib();
//                setHide(status);
                break;
        }
    }

    public void setHide(int i) {
        for (int num = 0; num < getChildCount(); num++) {
            Log.i("TAG============", getChildCount() + ">>>>>>>>" + getChildAt(num).getClass());
            if (num == i) {
                View view = getChildAt(num);
                view.setVisibility(View.VISIBLE);
            } else {
                View view = getChildAt(num);
                view.setVisibility(View.GONE);
            }

        }
    }

    private void initializeConnectingView() {
        connecting.setAdmissionsTimeInfo(type);
        connecting.setTag(CONNECTING_STATUS);
        connecting.setItemClickListener(this);
        if (isAdd(connecting)) {

        } else {
            addView(connecting);
        }
    }

    private void initializeCallingView() {
        chat.setOnChatPlugListener(this);
        chat.setTag(CALLING_STATUS);
        if (isAdd(chat)) {

        } else {
            addView(chat);
        }

    }

    private void initializeCallEndView() {
        chat.setTag(CALL_END_STATUS);
        chatEnd.setItemClickListener(this);
        if (isAdd(chatEnd)) {

        } else {
            addView(chatEnd);
        }
    }

    private void initializeCaseLib() {
        caseLibrary.setTag(CASE_LIBRARY);
        caseLibrary.setItemClickListener(this);
        if (isAdd(caseLibrary)) {

        } else {
            addView(caseLibrary);
        }
    }

    @Override
    public void OnBtnItemClickListener(int position) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
//                   intent.setClass(context, InterrogationCommentActivity.class);
                break;
            case 1:
//                   intent.setClass(context,InterrogationInputActivity.class);
                break;
            case 2:
                ignore();
                break;
            case 3:
                confirmAdmissions();
                break;
            case 4:
                //分享
                Intent caselist = PublishedActivity.getIntent(context, "from_share_cast");
                caselist.putExtra("caseId", caseID);
                context.startActivity(caselist);
                break;
            default:
                break;
        }
//        context.startActivity(intent);
    }

    @Override
    public void onTextSend(String text) {
        Log.i("TAG" + this.toString(), text);
        chat.setText(null);
        Message message = new Message();
        message.set_id(IDHelper.generateNew());
        message.setReceiverId(targetId);
        message.setSenderId(MyApplication.getProfile().getUserId());
        message.setContent(text);
        message.setType(MessageType.txt);
        message.setStatus(MessageStatus.ing);
        message.setTimestamp(System.currentTimeMillis());
        message.setCaseId(caseID);
        IMPushManager.getInstance(context.getApplicationContext()).sendMessage(message);

    }

    /**
     * 确认接诊
     */
    private void confirmAdmissions() {
        Request request = new Request(UrlHelper.ADMISSIONS, Request.RequestMethod.POST, context);
        request.put("Id", caseID);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                initializeCallingView();
                setHide(CALLING_STATUS);
                ChatActivity.casestatus = "1";
                ((ChatActivity) context).onRefresh(((ChatActivity) context).refreshLv);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    /**
     * 忽略接诊
     */
    private void ignore() {
        Request request = new Request(UrlHelper.IGNORE_CASE, Request.RequestMethod.POST, context);
        request.put("Id", caseID);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                ((Activity) context).finish();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onSendMsgVoice(String recorderPath) {

    }

    @Override
    public void onPluginClick(PluginEntity.PluginType type) {
        switch (type) {
            case Camera:
                Intent openCameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(FileUtil.getTmpDir() + "/"
                        + String.valueOf(System.currentTimeMillis()) + ".jpg");
                tempUri = Uri.fromFile(file);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                ((Activity) context).startActivityForResult(openCameraIntent,
                        SystemPhoto.CAMERA_IMAGE_CODE);
                break;
            case Images:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                ((Activity) context).startActivityForResult(i, SystemPhoto.FILE_IMAGE_CODE);
                break;
            case VideoCall:
                Toast.makeText(context, "此功能还没开发", Toast.LENGTH_LONG).show();

                break;
            case WalkieTalkie:
                Toast.makeText(context, "此功能还没开发", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    public Uri getUri() {
        return tempUri;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getCasestatus() {
        return casestatus;
    }

    public void setCasestatus(String casestatus) {
        this.casestatus = casestatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private boolean isAdd(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getClass().getSimpleName().equals(view.getClass().getSimpleName())) {
                return true;
            }
        }
        return false;
    }
}
