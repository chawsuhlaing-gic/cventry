/**
 * ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¨ÃƒÂ£Ã¯Â¿Â½Ã‚Â¦ÃƒÂ£Ã¯Â¿Â½Ã‚Â®ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â»ÃƒÂ©Ã¯Â¿Â½Ã‚Â¢
 * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½01/04/2019 Cho Cho Lwin, Jar Moon Taung
 * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½
 * 
 * ÃƒÂ¦Ã¢â‚¬ÂºÃ‚Â´ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½08/05/2019 Khin Myo Wai
 * ÃƒÂ¦Ã¢â‚¬ÂºÃ‚Â´ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½ORDER BY SQLÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬â„¢ÃƒÂ¨Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Â¹
 */

package mm.com.gic.ces.base.common;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.base.service.admin.AttendedApplicantService;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã†â€™Ã‚Â¼ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¿ÃƒÂ£Ã†â€™Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¹ÃƒÂ£Ã¯Â¿Â½Ã‚Â«ÃƒÂ£Ã¯Â¿Â½Ã¢â‚¬Å¡ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Â¹ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â±ÃƒÂ©Ã¢â€šÂ¬Ã…Â¡ÃƒÂ¦Ã†â€™Ã¢â‚¬Â¦ÃƒÂ¥Ã‚Â Ã‚Â±ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬â„¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¯Ã‚Â¿Ã‚Â½
 */
public class CommonService {
	Session session = null;   //ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â»ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â·ÃƒÂ£Ã†â€™Ã‚Â§ÃƒÂ£Ã†â€™Ã‚Â³

