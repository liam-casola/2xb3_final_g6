package src;

public class Town {
	private String location;
	private double latitude;
	private double longitude;
	private Apartment[] aptsList;
	
	//initalize a town with the 4 values it has
	public Town(String location, double latitude, double longitude, Apartment[] aptsList) {
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.aptsList = aptsList;
	}
	
	//get the name of the town
	public String getLocation() {
		return location;
	}
	
	//get the latitude of the town
	public double getLatitude() {
		return latitude;
	}
	
	//get the longitude of the town
	public double getLongitude() {
		return longitude;
	}
	
	//get the array of relevent apartments to this town
	public Apartment[] getAptsList() {
		return aptsList;
	}
	
    // distance calculation from this town to another
	public double distanceTo(Town t) {
		//becasue latitude and longitude are not the representation of coordinates on a 2D grid, special calculations
		//need to be made to determine the distance between the two points
		final double R = 6372.8; // In kilometers
		double dLat = Math.toRadians(t.getLatitude() - this.getLatitude());
	    	double dLon = Math.toRadians(t.getLongitude() - this.getLongitude());
	    	double lat1 = Math.toRadians(this.getLatitude());
	    	double lat2 = Math.toRadians(t.getLatitude());
	    	double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
	    	double c = 2 * Math.asin(Math.sqrt(a));
	    	double distance = R * c;
	    	return distance;
    }
}
