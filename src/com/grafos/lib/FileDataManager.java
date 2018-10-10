package com.grafos.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FileDataManager {
	public static List<String[]> extractFileData(String filePath) {
		List<String[]> extractedData = new ArrayList<String[]>();
		
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
			JOptionPane.showMessageDialog(null, "Arquivo inv�lido: " + error.getMessage(), "", JOptionPane.ERROR_MESSAGE, null);
		}
		
		return extractedData;
	}
}
