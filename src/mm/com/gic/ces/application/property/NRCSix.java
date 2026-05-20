package mm.com.gic.ces.application.property;
import java.io.Serializable;

public class NRCSix implements Serializable{
	private static final long serialVersionUID = 1L;
	String NRCSixID;
	String NRCSixName;
	
	public String getNRCSixID() {
		return NRCSixID;
	}
	public void setNRCSixID(String nRCSixID) {
		NRCSixID = nRCSixID;
	}
	public String getNRCSixName() {
		return NRCSixName;
	}
	public void setNRCSixName(String nRCSixName) {
		NRCSixName = nRCSixName;
	}
}
