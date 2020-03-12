package com.server03;

import java.util.Map;

public class WebApp {
	private static ServletContext contxt;
	static{
		contxt=new ServletContext();
		Map<String,String> mapping=contxt.getMapping();
		mapping.put("/login", "login");
		mapping.put("/log", "login");
		mapping.put("/reg", "register");
		mapping.put("/register", "register");
		Map<String, String> servlet=contxt.getServlet();
		servlet.put("login", "com.server03.LoginServlet");
		servlet.put("register", "com.server03.RegisterServlet");
	}
	public static Servlet getServlet(String url) throws InstantiationException,
						IllegalAccessException, ClassNotFoundException{
		if(url==null||(url=url.trim()).equals(""))
			return null;
		String path=contxt.getServlet().get(contxt.getMapping().get(url));//
		return (Servlet)Class.forName(path).newInstance();
	}
}
