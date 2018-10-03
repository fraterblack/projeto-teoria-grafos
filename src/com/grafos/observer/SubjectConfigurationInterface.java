package com.grafos.observer;

import com.grafos.model.Configuration;

public interface SubjectConfigurationInterface {
	public void addObserver(ObserverConfigurationInterface o);
    public void removeObserver(ObserverConfigurationInterface o);
    public void notifyObservers(Configuration configuration);
}
