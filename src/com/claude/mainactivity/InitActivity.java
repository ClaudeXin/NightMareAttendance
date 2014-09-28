package com.claude.mainactivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.claude.bean.Configure;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

@SuppressLint({ "DefaultLocale", "HandlerLeak" })
public class InitActivity extends Activity {

	private MyApp unique_infor;
	private String username;
	private String password;
	private Configure conf = Configure.getInstance();
	private LoginHandle handle_login = new LoginHandle();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_init);
		this.initCompone();

		final ArrayList<NameValuePair> user = new ArrayList<NameValuePair>();
		user.add(new BasicNameValuePair("account", username));
		user.add(new BasicNameValuePair("password", password));
		new Thread() {
			public void run() {
				HttpPost post = new HttpPost(conf.getLoginUrl());
				try {
					post.setEntity(new UrlEncodedFormEntity(user, HTTP.UTF_8));
					HttpResponse response = new DefaultHttpClient()
							.execute(post);
					if (response.getStatusLine().getStatusCode() == 200) {
						HttpEntity login_entity = response.getEntity();
						InputStream stream = login_entity.getContent();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(stream, "utf-8"));
						FileWriter xmlFile = new FileWriter(conf.getPath()
								+ getTime() + ".xml");
						BufferedWriter writer = new BufferedWriter(xmlFile);
						while (reader.ready()) {
							writer.write(reader.readLine());
						}
						writer.close();
						reader.close();
						xmlFile.close();
						stream.close();
						handle_login.sendEmptyMessage(0x000);
					} else {
						handle_login.sendEmptyMessage(0x001);
					}
				} catch (UnsupportedEncodingException e) {
					handle_login.sendEmptyMessage(0x001);
				} catch (ClientProtocolException e) {
					handle_login.sendEmptyMessage(0x001);
				} catch (IOException e) {
					handle_login.sendEmptyMessage(0x001);
				}
			}
		}.start();
	}

	public void initCompone() {
		unique_infor = (MyApp) getApplication();
		try {
			username = this.unique_infor.getTeacherInfor().get("username");
			password = this.unique_infor.getTeacherInfor().get("password");
		} catch (Exception error) {
			this.startActivity(new Intent().setClass(getApplicationContext(),
					LoginActivity.class));
		}

	}

	@SuppressLint("DefaultLocale")
	private String getTime() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String date = String.format("%d/%d/%d", year, month, day);
		String time = String.format("%d:%d:%d", hour, minute, second);
		return date + "-" + time;
	}

	class LoginHandle extends Handler {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x000:
				Log.e("NFC", "SUCCESS");
				Intent goto_main = new Intent();
				goto_main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				goto_main.setClass(getApplicationContext(), MainActivity.class);
				getApplication().startActivity(goto_main);
				break;
			case 0x001:
				Log.e("NFC", "FAILE");
				Intent goto_login = new Intent();
				goto_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				goto_login.setClass(getApplicationContext(),
						LoginActivity.class);
				getApplication().startActivity(goto_login);
				break;
			}
		}
	}
}
