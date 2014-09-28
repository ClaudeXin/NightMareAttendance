package com.claude.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private EditText username;
	private EditText password;
	private Button login_btn;
	private MyApp unique_infor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_login);
		this.initCompone();
		this.buttonAction();
	}

	private void initCompone() {
		this.username = (EditText) findViewById(R.id.username);
		this.password = (EditText) findViewById(R.id.password);
		this.login_btn = (Button) findViewById(R.id.login_btn);
		this.unique_infor = (MyApp) getApplication();
	}

	private void buttonAction() {
		login_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String str_username = username.getText().toString();
				String str_password = password.getText().toString();
				String[] user = new String[] { "username", str_username,
						"password", str_password };
				unique_infor.addTeacherInfor(user);
				Intent intent = new Intent();
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setClass(getApplicationContext(), InitActivity.class);
				getApplication().startActivity(intent);
			}
		});
	}
}
