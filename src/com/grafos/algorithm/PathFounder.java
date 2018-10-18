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

	public void add(String[] data) throws Exception {
		if (data.length != 5) {
			throw new Exception("Dados informados incorretamente.");
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
	}

	public String foundSmallerPath() throws Exception {
		String result = "";

		try {

			if (paths.size() < 1) {
				throw new Exception("Caminhos não indicados.");
			}

			Dijkstra dij = new Dijkstra(valuesOfMatrix.size());

			// Adiciona os caminhos no Dijkstra
			createDijkstraElements(dij);

			dij.findSmallestPath(6, 7);

			// Pega os caminhos percorridos
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getDistance());
			});

			// Pega o total de distância até o destino
			System.out.println(dij.getDistanceToDestination());

			// result = execute djikstra
			
			//TODO: Tem que melhorar esse código Roger!!!
			result = dij.getDistanceToDestination().toString();

		} catch (Exception error) {
			throw new Exception("Erro ao encontrar menor caminho: " + error.getMessage());
		}

		return result;
	}

	public void createDijkstraElements(Dijkstra dij) throws Exception {
		for (Path path : paths) {
			dij.insertEdge(valuesOfMatrix.get(path.getStart()), valuesOfMatrix.get(path.getFinish()),
					path.getDistance());
		}
	}

}
