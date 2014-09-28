package com.claude.mainactivity;

import java.util.HashMap;

import android.app.Application;

public class MyApp extends Application {

	private HashMap<String, String> teacher_infor = new HashMap<String, String>();

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
