package com.claude.mainactivity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private LoginMessage handleMessage = new LoginMessage();

	private String host = "http://signals.hyit.edu.cn:8080/attendanceV3/";
	private String url_login = "querySubjectClient/";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("account", "test"));
		params.add(new BasicNameValuePair("password", "test123456"));

		new Thread() {
			public void run() {
				HttpPost http_post = new HttpPost(host + url_login);
				final Message msg = new Message();
				try {
					http_post.setEntity(new UrlEncodedFormEntity(params,
							HTTP.UTF_8));
					HttpResponse http_response = new DefaultHttpClient()
							.execute(http_post);
					if (http_response.getStatusLine().getStatusCode() == 200) {
						String str_result = EntityUtils.toString(http_response
								.getEntity());
						if (!str_result.equals("ERROR")) {
							msg.what = 0x0;
							msg.obj = str_result;
						} else {
							msg.what = 0x1;
							msg.obj = str_result;
						}
					}
				} catch (UnsupportedEncodingException e) {
					msg.what = 0x2;
					msg.obj = "UNSUPPORT DATA FORMAT";
				} catch (ClientProtocolException e) {
					msg.what = 0x3;
					msg.obj = "NO LINK";
				} catch (IOException e) {
					msg.what = 0x4;
					msg.obj = "UNHANLD EXCEPTION";
				} finally {
					handleMessage.sendMessage(msg);
				}
			}
		}.start();
	}

	/**
	 * inner class for handle thread msg
	 * 
	 * @author Claude
	 *
	 */

	@SuppressLint("HandlerLeak")
	class LoginMessage extends Handler {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x0:
//				Toast.makeText(getApplicationContext(), msg.obj.toString(),
//						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setClass(getApplicationContext(), InitActivity.class);
				getApplicationContext().startActivity(intent);
				break;
			case 0x1:
				Toast.makeText(getApplicationContext(), msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
				break;
			case 0x2:
				Toast.makeText(getApplicationContext(), msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
				break;
			case 0x3:
				Toast.makeText(getApplicationContext(), msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
				break;
			case 0x4:
				Toast.makeText(getApplicationContext(), msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
}
