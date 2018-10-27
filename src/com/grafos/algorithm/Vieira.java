package com.grafos.algorithm;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Vieira extends Graph {
	Integer[][] matrixAuxiliar;
	Map<Integer, TreeMap<Integer, Edge>> memory;
	
	// Results
	private int distanceToDestination = 0;
	private TreeMap<Integer, Edge> pathToDestination;
	
	public Vieira(int vertexQuantity) throws Exception {
		super(vertexQuantity);
	}
	
	//The start of the class
	public void findSmallestPath(int origin, int destin) throws Exception {
		matrixAuxiliar = getMatrix();
		
		/////NEW Map<Integer, TreeMap<Integer, Edge>> PRECISA?
		
		TreeMap<Integer, Edge> pathToDestination = 
				recursiveSmallestPath(origin,destin,new TreeMap<Integer, Edge>());
		
		distanceToDestination = sumDistanceOfPaths(pathToDestination);
		
	}
	
	private TreeMap<Integer, Edge> recursiveSmallestPath(Integer origin, int destin, TreeMap<Integer, Edge> paths) {
		
		//Caso chegou ao destino
		if(origin == destin) {
			return paths;
		}
		
		//Caso já tenha cido passado por esse nó
		if(memory.containsKey(origin)) {
			
			for (Entry<Integer, Edge> entry : memory.get(origin).entrySet()) {
				paths.put(entry.getKey(),entry.getValue());
			}
			
			return paths;
		}
		
		Integer smallestValue = Integer.MAX_VALUE;
		Integer smallestValueIndex = -1;
		
		for(int i=0;i<matrixAuxiliar.length;i++) {
			if(matrixAuxiliar[origin][i] > 0) {
				
				memory.put(i, recursiveSmallestPath(i, destin, paths));
				
				Integer distance = sumDistanceOfPaths(memory.get(i));
				if(smallestValue > distance) {
					smallestValue = distance;
					smallestValueIndex = i;
				}

			}
		}
		
		//Não achou
		if(smallestValueIndex == -1) {
			 
		}
		else {
			paths.put(origin, new Edge(origin,smallestValueIndex,smallestValue));
			return recursiveSmallestPath(smallestValueIndex,destin,paths);
		}
		
		
		
		return null;
	}
	
	
	public Integer getDistanceToDestination() {
		return distanceToDestination;
	}
	
	public TreeMap<Integer, Edge> getPathToDestination() {
		return pathToDestination;
	}
	
	
	
	
	public static void main(String[] args) {

		try {
			Dijkstra dij = new Dijkstra(8);

			dij.insertEdge(0, 1, 4);
			dij.insertEdge(0, 2, 4);
			dij.insertEdge(0, 4, 2);
			dij.insertEdge(0, 6, 6);

			dij.insertEdge(1, 0, 4);
			dij.insertEdge(1, 2, 2);
			dij.insertEdge(1, 3, 7);
			dij.insertEdge(1, 6, 3);

			dij.insertEdge(2, 0, 4);
			dij.insertEdge(2, 1, 2);
			dij.insertEdge(2, 3, 3);
			dij.insertEdge(2, 4, 2);

			dij.insertEdge(3, 1, 7);
			dij.insertEdge(3, 2, 3);


			dij.insertEdge(4, 0, 2);
			dij.insertEdge(4, 2, 2);
			dij.insertEdge(4, 5, 7);

			dij.insertEdge(6, 0, 6);
			dij.insertEdge(6, 1, 3);

			dij.insertEdge(7, 3, 2);
			dij.insertEdge(7, 4, 4);
			dij.insertEdge(7, 5, 3);
			
			System.out.println("########### Test Case ##########");
			
			//Case 1
			dij.findSmallestPath(6, 7);
			
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});

			System.out.println(dij.getDistanceToDestination()); //Esperado: Total 10
			System.out.println("test");
			///////
			
			//Case 2
			dij.findSmallestPath(3, 5);
			
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});

			System.out.println(dij.getDistanceToDestination()); //Esperado: Total 5
			///////
		} catch (Exception ex) {
			if (ex.getMessage() == null)
				System.out.println("Ocorreu um erro de " + ex + " no main");
			else
				System.out.println(ex.getMessage() + "XXX");
		}

	}
	
}
