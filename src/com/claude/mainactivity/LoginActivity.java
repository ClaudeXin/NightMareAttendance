package com.claude.mainactivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class LoginActivity extends Activity {

	private EditText username;
	private EditText password;
	private Button login_btn;
	private MyApp unique_infor;
	private SharedPreferences sp;
	private CheckBox rem_pw;
	private String userNameValue, passwordValue;
	private TextView saveusername;
	private TextView savepassword;

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
		TextView autolink = (TextView) findViewById(R.id.autolink);
		autolink.setMovementMethod(LinkMovementMethod.getInstance());
		sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
		rem_pw = (CheckBox) findViewById(R.id.rememberPsw);
		saveusername = (TextView) findViewById(R.id.username);
		savepassword = (TextView) findViewById(R.id.password);

		if (sp.getBoolean("ISCHECK", false)) {
			rem_pw.setChecked(true);
			saveusername.setText(sp.getString("USER_NAME", ""));
			savepassword.setText(sp.getString("PASSWORD", ""));
		}
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
		
		rem_pw.setOnCheckedChangeListener(new OnCheckedChangeListener() {  
    		public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {  
	        		if (rem_pw.isChecked()) {   
                			sp.edit().putBoolean("ISCHECK", true).commit();  
	                }else {  
            		    	sp.edit().putBoolean("ISCHECK", false).commit();  
            		}  

		          }  
    	}); 
	}
}
