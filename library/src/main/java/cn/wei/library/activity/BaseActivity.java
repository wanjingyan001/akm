package cn.wei.library.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.ViewSwitcher;

import cn.wei.library.R;
import cn.wei.library.widget.EmptyView;

/**
 * 结构化activity的代码
 * 方法调用顺序为setContentView()->initializeView()->recoveryState(saveInstance)-> initializeData();
 */
public abstract class BaseActivity extends AppCompatActivity implements EmptyView.OnRetryListener {
    private String TAG = "wei";
    protected ViewSwitcher mViewSwitcher;
    protected EmptyView mEmptyView;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        if (!hasAppKilled()) {
            setContentView();
            initializeEmptyView();
            initializeView();
            if (saveInstance != null) {
                recoveryState(saveInstance);
            } else {
                initializeData();
            }
        } else {
            protectApp();
        }
    }

    private void initializeEmptyView() {
        if (findViewById(R.id.mEmptyView) != null) {
            mEmptyView = (EmptyView) findViewById(R.id.mEmptyView);
            mEmptyView.setOnRetryListener(this);
            mEmptyView.notifyDataChanged(EmptyView.State.ing);
            mViewSwitcher = (ViewSwitcher) findViewById(R.id.mViewSwitcher);
            load();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 判断app是否被强杀
     *
     * @return
     */
    public abstract boolean hasAppKilled();

    public void protectApp() {

        Log.e(TAG, hasAppKilled() + "protectApp:class=" + this.getClass().getSimpleName());
    }

    /**
     * 1. 设置布局
     */
    protected abstract void setContentView();

    /**
     * 2. 初始化布局
     */
    protected abstract void initializeView();

    /**
     * 界面被系统强杀 数据状态恢复
     *
     * @param saveInstance 状态数据
     */
    protected void recoveryState(Bundle saveInstance) {

    }

    /**
     * 3. 初始化ui数据
     */
    protected abstract void initializeData();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home && isCanFinish()) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 是否有回退功能
     *
     * @return
     */
    protected boolean isCanBack() {
        return true;
    }

    /**
     * 是否可以关闭当前界面
     *
     * @return
     */
    protected boolean isCanFinish() {
        return true;
    }

    /**
     * 标题是否居中
     *
     * @return
     */
    protected boolean isCenter() {
        return false;
    }


    @Override
    public void onRetry() {
        mEmptyView.notifyDataChanged(EmptyView.State.ing);
        load();
    }

    public void load() {
        mViewSwitcher.setDisplayedChild(0);
    }

    public void showContent(EmptyView.State state, int is_open_dialog) {
        if (state.equals(EmptyView.State.ing)) {
            mViewSwitcher.setDisplayedChild(1);
        } else {
            mViewSwitcher.setDisplayedChild(0);
            mEmptyView.notifyDataChanged(state);
        }
        mEmptyView.setDialogDismiss();
    }

    public void showContent() {
        mViewSwitcher.setDisplayedChild(1);
        mEmptyView.setDialogDismiss();
    }

    public void showErrorContent() {
        mViewSwitcher.setDisplayedChild(0);
        mEmptyView.setDialogDismiss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("TAG", getSupportFragmentManager().getBackStackEntryCount() + ">>>>>");
        getSupportFragmentManager().popBackStack();
        if (keyCode == KeyEvent.KEYCODE_BACK && getSupportFragmentManager().getBackStackEntryCount() == 0) {
            //退出程序的代码
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

}
