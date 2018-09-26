package com.grafos.trayIcon;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

import com.grafos.model.Configuration;

public class TrayIconApplication {
	private static final String ICON_IMAGE = "../media/icon.png";
	private Configuration configuration;
	
	public TrayIconApplication(Configuration configuration) {
		this.configuration = configuration;
	}

	public void initialize() {
		createAndShowGUI();
	}
	
    private void createAndShowGUI() {
    	final TrayIcon trayIcon = new TrayIcon(createImage(ICON_IMAGE, "Ícone"));
    	
        final PopupMenu popup = new PopupMenu();
        final SystemTray tray = SystemTray.getSystemTray();
        
        //Create a popup menu components
        MenuItem configurationMenuItem = new MenuItem("Configuração");
        CheckboxMenuItem visibleCheckBox = new CheckboxMenuItem("Visível");
        MenuItem exitMenuItem = new MenuItem("Sair");
        
        //Add components to popup menu
        popup.add(configurationMenuItem);
        popup.addSeparator();
        popup.add(visibleCheckBox);
        popup.addSeparator();
        popup.add(exitMenuItem);
        
        //Set disabled when configuration
        visibleCheckBox.setEnabled(!configuration.getAutomatic());
        
        trayIcon.setPopupMenu(popup);
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            return;
        }
        
        configurationMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Configuração foi clicado");
            }
        });
        
        visibleCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb1Id = e.getStateChange();
                if (cb1Id == ItemEvent.SELECTED){
                	JOptionPane.showMessageDialog(null, "Checkbox visível foi marcado");
                } else {
                	JOptionPane.showMessageDialog(null, "Checkbox visível foi desmarcado");
                }
            }
        });
        
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dois clíques no ícone");
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }
    
    protected Image createImage(String path, String description) {
        URL imageURL = TrayIconApplication.class.getResource(path);
        
        return (new ImageIcon(imageURL, description)).getImage();
    }
}