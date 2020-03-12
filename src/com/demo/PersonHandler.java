package com.demo;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
//存储对象
public class PersonHandler extends DefaultHandler{
	private List<Person> persons;
	private Person person;
	private String tag;//记录标签名 
	public static void main(String[] args) {
		
	}

	@Override
	public void startDocument() throws SAXException {
//		System.out.println("文档处理开始");
		persons=new ArrayList<Person>();
	
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//		System.out.println("开始一个元素"+qName);
		if(null!=qName) tag=qName;
		if(null!=qName&&qName.equals("person"))
			person=new Person();
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
//		System.out.println(new String(ch,start,length));
		String str=new String(ch,start,length);
		if(null!=tag&&tag.equals("name")){
			person.setName(str);
		}else if(null!=tag&&tag.equals("age")){
			person.setAge(Integer.valueOf(str));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
//		System.out.println("结束一个元素"+qName);
		if(qName.equals("person")){//person标签结束
			this.persons.add(person);
		}
		tag=null;
	}
	
	@Override
	public void endDocument() throws SAXException {
//		System.out.println("文档处理结束");
	}

	public List<Person> getPersons() {
		return persons;
	}

}
