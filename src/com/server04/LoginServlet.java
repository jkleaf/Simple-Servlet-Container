package com.server04;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request req,Response rep) throws Exception {
		String name=req.getParameterValue("uname");
		String pwd=req.getParameterValue("pwd");
		if(CheckLogin(name, pwd))
			rep.println("��¼�ɹ�");
		else 	
			rep.println("��¼ʧ��");
	}
	private boolean CheckLogin(String name,String pwd){
		return name.equals("ijk")&&pwd.equals("54321");
	}
	@Override
	public void doPost(Request req,Response rep) throws Exception {
		
	}
	
}
