package src;

import java.util.ArrayList;

public class ProcessRequest {
	public static void priceTrend(Town t) {
		//determine the change in price every year for an apartment
		ArrayList<Apartment> al = new ArrayList<Apartment>();
		ArrayList<Double> cv = new ArrayList<Double>();
		for(int i = 1; i < t.getAptsList().length; i++) {
			if (t.getAptsList()[i - 1].getYear() < t.getAptsList()[i].getYear() && t.getAptsList()[i - 1].getValue() > 0 && t.getAptsList()[i].getValue() > 0) {
				al.add(t.getAptsList()[i - 1]);
			} else {
				al.add(t.getAptsList()[i - 1]);
				Apartment[] a = al.toArray(new Apartment[al.size()]);
				cv.add(averagePriceChange(a));
				System.out.println(cv.get(0).toString());
				al.clear();
			}
			
		}
	}
	
	public static double averagePriceChange(Apartment[] a) {
		double changeValue = 0;
		for (int i = 1; i < a.length; i++) {
			changeValue += a[i].getValue() - a[i - 1].getValue();
		}
		changeValue = changeValue / (a.length - 1);
		return changeValue;
	}
	
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
}
