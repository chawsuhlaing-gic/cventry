package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCNine implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCNineID;
	String NRCNineName;
	
	public String getNRCNineID() {
		return NRCNineID;
	}
	public void setNRCNineID(String nRCNineID) {
		NRCNineID = nRCNineID;
	}
	public String getNRCNineName() {
		return NRCNineName;
	}
	public void setNRCNineName(String nRCNineName) {
		NRCNineName = nRCNineName;
	}

}
