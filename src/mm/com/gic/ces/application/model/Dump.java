/**
 * 作�履歴�06/10/2022 Zan Yai Htet
 * 作�概覼�新規作��
 * 
 * 更新履歴��dd/mm/yyyy name
 */

package mm.com.gic.ces.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dump")
public class Dump implements Serializable {
	
	private static final long serialVersionUID = -8860392725868280624L;  //Serial ID
	private int dump_ID;                                                 //Dummy ID	
	private String dump_HeaderFirstLine;                                 //Header First Line
	private String dump_HeaderSecondLine;									//Header Second Line
	private String dump_ExamTimeYear;										//Exam Time Year
	private String dump_YgnExamTimeMonth;									//Yangon Exam Time Month
	private String dump_YgnExamTimeDay;										//Yangon Exam Time Day
	private String dump_YgnExamTimeAlias;									//Yangon Exam Time Alias
	private String dump_YgnExamTimeDate;									//Yangon Exam Time Date
	private String dump_YgnExamPlace;										//Yangon Exam Place
	private String dump_MdyExamTimeMonth;									//Mandalay Exam Time Month
	private String dump_MdyExamTimeDay;										//Mandalay Exam Time Day
	private String dump_MdyExamTimeAlias;									//Mandalay Exam Time Alias
	private String dump_MdyExamTimeDate;									//Mandalay Exam Time Date
	private String dump_MdyExamPlace;										//Mandalay Exam Place
	private String dump_ApliStartDate;										//Applicant Form Start Date
	private String dump_ApliEndDate;										//Applicant Form End Date
	private String dump_YgnAddressMm;										//Yangon Office Address (MM)
	private String dump_YgnContactMm;										//Yangon Office Contact (MM)
	private String dump_MdyAddressMm;										//Mandalay Office Address (MM)
	private String dump_MdyContactMm;										//Mandalay Office Contact (MM)
	private String dump_YgnAddressEn;										//Yangon Office Address (EN)
	private String dump_YgnContactEn;										//Yangon Office Contact (EN)
	private String dump_YgnAddressGlo;										//Yangon Office Address Location G Map
	private String dump_MdyAddressEn;										//Mandalay Office Address (EN)
	private String dump_MdyContactEn;										//Mandalay Office Contact (EN)
	private String dump_MdyAddressGlo;										//Mandalay Office Address Location G Map
	private String dump_OfficeMail;											//Office Mail
	private String dump_OfficeUrl;
	private String dump_Pdf;
	private int dump_DelFlag;												//Delete Flag
	private String dump_ExamInformStartDate;								//Inform Exam Start Date
	private String dump_ExamInformEndDate;									//Inform Exam End Date
	

	//Dummy ID
	@Id
	@GeneratedValue
	@Column(name = "DUMP_ID", unique = true)
	public int getDump_ID() {
		return dump_ID;
	}
	public void setDump_ID(int dump_ID) {
		this.dump_ID = dump_ID;
	}
    //Header First Line
	@Column(name = "DUMP_HEADER_FIRST_LINE",length=75)
	public String getDumpHeaderFirstLine() {
		return dump_HeaderFirstLine;
	}
	public void setDumpHeaderFirstLine(String dump_HeaderFirstLine) {
		this.dump_HeaderFirstLine = dump_HeaderFirstLine;
	}
	
	//Header Second Line
	@Column(name = "DUMP_HEADER_SECOND_LINE",length=75)
	public String getDumpHeaderSecondLine() {
		return dump_HeaderSecondLine;
	}
	public void setDumpHeaderSecondLine(String dump_HeaderSecondLine) {
		this.dump_HeaderSecondLine = dump_HeaderSecondLine;
	}
	
	//Exam Time Year
	@Column(name = "DUMP_EXAM_TIME_YEAR",length=50)
	public String getDumpExamTimeYear() {
		return dump_ExamTimeYear;
	}
	public void setDumpExamTimeYear(String dump_ExamTimeYear) {
		this.dump_ExamTimeYear = dump_ExamTimeYear;
	}
	
	//Yangon Exam Time Month
	@Column(name = "DUMP_YGN_EXAM_TIME_MONTH",length=50)
	public String getDumpYgnExamTimeMonth() {
		return dump_YgnExamTimeMonth;
	}
	public void setDumpYgnExamTimeMonth(String dump_YgnExamTimeMonth) {
		this.dump_YgnExamTimeMonth = dump_YgnExamTimeMonth;
	}
	
	//Yangon Exam Time Day
	@Column(name = "DUMP_YGN_EXAM_TIME_DAY", length=50)
	public String getDumpYgnExamTimeDay() {
		return dump_YgnExamTimeDay;
	}
	public void setDumpYgnExamTimeDay(String dump_YgnExamTimeDay) {
		this.dump_YgnExamTimeDay = dump_YgnExamTimeDay;
	}
	
