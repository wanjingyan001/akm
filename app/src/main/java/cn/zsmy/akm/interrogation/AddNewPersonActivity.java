package cn.zsmy.akm.interrogation;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.zsmy.akm.R;
import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.home.MyApplication;


public class AddNewPersonActivity extends BaseTitleActivity implements View.OnClickListener{

	private EditText et;
	private Button save;
	@Override
	public void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_interrogation_add_new_person);
		MyApplication.getInstance().addActivity(this);
	}
	@Override
	public void initializeView() {
		// TODO Auto-generated method stub

		super.initializeView();
		et = (EditText)findViewById(R.id.edit_name);
		save=(Button)findViewById(R.id.save);
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
		setTitle("输入姓名");
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
		setIntentActivtyResult();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		setIntentActivtyResult();
		return super.onKeyDown(keyCode, event);

	}
	public void setIntentActivtyResult(){
		Intent intent = new Intent();
		intent.setAction(et.getText().toString().trim());
		setResult(RESULT_OK, intent);
		finish();
	}
}
