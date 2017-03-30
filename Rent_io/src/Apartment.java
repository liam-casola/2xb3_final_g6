public class Apartment {
	private int year;
	private String location;
	private int population;
	private String structure;
	private String unit;
	private double latitude;
	private double longitude;
	private double value;
	
	public Apartment(
					int year, 
					String location, 
					int population,
					String structure,
					String unit,
					double vector,
					double coordinate,
					double value
					) {
		this.year = year;
		this.location = location;
		this.population = population;
		this.structure = structure;
		this.unit = unit;
		this.latitude = vector;
		this.longitude = coordinate;
		this.value = value;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getLocation() {
		return location;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public String getStructure() {
		return structure;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getValue() {
		return value;
	}
	
    // distance calculation
    public double getDistance(Apartment a2){
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