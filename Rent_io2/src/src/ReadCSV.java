package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
	private static String primaryDS = "data/00270040-eng.csv";
	private static int PDSlength;
	private static String locationDS = "data/LATandLNG.csv";
	private static int LDSlength;
	
	//get the number of lines in the Primary Data Set (size() method needs to be run before this is called)
	public int getPDSLength() {
		return PDSlength;
	}
	
	//get the numberof lines in the location data set (size() method needs to be run before this is called)
	public int getLDSLength() {
		return LDSlength;
	}
	
	//given a filename, load the file and count how many lines it contains then return that value
	private static int size(String filename) {
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			while (reader.readLine() != null) {
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}
	
	//determine the size of the array for each apartment array located in towns. This is a jagged array length as each
	//town could have differerent amounts of recorded data
	private static int[] jagSize() throws IOException {
		int[] size = new int[LDSlength];
		BufferedReader reader = new BufferedReader(new FileReader(primaryDS));
		String previousName = null;;
		String row;
		row = reader.readLine(); //ignore the first line
		int locationCounter = 0;
		int counter = 0;
		
		for (int i = 0; i < PDSlength - 1; i++) {
			row = reader.readLine();
			String[] s = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
			if (i > 0 && !previousName.equals(s[1].replace("\"", ""))) {
				size[locationCounter] = counter;
				locationCounter++;
				counter = 0;
			}
			counter++;
			previousName = s[1].replace("\"", "");
			if (i == PDSlength - 2 ) {
				size[locationCounter] = counter;
			}
		}
		return size;
	}
	
	//construct an array of apartment arrays using the jagged avlues determined from jagSize. Then populate each
	//subarray with the apartments that are relevant
	private static Apartment[][] loadApartments() throws IOException {
		PDSlength = size(primaryDS);
		//ignore the first line in the primary dataset
		Apartment [][] apts = new Apartment[LDSlength][];
		int[] inSize = jagSize();
		
		for (int i = 0; i < apts.length; i++) {
			apts[i] = new Apartment[inSize[i]];
		}

		int jagCount = 0;
		BufferedReader reader = new BufferedReader(new FileReader(primaryDS));
		String previousName = null;;
		String row;
		row = reader.readLine(); //ignore the first line
		int locationCounter = 0;
		for (int i = 0; i < PDSlength - 1; i++) {
			row = reader.readLine();
			String[] s = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
			//Some population fields are empty
			try {
				Integer.parseInt(s[2]);
			} catch (NumberFormatException e) {
				s[2] = "0";
			}
			
			//some values contain ".." and "F"
			try {
				Double.parseDouble(s[7]);
			} catch (NumberFormatException e) {
				s[7] = "0";
			}
			
			//check if the location changed
			if (i > 0 && !previousName.equals(s[1].replace("\"", ""))) {
				locationCounter++;
				jagCount = 0;
			}
			previousName = s[1].replace("\"", "");
			
			//create the object
			apts[locationCounter][jagCount] = new Apartment(Integer.parseInt(s[0]), Integer.parseInt(s[2]), s[3], s[4], Double.parseDouble(s[7]));
			jagCount++;
		}
		return apts;
	}
	
	//constrcut the array of towns by reading in their data from the latlng file and inserting all relevent
	//apartment information into each one
	public static Town[] loadTowns() throws IOException {
		LDSlength = size(locationDS);
		Town [] towns = new Town[LDSlength];
		Apartment[][] apts = loadApartments();
		
		BufferedReader reader = new BufferedReader(new FileReader(locationDS));
		String row;
		for (int i = 0; i < towns.length; i++) {
			row = reader.readLine();
			//splits a string on all ',' excluding ',' inside of a pair of ""
			String[] s = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			towns[i] = new Town(s[0].replace("\"", ""), Double.parseDouble(s[1]), Double.parseDouble(s[2]), apts[i]);
		}
		return towns;
	}
}
