/**
 * 
 * 新規作成 :2019/4/1 Khin Myo Wai
 * 作成概要： to get Design
 * 
 * 更新履歴：
 * 更新概要：
 *
 */

package mm.com.gic.ces.application.property;

import java.io.Serializable;
public class Design implements Serializable{
	private static final long serialVersionUID = 1L;
	String designID;
	String designName;
	public String getDesignID() {
		return designID;
	}
	public void setDesignID(String designID) {
		this.designID = designID;
	}
	public String getDesignName() {
		return designName;
	}
	public void setDesignName(String designName) {
		this.designName = designName;
	}
	
	
}
