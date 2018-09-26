package com.grafos.controller;

import javax.swing.SwingUtilities;

import com.grafos.model.Configuration;
import com.grafos.trayIcon.TrayIconApplication;

public class MainController {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//Verificar aqui se j� existe arquivo de configura��o, caso n�o tenha, abril�-lo��
            	
            	//Temporariamente a configura��o est� sendo gerada aqui (Espera-se que as configura��es venham de um arquivo de configura��o)
            	Configuration configuration = new Configuration("", "", "", true);
            	
            	//Cria as thread (ou inst�ncia o objeto que far� isto) aqui para assistir mudan�as no diret�rio
            	//quando autom�tico estiver marcado
            	
            	//Inicializa o Tray Icon
            	TrayIconApplication trayIcon = new TrayIconApplication(configuration);
            	trayIcon.initialize();
            }
        });
    }
}
