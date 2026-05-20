package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCTwo implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCTwoID;
	String NRCTwoName;

	public String getNRCTwoID() {
		return NRCTwoID;
	}
	public void setNRCTwoID(String nRCTwoID) {
		NRCTwoID = nRCTwoID;
	}
	public String getNRCTwoName() {
		return NRCTwoName;
	}
	public void setNRCTwoName(String nRCTwoName) {
		NRCTwoName = nRCTwoName;
	}
}
