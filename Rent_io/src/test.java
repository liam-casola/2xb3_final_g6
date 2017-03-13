import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class test {
	public static void main(String[] args) throws IOException {

		CSVReader reader1 = new CSVReader(new FileReader("data/00270040-eng.csv"));
		CSVReader reader2 = new CSVReader(new FileReader("data/00270040-eng.csv"));

		String[] nextLine1 = reader1.readNext();
		int counter = 0;

		while ((nextLine1 = reader1.readNext()) != null) {
			if (Integer.parseInt(nextLine1[0]) == 2016) {
				counter++;
			}
		}
		System.out.println(counter);
		
		String[] nextLine2 = reader2.readNext();
		Apartment[] apartments = new Apartment[counter];
		int index = 0;
		
		while ((nextLine2 = reader2.readNext()) != null) {

			if (Integer.parseInt(nextLine2[0]) == 2016) {
				String[] line = nextLine2[1].split(", ");

				switch (line[1]) {
				case "Newfoundland and Labrador":
					line[1] = "NL";
					break;

				case "Ontario":
					line[1] = "ON";
					break;

				case "Quebec":
					line[1] = "QC";
					break;

				case "Nova Scotia":
					line[1] = "NS";
					break;

				case "New Brunswick":
					line[1] = "NB";
					break;

				case "Manitoba":
					line[1] = "MB";
					break;

				case "British Columbia":
					line[1] = "BC";
					break;

				case "Prince Edward Island":
					line[1] = "PE";
					break;

				case "Saskatchewan":
					line[1] = "SK";
					break;

				case "Alberta":
					line[1] = "AB";
					break;

				case "Northwest Territories":
					line[1] = "NT";
					break;

				case "Yukon":
					line[1] = "YT";
					break;

				case "Nunavut":
					line[1] = "NU";
					break;		

				case "New Brunswick part":
					if (line[2].equals("New Brunswick/Quebec")) {
						line[1] = "NB/QC";
						line[0] += "(New Brunswick part)";
					}
					break;

				case "Quebec part":
					if (line[2].equals("New-Brunswick/Quebec")) {
						line[1] = "NB/QC";
						line[0] += "(Quebec part)";
					} else if (line[2].equals("Ontario/Quebec")) {
						line[1] = "ON/QC";
						line[0] += "(Quebec part)";
					}
					break;

				case "Ontario/Quebec":
					line[1] = "ON/QC";
					break;
					
				case "Ontario part":
					if (line[2].equals("Ontario/Quebec")) {
						line[1] = "ON/QC";
						line[0] += "(Ontario part)";
					}
					break;

				case "Saskatchewan part":
					if (line[2].equals("Saskatchewan/Alberta")) {
						line[1] = "SK/AB";
						line[0] += "(Saskatchewan part)";
					}
					break;
		
				case "Alberta part":
					if (line[2].equals("Saskatchewan/Alberta")) {
						line[1] = "SK/AB";
						line[0] += "(Alberta part)";
					}
					break;

				default:
					line[1] = "0000000000000";
					break;
				}

				apartments[index] = new Apartment(line[0], line[1], nextLine2[4], nextLine2[7]);
				System.out.println(apartments[index].getCity() + ", " + apartments[index].getProvince() + ", Type: " + apartments[index].getType() + ", Value: " + apartments[index].getValue());
				index++;
			}
		}

	}

}