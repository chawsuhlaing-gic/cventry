/**
 * CV_A_031_受験社決定画面
 * 作成履歴：19/04/2019 Khin Myo Wai
 * 作成概要：新規作成　申請者出席を決定決定処理
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.base.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.base.common.HibernateUtil;

public class AttendedApplicantService {
	Session session = null;
	List<Applicant> applicantlist;
	List<Applicant> addendedApplicantlisttocount;
	Date date = new Date();

	/**
	 * チェックボックスがチェックされている場合は、App_Checkを 'Y'に設定する
	 * @param checkedIDList
	 */
	@SuppressWarnings("unchecked")
	public void checkListUpdate(List<String> checkedIDList) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {

			String query = "FROM Applicant WHERE exam_ID IN (:checkedIDList)";
			Query q = session.createQuery(query);
			q.setParameterList("checkedIDList", checkedIDList);
			List<Applicant> Check = new ArrayList<Applicant>();
			Check = q.list();
			for (Applicant e : Check) {
				e.setApp_Check("Y");
				e.setApp_ex_key(e.getApp_ex_key()+1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();

	}

	/**
	 * チェックボックスがチェックされていない場合は 、App_Checkを 'N'に設定する
	 * @param searchApplicantList
	 */
	@SuppressWarnings("unchecked")
	public void checkListNullUpdate(List<Applicant> searchApplicantList) {

		String checkedIDList = null;
		for (int i = 0; i < searchApplicantList.size(); i++) {

			checkedIDList = searchApplicantList.get(i).getExam_ID();
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try {
				String query = "FROM Applicant WHERE exam_ID IN (:checkedIDList)";
				Query q = session.createQuery(query);
				q.setParameter("checkedIDList", checkedIDList);
				List<Applicant> Check = new ArrayList<Applicant>();
				Check = q.list();
				for (Applicant e : Check) {
					e.setApp_Check("N");
					e.setApp_ex_key(e.getApp_ex_key()+1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}
	}

	/**
	 * 検索情報によって申請者情報を取得する
	 * @param year
	 * @return attendedApplicantlist
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<Applicant> getApplicantList(int jfYear, String examPlace,
			Date sDate, Date eDate, String sExamID, String eExamID) {
		List<Applicant> lstApplicant = new ArrayList<Applicant>();
		
		try {
			List<Integer> lstAppIDFromExaminee = new ArrayList<Integer>();
			lstAppIDFromExaminee = this.getAppIDListFromExaminee();
			//List<Integer> lstAppIDFromInterview = new ArrayList<Integer>();
			//lstAppIDFromInterview = this.getAppIDListFromInterview();
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
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
                            .add(Projections.property("app_Experience"), "app_Experience")
                            .add(Projections.property("app_CurrentCom"), "app_CurrentCom")
                            .add(Projections.property("app_CurrentPos"), "app_CurrentPos")
                            .add(Projections.property("app_JPSkill"), "app_JPSkill")
                            .add(Projections.property("app_ENGSkill"), "app_ENGSkill")
                            .add(Projections.property("app_ITSkill"), "app_ITSkill")
                            .add(Projections.property("app_JFYear"), "app_JFYear")
                            .add(Projections.property("app_RegDate"), "app_RegDate")
                            .add(Projections.property("app_ExamPlace"), "app_ExamPlace")
                            .add(Projections.property("mail_Check"), "mail_Check"))
                        .setResultTransformer(Transformers.aliasToBean(Applicant.class));
			cr.add(Restrictions.eq("del_flag", 0));
	        cr.add(Restrictions.eq("app_JFYear", jfYear));
	        cr.add(Restrictions.eq("app_ExamPlace", examPlace));
			cr.add(Restrictions.not(Restrictions.in("app_ID", lstAppIDFromExaminee)));
			//cr.add(Restrictions.not(Restrictions.in("app_ID", lstAppIDFromInterview)));
			lstApplicant = cr.list();
		} catch(Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		return lstApplicant;
	}
	/**
	 * 作成履歴：01/04/2019 Cho Cho Lwin
     * 作成概要：新規作成
     *
	 * ...JobFairを行われる年と取得する...
	 * @return lstYear JobFair年リスト
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAppIDListFromInterview() {
		List<Integer> lstAppIDFromInterview = new ArrayList<Integer>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try{
			Query q = session.createQuery("SELECT i.examinee.applicant.app_ID FROM Interview i WHERE i.interview_Pass = 'Y'");
			lstAppIDFromInterview = q.list();
		} catch(Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		return lstAppIDFromInterview;
	}
	
	/**
	 * 作成履歴：01/04/2019 Cho Cho Lwin
     * 作成概要：新規作成
     *
	 * ...JobFairを行われる年と取得する...
	 * @return lstYear JobFair年リスト
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAppIDListFromExaminee() {
		List<Integer> lstApp = new ArrayList<Integer>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try{
			Query q = session.createQuery("SELECT applicant.app_ID FROM Examinee WHERE examinee_Pass = 'Y'");
			lstApp = q.list();
		} catch(Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		return lstApp;
	}
}