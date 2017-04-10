package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
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
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//////////////////////////////
		//////////////////////////////
		JPanel map = new JPanel();
		f.add(map);
		f.setVisible(true);
		JLabel start = new JLabel("<html>Please select your city: <br></html>");
		map.add(start);
		map.setVisible(true);
		String[] choices = new String[towns.length+1];
		choices[0] = " ";
		for (int i=1;i<towns.length+1;i++){
			choices[i] = towns[i-1].getLocation();
		}

		final JComboBox<String> cb = new JComboBox<String>(choices);
		cb.setVisible(true);
		map.add(cb);
		Scanner reader = new Scanner(System.in);
		f.pack();
		f.setSize(502, 1000);

		//////////////////////////////
		//////////////////////////////
		JButton updateLocation = new JButton("OK");
		updateLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String youSelected = "You have seleced: "+ (String)cb.getSelectedItem();;
				map.add(new JLabel(youSelected));
				f.add(map);
				f.setVisible(true);
				map.setVisible(true);
				String searchRadius = "<html>Please select the search radius (in km)<br></html>";
				map.add(new JLabel(searchRadius));
				String[] radius = {"50", "100", "150", "200", "250", "300", "350", "400", "500", "600", "700", "800", "900", "1000"};
				final JComboBox<String> radiusDropDown = new JComboBox<String>(radius);
				map.add(radiusDropDown);
				radiusDropDown.setVisible(true);
				JButton confirmRadius = new JButton("Start Search");
				map.add(confirmRadius);
				confirmRadius.setVisible(true);
				map.setVisible(true);
				confirmRadius.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int zoomLevel = 0;
						int searchRadius = Integer.parseInt((String)radiusDropDown.getSelectedItem());
						if (searchRadius > 2000) {
							zoomLevel = 2;
						} else if (searchRadius > 1000) {
							zoomLevel = 4;
						} else if (searchRadius > 210) {
							zoomLevel = 6;
						} else if (searchRadius > 140) {
							zoomLevel = 7;
						} else if (searchRadius > 50) {
							zoomLevel = 8;
						} else {
							zoomLevel = 9;
						}
						
						JLabel townsHeading = new JLabel("<html>The following towns are within this area</br></html>");
						map.add(townsHeading);
						f.setVisible(true);

						int totalCities = 0;
						for (Edge edge : g.adj(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))) {
							if (edge.weight() < searchRadius) {
								String cityLetter = String.valueOf((char)(totalCities + 65));
								if (totalCities > 25) {
									cityLetter = "*";
								}
								Object row[]={totalCities,edge.weight(),towns[edge.other(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))].getLocation() };
								JLabel cityData = new JLabel(totalCities+"       " + (int)edge.weight()+"km       " + towns[edge.other(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))].getLocation());
								map.add(cityData);
								/*
								System.out.print( totalCities+ "\t");
								System.out.print((int)edge.weight() + "km\t");
								System.out.println(towns[edge.other(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))].getLocation());
								*/
								totalCities++;
							}
						}

						//create the array of valid towns to check
						Town[] validTowns = new Town[totalCities];
						int validCounter = 0;
						for (Edge ed : g.adj(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))) {
							if (ed.weight() < searchRadius) {
								validTowns[validCounter] = towns[ed.other(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))];
								validCounter++;
							}
						}
						try {
							//test using 150 and 200km
							//swap longtitude and latitude based on input files, swap zoom, draw point based on locations (do the math based on current long/la)
							String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + towns[Arrays.asList(choices).indexOf((String)cb.getSelectedItem())].getLatitude() + ","+ towns[Arrays.asList(choices).indexOf((String)cb.getSelectedItem())].getLongitude() + "&zoom="+zoomLevel+"&size=612x612&scale=3&maptype=roadmap";
							for (int i = 0; i < validTowns.length; i++){
								imageUrl = imageUrl + "&markers=color:red%7Clabel:" + i + "%7C" + validTowns[i].getLatitude() +"," + validTowns[i].getLongitude();
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
						} catch (IOException er) {
							er.printStackTrace();
							System.exit(1);
						}
						//add the map image to the program
						map.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(500, 550,   java.awt.Image.SCALE_SMOOTH))));
						f.add(map);
						f.setVisible(true);
						JLabel sub1 = new JLabel("<html><br>Enter the method of sorting: </html>");
						map.add(sub1);
						String[] method = {"","all","price","price history"};
						final JComboBox<String> methodDropDown = new JComboBox<String>(method);
						map.add(methodDropDown);
						JLabel sub = new JLabel("<html><br>Enter the number of the Town for more info: </html>");
						map.add(sub);
						f.add(map);
						String[] cityNum = new String [validTowns.length];
						for (int i=0;i<validTowns.length;i++){
							cityNum[i] = "" + i;
						}
						final JComboBox<String> cityNumDropDown = new JComboBox<String>(cityNum);
						map.add(cityNumDropDown);
						JButton confirmSubcity = new JButton("Start");
						map.add(confirmSubcity);
						f.add(map);
						f.setVisible(true);
						map.setVisible(true);
						confirmSubcity.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent eee) {
								if (((String)methodDropDown.getSelectedItem()).equals("all")){
									JFrame textf = new JFrame();
									JPanel text = new JPanel();
									textf.setSize(500, 1000);
									int cityNumValue = Integer.parseInt((String)cityNumDropDown.getSelectedItem());
									JTextArea details = new JTextArea(10, 38);
									JScrollPane sp = new JScrollPane(details);
									textf.getContentPane().add(sp);
									text.add(sp);
									sp.setVisible(true);
									details.append(ProcessRequest.currentPrices(validTowns[cityNumValue]));
									text.add(details);
									details.setVisible(true);
									text.setVisible(true);
									textf.add(text);
									textf.setVisible(true);
								}
								
								if (((String)methodDropDown.getSelectedItem()).equals("price")){
									JFrame textf = new JFrame();
									JPanel text = new JPanel();
									textf.setSize(700, 1000);
									JTextArea details = new JTextArea(10, 38);
									int cityNumValue = Integer.parseInt((String)cityNumDropDown.getSelectedItem());
									
									String[] maxPrice = {"500", "700", "900", "1100", "1300", "1500", "1700", "1900"};
									final JComboBox<String> priceDropDown = new JComboBox<String>(maxPrice);
									priceDropDown.setVisible(true);
									text.add(priceDropDown);
									JButton confirmPrice = new JButton("Start Search");
									text.add(confirmPrice);
									confirmPrice.setVisible(true);
									text.setVisible(true);
									textf.setVisible(true);
									textf.add(text);
									confirmPrice.addActionListener(new ActionListener() {	
										public void actionPerformed(ActionEvent e) {
											details.append(ProcessRequest.priceLimit(validTowns[cityNumValue], Integer.parseInt((String)priceDropDown.getSelectedItem())));
											text.add(details);
											textf.add(text);
										}
										});
									
								}
								if (((String)methodDropDown.getSelectedItem()).equals("price history")){
									JFrame textf = new JFrame();
									JPanel text = new JPanel();
									textf.setSize(1000, 1000);
									int cityNumValue = Integer.parseInt((String)cityNumDropDown.getSelectedItem());
									JTextArea details = new JTextArea(10, 38);
									JScrollPane sp = new JScrollPane(details);
									textf.getContentPane().add(sp);
									text.add(sp);
									sp.setVisible(true);
									details.append(ProcessRequest.priceHistory(validTowns[cityNumValue]));
									text.add(details);
									details.setVisible(true);
									text.setVisible(true);
									textf.add(text);
									textf.setVisible(true);
								}
								
								
								
								
								
								
								
							}
					
							
						});
						
						
						
						
						
						



		/*
		 * Grab the map image to use in the program based on current working location
		 */
						
						
			/////////////
						
						

					}
				});
				f.add(map);
				f.setVisible(true);
				
				

			}
		});map.add(updateLocation);
		f.add(map);
		f.setVisible(true);
		


		/*
		int searchRadius = reader.nextInt();
		if (searchRadius > 2000) {
			zoomLevel = 2;
		} else if (searchRadius > 1000) {
			zoomLevel = 4;
		} else if (searchRadius > 210) {
			zoomLevel = 6;
		} else if (searchRadius > 140) {
			zoomLevel = 7;
		} else if (searchRadius > 50) {
			zoomLevel = 8;
		} else {
			zoomLevel = 9;
		}
		
		System.out.println("The following towns are within this area\n----------------------------------");
		System.out.println("Marker\tDistance\tTown");
		int totalCities = 0;
		System.out.println(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()));
		for (Edge e : g.adj(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))) {
			if (e.weight() < searchRadius) {
				String cityLetter = String.valueOf((char)(totalCities + 65));
				if (totalCities > 25) {
					cityLetter = "*";
				}
				System.out.print( totalCities+ "\t");
				System.out.print((int)e.weight() + "km\t");
				System.out.println(towns[e.other(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))].getLocation());
				totalCities++;
			}
		}
		
		//create the array of valid towns to check
		Town[] validTowns = new Town[totalCities];
		int validCounter = 0;
		for (Edge e : g.adj(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))) {
			if (e.weight() < searchRadius) {
				validTowns[validCounter] = towns[e.other(Arrays.asList(choices).indexOf((String)cb.getSelectedItem()))];
				validCounter++;
			}
		}
		/*
		 * Grab the map image to use in the program based on current working location
		 */
		/*
		try {
			//test using 150 and 200km
        	//swap longtitude and latitude based on input files, swap zoom, draw point based on locations (do the math based on current long/la)
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + towns[Arrays.asList(choices).indexOf((String)cb.getSelectedItem())].getLatitude() + ","+ towns[Arrays.asList(choices).indexOf((String)cb.getSelectedItem())].getLongitude() + "&zoom="+zoomLevel+"&size=612x612&scale=3&maptype=roadmap";
            for (int i = 0; i < validTowns.length; i++){
            	imageUrl = imageUrl + "&markers=color:red%7Clabel:" + i + "%7C" + validTowns[i].getLatitude() +"," + validTowns[i].getLongitude();
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
        map.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,   java.awt.Image.SCALE_SMOOTH))));
		f.add(map);
		*/
        //TO DO
        //add user input


        //add circle radius around each point on map based on input distance
        

		//f.setLayout(null);

		/*
		System.out.println("Enter the number of the Town for more info");
		int subCityInput = reader.nextInt();
		ProcessRequest.currentPrices(validTowns[subCityInput]);
		*/
	}

}
