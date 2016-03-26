package cn.zsmy.akm.doctor.chat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.wei.library.widget.ProgressDialogUtils;
import cn.zsmy.akm.doctor.admissions.DoctorSuggestActivity;
import cn.zsmy.akm.doctor.admissions.DoctorsRecommend;
import cn.zsmy.akm.doctor.admissions.bean.Contact;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.chat.adapter.ChatAdapter;
import cn.zsmy.akm.doctor.chat.bean.Attachment;
import cn.zsmy.akm.doctor.chat.bean.CaseInfo;
import cn.zsmy.akm.doctor.chat.bean.ChatList;
import cn.zsmy.akm.doctor.chat.bean.Message;
import cn.zsmy.akm.doctor.chat.bean.MessageStatus;
import cn.zsmy.akm.doctor.chat.bean.MessageType;
import cn.zsmy.akm.doctor.chat.bean.User;
import cn.zsmy.akm.doctor.chat.call.CallChange;
import cn.zsmy.akm.doctor.chat.call.ChatConnecting;
import cn.zsmy.akm.doctor.chat.im.IMDataWatcher;
import cn.zsmy.akm.doctor.chat.im.IMPushManager;
import cn.zsmy.akm.doctor.chat.receiver.MyPushMessageReceiver;
import cn.zsmy.akm.doctor.chat.utils.FileUtil;
import cn.zsmy.akm.doctor.chat.utils.IDHelper;
import cn.zsmy.akm.doctor.chat.utils.MediaPlayerManager;
import cn.zsmy.akm.doctor.chat.utils.SystemPhoto;
import cn.zsmy.akm.doctor.chat.utils.TextUtil;
import cn.zsmy.akm.doctor.chat.utils.Trace;
import cn.zsmy.akm.doctor.conversation.bean.PatientDetail;
import cn.zsmy.akm.doctor.conversation.choosePhoto.Bimp;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.shared.ChatPictureLookActivity;
import cn.zsmy.akm.doctor.utils.FileUtils;
import cn.zsmy.akm.doctor.utils.ListSort;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by SANZ on 2015/12/30 15:11
 */
