package com.grafos.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.grafos.algorithm.PathFounder;
import com.grafos.lib.DatabaseManager;
import com.grafos.lib.FileDataManager;
import com.grafos.model.Configuration;
import com.grafos.observer.ObserverTrayIconInterface;
import com.grafos.trayIcon.TrayIconApplication;
import com.grafos.view.ConfigurationView;
import com.grafos.view.SearchView;

public class MainController implements ObserverTrayIconInterface {
	private DatabaseManager dm = new DatabaseManager();
	private Configuration configuration;
	private SearchView activeSearchView;
	
	public void initialize() {
    	//Verifica se existe configuração
    	if(!dm.hasConfig()) {
    		//chama a view para criar a configuração
    		ConfigurationView configurationView = new ConfigurationView();
    		configurationView.setVisible(true);
    		
    		configurationView.addWindowListener(new WindowAdapter() {
        		public void windowClosed(WindowEvent e) {
        			initialize();
        		}
    		});
    		
    		return;
    	}
    	
    	configuration = dm.getConfig();
    	
    	if (configuration != null) {
    		//Inicializa o Tray Icon
        	TrayIconApplication trayIcon = new TrayIconApplication();
        	trayIcon.initialize();
        	trayIcon.addObserver(this);
        	
    		if (configuration.getAutomatic()) {
    			startThread();
    		} else {
    			activeSearchView = trayIcon.openSearchView(configuration);
    		}
    	} else {
    		JOptionPane.showMessageDialog(null, "O programa não pode ser inicializado: Arquivo de configuração corrompido.", "", JOptionPane.ERROR_MESSAGE, null);
    	}
	}
	
	public void update(TrayIconApplication trayIcon) {
		Configuration oldConfiguration = configuration;
		configuration = trayIcon.getConfiguration();
		
		//Caso a nova configuração "ative" o modo automático, inicia a thread
		if (trayIcon.getConfiguration().getAutomatic() && !oldConfiguration.getAutomatic()) {
			if (activeSearchView != null) {
				trayIcon.closeSearchView();
			}
			
			startThread();
		}
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	MainController mainController = new MainController();
            	mainController.initialize();
            }
        });
    }
	
	private void startThread() {
		new Thread(new Runnable() {
			public void run() {
				try {				
					while (!Thread.currentThread().isInterrupted() && configuration.getAutomatic()) {
						File folder = new File(configuration.getRootFolder());
						for (String fileName : folder.list()) {
							if (fileName.endsWith(".txt")) {
								DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd_hh-mm-ss");
								
								String filePath = folder.getPath() + "\\" + fileName;
								java.nio.file.Path source = Paths.get(filePath);
								
								java.nio.file.Path errorPath = Paths.get(configuration.getRootFolder() + "\\" + configuration.getErrorFolder());
								java.nio.file.Path successPath = Paths.get(configuration.getRootFolder() + "\\" + configuration.getSuccessFolder());
								
								try {
									//Extrai dados do arquivo
									TreeMap<Integer, String[]> extractedData = FileDataManager.extractFileData(filePath);
									
									//Instância a classe que encontra o menor caminho
									PathFounder founder = new PathFounder();
									
									//Itera sobre os dados extraídos para adicionar ao founder
									extractedData
										.forEach((key, data) -> {
											try {
												founder.add(data);
									        } catch(Exception e) {
									        	throw new RuntimeException(e);
									        }
										});
									
									//Encontra o menor caminho
									String result = founder.foundSmallerPath();
									
									//SUCESSO
									//Guarda o resultado no final do arquivo
									writeMessageInFile(filePath, result);
									
									//Move arquivo
									Files.move(source, successPath.resolve(dateFormat.format(new Date()) + "_" + fileName));
								} catch (Exception error) {
									//EM CASO DE ERRO
									//Adiciona a mensagem de erro no final do arquivo
									writeMessageInFile(filePath, error.getMessage());
									
									//Move arquivo
									Files.move(source, errorPath.resolve(dateFormat.format(new Date()) + "_" + fileName));
								}
								
								//Interrompe o laco, pois a aplicação processa um arquivo por vez
								break;
							}
						}

						Thread.sleep(2000);
					}
				}					
				catch (Exception e) {
					//Erro ao executar Thread
					e.printStackTrace();
				}
			}
		}).start();	
	}
	
	private void writeMessageInFile(String filePath, String message) {
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(filePath, true));
			
			output.append(message);
			
			output.close();
		} catch (IOException e) {
			//Erro ao gravar mensagem no arquivo
			e.printStackTrace();
		}
	}
}
