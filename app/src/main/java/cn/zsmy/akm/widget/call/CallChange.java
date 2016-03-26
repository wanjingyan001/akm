package cn.zsmy.akm.widget.call;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;

import cn.zsmy.akm.R;
import cn.zsmy.akm.chat.bean.Message;
import cn.zsmy.akm.chat.bean.MessageStatus;
import cn.zsmy.akm.chat.bean.MessageType;
import cn.zsmy.akm.chat.im.IMPushManager;
import cn.zsmy.akm.chat.utils.IDHelper;
import cn.zsmy.akm.chat.utils.SystemPhoto;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.interrogation.InterrogationChatActivity;
import cn.zsmy.akm.interrogation.InterrogationCommentActivity;
import cn.zsmy.akm.interrogation.InterrogationInputActivity;
import cn.zsmy.akm.interrogation.PhoneInquiryActivity;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.FileUtil;

/**
 * @param
 * @author sanz
 * @since 2015.11.
 * Created by sanz on 2015/11/26.
 */
public class CallChange extends FrameLayout implements CallEndView.OnItemClickListener, ChatPlugView.OnChatPlugListener {
    private Context context;
    private Uri tempUri;
    private String targetId, caseID;
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
    private ChatPlugView chat;
    private CallEndView chatEnd;
    private ChatConnecting connecting;
    private String caseType;//问诊类型

    public CallChange(Context context) {
        super(context);
        this.context = context;
        setBackgroundColor(getResources().getColor(R.color.widgets_general_row_normal));
        connecting = new ChatConnecting(context);
        chat = new ChatPlugView(context);
        chatEnd = new CallEndView(context);
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
                setHide(status);
                break;
            case CALL_END_STATUS:
                initializeCallEndView();
                setHide(status);
                break;
        }
    }

    public void setHide(int i) {
        for (int num = 0; num < getChildCount(); num++) {
            if (num == i) {
                View view = getChildAt(i);
                view.setVisibility(View.VISIBLE);
            } else {
                if (getChildCount() == 1) {
                    View view = getChildAt(num);
                    view.setVisibility(View.VISIBLE);
                } else {
                    View view = getChildAt(num);
                    view.setVisibility(View.GONE);
                }

            }

        }
    }

    private void initializeConnectingView() {
        connecting.setTag(CONNECTING_STATUS);
        if (isAdd(chat)) {

        } else {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i).getClass().getSimpleName().equals(ChatConnecting.class.getSimpleName())) {
                    removeViewAt(i);
                }
            }
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
        chatEnd.setTag(CALL_END_STATUS);
        chatEnd.setItemClickListener(this);
        if (isAdd(chatEnd)) {

        } else {
            addView(chatEnd);
        }

    }

    private boolean isAdd(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getClass().getSimpleName().equals(view.getClass().getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void OnBtnItemClickListener(int position) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.putExtra("caseID", caseID);
                intent.setClass(context, InterrogationCommentActivity.class);
                ((InterrogationChatActivity) context).startActivityForResult(intent, 1);
//                context.startActivity(intent);
                break;
            case 1:
                // TODO: 2016/2/18 点击复诊时判断问诊类型是图文还是电话，复诊时依然是该类问诊
                if (caseType != null) {
                    if (caseType.equals("2")) {
                        Intent toPhone = new Intent(context, PhoneInquiryActivity.class);
                        toPhone.putExtra("doctorId", targetId);
                        context.startActivity(toPhone);
                    } else {
                        context.startActivity(InterrogationInputActivity.getIntent(context, 0, targetId, Constants.CHAT_FLAG_OF_CONSTACTS_PHONE_INFORMATION, Constants.CHAT_TYPES_OF_VIP));
                    }
                } else {
                    context.startActivity(InterrogationInputActivity.getIntent(context, 0, targetId, Constants.CHAT_FLAG_OF_CONSTACTS_PHONE_INFORMATION, Constants.CHAT_TYPES_OF_VIP));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onTextSend(String text) {
        chat.setText(null);
        Message message = new Message();
        message.set_id(IDHelper.generateNew());
        message.setReceiverId(targetId);
        String userId = MyApplication.getProfile().getUserId();
        message.setSenderId(userId);
        message.setContent(text);
        message.setType(MessageType.txt);
        message.setStatus(MessageStatus.ing);
        message.setTimestamp(System.currentTimeMillis());
        message.setCaseId(caseID);
        IMPushManager.getInstance(context.getApplicationContext()).sendMessage(message);

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
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

    public void setCallEndBtnStatus(String evaluateTime) {
        chatEnd.setButtonStatus(evaluateTime);
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
}
