package com.grafos.algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;

public class Dijkstra extends Graph {
	private int distances[];
	
	//Results
	private int distanceToDestination = 0;
	private TreeMap<Integer, Edge> pathToDestination;

	// Fila de prioridade.
	private HashSet<Integer> edgesRemaining = new HashSet<Integer>();

	public Dijkstra(int vertexQuantity) throws Exception {
		super(vertexQuantity);

		// Define o tamanho das distancias.
		distances = new int[vertexQuantity];
	}

	/**
	 * Método responsável por encontrar o menor caminho.
	 * 
	 * @param origin
	 * @param destin
	 * @throws Exception
	 */
	public void findSmallestPath(int origin, int destin) throws Exception {
		if (origin < 0) {
			throw new Exception("Nó de origem não pode ser negativa");
		}
		
		if (destin >= getNodesQuantity()) {
			throw new Exception("Nó de destino não pode ser maior que " + (getNodesQuantity() - 1));
		}

		initSearchForSmallestPath(origin);
		
		pathToDestination = extractSmallestPathTo(origin, destin);

		distanceToDestination = distances[destin];
	}
	
	public Integer getDistanceToDestination() {
		return distanceToDestination;
	}
	
	public TreeMap<Integer,Edge> getPathToDestination() {
		return pathToDestination;
	}

	/**
	 * Inicia a busca pelo menor caminho.
	 * 
	 * @param origin
	 */
	private void initSearchForSmallestPath(int origin) {
		pathToDestination = null;
		distanceToDestination = 0;
		
		for (int i = 0; i < getNodesQuantity(); i++) {
			distances[i] = Integer.MAX_VALUE; // Define-se a distancia de todos os nós como uma distancia infinita.

			edgesRemaining.add(new Integer(i)); // adiciona a aresta na fila
		}

		distances[origin] = 0; // Define a distância da origem como ZERO.
	}

	/**
	 * Extrai o menor caminho a ser seguido.
	 * @return TreeMap<Integer, Path>
	 */
	private TreeMap<Integer, Edge> extractSmallestPathTo(int origin, int destin) {
		TreeMap<Integer, Edge> paths = new TreeMap<Integer, Edge>();
		
		boolean smallestPathFound = false;
		int nodeOrigin = origin;

		// Percorre a lista de prioridade.
		while (!edgesRemaining.isEmpty()) {

			int smallValue = Integer.MAX_VALUE;
			int nodeWithSmallestWeight = 0;

			Iterator<Integer> it = edgesRemaining.iterator();
			while (it.hasNext()) {
				int currentVertex = it.next();

				if (distances[currentVertex] < smallValue) {
					smallValue = distances[currentVertex];
					nodeWithSmallestWeight = currentVertex;
				}
			}

			//Se o menor caminho ainda não foi encontrado
			if (!smallestPathFound && smallValue > 0) {
				paths.put(paths.size(), new Edge(nodeOrigin, nodeWithSmallestWeight, smallValue));
				
				//variável de controler para saber o último vértice na próxima iteração
				nodeOrigin = nodeWithSmallestWeight;
			}
			
			//Se o nó destino for o mesmo nó com menor menor caminho, o menor caminho foi encontrado
			if (destin == nodeWithSmallestWeight) {
				smallestPathFound = true;
			}
				
			edgesRemaining.remove(new Integer(nodeWithSmallestWeight));
			
			//Percorre todos os itens restantes para relaxar os itens
			for (int i = 0; i < getNodesQuantity(); i++) {
				if (getMatrix()[nodeWithSmallestWeight][i] > 0) {
					calculateDistances(nodeWithSmallestWeight, i);
				}
			}
		}
		
		return paths;
	}

	/*
	 * Relaxa arestas no grafo
	 */
	private void calculateDistances(int i, int j) {
		if (distances[j] > distances[i] + getMatrix()[i][j]) {
			distances[j] = distances[i] + getMatrix()[i][j];
		}
	}
}
