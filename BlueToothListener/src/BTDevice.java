
public class BTDevice {

	private String name;
	private String BTURL;
	
	public BTDevice(String name,String BTURL) {
		this.name=name;
		this.BTURL=BTURL;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public String getBTURL() {
		return BTURL;
	}
	
	
}
