package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCOne implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCOneID;
	String NRCOneName;
	
	public String getNRCOneID() {
		return NRCOneID;
	}
	public void setNRCOneID(String nRCOneID) {
		NRCOneID = nRCOneID;
	}
	public String getNRCOneName() {
		return NRCOneName;
	}
	public void setNRCOneName(String nRCOneName) {
		NRCOneName = nRCOneName;
	}
}
