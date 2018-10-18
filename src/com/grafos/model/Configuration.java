package com.grafos.model;

public class Configuration {
	private String rootFolder;
	private String successFolder;
	private String errorFolder;
	private Boolean automatic = false;

	public Configuration(String folder, String sucess, String error, Boolean automatic) {
		this.rootFolder = folder;
		this.successFolder = sucess;
		this.errorFolder = error;
		this.automatic = automatic;
	}
	
	public String getRootFolder() {
		return rootFolder;
	}
	
	public String getSuccessFolder() {
		return successFolder;
	}
	
	public String getErrorFolder() {
		return errorFolder;
	}
	
	public Boolean getAutomatic() {
		return automatic;
	}
}
