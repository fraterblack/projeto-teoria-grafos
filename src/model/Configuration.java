package model;

public class Configuration {
	String folder;
	String sucess;
	String error;
	
	public Configuration(String folder, String sucess, String error) {
		this.folder = folder;
		this.sucess = sucess;
		this.error = error;
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
	
}
