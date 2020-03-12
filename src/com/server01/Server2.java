package com.server01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server2 {
	private ServerSocket server;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	public static void main(String[] args) {
		Server2 server=new Server2();
		server.start();
		server.stop();
	}

	public void start(){
		try {
			server=new ServerSocket(6666);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void receive(){//private
		try {
			Socket client=server.accept();
			byte[] data=new byte[20480];
			int len=client.getInputStream().read(data);
			//接收客户端的请求信息
			String requestinfo=new String(data, 0, len).trim();
			System.out.println(requestinfo);
			//响应	
			StringBuilder responseContext=new StringBuilder();
			responseContext.append("<html><head><title>"
					+ "Response</title></head><body>"
					+ "Hello Server!</body></html>");
			
			StringBuilder response=new StringBuilder();
			//HTTP协议版本,状态码,描述
			response.append("HTTP/1.1").append(BLANK).append(200).
				append(BLANK).append("OK").append(CRLF);
//			response.append("HTTP/1.1 ").append(200).
//					append(" OK").append(CRLF);
			//响应头
			response.append("Server:ijk Server/0.1.1").append(CRLF);
			response.append("Date:").append(new Date()).append(CRLF);
			response.append("Content-type:text/html;charset=GBK").append(CRLF);
			//正文长度:字节长度
			response.append("Content-lenth:").
				append(responseContext.toString().getBytes().length).append(CRLF);
			//正文之前
			response.append(CRLF);
			//正文
			response.append(responseContext);
			BufferedWriter bw=new BufferedWriter(
					new OutputStreamWriter(client.getOutputStream()));
			bw.write(response.toString());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void stop(){
		
	}
}
