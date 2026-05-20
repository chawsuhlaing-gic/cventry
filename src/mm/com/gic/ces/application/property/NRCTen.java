package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCTen implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCTenID;
	String NRCTenName;
	
	public String getNRCTenID() {
		return NRCTenID;
	}
	public void setNRCTenID(String nRCTenID) {
		NRCTenID = nRCTenID;
	}
	public String getNRCTenName() {
		return NRCTenName;
	}
	public void setNRCTenName(String nRCTenName) {
		NRCTenName = nRCTenName;
	}
}
