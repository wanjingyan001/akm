package cn.zsmy.akm.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.zsmy.akm.chat.receiver.MyPushMessageReceiver;
import cn.zsmy.akm.utils.Constants;

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
            case Constants.CLOSE_ADVISORY_NOTICE:
                //不接受资讯消息
                MyPushMessageReceiver.isReceiveAdvisoryMSG = false;
                break;
            case Constants.OPEN_ADVISORY_NOTICE:
                MyPushMessageReceiver.isReceiveAdvisoryMSG = true;
                break;
            case Constants.CLOSE_SPREAD_NOTICE:
                //不接受推广消息
                MyPushMessageReceiver.isReceiveSpreadMSG = false;
                break;
            case Constants.OPEN_SPREAD_NOTICE:
                MyPushMessageReceiver.isReceiveSpreadMSG = true;
                break;
        }
    }
}
