package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCThree implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCThreeID;
	String NRCThreeName;
	
	public String getNRCThreeID() {
		return NRCThreeID;
	}
	public void setNRCThreeID(String nRCThreeID) {
		NRCThreeID = nRCThreeID;
	}
	public String getNRCThreeName() {
		return NRCThreeName;
	}
	public void setNRCThreeName(String nRCThreeName) {
		NRCThreeName = nRCThreeName;
	}
}
