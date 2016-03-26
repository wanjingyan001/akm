package cn.zsmy.akm.chat.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.R;

/**
 * Created by sanz on 2015/12/14 11:27
 */
public abstract class ChatHolder {
    public abstract void initializeView(View view);

    public abstract void initializeData(int position);


}

