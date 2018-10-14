package com.grafos.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class FileDataManager {
	public static Set<String[]> extractFileData(String filePath) {
		Set<String[]> extractedData = new HashSet<String[]>();
		
		try {
			BufferedReader bufferArquivo = new BufferedReader(new FileReader(filePath));
	
			String linha = bufferArquivo.readLine();
			
			while (linha != null) {
				if (!linha.isEmpty()) {
					String[] rowData = linha.split(";");
					
					extractedData.add(rowData);
				}
				
				linha = bufferArquivo.readLine();
			}
			
			bufferArquivo.close();
		} catch(Exception error) {
			JOptionPane.showMessageDialog(null, "Arquivo inválido: " + error.getMessage(), "", JOptionPane.ERROR_MESSAGE, null);
		}
		
		return extractedData;
	}
}
