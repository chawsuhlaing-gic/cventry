/**
 * 作成履歴：09/04/2019 Jar Moon Taung
 * 作成概要：新規作成　
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_examID")
public class ExamID implements Serializable{
	
	private static final long serialVersionUID = 2016043768850709659L;   //Serial ID
	private int exam_ID;                                                 //ID Key受験番号
	private String exam_Place;                                           //受験所
	private String exam_IDCount;                                         //受験所によって最後受験番号
    
	//ID Key受験番号
	@Id
	@GeneratedValue
	@Column(name = "EXAM_ID", unique = true)
	public int getExam_ID() {
		return exam_ID;
	}
	public void setExam_ID(int exam_ID) {
		this.exam_ID = exam_ID;
	}
    
	//受験所
	@Column(name = "EXAM_PLACE",length=10)
	public String getExam_Place() {
		return exam_Place;
	}
	public void setExam_Place(String exam_Place) {
		this.exam_Place = exam_Place;
	}
    
	//受験所によって最後受験番号
	@Column(name = "EXAM_ID_COUNT",length=10)
	public String getExam_IDCount() {
		return exam_IDCount;
	}
	public void setExam_IDCount(String exam_IDCount) {
		this.exam_IDCount = exam_IDCount;
	}	
}
