package com.grafos.model;

public class Path {
	String originCode;
	String originName;
	String destinationCode;
	String destinationName;
	Integer distance;
	
	public Path(String originCode, String originName, String destinationCode, String destinationName, Integer distance) {
		this.originCode = originCode;
		this.originName = originName;
		this.destinationCode = destinationCode;
		this.destinationName = destinationName;
		this.distance = distance;
	}

	public String getOriginCode() {
		return originCode;
	}

	public String getOriginName() {
		return originName;
	}

	public String getDestinationCode() {
		return destinationCode;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public Integer getDistance() {
		return distance;
	}
}
