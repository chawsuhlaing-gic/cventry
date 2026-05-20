/**
 *  ç”»é�¢ID_CV_Entry[011]_ç”»é�¢å��_å±¥æ­´æ›¸æƒ…å ±ä¸€è¦§ç”»é�¢		
 *	ä½œæˆ�å±¥æ­´ï¼š2019/01/22 Jar Moon Taung 		
 *	ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�ã€€CV_Entryã€�CV_A_011ã€‘å‡¦ç�†
 * 
 *  æ›´æ–°å±¥æ­´ï¼š10/09/2019 Jar Moon Taung
 *  æ›´æ–°æ¦‚è¦�ï¼šå‰Šé™¤SQLæ–‡ã‚’æ›´æ–°ã�™ã‚‹
 *
 */
package mm.com.gic.ces.base.service.admin;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.ExamID;
import mm.com.gic.ces.base.common.HibernateUtil;

public class RegisterServices extends HibernateUtil {

	private Session session = null;         //ã‚»ãƒƒã‚·ãƒ§ãƒ³
	Transaction tx = null;
	private List<Applicant> applicantList;  //ç”³è«‹è€…ãƒªã‚¹ãƒˆ


	/**
	 *ã€€å�—é¨“ç•ªå�·tbl_applicant.appExam_idã�®ç”Ÿæˆ�å‡¦ç�†
	 *
	 *ã€€@return prefixCount
	 * @throws SQLException 
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("deprecation")
	public String generateExamID(String examPlace) throws SQLException {
		String prefixCount = null;	
		int examCount = 0;
		int count = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if(examPlace.equals("Yangon")){
			examCount = 1;
		} else if (examPlace.equals("Mandalay")){
			examCount = 2;
		}
		ExamID examTesting = (ExamID) session.get(ExamID.class,examCount,LockMode.PESSIMISTIC_WRITE);
		
		if(examTesting != null){
			count = Integer.parseInt(examTesting.getExam_IDCount());
			prefixCount = new DecimalFormat("0000").format(count + 1);
			examTesting.setExam_IDCount(prefixCount);
			session.getTransaction().commit();
			session.close();
		} else {
			session.close();
			prefixCount = initCountData(examPlace);
		}
		return prefixCount;
	}
	
	public static String getAndUpdateExamID(Session session, String examPlace) {
	    int examID = "Yangon".equals(examPlace) ? 1 : 2;
	    int count = 0;
	    String prefixCount = null;

	    // Row lock + FOR UPDATE
	    ExamID exam = (ExamID) session.get(
	        ExamID.class,
	        examID,
	        LockOptions.UPGRADE
	            .setLockMode(LockMode.PESSIMISTIC_WRITE)
	            .setTimeOut(LockOptions.WAIT_FOREVER)
	    );
	    if (exam != null) {
			count = Integer.parseInt(exam.getExam_IDCount());
			prefixCount = new DecimalFormat("0000").format(count + 1);
			exam.setExam_IDCount(prefixCount);
			session.update(exam);
	    }

	    return prefixCount;
	}
	
	@SuppressWarnings("deprecation")
	public String getExamIDCount(String examPlace) throws SQLException {
		String prefixCount = null;	
		int examCount = 0;
		int count = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		//session.beginTransaction();
		if(examPlace.equals("Yangon")){
			examCount = 1;
		} else if (examPlace.equals("Mandalay")){
			examCount = 2;
		}
		ExamID examTesting = (ExamID) session.get(ExamID.class,examCount,LockMode.PESSIMISTIC_WRITE);
		if(examTesting != null){
			count = Integer.parseInt(examTesting.getExam_IDCount());
			prefixCount = new DecimalFormat("0000").format(count+1);
			//examTesting.setExam_IDCount(prefixCount);
			//session.getTransaction().commit();
			session.close();
		} 
		return prefixCount;
	}
	
	
	/**
	 * æœ€åˆ� tbl_examIDã�«è¨­å®šå‡¦ç�†
	 *  
	 * @return prefixCount
	 */	
     public String initCountData(String exam_Place){
		ExamID examID = new ExamID();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String tmpexamID = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "SELECT COUNT(*) FROM Applicant WHERE app_ExamPlace =:examPlace AND app_JFYear=:jfYear";
		Query query = session.createQuery(hql);
		query.setParameter("examPlace", exam_Place);
		query.setParameter("jfYear", year);
		Long i = (Long) query.uniqueResult();
        session.getTransaction().commit();
		session.close();
		
		int appID = new Integer(String.valueOf(i));
		tmpexamID = new DecimalFormat("0000").format(appID+1);

		   if(exam_Place.equals("Yangon")){
			   examID.setExam_ID(1);
			   examID.setExam_IDCount(tmpexamID);	
			   examID.setExam_Place(exam_Place);
			   saveExamID(examID);	
		    					
		   } else if(exam_Place.equals("Mandalay")){
			   examID.setExam_ID(2);
			   examID.setExam_IDCount(tmpexamID);	
			   examID.setExam_Place(exam_Place);
			   saveExamID(examID);	
		   }
		return tmpexamID;
	}
     

