package src;

public class Apartment {
	private int year;
	private int population;
	private String structure;
	private String unit;
	private double value;
	
	//creates a new apartment with the given properties
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
	
	//returns the year associated with the apartment
	public int getYear() {
		return year;
	}
	
	//returns the population associated with the apartment
	//this value is not going to be used
	public int getPopulation() {
		return population;
	}
	
	//returns the type of structure the apartment resides in
	public String getStructure() {
		return structure;
	}
	
	//retunrs the size of the unit the apartment is
	public String getUnit() {
		return unit;
	}
	
	//reutnrs the average cost of the apartment per month
	public double getValue() {
		return value;
	}
}
