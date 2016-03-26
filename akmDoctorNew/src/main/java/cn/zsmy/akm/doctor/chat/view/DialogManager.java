package cn.zsmy.akm.doctor.chat.view;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zsmy.doctor.R;


public class DialogManager {

	private AlertDialog.Builder builder;
	private ImageView mIcon;
	private ImageView mVoice;
	private TextView mLable;

	private Context mContext;

	private AlertDialog dialog;

	public DialogManager(Context context) {
		this.mContext = context;
	}

	public void showRecordingDialog() {
		builder = new AlertDialog.Builder(mContext, R.style.Theme_AudioDialog);
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.dialog_recorder, null);

		mIcon = (ImageView) view.findViewById(R.id.id_recorder_dialog_icon);
		mVoice = (ImageView) view.findViewById(R.id.id_recorder_dialog_voice);
		mLable = (TextView) view.findViewById(R.id.id_recorder_dialog_label);

		builder.setView(view);
		dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public void recording() {
		if (dialog != null && dialog.isShowing()) {
			mIcon.setVisibility(View.VISIBLE);
			mVoice.setVisibility(View.VISIBLE);
			mLable.setVisibility(View.VISIBLE);

			mIcon.setImageResource(R.drawable.recorder);
			mLable.setText("手指上滑取消发送");
		}
	}

	public void wantToCancel() {
		if (dialog != null && dialog.isShowing()) {
			mIcon.setVisibility(View.VISIBLE);
			mVoice.setVisibility(View.GONE);
			mLable.setVisibility(View.VISIBLE);

			mIcon.setImageResource(R.drawable.cancel);
			mLable.setText("松开手指,取消发送");
		}
	}

	public void tooShort() {
		if (dialog != null && dialog.isShowing()) {
			mIcon.setVisibility(View.VISIBLE);
			mVoice.setVisibility(View.GONE);
			mLable.setVisibility(View.VISIBLE);

			mIcon.setImageResource(R.drawable.voice_to_short);
			mLable.setText("录音时间太短");
		}
	}

	public void dimissDialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
			dialog = null;
		}
	}

	public void updateVoiceLevel(int level) {
		if (dialog != null && dialog.isShowing()) {
			int resId = mContext.getResources().getIdentifier("v" + level, "drawable", mContext.getPackageName());
			mVoice.setImageResource(resId);
		}
	}

}