	//Yangon Exam Time Alias
	@Column(name = "DUMP_YGN_EXAM_TIME_ALIAS", length=50)
	public String getDumpYgnExamTimeAlias() {
		return dump_YgnExamTimeAlias;
	}
	public void setDumpYgnExamTimeAlias(String dump_YgnExamTimeAlias) {
		this.dump_YgnExamTimeAlias = dump_YgnExamTimeAlias;
	}
	
	//Yangon Exam Time Date
	@Column(name = "DUMP_YGN_EXAM_TIME_DATE", length=50)
	public String getDumpYgnExamTimeDate() {
		return dump_YgnExamTimeDate;
	}
	public void setDumpYgnExamTimeDate(String dump_YgnExamTimeDate) {
		this.dump_YgnExamTimeDate = dump_YgnExamTimeDate;
	}
	
	//Yangon Exam Place
	@Column(name = "DUMP_YGN_EXAM_PLACE",length=50)
	public String getDumpYgnExamPlace() {
		return dump_YgnExamPlace;
	}
	public void setDumpYgnExamPlace(String dump_YgnExamPlace) {
		this.dump_YgnExamPlace = dump_YgnExamPlace;
	}
	
	//Mandalay Exam Time Month
	@Column(name = "DUMP_MDY_EXAM_TIME_MONTH",length=50)
	public String getDumpMdyExamTimeMonth() {
		return dump_MdyExamTimeMonth;
	}
	public void setDumpMdyExamTimeMonth(String dump_MdyExamTimeMonth) {
		this.dump_MdyExamTimeMonth = dump_MdyExamTimeMonth;
	}
	
	//Mandalay Exam Time Day
	@Column(name = "DUMP_MDY_EXAM_TIME_DAY",length=50)
	public String getDumpMdyExamTimeDay() {
		return dump_MdyExamTimeDay;
	}
	public void setDumpMdyExamTimeDay(String dump_MdyExamTimeDay) {
		this.dump_MdyExamTimeDay = dump_MdyExamTimeDay;
	}
	
	//Mandalay Exam Time Alias
	@Column(name = "DUMP_MDY_EXAM_TIME_ALIAS",length=50)
	public String getDumpMdyExamTimeAlias() {
		return dump_MdyExamTimeAlias;
	}
	public void setDumpMdyExamTimeAlias(String dump_MdyExamTimeAlias) {
		this.dump_MdyExamTimeAlias = dump_MdyExamTimeAlias;
	}
	
	//Mandalay Exam Time Date
	@Column(name = "DUMP_MDY_EXAM_TIME_DATE",length=50)
	public String getDumpMdyExamTimeDate() {
		return dump_MdyExamTimeDate;
	}
	public void setDumpMdyExamTimeDate(String dump_MdyExamTimeDate) {
		this.dump_MdyExamTimeDate = dump_MdyExamTimeDate;
	}
	
	//Mandalay Exam Place
	@Column(name = "DUMP_MDY_EXAM_PLACE",length=50)
	public String getDumpMdyExamPlace() {
		return dump_MdyExamPlace;
	}
	public void setDumpMdyExamPlace(String dump_MdyExamPlace) {
		this.dump_MdyExamPlace = dump_MdyExamPlace;
	}
	
	//Applicant Form Start Date
	@Column(name = "DUMP_APLI_START_DATE",length=20)
	public String getDumpApliStartDate() {
		return dump_ApliStartDate;
	}
	public void setDumpApliStartDate(String dump_ApliStartDate) {
		this.dump_ApliStartDate = dump_ApliStartDate;
	}
	
	//Applicant Form End Date
	@Column(name = "DUMP_APLI_END_DATE",length=20)
	public String getDumpApliEndDate() {
		return dump_ApliEndDate;
	}
	public void setDumpApliEndDate(String dump_ApliEndDate) {
		this.dump_ApliEndDate = dump_ApliEndDate;
	}
	
	//Yangon Office Address (MM)
	@Column(name = "DUMP_YGN_ADDRESS_MM",length=225)
	public String getDumpYgnAddressMm() {
		return dump_YgnAddressMm;
	}
	public void setDumpYgnAddressMm(String dump_YgnAddressMm) {
		this.dump_YgnAddressMm = dump_YgnAddressMm;
	}
	
	//Yangon Office Contact (MM)
	@Column(name = "DUMP_YGN_CONTACT_MM",length=100)
	public String getDumpYgnContactMm() {
		return dump_YgnContactMm;
	}
	public void setDumpYgnContactMm(String dump_YgnContactMm) {
		this.dump_YgnContactMm = dump_YgnContactMm;
	}
	//Mandalay Office Address (MM)
	@Column(name = "DUMP_MDY_ADDRESS_MM",length=225)
	public String getDumpMdyAddressMm() {
		return dump_MdyAddressMm;
	}
	public void setDumpMdyAddressMm(String dump_MdyAddressMm) {
		this.dump_MdyAddressMm = dump_MdyAddressMm;
	}
	
