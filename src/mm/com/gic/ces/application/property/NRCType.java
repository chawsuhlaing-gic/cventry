package mm.com.gic.ces.application.property;

import java.io.Serializable;
/**
 * 
 * 新規作成 :2019/4/1 Jar Moon Taung
 * 作成概要： to get NRCType
 * 
 * 更新履歴：
 * 更新概要：
 *
 */
public class NRCType implements Serializable{
	
	private static final long serialVersionUID = -8226464508695575972L;
	String nrcTypeID;
	String nrcType;
	public String getNrcTypeID() {
		return nrcTypeID;
	}
	public void setNrcTypeID(String nrcTypeID) {
		this.nrcTypeID = nrcTypeID;
	}
	public String getNrcType() {
		return nrcType;
	}
	public void setNrcType(String nrcType) {
		this.nrcType = nrcType;
	}
	
	

}
