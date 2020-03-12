package com.server04;

import java.util.HashMap;
import java.util.Map;
//иообнд
public class ServletContext {
	private Map<String, String> servlet;//login-->LoginServlet
	private Map<String, String> mapping;//url:(lg/login)-->login
	ServletContext() {
		servlet=new HashMap<>();
		mapping=new HashMap<>();
	}
	public Map<String, String> getServlet() {
		return servlet;
	}
	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
}
