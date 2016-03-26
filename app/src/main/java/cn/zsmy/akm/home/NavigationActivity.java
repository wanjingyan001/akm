package cn.zsmy.akm.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import cn.zsmy.akm.R;
import cn.zsmy.akm.widget.AutoScrollViewPager;


public class NavigationActivity extends AppCompatActivity {

    private AutoScrollViewPager viewPager;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=NavigationActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_navigation);
        viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);
        myAdapter = new MyAdapter();
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(myAdapter);
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        /**
         * 销毁当前page的相隔2个及2个以上的item时调用
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i("test", "销毁view位置:" + position);
            container.removeView((View) object); // 删除页卡
        }

        // 这个方法用来实例化页卡
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView photoView = new ImageView(container.getContext());
            photoView.setTag(position);
            photoView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            switch (position) {
                case 0:
                    photoView.setImageResource(R.drawable.doctor_navigation_one);
                    break;
                case 1:
                    photoView.setImageResource(R.drawable.doctor_navigation_two);
                    break;
                case 2:
                    photoView.setImageResource(R.drawable.doctor_navigation_three);
                    photoView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(NavigationActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    break;
                default:
                    break;
            }
            return photoView; // 返回该view对象，作为key
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
