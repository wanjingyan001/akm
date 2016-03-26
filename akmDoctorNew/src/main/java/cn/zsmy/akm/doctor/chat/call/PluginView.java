package cn.zsmy.akm.doctor.chat.call;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import cn.zsmy.doctor.R;


/**
 * @author Stay
 * @version create time：Oct 15, 2014 8:05:01 PM
 */
public class PluginView extends LinearLayout implements OnPageChangeListener {

	private ViewPager mEmoViewPager;
	private EmoDotView mEmoDotView;
	private EmoPagerAdapter adapter;
	private Context context;
	private ChatPlugView.OnChatPlugListener listener;
	private int pageCount = 1;
	private int pageIndex;
	private ArrayList<PluginEntity> entities;

	public PluginView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView(context);
	}

	public PluginView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView(context);
	}

	public PluginView(Context context) {
		super(context);
		initializeView(context);
	}

	private void initializeView(Context context) {
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.widget_plugin_container, this);
		mEmoDotView = (EmoDotView) findViewById(R.id.mEmoDotView);
		mEmoViewPager = (ViewPager) findViewById(R.id.mEmoViewPager);
		adapter = new EmoPagerAdapter();
		mEmoViewPager.setAdapter(adapter);
		mEmoViewPager.setOnPageChangeListener(this);
	}

	public void initializeData(ChatPlugView.OnChatPlugListener listener) {
		this.listener = listener;
		entities = new ArrayList<PluginEntity>();
		entities.add(new PluginEntity("图库",R.drawable.icon_picture, PluginEntity.PluginType.Images));
		entities.add(new PluginEntity("相机",R.drawable.icon_takepicture, PluginEntity.PluginType.Camera));
		entities.add(new PluginEntity("语音通话",R.drawable.icon_langi, PluginEntity.PluginType.WalkieTalkie));
		entities.add(new PluginEntity("视频",R.drawable.icon_mv, PluginEntity.PluginType.VideoCall));


	}


	class EmoPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pageCount;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			PluginGrid custom = new PluginGrid(context);
			custom.initializeData(entities, listener);
			container.addView(custom);
			return custom;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int pageIndex) {
		mEmoDotView.notifyDataChanged(pageIndex, pageCount);
		this.pageIndex = pageIndex;
	}

}
