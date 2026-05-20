/**
 * 
 * 新規作成 :2019/4/1 Khin Myo Wai
 * 作成概要： to get Programming
 * 
 * 更新履歴：
 * 更新概要：
 *
 */

package mm.com.gic.ces.application.property;

import java.io.Serializable;
public class Programming implements Serializable{
	private static final long serialVersionUID = 1L;
	String programmingID;
	String programmingName ;
	
	public String getProgrammingID() {
		return programmingID;
	}
	public void setProgrammingID(String programmingID) {
		this.programmingID = programmingID;
	}
	public String getProgrammingName() {
		return programmingName;
	}
	public void setProgrammingName(String programmingName) {
		this.programmingName = programmingName;
	}
	
	
}
