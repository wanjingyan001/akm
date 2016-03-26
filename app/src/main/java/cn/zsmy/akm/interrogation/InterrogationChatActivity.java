package cn.zsmy.akm.interrogation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.wei.library.widget.ProgressDialogUtils;
import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.chat.ChatAdapter;
import cn.zsmy.akm.chat.bean.Attachment;
import cn.zsmy.akm.chat.bean.Message;
import cn.zsmy.akm.chat.bean.MessageStatus;
import cn.zsmy.akm.chat.bean.MessageType;
import cn.zsmy.akm.chat.bean.User;
import cn.zsmy.akm.chat.im.IMDataWatcher;
import cn.zsmy.akm.chat.im.IMPushManager;
import cn.zsmy.akm.chat.receiver.MyPushMessageReceiver;
import cn.zsmy.akm.chat.utils.FileUtil;
import cn.zsmy.akm.chat.utils.IDHelper;
import cn.zsmy.akm.chat.utils.MediaPlayerManager;
import cn.zsmy.akm.chat.utils.SystemPhoto;
import cn.zsmy.akm.chat.utils.TextUtil;
import cn.zsmy.akm.entity.ChatHistory;
import cn.zsmy.akm.entity.ChatList;
import cn.zsmy.akm.home.HomeActivity;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.salon.activity.choosePhoto.Bimp;
import cn.zsmy.akm.salon.activity.choosePhoto.FileUtils;
import cn.zsmy.akm.shared.ChatPictureLookActivity;
import cn.zsmy.akm.utils.ListSort;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.call.CallChange;

/**
 * 问诊交流
 * Created by qinwei on 2015/11/24 13:29
 */
public class InterrogationChatActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener {
    private LinearLayout chatLinea;
    private CallChange chatChange;
    public PullToRefreshListView refreshLv;
    private ChatAdapter adapter;
    private List<Message> datas;
    public static String caseID;
    public static int messageCount;
    private String inquiryName, jobTitle, targetId, selfId, flag;
    private User targetUser;// 对方
    private User sendUser;// 自己
    private ChatHistory chatHistory;
    private String TAG = this.getClass().getSimpleName();
    ;
    private IMDataWatcher watcher = new IMDataWatcher() {
        @Override
        public void updateMessage(Message oldMessage, Message newMessage) {
            if (oldMessage.getReceiverId() != null) {
                if (!oldMessage.getReceiverId().equals(targetId)) {
                    return;
                }
            }
            int index = datas.indexOf(oldMessage);
            if (index == -1) {
                datas.add(oldMessage);
                notifyDataSetChanged(true);
            } else {
                if (newMessage != null) {
                    datas.remove(index);
                    datas.add(newMessage);
                } else {
                    datas.remove(index);
                    datas.add(index, oldMessage);
                }
                notifyDataSetChanged(true);
            }

        }

        @Override
        public void receiverMessage(Message message) {
//            if (message.getReceiverId().equals(selfId)
//                    && message.getCaseId().equals(caseID)) {
            // TODO:判断这个message是系统发的消息
//             refreshUI():
            if (message.getType() == MessageType.notify) {
                message.set_id(UUID.randomUUID().toString());
//                    refreshUI();
                mHandler.sendEmptyMessage(0);

            }
            datas.add(message);
            notifyDataSetChanged(true);
//            }
        }
    };
    private String caseType;
    private String path;
    private Uri uri;

    /**
     * 更新listview
     *
     * @param isScrollBottom 是否滚动到底部
     */
    public void notifyDataSetChanged(boolean isScrollBottom) {
        if (isScrollBottom && datas.size() > 0) {
            mHandler.sendEmptyMessageDelayed(1, 200);
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                chatChange.setView(1);
            } else {
//                adapter.notifyDataSetChanged();
                refreshLv.getRefreshableView()
                        .setSelection(datas.size());
            }
        }

