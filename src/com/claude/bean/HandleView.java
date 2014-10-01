package com.claude.bean;
import com.claude.mainactivity.LoginActivity;
import com.claude.mainactivity.MainActivity;
import android.annotation.SuppressLint;
import android.content.*;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class HandleView {
	
	private Context context;
	
	public HandleView(Context context){
		this.context = context;
	}
	
	public LoginHandle getLoginHandle(){
		return new LoginHandle();
	}
	
	@SuppressLint("HandlerLeak")
	public class LoginHandle extends Handler {
		public void handleMessage(Message msg) {
			Intent goto_login = new Intent();
			switch (msg.what) {
			case 0x000:
				Log.e("NFC", "SUCCESS");
				Intent goto_main = new Intent();
				goto_main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				goto_main.setClass(context, MainActivity.class);
				context.startActivity(goto_main);
				break;
			case 0x001:
				Log.e("NFC", "FAILE");
				goto_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				goto_login.setClass(context,
						LoginActivity.class);
				context.startActivity(goto_login);
				break;
			}
		}
	}
}
