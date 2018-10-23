package com.grafos.algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;

public class Dijkstra extends Graph {
	private int distances[];

	// Results
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

	public TreeMap<Integer, Edge> getPathToDestination() {
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
	 * 
	 * @return TreeMap<Integer, Path>
	 */
	private TreeMap<Integer, Edge> extractSmallestPathTo(int origin, int destin) throws Exception {
		TreeMap<Integer, Edge> paths = new TreeMap<Integer, Edge>();

		boolean smallestPathFound = false;
		int nodeOrigin = origin;

		// Percorre a lista de prioridade.
		while (!edgesRemaining.isEmpty()) {
			boolean change = false;
			int smallValue = Integer.MAX_VALUE;
			int nodeWithSmallestWeight = 0;

			Iterator<Integer> it = edgesRemaining.iterator();
			while (it.hasNext()) {
				int currentVertex = it.next();

				if (distances[currentVertex] < smallValue) {
					smallValue = distances[currentVertex];
					nodeWithSmallestWeight = currentVertex;
					change = true;
				}
			}

			// Se o menor caminho ainda não foi encontrado
			if (!smallestPathFound && smallValue > 0) {
				paths.put(paths.size(), new Edge(nodeOrigin, nodeWithSmallestWeight, smallValue));

				// variável de controler para saber o último vértice na próxima iteração
				nodeOrigin = nodeWithSmallestWeight;
			}

			// Se o nó destino for o mesmo nó com menor menor caminho, o menor caminho foi
			// encontrado
			if (destin == nodeWithSmallestWeight) {
				smallestPathFound = true;
			}
			System.out.println(nodeWithSmallestWeight);
			edgesRemaining.remove(new Integer(nodeWithSmallestWeight));

			// Percorre todos os itens restantes para relaxar os itens
			for (int i = 0; i < getNodesQuantity(); i++) {
				if (getMatrix()[nodeWithSmallestWeight][i] > 0) {
					calculateDistances(nodeWithSmallestWeight, i);
				}
			}
			
			if(!change) {
				throw new Exception("Caminho impossivel.");
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

	public static void main(String[] args) {

		try {
			Dijkstra dij = new Dijkstra(8);

			dij.insertEdge(0, 0, 0);
			dij.insertEdge(0, 1, 4);
			dij.insertEdge(0, 2, 4);
			dij.insertEdge(0, 3, 0);
			dij.insertEdge(0, 4, 2);
			dij.insertEdge(0, 5, 0);
			dij.insertEdge(0, 6, 6);
			dij.insertEdge(0, 7, 0);

			dij.insertEdge(1, 0, 4);
			dij.insertEdge(1, 1, 0);
			dij.insertEdge(1, 2, 2);
			dij.insertEdge(1, 3, 7);
			dij.insertEdge(1, 4, 0);
			dij.insertEdge(1, 5, 0);
			dij.insertEdge(1, 6, 3);
			dij.insertEdge(1, 7, 0);

			dij.insertEdge(2, 0, 4);
			dij.insertEdge(2, 1, 2);
			dij.insertEdge(2, 2, 0);
			dij.insertEdge(2, 3, 3);
			dij.insertEdge(2, 4, 2);
			dij.insertEdge(2, 5, 0);
			dij.insertEdge(2, 6, 0);
			dij.insertEdge(2, 7, 0);

			dij.insertEdge(3, 0, 0);
			dij.insertEdge(3, 1, 7);
			dij.insertEdge(3, 2, 3);
			dij.insertEdge(3, 3, 0);
			dij.insertEdge(3, 4, 0);
			dij.insertEdge(3, 5, 0);
			dij.insertEdge(3, 6, 0);
			dij.insertEdge(3, 7, 2);

			dij.insertEdge(4, 0, 2);
			dij.insertEdge(4, 1, 0);
			dij.insertEdge(4, 2, 2);
			dij.insertEdge(4, 3, 0);
			dij.insertEdge(4, 4, 0);
			dij.insertEdge(4, 5, 7);
			dij.insertEdge(4, 6, 0);
			dij.insertEdge(4, 7, 4);

			dij.insertEdge(5, 0, 0);
			dij.insertEdge(5, 1, 0);
			dij.insertEdge(5, 2, 0);
			dij.insertEdge(5, 3, 0);
			dij.insertEdge(5, 4, 7);
			dij.insertEdge(5, 5, 0);
			dij.insertEdge(5, 6, 0);
			dij.insertEdge(5, 7, 3);

			dij.insertEdge(6, 0, 6);
			dij.insertEdge(6, 1, 3);
			dij.insertEdge(6, 2, 0);
			dij.insertEdge(6, 3, 0);
			dij.insertEdge(6, 4, 0);
			dij.insertEdge(6, 5, 0);
			dij.insertEdge(6, 6, 0);
			dij.insertEdge(6, 7, 0);

			dij.insertEdge(7, 0, 0);
			dij.insertEdge(7, 1, 0);
			dij.insertEdge(7, 2, 0);
			dij.insertEdge(7, 3, 2);
			dij.insertEdge(7, 4, 4);
			dij.insertEdge(7, 5, 3);
			dij.insertEdge(7, 6, 0);
			dij.insertEdge(7, 7, 0);
			
			System.out.println("########### Test Case ##########");
			
			//Case 1
			dij.findSmallestPath(6, 7);
			
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});

			System.out.println(dij.getDistanceToDestination()); //Esperado: Total 10
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
