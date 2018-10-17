package com.grafos.algorithm;

public class Path {
	private Integer nodeOrigin;
	private Integer nodeDestin;
	private Integer distance;
	
	public Path(Integer nodeOrigin, Integer nodeDestin, Integer distance) {
		this.nodeOrigin = nodeOrigin;
		this.nodeDestin = nodeDestin;
		this.distance = distance;
	}

	public Integer getNodeOrigin() {
		return nodeOrigin;
	}

	public Integer getNodeDestin() {
		return nodeDestin;
	}

	public Integer getDistance() {
		return distance;
	}
}
