/**
 * CV_A_051_会社別採用者決定画面
 * 作成履歴：19/04/2019 Khin Myo Wai
 * 作成概要：新規作成　会社別採用者決定処理
 * 
 * 更新履歴：08/05/2019 Khin Myo Wai
 * 更新概要：ORDER BY SQLを追加する	
 */
package mm.com.gic.ces.base.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mm.com.gic.ces.application.model.Employee;
import mm.com.gic.ces.application.model.Interview;
import mm.com.gic.ces.base.common.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class SuccessfulInterviewerService {
	Session session = null;
	List<Interview> InterviewerList;
	List<Interview> count;
	List<Interview> Check;
	List<Interview> UnCheck;
	List<Interview> addendedInterviewerlisttocount;
	List<Interview> interviewSuccess;
	List<Employee> employee;
	Date date=new Date();

	/**
	 * データベースに成功した会社員を保存する
	 * 
	 * @param interview
	 */
	public void saveEmployee(Interview interview) {
		Employee employee=new Employee();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		employee.setCompany(interview.getCompany());
		employee.setInterview(interview);
		session.save(employee);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 面接者を検索する
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
	 * 
	 */
	public List<Interview> SearchInterviewer(int jfYear, String examPlace,
			 Date sDate, Date eDate,String sExamID, String eExamID,
			 String firstCompany, String secondCompany,
		     int lowestIQMark, int highestIQMark
		    ) {
		List<Interview> interviewSearchlist = new ArrayList<Interview>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String query;
		StringBuilder sb=new StringBuilder("FROM Interview  WHERE examinee.examinee_Pass='Y' AND "
				+ "examinee.applicant.app_Check='Y' and examinee.applicant.app_JFYear=:jfYear "
				+"AND examinee.applicant.app_ExamPlace=:examPlace");
		if (sDate != null && eDate != null) {
			sb.append(" AND examinee.applicant.app_RegDate>=:sDate AND examinee.applicant.app_RegDate<=:eDate");
		} else {
			if (sDate != null) {
				sb.append(" AND examinee.applicant.app_RegDate>=:sDate");
			}
			if (eDate != null) {
				sb.append(" AND examinee.applicant.app_RegDate<=:eDate");
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
		if (firstCompany!= null && !firstCompany.trim().isEmpty()) {
			sb.append(" AND examinee.firstCompany.com_Sname=:firstCompany");
		}
		if (secondCompany!= null && !secondCompany.trim().isEmpty()) {
			sb.append( " AND examinee.secondCompany.com_Sname=:secondCompany");
		}
		if (lowestIQMark != 0 && highestIQMark != 0) {
			sb.append(" AND examinee.examinee_IQmark>=:lowestIQMark AND examinee.examinee_IQmark<=:highestIQMark");
		} else {
			if (lowestIQMark != 0) {
				sb.append(" AND examinee.examinee_IQmark>=:lowestIQMark");
			}
			if (highestIQMark != 0) {
				sb.append( " AND examinee.examinee_IQmark>=:highestIQMark");
			}
		}
		sb.append( " AND interview_Pass!='Y'");
		sb.append( " ORDER BY examinee.applicant.exam_ID");
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
		interviewSearchlist = q.list();
		session.getTransaction().commit();
		session.close();
		return interviewSearchlist;
	}
	
	public int getInterviewerCount(int jfYear, String examPlace, Date sDate, Date eDate, 
			String sExamID, String eExamID, String firstCompany, String secondCompany,
			 int lowestIQMark, int highestIQMark) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		int interviewerCount = 0;
		String query;
		StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM Interview WHERE examinee.examinee_Pass = 'Y' "
				+ "AND examinee.applicant.app_Check = 'Y' and examinee.applicant.app_JFYear =:jfYear "
				+"AND examinee.applicant.app_ExamPlace =:examPlace");
		if (sDate != null && eDate != null) {
			sb.append(" AND examinee.applicant.app_RegDate >=:sDate AND examinee.applicant.app_RegDate <=:eDate");
		} else {
			if (sDate != null) {
				sb.append(" AND examinee.applicant.app_RegDate>=:sDate");
			}
			if (eDate != null) {
				sb.append(" AND examinee.applicant.app_RegDate<=:eDate");
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
		if (firstCompany!= null && !firstCompany.trim().isEmpty()) {
			sb.append(" AND examinee.firstCompany.com_Sname=:firstCompany");
		}
		if (secondCompany!= null && !secondCompany.trim().isEmpty()) {
			sb.append( " AND examinee.secondCompany.com_Sname=:secondCompany");
		}
		if (lowestIQMark != 0 && highestIQMark != 0) {
			sb.append(" AND examinee.examinee_IQmark>=:lowestIQMark AND examinee.examinee_IQmark<=:highestIQMark");
		} else {
			if (lowestIQMark != 0) {
				sb.append(" AND examinee.examinee_IQmark>=:lowestIQMark");
			}
			if (highestIQMark != 0) {
				sb.append( " AND examinee.examinee_IQmark>=:highestIQMark");
			}
		}
		sb.append( " AND interview_Pass!='Y'");
		sb.append( " ORDER BY examinee.applicant.exam_ID");
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
		Long iCount = (Long)q.uniqueResult();
		interviewerCount = iCount.intValue();
		session.getTransaction().commit();
		session.close();
		return interviewerCount;
	}

	/**
	 *　チェックボックスがチェックされている場合は、interview_checkを 'Y'に設定する
	 * @param InterviewerList ,CheckList          
	 * @return
	 * @throws SQLException
	 */
	public List<Interview> checkListUpdate(List<String> checkedIDList) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			String query = "FROM Interview WHERE examinee.applicant.exam_ID IN (:checkedIDList)";
			Query q = session.createQuery(query);
			q.setParameterList("checkedIDList", checkedIDList);
			List<Interview> Check = new ArrayList<Interview>();
			Check = q.list();
			for (Interview e : Check) {
				e.setInterview_Pass("Y");
				e.setInterview_ex_key(e.getInterview_ex_key()+1);
				e.setLast_updateTime(date);
			}
			session.getTransaction().commit();
			session.close();
			return Check;
		} catch (Exception e) {
			e.printStackTrace();
			return Check;
		}
	}

	/**
	 * *　チェックボックスがチェックされていない場合は 、interview_checkを'N'に設定する
	 * @param InterviewerList          
	 * @return
	 * @throws SQLException
	 */
	public void checkListNullUpdate(List<Interview> searchInterviewerlist) {
		String checkedIDList = null;
		for (int i = 0; i < searchInterviewerlist.size(); i++) {

			checkedIDList = searchInterviewerlist.get(i).getExaminee().getApplicant()
					.getExam_ID();
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try {
				String query = "FROM Interview WHERE examinee.applicant.exam_ID IN (:checkedIDList)";
				Query q = session.createQuery(query);
				q.setParameter("checkedIDList", checkedIDList);
				List<Interview> Check = new ArrayList<Interview>();
				Check = q.list();
				for (Interview e : Check) {
					e.setInterview_Pass("N");
					e.setInterview_ex_key(e.getInterview_ex_key()+1);
					e.setLast_updateTime(date);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}
	}
}