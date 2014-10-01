package com.claude.mainactivity;

import java.io.File;
import java.util.HashMap;

import com.claude.bean.Configure;

import android.app.Application;

public class MyApp extends Application {

	public void onCreate() {
		super.onCreate();
		File path = new File(Configure.getInstance().getPath());
		if (!path.exists()) {
			path.mkdirs();
		}
	}

	private HashMap<String, String> teacher_infor = new HashMap<String, String>();

	private String cur_login_file;

	public void setCurLoginFile(String file_path) {
		this.cur_login_file = file_path;
	}

	public String getCurLoginFile() {
		return this.cur_login_file;
	}

	public void addTeacherInfor(String[] args) {
		if (args.length % 2 != 0) {
			return;
		}
		for (int i = 0; i < args.length; i = i + 2) {
			this.teacher_infor.put(args[i], args[i + 1]);
		}
	}

	public HashMap<String, String> getTeacherInfor() {
		return this.teacher_infor;
	}
}
