package cn.zsmy.akm.salon.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.salon.activity.choosePhoto.ForumReleaseActivity;
import cn.zsmy.akm.salon.fragment.PostListFragment;
import cn.zsmy.akm.utils.Is_Login;
import cn.zsmy.akm.widget.dialog.ChooseDialog;
import cn.zsmy.akm.widget.scrolltabview.SlideTabView;

/**
 * 论坛界面
 */
public class PlatformListActivity extends BaseTitleActivity {
    private SlideTabView mSlideTabView;
    private static String TAG = "MyApplication";
    private int ADD_NEW_POST = 1;//发新帖
    private String platform;
    private PostListFragment fragment;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_platform_list);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeData() {
        String platforName = getIntent().getStringExtra("platforName");
        setTitle(platforName);

    }

    @Override
    protected void initializeView() {
        super.initializeView();
        platform = getIntent().getStringExtra("platform");
        mSlideTabView = ((SlideTabView) findViewById(R.id.platform_slidetab));
        ArrayList<String> titlies = new ArrayList<>();
        titlies.add("全部");
        titlies.add("热门");
        titlies.add("最新");
        ArrayList<Fragment> clazz = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            fragment = PostListFragment.newInstance(i+1,platform);
//            Bundle bundle = new Bundle();
//            fragment.setArguments(bundle);
//            clazz.add(fragment);
//        }
        PostListFragment fragment1 = PostListFragment.newInstance(0, platform);
        PostListFragment fragment2 = PostListFragment.newInstance(2, platform);
        PostListFragment fragment3 = PostListFragment.newInstance(1, platform);
        clazz.add(fragment1);
        clazz.add(fragment2);
        clazz.add(fragment3);
        SlideTabView.TabConfig config = new SlideTabView.TabConfig(titlies, clazz);
        config.textSize = 16;
        config.normalItemColor = R.color.title_color;
        config.selectedItemColor = R.color.font_color;
        config.itemBackground = R.color.widgets_general_row_normal;
        mSlideTabView.initializeData(getSupportFragmentManager(), config);
        mSlideTabView.notifyDataChanged();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, PlatformListActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_platform_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.publish_post) {
            //判断用户是否登录，若登录则直接进入发帖界面，否则进入登录界面
            ChooseDialog chooseDialog = new ChooseDialog(this, 0);
            boolean flag = Is_Login.getLoginStatus(this);
            if (flag) {
                Intent intent = ForumReleaseActivity.getIntent(this);
                intent.putExtra("platform", platform);
                startActivityForResult(intent, ADD_NEW_POST);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NEW_POST) {
            //刷新列表

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
