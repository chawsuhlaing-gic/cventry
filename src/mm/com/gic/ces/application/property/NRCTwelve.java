package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCTwelve implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCTwelveID;
	String NRCTwelveName;
	
	public String getNRCTwelveID() {
		return NRCTwelveID;
	}
	public void setNRCTwelveID(String nRCTwelveID) {
		NRCTwelveID = nRCTwelveID;
	}
	public String getNRCTwelveName() {
		return NRCTwelveName;
	}
	public void setNRCTwelveName(String nRCTwelveName) {
		NRCTwelveName = nRCTwelveName;
	}
}
