package com.server02;

import java.io.IOException;
import java.net.Socket;

import com.util.CloseUtil;

public class Dispatcher implements Runnable{
	private Socket client;
	private Request req;
	private Response rep;
	private int code=200;
	
	Dispatcher(Socket client) {
		this.client=client;
		try {
			req=new Request(client.getInputStream());//请求
			rep=new Response(client.getOutputStream());//响应	
		} catch (IOException e) {
			this.code=500;
			return;
		}
	}
	public void run() {
		try {
			Servlet serv=WebApp.getServlet(req.getUrl());//多态
			if(null==serv) 
				this.code=404;//找不到处理(资源)
			else//
				serv.service(req, rep);
			rep.pushToClient(code);
		} catch (Exception e) {
			this.code=500;
			try {
				rep.pushToClient(code);
			} catch (IOException e1) {
				e1.printStackTrace(); 
			}
		}
		CloseUtil.closeAll(client);
	}
}
