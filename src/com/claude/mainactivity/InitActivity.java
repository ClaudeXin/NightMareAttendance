package com.claude.mainactivity;

import java.io.IOException;

import com.claude.bean.Configure;
import com.claude.bean.HandleView;
import com.claude.bean.HandleView.LoginHandle;
import com.claude.handle.ErrorArgException;
import com.claude.obtaininformation.XmlResDetech;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

@SuppressLint({ "DefaultLocale", "HandlerLeak" })
public class InitActivity extends Activity {

	private MyApp unique_infor;
	private String username;
	private String password;
	private Configure conf = Configure.getInstance();
	private HandleView handle;
	private LoginHandle handle_login;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_init);
		this.initCompone();
		new Thread() {
			public void run() {
				try {
					String login_path = String.format(conf.getPath()
							+ "/%s.xml", conf.getTime());
					XmlResDetech detech = new XmlResDetech("login",
							new String[] { username, password }, login_path);
					unique_infor.setCurLoginFile(login_path);
					if (detech.getStaus()) {
						handle_login.sendEmptyMessage(0x000);
					} else {
						handle_login.sendEmptyMessage(0x001);
					}
				} catch (ErrorArgException e) {
					handle_login.sendEmptyMessage(0x001);
				} catch (IOException e) {
					e.printStackTrace();
					handle_login.sendEmptyMessage(0x001);
				}
			}
		}.start();
	}

	public void initCompone() {
		unique_infor = (MyApp) getApplication();
		handle = new HandleView(getApplicationContext());
		handle_login = handle.getLoginHandle();
		try {
			username = this.unique_infor.getTeacherInfor().get("username");
			password = this.unique_infor.getTeacherInfor().get("password");
		} catch (Exception error) {
			this.startActivity(new Intent().setClass(getApplicationContext(),
					LoginActivity.class));
		}
	}

}
