
public class Apartment {
	private int year;
	private int population;
	private String structure;
	private String unit;
	private double value;
	
	public Apartment(
					int year,  
					int population,
					String structure,
					String unit,
					double value
					) {
		this.year = year;
		this.population = population;
		this.structure = structure;
		this.unit = unit;
		this.value = value;
	}
	
	public int getYear() {
		return year;
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
	
	public double getValue() {
		return value;
	}
}
