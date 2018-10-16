package com.grafos.model;

public class City {
	int code;
	String name;
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public City(int code, String name) {
		this.code = code;
		this.name = name;
	}
}
