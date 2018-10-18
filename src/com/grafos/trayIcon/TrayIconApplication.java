package com.grafos.trayIcon;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.*;

import com.grafos.lib.DatabaseManager;
import com.grafos.model.Configuration;
import com.grafos.observer.ObserverTrayIconInterface;
import com.grafos.observer.SubjectTrayIconInterface;
import com.grafos.view.SearchView;
import com.grafos.view.ConfigurationView;

public class TrayIconApplication implements SubjectTrayIconInterface {
	private static final String ICON_IMAGE = "../media/icon.png";
	DatabaseManager dm = new DatabaseManager();
	private Configuration configuration;
	
	private Collection<ObserverTrayIconInterface> observers = new HashSet<ObserverTrayIconInterface>();
	
	private MenuItem configurationMenuItem;
	private CheckboxMenuItem visibleCheckBox;
	private MenuItem exitMenuItem;
    
	private ConfigurationView configurationView;
	private SearchView searchView;
	
	public TrayIconApplication() {
		configuration = dm.getConfig();
	}

	public void initialize() {
		createAndShowGUI();
	}
	
	public SearchView openSearchView(Configuration config) {
		searchView = new SearchView(config);

		searchView.addWindowListener(new WindowAdapter() {
    		public void windowClosed(WindowEvent e) {
    			visibleCheckBox.setState(false);
    			
    			notifyObservers(getInstance());
    		}
		});
		
		searchView.setVisible(true);
		
		visibleCheckBox.setState(true);
		
		return searchView;
	}
	
	public void closeSearchView() {
		searchView.setVisible(false);
		
		visibleCheckBox.setState(false);
	}
	
	public ConfigurationView openConfigurationView() {
    	configurationView = new ConfigurationView();
    	
    	configurationView.addWindowListener(new WindowAdapter() {
    		public void windowClosed(WindowEvent e) {
    			configuration = dm.getConfig();
    			
    			visibleCheckBox.setEnabled(!configuration.getAutomatic());
    			
    			notifyObservers(getInstance());
    		}
		});
    	
		configurationView.setVisible(true);
		
		return configurationView;
    }
	
	public void addObserver(ObserverTrayIconInterface o) {
		observers.add(o);
	}

	public void removeObserver(ObserverTrayIconInterface o) {
		observers.remove(o);
	}

	public void notifyObservers(TrayIconApplication trayIcon) {
		Iterator<ObserverTrayIconInterface> it = observers.iterator();
		
		while (it.hasNext()) {
			ObserverTrayIconInterface observer = (ObserverTrayIconInterface) it.next();
			observer.update(trayIcon);
		}
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
	
	private TrayIconApplication getInstance() {
		return this;
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
        
        configurationMenuItem.addActionListener(configurationMenuItemAction());
        
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
    
    private ActionListener configurationMenuItemAction() {
    	ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openConfigurationView();
            }
        };
        
        return action;
    }
    
    private Image createImage(String path, String description) {
        URL imageURL = TrayIconApplication.class.getResource(path);
        
        return (new ImageIcon(imageURL, description)).getImage();
    }
}