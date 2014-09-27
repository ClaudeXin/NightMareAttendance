package com.claude.bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {

	private HashMap<String, String> teacher = new HashMap<String, String>();
	private HashMap<String, String> cron = new HashMap<String, String>();
	private HashMap<String, String> lesson = new HashMap<String, String>();
	private HashMap<String, String> session = new HashMap<String, String>();
	private ArrayList<HashMap<String, String>> subject = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> student = new ArrayList<HashMap<String, String>>();

	private DocumentBuilderFactory factory;

	public HashMap<String, String> getTeacher() {
		return this.teacher;
	}

	public HashMap<String, String> getCron() {
		return this.cron;
	}

	public HashMap<String, String> getLesson() {
		return this.lesson;
	}

	public HashMap<String, String> getSession() {
		return this.session;
	}

	public ArrayList<HashMap<String, String>> getSubject() {
		return this.subject;
	}

	public ArrayList<HashMap<String, String>> getStudent() {
		return this.student;
	}

	// init factory
	public XmlParser() {
		factory = DocumentBuilderFactory.newInstance();

	}

	// interface parse cron
	public void parseCron(String path) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(path));
			NodeList node = document.getElementsByTagName("info");
			Element element = (Element) node.item(0);
			this.parsePartCron(element);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// interface parse teacher
	public void parseTeacher(String path) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(path));
			NodeList node = document.getElementsByTagName("teacher");
			Element element = (Element) node.item(0);
			this.parsePartTeacher(element);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// interface parse lesson
	public void parseLesson(String path) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(path));
			NodeList node = document.getElementsByTagName("lesson");
			Element element = (Element) node.item(0);
			this.parsePartLesson(element);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// interface parse session
	public void parseSession(String path) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(path));
			NodeList node = document.getElementsByTagName("session");
			Element element = (Element) node.item(0);
			this.parsePartSession(element);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// interface parse subject
	public void parseSubject(String path) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(path));
			NodeList node = document.getElementsByTagName("subject");
			int index = 0;
			while (node.getLength() != index) {
				this.subject.add(this.parsePartSubject((Element) node
						.item(index)));
				index++;
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// interface parse student
	public void parseStudent(String path) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(path));
			NodeList node = document.getElementsByTagName("student");
			int index = 0;
			while (node.getLength() != index) {
				this.student.add(this.parsePartStudent((Element) node
						.item(index)));
				index++;
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * parse cron data
	 * 
	 * @param element
	 */
	private void parsePartCron(Element element) {
		String cron_number = element.getElementsByTagName("cronNumber").item(0)
				.getFirstChild().getNodeValue();
		String execute_date = element.getElementsByTagName("executeDate")
				.item(0).getFirstChild().getNodeValue();
		String order_date = element.getElementsByTagName("orderDate").item(0)
				.getFirstChild().getNodeValue();
		String status = element.getElementsByTagName("status").item(0)
				.getFirstChild().getNodeValue();
		this.cron.put("cronNumber", cron_number);
		this.cron.put("executeDate", execute_date);
		this.cron.put("orderDate", order_date);
		this.cron.put("status", status);
		System.out.println(cron_number + execute_date + order_date + status);
	}

	/**
	 * parse teacher data
	 * 
	 * @param element
	 */
	private void parsePartTeacher(Element element) {
		String teacher_number = element.getElementsByTagName("teacherNumber")
				.item(0).getFirstChild().getNodeValue();
		String name = element.getElementsByTagName("name").item(0)
				.getFirstChild().getNodeValue();
		String department_number = element
				.getElementsByTagName("departmentNumber").item(0)
				.getFirstChild().getNodeValue();
		this.teacher.put("teacherNumber", teacher_number);
		this.teacher.put("name", name);
		this.teacher.put("departmentNumber", department_number);
		System.out.println(teacher_number + name + department_number);
	}

	/**
	 * parse lesson data
	 * 
	 * @param args
	 */
	private void parsePartLesson(Element element) {
		String lesson_number = element.getElementsByTagName("lessonNumber")
				.item(0).getFirstChild().getNodeValue();
		String start_time = element.getElementsByTagName("startTime").item(0)
				.getFirstChild().getNodeValue();
		String end_time = element.getElementsByTagName("endTime").item(0)
				.getFirstChild().getNodeValue();
		String classroom = element.getElementsByTagName("classroom").item(0)
				.getFirstChild().getNodeValue();
		this.lesson.put("lessonNumber", lesson_number);
		this.lesson.put("startTime", start_time);
		this.lesson.put("endTime", end_time);
		this.lesson.put("classroom", classroom);
		System.out.println(lesson_number + start_time + end_time + classroom);
	}

	/**
	 * parse session data
	 * 
	 * @param args
	 */
	private void parsePartSession(Element element) {
		String session_number = element.getElementsByTagName("sessionNumber")
				.item(0).getFirstChild().getNodeValue();
		String subject_number = element.getElementsByTagName("subjectNumber")
				.item(0).getFirstChild().getNodeValue();
		String class_name = element.getElementsByTagName("className").item(0)
				.getFirstChild().getNodeValue();
		String start_week = element.getElementsByTagName("startWeek").item(0)
				.getFirstChild().getNodeValue();
		String end_week = element.getElementsByTagName("endWeek").item(0)
				.getFirstChild().getNodeValue();
		session.put("sessionNumber", session_number);
		session.put("subjectNumber", subject_number);
		session.put("className", class_name);
		session.put("startWeek", start_week);
		session.put("endWeek", end_week);
		System.out.println(session_number + subject_number + class_name
				+ start_week + end_week);
	}

	/**
	 * parse subject data
	 * 
	 * @param args
	 */
	private HashMap<String, String> parsePartSubject(Element element) {
		String subject_number = element.getElementsByTagName("subjectNumber")
				.item(0).getFirstChild().getNodeValue();
		String name = element.getElementsByTagName("name").item(0)
				.getFirstChild().getNodeValue();
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("subjectNumber", subject_number);
		result.put("name", name);
		System.out.println(subject_number + name);
		return result;
	}

	/**
	 * parse student data
	 * 
	 * @param args
	 */
	private HashMap<String, String> parsePartStudent(Element element) {
		String student_number = element.getElementsByTagName("studentNumber")
				.item(0).getFirstChild().getNodeValue();
		String name = element.getElementsByTagName("name").item(0)
				.getFirstChild().getNodeValue();
		String card_mac = element.getElementsByTagName("cardMac").item(0)
				.getFirstChild().getNodeValue();
		String enter_year = element.getElementsByTagName("enterYear").item(0)
				.getFirstChild().getNodeValue();
		String card_id = element.getElementsByTagName("cardID").item(0)
				.getFirstChild().getNodeValue();
		String profession = element.getElementsByTagName("profession").item(0)
				.getFirstChild().getNodeValue();
		String sex = element.getElementsByTagName("sex").item(0)
				.getFirstChild().getNodeValue();
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("studentNumber", student_number);
		result.put("name", name);
		result.put("cardMac", card_mac);
		result.put("enterYear", enter_year);
		result.put("cardID", card_id);
		result.put("profession", profession);
		result.put("sex", sex);
		System.out.println(student_number + name + card_mac + enter_year
				+ card_id + profession + sex);
		return null;
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new XmlParser().parseStudent("queryCronClient.xml");
	}
}
