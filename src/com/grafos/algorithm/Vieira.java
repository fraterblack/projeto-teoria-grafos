package com.grafos.algorithm;

import java.util.TreeMap;
import java.util.Map.Entry;


public class Vieira extends Graph {
	
	

	// Results
	private Integer distanceToDestination;
	private TreeMap<Integer, Edge> pathToDestination;
	private boolean founded = false;
	
	//Auxiliar
	Integer[][] matrixAuxiliar;
	
	
	public Vieira(int vertexQuantity) throws Exception {
		super(vertexQuantity);
	}
	
	//The start of the class
	public void findSmallestPath(int origin, int destin) throws Exception {
		matrixAuxiliar = getMatrix();
		distanceToDestination = Integer.MAX_VALUE;
		
		recursivePathFounder(origin,destin,new TreeMap<Integer, Edge>(), 0);
		
		if(!founded) {
			throw new Exception("Não encontradaso.");
		}
		

	}

	private Integer index = 100;
	
	public void recursivePathFounder(Integer origin, Integer destin, TreeMap<Integer, Edge> paths, Integer sum) {
		
		if(origin == destin) {
			founded = true;
			
			/*  Testss
			System.out.println("Chegou");
			
			paths.forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});
			*/
			
			if(sum < this.distanceToDestination) {
				
				/* tava passando memoria por isso tava bugando
				 this.pathToDestination = paths;
				 */
				this.distanceToDestination = sum;
				
				pathToDestination = new TreeMap<Integer, Edge>();
				
				Integer localIndex =0;
				for (Entry<Integer, Edge> entry : paths.entrySet()) {
					
					pathToDestination.put(localIndex,entry.getValue());
					localIndex++;
				}
				
				//System.out.println("Menor");
				
			}
			//System.out.println("=============");
			return;
		}

		Integer[] values = new Integer[matrixAuxiliar.length];
		for(Integer i=0;i<matrixAuxiliar.length;i++) {
			values[i] = matrixAuxiliar[origin][i];
			matrixAuxiliar[origin][i] = 0;
			matrixAuxiliar[i][origin] = 0;
			
		}
		
		for(Integer i=0;i<matrixAuxiliar.length;i++) {
			//existe caminho
			if(values[i] > 0) {
				Edge edge = new Edge(origin,i,values[i]);

				
				paths.put(index, edge);
				index++;
				
				recursivePathFounder(i,destin,paths,sum + edge.getAccumulatedDistance());
				
				paths.remove(index - 1);
				
			}
			
		}
		
		
		return;
	}
	
	

	public Integer getDistanceToDestination() {
		return distanceToDestination;
	}
	
	public TreeMap<Integer, Edge> getPathToDestination() {
		return pathToDestination;
	}
	
	public static void main(String[] args) {

		try {
			Vieira dij = new Vieira(6);
			/*
			5 7
			0 1 4
			0 2 8
			1 3 5
			1 2 2
			2 3 5
			2 4 9
			3 4 4
			*/
			dij.insertEdge(0, 1, 4);
			dij.insertEdge(0, 2, 8);
			dij.insertEdge(1, 3, 5);
			dij.insertEdge(1, 2, 2);
			dij.insertEdge(2, 3, 5);
			dij.insertEdge(2, 4, 9);
			dij.insertEdge(3, 4, 4);

			
			
			
			//Case 1
			dij.findSmallestPath(0, 4);
			System.out.println("########### Test Case ##########");
			
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});

			System.out.println("Distancia total: " + dij.getDistanceToDestination()); //Esperado: Total 10
			System.out.println("test");
			///////
			/*
			//Case 2
			dij.findSmallestPath(3, 5);
			
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});

			System.out.println(dij.getDistanceToDestination()); //Esperado: Total 5*/
			///////
		} catch (Exception ex) {
			if (ex.getMessage() == null)
				System.out.println("Ocorreu um erro de " + ex + " no main");
			else
				System.out.println(ex.getMessage() + "XXX");
		}

	}
	
}
