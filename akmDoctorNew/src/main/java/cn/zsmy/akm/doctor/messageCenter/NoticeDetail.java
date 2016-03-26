package cn.zsmy.akm.doctor.messageCenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.doctor.base.BaseTitleListActivity;
import cn.zsmy.akm.doctor.bean.SystemNotice;
import cn.zsmy.akm.doctor.chat.bean.MessageType;
import cn.zsmy.akm.doctor.db.PushMessage.PushMessage;
import cn.zsmy.akm.doctor.db.PushMessage.PushMessageController;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.doctor.R;

/**
 * Created by wanjingyan
 * on 2015/12/16 15:27.
 */
public class NoticeDetail extends BaseTitleListActivity {
    private int status;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showContent(EmptyView.State.empty, 1);
                    break;
                case 1:
                    showContent();
                    break;
            }
        }
    };
    private int type;
    private List<SystemNotice.DataEntity> data;
    private List<PushMessage> pushMessages;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_notice_detail);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();

    }

    @Override
    protected void initializeData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE", -1);
        switch (type) {
            case 0:
                setTitle(getText(R.string.patient_reply));
                pushMessages = PushMessageController.queryReplyMessage(MessageType.info);
                break;
            case 1:
                setTitle(getText(R.string.system_messages));
                pushMessages = PushMessageController.queryAllMessage();
                break;
        }
        modules.addAll(pushMessages);
        adapter.notifyDataSetChanged();
        if (modules.size() > 0) {
            handler.sendEmptyMessageDelayed(1, 1000);
        } else {
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, NoticeDetail.class);
    }


    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        switch (type) {
            case 0:
                pushMessages = PushMessageController.queryReplyMessage(MessageType.info);
                break;
            case 1:
                 pushMessages = PushMessageController.queryAllMessage();
                break;
        }
        adapter.notifyDataSetChanged();
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        PushMessage message = (PushMessage) modules.get(position - 1);
        switch (type) {
            case 0:
//                PatientsNotice.DataEntity notice = (PatientsNotice.DataEntity) modules.get(position - 1);
//                Intent intent = new Intent(NoticeDetail.this, ChatActivity.class);
//                intent.putExtra("caseId", notice.getCaseId());
//                startActivity(intent);
                break;
            case 1:

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (type) {
            case 0:
                break;
            case 1:
                getMenuInflater().inflate(R.menu.menu_edit, menu);
                break;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_notice) {
            String title = item.getTitle().toString();
            if (title.equals("编辑")) {
                item.setTitle("完成");
                status = 1;
                adapter.notifyDataSetChanged();

            } else if (title.equals("完成")) {
                item.setTitle("编辑");
                status = 0;
                adapter.notifyDataSetChanged();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.item_notice_detail, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    public class Holder extends QBaseViewHolder {
        private ImageView dNoticeImage;
        private TextView dNoticeTime;
        private TextView dNoticeContent;
        private ImageView dDeleteNotice;


        @Override
        public void initializeView(View view) {
            dNoticeImage = ((ImageView) view.findViewById(R.id.notice_image));
            dNoticeTime = ((TextView) view.findViewById(R.id.notice_time));
            dNoticeContent = ((TextView) view.findViewById(R.id.notice_content));
            dDeleteNotice = ((ImageView) view.findViewById(R.id.icon_delete_notice));
        }

        @Override
        public void initializeData(final int position) {
            setIcon(status);
            final PushMessage message = (PushMessage) modules.get(position);
            dNoticeContent.setText(message.getContent());
            dDeleteNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteNotic(message);
                    modules.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        /**
         * 删除消息
         *
         * @param  message
         */
        private void deleteNotic(PushMessage message) {
            PushMessageController.deleteData(message);
        }

        public void setIcon(int status) {
            switch (status) {
                case 0:
                    dDeleteNotice.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    dDeleteNotice.setVisibility(View.VISIBLE);
                    break;
            }
        }

    }
}
