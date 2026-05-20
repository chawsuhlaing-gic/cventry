/**
 * 
 * 作�履歴�31/10/202 Zan Yai Htet
 * 作�概覼�ラン�ィングペ�ジの作���
 * 
 * 更新履歴��dd/mm/yyyy name
 * 更新概覼�XXXXXXXX	
 */

package mm.com.gic.ces.application.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Dump;
import mm.com.gic.ces.base.common.HibernateUtil;
import mm.com.gic.ces.base.service.user.HomeViewService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HomePageEditAction extends ActionSupport{
    
	private CommonUtility commonUtility = new CommonUtility();
	private Dump dumpData = new Dump(); 
	private String successMsg;                      				// サクセスメ�セージ
	private String errorMsg;                        				// エラーメ�セージ
	HomeViewService hv = new HomeViewService();
	HttpServletRequest request = ServletActionContext.getRequest(); //サーブレ�トリクエス�
	HttpSession httpSession = request.getSession();                 //セ�ション
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
	String dumpOfficeUrl;
	String dumpPdf;
	String dumpOfficeMail;											//Office Mail
	int dumpDelFlag;												//Delete Flag
	String dumpYgnTime;
	String dumpMdyTime;
	String dumpExamInformStartDate;									//To inform Exam Start Date
	String dumpExamInformEndDate;									//To inform Exam End Date
	
	public String init() throws IOException, SQLException {
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
		dumpApliStartDate = customShowDateFormat(dump_data.getDumpApliStartDate());
		dumpApliEndDate = customShowDateFormat(dump_data.getDumpApliEndDate());
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
		dumpYgnTime = customShowDateSplitFormt(dumpYgnExamTimeDay,dumpYgnExamTimeMonth,dumpExamTimeYear);
		dumpMdyTime = customShowDateSplitFormt(dumpMdyExamTimeDay,dumpMdyExamTimeMonth,dumpExamTimeYear);
		dumpDelFlag = dump_data.getDumpDelFlag();
		dumpExamInformStartDate = customShowDateFormat(dump_data.getDump_ExamInformStartDate());
		dumpExamInformEndDate = customShowDateFormat(dump_data.getDump_ExamInformEndDate());
		return "LandingPageEdit";
	}
	
	public Boolean recruitFormOnOff() throws Throwable {
		
		return true;
	}
	
	public String saveLandingPage() throws IOException, Throwable {
		@SuppressWarnings("static-access")
	    Properties prop = commonUtility.getValue("Message.properties");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		dumpData = (Dump) session.get(Dump.class, 1);
		if (dumpData != null) {
			dumpData.setDumpHeaderFirstLine(dumpHeaderFirstLine);
			dumpData.setDumpHeaderSecondLine(dumpHeaderSecondLine);
			dumpData.setDumpExamTimeYear(dumpExamTimeYear);
			dumpData.setDumpOfficeMail(dumpOfficeMail);
			dumpData.setDumpOfficeUrl(dumpOfficeUrl);
			dumpData.setDumpPdf(dumpPdf);
			dumpData.setDumpYgnExamPlace(dumpYgnExamPlace);
			dumpData.setDumpMdyExamPlace(dumpMdyExamPlace);
			dumpData.setDumpApliStartDate(customSaveDateFormat(dumpApliStartDate));
			dumpData.setDumpApliEndDate(customSaveDateFormat(dumpApliEndDate));
			
			String ygnTime = customSaveDateSplitFormat(getDumpYgnTime());
			String[] resultYgnTime = ygnTime.split(",");
			String mdyTime = customSaveDateSplitFormat(getDumpMdyTime());
			String[] resultMdyTime = mdyTime.split(",");
			
			dumpData.setDumpYgnExamTimeMonth(resultYgnTime[2]);
			dumpData.setDumpYgnExamTimeDay(resultYgnTime[3]);
			dumpData.setDumpYgnExamTimeAlias(customAlias(resultYgnTime[3]));
			dumpData.setDumpMdyExamTimeMonth(resultMdyTime[2]);
			dumpData.setDumpMdyExamTimeDay(resultMdyTime[3]);
			dumpData.setDumpMdyExamTimeAlias(customAlias(resultMdyTime[3]));
			dumpData.setDumpDelFlag(dumpDelFlag);
			dumpData.setDump_ExamInformStartDate(customSaveDateFormat(dumpExamInformStartDate));
			dumpData.setDump_ExamInformEndDate(customSaveDateFormat(dumpExamInformEndDate));
			
			session.update(dumpData);
			successMsg = prop.getProperty(Integer.toString(1));
			httpSession.setAttribute("successMsg",successMsg);
			tx.commit();
		} else {
			errorMsg = prop.getProperty(Integer.toString(20));
			httpSession.setAttribute("errorMsg",errorMsg);
			tx.rollback();
			session.close();
			return "error";
		}
		session.close();
		return "success";// last add
	}
	
	public String customSaveDateFormat(String entryDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate formatDate = LocalDate.parse(entryDateTime.split("T")[0], formatter);
		DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("d.MM.yyyy");
		String resultDate = formatDate.format(displayFormat);
		return resultDate;
	}
	
	public String customShowDateFormat(String entryDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
		LocalDate formatDate = LocalDate.parse(entryDateTime, formatter);
		DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String resultDate = formatDate.format(displayFormat);
		return resultDate;
	}
	
	public String customSaveDateSplitFormat(String entryDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate formatDate = LocalDate.parse(entryDateTime.split("T")[0], formatter);
		DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("EEEE,yyyy,MMM,d");
		String resultDate = formatDate.format(displayFormat);
		return resultDate;
	}
	
	public String customShowDateSplitFormt(String day,String month,String year) {
		String entryDateTime = day+"/"+month+"/"+year;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MMM/yyyy");
		LocalDate formatDate = LocalDate.parse(entryDateTime, formatter);
		DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String resultDate = formatDate.format(displayFormat);
		return resultDate;
	}
	
	public String customAlias(String entryDate) {
		String alias = "";
		if (entryDate.equals("1") || entryDate.equals("21") || entryDate.equals("31")) {
			alias = "st";
		} else if (entryDate.equals("2") || entryDate.equals("22")) {
			alias = "nd";
		} else if (entryDate.equals("3") || entryDate.equals("23")) {
			alias = "rd";
		} else {
			alias = "th";
		}
		return alias;
	}
	
	// エラーメ�セージ
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	// サクセスメ�セージ
	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
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
	public String getDumpOfficeUrl() {
		return dumpOfficeUrl;
	}

	public void setDumpOfficeUrl(String dumpOfficeUrl) {
		this.dumpOfficeUrl = dumpOfficeUrl;
	}
	//PDF
	public String getDumpPdf() {
		return dumpPdf;
	}
	public void setDumpPdf(String dumpPdf) {
		this.dumpPdf = dumpPdf;
	}
	
	//Date Picker Yangon
	public String getDumpYgnTime() {
		return dumpYgnTime;
	}
	public void setDumpYgnTime(String dumpYgnTime) {
		this.dumpYgnTime = dumpYgnTime;
	}
	
	//Date Picker Mandalay
	public String getDumpMdyTime() {
		return dumpMdyTime;
	}
	public void setDumpMdyTime(String dumpMdyTime) {
		this.dumpMdyTime = dumpMdyTime;
	}
	
	//Delete Flag
	public int getDumpDelFlag() {
		return dumpDelFlag;
	}
	public void setDumpDelFlag(int dumpDelFlag) {
		this.dumpDelFlag = dumpDelFlag;
	}
	//Inform Exam Start Date
	public String getDumpExamInformStartDate() {
		return dumpExamInformStartDate;
	}

	public void setDumpExamInformStartDate(String dumpExamInformStartDate) {
		this.dumpExamInformStartDate = dumpExamInformStartDate;
	}
	//Inform Exam End Date
	public String getDumpExamInformEndDate() {
		return dumpExamInformEndDate;
	}

	public void setDumpExamInformEndDate(String dumpExamInformEndDate) {
		this.dumpExamInformEndDate = dumpExamInformEndDate;
	}
	
	
	
}
