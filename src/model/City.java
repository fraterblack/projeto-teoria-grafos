package model;

public class City extends ModelBase {
	int cityId;
	String CityName;
	double distance;
	
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return CityName;
	},
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public City(int cityId, String cityName, double distance) {
		super();
		this.cityId = cityId;
		CityName = cityName;
		this.distance = distance;
	}
	
}
