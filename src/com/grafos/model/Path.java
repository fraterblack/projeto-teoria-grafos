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

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getCityStart() {
		return cityStart;
	}

	public void setCityStart(String cityStart) {
		this.cityStart = cityStart;
	}

	public Integer getFinish() {
		return finish;
	}

	public void setFinish(Integer finish) {
		this.finish = finish;
	}

	public String getCityFinish() {
		return cityFinish;
	}

	public void setCityFinish(String cityFinish) {
		this.cityFinish = cityFinish;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
	
	
}
