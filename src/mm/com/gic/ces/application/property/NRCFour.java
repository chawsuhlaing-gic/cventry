package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCFour implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCFourID;
	String NRCFourName;
	
	public String getNRCFourID() {
		return NRCFourID;
	}
	public void setNRCFourID(String nRCFourID) {
		NRCFourID = nRCFourID;
	}
	public String getNRCFourName() {
		return NRCFourName;
	}
	public void setNRCFourName(String nRCFourName) {
		NRCFourName = nRCFourName;
	}
}
