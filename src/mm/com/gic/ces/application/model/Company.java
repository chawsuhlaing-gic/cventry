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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_company")
public class Company implements Serializable{

	private static final long serialVersionUID = 4621970634611692847L;   //SerailID
	private int com_ID;                                                  //会社番号	
	private String com_Lname;                                            //会社名(名称)
	private String com_Sname;                                            //会社名（略称）
	private int com_Reg_Year;                                            //登録年
	private String last_updateUser;                                      //更新ユーザー
	private Date last_updateTime;                                        //更新日付
	private int del_flag;                                                //削除フラグ
	private int com_ex_key;                                              //排他キー	
	private List<Examinee> lstFExaminee;                                 //リスト
	private List<Examinee> lstSExaminee;                                 //リスト
	private List<Employee> lstEmployee;                                  //社員リスト
	private List<Interview> lstInterview;                                //面接者リスト

	//会社番号
	@Id
	@GeneratedValue
	@Column(name = "COMPANY_ID", unique = true, nullable = false)
	public int getCom_ID() {
		return com_ID;
	}

	public void setCom_ID(int com_ID) {
		this.com_ID = com_ID;
	}
	//会社名(名称)
	@Column(name = "COMPANY_LONG_NAME",length=100)
	public String getCom_Lname() {
		return com_Lname;
	}

	public void setCom_Lname(String com_Lname) {
		this.com_Lname = com_Lname;
	}
	//会社名（略称）
	@Column(name = "COMPANY_SHORT_NAME",length=50)
	public String getCom_Sname() {
		return com_Sname;
	}

	public void setCom_Sname(String com_Sname) {
		this.com_Sname = com_Sname;
	}
	//登録年	
	@Column(name = "COMPANY_REG_YEAR")
	public int getCom_Reg_Year() {
		return com_Reg_Year;
	}

	public void setCom_Reg_Year(int com_Reg_Year) {
		this.com_Reg_Year = com_Reg_Year;
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
	//削除フラグ
	@Column(name = "COMPANY_EX_KEY")
	public int getCom_ex_key() {
		return com_ex_key;
	}

	public void setCom_ex_key(int com_ex_key) {
		this.com_ex_key = com_ex_key;
	}
	
	//排他キー	
	@Column(name = "DEL_FLAG", columnDefinition = "int(1) default 0")
	public int getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}	
	
	@OneToMany(mappedBy = "company")
	public List<Interview> getLstInterview() {
		return lstInterview;
	}

	public void setLstInterview(List<Interview> lstInterview) {
		this.lstInterview = lstInterview;
	}

	@OneToMany(mappedBy = "secondCompany")
	public List<Examinee> getLstSExaminee() {
		return lstSExaminee;
	}

	public void setLstSExaminee(List<Examinee> lstSExaminee) {
		this.lstSExaminee = lstSExaminee;
	}

	@OneToMany(mappedBy = "company")
	public List<Employee> getLstEmployee() {
		return lstEmployee;
	}

	public void setLstEmployee(List<Employee> lstEmployee) {
		this.lstEmployee = lstEmployee;
	}

	@OneToMany(mappedBy = "firstCompany")
	public List<Examinee> getLstFExaminee() {
		return lstFExaminee;
	}

	public void setLstFExaminee(List<Examinee> lstFExaminee) {
		this.lstFExaminee = lstFExaminee;
	}
	
}
