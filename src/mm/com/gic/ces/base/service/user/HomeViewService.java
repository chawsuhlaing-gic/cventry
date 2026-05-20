/**
 * 作成履歴：06/10/2022 Zan Yai Htet
 * 作成概要：新規作成
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */

package mm.com.gic.ces.base.service.user;

import java.sql.SQLException;

import mm.com.gic.ces.application.model.Dump;
import mm.com.gic.ces.application.model.RoleSetting;
import mm.com.gic.ces.base.common.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class HomeViewService extends HibernateUtil {
	Session session = null;
	Dump dumpData = new Dump();
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
	int dumpDelFlag;												//Delete Flag
	
	/**
	 * Dummy_IDによってDummyのデータをデータベースから取得する
	 * 
	 * @param id
	 * @return　Dump
	 */
	public Dump getDumpData() throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{		
		session.beginTransaction();
		Query query=session.createQuery("FROM Dump WHERE DUMP_ID=1");
		dumpData = (Dump) query.uniqueResult();
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return dumpData;
	}
	
//	public void updateDumpData(Dump dumpData){
//		session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		session.update(dumpData);
//		session.getTransaction().commit();
//		session.close();
//	}
	
	public int getDumpDataDelFlag() throws SQLException {
		int dumpDataDelFlag = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("SELECT dp.del_flag FROM Dump dp WHERE dp.DUMP_ID=1");
			System.out.println(query);
			dumpDataDelFlag = query.executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return dumpDataDelFlag;
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
	
	//Delete Flag
	public int getDumpDelFlag() {
		return dumpDelFlag;
	}
	public void setDumpDelFlag(int dumpDelFlag) {
		this.dumpDelFlag = dumpDelFlag;
	}
	
}
