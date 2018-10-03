package com.grafos.controller;

import java.io.File;

import javax.swing.SwingUtilities;

import com.grafos.lib.DatabaseManager;
import com.grafos.model.Configuration;
import com.grafos.trayIcon.TrayIconApplication;
import com.grafos.view.ConfiguracaoView;

public class MainController {
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	DatabaseManager dm = new DatabaseManager();
            	
            	//Verifica se existe configuração
            	if(!dm.hasConfig()) {
            		//chama a view para criar a configuração
            		ConfiguracaoView cv = new ConfiguracaoView();
            		cv.setVisible(true);
            		return;
            	}
            	
            	Configuration config = dm.getConfig();
            	
        		if (config.getAutomatic()) {
        			new Thread(new Runnable() {
      
        				@Override
        				public void run() {
        					try {				
        						while (!Thread.currentThread().isInterrupted()) {
        							/* TA BUGANDO */
        							File folder = new File(config.getFolder()+"\\pasta");
        							for (String file : folder.list()) {
        								if (file.endsWith(".txt")) {
        									System.out.println("test");
        								}
        							}
        							
        							
        							Thread.sleep(1000);
        						}
        					}					
        					catch (Exception e) {

        					}
        				}
        			}).start();	
        		}
            	
            	//Inicializa o Tray Icon
            	TrayIconApplication trayIcon = new TrayIconApplication(dm.getConfig());
            	trayIcon.initialize();
            }
        });
    }
}
