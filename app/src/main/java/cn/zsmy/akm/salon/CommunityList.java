package cn.zsmy.akm.salon;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.salon.fragment.CommunityFragment;
import cn.zsmy.akm.widget.scrolltabview.SlideTabView;




/**
 * 社区活动列表
 * Created by qinwei on 2015/11/30 14:38
 */
public class CommunityList extends BaseTitleActivity {
    private SlideTabView tabView;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_community_list);
        tabView = (SlideTabView) findViewById(R.id.community_slide_tabID);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("全部");
        labels.add("推荐");
        labels.add("进行中");
        labels.add("往期活动");
        ArrayList< Fragment> clazz = new ArrayList<Fragment>();
        CommunityFragment communityFragment;
        for (int i = 0; i < labels.size(); i++) {
            communityFragment=new CommunityFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("TYPE",i);
            communityFragment.setArguments(bundle);
            clazz.add(communityFragment);
        }
        SlideTabView.TabConfig config = new SlideTabView.TabConfig(labels, clazz);
        config.textSize = 16;
        config.normalItemColor = R.color.text_color_black;
        config.selectedItemColor = R.color.font_color;
        config.itemBackground = R.color.white;
        tabView.initializeData(getSupportFragmentManager(), config);
        tabView.notifyDataChanged();
    }

    @Override
    protected void initializeData() {
        setTitle("活动社区");

    }

}
