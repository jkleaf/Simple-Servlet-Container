package com.server01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.util.CloseUtil;


//封装响应信息
public class Response {
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	private StringBuilder headInfo;//头信息
	private int len;//正文长度
	private StringBuilder content;//正文
	private BufferedWriter bw;
	public Response() {
		headInfo=new StringBuilder();
		content=new StringBuilder();
		len=0;
	}
	public Response(OutputStream os){
		this();
		bw=new BufferedWriter(new OutputStreamWriter(os));
	}
	//构建正文
	public Response print(String info){
		content.append(info);
		len+=info.getBytes().length;
		return this;
	}
	//构建正文+回车
	public Response println(String info){
		content.append(info).append(CRLF);
		len+=(info+CRLF).getBytes().length;
		return this;
	}
	//响应头
	private void creatheadInfo(int code){
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch (code) {
		case 200: 
			headInfo.append("OK");
			break;
		case 404:
			headInfo.append("NOT FOUND");
			break;
		case 500:
			headInfo.append("SERVER ERROR");
			break;
		}
		headInfo.append(CRLF);
		headInfo.append("Server:ijk Server/0.1.1").append(CRLF);	
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
		//正文长度:字节长度
		headInfo.append("Content-lenth:").append(len).append(CRLF);
		//正文之前
		headInfo.append(CRLF);
	}
	//推送到客户端
	void pushToClient(int code) throws IOException{
		if(null==headInfo) code=500;
		creatheadInfo(code);
		bw.append(headInfo.toString()).append(content.toString());
		bw.flush();
		close();//
	} 
	public void close(){//关闭
		CloseUtil.closeAll(bw);
	}
}
