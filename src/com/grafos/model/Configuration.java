package com.grafos.model;

public class Configuration {
	private String folder;
	private String sucess;
	private String error;
	private Boolean automatic = false;

	public Configuration(String folder, String sucess, String error, Boolean automatic) {
		this.folder = folder;
		this.sucess = sucess;
		this.error = error;
		this.automatic = automatic;
	}
	
	public String getFolder() {
		return folder;
	}
	
	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	public String getSucess() {
		return sucess;
	}
	
	public void setSucess(String sucess) {
		this.sucess = sucess;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public Boolean getAutomatic() {
		return automatic;
	}

	public void setAutomatic(Boolean automatic) {
		this.automatic = automatic;
	}
}
