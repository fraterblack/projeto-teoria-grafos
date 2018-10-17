package com.grafos.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class FileDataManager {
	private final static String DATA_SEPARATOR = ";";
	
	public static Set<String[]> extractFileData(String filePath) {
		Set<String[]> extractedData = new HashSet<String[]>();
		
		try {
			BufferedReader bufferArquivo = new BufferedReader(new FileReader(filePath));
	
			String lineData = bufferArquivo.readLine();
			
			while (lineData != null) {
				if (!lineData.isEmpty()) {
					String[] rowData = lineData.split(DATA_SEPARATOR);
					
					extractedData.add(rowData);
				}
				
				lineData = bufferArquivo.readLine();
			}
			
			bufferArquivo.close();
		} catch(Exception error) {
			JOptionPane.showMessageDialog(null, "Arquivo inválido: " + error.getMessage(), "", JOptionPane.ERROR_MESSAGE, null);
		}
		
		return extractedData;
	}
}
