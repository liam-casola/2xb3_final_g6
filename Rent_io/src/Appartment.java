

public class Appartment implements Comparable<Appartment>{
	
	private String city;
	private String province;
	private int type;
	private int value;
	
	public Appartment(String c, String p, int t, int v) {
		this.city = c;
		this.province = p;
		this.type = t;
		this.value = v;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getProvince() {
		return this.province;
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getValue() {
		return this.value;
	}

	@Override
	public int compareTo(Appartment o) {
		// TODO Auto-generated method stub
		if(this.value < o.value) {
			return -1;
		} else if(this.value > o.value) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	
}
