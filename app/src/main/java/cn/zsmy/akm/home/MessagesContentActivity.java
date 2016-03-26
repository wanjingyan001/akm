package cn.zsmy.akm.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.adapter.PushMessageAdapter;
import cn.zsmy.akm.chat.bean.MessageType;
import cn.zsmy.akm.chat.utils.Trace;
import cn.zsmy.akm.db.PushMessage.PushMessage;
import cn.zsmy.akm.db.PushMessage.PushMessageController;
import cn.zsmy.akm.salon.FunctionDetailActivity;
import cn.zsmy.akm.utils.UrlHelpper;

public class MessagesContentActivity extends BaseTitleActivity implements SwipeMenuListView.OnMenuItemClickListener, AdapterView.OnItemClickListener {
    private SwipeMenuListView mMessageList;
    private List<PushMessage> pushMessages = new ArrayList<>();
    private String url;
    public static int chooseType;
    public static int advisoryType;
    public static int spreadType;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            EmptyView.State state = null;
            switch (msg.what) {
                case 0:
                    state = EmptyView.State.ing;
                    break;
                case 1:
                    state = EmptyView.State.empty;
                    break;
                case 2:

            }
            showContent(state, 1);
        }
    };
    private PushMessageAdapter pushMessageAdapter;
    private List<PushMessage> pubMsg;

    @Override
    public boolean hasAppKilled() {
        return false;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_messages_content);
        MyApplication.getInstance().addActivity(this);

    }

    @Override
    protected void initializeView() {
        super.initializeView();
        setTitle("消息中心");
        mMessageList = ((SwipeMenuListView) findViewById(R.id.messages_list));
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu swipeMenu) {
                creatItem(swipeMenu);
            }
        };
        mMessageList.setMenuCreator(creator);
        mMessageList.setOnMenuItemClickListener(this);
        mMessageList.setOnItemClickListener(this);
    }


    @Override
    protected void initializeData() {
        pubMsg = PushMessageController.queryAllMessage();
        if (pubMsg != null && pubMsg.size() > 0) {
            Log.d("TAG", "数据库中数据条数：" + pubMsg.size());
            pushMessages.addAll(pubMsg);
            pushMessageAdapter = new PushMessageAdapter(this, pushMessages);
            mMessageList.setAdapter(pushMessageAdapter);
            mHandler.sendEmptyMessageDelayed(0, 1000);
        } else
            mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MessagesContentActivity.class);
    }

    //创建侧滑删除的菜单项
    private void creatItem(SwipeMenu swipeMenu) {
        SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
        openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                0xCE)));
        DisplayMetrics metrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        openItem.setWidth(widthPixels / 4);
        openItem.setTitle("标记已读");
        openItem.setTitleSize(16);
        openItem.setTitleColor(Color.WHITE);
        swipeMenu.addMenuItem(openItem);
        SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                0x3F, 0x25)));
        deleteItem.setTitle("删除");
        deleteItem.setTitleSize(16);
        deleteItem.setTitleColor(Color.WHITE);
        deleteItem.setWidth(widthPixels / 4);
        swipeMenu.addMenuItem(deleteItem);
    }

    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        switch (index) {
            case 0:
                //标记已读
                changeNotice(position);
                break;
            case 1:
                //删除
                deleteNotic(position);

                break;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notice, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (pushMessages != null) {
            switch (itemId) {
                case R.id.all_readed:
                    readedAll();
                    break;
                case R.id.all_delete:
                    deleteAll();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 删除消息
     *
     * @param position
     */
    private void deleteNotic(int position) {
        PushMessageController.deleteData(pushMessages.get(position));
        pushMessages.remove(position);
        pushMessageAdapter.notifyDataSetChanged();
    }

    /**
     * 修改消息状态
     *
     * @param position
     */
    private void changeNotice(int position) {
        View childAt = mMessageList.getChildAt(position);
        ((TextView) childAt.findViewById(R.id.message_title)).setTextColor(Color.rgb(131, 131, 131));
        ((TextView) childAt.findViewById(R.id.message_centent)).setTextColor(Color.rgb(131, 131, 131));
        ((TextView) childAt.findViewById(R.id.messags_time)).setTextColor(Color.rgb(131, 131, 131));
        PushMessageController.updateStaus(pushMessages.get(position).getId());
    }

    /**
     * 全部已读
     */
    private void readedAll() {
        for (int i = 0; i < pushMessages.size(); i++) {
            changeNotice(i);
        }
    }


    private void deleteAll() {
        PushMessageController.deleteAll();
        pushMessages.clear();
        pushMessageAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PushMessage message = pubMsg.get(position);
        if (message != null) {
            MessageType messageType = message.getType();
            switch (messageType) {
                case news:
                    Toast.makeText(this, "前往帖子列表", Toast.LENGTH_SHORT).show();
                    break;
                case activity:
                    startActivity(FunctionDetailActivity.getIntent(MessagesContentActivity.this, UrlHelpper.FILE_IP + message.getHttpUrl(), message.getTitle()));
                    Toast.makeText(this, "前往活动列表", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Trace.d("----------");
                    break;
            }
        }
    }
}
