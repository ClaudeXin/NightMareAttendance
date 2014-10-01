package com.claude.bean;

import java.util.Calendar;

import android.annotation.SuppressLint;

public class Configure {

	static public Configure getInstance() {
		return new Configure();
	}

	private Configure() {

	}

	private final String host = "http://signals.hyit.edu.cn:8080/attendanceV3/";
	private final String login_url = "querySubjectClient";
	private final String subject_url = "querySessionClient";
	private final String session_url = "queryCronClient";
	private final String saved_path = android.os.Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/Nightmare";

	public String getLoginUrl() {
		return this.host + this.login_url;
	}

	public String getSubjectUrl() {
		return this.host + this.subject_url;
	}

	public String getSessionUrl() {
		return this.host + this.session_url;
	}

	public String getPath() {
		return this.saved_path;
	}
	
	@SuppressLint("DefaultLocale")
	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String date = String.format("%d%d%d", year, month, day);
		String time = String.format("%d%d%d", hour, minute, second);
		return date + "_" + time;
	}
}