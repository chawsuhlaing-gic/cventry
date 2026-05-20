package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCEleven implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCElevenID;
	String NRCElevenName;
	
	public String getNRCElevenID() {
		return NRCElevenID;
	}
	public void setNRCElevenID(String nRCElevenID) {
		NRCElevenID = nRCElevenID;
	}
	public String getNRCElevenName() {
		return NRCElevenName;
	}
	public void setNRCElevenName(String nRCElevenName) {
		NRCElevenName = nRCElevenName;
	}
}
