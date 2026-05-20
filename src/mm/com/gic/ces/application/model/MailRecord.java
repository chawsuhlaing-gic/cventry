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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_mailrecord")
public class MailRecord implements Serializable{
	
	private int mail_ID;                 //メール送信ID
	private Date mail_SendDate;          //申請者ID	
	private String mail_Content;         //権限番号	
	private String mail_Subject;         //メール送信日付
	private Applicant applicant;         //メール送信内容
	private RoleSetting rolesetting;     //メール送信条件
	
    //メール送信ID
	@Id
	@GeneratedValue
	@Column(name = "MAIL_ID", unique = true, nullable = false)
	public int getMail_ID() {
		return mail_ID;
	}
	public void setMail_ID(int mail_ID) {
		this.mail_ID = mail_ID;
	}
	//申請者ID	
	@Column(name = "MAIL_SENDDATE",length=50)
	public Date getMail_SendDate() {
		return mail_SendDate;
	}
	public void setMail_SendDate(Date mail_SendDate) {
		this.mail_SendDate = mail_SendDate;
	}
	//権限番号	
	@Column(name = "MAIL_CONTENT",length=1024)
	public String getMail_Content() {
		return mail_Content;
	}
	public void setMail_Content(String mail_Content) {
		this.mail_Content = mail_Content;
	}
	//メール送信日付
	@Column(name = "MAIL_SUBJECT",length=1024)
	public String getMail_Subject() {
		return mail_Subject;
	}
	public void setMail_Subject(String mail_Subject) {
		this.mail_Subject = mail_Subject;
	}
	 //メール送信内容
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID", nullable = false)
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	//メール送信条件
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public RoleSetting getRolesetting() {
		return rolesetting;
	}
	public void setRolesetting(RoleSetting rolesetting) {
		this.rolesetting = rolesetting;
	}
	
}
