package cn.zsmy.akm.doctor.chat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class MGridView extends GridView {
	
	private OnTouchInvalidPositionListener  mTouchInvalidPosListener;
	
	public interface OnTouchInvalidPositionListener {
	    /**
	     * motionEvent 可使用 MotionEvent.ACTION_DOWN 或者 MotionEvent.ACTION_UP等来按需要进行判断
	     * @return 是否要终止事件的路由
	     */
	    boolean onTouchInvalidPosition(int motionEvent);
	  }
	  /**
	   * 点击空白区域时的响应和处理接口
	   */
	  public void setOnTouchInvalidPositionListener(OnTouchInvalidPositionListener listener) {
	    mTouchInvalidPosListener = listener;
	  }

	public boolean hasScrollBar = true;

	/**
	 * @param context
	 */
	public MGridView(Context context) {
		this(context, null);
	}

	public MGridView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public MGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = heightMeasureSpec;
		if (hasScrollBar) {
			expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);// 注意这里,这里的意思是直接测量出GridView的高度
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
	
	 @Override
	  public boolean onTouchEvent(MotionEvent event) {
	    if(mTouchInvalidPosListener == null) {
	      return super.onTouchEvent(event);
	    }
	    if (!isEnabled()) {
	      // A disabled view that is clickable still consumes the touch
	      // events, it just doesn't respond to them.
	      return isClickable() || isLongClickable();
	    }
	    final int motionPosition = pointToPosition((int)event.getX(), (int)event.getY());
	    if( motionPosition == INVALID_POSITION ) {
	      super.onTouchEvent(event);
	      return mTouchInvalidPosListener.onTouchInvalidPosition(event.getActionMasked());
	    }
	    return super.onTouchEvent(event);
	  }

}