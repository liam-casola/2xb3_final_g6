package src;

import java.util.ArrayList;

public class ProcessRequest {
	
	//prints the price of all structures and unit types in the selected town
	public static void currentPrices(Town t) {
		for (int i = 0; i < t.getAptsList().length; i++) {
			if (t.getAptsList()[i].getYear() == 2016 && t.getAptsList()[i].getValue() > 0) {
				System.out.println("--Building Type--\n" + t.getAptsList()[i].getStructure());
				System.out.println("--Unit Size--\n" + t.getAptsList()[i].getUnit());
				System.out.println("--Average Unit Price--\n$" + t.getAptsList()[i].getValue() + "/m");
				System.out.println();
			}
		}
	}
	
	public static void priceHistory(Town t) {
		double change = 0;
		double counter = 0;
		
		for (int i = 0; i < t.getAptsList().length; i++) {
			if (i < t.getAptsList().length - 1 && t.getAptsList()[i].getValue() > 0) {
				counter++;
				if (t.getAptsList()[i].getYear() < t.getAptsList()[i + 1].getYear()) {
					change = t.getAptsList()[i + 1].getValue() - t.getAptsList()[i].getValue();
				} else {
					System.out.print("$" + ((double)(int)(change / counter * 100)) / 100);
					System.out.println("\tis the average price increase per year for " + t.getAptsList()[i].getStructure() + " with " + t.getAptsList()[i].getUnit());
					change = 0;
					counter = 0;
				}
			}
		}
	}
	
	public static void priceLimit(Town t, double r) {
		for (int i = 0; i < t.getAptsList().length; i++) {
			if (t.getAptsList()[i].getYear() == 2016 && t.getAptsList()[i].getValue() > 0 && t.getAptsList()[i].getValue() < r) {
				System.out.println("--Building Type--\n" + t.getAptsList()[i].getStructure());
				System.out.println("--Unit Size--\n" + t.getAptsList()[i].getUnit());
				System.out.println("--Average Unit Price--\n$" + t.getAptsList()[i].getValue() + "/m");
				System.out.println();
			}
		}
	}
}
