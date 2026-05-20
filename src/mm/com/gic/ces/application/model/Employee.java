/**
 * 作成履歴：09/04/2019 Jar Moon Taung
 * 作成概要：新規作成　
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.application.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_employee")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = -1170896742662942401L;   //SerialID
	private int employee_ID;                                              //採用者番号
	private Company company;                                              //会社番号
	private Interview interview;                                          //合格者番号
	//採用者番号
	@OneToOne
	@JoinColumn(name="INTERVIEW_ID")
	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	 //会社番号
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COMPANY_ID")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
    //合格者番号
	@Id
	@GeneratedValue
	@Column(name = "EMPLOYEE_ID", unique = true, nullable = false)
	public int getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(int employee_ID) {
		this.employee_ID = employee_ID;
	}

}
