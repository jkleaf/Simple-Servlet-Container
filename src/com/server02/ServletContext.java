package com.server02;

import java.util.HashMap;
import java.util.Map;
//иообнд
public class ServletContext {
	private Map<String, Servlet> servlet;//login-->LoginServlet
	private Map<String, String> mapping;//url:(lg/login)-->login
	ServletContext() {
		servlet=new HashMap<>();
		mapping=new HashMap<>();
	}
	public Map<String, Servlet> getServlet() {
		return servlet;
	}
	public void setServlet(Map<String, Servlet> servlet) {
		this.servlet = servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
}
