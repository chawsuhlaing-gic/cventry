/**
 * CV_U_011_CVEntryç”»é�¢
 * ä½œæˆ�å±¥æ­´ï¼š01/04/2019 Khin Myo Wai
 * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�ã€€ä¿�å­˜å‡¦ç�†ã€�å�–å¾—å‡¦ç�†
 * 
 * æ›´æ–°å±¥æ­´ï¼š03/09/2019 Jar Moon Taung
 * æ›´æ–°æ¦‚è¦�ï¼š æ›´æ–°ãƒ¢ãƒ¼ãƒ‰ã�«ç”³è«‹è€…ãƒ‡ãƒ¼ã‚¿ã‚’é‡�è¤‡ãƒ�ã‚§ãƒƒã‚¯å�–å¾—
 */

package mm.com.gic.ces.base.service.cv;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.Experience;
import mm.com.gic.ces.base.common.HibernateUtil;
import mm.com.gic.ces.base.service.admin.RegisterServices;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * ãƒ‡ãƒ¼ã‚¿ãƒ™ãƒ¼ã‚¹ã‚µãƒ¼ãƒ“ã‚¹ã�«ã‚¢ã‚¯ã‚»ã‚¹ã�™ã‚‹
 */
public class CVService {
	Session session = null; //
	Transaction tx = null; //

	/**
	 * å¿œå‹Ÿè€…ãƒ‡ãƒ¼ã‚¿ã‚’ãƒ‡ãƒ¼ã‚¿ãƒ™ãƒ¼ã‚¹ã�«ä¿�å­˜ã�™ã‚‹
	 */
	/*
	 * public void saveApplicant(Applicant applicant) { try{ session =
	 * HibernateUtil.getSessionFactory().openSession(); tx =
	 * session.beginTransaction(); session.save(applicant);
	 * session.getTransaction().commit(); }catch(Exception e){ e.printStackTrace();
	 * }finally{ session.close(); } }
	 */
	/*
	 * public String saveApplicant(Applicant applicant) { Session session = null;
	 * Transaction tx = null;
	 * 
	 * try { session = HibernateUtil.getSessionFactory().openSession(); tx =
	 * session.beginTransaction(); session.save(applicant); tx.commit(); // If
	 * everything is OK, commit return "Success"; } catch (Exception e) { if (tx !=
	 * null) { tx.rollback(); // Rollback if any error } e.printStackTrace(); return
	 * "Fail"; } finally { if (session != null) { session.close(); // Always close
	 * session } } }
	 */

	public String saveApplicant(Applicant applicant) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String examIDCount = RegisterServices.getAndUpdateExamID(session, applicant.getApp_ExamPlace());
			int count = Integer.parseInt(examIDCount);
			String jfexamID = "JF" + year;
			if (applicant.getApp_ExamPlace().equals("Yangon")) {
				jfexamID = jfexamID.concat("Y").concat(examIDCount);
			} else if (applicant.getApp_ExamPlace().equals("Mandalay")) {
				jfexamID = jfexamID.concat("M").concat(examIDCount);
			}
			applicant.setExam_ID(jfexamID);
			session.save(applicant);
			tx.commit(); // If everything is OK, commit
			return "Success " + jfexamID;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // Rollback if any error
			}
			e.printStackTrace();
			return "Fail";
		} finally {
			if (session != null) {
				session.close(); // Always close session
			}
		}
	}

	public String updateApplicant(Applicant updatedApplicant) {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Applicant existingApplicant = (Applicant) session.get(Applicant.class, updatedApplicant.getApp_ID());

			if (existingApplicant != null) {
				existingApplicant.setMail_Check(updatedApplicant.getMail_Check());
				session.update(existingApplicant); // Update command
				tx.commit();
				return "Update successful";
			} else {
				return "Applicant not found";
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "Error while updating";
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * ãƒ¡ãƒ¼ãƒ«ã�¨å€‹äººç•ªå�·ã‚’æ¤œè¨¼ã�™ã‚‹ã�Ÿã‚�ã�«ã�™ã�¹ã�¦ã�®ç”³è«‹è€…ãƒ‡ãƒ¼ã‚¿ã‚’å�–å¾—
	 */
	@SuppressWarnings("unchecked")
	public List<Applicant> getAllApplicant() {

		List<Applicant> lstAllCustomer = new ArrayList<Applicant>();
		try {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("SELECT * FROM tbl_applicant where APP_JF_YEAR='" + year + "';");
			query.addEntity(Applicant.class);
			lstAllCustomer = query.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lstAllCustomer;
	}

	/**
	 * ãƒ¡ãƒ¼ãƒ«ã�¨å€‹äººç•ªå�·ã‚’æ¤œè¨¼ã�™ã‚‹ã�Ÿã‚�ã�«ã�™ã�¹ã�¦ã�®ç”³è«‹è€…ãƒ‡ãƒ¼ã‚¿ã‚’å�–å¾—
	 */
	public String getDuplicateData(String nrc, String email) {
		String result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("SELECT CASE" + " WHEN (DEL_FLAG=0 AND APP_NRC='" + nrc
					+ "' AND APP_EMAIL='" + email + "') THEN 'both duplicate'" + " WHEN (DEL_FLAG=0 AND APP_EMAIL='"
					+ email + "') THEN 'email duplicate'" + " WHEN (DEL_FLAG=0 AND APP_NRC='" + nrc
					+ "') THEN 'nrc duplicate'"
					+ " END AS DUPLICATE_RESULT FROM tbl_applicant WHERE DEL_FLAG=0 AND (APP_NRC='" + nrc
					+ "' OR APP_EMAIL='" + email + "')LIMIT 1;");

			/* query.addEntity(Applicant.class); */
			result = (String) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * æ›´æ–°ãƒ¢ãƒ¼ãƒ‰ã�«ç”³è«‹è€…ãƒ‡ãƒ¼ã‚¿ã‚’é‡�è¤‡ãƒ�ã‚§ãƒƒã‚¯å�–å¾—
	 */

	public String getDuplicateDataUpdate(String nrc, String email, String examID) {

		String result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("SELECT CASE" + " WHEN (DEL_FLAG=0 AND APP_NRC='" + nrc
					+ "' AND APP_EMAIL='" + email + "') THEN 'both duplicate'" + " WHEN (DEL_FLAG=0 AND APP_EMAIL='"
					+ email + "') THEN 'email duplicate'" + " WHEN (DEL_FLAG=0 AND APP_NRC='" + nrc
					+ "') THEN 'nrc duplicate'"
					+ " END AS DUPLICATE_RESULT FROM tbl_applicant WHERE DEL_FLAG=0 AND (APP_NRC='" + nrc
					+ "' OR APP_EMAIL='" + email + "') AND EXAM_ID != '" + examID + "' LIMIT 1;");

			result = (String) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	public void saveExperience(List<Experience> experienceList) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			for (Experience experience : experienceList) {
				session.save(experience);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // Rollback on error
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
