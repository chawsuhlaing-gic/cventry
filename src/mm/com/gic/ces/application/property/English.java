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
public class English implements Serializable{
	private static final long serialVersionUID = 1L;
	String englishID;
	String englishLevel;
	
	public String getEnglishID() {
		return englishID;
	}
	public void setEnglishID(String englishID) {
		this.englishID = englishID;
	}
	public String getEnglishLevel() {
		return englishLevel;
	}
	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}
	

}
