package com.server01;
//post
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
	private ServerSocket server;
	public static void main(String[] args) {
		Server1 server=new Server1();
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
			//读取字节
			int len=client.getInputStream().read(data);
			//接收客户端的请求信息
			String requestinfo=new String(data, 0, len).trim();
			System.out.println(requestinfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void stop(){
		
	}
}
