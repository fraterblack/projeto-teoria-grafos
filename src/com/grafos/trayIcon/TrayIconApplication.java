package com.grafos.trayIcon;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

import com.grafos.model.Configuration;
import com.grafos.observer.ObserverConfigurationInterface;
import com.grafos.view.SearchView;
import com.grafos.view.ConfigurationView;

public class TrayIconApplication implements ObserverConfigurationInterface {
	private static final String ICON_IMAGE = "../media/icon.png";
	private Configuration configuration;
	
	MenuItem configurationMenuItem;
    CheckboxMenuItem visibleCheckBox;
    MenuItem exitMenuItem;
    
    ConfigurationView configurationView;
    SearchView searchView;
	
	public TrayIconApplication(Configuration configuration) {
		this.configuration = configuration;
	}

	public void initialize() {
		createAndShowGUI();
	}
	
	public SearchView openSearchView(Configuration config) {
		SearchView searchView = new SearchView(config);

		searchView.addWindowListener(new WindowAdapter() {
    		public void windowClosed(WindowEvent e) {
    			visibleCheckBox.setState(false);
    		}
		});
		
		searchView.setVisible(true);
		
		visibleCheckBox.setState(true);
		
		return searchView;
	}
	
	public ConfigurationView openConfigurationView() {
    	configurationView = new ConfigurationView();
    	configurationView.addObserver(this);
		configurationView.setVisible(true);
		
		return configurationView;
    }
	
	public void update(Configuration configuration) {
		this.configuration = configuration;
		
		visibleCheckBox.setEnabled(!configuration.getAutomatic());
	}
	
    private void createAndShowGUI() {
    	final TrayIcon trayIcon = new TrayIcon(createImage(ICON_IMAGE, "Ícone"));
    	
        final PopupMenu popup = new PopupMenu();
        final SystemTray tray = SystemTray.getSystemTray();
        
        //Create a popup menu components
        configurationMenuItem = new MenuItem("Configuração");
        visibleCheckBox = new CheckboxMenuItem("Visível");
        exitMenuItem = new MenuItem("Sair");
        
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
            	openConfigurationView();
            }
        });
        
        visibleCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb1Id = e.getStateChange();
                if (cb1Id == ItemEvent.SELECTED){
                	openSearchView(configuration);
                } else {
                	searchView.setVisible(false);
                }
            }
        });
        
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Dois clíques no ícone");
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }
    
    private Image createImage(String path, String description) {
        URL imageURL = TrayIconApplication.class.getResource(path);
        
        return (new ImageIcon(imageURL, description)).getImage();
    }
}