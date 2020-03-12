package com.server04;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHandler extends DefaultHandler{
	private List<Entity> entityList;
	private List<Mapping> mappingList;
	private Entity entity;
	private Mapping mapping;
	private String tag;
	private boolean isMap;
	
	@Override//解析开始
	public void startDocument() throws SAXException {		
		entityList=new ArrayList<>();
		mappingList=new ArrayList<>();
	}
	
	@Override//开始元素
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(null!=qName){
			tag=qName;
			if(qName.equals("servlet")){
				entity=new Entity();
				isMap=false;
			}else if(qName.equals("servlet-mapping")){
				mapping=new Mapping();
				isMap=true;
			}
		}
	}
	
	@Override//处理内容
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(null!=tag){
			String str=new String(ch, start, length);
			if(!isMap){
				if(tag.equals("servlet-name")){
					entity.setName(str);
				}else if(tag.equals("servlet-class")){
					entity.setClz(str);
				}
			}else{
				if(tag.equals("servlet-name")){
					mapping.setName(str);
				}else if(tag.equals("url-pattern")){
					mapping.getUrlpattern().add(str);//get
				}
			}
		}
	}
	
	@Override//结束元素
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("servlet")){
			entityList.add(entity);
		}else if(qName.equals("servlet-mapping")){
			mappingList.add(mapping);
		}	
		tag=null;
	}
	@Override//解析结束
	public void endDocument() throws SAXException {
		
	}

	public List<Entity> getEntityList() {//return
		return entityList;
	}

	public List<Mapping> getMappingList() {//return
		return mappingList;
	}
	
}
