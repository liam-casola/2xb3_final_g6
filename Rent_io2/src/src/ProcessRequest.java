

package src;

import java.util.ArrayList;

public class ProcessRequest {
	
	//prints the price of all structures and unit types in the selected town
	public static String currentPrices(Town t) {
		String result = "";
		for (int i = 0; i < t.getAptsList().length; i++) {
			if (t.getAptsList()[i].getYear() == 2016 && t.getAptsList()[i].getValue() > 0) {
				result+= ("--Building Type--\n" + t.getAptsList()[i].getStructure());
				result+=("--Unit Size--\n" + t.getAptsList()[i].getUnit());
				result+=("--Average Unit Price--\n$" + t.getAptsList()[i].getValue() + "/m" + "\n");
			}
		}
		return result;
	}
	
	public static String priceHistory(Town t) {
		double change = 0;
		double counter = 0;
		String result = "";
		for (int i = 0; i < t.getAptsList().length; i++) {
			
			if (i < t.getAptsList().length - 1 && t.getAptsList()[i].getValue() > 0) {
				counter++;
				if (t.getAptsList()[i].getYear() < t.getAptsList()[i + 1].getYear()) {
					change = t.getAptsList()[i + 1].getValue() - t.getAptsList()[i].getValue();
				} else {
					result+="$" + ((double)(int)(change / counter * 100)) / 100+ "\n";
					result+="\tis the average price increase per year for " + t.getAptsList()[i].getStructure() + " with " + t.getAptsList()[i].getUnit()+"\n"+"\n";
					change = 0;
					counter = 0;
				}
			}
		}
		return result;
	}
	
	public static String priceLimit(Town t, double r) {
		String result = "";
		for (int i = 0; i < t.getAptsList().length; i++) {
			if (t.getAptsList()[i].getYear() == 2016 && t.getAptsList()[i].getValue() > 0 && t.getAptsList()[i].getValue() < r) {
				result +=("--Building Type--\n" + t.getAptsList()[i].getStructure()+"\n");
				result +=("--Unit Size--\n" + t.getAptsList()[i].getUnit())+"\n";
				result +=("--Average Unit Price--\n$" + t.getAptsList()[i].getValue() + "/m"+"\n");
			}
		}
		return result;
	}
}
