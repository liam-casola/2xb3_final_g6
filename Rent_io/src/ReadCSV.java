
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
	private String filename;
	private int length;
	
	public ReadCSV(String fileName) throws IOException {
		this.filename = fileName;
		size();
	}
	
	public int getLength() {
		return length;
	}
	
	private void size() {
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
		this.length = lines - 1; //ignore the first line
	}
	
	public Apartment[] readIn() throws IOException {
		Apartment [] apts = new Apartment[length];
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String row;
		row = reader.readLine(); //ignore the first line
		for (int i = 0; i < length; i++) {
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
			
			apts[i] = new Apartment(
					Integer.parseInt(s[0]),
					s[1].replace("\"", ""),
					Integer.parseInt(s[2]),
					s[3],
					s[4],
					s[5],
					s[6],
					Double.parseDouble(s[7])
					);
		}
		return apts;
	}
}
