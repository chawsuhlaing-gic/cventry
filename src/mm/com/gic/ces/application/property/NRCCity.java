/**
 * 
 * 新規作成 :2019/04/01 Jar Moon Taung
 * 作成概要： to get NRC 
 * 
 * 更新履歴：
 * 更新概要：
 *
 */

package mm.com.gic.ces.application.property;

import java.io.Serializable;
public class NRCCity implements Serializable{
	
	private static final long serialVersionUID = 2975811846116694623L;
	String nrcCityID;
	String nrcCity;
	public String getNrcCityID() {
		return nrcCityID;
	}
	public void setNrcCityID(String nrcCityID) {
		this.nrcCityID = nrcCityID;
	}
	public String getNrcCity() {
		return nrcCity;
	}
	public void setNrcCity(String nrcCity) {
		this.nrcCity = nrcCity;
	}
	
	

}
