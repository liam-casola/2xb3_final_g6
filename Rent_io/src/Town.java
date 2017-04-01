
public class Town {
	private String location;
	private double latitude;
	private double longitude;
	private Apartment[] aptsList;
	
	public Town(String location, double latitude, double longitude, Apartment[] aptsList) {
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.aptsList = aptsList;
	}
	
	public String getLocation() {
		return location;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public Apartment[] getAptsList() {
		return aptsList;
	}
}
