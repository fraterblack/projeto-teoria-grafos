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
            	
            	//Verifica se existe configura��o
            	if(!dm.hasConfig()) {
            		//chama a view para criar a configura��o
            		ConfiguracaoView cv = new ConfiguracaoView();
            		cv.setVisible(true);
            		return;
            	}
            	
            	//Cria as thread (ou inst�ncia o objeto que far� isto) aqui para assistir mudan�as no diret�rio
            	//quando autom�tico estiver marcado
            	
            	//Inicializa o Tray Icon
            	TrayIconApplication trayIcon = new TrayIconApplication(dm.getConfig());
            	trayIcon.initialize();
            }
        });
    }
}
