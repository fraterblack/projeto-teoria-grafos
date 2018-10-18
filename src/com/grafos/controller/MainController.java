package com.grafos.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.grafos.lib.DatabaseManager;
import com.grafos.model.Configuration;
import com.grafos.observer.ObserverTrayIconInterface;
import com.grafos.trayIcon.TrayIconApplication;
import com.grafos.view.ConfigurationView;
import com.grafos.view.SearchView;

public class MainController implements ObserverTrayIconInterface {
	DatabaseManager dm = new DatabaseManager();
	Configuration configuration;
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
						System.out.println("Thread");
						File folder = new File(configuration.getFolder());
						for (String file : folder.list()) {
							if (file.endsWith(".txt") && !file.contains("-processing")) {
								//TODO: antes mesmo de processar o arquivo, renomeá-lo com "-processing"
								//Desta forma, o arquivo não será processado na próxima vez que a thread varrer o diretório
								
								System.out.println("process txt");
							}
						}

						Thread.sleep(2000);
					}
				}					
				catch (Exception e) {

				}
			}
		}).start();	
	}
}
