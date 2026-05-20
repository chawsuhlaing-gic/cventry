package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCEight implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCEightID;
	String NRCEightName;
	
	public String getNRCEightID() {
		return NRCEightID;
	}
	public void setNRCEightID(String nRCEightID) {
		NRCEightID = nRCEightID;
	}
	public String getNRCEightName() {
		return NRCEightName;
	}
	public void setNRCEightName(String nRCEightName) {
		NRCEightName = nRCEightName;
	}
}
