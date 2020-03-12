package com.server04;

import java.util.List;
//import java.io.IOException;
import java.util.Map;

//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

//import org.xml.sax.SAXException;

public class WebApp {
	private static ServletContext contxt;
	static{		
		try {
			SAXParserFactory factory=SAXParserFactory.newInstance();
			SAXParser parser;
			parser = factory.newSAXParser();
			WebHandler handler=new WebHandler();
			parser.parse(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("com/server04/web.xml"), handler);
			contxt=new ServletContext();
			Map<String, String> servlet=contxt.getServlet();
			for (Entity entity : handler.getEntityList()) {
				servlet.put(entity.getName(), entity.getClz());
			}	
			Map<String,String> mapping=contxt.getMapping();
			for(Mapping mapp : handler.getMappingList()){
				List<String> urls=mapp.getUrlpattern();
				for (String url : urls) {
					mapping.put(url,mapp.getName());
				}
			}
		} catch (Exception e) {
		}		
	}
	public static Servlet getServlet(String url) throws InstantiationException,
						IllegalAccessException, ClassNotFoundException{
		if(url==null||(url=url.trim()).equals(""))
			return null;
		String path=contxt.getServlet().get(contxt.getMapping().get(url));//
		return (Servlet)Class.forName(path).newInstance();
	}
}
