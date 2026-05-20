/***
 * CV_A_042_会社別合格者情報登録画面
 * 新規作成: 2019/2/8 Saw Yu Nwe 
 * 作成概要： 会社別合格者情報登録処理
 * 
 * 更新履歴： 
 * 更新概要：
 *
 */
package mm.com.gic.ces.base.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.application.model.Interview;
import mm.com.gic.ces.base.common.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class IntervieweeService {
	private List<ApplicantInfo> examinee_List;    //受験者リスト
	Session session = null;                       //セッション

	/**
	 * ...検索情報によって受験者を検索する。...
	 * @param jfYear     JobFair年
	 * @param examPlace　　受験場所
	 * @param sDate　　　　　開始日
	 * @param eDate　　　　　終了日
	 * @param sExamID　　　開始受験ID
	 * @param eExamID　　　終了受験ID
	 * @param firstCom   第一希望会社名
	 * @param secondCom　　第二希望会社名
	 * @return　examinee_List
	 */
	@SuppressWarnings("unchecked")
	public List<ApplicantInfo> searchExaminee(int jfYear, String examPlace,
			Date sDate, Date eDate, String sExamID, String eExamID,
			String firstCom, String secondCom) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		examinee_List = new ArrayList<ApplicantInfo>();
		String query;
		StringBuilder sb=new StringBuilder("FROM Examinee WHERE examinee_Pass='Y' AND applicant.app_JFYear=:jfYear "
				+ "AND applicant.app_ExamPlace=:examPlace");
		if (sDate != null && eDate != null) {
			sb.append(" AND applicant.app_RegDate>=:sDate AND applicant.app_RegDate<=:eDate");
		} else {
			if (sDate != null) {
				sb.append(" AND applicant.app_RegDate>=:sDate");
			}
			if (eDate != null) {
				sb.append(" AND applicant.app_RegDate<=:eDate");
			}
		}
		if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
				&& !eExamID.trim().isEmpty()) {
			sb.append(" AND applicant.exam_ID>=:sExamID AND applicant.exam_ID<=:eExamID");
		} else {
			if (sExamID != null && !sExamID.trim().isEmpty()) {
				sb.append(" AND applicant.exam_ID>=:sExamID");
			}
			if (eExamID != null && !eExamID.trim().isEmpty()) {
				sb.append(" AND applicant.exam_ID<=:eExamID");
			}
		}
		if (!firstCom.equals("")) {
			sb.append(" AND firstCompany.com_Sname=:firstCom");
		}
		if (!secondCom.equals("")) {
			sb.append(" AND secondCompany.com_Sname=:secondCom");
		}
		sb.append( " ORDER BY applicant.exam_ID");
		query=sb.toString();
		Query q = session.createQuery(query);
		q.setParameter("jfYear", jfYear);
		q.setParameter("examPlace", examPlace);
		if (sDate != null) {
			q.setParameter("sDate", sDate);
		}
		if (eDate != null) {
			q.setParameter("eDate", eDate);
		}
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			q.setParameter("sExamID", sExamID);
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			q.setParameter("eExamID", eExamID);
		}
		if (!firstCom.equals("")) {
			q.setParameter("firstCom", firstCom);
		}
		if (!secondCom.equals("")) {
			q.setParameter("secondCom", secondCom);
		}
		List<Examinee> examinees;
		examinees = q.list();
		for (int i = 0; i < examinees.size(); i++) {
			Examinee examinee = (Examinee) examinees.get(i);
			Criteria cr = session.createCriteria(Interview.class).add(
					Restrictions.eq("examinee.examinee_ID", examinee.getExaminee_ID()));
			List<Interview> interviewees = cr.list();
			ApplicantInfo appInfo = new ApplicantInfo();
			appInfo.setExaminee(examinee);
			if (!interviewees.isEmpty()) {
				appInfo.setInterview(interviewees.get(0));
			} else
				appInfo.setInterview(null);
			examinee_List.add(appInfo);
		}
		session.getTransaction().commit();
		session.close();
		return examinee_List;
	}

	public int getExamineeCount(int jfYear, String examPlace, Date sDate, Date eDate, 
			String sExamID, String eExamID, String firstCom, String secondCom) {
		int appCount = 0;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String countQuery;
		StringBuilder sb=new StringBuilder("SELECT COUNT(*) FROM Examinee WHERE examinee_Pass='Y' AND applicant.app_JFYear=:jfYear "
				+ "AND applicant.app_ExamPlace=:examPlace");
		if (sDate != null && eDate != null) {
			sb.append(" AND applicant.app_RegDate>=:sDate AND applicant.app_RegDate<=:eDate");
		} else {
			if (sDate != null) {
				sb.append(" AND applicant.app_RegDate>=:sDate");
			}
			if (eDate != null) {
				sb.append(" AND applicant.app_RegDate<=:eDate");
			}
		}
		if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
				&& !eExamID.trim().isEmpty()) {
			sb.append(" AND applicant.exam_ID>=:sExamID AND applicant.exam_ID<=:eExamID");
		} else {
			if (sExamID != null && !sExamID.trim().isEmpty()) {
				sb.append(" AND applicant.exam_ID>=:sExamID");
			}
			if (eExamID != null && !eExamID.trim().isEmpty()) {
				sb.append(" AND applicant.exam_ID<=:eExamID");
			}
		}
		if (!firstCom.equals("")) {
			sb.append(" AND firstCompany.com_Sname=:firstCom");
		}
		if (!secondCom.equals("")) {
			sb.append(" AND secondCompany.com_Sname=:secondCom");
		}
		countQuery = sb.toString();
		Query q = session.createQuery(countQuery);
		q.setParameter("jfYear", jfYear);
		q.setParameter("examPlace", examPlace);
		if (sDate != null) {
			q.setParameter("sDate", sDate);
		}
		if (eDate != null) {
			q.setParameter("eDate", eDate);
		}
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			q.setParameter("sExamID", sExamID);
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			q.setParameter("eExamID", eExamID);
		}
		if (!firstCom.equals("")) {
			q.setParameter("firstCom", firstCom);
		}
		if (!secondCom.equals("")) {
			q.setParameter("secondCom", secondCom);
		}
		Long count =(Long)q.uniqueResult();
		appCount = count.intValue();
		session.getTransaction().commit();
		session.close();
		return appCount;
	}

	public int getExamCount(String sExamID,String eExamID,Date sRegDate,Date eRegDate,
			String examPlace,int jfYear, String firstCom, String secondCom){
		boolean s_exam_year = false;     //開始JobFair年
		boolean s_exam_place = false;    //開始受験場所
		boolean e_exam_year = false;     //終了JobFair年
		boolean e_exam_place = false;    //終了受験場所
		int appCount = 0;
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			int s_exam_yr = Integer.parseInt(sExamID.substring(2,6));
			if(s_exam_yr == jfYear){
				s_exam_year = true;
			}
			s_exam_place = sExamID.contains(examPlace.substring(0,1));
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			int e_exam_yr = Integer.parseInt(eExamID.substring(2,6));
			if(e_exam_yr == jfYear){
				e_exam_year = true;
			}
			e_exam_place = eExamID.contains(examPlace.substring(0,1));
		}
        if ((sExamID != null && !sExamID.trim().isEmpty())
            && (eExamID != null && !eExamID.trim().isEmpty())) {
            if (s_exam_year == true && e_exam_year == true) {
				if (examPlace.trim().toUpperCase().equals("Yangon".toUpperCase())
						&& s_exam_place == true 
	                    && (examPlace.trim().toUpperCase().equals("Yangon".toUpperCase()) 
	                            && e_exam_place == true)){
					appCount = getExamineeCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, firstCom, secondCom);
	        	} else if (examPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
	        			&& s_exam_place == true 
	                    && (examPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
	                            && e_exam_place == true)){
	        		appCount = getExamineeCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, firstCom, secondCom);
	        	} else{
	        		appCount = 0;
	        	}
			} else {
			}
		} else if (sExamID != null && !sExamID.trim().isEmpty()) {
			if (s_exam_year == true) {
				if (examPlace.trim().toUpperCase().equals("Yangon".toUpperCase()) && s_exam_place == true) {
					appCount = getExamineeCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, firstCom, secondCom);
			    } else if (examPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
			            && s_exam_place == true) {
			    	appCount = getExamineeCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, firstCom, secondCom);
		        } else {
		        	appCount = 0;
			    }
			} else {
				appCount = 0;
			}
		} else if (eExamID != null && !eExamID.trim().isEmpty()) {
			if (e_exam_year == true) {
				if (examPlace.trim().toUpperCase().equals("Yangon".toUpperCase()) && e_exam_place == true) {
					appCount = getExamineeCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, firstCom, secondCom);
			    } else if (examPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
			            && e_exam_place == true) {
			    	appCount = getExamineeCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, firstCom, secondCom);
		        } else {
		        	appCount = 0;
			    }
			} else {
				appCount = 0;
			}
		} else {
			appCount = getExamineeCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, firstCom, secondCom);
		}
		return appCount;
	}

	/**
	 * ...面接者をデータベースに更新する...
	 * @param interview
	 * @throws SQLException
	 */
	public void updateInterview(Interview interview) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(interview);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * ...面接者をデータベースに登録する...
	 * @param interview
	 * @throws SQLException
	 */
	public void insertInterview(Interview interview) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(interview);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * ...受験IDで受験情報を検索する....
	 * @param examinee_ID
	 * @return examinee
	 * @throws SQLException
	 */
	public Examinee searchExaminee(int examinee_ID) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Examinee examinee = (Examinee) session.get(Examinee.class, examinee_ID);
		session.getTransaction().commit();
		session.close();
		return examinee;
	}

	/**
	 * ...会社IDで会社情報を検索する....
	 * @param com_ID
	 * @return company
	 * @throws SQLException
	 */
	public Company searchCompany(int com_ID) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = (Company) session.get(Company.class, com_ID);
		session.getTransaction().commit();
		session.close();
		return company;
	}

	/**
	 * ...受験IDで面接者を検索する。...
	 * @param examinee_ID
	 * @return interview
	 */
	public Interview checkInterview(int examinee_ID) {
		session = HibernateUtil.getSessionFactory().openSession();
		Interview interview = new Interview();
		session.beginTransaction();
		Query query = session.createQuery("FROM Interview where examinee_ID= " + examinee_ID);
		interview = (Interview) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return interview;
	}

	/**
	 * ...面接IDで面接者を検索する。...
	 * @param interview_ID
	 * @return interview
	 */
	public Interview searchInterview(int interview_ID) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Interview interview = (Interview)session.get(Interview.class, interview_ID);
		session.getTransaction().commit();
		session.close();
		return interview;
	}
}