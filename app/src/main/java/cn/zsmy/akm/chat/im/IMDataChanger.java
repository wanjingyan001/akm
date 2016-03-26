package cn.zsmy.akm.chat.im;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Observable;
import java.util.UUID;

import cn.zsmy.akm.chat.bean.Attachment;
import cn.zsmy.akm.chat.bean.Message;
import cn.zsmy.akm.chat.bean.MessageType;
import cn.zsmy.akm.chat.utils.FileUtil;
import cn.zsmy.akm.db.MessageController;
import cn.zsmy.akm.home.MyApplication;

public class IMDataChanger extends Observable {
    private static IMDataChanger mInstance;
    private Gson gson;
    private String TAG = this.getClass().getSimpleName();

    public IMDataChanger() {
        gson = new Gson();
    }

    public static IMDataChanger getInstance() {
        if (mInstance == null) {
            mInstance = new IMDataChanger();
        }
        return mInstance;
    }

    public void handlerPushMessage(String json) {
        // TODO handler json
        setChanged();
        Message message = gson.fromJson(json, Message.class);
        Log.i("TAG","===========" + json);
        Log.i("TAG", message + "===========" + message.getReceiverId() + "==========" + message.getType());
        if (MyApplication.getProfile() != null) {
            if (message.getReceiverId().equals(MyApplication.getProfile().getUserId()) && message.getType() == MessageType.voice) {
                String path = System.currentTimeMillis() + ".arm";
                ArrayList<Attachment> attachments = new ArrayList<Attachment>();
                Attachment attachment = new Attachment();
                attachment.setFile_path(FileUtil.getVoiceDir() + "/" + path);
                attachment.setFile_name(path);
                attachments.add(attachment);
                message.setAttachments(attachments);
            }
        }
        // TODO save message to db
        if (message.getType() != MessageType.notify) {
            // TODO:这条信息还是要存的。。。以后再说
            MessageController.addOrUpdate(message);
        } else {
            message.set_id(UUID.randomUUID().toString());
        }

        Log.i("TAG", message.toString());
        notifyObservers(message);
    }

    public synchronized void notifyDataChanged(Message oldMessage, Message updatedMessage) {
        setChanged();
        // TODO save updatedMessage to db
        if (updatedMessage != null) {
            MessageController.delete(oldMessage);
            MessageController.addOrUpdate(updatedMessage);
            Log.i(TAG + "updatedMessage", oldMessage.getContent() + "============" + updatedMessage.getContent());
        } else {
            Log.i(TAG + "oldMessage", oldMessage.getSenderId());
            MessageController.addOrUpdate(oldMessage);
        }
        // TODO notify ui update
        Message[] messages = new Message[2];
        messages[0] = oldMessage;
        messages[1] = updatedMessage;
        notifyObservers(messages);
    }

    public synchronized void notifyProgressUpdate(int percent, Message message) {
        // FIXME 上传进度，下载进度 都可此处更新
        setChanged();
        notifyObservers(message);
    }
}
