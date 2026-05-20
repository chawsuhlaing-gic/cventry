/**
 * 作成履歴：09/04/2019 Jar Moon Taung
 * 作成概要：新規作成　
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.application.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 面接者情報を取得する
 */

@Entity
@Table(name = "tbl_interview")
public class Interview implements Serializable{

	private static final long serialVersionUID = -4526769199164417268L;   //SerialID
	private int interview_ID;                                             //合格者番号 
	private Company company;                                              //会社番号
	private Examinee examinee;                                            //受験者番号
	private String interview_Place;                                       //面接住所
	private String interview_Date;                                        //面接日付
	private String interview_StartTime;                                   //面接開始時
	private String interview_EndTime;                                     //面接終了時
	private String interviewer;                                           //面接者名		
	private String interview_Pass;                                        //合格者フラグ
	private String last_updateUser;                                       //更新ユーザー
	private Date last_updateTime;                                         //更新日付
	private int interview_ex_key;                                         //排他キー
	
	//合格者番号
	@Id
	@GeneratedValue
	@Column(name = "INTERVIEW_ID", unique = true, nullable = false)
	public int getInterview_ID() {
		return interview_ID;
	}

	public void setInterview_ID(int interview_ID) {
		this.interview_ID = interview_ID;
	}
	//会社番号
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
    //受験者番号
	@OneToOne
	@JoinColumn(name="EXAMINEE_ID")
	public Examinee getExaminee() {
		return examinee;
	}

	public void setExaminee(Examinee examinee) {
		this.examinee = examinee;
	}
    //面接住所	
	@Column(name = "INTERVIEW_PLACE",length=50)
	public String getInterview_Place() {
		return interview_Place;
	}

	public void setInterview_Place(String interview_Place) {
		this.interview_Place = interview_Place;
	}
    //面接日付	
	@Column(name = "INTERVIEW_DATE",length=50)
	public String getInterview_Date() {
		return interview_Date;
	}
    
	public void setInterview_Date(String interview_Date) {
		this.interview_Date = interview_Date;
	}
    //面接開始時
	@Column(name = "INTERVIEW_START_TIME",length=50)
	public String getInterview_StartTime() {
		return interview_StartTime;
	}

	public void setInterview_StartTime(String interview_StartTime) {
		this.interview_StartTime = interview_StartTime;
	}
	//面接終了時
	@Column(name = "INTERVIEW_END_TIME",length=50)
	public String getInterview_EndTime() {
		return interview_EndTime;
	}

	public void setInterview_EndTime(String interview_EndTime) {
		this.interview_EndTime = interview_EndTime;
	}
	//面接者名
	@Column(name = "INTERVIEWER",length=50)
	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}
	//合格者フラグ
	@Column(name = "INTERVIEW_PASS",columnDefinition = "varchar(3) default 'N'")
	public String getInterview_Pass() {
		return interview_Pass;
	}

	public void setInterview_Pass(String interview_Pass) {
		this.interview_Pass = interview_Pass;
	}
	//更新ユーザー
	@Column(name = "LAST_UPDATEUSER",length=150)
	public String getLast_updateUser() {
		return last_updateUser;
	}

	public void setLast_updateUser(String last_updateUser) {
		this.last_updateUser = last_updateUser;
	}
	//更新日付	
	@Column(name = "LAST_UPDATETIME")
	public Date getLast_updateTime() {
		return last_updateTime;
	}

	public void setLast_updateTime(Date last_updateTime) {
		this.last_updateTime = last_updateTime;
	}
	//排他キー
	@Column(name = "INTERVIEW_EX_KEY")
	public int getInterview_ex_key() {
		return interview_ex_key;
	}

	public void setInterview_ex_key(int interview_ex_key) {
		this.interview_ex_key = interview_ex_key;
	}
    	
}