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
	private String method;//请求方式
	private String url;//请求资源
	private Map<String, List<String>> parameterMap;//请求参数
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
		parseRequestInfo();//分析头信息
	}
	private void parseRequestInfo(){//分析请求信息
		if(null==requestInfo||(requestInfo=requestInfo.trim()).equals("")){
			return;
		}
		//分解: 请求方式   请求路径   请求参数(get可能存在,post可能在最后正文中)
		String parseString="";
		String firstLine=requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx=firstLine.indexOf("/");
		this.method=firstLine.substring(0,idx).trim();
		String urlString=firstLine.substring(idx,firstLine.indexOf("HTTP/")).trim();
		if(this.method.equalsIgnoreCase("get")){
			if(urlString.contains("?")){
				String urlArray[]=urlString.split("\\?");//分割
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
	}//将请求参数封装到Map中
	private void parseparams(String parseString){
		StringTokenizer token=new StringTokenizer(parseString, "&");
		while(token.hasMoreTokens()){
			String keyValue=token.nextToken();
			String[] keyValues=keyValue.split("=");
			if(keyValues.length==1){//只有键,没有值
				keyValues=Arrays.copyOf(keyValues, 2);//扩充
				keyValues[1]=null;
			}
			String key=keyValues[0].trim();
			String value=null==keyValues[1]?null:decode(keyValues[1].trim(),"gbk");
			//Map
			if(!parameterMap.containsKey(key)){//不存在,新建
				parameterMap.put(key, new ArrayList<>());
			}
			List<String> values=parameterMap.get(key);///取出对应List
			values.add(value);
		}
	}
	private String decode(String value,String code){//为中文解码
		try {
			return java.net.URLDecoder.decode(value,code);
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	public String[] getParameterValues(String name){//根据name获取多个值
		List<String> values=null;//
		if((values=parameterMap.get(name))==null)
			return null;
		else 
			return values.toArray(new String[0]);
	}
	public String getParameterValue(String name){//根据name获取单个值
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