public class ChatActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener {
    private LinearLayout chatLinea;
    private CallChange chatChange;
    public PullToRefreshListView refreshLv;
    private ChatAdapter adapter;
    private List<Message> datas;
    public static String caseID;
    private User targetUser;// 对方
    private User sendUser;// 自己
    private String targetId;
    private String selfId, inquireID;
    private String chatType;
    private Contact.DataEntity contactEntity;
    private PatientDetail.DataEntity patientDetailEntity;
    private String TAG = this.getClass().getSimpleName();
    public ChatList chatList;
    private IMDataWatcher watcher = new IMDataWatcher() {
        @Override
        public void updateMessage(Message oldMessage, Message newMessage) {
            Log.i(TAG, oldMessage.getReceiverId() + "" + newMessage + "");
            if (oldMessage.getReceiverId() != null) {
                if (!oldMessage.getReceiverId().equals(inquireID)) {
                    return;
                }
            }
            int index = datas.indexOf(oldMessage);
            Log.i("TAG>>>>>", index + "");
            if (index == -1) {
                Log.i("TAG>>>>>-1", index + "");
                datas.add(oldMessage);
                notifyDataSetChanged(true);
            } else {

                if (newMessage != null) {
                    Log.i("TAG>>>>>-1", index + "");
                    datas.remove(index);
                    datas.add(index, newMessage);
                } else {
                    Log.i("TAG>>>>>-1", index + "");
                    datas.remove(index);
                    datas.add(index, oldMessage);
                }
                Log.i("TAG>>>>>-1", index + "");
                notifyDataSetChanged(true);
            }
        }

        @Override
        public void receiverMessage(Message message) {
            Log.i("TAG>>>>>==========", message.getSenderId() + ">>>>>>>>>>" + inquireID);
//            if (message.getSenderId().equals(patientID)
//                    && message.getCaseId().equals(caseID)) {
            // TODO:判断这个message是系统发的消息
            // refreshUI():
            if (message.getType() == MessageType.notify) {
                message.set_id(UUID.randomUUID().toString());
//                    refreshUI();
            }
            datas.add(message);
            notifyDataSetChanged(true);
//            }
        }
    };
    public static String casestatus;
    private int type;
    private CaseInfo.DataEntity dataEntity;
    private String path;


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
//            adapter.notifyDataSetChanged();
            refreshLv.getRefreshableView()
                    .setSelection(datas.size() + 1);
        }

        ;
    };


    public static Intent getIntent(Context context, Contact.DataEntity contactEntity) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("CONTACT_ENTITY", contactEntity);
        return intent;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            chatChange.setView(msg.what);
        }
    };

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_chat);
        MyApplication.getInstance().addActivity(this);
        IMPushManager.getInstance(getApplicationContext()).addObserver(watcher);
        refreshLv = (PullToRefreshListView) findViewById(R.id.generalPullToRefreshLsv);
    }

    @Override
    protected void initializeData() {
        datas = new ArrayList<Message>();
        adapter = new ChatAdapter(ChatActivity.this, datas);
        refreshLv.setAdapter(adapter);
        refreshLv.setOnItemClickListener(this);
        refreshLv.setOnRefreshListener(this);
        refreshLv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        selfId = MyApplication.getProfile().getUserId();
        getCurrentCase(caseID);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        chatLinea = (LinearLayout) findViewById(R.id.chat_lineaID);
        chatChange = new CallChange(this);
        chatLinea.addView(chatChange);
        chatType = getIntent().getStringExtra("chatType");
        type = getIntent().getIntExtra("TYPE", -1);//为0
        if (getIntent().getSerializableExtra("CONTACT_ENTITY") != null) {
            contactEntity = (Contact.DataEntity) getIntent().getSerializableExtra("CONTACT_ENTITY");
            caseID = contactEntity.getId();
            inquireID = contactEntity.getInquirer().getId();
            casestatus = contactEntity.getStatus();
            if (contactEntity.getInquirer().getNickname() != null) {
                setTitle(contactEntity.getInquirer().getNickname());
            } else if (contactEntity.getInquirer().getPhone() != null) {
                setTitle(contactEntity.getInquirer().getPhone());
            }
        } else if (getIntent().getStringExtra("caseId") != null) {
            caseID = getIntent().getStringExtra("caseId");

        } else {
            setTitle(null);
        }
    }

    /**
     * 根据病例状态修改界面
     */
    private void changeStatus() {
        if (caseID != null) {
            if (casestatus != null) {
                switch (casestatus) {
                    case "0"://正在连线状态
                        chatChange.setType(type);
                        chatChange.setView(0);
                        break;
                    case "1"://正在通话状态
                        chatChange.setView(1);
                        break;
                    case "2"://通话结束状态
                        chatChange.setView(3);
                        break;

                }
            }
            chatChange.setCaseID(caseID);
            chatChange.setTargetId(inquireID);
            chatChange.setCasestatus(casestatus);

        } else {
            chatChange.setView(0);
        }
        for (int i = 0; i < chatLinea.getChildCount(); i++) {
            if (chatLinea.getChildAt(i) instanceof ChatConnecting) {
                ((ChatConnecting) chatLinea.getChildAt(i)).setAdmissionsTimeInfo(type);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "病例id" + caseID);

    }

    /***
     * 模拟聊天时候出现的状态改变
     **/
    public void ChatTypeChange() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i < 2; i++) {
                        Thread.sleep(3000);
                        handler.sendEmptyMessage(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 获取病例详情（患者的提问、头像、图片等信息）
     */
    private String doctor_head = null;
    private String paitent_head = null;

    private void getCurrentCase(final String caseID) {
        Request request = new Request(UrlHelper.CURRENT_CASE + "?caseId=" + caseID, this);
        Log.d("TAG", "病例详情URL" + UrlHelper.CURRENT_CASE + "?caseId=" + caseID);
        ProgressDialogUtils.showProgressDialog(this, "正在加载");
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                datas.clear();
                CaseInfo caseInfo = JsonParser.deserializeFromJson(result, CaseInfo.class);
                dataEntity = caseInfo.getData();
                casestatus = dataEntity.getStatus();
                changeStatus();
                Message message = new Message();
                if (dataEntity.getInquirer() != null) {
                    if (dataEntity.getInquirer().getAvatar() != null) {
                        message.setReceiverIcon(dataEntity.getInquirer().getAvatar());
                        paitent_head = dataEntity.getInquirer().getAvatar();
                    }
                    adapter.setReceiverIcon(dataEntity.getInquirer().getAvatar());
                }
                if (dataEntity.getDoctor() != null) {
                    if (dataEntity.getDoctor().getAvatar() != null) {
                        message.setSenderIcon(dataEntity.getDoctor().getAvatar());
                        doctor_head = dataEntity.getDoctor().getAvatar();
                    }
                    adapter.setSenderIcon(dataEntity.getDoctor().getAvatar());
                }
                loadChatList(UrlHelper.CHAT_LIST_URL + caseID);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    /**
     * 拉取聊天记录
     *
     * @param url
     */
    public void loadChatList(String url) {
        Request request = new Request(url, Request.RequestMethod.GET, this);
        Trace.d(url);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                chatList = JsonParser.deserializeFromJson(result, ChatList.class);
                for (int i = 0; i < chatList.getData().size(); i++) {
                    Message message = new Message();
                    message.setContent(chatList.getData().get(i).getContent());
                    message.setReceiverId(chatList.getData().get(i).getReceiverId());
                    message.setSenderId(chatList.getData().get(i).getSenderId());
                    message.setTimestamp(chatList.getData().get(i).getTimestamp());
                    if (doctor_head != null) {
                        message.setSenderIcon(doctor_head);
                    }
                    if (paitent_head != null) {
                        message.setReceiverIcon(paitent_head);
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
                if (datas.size() > 0) {
                    ProgressDialogUtils.closeProgressDialog();
                }
                ListSort.sortChatRecord(datas);
                adapter.imgNumber = datas.size();
                adapter.notifyDataSetChanged();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
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
//
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
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SystemPhoto.FILE_IMAGE_CODE:
                    Log.i("TAG", requestCode + ">>>>>>>>");
                    handleImg(data.getData());
                    break;
                case SystemPhoto.CAMERA_IMAGE_CODE:
                    handleImg(chatChange.getUri());
                    break;
                case 300:
                    // update UI
                    Toast.makeText(this, "评价成功", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        if (casestatus != null) {
            if (casestatus.equals("2")) {
                getMenuInflater().inflate(R.menu.menu_home_back, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_doctor_suggest, menu);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.to_doctor_suggest) {
            Log.i("TAG", caseID + "");
            if (casestatus != null && casestatus.equals("1")) {
                startActivity(DoctorSuggestActivity.getIntent(this, caseID));
            } else if (casestatus != null && casestatus.equals("0")) {
                Toast.makeText(this, "还未接诊", Toast.LENGTH_SHORT).show();
            } else if (dataEntity.getCaseAdvices().size() != 0) {
                Intent intent = DoctorsRecommend.getIntent(this, caseID);
                startActivity(intent);
            }
        } else if (itemId == R.id.home_back) {
            if (dataEntity.getVeriftyTime() != 0 && dataEntity.getCaseAdvices().size() != 0) {
                Intent intent = DoctorsRecommend.getIntent(this, caseID);
                startActivity(intent);
            } else {
                Toast.makeText(this, "没有就医建议", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyPushMessageReceiver.isChatAtReception = true;
        if (MyApplication.getProfile().getUserId() != null) {
            IMPushManager.getInstance(getApplicationContext()).startPush();
            IMPushManager.getInstance(getApplicationContext()).setTags(
                    MyApplication.getProfile().getUserId());
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        MyPushMessageReceiver.isChatAtReception = false;
//        IMPushManager.getInstance(getApplicationContext()).stopPush();

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
        //loadChatList()里边增加一个第一条的聊天时间
        List<ChatList.DataEntity> data = chatList.getData();
        long mintime = data.get(0).getTimestamp();
        for (int i = 0; i < data.size(); i++) {
            if (mintime > data.get(i).getTimestamp()) {
                mintime = data.get(i).getTimestamp();
            }
        }
        if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
            datas.clear();
            loadChatList(UrlHelper.CHAT_LIST_URL + caseID);
        } else if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            loadChatList(UrlHelper.CHAT_LIST_URL + caseID + "&ltTimeStamp=" + mintime);
        }
        refreshLv.postDelayed(new Runnable() {
            @Override
            public void run() {

                refreshLv.onRefreshComplete();
            }
        }, 2000);
    }
}
