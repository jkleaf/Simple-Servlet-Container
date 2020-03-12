package com.server04;

import java.util.ArrayList;
import java.util.List;

public class Mapping {
	private String name;
	private List<String> urlpattern;
	
	public Mapping() {
		urlpattern=new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getUrlpattern() {
		return urlpattern;
	}
	public void setUrlpattern(List<String> urlpattern) {
		this.urlpattern = urlpattern;
	}
	
}
