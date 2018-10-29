package com.grafos.tester;

import com.grafos.algorithm.Dijkstra;
import com.grafos.algorithm.Graph;
import com.grafos.algorithm.Vieira;

public class AlgorithmTester {

	public static void main(String[] args) {
		System.out.println("===== TESTE ALGORITIMOS =====");
		System.out.println("== DIJ ==");
		Dijkstra dij;
		Long startTime;
		Long finishTime;
		System.out.println("== VIEIRA ==");
		Vieira vieira;
		
		try {
			vieira = new Vieira(8);
			
			addEdge(vieira);
			
			startTime = System.currentTimeMillis();
			
			vieira.findSmallestPath(2, 5);
			
			finishTime = System.currentTimeMillis();
			System.out.println(finishTime);
			System.out.println("Distancia: " + vieira.getDistanceToDestination());
			System.out.println("Rota:");
			vieira.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});
			System.out.println("Tempo de execução: " + (finishTime - startTime) + "ms");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dij = new Dijkstra(8);
			
			addEdge(dij);
			
			startTime = System.currentTimeMillis();
			
			dij.findSmallestPath(2, 5);
			
			finishTime = System.currentTimeMillis();
			System.out.println("Distancia: " + dij.getDistanceToDestination());
			System.out.println("Rota:");
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});
			System.out.println("Tempo de execução: " + (finishTime - startTime) + "ms");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static void addEdge(Graph algGraph) {
		try {
			algGraph.insertEdge(0, 1, 4);
			algGraph.insertEdge(0, 2, 4);
			algGraph.insertEdge(0, 4, 2);
			algGraph.insertEdge(0, 6, 6);

			algGraph.insertEdge(1, 0, 4);
			algGraph.insertEdge(1, 2, 2);
			algGraph.insertEdge(1, 3, 7);
			algGraph.insertEdge(1, 6, 3);

			algGraph.insertEdge(2, 0, 4);
			algGraph.insertEdge(2, 1, 2);
			algGraph.insertEdge(2, 3, 3);
			algGraph.insertEdge(2, 4, 2);

			algGraph.insertEdge(3, 1, 7);
			algGraph.insertEdge(3, 2, 3);
			algGraph.insertEdge(3, 7, 2);

			algGraph.insertEdge(4, 0, 2);
			algGraph.insertEdge(4, 2, 2);
			algGraph.insertEdge(4, 5, 7);
			algGraph.insertEdge(4, 7, 4);

			algGraph.insertEdge(5, 4, 7);
			algGraph.insertEdge(5, 7, 3);

			algGraph.insertEdge(6, 0, 6);
			algGraph.insertEdge(6, 1, 3);

			algGraph.insertEdge(7, 3, 2);
			algGraph.insertEdge(7, 4, 4);
			algGraph.insertEdge(7, 5, 3);
		}catch(Exception error) {
			System.out.println("Erro add Edge: " + error.getMessage());
		}
		
	}

}
