package com.server01;

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
			req=new Request(client.getInputStream());//«Î«Û
			rep=new Response(client.getOutputStream());//œÏ”¶	
		} catch (IOException e) {
			code=500;
			return;
		}
	}
	public void run() {
		Servlet serv=new Servlet();
		try {
			serv.Service(req, rep);//
			rep.pushToClient(code);
		} catch (IOException e) {
//			e.printStackTrace();
			try {
			rep.pushToClient(500);
			} catch (IOException e1) {
				e1.printStackTrace(); 
			}
		}
		
		CloseUtil.closeAll(client);
	}
}
