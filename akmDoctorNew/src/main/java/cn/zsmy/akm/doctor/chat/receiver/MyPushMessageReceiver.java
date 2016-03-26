package cn.zsmy.akm.doctor.chat.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

import cn.zsmy.akm.doctor.chat.ChatActivity;
import cn.zsmy.akm.doctor.chat.bean.Message;
import cn.zsmy.akm.doctor.chat.bean.MessageType;
import cn.zsmy.akm.doctor.chat.im.IMDataChanger;
import cn.zsmy.akm.doctor.chat.im.IMPushManager;
import cn.zsmy.akm.doctor.chat.utils.Constants;
import cn.zsmy.akm.doctor.chat.utils.PrefsAccessor;
import cn.zsmy.akm.doctor.chat.utils.Trace;
import cn.zsmy.akm.doctor.db.PushMessage.PushMessage;
import cn.zsmy.akm.doctor.db.PushMessage.PushMessageController;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.messageCenter.NoticeCenter;
import cn.zsmy.doctor.R;


/*
 * Push消息处理receiver。请编写您需要的回调函数， 一般来说： onBind是必须的，用来处理startWork返回值；
 *onMessage用来接收透传消息； onSetTags、onDelTags、onListTags是tag相关操作的回调；
 *onNotificationClicked在通知被点击时回调； onUnbind是stopWork接口的返回值回调

 * 返回值中的errorCode，解释如下：
 *0 - Success
 *10001 - Network Problem
 *10101  Integrate Check Error
 *30600 - Internal Server Error
 *30601 - Method Not Allowed
 *30602 - Request Params Not Valid
 *30603 - Authentication Failed
 *30604 - Quota Use Up Payment Required
 *30605 -Data Required Not Found
 *30606 - Request Time Expires Timeout
 *30607 - Channel Token Timeout
 *30608 - Bind Relation Not Found
 *30609 - Bind Number Too Many

 * 当您遇到以上返回错误时，如果解释不了您的问题，请用同一请求的返回值requestId和errorCode联系我们追查问题。
 *
 */

public class MyPushMessageReceiver extends PushMessageReceiver {
    public static boolean isReceiveReplyMSG = true;//是否接收回复消息
    public static boolean isReceiveSystemMSG = true;//是否接收系统消息
    public static boolean isChatAtReception = false;//聊天界面是否在前台

    /**
     * TAG to Log
     */
    public static final String TAG = MyPushMessageReceiver.class.getSimpleName();
    private int times = 0;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Context context = (Context) msg.obj;
                    IMPushManager.getInstance(context).startPush();
                    break;

