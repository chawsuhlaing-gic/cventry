/**
 * CV_A_053_会社別不採用者情報照会画面
 * 作成履歴：21/2/2019 Nwe Ni Hlaing
 * 作成概要：新規作成
 * 
 * 更新履歴：12/04/2019 Nwe Ni Hlaing
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.base.service.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Interview;
import mm.com.gic.ces.base.common.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * データベースにある不採用者情報を取る
 */
public class UnemployeeInfoService {
	Session session = null; // セッション
	List<ApplicantInfo> lstApplicantInfo; // 申請者情報のリスト

	/**
	 * ...申請者IDによって申請者情報を取る ...
	 * 
	 * @param appIDList 申請者IDリスト
	 * @return　lstApplicantInfo 申請者情報リスト
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<ApplicantInfo> getApplicantID(List<Integer> appIDList)
			throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		lstApplicantInfo = new ArrayList<ApplicantInfo>();
		String query;
		StringBuilder sb=new StringBuilder("FROM Interview WHERE examinee.applicant.app_ID IN (:IDList)");
		sb.append( " ORDER BY examinee.applicant.exam_ID");
		query=sb.toString();
		Query queryResult = session.createQuery(query);
		queryResult.setParameterList("IDList", appIDList);
		List<Interview> interviews;
		interviews = queryResult.list();
		for (int i = 0; i < interviews.size(); i++) {
			Interview interview = (Interview) interviews.get(i);
			ApplicantInfo appInfo = new ApplicantInfo();
			appInfo.setInterview(interview);
			lstApplicantInfo.add(appInfo);
		}
		session.getTransaction().commit();
		return lstApplicantInfo;
	}

	/**
	 * ...検索情報によって申請者情報を取得する...
	 * 
	 * @param jfYear　JobFair年
	 * @param examPlace　受験場所
	 * @param sDate　登録開始日
	 * @param eDate　登録終了日
	 * @param sExamID 開始受験ID
	 * @param eExamID 終了受験ID
	 * @return lstApplicantInfo 申請者情報リスト
	 */
	@SuppressWarnings("unchecked")
	public List<ApplicantInfo> searchUnemployee(int jfYear, Date startDate,
			Date endDate, String examPlace, String sExamID, String eExamID)
			throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		lstApplicantInfo = new ArrayList<ApplicantInfo>();
		String sDate = "";
		String eDate = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (startDate != null) {
			sDate = dateFormat.format(startDate);
		}
		if (endDate != null) {
			eDate = dateFormat.format(endDate);
		}
		
		String query;
		StringBuilder sb=new StringBuilder("FROM Interview interview WHERE interview.interview_Pass='N' AND examinee.applicant.app_JFYear=:jfYear "
				+ "AND examinee.applicant.app_ExamPlace=:examPlace");

		if (startDate != null && endDate != null) {
			sb.append(" and interview_Date>=:startDate and interview_Date<=:endDate");
		} else {
			if (startDate != null) {
				sb.append(" and interview_Date>=:startDate");
			} else if (endDate != null) {
				sb.append(" and interview_Date<=:endDate");
			}
		}
		
