/**
 * 
 * 新規作成 :2019/4/1 Khin Myo Wai
 * 作成概要： to get Other uni
 * 
 * 更新履歴：
 * 更新概要：
 *
 */

package mm.com.gic.ces.application.property;

import java.io.Serializable;
public class Others implements Serializable{
	private static final long serialVersionUID = 1L;
	String othersID;
	String othersName;
	
	
	public String getOthersID() {
		return othersID;
	}
	public void setOthersID(String othersID) {
		this.othersID = othersID;
	}
	public String getOthersName() {
		return othersName;
	}
	public void setOthersName(String othersName) {
		this.othersName = othersName;
	}
	
	
	

	
}
