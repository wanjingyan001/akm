package cn.zsmy.akm.doctor.chat.im;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Observable;
import java.util.UUID;

import cn.zsmy.akm.doctor.chat.bean.Attachment;
import cn.zsmy.akm.doctor.chat.bean.Message;
import cn.zsmy.akm.doctor.chat.bean.MessageType;
import cn.zsmy.akm.doctor.chat.utils.FileUtil;
import cn.zsmy.akm.doctor.db.MessageController;
import cn.zsmy.akm.doctor.home.MyApplication;


public class IMDataChanger extends Observable {
    private static IMDataChanger mInstance;
    private Gson gson;

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
        notifyObservers(message);
    }

    public synchronized void notifyDataChanged(Message oldMessage, Message updatedMessage) {
        setChanged();
        // TODO save updatedMessage to db
        if (updatedMessage != null) {
            MessageController.delete(oldMessage);
            MessageController.addOrUpdate(updatedMessage);
        } else {
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
