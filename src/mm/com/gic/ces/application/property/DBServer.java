/**
 * 
 * 新規作成 :2019/4/1 Khin Myo Wai
 * 作成概要： to get DBServer
 * 
 * 更新履歴：
 * 更新概要：
 *
 */

package mm.com.gic.ces.application.property;

import java.io.Serializable;
public class DBServer implements Serializable{
	private static final long serialVersionUID = 1L;
	String dbServerID;
	String dbServerName;
	
	public String getDbServerID() {
		return dbServerID;
	}
	public void setDbServerID(String dbServerID) {
		this.dbServerID = dbServerID;
	}
	public String getDbServerName() {
		return dbServerName;
	}
	public void setDbServerName(String dbServerName) {
		this.dbServerName = dbServerName;
	}
	
	
}
