package mm.com.gic.ces.application.property;

import java.io.Serializable;

/**
 * 
 * 新規作成 :2019/4/1 Khin Myo Wai
 * 作成概要： to get City Of University
 * 
 * 更新履歴：
 * 更新概要：
 *
 */
public class CityofUniversity implements Serializable{
	private static final long serialVersionUID = 1L;
	String cityofUniID;
	String cityofUniName;
	
	public String getCityofUniID() {
		return cityofUniID;
	}
	public void setCityofUniID(String cityofUniID) {
		this.cityofUniID = cityofUniID;
	}
	public String getCityofUniName() {
		return cityofUniName;
	}
	public void setCityofUniName(String cityofUniName) {
		this.cityofUniName = cityofUniName;
	}
	
	
}
