package cn.zsmy.akm.doctor.messageCenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.zsmy.akm.doctor.chat.receiver.MyPushMessageReceiver;
import cn.zsmy.akm.doctor.utils.Constants;


/**
 * 广播接收者，用于用户消息接收控制
 * Created by Administrator on 2016/2/22.
 */
public class NoticeBroadCastReceive extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String type = intent.getStringExtra("TYPE");
        switch (type) {
            case Constants.CLOSE_REPLY_NOTICE:
                //不接受回复消息
                MyPushMessageReceiver.isReceiveReplyMSG = false;
                break;
            case Constants.OPEN_REPLY_NOTICE:
                MyPushMessageReceiver.isReceiveReplyMSG = true;
                break;
            case Constants.CLOSE_STUDY_NOTICE:
                //不接受系统消息
                MyPushMessageReceiver.isReceiveSystemMSG = false;
                break;
            case Constants.OPEN_STUDY_NOTICE:
                MyPushMessageReceiver.isReceiveSystemMSG = true;
                break;
        }
    }
}
