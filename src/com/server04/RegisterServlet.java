package com.server04;

public class RegisterServlet extends Servlet{

	@Override
	public void doGet(Request req, Response rep) throws Exception {
		
	}

	@Override
	public void doPost(Request req, Response rep) throws Exception {
		rep.println("<html><head><title>Register</title>");
		rep.println("</head><body>");
		rep.println("�����û���Ϊ:"+req.getParameterValue("uname"));
		rep.println("</body></html>");
	}
	
}
