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

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LoginActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("account", "test"));
		params.add(new BasicNameValuePair("password", "test123456"));

		new Thread() {
			public void run() {
				HttpPost http_post = new HttpPost("");
				try {
					http_post.setEntity(new UrlEncodedFormEntity(params,
							HTTP.UTF_8));
					HttpResponse http_response = new DefaultHttpClient()
							.execute(http_post);
					if (http_response.getStatusLine().getStatusCode() == 200) {
						String str_result = EntityUtils.toString(http_response
								.getEntity());
						if (!str_result.equals("ERROR")) {
							
						} else {

						}
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	class LoginMessage extends Handler{
		public void handleMessage(Message msg){
			switch(msg.what){
			
			}
		}
	}
}
