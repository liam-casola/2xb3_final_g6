
public class Apartment {
	private int year;
	private String location;
	private int population;
	private String structure;
	private String unit;
	private String vector;
	private String coordinate;
	private double value;
	
	public Apartment(
					int year, 
					String location, 
					int population,
					String structure,
					String unit,
					String vector,
					String coordinate,
					double value
					) {
		this.year = year;
		this.location = location;
		this.population = population;
		this.structure = structure;
		this.unit = unit;
		this.vector = vector;
		this.coordinate = coordinate;
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
	
	public String getVector() {
		return vector;
	}
	
	public String getVCoordinate() {
		return coordinate;
	}
	
	public double getValue() {
		return value;
	}
}
