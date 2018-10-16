package com.grafos.model;

public class Path {
	Integer start;
	String cityStart;
	Integer finish;
	String cityFinish;
	Integer distance;
	
	public Path(Integer start, String cityStart, Integer finish, String cityFinish, Integer distance) {
		super();
		this.start = start;
		this.cityStart = cityStart;
		this.finish = finish;
		this.cityFinish = cityFinish;
		this.distance = distance;
	}
	
	
	
}
