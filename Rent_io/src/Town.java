
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
	
    // distance calculation
    public double getDistance(Town a2){
    	final double R = 6372.8; // In kilometers
    	double dLat = Math.toRadians(a2.getLatitude() - this.getLatitude());
    	double dLon = Math.toRadians(a2.getLongitude() - this.getLongitude());
    	double lat1 = Math.toRadians(this.getLatitude());
    	double lat2 = Math.toRadians(a2.getLatitude());
    	double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
    	double c = 2 * Math.asin(Math.sqrt(a));
    	double distance = R * c;
		return distance;
    }
}