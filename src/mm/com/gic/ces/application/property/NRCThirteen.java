package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCThirteen implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCThirteenID;
	String NRCThirteenName;
	
	public String getNRCThirteenID() {
		return NRCThirteenID;
	}
	public void setNRCThirteenID(String nRCThirteenID) {
		NRCThirteenID = nRCThirteenID;
	}
	public String getNRCThirteenName() {
		return NRCThirteenName;
	}
	public void setNRCThirteenName(String nRCThirteenName) {
		NRCThirteenName = nRCThirteenName;
	}
}