	/**
	 * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½01/04/2019 Cho Cho Lwin
     * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½
     *
	 * ...JobFairÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬â„¢ÃƒÂ¨Ã‚Â¡Ã…â€™ÃƒÂ£Ã¢â‚¬Å¡Ã¯Â¿Â½ÃƒÂ£Ã¢â‚¬Å¡Ã…â€™ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Â¹ÃƒÂ¥Ã‚Â¹Ã‚Â´ÃƒÂ£Ã¯Â¿Â½Ã‚Â¨ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¾Ã¢â‚¬â€�ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¿Ã‚Â½...
	 * @return lstYear JobFairÃƒÂ¥Ã‚Â¹Ã‚Â´ÃƒÂ£Ã†â€™Ã‚ÂªÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> selectJFYear() {
		List<Integer> lstYear = new ArrayList<Integer>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("SELECT DISTINCT app.app_JFYear FROM Applicant app "
				+ "WHERE app.del_flag = 0 ORDER BY app.app_JFYear DESC");
		lstYear = q.list();
		session.getTransaction().commit();
		session.close();
		return lstYear;
	}
	
	/**
	 * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½27/08/2019 Cho Cho Lwin
     * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½
     *
	 * ...ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â³ÃƒÂ¨Ã‚Â«Ã¢â‚¬Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬â„¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¾Ã¢â‚¬â€�ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¿Ã‚Â½...
	 * @return appCount ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â³ÃƒÂ¨Ã‚Â«Ã¢â‚¬Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°
	 */
	public int getApplicantCount(int jfYear, String examPlace, Date sDate, Date eDate, 
			String sExamID, String eExamID, int screenID) {
		int appCount = 0;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String countQuery;
		StringBuilder stringBuilder = new StringBuilder("SELECT COUNT(exam_ID) FROM Applicant WHERE del_flag = 0 AND "
				+ "app_JFYear =:jfYear AND app_ExamPlace =:examPlace");
		if (sDate != null && eDate != null) {
			stringBuilder.append(" AND app_RegDate >=:sDate AND app_RegDate <=:eDate");
		} else {
			if (sDate != null) {
				stringBuilder.append(" AND app_RegDate >=:sDate");
			}
			if (eDate != null) {
				stringBuilder.append(" AND app_RegDate <=:eDate");
			}
		}
		if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
				&& !eExamID.trim().isEmpty()) {
			stringBuilder.append(" AND exam_ID >=:sExamID AND exam_ID <=:eExamID");
		} else {
			if (sExamID != null && !sExamID.trim().isEmpty()) {
				stringBuilder.append(" AND exam_ID >=:sExamID");
			}
			if (eExamID != null && !eExamID.trim().isEmpty()) {
				stringBuilder.append(" AND exam_ID <=:eExamID");
			}
		}
		if(screenID == 31) {
			stringBuilder.append(" AND app_ID NOT IN (SELECT applicant.app_ID FROM Examinee WHERE examinee_Pass = 'Y')");
			stringBuilder.append(" AND app_ID NOT IN (select i.examinee.applicant.app_ID FROM Interview i WHERE i.interview_Pass = 'Y' )");
		}
		if(screenID == 32) {
			stringBuilder.append(" AND app_check='Y' ");
		}
		countQuery = stringBuilder.toString();
		Query q = session.createQuery(countQuery);
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
		Long count = (Long)q.uniqueResult();
		appCount = count.intValue();
		session.getTransaction().commit();
		session.close();
		return appCount;
	}

	/**
	 * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½01/04/2019 Cho Cho Lwin
     * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½
     * 
     * ÃƒÂ¦Ã¢â‚¬ÂºÃ‚Â´ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½05/09/2019 Cho Cho Lwin
     * ÃƒÂ¦Ã¢â‚¬ÂºÃ‚Â´ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½SQLÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¯Â¿Â½Ã‚Â®ÃƒÂ¤Ã‚Â»Ã‚Â£ÃƒÂ£Ã¢â‚¬Å¡Ã¯Â¿Â½ÃƒÂ£Ã¢â‚¬Å¡Ã…Â ÃƒÂ£Ã¯Â¿Â½Ã‚Â«CriteriaÃƒÂ£Ã¯Â¿Â½Ã‚Â«ÃƒÂ¥Ã‚Â¤Ã¢â‚¬Â°ÃƒÂ¦Ã¢â‚¬ÂºÃ‚Â´ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Â¹
     *
	 * ...ÃƒÂ¦Ã‚Â¤Ã…â€œÃƒÂ§Ã‚Â´Ã‚Â¢ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â Ã‚Â±ÃƒÂ£Ã¯Â¿Â½Ã‚Â«ÃƒÂ£Ã¢â‚¬Å¡Ã‹â€ ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¯Â¿Â½Ã‚Â¦ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â³ÃƒÂ¨Ã‚Â«Ã¢â‚¬Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â Ã‚Â±ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬â„¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¾Ã¢â‚¬â€�ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¿Ã‚Â½...
	 * @param jfYear JobFairÃƒÂ¥Ã‚Â¹Ã‚Â´
	 * @param examPlace ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€�ÃƒÂ©Ã‚Â¨Ã¢â‚¬Å“ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½
	 * @param sDate ÃƒÂ§Ã¢â€žÂ¢Ã‚Â»ÃƒÂ©Ã…â€™Ã‚Â²ÃƒÂ©Ã¢â‚¬â€œÃ¢â‚¬Â¹ÃƒÂ¥Ã‚Â§Ã¢â‚¬Â¹ÃƒÂ¦Ã¢â‚¬â€�Ã‚Â¥
	 * @param eDate ÃƒÂ§Ã¢â€žÂ¢Ã‚Â»ÃƒÂ©Ã…â€™Ã‚Â²ÃƒÂ§Ã‚ÂµÃ¢â‚¬Å¡ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬â€�Ã‚Â¥
	 * @param sExamID ÃƒÂ©Ã¢â‚¬â€œÃ¢â‚¬Â¹ÃƒÂ¥Ã‚Â§Ã¢â‚¬Â¹ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€�ÃƒÂ©Ã‚Â¨Ã¢â‚¬Å“ID
	 * @param eExamID ÃƒÂ§Ã‚ÂµÃ¢â‚¬Å¡ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€�ÃƒÂ©Ã‚Â¨Ã¢â‚¬Å“ID
	 * @return lstApplicant ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â³ÃƒÂ¨Ã‚Â«Ã¢â‚¬Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã†â€™Ã‚ÂªÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½
	 */
	@SuppressWarnings("unchecked")
	public List<Applicant> getApplicantList(int jfYear, String examPlace,
			Date sDate, Date eDate, String sExamID, String eExamID, int screenID) {
		List<Applicant> lstApplicant = new ArrayList<Applicant>();
		List<Integer> lstAppIDFromExaminee = new ArrayList<Integer>();
		//List<Integer> lstAppIDFromInterview = new ArrayList<Integer>();
		AttendedApplicantService attendedApplicantService = new AttendedApplicantService();

		if(screenID == 31) {
			lstAppIDFromExaminee = attendedApplicantService.getAppIDListFromExaminee();
			//lstAppIDFromInterview = attendedApplicantService.getAppIDListFromInterview();
		}
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try{
			Criteria cr = session.createCriteria(Applicant.class)
				    .setProjection(Projections.projectionList()
				      .add(Projections.property("app_ID"), "app_ID")
				      .add(Projections.property("exam_ID"), "exam_ID")
				      .add(Projections.property("app_Name"), "app_Name")
				      .add(Projections.property("app_Gender"), "app_Gender")
				      .add(Projections.property("app_Email"), "app_Email")
				      .add(Projections.property("app_DOB"), "app_DOB")
				      .add(Projections.property("app_Nrc"), "app_Nrc")
				      .add(Projections.property("app_PhNo"), "app_PhNo")
				      .add(Projections.property("app_Degree"), "app_Degree")
				      .add(Projections.property("app_ACYear"), "app_ACYear")
				      .add(Projections.property("app_Education"), "app_Education")
				      .add(Projections.property("app_University"), "app_University")
				      .add(Projections.property("app_CityofUniversity"), "app_CityofUniversity")
				      .add(Projections.property("app_RegDate"), "app_RegDate")
				      .add(Projections.property("app_RegDate"), "app_RegDate")
				      .add(Projections.property("app_Experience"), "app_Experience")
				      .add(Projections.property("jp_company_exp"), "jp_company_exp")
				      .add(Projections.property("app_JPSkill"), "app_JPSkill")
				      .add(Projections.property("app_ENGSkill"), "app_ENGSkill")
				      .add(Projections.property("app_ExamPlace"), "app_ExamPlace")
				      .add(Projections.property("app_Address"), "app_Address")
				      .add(Projections.property("app_ex_key"), "app_ex_key")
				      .add(Projections.property("del_flag"), "del_flag"))
				    .setResultTransformer(Transformers.aliasToBean(Applicant.class));
	        cr.add(Restrictions.eq("del_flag", 0));
	        cr.add(Restrictions.eq("app_JFYear", jfYear));
	        cr.add(Restrictions.eq("app_ExamPlace", examPlace));
	        
	        if (sDate != null && eDate != null) {
	        	cr.add(Restrictions.ge("app_RegDate", sDate)) 
		          .add(Restrictions.le("app_RegDate", eDate));
			} else {
				if (sDate != null) {
					cr.add(Restrictions.ge("app_RegDate", sDate));
				}
				if (eDate != null) {
					cr.add(Restrictions.le("app_RegDate", eDate));
				}
			}
			if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
					&& !eExamID.trim().isEmpty()) {
				cr.add(Restrictions.ge("exam_ID", sExamID)) 
		          .add(Restrictions.le("exam_ID", eExamID));
			} else {
				if (sExamID != null && !sExamID.trim().isEmpty()) {
					cr.add(Restrictions.ge("exam_ID", sExamID));
				}
				if (eExamID != null && !eExamID.trim().isEmpty()) {
					cr.add(Restrictions.le("exam_ID", eExamID));
				}
			}
			if(screenID == 31) {
				cr.add(Restrictions.not(Restrictions.in("app_ID", lstAppIDFromExaminee)));
				//cr.add(Restrictions.not(Restrictions.in("app_ID", lstAppIDFromInterview)));
			}
	        cr.addOrder(Order.asc("exam_ID"));
			lstApplicant = cr.list();
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		session.getTransaction().commit();
		session.close();
		return lstApplicant;
	}
	
    /**
     * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½01/04/2019 Jar Moon Taung
     * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½
     * 
     * ÃƒÂ¦Ã¢â‚¬ÂºÃ‚Â´ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½05/09/2019 Cho Cho Lwin
     * ÃƒÂ¦Ã¢â‚¬ÂºÃ‚Â´ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½SQLÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¯Â¿Â½Ã‚Â®ÃƒÂ¤Ã‚Â»Ã‚Â£ÃƒÂ£Ã¢â‚¬Å¡Ã¯Â¿Â½ÃƒÂ£Ã¢â‚¬Å¡Ã…Â ÃƒÂ£Ã¯Â¿Â½Ã‚Â«CriteriaÃƒÂ£Ã¯Â¿Â½Ã‚Â«ÃƒÂ¥Ã‚Â¤Ã¢â‚¬Â°ÃƒÂ¦Ã¢â‚¬ÂºÃ‚Â´ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Â¹
     *
     * ...ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â³ÃƒÂ¨Ã‚Â«Ã¢â‚¬Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½IDÃƒÂ£Ã¯Â¿Â½Ã‚Â«ÃƒÂ£Ã¢â‚¬Å¡Ã‹â€ ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¯Â¿Â½Ã‚Â¦ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â³ÃƒÂ¨Ã‚Â«Ã¢â‚¬Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â Ã‚Â±ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬â„¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¾Ã¢â‚¬â€�ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¿Ã‚Â½...
     * @param lstApplicantID ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â³ÃƒÂ¨Ã‚Â«Ã¢â‚¬Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½IDÃƒÂ£Ã†â€™Ã‚ÂªÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½
     * @return lstApplicant ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â³ÃƒÂ¨Ã‚Â«Ã¢â‚¬Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã†â€™Ã‚ÂªÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¹ÃƒÂ¯Ã‚Â¿Ã‚Â½
     */
	@SuppressWarnings("unchecked")
	public List<Applicant> excelFile(List<Integer> lstApplicantID){
		List<Applicant> lstApplicant = new ArrayList<Applicant>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			Criteria cr = session.createCriteria(Applicant.class)
                      .setProjection(Projections.projectionList()
                            .add(Projections.property("exam_ID"), "exam_ID")
                            .add(Projections.property("app_Name"), "app_Name")
                            .add(Projections.property("app_Nrc"), "app_Nrc")
                            .add(Projections.property("app_DOB"), "app_DOB")
                            .add(Projections.property("app_Gender"), "app_Gender")
                            .add(Projections.property("app_PhNo"), "app_PhNo")
                            .add(Projections.property("app_Email"), "app_Email")
                            .add(Projections.property("app_Address"), "app_Address")
                            .add(Projections.property("app_CityofUniversity"), "app_CityofUniversity")
                            .add(Projections.property("app_University"), "app_University")
                            .add(Projections.property("app_Degree"), "app_Degree")
                            .add(Projections.property("app_ACYear"), "app_ACYear")
                            .add(Projections.property("app_ACPlan"), "app_ACPlan")
                            .add(Projections.property("app_EGD"), "app_EGD")
                            .add(Projections.property("app_FEC"), "app_FEC")
                            .add(Projections.property("app_FEPlan"), "app_FEPlan")
                            .add(Projections.property("app_GICAcademy"), "app_GICAcademy")
                            .add(Projections.property("app_Experience"), "app_Experience")
                            .add(Projections.property("app_CurrentCom"), "app_CurrentCom")
                            .add(Projections.property("app_CurrentPos"), "app_CurrentPos")
                            .add(Projections.property("app_JPSkill"), "app_JPSkill")
                            .add(Projections.property("app_ENGSkill"), "app_ENGSkill")
                            .add(Projections.property("app_ITSkill"), "app_ITSkill")
                            .add(Projections.property("app_JFYear"), "app_JFYear")
                            .add(Projections.property("app_RegDate"), "app_RegDate")
                            .add(Projections.property("app_ExamPlace"), "app_ExamPlace")
                            .add(Projections.property("mail_Check"), "mail_Check")
                            .add(Projections.property("zero_yen_study"), "zero_yen_study")
                            .add(Projections.property("jp_company_exp"), "jp_company_exp"))
                        .setResultTransformer(Transformers.aliasToBean(Applicant.class));
			cr.add(Restrictions.in("app_ID", lstApplicantID));
			lstApplicant = cr.list();
		} catch(Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		return lstApplicant;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> experienceList(List examIdList){
	    List<Object[]> lstExperience = new ArrayList<>();
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    try {
	        String sql = "SELECT " +
	                     "e.exam_ID, " +
	                     "SUM(e.exp_Year) AS Year, " +
	                     "GROUP_CONCAT(CONCAT(e.exp_Company, '(', e.exp_Year, ')')) AS Company, " +
	                     "GROUP_CONCAT(e.exp_Position) AS Position " +
	                     "FROM tbl_experience e " +
	                     "WHERE e.exam_ID IN (:examIdList) " +
	                     "GROUP BY e.exam_ID";
	        
	        Query query = session.createSQLQuery(sql);
	        query.setParameterList("examIdList", examIdList);
	        lstExperience = query.list(); // List<Object[]> where each Object[] holds the columns per row
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.getTransaction().commit();
	        session.close();
	    }
	    return lstExperience;
	}
	
	/**
     * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½18/04/2019 Thet Ngon Tun
     * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½
     *
	 * ÃƒÂ©Ã¯Â¿Â½Ã‚Â¸ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Å“ÃƒÂ£Ã¯Â¿Â½Ã‚Â JobFairÃƒÂ¥Ã‚Â¹Ã‚Â´ÃƒÂ£Ã¯Â¿Â½Ã‚Â«ÃƒÂ£Ã¢â‚¬Å¡Ã‹â€ ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¯Â¿Â½Ã‚Â¦ÃƒÂ§Ã¢â€žÂ¢Ã‚Â»ÃƒÂ©Ã…â€™Ã‚Â²ÃƒÂ£Ã¯Â¿Â½Ã¢â‚¬â€�ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¤Ã‚Â¼Ã…Â¡ÃƒÂ§Ã‚Â¤Ã‚Â¾ÃƒÂ¥Ã¯Â¿Â½Ã¯Â¿Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¨Ã‚Â¦Ã‚Â§ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬â„¢ÃƒÂ£Ã†â€™Ã¢â‚¬Â¡ÃƒÂ£Ã†â€™Ã‚Â¼ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¿ÃƒÂ£Ã†â€™Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¹ÃƒÂ£Ã¯Â¿Â½Ã¢â‚¬Â¹ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Â°ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¾Ã¢â‚¬â€�ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¿Ã‚Â½
	 * @param jfYear ÃƒÂ©Ã¯Â¿Â½Ã‚Â¸ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Å“ÃƒÂ£Ã¯Â¿Â½Ã‚Â JobFairÃƒÂ¥Ã‚Â¹Ã‚Â´
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Company> getCompany(int jfYear) throws IOException {

		session = HibernateUtil.getSessionFactory().openSession();
		List<Company> lstCompany = new ArrayList<Company>();
		session.beginTransaction();
		Query q = session.createQuery("FROM Company WHERE del_flag = 0 AND com_Reg_Year =:jfYear");
		q.setParameter("jfYear", jfYear);
		lstCompany = q.list();
		session.getTransaction().commit();
		session.close();
		return lstCompany;
	}

	
	/**
	 * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½14/05/2019 Thet Ngon Tun
     * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½
     * 
     * ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â­ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¹ÃƒÂ£Ã†â€™Ã‹â€ ÃƒÂ£Ã†â€™Ã¢â‚¬Â¢ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¡ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¤ÃƒÂ£Ã†â€™Ã‚Â«ÃƒÂ£Ã¯Â¿Â½Ã¢â‚¬Â¹ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Â°ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â½Ã¢â‚¬Å¡ÃƒÂ¯Ã‚Â¼Ã‚Â¦ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â½Ã¢â‚¬â„¢ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬â„¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¾Ã¢â‚¬â€�ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¿Ã‚Â½
	 * @return ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â½Ã¢â‚¬Å¡ÃƒÂ¯Ã‚Â¼Ã‚Â¦ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â½Ã¢â‚¬â„¢ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½
	 */
	@SuppressWarnings({ "static-access"})
	public String getJobFairPlace(String selectedExamPlace) {
		CommonUtility comUtility = new CommonUtility();
		String jobFairPlace="";
		try {
			Properties prop = comUtility.getValue("JobFairInfo.properties");
			if (selectedExamPlace.toLowerCase().equals("yangon")) {
				jobFairPlace = prop.getProperty("JobFairPlaceYgn");
			} else if(selectedExamPlace.toLowerCase().equals("mandalay")) {
				jobFairPlace = prop.getProperty("JobFairPlaceMdy");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			jobFairPlace="";
		}
		return jobFairPlace;
	}

	/**
	 * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¥Ã‚Â±Ã‚Â¥ÃƒÂ¦Ã‚Â­Ã‚Â´ÃƒÂ¯Ã‚Â¿Ã‚Â½14/05/2019 Thet Ngon Tun
     * ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¨Ã‚Â¦Ã‚Â¼ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â°ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã…â€œÃƒÂ¯Ã‚Â¿Ã‚Â½
     * 
     * ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â­ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¹ÃƒÂ£Ã†â€™Ã‹â€ ÃƒÂ£Ã†â€™Ã¢â‚¬Â¢ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¡ÃƒÂ£Ã¢â‚¬Å¡Ã‚Â¤ÃƒÂ£Ã†â€™Ã‚Â«ÃƒÂ£Ã¯Â¿Â½Ã¢â‚¬Â¹ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬Â°ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â½Ã¢â‚¬Å¡ÃƒÂ¯Ã‚Â¼Ã‚Â¦ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â½Ã¢â‚¬â„¢ÃƒÂ¦Ã¢â‚¬â€�Ã‚Â¥ÃƒÂ£Ã¢â‚¬Å¡Ã¢â‚¬â„¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¾Ã¢â‚¬â€�ÃƒÂ£Ã¯Â¿Â½Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¿Ã‚Â½
	 * @return ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â½Ã¢â‚¬Å¡ÃƒÂ¯Ã‚Â¼Ã‚Â¦ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â½Ã¢â‚¬â„¢ÃƒÂ¦Ã¢â‚¬â€�Ã‚Â¥
	 */
	@SuppressWarnings("static-access")
	public String getJobFairDate(String selectedExamPlace) {
		CommonUtility comUtility = new CommonUtility();		
		String jobFairDate = "";
		try {
		    Properties prop = comUtility.getValue("JobFairInfo.properties");
		    if(selectedExamPlace.toLowerCase().equals("yangon")) {
			    jobFairDate = prop.getProperty("JobFairDateYgn");
		    } else if(selectedExamPlace.toLowerCase().equals("mandalay")) {
			    jobFairDate = prop.getProperty("JobFairDateMdy");
		    }
		   
		}catch (Exception e) {
			e.printStackTrace();
			jobFairDate = "";
		}
		return jobFairDate;
	}
}