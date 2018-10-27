package com.grafos.algorithm;

import java.util.TreeMap;


public class Vieira extends Graph {
	Integer[][] matrixAuxiliar;
	TreeMap<Integer, TreeMap<Integer, Edge>> memory;

	// Results
	private int distanceToDestination = 0;
	private TreeMap<Integer, Edge> pathToDestination;
	
	public Vieira(int vertexQuantity) throws Exception {
		super(vertexQuantity);
	}
	
	//The start of the class
	public void findSmallestPath(int origin, int destin) throws Exception {
		matrixAuxiliar = getMatrix();
		
		for(int i =0;i<matrixAuxiliar.length;i++) {
			for(int j=0;j<matrixAuxiliar.length;j++) {
				System.out.print(matrixAuxiliar[i][j] + " ");
			}
			System.out.println("");
		}
		
		/////NEW Map<Integer, TreeMap<Integer, Edge>> PRECISA?
		memory = new TreeMap<Integer, TreeMap<Integer, Edge>>();
		
		this.pathToDestination = 
				recursiveSmallestPath(origin,destin,new TreeMap<Integer, Edge>());
		

		if(pathToDestination == null) {
			throw new Exception("Não encontradaso.");
		}

		this.distanceToDestination = sumDistanceOfPaths(pathToDestination);
	}

	private Integer helper = 1000; //GAMBIARRA ROGER
	private TreeMap<Integer, Edge> recursiveSmallestPath(Integer origin, int destin, TreeMap<Integer, Edge> paths) {

		//Caso chegou ao destino
		if(origin == destin) {
			return paths;
		}
		
		//Caso já tenha cido passado por esse nó
		if(memory.containsKey(origin)) {
			return memory.get(origin);
		}
		
		Integer smallestValue = Integer.MAX_VALUE; //Seta para o maior inteiro possivel
		Integer smallestValueIndex = -1;
		Integer the_rect = -1;
		for(int i=0;i<matrixAuxiliar.length;i++) {
			
			if(matrixAuxiliar[origin][i] > 0) {
				
				//Cria o caminho
				Edge edge = new Edge(origin, i, matrixAuxiliar[origin][i]);
				
				//Destroi os caminhos de ida e volta da matrix auxiliar
				Integer rect = matrixAuxiliar[origin][i];
				matrixAuxiliar[origin][i] = 0;
				matrixAuxiliar[i][origin] = 0; 
				
				TreeMap<Integer, Edge> local = new TreeMap<Integer, Edge>();
				paths.put(helper, edge);
				//Começa a recursividade do proximo no e salva na memoria
				TreeMap<Integer, Edge> auxiliar = recursiveSmallestPath(i, destin, local);


				if(auxiliar != null) {
					memory.put(i, auxiliar);
					
					//Pega a distancia
					Integer distance = sumDistanceOfPaths(auxiliar);
					if(origin == 6) {
						System.out.println("Distancae " + distance);
					}
					//Verifica se é a menor distancia
					if(smallestValue > distance && distance > 0) {
						smallestValue = distance;
						smallestValueIndex = i;
						the_rect = rect;
					}
					
				}
				paths.remove(helper);
				helper++;
				
			}
			
		}
		
		//Caso não possua nenhum nó ligado a este  e tambem não encontrou o caminho
		if(smallestValueIndex == -1) {
			System.out.println(origin + " rigin");
			return null;
		}
		else {
			paths.put(origin, new Edge(origin,smallestValueIndex,the_rect));
			System.out.println("ADD PATH");
			System.out.println(origin);
			System.out.println(smallestValueIndex);
			System.out.println(the_rect);
			return recursiveSmallestPath(smallestValueIndex, destin, paths);
		}
		
	}
	
	public Integer getDistanceToDestination() {
		return distanceToDestination;
	}
	
	public TreeMap<Integer, Edge> getPathToDestination() {
		return pathToDestination;
	}
	
	
	
	
	public static void main(String[] args) {

		try {
			Vieira dij = new Vieira(8);

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
			dij.insertEdge(3, 7, 2);

			dij.insertEdge(4, 0, 2);
			dij.insertEdge(4, 2, 2);
			dij.insertEdge(4, 5, 7);
			dij.insertEdge(4, 7, 4);

			dij.insertEdge(5, 4, 7);
			dij.insertEdge(5, 7, 3);

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
