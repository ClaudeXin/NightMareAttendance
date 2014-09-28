package com.claude.bean;

public class Configure {

	static public Configure getInstance() {
		return new Configure();
	}

	private Configure() {

	}

	final String host = "http://signals.hyit.edu.cn:8080/attendanceV3/";
	final String login_url = "querySubjectClient/";
	
	public String getLoginUrl(){
		return host+login_url;
	}
}