package com.claude.mainactivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
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

		new Thread() {
			public void run() {
				try {
					URL login_xml_file = new URL(
							getLoginUrl(username, password));
					InputStream xml_stream = login_xml_file.openStream();
					BufferedReader login_stream = new BufferedReader(
							new InputStreamReader(xml_stream));
					FileWriter login_file = new FileWriter(conf.getPath() + getTime() + ".xml");
					BufferedWriter writer_file_stream = new BufferedWriter(login_file);
					while(login_stream.ready()){
						writer_file_stream.write(login_stream.readLine());
						writer_file_stream.newLine();
					}
					writer_file_stream.close();
					login_stream.close();
					login_file.close();
					xml_stream.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	private String getLoginUrl(String name, String password) {
		String tmp_url = conf.getLoginUrl();
		tmp_url += String.format("?account=%s&password=%s", name, password);
		return tmp_url;
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
