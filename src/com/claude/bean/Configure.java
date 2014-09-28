package com.claude.bean;

public class Configure {

	static public Configure getInstance() {
		return new Configure();
	}

	private Configure() {

	}

	private final String host = "http://signals.hyit.edu.cn:8080/attendanceV3/";
	private final String login_url = "querySubjectClient";
	private final String saved_path = android.os.Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/Nightmare";

	public String getLoginUrl() {
		return this.host + this.login_url;
	}
	
	public String getPath(){
		return this.saved_path;
	}
}