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
			throw new Exception("O arquivo contém dados inválidos");
		}

		Path path = new Path(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3],
				Integer.parseInt(data[4]));

		paths.add(path);
		// REFATORADO
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
		
		//addNewCityCodeInTheHashMap(Integer.parseInt(data[0]));
		//addNewCityCodeInTheHashMap(Integer.parseInt(data[2]));
		
	}
	
	public void addNewCityCodeInTheHashMap(Integer cityNumber) {
		if(!valuesOfMatrix.containsValue(cityNumber)) {
			valuesOfMatrix.put(index, cityNumber);
			index++;
		}
	}

	public String foundSmallerPath(Integer origin, Integer destination) throws Exception {
		String result = "";

		try {

			if (paths.size() < 1) {
				throw new Exception("Caminhos não indicados.");
			}
			
			System.out.println("....." + origin + "-" + valuesOfMatrix.size());

			Dijkstra dij = new Dijkstra(valuesOfMatrix.size());
			
			System.out.println(origin + ", " + destination);

			// Adiciona os caminhos no Dijkstra
			createDijkstraElements(dij);
			
			//Encontra o menor caminho entre a origem e o destino
			dij.findSmallestPath(origin, destination);

			// Pega os caminhos percorridos
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});

			System.out.println("...." + valuesOfMatrix.get(origin));
			
			// Pega o total de distância até o destino
			System.out.println("Total: " + dij.getDistanceToDestination());

			result = dij.getDistanceToDestination().toString();

		} catch (Exception error) {
			throw new Exception("Erro ao encontrar menor caminho: " + error.getMessage());
		}

		return result;
	}
	
	public String foundSmallerPath() throws Exception {
		
		paths.forEach(path -> System.out.println(path.getCityFinish()));
		
		//Pega o Index do começo do primeiro caminho e o final do ultimo caminho
		return foundSmallerPath(valuesOfMatrix.get(paths.get(0).getStart()), 
				valuesOfMatrix.get(paths.get(paths.size() - 1).getFinish()));
	}

	public void createDijkstraElements(Dijkstra dij) throws Exception {
		for (Path path : paths) {
			dij.insertEdge(valuesOfMatrix.get(path.getStart()), valuesOfMatrix.get(path.getFinish()),
					path.getDistance());
		}
	}

}
