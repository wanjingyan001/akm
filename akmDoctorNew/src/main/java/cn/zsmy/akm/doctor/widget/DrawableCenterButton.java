package cn.zsmy.akm.doctor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 图片和文字居中的button(使用时必须使用android:gravity="fill_vertical")
 * Created by wanjingyan
 * on 2015/12/15 14:05.
 */
public class DrawableCenterButton extends Button {
    public DrawableCenterButton(Context context) {
        super(context);
    }

    public DrawableCenterButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableCenterButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                final float textWidth = getPaint().measureText(getText().toString());
                final int drawablePadding = getCompoundDrawablePadding();
                final int drawableWidth = drawableLeft.getIntrinsicWidth();
                final float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
    }
}
