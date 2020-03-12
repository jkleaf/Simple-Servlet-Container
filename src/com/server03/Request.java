package com.server03;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Request {
	private String method;//����ʽ
	private String url;//������Դ
	private Map<String, List<String>> parameterMap;//�������
	public static final String CRLF="\r\n";
	@SuppressWarnings("unused")
	private InputStream is;
	private String requestInfo;
	
	public Request(){
		method="";
		url="";
		parameterMap=new HashMap<>();
		requestInfo="";
	}
	public Request(InputStream is){
		this();
		this.is=is;
		try {
			byte[] data=new byte[20480];
			int len=is.read(data);
			requestInfo=new String(data, 0 , len);
		} catch (IOException e) {
			return;
		}
		parseRequestInfo();//����ͷ��Ϣ
	}
	private void parseRequestInfo(){//����������Ϣ
		if(null==requestInfo||(requestInfo=requestInfo.trim()).equals("")){
			return;
		}
		//�ֽ�: ����ʽ   ����·��   �������(get���ܴ���,post���������������)
		String parseString="";
		String firstLine=requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx=firstLine.indexOf("/");
		this.method=firstLine.substring(0,idx).trim();
		String urlString=firstLine.substring(idx,firstLine.indexOf("HTTP/")).trim();
		if(this.method.equalsIgnoreCase("get")){
			if(urlString.contains("?")){
				String urlArray[]=urlString.split("\\?");//�ָ�
				this.url=urlArray[0];
				parseString=urlArray[1];
			}else {
				this.url=urlString;				
			}
		}else if (this.method.equalsIgnoreCase("post")) {
			this.url=urlString;
			parseString=requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
		}																	//
		if(parseString.equals("")) return;
		parseparams(parseString);
	}//�����������װ��Map��
	private void parseparams(String parseString){
		StringTokenizer token=new StringTokenizer(parseString, "&");
		while(token.hasMoreTokens()){
			String keyValue=token.nextToken();
			String[] keyValues=keyValue.split("=");
			if(keyValues.length==1){//ֻ�м�,û��ֵ
				keyValues=Arrays.copyOf(keyValues, 2);//����
				keyValues[1]=null;
			}
			String key=keyValues[0].trim();
			String value=null==keyValues[1]?null:decode(keyValues[1].trim(),"gbk");
			//Map
			if(!parameterMap.containsKey(key)){//������,�½�
				parameterMap.put(key, new ArrayList<>());
			}
			List<String> values=parameterMap.get(key);///ȡ����ӦList
			values.add(value);
		}
	}
	private String decode(String value,String code){//Ϊ���Ľ���
		try {
			return java.net.URLDecoder.decode(value,code);
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	public String[] getParameterValues(String name){//����name��ȡ���ֵ
		List<String> values=null;//
		if((values=parameterMap.get(name))==null)
			return null;
		else 
			return values.toArray(new String[0]);
	}
	public String getParameterValue(String name){//����name��ȡ����ֵ
		String[] values=getParameterValues(name);
		if(values==null) return null;
		else return values[0];
	}
	public String getUrl() {//getter
		return url;
	}
	public void setUrl(String url) {//setter
		this.url = url;
	}
}