                default:
                    break;
            }

        }

        ;
    };

    /**
     * 调用PushManager.startWork后，sdk将对push
     * server发起绑定请求，这个过程是异步的。绑定请求的结果通过onBind返回。 如果您需要用单播推送，需要把这里获取的channel
     * id和user id上传到应用server中，再调用server接口用channel id和user id给单个手机或者用户推送。
     *
     * @param context   BroadcastReceiver的执行Context
     * @param errorCode 绑定接口返回值，0 - 成功
     * @param appid     应用id。errorCode非0时为null
     * @param userId    应用user id。errorCode非0时为null
     * @param channelId 应用channel id。errorCode非0时为null
     * @param requestId 向服务端发起的请求id。在追查问题时有用；
     * @return none
     */
    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        String responseString = "onBind errorCode=" + errorCode + " appid=" + appid + " userId=" + userId + " channelId=" + channelId
                + " requestId=" + requestId;
        Trace.d(responseString);
        if (errorCode == 0) {
            // 绑定成功
            if (MyApplication.getProfile().getUserId() != null) {
                IMPushManager.getInstance(context).setTags(MyApplication.getProfile().getUserId());
            }
        } else {
            times++;
//			if (MyApplication.isNetConnected && times < 50) {
//				handler.sendMessageDelayed(handler.obtainMessage(0, context), 5000);
//			}
            if (times < 50) {
//                handler.sendMessageDelayed(handler.obtainMessage(0, context), 2000);
                handler.sendMessageDelayed(handler.obtainMessage(0, context), times * 1000);
            }
        }
        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
        updateContent(context, responseString);
    }

    /**
     * 接收透传消息的函数。
     *
     * @param context             上下文
     * @param message             推送的消息
     * @param customContentString 自定义内容,为空或者json字符串
     */
    @Override
    public void onMessage(Context context, String message, String customContentString) {
        // TODO: 接收消息后判断是聊天消息还是推送消息，分别存入数据库
        String messageString = "透传消息 message=\"" + message + "\" customContentString=" + customContentString;
        Trace.d(messageString);
        try {
            JSONObject object = new JSONObject(message);
            String type = object.getString("type");
            if (type.equals("txt") || type.equals("img") || type.equals("voice") || type.equals("video") || type.equals("advice")) {
                Gson gson = new Gson();
                Message msg = gson.fromJson(message, Message.class);
                MessageType msgType = msg.getType();
                if (isReceiveReplyMSG && !isChatAtReception) {
                    // TODO: 2016/3/2 控制是否接收回复消息
                    ifShowMessage(msg, context);
//            IMDataChanger.getInstance().handlerPushMessage(message);
                }
                if (msg != null && ChatActivity.caseID != null && msg.getCaseId().equals(ChatActivity.caseID)) {
                    Log.d(TAG, msg.getType() + "");
                    if (msg.getType() == MessageType.info) {
                        ifShowMessage(msg, context);
                    } else {
                        IMDataChanger.getInstance().handlerPushMessage(message);
                    }
                    // 自定义内容获取方式，mykey和myvalue对应透传消息推送时自定义内容中设置的键和值
                    if (!TextUtils.isEmpty(customContentString)) {
                        JSONObject customJson = null;
                        try {
                            customJson = new JSONObject(customContentString);
                            String myvalue = null;
                            if (!customJson.isNull("mykey")) {
                                myvalue = customJson.getString("mykey");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                PushMessage pushMessage = JsonParser.deserializeFromJson(message, PushMessage.class);
                MessageType messageType = pushMessage.getType();
                if (isReceiveSystemMSG && (messageType == MessageType.news || messageType == MessageType.activity)) {
                    // TODO: 2016/3/2 控制是否接收系统消息
                    notice(pushMessage, context);
                    PushMessageController.addOrUpdate(pushMessage);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        if (MyApplication.getProfile() != null && MyApplication.getProfile().getUserId() != null) {
//            if (msg.getReceiverId().equals(MyApplication.getProfile().getUserId())) {
//                Log.d(TAG, msg.getType() + "");
//                if (msg.getType() == MessageType.info) {
//                    ifShowMessage(msg, context);
//                } else {
//                    ifShowMessage(msg, context);
//                    IMDataChanger.getInstance().handlerPushMessage(message);
//                }
//            }
//        }

        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
//        updateContent(context, message);
    }

    private void ifShowMessage(Message message, final Context mContext) {
        if (MyApplication.getProfile() != null && MyApplication.getProfile().getUserId() != null) {
            if (message.getReceiverId().equals(MyApplication.getProfile().getUserId())) {
                boolean isOpenMessage = PrefsAccessor.getInstance(mContext).getBoolean(Constants.KEY_OPEN_MESSAGE, false);
                if (isOpenMessage) {
                    boolean quietStyle = PrefsAccessor.getInstance(mContext).getBoolean(Constants.KEY_QUIET_STYLE, false);
                    if (quietStyle) {
                        String startTime = PrefsAccessor.getInstance(mContext).getString(Constants.KEY_START_TIME_BY_INT);
                        String endTime = PrefsAccessor.getInstance(mContext).getString(Constants.KEY_END_TIME_BY_INT);
                        if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
                            String[] split = startTime.split("-");
                            int startH = Integer.valueOf(split[0]);
                            int startM = Integer.valueOf(split[1]);
                            split = endTime.split("-");
                            int endH = Integer.valueOf(split[0]);
                            int endM = Integer.valueOf(split[1]);
                            Calendar calendar = Calendar.getInstance();
                            int currtenH = calendar.get(Calendar.HOUR_OF_DAY);
                            int currtenM = calendar.get(Calendar.MINUTE);

                            int startSS = (startH * 60 + startM) * 60;
                            int endSS = (endH * 60 + endM) * 60;
                            int currentSS = (currtenH * 60 + currtenM) * 60;

                            if (startSS - endSS > 0) {
                                if (currentSS > endSS && currentSS < startSS) {
                                    // TODO 通知消息
                                    notice(message, mContext);
                                }
                            } else if (startSS - endSS < 0) {
                                if ((currentSS > 0 && currentSS < startSS) || (currentSS < 86400 && currentSS > endSS)) {
                                    // TODO 通知消息
                                    notice(message, mContext);
                                }
                            }
                        } else {
                            notice(message, mContext);
                        }
                    } else {
                        notice(message, mContext);
                    }
                } else {
                    notice(message, mContext);
                }
            }
        }
    }

    private void notice(Object message, Context mContext) {
        // TODO Auto-generated method stub
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(ns);
        CharSequence tickerText = mContext.getString(R.string.app_name);
        long when = System.currentTimeMillis();
        Intent notificationIntent = new Intent(mContext, NoticeCenter.class);
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
        Builder builder = new Builder(mContext);
        builder.setAutoCancel(true);
        String content = null;
        if (message instanceof Message) {
            content = ((Message) message).getContent();
        } else if (message instanceof PushMessage) {
            content = ((PushMessage) message).getContent();
        }
        if (!content.startsWith("upload/")) {
            builder.setContentText(content);
            builder.setTicker(content);
        } else {
            builder.setContentText("[图片]");
            builder.setTicker("[图片]");
        }
        builder.setContentTitle(tickerText);
        builder.setWhen(when);
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        mNotificationManager.notify(0, builder.build());
    }

    /**
     * 接收通知点击的函数。
     *
     * @param context             上下文
     * @param title               推送的通知的标题
     * @param description         推送的通知的描述
     * @param customContentString 自定义内容，为空或者json字符串
     */
    @Override
    public void onNotificationClicked(Context context, String title, String description, String customContentString) {
        String notifyString = "通知点击 title=\"" + title + "\" description=\"" + description + "\" customContent=" + customContentString;
        Trace.d(notifyString);

        // 自定义内容获取方式，mykey和myvalue对应通知推送时自定义内容中设置的键和值
        if (!TextUtils.isEmpty(customContentString)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(customContentString);
                String myvalue = null;
                if (!customJson.isNull("mykey")) {
                    myvalue = customJson.getString("mykey");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
        updateContent(context, notifyString);
    }

    /**
     * 接收通知到达的函数。
     *
     * @param context             上下文
     * @param title               推送的通知的标题
     * @param description         推送的通知的描述
     * @param customContentString 自定义内容，为空或者json字符串
     */

    @Override
    public void onNotificationArrived(Context context, String title, String description, String customContentString) {

        String notifyString = "onNotificationArrived  title=\"" + title + "\" description=\"" + description + "\" customContent="
                + customContentString;
        Log.d(TAG, notifyString);

        // 自定义内容获取方式，mykey和myvalue对应通知推送时自定义内容中设置的键和值
        if (!TextUtils.isEmpty(customContentString)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(customContentString);
                String myvalue = null;
                if (!customJson.isNull("mykey")) {
                    myvalue = customJson.getString("mykey");
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
        // 你可以參考 onNotificationClicked中的提示从自定义内容获取具体值
        updateContent(context, notifyString);
    }

    /**
     * setTags() 的回调函数。
     *
     * @param context    上下文
     * @param errorCode  错误码。0表示某些tag已经设置成功；非0表示所有tag的设置均失败。
     * @param sucessTags 设置成功的tag
     * @param failTags   设置失败的tag
     * @param requestId  分配给对云推送的请求的id
     */
    @Override
    public void onSetTags(Context context, int errorCode, List<String> sucessTags, List<String> failTags, String requestId) {
        String responseString = "onSetTags errorCode=" + errorCode + " sucessTags=" + sucessTags + " failTags=" + failTags + " requestId="
                + requestId;
        Trace.d(responseString);

        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
        updateContent(context, responseString);
    }

    /**
     * delTags() 的回调函数。
     *
     * @param context    上下文
     * @param errorCode  错误码。0表示某些tag已经删除成功；非0表示所有tag均删除失败。
     * @param sucessTags 成功删除的tag
     * @param failTags   删除失败的tag
     * @param requestId  分配给对云推送的请求的id
     */
    @Override
    public void onDelTags(Context context, int errorCode, List<String> sucessTags, List<String> failTags, String requestId) {
        String responseString = "onDelTags errorCode=" + errorCode + " sucessTags=" + sucessTags + " failTags=" + failTags + " requestId="
                + requestId;
        Trace.d(responseString);

        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
        updateContent(context, responseString);
    }

    /**
     * listTags() 的回调函数。
     *
     * @param context   上下文
     * @param errorCode 错误码。0表示列举tag成功；非0表示失败。
     * @param tags      当前应用设置的所有tag。
     * @param requestId 分配给对云推送的请求的id
     */
    @Override
    public void onListTags(Context context, int errorCode, List<String> tags, String requestId) {
        String responseString = "onListTags errorCode=" + errorCode + " tags=" + tags;
        Trace.d(responseString);

        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
        updateContent(context, responseString);
    }

    /**
     * PushManager.stopWork() 的回调函数。
     *
     * @param context   上下文
     * @param errorCode 错误码。0表示从云推送解绑定成功；非0表示失败。
     * @param requestId 分配给对云推送的请求的id
     */
    @Override
    public void onUnbind(Context context, int errorCode, String requestId) {
        String responseString = "onUnbind errorCode=" + errorCode + " requestId = " + requestId;
        Trace.d(responseString);

        if (errorCode == 0) {
            // 解绑定成功
        }
        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
        updateContent(context, responseString);
    }

    private void updateContent(Context context, String content) {
        Log.d(TAG, "updateContent");
    }
}
