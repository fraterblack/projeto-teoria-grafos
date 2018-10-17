package com.grafos.lib;

public class Graph {
	private final int NUMERO_VERTICES;
	
	private Integer[][] matriz;

	public Graph(int qtdVertices) throws Exception {
		
		// Testa a quantidade de vertice.
		if (qtdVertices < 0) {
			throw new Exception("Número de vertices não pode ser menor do que zero.");
		}
		
		// Guarda a quantidade de vertices.
		NUMERO_VERTICES = qtdVertices;
		
		// Cria a matriz.
		criaMatriz();
		
		// Manda imprimir.
		imprimirMatriz();
	}
	
	/**
	 * Método responsável por criar a matriz.
	 */
	private void criaMatriz() {
		
		//  Cria a matriz.
		matriz = new Integer[NUMERO_VERTICES][];
		
		// Percorre as linhas da matriz.
		for (int l = 0; l < NUMERO_VERTICES; l++) {
			
			// Instancia a linha.
			matriz[l] = new Integer[NUMERO_VERTICES];
			
			// Percorre a quantidade de colunas.
			for (int c = 0; c < matriz[l].length; c++) {
				
				// Adiciona um valor padrão.
				matriz[l][c] = 0;
			}
		}
	}
	
	/**
	 * Imprimi a matriz criada.
	 * @throws Exception
	 * 		   Se uma matriz não for criada ou não inicializada.
	 */
	private void imprimirMatriz() throws Exception {
		if (matriz == null) {
			throw new Exception("Matriz nula encontrada");
		}
		else if (matriz[0] == null) {
			throw new Exception("Matriz não inicializada");
		}
		else {
			for(int l = 0 ; l < NUMERO_VERTICES ; l++){ 
                for(int c = 0 ; c < NUMERO_VERTICES ; c++) {
                	System.out.print("["+matriz[l][c]+"] ");
                }    
                System.out.print("\n"); 
            }  
		}
	}
	
	/**
	 * Método responsável por inserir uma aresta.
	 * @param A
	 * @param B
	 * @param peso
	 * @throws Exception 
	 */
	public void inserirAresta(final int A, final int B, final int peso) throws Exception {
		
		// Se vertice for menor do que zero.
		if (A < 0 || B < 0) {
			throw new Exception("Um dos vertices são inválidos");
		}
		else if (A > NUMERO_VERTICES || B > NUMERO_VERTICES) {
			throw new Exception("Um dos vertices é maior que o tamanho de vértices");
		}
		else if (peso < 0) {
			throw new Exception("Peso não pode ser negativo");
		}
		
		matriz[A][B] = peso;
	}
	
	public int getQtdVertice() {
		return NUMERO_VERTICES;
	}
	
	public Integer[][] getMatriz() {
		return matriz;
	}

	public static void main(String[] args) {		
		try {
			Graph g = new Graph(8);
			
			g.inserirAresta(0, 0, 0);
			g.inserirAresta(0, 1, 4);
			g.inserirAresta(0, 2, 4);
			g.inserirAresta(0, 3, 0);
			g.inserirAresta(0, 4, 2);
			g.inserirAresta(0, 5, 0);
			g.inserirAresta(0, 6, 6);
			g.inserirAresta(0, 7, 0);
			
			g.inserirAresta(1, 0, 4);
			g.inserirAresta(1, 1, 0);
			g.inserirAresta(1, 2, 2);
			g.inserirAresta(1, 3, 7);
			g.inserirAresta(1, 4, 0);
			g.inserirAresta(1, 5, 0);
			g.inserirAresta(1, 6, 3);
			g.inserirAresta(1, 7, 0);
			
			g.inserirAresta(2, 0, 4);
			g.inserirAresta(2, 1, 2);
			g.inserirAresta(2, 2, 0);
			g.inserirAresta(2, 3, 3);
			g.inserirAresta(2, 4, 2);
			g.inserirAresta(2, 5, 0);
			g.inserirAresta(2, 6, 0);
			g.inserirAresta(2, 7, 0);

			g.inserirAresta(3, 0, 0);
			g.inserirAresta(3, 1, 7);
			g.inserirAresta(3, 2, 3);
			g.inserirAresta(3, 3, 0);
			g.inserirAresta(3, 4, 0);
			g.inserirAresta(3, 5, 0);
			g.inserirAresta(3, 6, 0);
			g.inserirAresta(3, 7, 2);
			
			g.inserirAresta(4, 0, 2);
			g.inserirAresta(4, 1, 0);
			g.inserirAresta(4, 2, 2);
			g.inserirAresta(4, 3, 0);
			g.inserirAresta(4, 4, 0);
			g.inserirAresta(4, 5, 7);
			g.inserirAresta(4, 6, 0);
			g.inserirAresta(4, 7, 4);
			
			g.inserirAresta(5, 0, 0);
			g.inserirAresta(5, 1, 0);
			g.inserirAresta(5, 2, 0);
			g.inserirAresta(5, 3, 0);
			g.inserirAresta(5, 4, 7);
			g.inserirAresta(5, 5, 0);
			g.inserirAresta(5, 6, 0);
			g.inserirAresta(5, 7, 3);
			
			g.inserirAresta(6, 0, 6);
			g.inserirAresta(6, 1, 3);
			g.inserirAresta(6, 2, 0);
			g.inserirAresta(6, 3, 0);
			g.inserirAresta(6, 4, 0);
			g.inserirAresta(6, 5, 0);
			g.inserirAresta(6, 6, 0);
			g.inserirAresta(6, 7, 0);
			
			g.inserirAresta(7, 0, 0);
			g.inserirAresta(7, 1, 0);
			g.inserirAresta(7, 2, 0);
			g.inserirAresta(7, 3, 2);
			g.inserirAresta(7, 4, 4);
			g.inserirAresta(7, 5, 3);
			g.inserirAresta(7, 6, 0);
			g.inserirAresta(7, 7, 0);
			
			System.out.println("");
			System.out.println("Grafo com pesos");
			System.out.println("");
			
			g.imprimirMatriz();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
