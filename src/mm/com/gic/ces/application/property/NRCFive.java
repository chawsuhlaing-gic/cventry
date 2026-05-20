package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCFive implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCFiveID;
	String NRCFiveName;
	
	public String getNRCFiveID() {
		return NRCFiveID;
	}
	public void setNRCFiveID(String nRCFiveID) {
		NRCFiveID = nRCFiveID;
	}
	public String getNRCFiveName() {
		return NRCFiveName;
	}
	public void setNRCFiveName(String nRCFiveName) {
		NRCFiveName = nRCFiveName;
	}
}