        ;
    };

    public static Intent getIntent(Context context, String caseID, String targetId) {
        Intent intent = new Intent(context, InterrogationChatActivity.class);
        intent.putExtra("CASEID", caseID);
        intent.putExtra("TARGETID", targetId);
        return intent;
    }

    public static Intent getIntent(Context context, String caseID, String targetId, String from_activity) {
        Intent intent = new Intent(context, InterrogationChatActivity.class);
        intent.putExtra("CASEID", caseID);
        intent.putExtra("TARGETID", targetId);
        intent.putExtra("ACTIVITY", from_activity);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_interrogation_chat);
        MyApplication.getInstance().addActivity(this);
        IMPushManager.getInstance(getApplicationContext()).addObserver(watcher);
        caseID = getIntent().getStringExtra("CASEID");
        targetId = getIntent().getStringExtra("TARGETID");
        flag = getIntent().getStringExtra("ACTIVITY");
        caseType = getIntent().getStringExtra("zType");
        refreshLv = (PullToRefreshListView) findViewById(R.id.generalPullToRefreshLsv);
    }

    @Override
    protected void initializeData() {
        refreshLv.setOnItemClickListener(this);
        refreshLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        selfId = MyApplication.getProfile().getUserId();
        datas = new ArrayList<>();
        adapter = new ChatAdapter(this, datas);
        refreshLv.setAdapter(adapter);
        refreshLv.setOnRefreshListener(this);
        loadChatInfo();
    }


    @Override
    protected void initializeView() {
        super.initializeView();
        chatLinea = (LinearLayout) findViewById(R.id.chat_lineaID);
        if (caseID != null) {
            chatChange = new CallChange(this);
            chatLinea.addView(chatChange);
            chatChange.setCaseID(caseID);
        } else {
            chatChange = new CallChange(this);
            chatLinea.addView(chatChange);
            chatChange.setView(0);
        }
        chatChange.setCaseType(caseType);

    }

    public void retrySendMessage(final Message message) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setMessage("是否重新发送!")
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IMPushManager.getInstance(getApplicationContext())
                                .sendMessage(message);
                    }
                }).setPositiveButton("取消", null).create().show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (datas.get(position - 1) != null) {
            if (datas.get(position - 1).getType() != null && datas.get(position - 1).getType().toString().equals("advice")) {
                startActivity(DoctorsRecommend.getIntent(this, caseID));
            } else if (datas.get(position - 1).getType() != null && datas.get(position - 1).getType().toString().equals("img")) {
                if (datas.get(position - 1).getContent() != null) {
                    startActivity(ChatPictureLookActivity.getIntent(this, datas.get(position - 1).getContent(), null));
                } else {
                    startActivity(ChatPictureLookActivity.getIntent(this, null, path));
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG", "onActivityResult");
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SystemPhoto.FILE_IMAGE_CODE:
                    uri = data.getData();
                    handleImg(uri);
                    break;
                case SystemPhoto.CAMERA_IMAGE_CODE:
                    uri = chatChange.getUri();
                    handleImg(uri);
                    break;
                case 300:
                    // update UI
                    chatChange.setCallEndBtnStatus(chatHistory.getData().getEvaluateTime());
                    Toast.makeText(this, "评价成功", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        } else if (resultCode == 2) {
            if (requestCode == 1) {
                finish();
            }
        } else {
            switch (requestCode) {
                case SystemPhoto.FILE_IMAGE_CODE:
                    break;
                case SystemPhoto.CAMERA_IMAGE_CODE:
                    String path = chatChange.getUri().getPath();
                    File file = new File(path);
                    if (file.exists()) {
                        file.delete();// 刪除緩存文件
                    }
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleImg(Uri uri) {
        Message message = new Message();
        message.setCaseId(caseID);
        message.set_id(IDHelper.generateNew());
        message.setTimestamp(System.currentTimeMillis());
        message.setType(MessageType.img);
        if (targetId != null) {
            message.setReceiverId(targetId);
        }
        message.setSenderId(selfId);
        message.setStatus(MessageStatus.ing);
        ArrayList<Attachment> attachments = new ArrayList<Attachment>();
        String type = getContentResolver().getType(uri);
        path = null;
        Attachment attachment = new Attachment();
        if (TextUtil.isValidate(type)) {
            path = FileUtil.getFilePathByUri(this, uri);
            attachment.setFile_type(type);
        } else {
            attachment.setFile_type("image/jpg");
            path = uri.getPath();
        }
        Log.d("TAG", "文件路径" + path);
        adapter.setLocationImagePath(path);
        try {
            Bitmap bitmap = Bimp.revitionImageSize(path);
            String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
            FileUtils.saveBitmap(bitmap, "" + newStr);
            path = FileUtils.SDPATH + newStr + ".JPEG";
            long fileSize = Bimp.getFileSize(new File(path));
            Log.d("TAG", "文件大小：" + Bimp.bytes2kb((int) fileSize));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        path = IOUtilities.copyAttachmentToPackage(path, type);
        attachment.setFile_path(path);
        attachment.setFile_name(path.substring(path.lastIndexOf("/") + 1));
        attachments.add(attachment);
        message.setAttachments(attachments);
        IMPushManager.getInstance(getApplicationContext()).sendMessage(message);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "onStart");
        MyPushMessageReceiver.isChatAtReception = true;
        if (MyApplication.getProfile() != null) {
            if (MyApplication.getProfile().getUserId() != null) {
                IMPushManager.getInstance(getApplicationContext()).startPush();
                IMPushManager.getInstance(getApplicationContext()).setTags(
                        MyApplication.getProfile().getUserId());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume");

    }


    private Handler chathandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            ChatHistory.DataEntity dataEntity = chatHistory.getData();
            if (dataEntity.getDoctor() != null && dataEntity.getDoctor().getId() != null) {
                targetId = dataEntity.getDoctor().getId();
                chatChange.setTargetId(targetId);
            }
            Log.i("'TAG", dataEntity.getStatus());
            if (dataEntity.getStatus().equals("2")) {
                chatChange.setView(2);
                chatChange.setCallEndBtnStatus(dataEntity.getEvaluateTime());
            } else if (dataEntity.getStatus().equals("0")) {
                chatChange.setView(0);
            } else if (dataEntity.getStatus().equals("1")) {
                chatChange.setView(1);
            }
            loadChatList(UrlHelpper.CHAT_LIST_URL + "?caseId=" + caseID);
        }
    };

    private String doctor_head = null;
    private String paitent_head = null;

    public void loadChatInfo() {
        String url = UrlHelpper.CURRENT_ILLNESS + "?caseId=" + caseID;
        Log.i("'TAG", url);
        Request request = new Request(url, Request.RequestMethod.GET, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                datas.clear();
                chatHistory = JsonParser.deserializeFromJson(result, ChatHistory.class);
                ChatHistory.DataEntity dataEntity = chatHistory.getData();
                if (dataEntity.getDoctor() != null) {
                    inquiryName = dataEntity.getDoctor().getName();
                    jobTitle = dataEntity.getDoctor().getProfessionalTitle();
                    String name = inquiryName + jobTitle;
                    setTitle(name);
                }
                Message message = new Message();
                if (dataEntity.getDoctor() != null) {
                    if (dataEntity.getDoctor().getAvatar() != null) {
                        message.setReceiverIcon(dataEntity.getDoctor().getAvatar());
                        doctor_head = dataEntity.getDoctor().getAvatar();
                    }
                }
                if (dataEntity.getInquirer() != null) {
                    if (dataEntity.getInquirer().getAvatar() != null) {
                        message.setSenderIcon(dataEntity.getInquirer().getAvatar());
                        paitent_head = dataEntity.getInquirer().getAvatar();
                    }
                }
                chathandler.sendEmptyMessage(0);
                adapter.setReceiverIcon(dataEntity.getDoctor().getAvatar());
                adapter.setSenderIcon(dataEntity.getInquirer().getAvatar());
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    private ChatList chatList;

    public void loadChatList(String url) {
        Log.i("'TAG", url);
        Request request = new Request(url, Request.RequestMethod.GET, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.optString("code");
                    if (code.equals("SUCC")) {
                        chatList = JsonParser.deserializeFromJson(result, ChatList.class);
                        for (int i = 0; i < chatList.getData().size(); i++) {
                            Message message = new Message();
                            message.setContent(chatList.getData().get(i).getContent());
                            message.setReceiverId(chatList.getData().get(i).getReceiverId());
                            message.setSenderId(chatList.getData().get(i).getSenderId());
                            message.setTimestamp(chatList.getData().get(i).getTimestamp());
                            if (paitent_head != null) {
                                message.setSenderIcon(paitent_head);
                            }
                            if (doctor_head != null) {
                                message.setReceiverIcon(doctor_head);
                            }
                            if (chatList.getData().get(i).getType() == null) {
                                message.setType(MessageType.txt);
                            } else if (chatList.getData().get(i).getType().equals("notify")) {
                                message.setType(MessageType.notify);
                            } else if (chatList.getData().get(i).getType().equals("advice")) {
                                message.setType(MessageType.advice);
                            } else if (chatList.getData().get(i).getType().equals("img")) {
                                message.setType(MessageType.img);
                            } else if (chatList.getData().get(i).getType().equals("txt")) {
                                message.setType(MessageType.txt);
                            }
                            datas.add(message);
                        }
                        Log.i("'TAG", datas.size() + "=======" + datas.get(0).getContent());
                        if (datas.size() > 0) {
                            ProgressDialogUtils.closeProgressDialog();
                        }
                        ListSort.sortChatRecord(datas);
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception exception) {
//					throw new AppException(ExceptionStatu.ParseJsonException, "ParseJsonException:" + exception.getMessage());
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (flag != null) {
                finish();
            } else {
                toCaseList();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void toCaseList() {
        List<Activity> datas = MyApplication.getInstance().getmList();
        for (int i = datas.size() - 1; i >= 0; i--) {
            if (!datas.get(i).getClass().getSimpleName().equals(HomeActivity.class.getSimpleName())) {
                datas.get(i).finish();
                datas.remove(i);
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == android.R.id.home) {
            if (flag != null) {
                finish();
            } else {
                toCaseList();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyPushMessageReceiver.isChatAtReception = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IMPushManager.getInstance(getApplicationContext()).removeObserver(watcher);
        MediaPlayerManager.getInstance().releaseMedia();
    }

    /**
     * onRefresh will be called for both a Pull from start, and Pull from
     * end
     *
     * @param refreshView
     */
    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        List<ChatList.DataEntity> data = chatList.getData();
        //防治列表乱序
        long mintime = data.get(0).getTimestamp();
        for (int i = 0; i < data.size(); i++) {
            if (mintime > data.get(i).getTimestamp()) {
                mintime = data.get(i).getTimestamp();
            }
        }
        loadChatList(UrlHelpper.CHAT_LIST_URL + "?caseId=" + caseID + "&ltTimeStamp=" + mintime);
        refreshLv.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLv.onRefreshComplete();
            }
        }, 2000);
    }
}
