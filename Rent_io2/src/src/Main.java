package src;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Town[] towns = ReadCSV.loadTowns();

		//initialize and fill the graph
		Graph g = new Graph(towns.length);
		for (int i = 0; i < towns.length - 1; i++) {
			for (int j = i + 1; j < towns.length - i; j++) {
					Edge e = new Edge(i, j, towns[i].distanceTo(towns[j]));
					g.addEdge(e);
			}
		}
		
		JFrame f = new JFrame();
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the index of the central city");
		int cityIndex = reader.nextInt();
		System.out.println("You have seleced: "+towns[cityIndex].getLocation());
		System.out.println("Please enter the search radius in km");
		int searchRadius = reader.nextInt();
		System.out.println("The following towns are within this area\n----------------------------------");
		
		int totalCities = 0;
		for (Edge e : g.adj(cityIndex)) {
			if (e.weight() < searchRadius) {
				System.out.println(towns[e.other(cityIndex)].getLocation());
				totalCities++;
			}
		}
		
		//create the array of valid towns to check
		Town[] validTowns = new Town[totalCities];
		int validCounter = 0;
		for (Edge e : g.adj(cityIndex)) {
			if (e.weight() < searchRadius) {
				validTowns[validCounter] = towns[e.other(cityIndex)];
			}
		}
		/*
		 * Grab the map image to use in the program based on current working location
		 */
		try {
        	//swap longtitude and latitude based on input files, swap zoom, draw point based on locations (do the math based on current long/la)
		String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + towns[cityIndex].getLatitude() + ","+ towns[cityIndex].getLongitude() + "&zoom=4&size=612x612&scale=3&maptype=roadmap";
            for (int i=0;i<validTowns.length;i++){
            	imageUrl = imageUrl+ "&markers=color:blue%7Clabel:S%7C" + validTowns[i].getLatitude() +"," + validTowns[i].getLongitude();
            }
			
            String destinationFile = "image.jpg";
            String str = destinationFile;
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
		//add the map image to the program
        f.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,   java.awt.Image.SCALE_SMOOTH))));
		
        //TO DO
        //add user input
        //add circle radius around each point on map based on input distance
        
		f.pack();
		f.setSize(720, 480);
		//f.setLayout(null);
		f.setVisible(true);
		
	}

}
