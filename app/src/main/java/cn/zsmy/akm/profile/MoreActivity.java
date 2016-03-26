package cn.zsmy.akm.profile;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.widget.dialog.ChooseDialog;
import cn.zsmy.akm.widget.row.BaseRowDescriptor;
import cn.zsmy.akm.widget.row.ContainerDescriptor;
import cn.zsmy.akm.widget.row.ContainerView;
import cn.zsmy.akm.widget.row.GeneralRowDescriptor;
import cn.zsmy.akm.widget.row.GroupDescriptor;
import cn.zsmy.akm.widget.row.OnRowClickListener;
import cn.zsmy.akm.widget.row.tool.RowActionEnum;
import cn.zsmy.akm.widget.row.tool.RowViewFactory;

/**
 * 更多
 * Created by qinwei on 2015/11/24 17:23
 */
public class MoreActivity extends BaseTitleActivity implements OnRowClickListener, View.OnClickListener {
    private ContainerView mWidgetContainerView;
    private Button signout;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_more);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        signout = ((Button) findViewById(R.id.sign_out));
        signout.setOnClickListener(this);
        if (MyApplication.getProfile() == null) {
            signout.setVisibility(View.GONE);
        } else {
            signout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initializeData() {
        setTitle("更多");
        RowViewFactory.LINE_IS_MATCH_PARENT = true;
        mWidgetContainerView = (ContainerView) findViewById(R.id.mWidgetContainerView);
        ArrayList<GroupDescriptor> groupDescriptors = new ArrayList<GroupDescriptor>();
        ArrayList<BaseRowDescriptor> rowDescriptors1 = new ArrayList<BaseRowDescriptor>();
        rowDescriptors1.add(new GeneralRowDescriptor(0, "消息通知", "", RowActionEnum.profile_Message_notification));


        GroupDescriptor groupDescriptor1 = new GroupDescriptor("", rowDescriptors1);

        ArrayList<BaseRowDescriptor> rowDescriptors2 = new ArrayList<BaseRowDescriptor>();

        rowDescriptors2.add(new GeneralRowDescriptor(0, "用户协议", RowActionEnum.profile_User_Agreement));
        rowDescriptors2.add(new GeneralRowDescriptor(0, "关于我们", RowActionEnum.profile_about_us));
        rowDescriptors2.add(new GeneralRowDescriptor(0, "使用帮助", RowActionEnum.profile_use_help));
        GroupDescriptor groupDescriptor2 = new GroupDescriptor("", rowDescriptors2);


        groupDescriptors.add(groupDescriptor1);
        groupDescriptors.add(groupDescriptor2);
        ContainerDescriptor containerDescriptor = new ContainerDescriptor(groupDescriptors);
        mWidgetContainerView.initializeData(containerDescriptor, this);
        mWidgetContainerView.notifyDataChanged();
    }

    @Override
    public void onRowClick(View rowView, RowActionEnum action) {
        switch (action) {
            case profile_Message_notification:
                //消息通知
                startActivity(new Intent(this, NoticeSettingsActivity.class));
                break;
            case profile_User_Agreement:
                //用户协议
                startActivity(new Intent(this, UserAgreementActivity.class));
                break;
            case profile_about_us:
                //关于我们
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case profile_use_help:
                //使用帮助
                startActivity(new Intent(this, UserHelpActivity.class));
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_out:
                ChooseDialog dialog = new ChooseDialog(this, 1);
                dialog.show();
                break;
        }
    }
}
