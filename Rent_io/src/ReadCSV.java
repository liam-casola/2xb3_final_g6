
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
	private static String primaryDS = "data/00270040-eng.csv";
	private static int PDSlength;
	private static String locationDS = "data/LATandLNG.csv";
	private static int LDSlength;
	
	public int getPDSLength() {
		return PDSlength;
	}
	
	public int getLDSLength() {
		return LDSlength;
	}
	
	private static int size(String filename) {
		int lines = 0;
		int length;
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
		return length = lines; //ignore the first line
	}
	
	public static Apartment[] readIn() throws IOException {
		//read in the locations into an array first
		LDSlength = size(locationDS);
		String[][] lctn = new String[LDSlength][3];
		BufferedReader preReader = new BufferedReader(new FileReader(locationDS));
		String row;
		for (int i = 0; i < LDSlength; i++) {
			row = preReader.readLine();
			String[] s = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			lctn[i][0] = s[0];
			lctn[i][1] = s[1];
			lctn[i][2] = s[2];
		}
		
		PDSlength = size(primaryDS);
		//ignore the first line in the primary dataset
		Apartment [] apts = new Apartment[PDSlength - 1];
		BufferedReader reader = new BufferedReader(new FileReader(primaryDS));
		row = null;
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
				Double.parseDouble(s[5]);
				Double.parseDouble(s[6]);
				Double.parseDouble(s[7]);
			} catch (NumberFormatException e) {
				s[5] = "0";
				s[6] = "0";
				s[7] = "0";
			}
			
			//check if the location changed
			apts[i] = new Apartment(
					Integer.parseInt(s[0]),
					s[1].replace("\"", ""),
					Integer.parseInt(s[2]),
					s[3],
					s[4],
					Double.parseDouble(lctn[locationCounter][1]),
					Double.parseDouble(lctn[locationCounter][2]),
					Double.parseDouble(s[7])
					);
		}
		return apts;
	}
}
