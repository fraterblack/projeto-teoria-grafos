package com.grafos.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.TreeMap;
import com.grafos.model.Path;

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
	
	// TODO CHANGE paths to STRING[][]
	public static void exportFileDate(Path[] paths, String filePath, String fileName, String resultMessage) throws Exception  {
		
		try {
			BufferedWriter bufferArquivo = new BufferedWriter(new FileWriter(filePath + "\\" + fileName));

			for(Path path : paths) {
				bufferArquivo.append(path.getOriginCode() 
									+ DATA_SEPARATOR 
									+ path.getOriginName()
									+ DATA_SEPARATOR 
									+ path.getDestinationCode()
									+ DATA_SEPARATOR 
									+ path.getDestinationName()
									+ DATA_SEPARATOR 
									+ path.getDistance());
				bufferArquivo.append(System.getProperty("line.separator"));
				
				System.out.println(path.getOriginCode() 
									+ DATA_SEPARATOR 
									+ path.getOriginName()
									+ DATA_SEPARATOR 
									+ path.getDestinationCode()
									+ DATA_SEPARATOR 
									+ path.getDestinationName()
									+ DATA_SEPARATOR 
									+ path.getDistance()
									+ "\n");
			}
			
			bufferArquivo.append(resultMessage);
			
			bufferArquivo.close();
		} catch(Exception error) {
			throw new Exception("Arquivo inválido: " + error.getMessage());
		}
		
	}
	
}
