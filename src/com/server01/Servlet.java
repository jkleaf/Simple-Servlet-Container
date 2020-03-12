package com.server01;

public class Servlet {
	public void Service(Request req,Response rep){
		rep.println("<html><head><title>Response</title>");
		rep.println("</head><body>");
		rep.println("ª∂”≠").println(req.getParameterValue("uname")).println("ªÿ¿¥");
		rep.println("</body></html>");
	}
}
