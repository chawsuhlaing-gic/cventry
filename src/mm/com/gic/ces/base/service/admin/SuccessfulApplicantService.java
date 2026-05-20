/**
 * CV_A_041_会社別合格者決定画面

 * 作成履歴：19/04/2019 Khin Myo Wai
 * 作成概要：新規作成　成功面接官を決定処理
 * 
 * 更新履歴：08/05/2019 Khin Myo Wai
 * 更新概要：ORDER BY SQLを追加する	
 */
package mm.com.gic.ces.base.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.base.common.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class SuccessfulApplicantService {
	Session session=null;
	List<Examinee> SuccessfulExaminee;
	List<Examinee> SuccessfulExamineecount;
	List<Examinee> ExamineeCheck;
	List<Examinee> ExamineeUnCheck;
	Date date=new Date();

	/**
	 *　チェックボックスがチェックされている場合は、Examinee_Passを 'Y'に設定する
	 * @param InterviewerList ,CheckList          
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void checkListUpdate(List<String> checkedIDList) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			String query = "FROM Examinee WHERE applicant.exam_ID IN (:checkedIDList)";
			Query q = session.createQuery(query);
			q.setParameterList("checkedIDList", checkedIDList);
			List<Examinee> Check = new ArrayList<Examinee>();
			Check = q.list();
			for (Examinee e : Check) {
				e.setExaminee_Pass("Y");
				e.setExaminee_ex_key(e.getExaminee_ex_key()+1);
				e.setLast_updateTime(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * チェックボックスがチェックされている場合は、Examinee_Passを 'N'に設定する
	 * @param InterviewerList          
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void checkListNullUpdate(List<Examinee> searchInterviewerlist) {
		String checkedIDList = null;
		for (int i = 0; i < searchInterviewerlist.size(); i++) {

			checkedIDList = searchInterviewerlist.get(i).getApplicant()
					.getExam_ID();
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try {
				String query = "FROM Examinee WHERE applicant.exam_ID IN (:checkedIDList)";
				Query q = session.createQuery(query);
				q.setParameter("checkedIDList", checkedIDList);
				List<Examinee> Check = new ArrayList<Examinee>();
				Check = q.list();
				for (Examinee e : Check) {
					e.setExaminee_Pass("N");
					e.setExaminee_ex_key(e.getExaminee_ex_key()+1);
					e.setLast_updateTime(date);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}
	}

	/**
	 * 成功申請者を検索する
	 * @param jfYear
	 * @param examPlace
	 * @param sDate
	 * @param eDate
	 * @param sExamID
	 * @param eExamID
	 * @param firstCompany
	 * @param secondCompany
	 * @param lowestIQMark
	 * @param highestIQMark
	 * @return interviewSearchlist
	 */
	@SuppressWarnings("unchecked")
	public List<Examinee> SearchSuccessfulApplicant(int jfYear, String examPlace,
			 Date sDate, Date eDate,String sExamID, String eExamID,
			 String firstCompany, String secondCompany,
		     int lowestIQMark, int highestIQMark
		    ) {
		List<Examinee> examineeSearchlist = new ArrayList<Examinee>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String query;
		StringBuilder sb=new StringBuilder("FROM Examinee WHERE applicant.app_Check ='Y' and "
				+ "applicant.app_JFYear=:jfYear AND applicant.app_ExamPlace=:examPlace");
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
		if (firstCompany!= null && !firstCompany.trim().isEmpty()) {
			sb.append(" AND firstCompany.com_Sname=:firstCompany");
		}
		if (secondCompany!= null && !secondCompany.trim().isEmpty()) {
			sb.append( " AND secondCompany.com_Sname=:secondCompany");
		}
		if (lowestIQMark != 0 && highestIQMark != 0) {
			sb.append(" AND examinee_IQmark>=:lowestIQMark AND examinee_IQmark<=:highestIQMark");
		} else {
			if (lowestIQMark != 0) {
				sb.append(" AND examinee_IQmark>=:lowestIQMark");
			}
			if (highestIQMark != 0) {
				sb.append( " AND examinee_IQmark<=:highestIQMark");
			}
		}
		sb.append(" AND examinee_ID NOT IN (select examinee.examinee_ID FROM Interview WHERE interview_Pass='Y')");
		sb.append( " ORDER BY applicant.exam_ID");
		query=sb.toString();
		Query q = session.createQuery(query);
		q.setParameter("jfYear", jfYear);
		q.setParameter("examPlace", examPlace.trim());
		if (sDate != null) {
			q.setParameter("sDate", sDate);
		}
		if (eDate != null) {
			q.setParameter("eDate", eDate);
		}
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			q.setParameter("sExamID", sExamID.trim());
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			q.setParameter("eExamID", eExamID.trim());
		}
		if (firstCompany != null && !firstCompany.trim().isEmpty()) {
		q.setParameter("firstCompany",firstCompany);
		}
		if (secondCompany != null && !secondCompany.trim().isEmpty()) {
		q.setParameter("secondCompany", secondCompany);
		}
		if (lowestIQMark != 0) {
			q.setParameter("lowestIQMark", lowestIQMark);
		}
		if (highestIQMark !=0) {
			q.setParameter("highestIQMark", highestIQMark);
		}
		examineeSearchlist = q.list();
		session.getTransaction().commit();
		session.close();
		return examineeSearchlist;
	}
	
	public int getExamineeCount(int jfYear, String examPlace, Date sDate, Date eDate,String sExamID, 
			String eExamID, String firstCompany, String secondCompany, int lowestIQMark, 
			int highestIQMark) {
		int examineeCount = 0;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String query;
		StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM Examinee WHERE applicant.app_Check = 'Y' "
				+ "AND applicant.app_JFYear =:jfYear AND applicant.app_ExamPlace =:examPlace AND applicant.del_flag = 0");
		if (sDate != null && eDate != null) {
			sb.append(" AND applicant.app_RegDate >=:sDate AND applicant.app_RegDate <=:eDate");
		} else {
			if (sDate != null) {
				sb.append(" AND applicant.app_RegDate >=:sDate");
			}
			if (eDate != null) {
				sb.append(" AND applicant.app_RegDate <=:eDate");
			}
		}
		if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
				&& !eExamID.trim().isEmpty()) {
			sb.append(" AND applicant.exam_ID >=:sExamID AND applicant.exam_ID <=:eExamID");
		} else {
			if (sExamID != null && !sExamID.trim().isEmpty()) {
				sb.append(" AND applicant.exam_ID >=:sExamID");
			}
			if (eExamID != null && !eExamID.trim().isEmpty()) {
				sb.append(" AND applicant.exam_ID <=:eExamID");
			}
		}
		if (firstCompany!= null && !firstCompany.trim().isEmpty()) {
			sb.append(" AND firstCompany.com_Sname =:firstCompany");
		}
		if (secondCompany!= null && !secondCompany.trim().isEmpty()) {
			sb.append( " AND secondCompany.com_Sname =:secondCompany");
		}
		if (lowestIQMark != 0 && highestIQMark != 0) {
			sb.append(" AND examinee_IQmark >=:lowestIQMark AND examinee_IQmark <=:highestIQMark");
		} else {
			if (lowestIQMark != 0) {
				sb.append(" AND examinee_IQmark>=:lowestIQMark");
			}
			if (highestIQMark != 0) {
				sb.append( " AND examinee_IQmark<=:highestIQMark");
			}
		}
		sb.append(" AND examinee_ID NOT IN (SELECT examinee.examinee_ID FROM Interview WHERE interview_Pass = 'Y')");
		sb.append( " ORDER BY applicant.exam_ID");
		query = sb.toString();
		Query q = session.createQuery(query);
		q.setParameter("jfYear", jfYear);
		q.setParameter("examPlace", examPlace.trim());
		if (sDate != null) {
			q.setParameter("sDate", sDate);
		}
		if (eDate != null) {
			q.setParameter("eDate", eDate);
		}
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			q.setParameter("sExamID", sExamID.trim());
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			q.setParameter("eExamID", eExamID.trim());
		}
		if (firstCompany != null && !firstCompany.trim().isEmpty()) {
		    q.setParameter("firstCompany", firstCompany);
		}
		if (secondCompany != null && !secondCompany.trim().isEmpty()) {
		    q.setParameter("secondCompany", secondCompany);
		}
		if (lowestIQMark != 0) {
			q.setParameter("lowestIQMark", lowestIQMark);
		}
		if (highestIQMark !=0) {
			q.setParameter("highestIQMark", highestIQMark);
		}
		Long eCount = (Long)q.uniqueResult();
		examineeCount = eCount.intValue();
		session.getTransaction().commit();
		session.close();
		return examineeCount;
	}
}