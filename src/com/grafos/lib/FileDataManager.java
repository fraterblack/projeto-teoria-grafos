package com.grafos.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;

public class FileDataManager {
	private final static String DATA_SEPARATOR = ";";
	
	public static TreeMap<Integer, String[]> extractFileData(String filePath) throws Exception {
		TreeMap<Integer, String[]> extractedData = new TreeMap<Integer, String[]>();
		
		try {
			BufferedReader bufferArquivo = new BufferedReader(new FileReader(filePath));
	
			String lineData = bufferArquivo.readLine();
			
			while (lineData != null) {
				if (!lineData.isEmpty()) {
					String[] rowData = lineData.split(DATA_SEPARATOR);
					
					extractedData.put(extractedData.size(), rowData);
				}
				
				lineData = bufferArquivo.readLine();
			}
			
			bufferArquivo.close();
		} catch(Exception error) {
			throw new Exception("Arquivo inválido: " + error.getMessage());
		}
		
		return extractedData;
	}
}