 	public void saveExamID(ExamID examID) {
         session = HibernateUtil.getSessionFactory().openSession();
         tx = session.beginTransaction();
         session.save(examID);
         session.getTransaction().commit();
         session.close();
 	}
         
	/**
	 *  tbl_examIDã�«æ›´æ–°å‡¦ç�†
	 *  å�—é¨“ç•ªå�·å‡ºåŠ›
	 */
	public void updateExamIDCount(String exam_Place,String exam_id_count)throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String queryString = "UPDATE ExamID SET exam_IDCount='" + exam_id_count + "' WHERE exam_Place = '" + exam_Place+"'and exam_ID <> 0";	
		Query query = session.createQuery(queryString);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
	}
	
	/**
	 * ç”³è«‹è€…å‰Šé™¤ãƒªã‚¹ãƒˆæ¤œç´¢å‡¦ç�†
	 */
	@SuppressWarnings("unchecked")
	public List<Applicant> searchApplicantList(int jfYear, String examPlace) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		applicantList = new ArrayList<Applicant>();
        Criteria cr = session.createCriteria(Applicant.class)
					.setProjection(Projections.projectionList()
							.add(Projections.property("app_ID"), "app_ID")
                            .add(Projections.property("exam_ID"), "exam_ID")
                            .add(Projections.property("app_Name"), "app_Name")
                            .add(Projections.property("app_Nrc"), "app_Nrc")
                            .add(Projections.property("app_Gender"), "app_Gender")
                            .add(Projections.property("app_DOB"), "app_DOB")
                            .add(Projections.property("app_Education"), "app_Education")
                            .add(Projections.property("app_University"), "app_University")
                            .add(Projections.property("app_CityofUniversity"), "app_CityofUniversity")
                            .add(Projections.property("app_JPSkill"), "app_JPSkill")
                            .add(Projections.property("app_ENGSkill"), "app_ENGSkill")
                            .add(Projections.property("app_JFYear"), "app_JFYear")
                            .add(Projections.property("app_ExamPlace"), "app_ExamPlace")
                            .add(Projections.property("app_RegDate"), "app_RegDate")
                            .add(Projections.property("app_ex_key"), "app_ex_key")
                            .add(Projections.property("del_flag"), "del_flag"))
                           
                        .setResultTransformer(Transformers.aliasToBean(Applicant.class));
                      cr.add(Restrictions.eq("del_flag", 1));
                      cr.add(Restrictions.eq("app_JFYear", jfYear));
                      cr.add(Restrictions.eq("app_ExamPlace", examPlace));
                      applicantList = cr.list();
		
		return applicantList;
	}
}