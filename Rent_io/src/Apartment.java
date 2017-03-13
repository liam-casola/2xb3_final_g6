

public class Apartment implements Comparable<Apartment>{
	
	private String city;
	private String province;
	private String type;
	private String value;
	
	public Apartment(String c, String p, String t, String v) {
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
	
	public String getType() {
		return this.type;
	}
	
	public String getValue() {
		return this.value;
	}

	@Override
	public int compareTo(Apartment o) {
		// TODO Auto-generated method stub
		if(this.value.compareTo(o.value) < 0) {
			return -1;
		} else if(this.value.compareTo(o.value) > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	
}
