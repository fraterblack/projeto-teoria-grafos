package com.grafos.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.grafos.model.Path;

public class PathFounder {
	
	//Codigo da cidade -> ID na matrix
	HashMap<Integer, Integer> valuesOfMatrix;
	Integer index = 0;
	ArrayList<Path> paths;
	
	Graph graph;
	
	public PathFounder() {
		paths = new ArrayList<Path>();
		valuesOfMatrix = new HashMap<Integer, Integer>();
		
	}
	
	//1 City1, 2 City2 200
	public void add(String[] data) {
		
		try {
			if(data.length != 5) {
				throw new Exception("Dados informados incorretamente");
			}
			Path path = new Path(Integer.parseInt(data[0]),
					data[1],
					Integer.parseInt(data[2]),
					data[3],
					Integer.parseInt(data[4]));
			
			paths.add(path);
			
			//verify if already exist the key
			if(!valuesOfMatrix.containsKey(Integer.parseInt(data[0]))) {
				valuesOfMatrix.put(Integer.parseInt(data[0]),index);
				index++;
			}
			
			//verify if already exist the key
			if(!valuesOfMatrix.containsKey(Integer.parseInt(data[2]))) {
				valuesOfMatrix.put(Integer.parseInt(data[2]),index);
				index++;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public String foundSmallerPath() {
		String result = "";
		
		try {
			
			if(paths.size() < 1) {
				throw new Exception("Caminhos não indicados.");
			}
			
			graph = new Graph(valuesOfMatrix.size());
			createGraphElements();
			//result = execute djikstra
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void createGraphElements() {
		
		//foreach(paths as)
		
	}
	
}
