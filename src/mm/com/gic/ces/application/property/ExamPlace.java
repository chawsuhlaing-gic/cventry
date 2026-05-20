/**
 * 全ての画面
 * 作成履歴：01/04/2019 Cho Cho Lwin
 * 作成概要：新規作成
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */

package mm.com.gic.ces.application.property;
import java.io.Serializable;

/**
 * 受験場所モデル
 */
public class ExamPlace implements Serializable{

	private static final long serialVersionUID = 1L;
	String examPlaceID;    //受験場所ID
	String examPlace;      //受験場所
	
	//受験場所ID
	public String getExamPlaceID() {
		return examPlaceID;
	}

	public void setExamPlaceID(String examPlaceID) {
		this.examPlaceID = examPlaceID;
	}
	
	//受験場所
	public String getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(String examPlace) {
		this.examPlace = examPlace;
	}
}
