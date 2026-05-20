/**
 * CV_U_011_Homeç”»é�¢
 * ä½œæˆ�å±¥æ­´ï¼š01/04/2019 Jar Moon Taung
 * ä½œæˆ�æ¦‚è¦�ï¼šUser Home View
 * 
 * æ›´æ–°å±¥æ­´ï¼š27/09/2019 Jar Moon Taung
 * æ›´æ–°æ¦‚è¦�ï¼šCV Form Link Close(recruitFormClose)
 *  
 * æ›´æ–°å±¥æ­´ï¼š06/10/2022 Zan Yai Htet
 * æ›´æ–°æ¦‚è¦�ï¼šLanding Page Content Change 	
 */
package mm.com.gic.ces.application.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import mm.com.gic.ces.application.model.Dump;
import mm.com.gic.ces.base.service.user.HomeViewService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HomeViewAction extends ActionSupport {

	HomeViewService hv = new HomeViewService();
	String dumpHeaderFirstLine;                                 	//Header First Line
	String dumpHeaderSecondLine;									//Header Second Line
	String dumpExamTimeYear;										//Exam Time Year
	String dumpYgnExamTimeMonth;									//Yangon Exam Time Month
	String dumpYgnExamTimeDay;										//Yangon Exam Time Day
	String dumpYgnExamTimeAlias;									//Yangon Exam Time Alias
	String dumpYgnExamTimeDate;										//Yangon Exam Time Date
	String dumpYgnExamPlace;										//Yangon Exam Place
	String dumpMdyExamTimeMonth;									//Mandalay Exam Time Month
	String dumpMdyExamTimeDay;										//Mandalay Exam Time Day
	String dumpMdyExamTimeAlias;									//Mandalay Exam Time Alias
	String dumpMdyExamTimeDate;										//Mandalay Exam Time Date
	String dumpMdyExamPlace;										//Mandalay Exam Place
	String dumpApliStartDate;										//Applicant Form Start Date
	String dumpApliEndDate;											//Applicant Form End Date
	String dumpYgnAddressMm;										//Yangon Office Address (MM)
	String dumpYgnContactMm;										//Yangon Office Contact (MM)
	String dumpMdyAddressMm;										//Mandalay Office Address (MM)
	String dumpMdyContactMm;										//Mandalay Office Contact (MM)
	String dumpYgnAddressEn;										//Yangon Office Address (EN)
	String dumpYgnContactEn;										//Yangon Office Contact (EN)
	String dumpYgnAddressGlo;										//Yangon Office Address Location G Map
	String dumpMdyAddressEn;										//Mandalay Office Address (EN)
	String dumpMdyContactEn;										//Mandalay Office Contact (EN)
	String dumpMdyAddressGlo;										//Mandalay Office Address Location G Map
	String dumpOfficeMail;											//Office Mail
	String dumpOfficeUrl;
	String dumpPdf;
	int dumpDelFlag;												//Delete Flag
	String dumpExamInformStartDate;                                 //Inform Exam Start Date
	String dumpExamInformEndDate;                                 //Inform Exam End Date
	
	public String init() throws IOException, SQLException {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		session.getTransaction().commit();
//		session.close();
		Dump dump_data =  hv.getDumpData();
		dumpHeaderFirstLine = dump_data.getDumpHeaderFirstLine();
		dumpHeaderSecondLine = dump_data.getDumpHeaderSecondLine();
		dumpExamTimeYear = dump_data.getDumpExamTimeYear();
		dumpYgnExamTimeMonth = dump_data.getDumpYgnExamTimeMonth();
		dumpYgnExamTimeDay = dump_data.getDumpYgnExamTimeDay();
		dumpYgnExamTimeAlias = dump_data.getDumpYgnExamTimeAlias();
		dumpYgnExamTimeDate = dump_data.getDumpYgnExamTimeDate();
		dumpYgnExamPlace = dump_data.getDumpYgnExamPlace();
		dumpMdyExamTimeMonth = dump_data.getDumpMdyExamTimeMonth();
		dumpMdyExamTimeDay = dump_data.getDumpMdyExamTimeDay();
		dumpMdyExamTimeAlias = dump_data.getDumpMdyExamTimeAlias();
		dumpMdyExamTimeDate = dump_data.getDumpMdyExamTimeDate();
		dumpMdyExamPlace = dump_data.getDumpMdyExamPlace();
		dumpApliStartDate = dump_data.getDumpApliStartDate();
		dumpApliEndDate = dump_data.getDumpApliEndDate();
		dumpYgnAddressMm = dump_data.getDumpYgnAddressMm();
		dumpYgnContactMm = dump_data.getDumpYgnContactMm();
		dumpMdyAddressMm = dump_data.getDumpMdyAddressMm();
		dumpMdyContactMm = dump_data.getDumpMdyContactMm();
		dumpYgnAddressEn = dump_data.getDumpYgnAddressEn();
		dumpYgnContactEn = dump_data.getDumpYgnContactEn();
		dumpYgnAddressGlo = dump_data.getDumpYgnAddressGlo();
		dumpMdyAddressEn = dump_data.getDumpMdyAddressEn();
		dumpMdyContactEn = dump_data.getDumpMdyContactEn();
		dumpMdyAddressGlo = dump_data.getDumpMdyAddressGlo();
		dumpOfficeMail = dump_data.getDumpOfficeMail();
		dumpOfficeUrl = dump_data.getDumpOfficeUrl();
		dumpPdf = dump_data.getDumpPdf();
		dumpExamInformStartDate = dump_data.getDump_ExamInformStartDate();
		dumpExamInformEndDate = dump_data.getDump_ExamInformEndDate();
		
		return "initHomeView";
	}
	
	//Header First Line
	public String getDumpHeaderFirstLine() {
		return dumpHeaderFirstLine;
	}

	public void setDumpHeaderFirstLine(String dumpHeaderFirstLine) {
		this.dumpHeaderFirstLine = dumpHeaderFirstLine;
	}
		
	//Header Second Line
	public String getDumpHeaderSecondLine() {
		return dumpHeaderSecondLine;
	}
	public void setDumpHeaderSecondLine(String dumpHeaderSecondLine) {
		this.dumpHeaderSecondLine = dumpHeaderSecondLine;
	}
			
	//Exam Time Year
	public String getDumpExamTimeYear() {
		return dumpExamTimeYear;
	}
	public void setDumpExamTimeYear(String dumpExamTimeYear) {
		this.dumpExamTimeYear = dumpExamTimeYear;
	}
	
	//Yangon Exam Time Month
	public String getDumpYgnExamTimeMonth() {
		return dumpYgnExamTimeMonth;
	}
	public void setDumpYgnExamTimeMonth(String dumpYgnExamTimeMonth) {
		this.dumpYgnExamTimeMonth = dumpYgnExamTimeMonth;
	}
	
	//Yangon Exam Time Day
	public String getDumpYgnExamTimeDay() {
		return dumpYgnExamTimeDay;
	}
	public void setDumpYgnExamTimeDay(String dumpYgnExamTimeDay) {
		this.dumpYgnExamTimeDay = dumpYgnExamTimeDay;
	}
	
	//Yangon Exam Time Alias
	public String getDumpYgnExamTimeAlias() {
		return dumpYgnExamTimeAlias;
	}
	public void setDumpYgnExamTimeAlias(String dumpYgnExamTimeAlias) {
		this.dumpYgnExamTimeAlias = dumpYgnExamTimeAlias;
	}
	
	//Yangon Exam Time Date
	public String getDumpYgnExamTimeDate() {
		return dumpYgnExamTimeDate;
	}
	public void setDumpYgnExamTimeDate(String dumpYgnExamTimeDate) {
		this.dumpYgnExamTimeDate = dumpYgnExamTimeDate;
	}
			
	//Yangon Exam Place
	public String getDumpYgnExamPlace() {
		return dumpYgnExamPlace;
	}
	public void setDumpYgnExamPlace(String dumpYgnExamPlace) {
		this.dumpYgnExamPlace = dumpYgnExamPlace;
	}
			
	//Mandalay Exam Time Month
	public String getDumpMdyExamTimeMonth() {
		return dumpMdyExamTimeMonth;
	}
	public void setDumpMdyExamTimeMonth(String dumpMdyExamTimeMonth) {
		this.dumpMdyExamTimeMonth = dumpMdyExamTimeMonth;
	}
	
	//Mandalay Exam Time Day
	public String getDumpMdyExamTimeDay() {
		return dumpMdyExamTimeDay;
	}
	public void setDumpMdyExamTimeDay(String dumpMdyExamTimeDay) {
		this.dumpMdyExamTimeDay = dumpMdyExamTimeDay;
	}
	
	//Mandalay Exam Time Alias
	public String getDumpMdyExamTimeAlias() {
		return dumpMdyExamTimeAlias;
	}
	public void setDumpMdyExamTimeAlias(String dumpMdyExamTimeAlias) {
		this.dumpMdyExamTimeAlias = dumpMdyExamTimeAlias;
	}
	
	//Mandalay Exam Time Date
	public String getDumpMdyExamTimeDate() {
		return dumpMdyExamTimeDate;
	}
	public void setDumpMdyExamTimeDate(String dumpMdyExamTimeDate) {
		this.dumpMdyExamTimeDate = dumpMdyExamTimeDate;
	}
	
	//Mandalay Exam Place
	public String getDumpMdyExamPlace() {
		return dumpMdyExamPlace;
	}
	public void setDumpMdyExamPlace(String dumpMdyExamPlace) {
		this.dumpMdyExamPlace = dumpMdyExamPlace;
	}
			
	//Applicant Form Start Date
	public String getDumpApliStartDate() {
		return dumpApliStartDate;
	}
	public void setDumpApliStartDate(String dumpApliStartDate) {
		this.dumpApliStartDate = dumpApliStartDate;
	}
			
	//Applicant Form End Date
	public String getDumpApliEndDate() {
		return dumpApliEndDate;
	}
	public void setDumpApliEndDate(String dumpApliEndDate) {
		this.dumpApliEndDate = dumpApliEndDate;
	}
			
	//Yangon Office Address (MM)
	public String getDumpYgnAddressMm() {
		return dumpYgnAddressMm;
	}
	public void setDumpYgnAddressMm(String dumpYgnAddressMm) {
		this.dumpYgnAddressMm = dumpYgnAddressMm;
	}
			
	//Yangon Office Contact (MM)
	public String getDumpYgnContactMm() {
		return dumpYgnContactMm;
	}
	public void setDumpYgnContactMm(String dumpYgnContactMm) {
		this.dumpYgnContactMm = dumpYgnContactMm;
	}
			
	//Mandalay Office Address (MM)
	public String getDumpMdyAddressMm() {
		return dumpMdyAddressMm;
	}
	public void setDumpMdyAddressMm(String dumpMdyAddressMm) {
		this.dumpMdyAddressMm = dumpMdyAddressMm;
	}
			
	//Mandalay Office Contact (MM)
	public String getDumpMdyContactMm() {
		return dumpMdyContactMm;
	}
	public void setDumpMdyContactMm(String dumpMdyContactMm) {
		this.dumpMdyContactMm = dumpMdyContactMm;
	}
			
	//Yangon Office Address (EN)
	public String getDumpYgnAddressEn() {
		return dumpYgnAddressEn;
	}
	public void setDumpYgnAddressEn(String dumpYgnAddressEn) {
		this.dumpYgnAddressEn = dumpYgnAddressEn;
	}
			
	//Yangon Office Contact (EN)
	public String getDumpYgnContactEn() {
		return dumpYgnContactEn;
	}
	public void setDumpYgnContactEn(String dumpYgnContactEn) {
		this.dumpYgnContactEn = dumpYgnContactEn;
	}
			
	//Yangon Office Address Location G Map
	public String getDumpYgnAddressGlo() {
		return dumpYgnAddressGlo;
	}
	public void setDumpYgnAddressGlo(String dumpYgnAddressGlo) {
		this.dumpYgnAddressGlo = dumpYgnAddressGlo;
	}
			
	//Mandalay Office Address (EN)
	public String getDumpMdyAddressEn() {
		return dumpMdyAddressEn;
	}
	public void setDumpMdyAddressEn(String dumpMdyAddressEn) {
		this.dumpMdyAddressEn = dumpMdyAddressEn;
	}
			
	//Mandalay Office Contact (EN)
	public String getDumpMdyContactEn() {
		return dumpMdyContactEn;
	}
	public void setDumpMdyContactEn(String dumpMdyContactEn) {
		this.dumpMdyContactEn = dumpMdyContactEn;
	}
			
	//Mandalay Office Address Location G Map
	public String getDumpMdyAddressGlo() {
		return dumpMdyAddressGlo;
	}
	public void setDumpMdyAddressGlo(String dumpMdyAddressGlo) {
		this.dumpMdyAddressGlo = dumpMdyAddressGlo;
	}
			
	//Office Mail
	public String getDumpOfficeMail() {
		return dumpOfficeMail;
	}
	public void setDumpOfficeMail(String dumpOfficeMail) {
		this.dumpOfficeMail = dumpOfficeMail;
	}
	//Office URL
	public void setDumpOfficeUrl(String dumpOfficeUrl) {
		this.dumpOfficeUrl = dumpOfficeUrl;
	}
	public String getDumpOfficeUrl() {
		return dumpOfficeUrl;
	}
	
	//PDF
	public String getDumpPdf() {
		return dumpPdf;
	}

	public void setDumpPdf(String dumpPdf) {
		this.dumpPdf = dumpPdf;
	}
	
	//Delete Flag
	public int getDumpDelFlag() {
		return dumpDelFlag;
	}
	public void setDumpDelFlag(int dumpDelFlag) {
		this.dumpDelFlag = dumpDelFlag;
	}

	
	/**
	 * @throws IOException *
	 * ç”³è«‹æ¸ˆã�¿
	 * return SUCCESS
	 */	
	public String recruitFormClose() throws IOException {
		try {
			Dump dump = hv.getDumpData();
			dumpDelFlag = dump.getDumpDelFlag();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "recruitClose";
	}
}
