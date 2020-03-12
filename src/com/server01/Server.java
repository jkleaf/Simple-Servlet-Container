package com.server01;
//get
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket server;
	public static void main(String[] args) {
		Server server=new Server();
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
	@SuppressWarnings("unused")
	private void receive(){//private
		try {
			Socket client=server.accept();
			BufferedReader br=new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			StringBuilder sb=new StringBuilder();
			String msg=null;
			while((msg=br.readLine()).length()>0){
				sb.append(msg);
				sb.append("\r\n");
				if(null==msg) break;
			}
			//接收客户端的请求信息
			String requestinfo=sb.toString().trim();
			System.out.println(requestinfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void stop(){
		
	}
}
