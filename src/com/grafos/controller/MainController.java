package com.grafos.controller;

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
            	
            	//Cria as thread (ou instância o objeto que fará isto) aqui para assistir mudanças no diretório
            	//quando automático estiver marcado
            	
            	//Inicializa o Tray Icon
            	TrayIconApplication trayIcon = new TrayIconApplication(dm.getConfig());
            	trayIcon.initialize();
            }
        });
    }
}
