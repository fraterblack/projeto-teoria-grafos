package com.grafos.observer;

import com.grafos.trayIcon.TrayIconApplication;

public interface SubjectTrayIconInterface {
	public void addObserver(ObserverTrayIconInterface o);
    public void removeObserver(ObserverTrayIconInterface o);
    public void notifyObservers(TrayIconApplication trayIcon);
}
