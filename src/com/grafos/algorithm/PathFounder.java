package com.grafos.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import com.grafos.model.Path;

public class PathFounder {

	HashMap<Integer, Integer> valuesOfMatrix;
	Integer index = 0;
	ArrayList<Path> paths;

	Graph graph;

	public PathFounder() {
		paths = new ArrayList<Path>();
		valuesOfMatrix = new HashMap<Integer, Integer>();

	}

	public void add(String[] data) {

		try {
			if (data.length != 5) {
				throw new Exception("Dados informados incorretamente");
			}
			
			Path path = new Path(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3],
					Integer.parseInt(data[4]));

			paths.add(path);

			// verify if already exist the key
			if (!valuesOfMatrix.containsKey(Integer.parseInt(data[0]))) {
				valuesOfMatrix.put(Integer.parseInt(data[0]), index);
				index++;
			}

			// verify if already exist the key
			if (!valuesOfMatrix.containsKey(Integer.parseInt(data[2]))) {
				valuesOfMatrix.put(Integer.parseInt(data[2]), index);
				index++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String foundSmallerPath() {
		String result = "";

		try {

			if (paths.size() < 1) {
				throw new Exception("Caminhos não indicados.");
			}
			
			createGraphElements();
			
			/*
			Dijkstra dij = new Dijkstra(valuesOfMatrix.size());
			
			//Percorre itens e adiciona as arestas
			//FOR
			dij.insertEdge(0, 1, 4);
			//ENDFOR
			
			//Procura o menor caminho de um nó de origem até o nó de destino
			dij.findSmallestPath(6, 7);
			
			//Pega os caminhos percorridos
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getDistance());
			});
			
			//Pega o total de distância até o destino
			System.out.println(dij.getDistanceToDestination());
			*/
			// result = execute djikstra

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void createGraphElements() {

		// foreach(paths as)

	}

}
