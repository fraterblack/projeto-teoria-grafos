package com.grafos.algorithm;

import java.util.TreeMap;
import java.util.Map.Entry;

public class Vieira extends Graph {

	// Results
	private Integer distanceToDestination;
	private TreeMap<Integer, Edge> pathToDestination;
	private boolean founded = false;

	// Auxiliar
	Integer[][] matrixAuxiliar;
	private Integer index = 1;

	public Vieira(int vertexQuantity) throws Exception {
		super(vertexQuantity);
	}

	public void findSmallestPath(int origin, int destin) throws Exception {
		int len = getNodesQuantity();
		matrixAuxiliar = new Integer[len][len];
		for(int l=0;l<len;l++) {
			for(int k=0;k<len;k++) {
				matrixAuxiliar[l][k] = getMatrix()[l][k];
			}
		}
		
		distanceToDestination = Integer.MAX_VALUE;

		recursivePathFounder(origin, destin, new TreeMap<Integer, Edge>(), 0,true);

		if (!founded) {
			throw new Exception("NÃ£o encontrado.");
		}
	}

	public void recursivePathFounder(Integer origin, Integer destin, TreeMap<Integer, Edge> paths, Integer sum,boolean first) {
		
		//Caso a soma ate aqui ja seja maior que a salva
		//(Diminui o tempo de execucao)
		if(sum > getDistanceToDestination()) {
			return;
		}
		
		if (origin == destin) {
			founded = true;
			
			//Verifica se a distancia encontrada é menor que a final
			if (sum < this.distanceToDestination) {

				this.distanceToDestination = sum;

				pathToDestination = new TreeMap<Integer, Edge>();
				
				//nutri o caminhos final
				Integer localIndex = 0;
				for (Entry<Integer, Edge> entry : paths.entrySet()) {

					pathToDestination.put(localIndex, entry.getValue());
					localIndex++;
				}

			}

			return;
		}
		//Vetor com os valores de distancia
		Integer[] values = new Integer[matrixAuxiliar.length];
		
		for (Integer i = 0; i < matrixAuxiliar.length; i++) {

			//Salva os valores em um vetor local
			values[i] = matrixAuxiliar[origin][i];
			
			//apaga os caminhos de inda e vinda para impedir voltar para este no
			matrixAuxiliar[origin][i] = 0;
			matrixAuxiliar[i][origin] = 0;

		}

		//Main Loop
		for (Integer i = 0; i < matrixAuxiliar.length; i++) {

			// existe caminho
			if (values[i] > 0) {
				
				int len = getNodesQuantity();
				Integer localIndex=1;
				
				//Variaveis de memoria
				TreeMap<Integer, Edge> savePath = new TreeMap<Integer,Edge>();
				Integer[][] saveMatrix = new Integer[len][len];
				
				//Nutri o caminho de memoria
				for (Entry<Integer, Edge> entry : paths.entrySet()) {
					savePath.put(localIndex, entry.getValue());
					localIndex++;
				}
				
				//Nutri a matrix de memoria
				for(int l=0;l<len;l++) {
					for(int k=0;k<len;k++) {
						saveMatrix[l][k] = matrixAuxiliar[l][k];
					}
				}
				
				//Cria edge local
				Edge edge = new Edge(origin, i, values[i]);

				savePath.put(index, edge); // adiciona para o loop local
				index++;
				
				// chama a recursividade para cada no
				recursivePathFounder(i, destin, savePath, sum + edge.getAccumulatedDistance(),false);
				
				//Reseta a matrix de acordo com o salvado
				//Elimina o que mudou dentro da recursividade
				for(int l=0;l<len;l++) {
					
					for(int k=0;k<len;k++) {
						matrixAuxiliar[l][k] = saveMatrix[l][k];
					}
				}
				
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

			// Case 1
			dij.findSmallestPath(2, 5);
			System.out.println("########### Test Case ##########");

			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(
						edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});

			System.out.println("Distancia total: " + dij.getDistanceToDestination()); // Esperado: Total 10
			System.out.println("test");
			///////
			/*
			 * //Case 2 dij.findSmallestPath(3, 5);
			 * 
			 * dij.getPathToDestination().forEach((key, edge) -> {
			 * System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" +
			 * edge.getAccumulatedDistance()); });
			 * 
			 * System.out.println(dij.getDistanceToDestination()); //Esperado: Total 5
			 */
			///////
		} catch (Exception ex) {
			if (ex.getMessage() == null)
				System.out.println("Ocorreu um erro de " + ex + " no main");
			else
				System.out.println(ex.getMessage() + "XXX");
		}

	}

}
