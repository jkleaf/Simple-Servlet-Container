package com.server01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.util.CloseUtil;


//��װ��Ӧ��Ϣ
public class Response {
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	private StringBuilder headInfo;//ͷ��Ϣ
	private int len;//���ĳ���
	private StringBuilder content;//����
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
	//��������
	public Response print(String info){
		content.append(info);
		len+=info.getBytes().length;
		return this;
	}
	//��������+�س�
	public Response println(String info){
		content.append(info).append(CRLF);
		len+=(info+CRLF).getBytes().length;
		return this;
	}
	//��Ӧͷ
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
		//���ĳ���:�ֽڳ���
		headInfo.append("Content-lenth:").append(len).append(CRLF);
		//����֮ǰ
		headInfo.append(CRLF);
	}
	//���͵��ͻ���
	void pushToClient(int code) throws IOException{
		if(null==headInfo) code=500;
		creatheadInfo(code);
		bw.append(headInfo.toString()).append(content.toString());
		bw.flush();
		close();//
	} 
	public void close(){//�ر�
		CloseUtil.closeAll(bw);
	}
}
