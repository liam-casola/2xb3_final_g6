
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.*;

public class Main {

	public static void main(String[] args) throws IOException {
		ReadCSV input = new ReadCSV("data/00270040-eng.csv");
		Apartment[] apts = new Apartment[input.getLength()];
		apts = input.readIn();
		JFrame f = new JFrame();
		
		/*
		 * NOTE THE FOLLOWING BUTTONS ARE FOR SHOW
		 */
		//OK button
		JButton b1 = new JButton("OK");
		b1.setBounds(80, 350, 200, 30);
		//Search button
		JButton b2 = new JButton("Search");
		b2.setBounds(320, 350, 200, 30);
		
		/*
		 * NOTE THE FOLLOWING TEXT AREA IS FOR SHOW
		 */
		//instantiate a new mutliline text area
		JTextArea tf = new JTextArea();
		tf.setEditable(true);
		
		f.add(b1);
		f.add(b2);
		f.add(tf);
		
		//add the header to the text area
		tf.setText("Year\tTown/City\t\tAverage Value\n");
		
		//append rows of information to the text area
		for (int  i = 0; i < 20; i++) {
			tf.append(
					apts[i+25889].getYear() + "\t" + 
					apts[i+25889].getLocation() + "\t" + 
					apts[i+25889].getValue() + "\n"
					);
		}
		
		/*
		 * Grab the map image to use in the program based on current working location
		 */
		try {
        	//swap longtitude and latitude based on input files, swap zoom, draw point based on locations (do the math based on current long/la)
            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=43.259605,-79.9259135&zoom=14&size=612x612&scale=3&maptype=roadmap";
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
