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
 * 受験に参加した申込者情報を取得する
 */
@Entity
@Table(name = "tbl_examinee")
public class Examinee implements Serializable {
	
	private static final long serialVersionUID = -7239864660706496271L;
	private int examinee_ID;                                             //受験者番号
	private Applicant applicant;                                         //申請者番号
	private Company firstCompany;                                        //一番会社名番号
	private Company secondCompany;                                       //二番会社名番号
	private int examinee_IQmark;                                         //受験者IQマーク
	private int examinee_EQmark;                                         //受験者EQマーク
	private String examinee_Pass;                                        //受験者フラグ
	private String last_updateUser;                                      //更新ユーザー
	private Date last_updateTime;                                        //更新日付	
	private int examinee_ex_key;                                         //排他キー	
	
	//受験者番号
	@Id
	@GeneratedValue
	@Column(name = "EXAMINEE_ID", unique = true, nullable = false)
	public int getExaminee_ID() {
		return examinee_ID;
	}

	public void setExaminee_ID(int examinee_ID) {
		this.examinee_ID = examinee_ID;
	}
    //申請者番号	
	@OneToOne
	@JoinColumn(name = "APP_ID")
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
    //一番会社名番号
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FIRST_COMPANY_ID")
	public Company getFirstCompany() {
		return firstCompany;
	}

	public void setFirstCompany(Company firstCompany) {
		this.firstCompany = firstCompany;
	}
    //二番会社名番号
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SECOND_COMPANY_ID")
	public Company getSecondCompany() {
		return secondCompany;
	}

	public void setSecondCompany(Company secondCompany) {
		this.secondCompany = secondCompany;
	}

    //受験者IQマーク
	@Column(name = "EXAMINEE_IQMARK",length=5)
	public int getExaminee_IQmark() {
		return examinee_IQmark;
	}

	public void setExaminee_IQmark(int examinee_IQmark) {
		this.examinee_IQmark = examinee_IQmark;
	}
    //受験者EQマーク
	@Column(name = "EXAMINEE_EQMARK",length=5)
	public int getExaminee_EQmark() {
		return examinee_EQmark;
	}

	public void setExaminee_EQmark(int examinee_EQmark) {
		this.examinee_EQmark = examinee_EQmark;
	}
    //受験者フラグ
	@Column(name = "EXAMINEE_PASS",columnDefinition = "varchar(3) default 'N'")
	public String getExaminee_Pass() {
		return examinee_Pass;
	}

	public void setExaminee_Pass(String examinee_Pass) {
		this.examinee_Pass = examinee_Pass;
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
	@Column(name = "EXAMINEE_EX_KEY")
	public int getExaminee_ex_key() {
		return examinee_ex_key;
	}

	public void setExaminee_ex_key(int examinee_ex_key) {
		this.examinee_ex_key = examinee_ex_key;
	}
	
}
