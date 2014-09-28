package com.claude.mainactivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.claude.bean.Configure;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class InitActivity extends Activity {

	private MyApp unique_infor;
	private String username;
	private String password;
	private Configure conf = Configure.getInstance();

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
					HttpResponse response = new DefaultHttpClient().execute(post);
					if(response.getStatusLine().getStatusCode() == 200){
						HttpEntity entity = response.getEntity();
						InputStream stream = entity.getContent();
					}else{
						
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void initCompone() {
		this.unique_infor = (MyApp) getApplication();
		HashMap<String, String> user = this.unique_infor.getTeacherInfor();
		username = user.get("username");
		password = user.get("password");
	}

	class LoginHandle extends Handler {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x001:
				try {
					Log.e("Clla", msg.obj.toString());
				} catch (Exception error) {
					Log.e("Clla", error.toString());
				}
				break;
			case 0x002:

				break;
			}
		}
	}
}
