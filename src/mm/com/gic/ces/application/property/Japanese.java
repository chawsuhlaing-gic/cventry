package mm.com.gic.ces.application.property;

import java.io.Serializable;

/**
 * 
 * 新規作成 :2019/4/1 Khin Myo Wai
 * 作成概要： to get Japanese
 * 
 * 更新履歴：
 * 更新概要：
 *
 */
public class Japanese implements Serializable{
	private static final long serialVersionUID = 1L;
	String japaneseID;
	String japaneseLevel;
	
	public String getJapaneseID() {
		return japaneseID;
	}
	public void setJapaneseID(String japaneseID) {
		this.japaneseID = japaneseID;
	}
	public String getJapaneseLevel() {
		return japaneseLevel;
	}
	public void setJapaneseLevel(String japaneseLevel) {
		this.japaneseLevel = japaneseLevel;
	}
}
