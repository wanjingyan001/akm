package cn.zsmy.akm.salon.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.dialog.ProgressDialogUtils;
import cn.zsmy.akm.widget.dialog.ReplyDialog;

/**
 * 回复帖子
 * Created by Administrator on 2016/1/6.
 */
public class ReplyPostActivity extends BaseTitleActivity {
    private EditText comment;
    private String nickname;
    private String post_id;
    private String post_title;
    private AlertDialog.Builder builder;
    private String comment1;

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_reply_post);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        comment = ((EditText) findViewById(R.id.comment_submit));
    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        post_id = intent.getStringExtra("post_ID");
        post_title = intent.getStringExtra("post_title");
        comment1 = intent.getStringExtra("COMMENT");
        if (nickname != null) {
            setTitle("回复" + nickname);
        } else {
            setTitle("回复帖子");
        }
        if (comment1 != null) {
            SpannableString spanString = new SpannableString("@" + nickname + "\t");
            ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
            spanString.setSpan(span, 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            comment.append(spanString);
        }
    }

    public static Intent getIntent(Context context, String nickname, String postId, String postTitle) {
        Intent intent = new Intent(context, ReplyPostActivity.class);
        intent.putExtra("nickname", nickname);
        intent.putExtra("post_ID", postId);
        intent.putExtra("post_title", postTitle);
        return intent;
    }


    private void submitReply(String value) {
        ProgressDialogUtils.showProgressDialog(this, "提交中");
        Request request = new Request(UrlHelpper.SUBMIT_REPLY, Request.RequestMethod.POST, this);
        request.put("title", post_title);
        request.put("content", value);
        request.put("topicId", post_id);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                ProgressDialogUtils.closeProgressDialog();
                finish();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reply_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String value = comment.getText().toString();
        if (item.getItemId() == R.id.reply_post) {
            //提交帖子评论
            if (!TextUtils.isEmpty(value) || !value.equals("@" + nickname + "\t")) {
                submitReply(value);
            } else {
                Toast.makeText(ReplyPostActivity.this, "评论内容为空", Toast.LENGTH_SHORT).show();
            }
        } else if (item.getItemId() == android.R.id.home) {
            if (TextUtils.isEmpty(value)) {
                finish();
            } else {
                ReplyDialog dialog = new ReplyDialog(this);
                dialog.show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (TextUtils.isEmpty(comment.getText().toString())) {
                finish();
            } else {
                ReplyDialog dialog = new ReplyDialog(this);
                dialog.show();
                return true;
            }
        }
        return true;
    }
}