	//Mandalay Office Contact (MM)
	@Column(name = "DUMP_MDY_CONTACT_MM",length=100)
	public String getDumpMdyContactMm() {
		return dump_MdyContactMm;
	}
	public void setDumpMdyContactMm(String dump_MdyContactMm) {
		this.dump_MdyContactMm = dump_MdyContactMm;
	}
	
	//Yangon Office Address (EN)
	@Column(name = "DUMP_YGN_ADDRESS_EN",length=225)
	public String getDumpYgnAddressEn() {
		return dump_YgnAddressEn;
	}
	public void setDumpYgnAddressEn(String dump_YgnAddressEn) {
		this.dump_YgnAddressEn = dump_YgnAddressEn;
	}
	
	//Yangon Office Contact (EN)
	@Column(name = "DUMP_YGN_CONTACT_EN",length=100)
	public String getDumpYgnContactEn() {
		return dump_YgnContactEn;
	}
	public void setDumpYgnContactEn(String dump_YgnContactEn) {
		this.dump_YgnContactEn = dump_YgnContactEn;
	}
	
	//Yangon Office Address Location G Map
	@Column(name = "DUMP_YGN_ADDRESS_GLO",length=1024)
	public String getDumpYgnAddressGlo() {
		return dump_YgnAddressGlo;
	}
	public void setDumpYgnAddressGlo(String dump_YgnAddressGlo) {
		this.dump_YgnAddressGlo = dump_YgnAddressGlo;
	}
	
	//Mandalay Office Address (EN)
	@Column(name = "DUMP_MDY_ADDRESS_EN",length=225)
	public String getDumpMdyAddressEn() {
		return dump_MdyAddressEn;
	}
	public void setDumpMdyAddressEn(String dump_MdyAddressEn) {
		this.dump_MdyAddressEn = dump_MdyAddressEn;
	}
	
	//Mandalay Office Contact (EN)
	@Column(name = "DUMP_MDY_CONTACT_EN",length=100)
	public String getDumpMdyContactEn() {
		return dump_MdyContactEn;
	}
	public void setDumpMdyContactEn(String dump_MdyContactEn) {
		this.dump_MdyContactEn = dump_MdyContactEn;
	}
	
	//Mandalay Office Address Location G Map
	@Column(name = "DUMP_MDY_ADDRESS_GLO",length=1024)
	public String getDumpMdyAddressGlo() {
		return dump_MdyAddressGlo;
	}
	public void setDumpMdyAddressGlo(String dump_MdyAddressGlo) {
		this.dump_MdyAddressGlo = dump_MdyAddressGlo;
	}
	
	//Office Mail
	@Column(name = "DUMP_OFFICE_MAIL",length=50)
	public String getDumpOfficeMail() {
		return dump_OfficeMail;
	}
	public void setDumpOfficeMail(String dump_OfficeMail) {
		this.dump_OfficeMail = dump_OfficeMail;
	}
	//Office Url
	@Column(name = "DUMP_OFFICE_URL",length=255)
	public String getDumpOfficeUrl() {
		return dump_OfficeUrl;
	}
	public void setDumpOfficeUrl(String dump_OfficeUrl) {
		this.dump_OfficeUrl = dump_OfficeUrl;
	}
	
	//PDF
	@Column(name = "DUMP_PDF",length=255)
	public String getDumpPdf() {
		return dump_Pdf;
	}
	public void setDumpPdf(String dump_Pdf) {
		this.dump_Pdf = dump_Pdf;
	}
	
	//Delete Flag
	@Column(name = "DEL_FLAG",length=1)
	public int getDumpDelFlag() {
		return dump_DelFlag;
	}
	public void setDumpDelFlag(int dump_DelFlag) {
		this.dump_DelFlag = dump_DelFlag;
	}
	
	//Inform Exam Start Date
	@Column(name = "DUMP_EXAM_INFORM_START_DATE",length=20)
	public String getDump_ExamInformStartDate() {
		return dump_ExamInformStartDate;
	}
	public void setDump_ExamInformStartDate(String dump_ExamInformStartDate) {
		this.dump_ExamInformStartDate = dump_ExamInformStartDate;
	}
	
	//Inform Exam End Date
	@Column(name = "DUMP_EXAM_INFORM_END_DATE",length=20)
	public String getDump_ExamInformEndDate() {
		return dump_ExamInformEndDate;
	}
	public void setDump_ExamInformEndDate(String dump_ExamInformEndDate) {
		this.dump_ExamInformEndDate = dump_ExamInformEndDate;
	}
	
	
}

