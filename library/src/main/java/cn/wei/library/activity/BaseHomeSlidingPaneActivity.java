package cn.wei.library.activity;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.DisplayMetrics;
import android.view.View;

import cn.wei.library.R;
import cn.wei.library.fragment.BaseFragment;


public abstract class BaseHomeSlidingPaneActivity extends BaseActivity
		implements SlidingPaneLayout.PanelSlideListener {
	public static final int one = 0;
	public static final int two = 1;
	public static final int three = 2;
	private SlidingPaneLayout slidingpaneLayout;
	private DisplayMetrics displayMetrics = new DisplayMetrics();
	private BaseFragment menuFragment;
	private BaseFragment contentFragment;
	private int maxMargin;

	@Override
	public final void setContentView() {
		setContentView(getContentViewLayoutId());
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		maxMargin = displayMetrics.heightPixels / 10;
	}
	public abstract int getContentViewLayoutId();

	@Override
	public void initializeData() {

	}

	@Override
	public void initializeView() {
		slidingpaneLayout = (SlidingPaneLayout) findViewById(R.id.generalSlidingpaneLayout);
		slidingpaneLayout.setSliderFadeColor(0);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		menuFragment = getMenuFragment();
		contentFragment = getContentFragment();
		transaction.replace(R.id.generalMenu, menuFragment);
		transaction.replace(R.id.generalContent, contentFragment);
		transaction.commit();
		slidingpaneLayout.setPanelSlideListener(this);
	}

	/**
	 *
	 * 获取左滑菜单 Fragment
	 * Fragment需要继承BaseFragment
	 * @return
	 */
	public abstract BaseFragment getMenuFragment();

	/**
	 *  
	 * 内容 Fragment
	 * Fragment需要继承BaseFragment
	 * @return
	 */
	public abstract BaseFragment getContentFragment();

	/**
	 * 
	 * @return 滑动动画方式
	 * 0,1,2三种选择
	 */
	public  int getShowType(){
		return one;
	}

	@SuppressLint("NewApi") @Override
	public final void onPanelSlide(View panel, float slideOffset) {
		View view = menuFragment.getCurrentView();
		switch (getShowType()) {
		case one:
			View contentView = contentFragment.getCurrentView();
			float scaleSize=(float) (1-slideOffset*0.2);
			contentView.setScaleX(scaleSize);
			contentView.setScaleY(scaleSize);//1-0  1-0.8
			contentView.setPivotX(0);// 设置缩放和选择的点
			contentView.setPivotY(displayMetrics.heightPixels / 2);
			float scale = 1 - ((1 - slideOffset) * maxMargin * 2)
					/ (float) displayMetrics.heightPixels;
			view.setScaleX(scale);// 设置缩放的基准点
			view.setScaleY(scale);// 设置缩放的基准点
			view.setPivotX(0);// 设置缩放和选择的点
			view.setPivotY(displayMetrics.heightPixels / 2);
			view.setAlpha(slideOffset);
			view.setTranslationX((float) (-(1 - slideOffset) * view.getWidth() * 0.3));
			break;
		case two:
			view.setAlpha(slideOffset);
			break;
		case three:
			view.setAlpha(slideOffset);
			view.setTranslationX((float) (-(1 - slideOffset) * view.getWidth() * 0.3));
			break;
		default:
			break;
		}
	}

	@Override
	public void onPanelOpened(View panel) {

	}

	@Override
	public void onPanelClosed(View panel) {

	}
}
