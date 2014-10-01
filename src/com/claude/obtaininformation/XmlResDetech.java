package com.claude.obtaininformation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.claude.bean.Configure;
import com.claude.handle.ErrorArgException;

public class XmlResDetech {

	private String type;
	private String cur_url;
	private String[] args;
	private Configure conf = Configure.getInstance();
	private boolean staus = false;

	public XmlResDetech(String type, String[] args, String file_name)
			throws ErrorArgException, IOException {
		this.type = type;
		this.args = args;
		this.switchType();
		this.getXmlFile(file_name);
	}

	private void switchType() throws ErrorArgException {

		if (this.type.equals("login")) {
			this.cur_url = this.getLoginUrl(this.args);
		} else if (this.type.equals("subject")) {
			this.cur_url = this.getSubjectUrl(this.args);
		} else if (this.type.equals("session")) {
			this.cur_url = this.getSessionUrl(this.args);
		} else {
			throw new ErrorArgException();
		}
	}

	private void getXmlFile(String file_name) throws IOException {
		URL xml_post = new URL(this.cur_url);
		InputStream xml_stream = xml_post.openStream();
		BufferedReader stream = new BufferedReader(new InputStreamReader(
				xml_stream));
		FileWriter file = new FileWriter(file_name);
		BufferedWriter writer_file_stream = new BufferedWriter(file);
		if (stream.ready()) {
			do {
				writer_file_stream.write(stream.readLine());
				writer_file_stream.newLine();
			} while (stream.ready());
			this.staus = true;
		} else {
			this.staus = false;
		}
		writer_file_stream.close();
		stream.close();
		file.close();
		xml_stream.close();
	}

	private String getLoginUrl(String[] args) {
		String tmp_url = conf.getLoginUrl();
		switch (args.length) {
		case 2:
			tmp_url += String.format("?account=%s&password=%s", args[0],
					args[1]);
			break;
		case 1:
			tmp_url += String.format("?cardMac=%s", args[0]);
			break;
		}
		return tmp_url;
	}

	private String getSubjectUrl(String[] args) {
		String tmp_url = conf.getSubjectUrl();
		tmp_url += String.format("?teacherNumber=%s&subjectNumber=%s", args[0],
				args[1]);
		return tmp_url;
	}

	private String getSessionUrl(String[] args) {
		String tmp_url = conf.getSessionUrl();
		tmp_url += String.format("?teacherNumber=%s&sessionNumber=%s", args[0],
				args[1]);
		return tmp_url;
	}

	public boolean getStaus() {
		return this.staus;
	}
}
