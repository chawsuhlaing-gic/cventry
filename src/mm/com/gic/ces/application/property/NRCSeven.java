package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCSeven implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCSevenID;
	String NRCSevenName;
	
	public String getNRCSevenID() {
		return NRCSevenID;
	}
	public void setNRCSevenID(String nRCSevenID) {
		NRCSevenID = nRCSevenID;
	}
	public String getNRCSevenName() {
		return NRCSevenName;
	}
	public void setNRCSevenName(String nRCSevenName) {
		NRCSevenName = nRCSevenName;
	}
}
