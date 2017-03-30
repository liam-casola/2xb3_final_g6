
import java.io.IOException;

import javax.swing.*;

public class Main {

	public static void main(String[] args) throws IOException {
		ReadCSV input = new ReadCSV("data/00270040-eng.csv");
		Apartment[] apts = new Apartment[input.getLength()];
		apts = input.readIn();
		JFrame f = new JFrame();
		
		JButton b1 = new JButton("OK");
		b1.setBounds(80, 350, 200, 30);
		JButton b2 = new JButton("Search");
		b2.setBounds(320, 350, 200, 30);
		
		JTextArea tf = new JTextArea();
		tf.setEditable(true);
		
		f.add(b1);
		f.add(b2);
		f.add(tf);
		
		tf.setText("Year\tTown/City\t\tAverage Value\n");
		
		for (int  i = 0; i < 20; i++) {
			tf.append(
					apts[i+25889].getYear() + "\t" + 
					apts[i+25889].getLocation() + "\t" + 
					apts[i+25889].getValue() + "\n"
					);
		}
		
		f.pack();
		f.setSize(720, 480);
		//f.setLayout(null);
		f.setVisible(true);
		
	}

}