		if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
				&& !eExamID.trim().isEmpty()) {
			sb.append(" AND examinee.applicant.exam_ID>=:sExamID AND examinee.applicant.exam_ID<=:eExamID");
		} else {
			if (sExamID != null && !sExamID.trim().isEmpty()) {
				sb.append(" AND examinee.applicant.exam_ID>=:sExamID");
			}
			if (eExamID != null && !eExamID.trim().isEmpty()) {
				sb.append(" AND examinee.applicant.exam_ID<=:eExamID");
			}
		}

		sb.append( " ORDER BY examinee.applicant.exam_ID");
		query=sb.toString();
		Query q = session.createQuery(query);
		q.setParameter("jfYear", jfYear);
		q.setParameter("examPlace", examPlace);

		if (startDate != null && endDate != null) {
			q.setParameter("startDate", sDate);
			q.setParameter("endDate", eDate);
		} else {
			if (startDate != null) {
				q.setParameter("startDate", sDate);
			} else if (endDate != null) {
				q.setParameter("endDate", eDate);
			}
		}
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			q.setParameter("sExamID", sExamID);
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			q.setParameter("eExamID", eExamID);
		}
		
		List<Interview> interviews;
		interviews = q.list();
		ApplicantInfo appInfo = new ApplicantInfo();
		for (int i = 0; i < interviews.size(); i++) {
			Interview interview = (Interview) interviews.get(i);
			appInfo = new ApplicantInfo();
			appInfo.setInterview(interview);
			if (appInfo != null) {
				lstApplicantInfo.add(appInfo);
			}
		}
		session.getTransaction().commit();
		session.close();
		return lstApplicantInfo;
	}
	
	/**
	 * ...検索情報によって申請者情報を取得する...
	 * 
	 * @param jfYear　JobFair年
	 * @param examPlace　受験場所
	 * @param sDate　登録開始日
	 * @param eDate　登録終了日
	 * @param sExamID 開始受験ID
	 * @param eExamID 終了受験ID
	 * @return count
	 */
	public int getCountList(int jfYear, Date startDate,
			Date endDate, String examPlace, String sExamID, String eExamID)
			throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		lstApplicantInfo = new ArrayList<ApplicantInfo>();
		String sDate = "";
		String eDate = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (startDate != null) {
			sDate = dateFormat.format(startDate);
		}
		if (endDate != null) {
			eDate = dateFormat.format(endDate);
		}
		
		String query;
		StringBuilder sb=new StringBuilder("SELECT COUNT(*) FROM Interview interview WHERE interview.interview_Pass='N' AND examinee.applicant.app_JFYear=:jfYear "
				+ "AND examinee.applicant.app_ExamPlace=:examPlace");

		if (startDate != null && endDate != null) {
			sb.append(" and interview_Date>=:startDate and interview_Date<=:endDate");
		} else {
			if (startDate != null) {
				sb.append(" and interview_Date>=:startDate");
			} else if (endDate != null) {
				sb.append(" and interview_Date<=:endDate");
			}
		}
		
		if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
				&& !eExamID.trim().isEmpty()) {
			sb.append(" AND examinee.applicant.exam_ID>=:sExamID AND examinee.applicant.exam_ID<=:eExamID");
		} else {
			if (sExamID != null && !sExamID.trim().isEmpty()) {
				sb.append(" AND examinee.applicant.exam_ID>=:sExamID");
			}
			if (eExamID != null && !eExamID.trim().isEmpty()) {
				sb.append(" AND examinee.applicant.exam_ID<=:eExamID");
			}
		}

		sb.append( " ORDER BY examinee.applicant.exam_ID");
		query=sb.toString();
		Query q = session.createQuery(query);
		q.setParameter("jfYear", jfYear);
		q.setParameter("examPlace", examPlace);

		if (startDate != null && endDate != null) {
			q.setParameter("startDate", sDate);
			q.setParameter("endDate", eDate);
		} else {
			if (startDate != null) {
				q.setParameter("startDate", sDate);
			} else if (endDate != null) {
				q.setParameter("endDate", eDate);
			}
		}
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			q.setParameter("sExamID", sExamID);
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			q.setParameter("eExamID", eExamID);
		}
		int count;
		Long iCount = (Long)q.uniqueResult();
		count = iCount.intValue();	
		session.getTransaction().commit();
		session.close();
		return count;
	}

	/**
	 * ...会社名を取得する...
	 * 
	 * @param jfYear　JobFair年
	 * @return lstCompany 会社リスト
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Company> getCompany(int jfYear) throws IOException {

		session = HibernateUtil.getSessionFactory().openSession();
		List<Company> lstCompany = new ArrayList<Company>();
		session.beginTransaction();
		Query q = session
				.createQuery("FROM Company WHERE del_flag=0 AND com_Reg_Year=:jfYear");
		q.setParameter("jfYear", jfYear);
		lstCompany = q.list();
		session.getTransaction().commit();
		session.close();
		return lstCompany;
	}
}