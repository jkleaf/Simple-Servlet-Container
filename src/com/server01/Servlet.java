package com.server01;

public class Servlet {
	public void Service(Request req,Response rep){
		rep.println("<html><head><title>Response</title>");
		rep.println("</head><body>");
		rep.println("��ӭ").println(req.getParameterValue("uname")).println("����");
		rep.println("</body></html>");
	}
}
