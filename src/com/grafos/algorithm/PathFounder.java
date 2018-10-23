package com.grafos.algorithm;

import java.util.TreeMap;

import com.grafos.model.Path;

public class PathFounder {
	private TreeMap<Integer, Path> paths = new TreeMap<Integer, Path>();
	private TreeMap<Integer, String> cities = new TreeMap<Integer, String>();
	private TreeMap<String, Integer> nodeIndexes = new TreeMap<String, Integer>();
	
	public Path[] getPaths() {
		Path[] pathsToReturn = new Path[paths.size()];
		
		for (Integer i = 0; i < paths.size(); i++) {
			pathsToReturn[i] = paths.get(i);
		}

		return pathsToReturn;
	}
	
	public void add(String[] data) throws Exception {
		if (data.length != 5) {
			throw new Exception("O arquivo contém dados inválidos");
		}
		
		Path path = new Path(data[0], data[1], data[2], data[3],
				Integer.parseInt(data[4]));
		
		paths.put(paths.size(), path);
		
		extractDataFromPath(path);
	}

	public String foundSmallerPath(Integer origin, Integer destination) throws Exception {
		String result = "";

		try {
			if (paths.size() == 0) {
				throw new Exception("Nenhum caminho indicado.");
			}
			
			Dijkstra dij = new Dijkstra(cities.size());

			// Adiciona os caminhos no Dijkstra
			paths.forEach((key, path) -> {
				try {
					dij.insertEdge(nodeIndexes.get(path.getOriginCode()), nodeIndexes.get(path.getDestinationCode()), path.getDistance());
		        } catch(Exception e) {
		        	throw new RuntimeException(e);
		        }
			});
			
			//Encontra o menor caminho entre a origem e o destino
			dij.findSmallestPath(origin, destination);

			// Pega os caminhos percorridos
			dij.getPathToDestination().forEach((key, edge) -> {
				//System.out.println(cities.get(edge.getNodeOrigin()) + "->" + cities.get(edge.getNodeDestin()) + "=" + edge.getAccumulatedDistance());
			});

			result = "Menor caminho entre " + cities.get(origin) + " e " + cities.get(destination) + " é " + dij.getDistanceToDestination().toString() + " km.";
		} catch (Exception error) {
			throw new Exception("Erro ao encontrar menor caminho: " + error.getMessage());
		}

		return result;
	}
	
	public String foundSmallerPath() throws Exception {
		if (paths.size() == 0) {
			throw new Exception("Nenhum caminho indicado.");
		}
		
		return foundSmallerPath(
				nodeIndexes.get(paths.get(0).getOriginCode()), 
				nodeIndexes.get(paths.get(paths.size() - 1).getDestinationCode())
		);
	}
	
	public TreeMap<String, Integer> getNodeIndexes() {
		return nodeIndexes;
	}
	
	public TreeMap<Integer, String> getCities() {
		return cities;
	}
	
	private void extractDataFromPath(Path path) {
		if (nodeIndexes.get(path.getOriginCode()) == null) {
			nodeIndexes.put(path.getOriginCode(), nodeIndexes.size());
			cities.put(cities.size(), path.getOriginName());
		}
		
		if (nodeIndexes.get(path.getDestinationCode()) == null) {
			nodeIndexes.put(path.getDestinationCode(), nodeIndexes.size());
			cities.put(cities.size(), path.getDestinationName());
		}
	}
}
