package com.grafos.algorithm;

public class Graph {
	private final int VERTEX_QUANTITY;
	private Integer[][] matrix;
	
	public Graph(int vertexQuantity) throws Exception {
		validateVertex(vertexQuantity);
		
		VERTEX_QUANTITY = vertexQuantity;
		
		createMatrix();
	}
	
	public Integer[][] getMatrix() {
		return matrix;
	}
	
	private boolean validateVertex(int vertexQuantity) throws Exception {
		if (vertexQuantity < 0) {
			throw new Exception("A quantidade de vértices não pode ser menor que zero.");
		}
		
		return true;
	}
	
	private void createMatrix() {
		matrix = new Integer[VERTEX_QUANTITY][];
		
		for (int i = 0; i < VERTEX_QUANTITY; i++) {
			matrix[i] = new Integer[VERTEX_QUANTITY];
	
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = 0;
			}
		}
	}
}
