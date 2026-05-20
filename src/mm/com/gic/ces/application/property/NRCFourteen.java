package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCFourteen implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCFourteenID;
	String NRCFourteenName;
	
	public String getNRCFourteenID() {
		return NRCFourteenID;
	}
	public void setNRCFourteenID(String nRCFourteenID) {
		NRCFourteenID = nRCFourteenID;
	}
	public String getNRCFourteenName() {
		return NRCFourteenName;
	}
	public void setNRCFourteenName(String nRCFourteenName) {
		NRCFourteenName = nRCFourteenName;
	}
}
