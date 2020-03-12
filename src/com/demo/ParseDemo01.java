package com.demo;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParseDemo01 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//获取解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//从解析工厂获取解析器
		SAXParser parser=factory.newSAXParser();
		//加载文档Document注册处理器
		//编写处理器
		PersonHandler handler=new PersonHandler();
		parser.parse(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("com/demo/person.xml")//流
						, handler);
		List<Person> persons=handler.getPersons();
		for(Person p : persons){
			System.out.println(p.getName()+"\t"+p.getAge());
		}
	} 

}
