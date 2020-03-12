package com.server04;

import java.io.IOException;
import java.net.ServerSocket;
	
import com.util.CloseUtil;

public class Server {
	private ServerSocket server;
	boolean isShutdown=false;
	public static void main(String[] args) {
		Server server=new Server();
		server.start();
		server.stop();
	}
	public void start(){//Ĭ�϶˿�����
		start(6666);
	}
	public void start(int port){//ָ���˿�����
		try {
			server=new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			stop();
		}
	}
	private void receive(){//private
		try {
			while(!isShutdown)
				new Thread(new Dispatcher(server.accept())).start();
		} catch (IOException e) {
			stop();
		}
	}
	public void stop(){
		isShutdown=true;
		CloseUtil.closeAll(server);
	}
}
