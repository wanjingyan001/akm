package cn.zsmy.akm.doctor.profile.edit;

import android.content.Context;
import android.content.Intent;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.Utils;
import cn.zsmy.doctor.R;


public class EditActivity extends BaseTitleActivity implements View.OnClickListener{

	private EditText et;
	private TextView save;
	private String flag;
	private String content;
	@Override
	public void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_edit_person);
		MyApplication.getInstance().addActivity(this);
		flag=getIntent().getStringExtra("CONTEXT");
		content=getIntent().getStringExtra("CONTENT");
		Log.i("TAG", flag);
		if(flag==null){
			return;
		}
	}
	@Override
	public void initializeView() {
		// TODO Auto-generated method stub
		super.initializeView();
		et = (EditText)findViewById(R.id.edit_name);
		save=(TextView)findViewById(R.id.save);
		save.setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}
	@Override
	public boolean hasAppKilled() {
		return super.hasAppKilled();
	}
	@Override
	public void initializeData() {
		if(flag.equals(Constants.EDIT_PERSON_NAME_VALUES)){
			setTitle("输入姓名");
			et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
		} else if(flag.equals(Constants.EDIT_OFFICE_PHONE_NAME_VALUES)){
			setTitle("输入科室电话");
			et.setInputType(InputType.TYPE_CLASS_PHONE);
			et.setHint("请输入科室电话");
			et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
		}
		if(!TextUtils.isEmpty(content)){
              et.setText(content);
		}

	}
	@Override
	protected boolean isCanFinish() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setAction(et.getText().toString().trim());
		setResult(RESULT_OK, intent);
		return true;
	}
	@Override
	public void onClick(View v) {
		if(flag.equals(Constants.EDIT_PERSON_NAME_VALUES)){
			if(TextUtils.isEmpty(et.getText().toString())){
				Toast.makeText(this, "你还未输入姓名", Toast.LENGTH_LONG).show();
			}
		}else if(flag.equals(Constants.EDIT_OFFICE_PHONE_NAME_VALUES)){
			if(TextUtils.isEmpty(et.getText().toString())){
				Toast.makeText(this, "你还未输入科室电话", Toast.LENGTH_LONG).show();
			}
		}
		setIntentActivtyResult();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_DEL){
			return true;
		}
		setIntentActivtyResult();
		return true;

	}
	public void setIntentActivtyResult(){
		Intent intent = new Intent();
		if(flag.equals(Constants.EDIT_PERSON_NAME_VALUES)){
			intent.setAction(et.getText().toString().trim());
		}else if(flag.equals(Constants.EDIT_OFFICE_PHONE_NAME_VALUES)){
				intent.setAction(et.getText().toString().trim());
		}
		setResult(RESULT_OK, intent);
		finish();
	}
}
