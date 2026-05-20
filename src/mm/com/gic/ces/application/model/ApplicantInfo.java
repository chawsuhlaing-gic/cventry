/**
 * 作成履歴：09/04/2019 Thet Ngon Tun
 * 作成概要：新規作成　
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.application.model;

import java.io.Serializable;

public class ApplicantInfo implements Serializable {
	
	private static final long serialVersionUID = -1867292712552680634L;  //Serial ID
	private Applicant applicant;                                         //申込者情報
	private Examinee examinee;                                           //受験者情報
	private Interview interview;                                         //面接者情報
	
	//申込者情報
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
    //受験者情報
	public Examinee getExaminee() {
		return examinee;
	}
	public void setExaminee(Examinee examinee) {
		this.examinee = examinee;
	}
    //面接者情報
	public Interview getInterview() {
		return interview;
	}
	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	
}
