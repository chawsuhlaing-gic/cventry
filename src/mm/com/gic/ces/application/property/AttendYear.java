package mm.com.gic.ces.application.property;

import java.io.Serializable;

/**
 * 
 * 新規作成 :2019/4/2 Jar Moon Tuang
 * 作成概要： to get Attended Year
 * 
 * 更新履歴：
 * 更新概要：
 *
 */
public class AttendYear implements Serializable{

	
	private static final long serialVersionUID = 5425292567344896362L;
	String attendYearID;
	String attendYear;
	public String getAttendYearID() {
		return attendYearID;
	}
	public void setAttendYearID(String attendYearID) {
		this.attendYearID = attendYearID;
	}
	public String getAttendYear() {
		return attendYear;
	}
	public void setAttendYear(String attendYear) {
		this.attendYear = attendYear;
	}
	
	

}
