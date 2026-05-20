package mm.com.gic.ces.application.property;

import java.io.Serializable;

/**
 * 
 * 新規作成 :2019/4/1 Khin Myo Wai
 * 作成概要： to get Degree
 * 
 * 更新履歴：
 * 更新概要：
 *
 */
public class Degree implements Serializable{
	private static final long serialVersionUID = 1L;
	String degreeID;
	String degreeName;
	
	public String getDegreeID() {
		return degreeID;
	}
	public void setDegreeID(String degreeID) {
		this.degreeID = degreeID;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	
	
}
