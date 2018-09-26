package com.grafos.controller;

import javax.swing.SwingUtilities;

import com.grafos.model.Configuration;
import com.grafos.trayIcon.TrayIconApplication;

public class MainController {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//Verificar aqui se já existe arquivo de configuração, caso não tenha, abrilô-loôô
            	
            	//Temporariamente a configuração está sendo gerada aqui (Espera-se que as configurações venham de um arquivo de configuração)
            	Configuration configuration = new Configuration("", "", "", true);
            	
            	//Cria as thread (ou instância o objeto que fará isto) aqui para assistir mudanças no diretório
            	//quando automático estiver marcado
            	
            	//Inicializa o Tray Icon
            	TrayIconApplication trayIcon = new TrayIconApplication(configuration);
            	trayIcon.initialize();
            }
        });
    }
}
