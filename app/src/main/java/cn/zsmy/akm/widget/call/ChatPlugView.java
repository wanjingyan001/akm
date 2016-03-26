package cn.zsmy.akm.widget.call;

import java.io.File;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.zsmy.akm.R;
import cn.zsmy.akm.chat.view.AudioRecorderButton;


public class ChatPlugView extends LinearLayout implements IChatPlug, OnClickListener, TextWatcher, OnFocusChangeListener,
		AudioRecorderButton.AudioFinishRecorderListener {
	public int currentPattern;
	private EditText mChatPlugInputEdt;
	private ImageView mChatPlugLeftImg;
	private ImageView mChatPlugRightImg;
	private PluginView mChatPlugOterToolContanier;
	private InputMethodManager imm;
	private TextView mChatPlugTxtCommitLabel;
	public static final int txt = 1;
	public static final int img = 2;
	public static final int voice = 3;
	private OnChatPlugListener listener;
	private AudioRecorderButton id_recorder_button;
	private Context context;

	public interface OnChatPlugListener {
		/**
		 * 发送文本消息
		 * 
		 * @param text
		 */
		void onTextSend(String text);

		/**
		 * 发送声音消息
		 * 
		 * @param recorderPath
		 */
		void onSendMsgVoice(String recorderPath);

		void onPluginClick(PluginEntity.PluginType type);
	}

	public ChatPlugView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView(context);
	}

	public ChatPlugView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView(context);
	}

	public ChatPlugView(Context context) {
		super(context);
		this.context=context;
		initializeView(context);
//		AnimationSet animSet = new AnimationSet(true);
//		TranslateAnimation anim=new TranslateAnimation(0,0,getY(),0);
//
//		anim.setDuration(1000);
//		animSet.addAnimation(anim);
//		startAnimation(anim);
	}

	public void setOnChatPlugListener(OnChatPlugListener listener) {
		this.listener = listener;
		mChatPlugOterToolContanier.initializeData(listener);
	}

	private void initializeView(Context context) {
		LayoutInflater.from(context).inflate(R.layout.widget_chat_plug, this);
		imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		id_recorder_button = (AudioRecorderButton) findViewById(R.id.id_recorder_button);
		id_recorder_button.setAudioFinishRecorderListener(this);
		mChatPlugOterToolContanier = (PluginView) findViewById(R.id.mChatPlugOterToolContanier);
		mChatPlugInputEdt = (EditText) findViewById(R.id.mChatPlugInputEdt);
		mChatPlugInputEdt.addTextChangedListener(this);
		mChatPlugInputEdt.setOnClickListener(this);
		mChatPlugInputEdt.setOnFocusChangeListener(this);
		mChatPlugLeftImg = (ImageView) findViewById(R.id.mChatPlugLeftImg);
		mChatPlugRightImg = (ImageView) findViewById(R.id.mChatPlugRightImg);
		mChatPlugTxtCommitLabel = (TextView) findViewById(R.id.mChatPlugTxtCommitLabel);
		mChatPlugTxtCommitLabel.setOnClickListener(this);
		mChatPlugRightImg.setOnClickListener(this);
		mChatPlugLeftImg.setOnClickListener(this);
		currentPattern = txt;
		changePatternForInput();
	}

	@Override
	public void changePatternForInput() {
		// 左右两边按钮
		mChatPlugLeftImg.setImageResource(R.drawable.chat_recoder_icon_false);
		mChatPlugRightImg.setImageResource(R.drawable.chat_add_img_icon_false);
		mChatPlugInputEdt.setVisibility(View.VISIBLE);
		mChatPlugOterToolContanier.setVisibility(View.GONE);
	}

	@Override
	public void changePatternForRecoder() {
		mChatPlugLeftImg.setImageResource(R.drawable.chat_key_input_pressed_icon_false);
	}

	@Override
	public void changePatternForPicture() {

	}

	long pressedTime = 0;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mChatPlugTxtCommitLabel:
			if (listener != null) {
				listener.onTextSend(mChatPlugInputEdt.getText().toString().trim());
			}
			break;
		case R.id.mChatPlugRightImg:
			if (currentPattern == voice) {
				currentPattern = txt;
				mChatPlugLeftImg.setImageResource(R.drawable.chat_recoder_icon_false);
				mChatPlugInputEdt.setVisibility(View.VISIBLE);
				id_recorder_button.setVisibility(View.GONE);
			}
			if (mChatPlugOterToolContanier.getVisibility() == View.VISIBLE) {
				mChatPlugOterToolContanier.setVisibility(View.GONE);
			} else {
				if (imm.isActive()) {
					hideSoftInput();
				}
				mChatPlugOterToolContanier.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.mChatPlugLeftImg:

			if (mChatPlugOterToolContanier.getVisibility() == View.VISIBLE) {
				mChatPlugOterToolContanier.setVisibility(View.GONE);
			}
			if (currentPattern == txt) {
				currentPattern = voice;
				mChatPlugLeftImg.setImageResource(R.drawable.chat_key_input_pressed_icon_false);
				mChatPlugInputEdt.setVisibility(View.GONE);
				id_recorder_button.setVisibility(View.VISIBLE);

			} else {
				currentPattern = txt;
				mChatPlugLeftImg.setImageResource(R.drawable.chat_recoder_icon_false);
				mChatPlugInputEdt.setVisibility(View.VISIBLE);
				id_recorder_button.setVisibility(View.GONE);
			}
			hideSoftInput();

			break;
		case R.id.mChatPlugInputEdt:
			if (mChatPlugOterToolContanier.getVisibility() == View.VISIBLE) {
				mChatPlugOterToolContanier.setVisibility(View.GONE);
			}
			// Toast.makeText(getContext(), "mChatPlugInputEdt", 1000).show();
			break;
		default:
			break;
		}
	}

	private void hideSoftInput() {
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(mChatPlugInputEdt.getWindowToken(), 0); // 强制隐藏键盘
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (TextUtils.isEmpty(s)) {
			mChatPlugTxtCommitLabel.setVisibility(View.GONE);
			mChatPlugRightImg.setVisibility(View.VISIBLE);
		} else {
			mChatPlugTxtCommitLabel.setVisibility(View.VISIBLE);
			mChatPlugRightImg.setVisibility(View.GONE);
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	public void setText(String string) {
		mChatPlugInputEdt.setText(string);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			mChatPlugOterToolContanier.setVisibility(View.GONE);
		}
	}

	@Override
	public void onFinish(float seconds, String filePath) {
		File file = new File(filePath);
		if (file.exists() && file.length() > 500) {
			listener.onSendMsgVoice(filePath);
		}
	}

}
