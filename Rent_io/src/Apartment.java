
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
}
