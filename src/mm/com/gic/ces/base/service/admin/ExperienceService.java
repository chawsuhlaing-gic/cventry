package mm.com.gic.ces.base.service.admin;

import org.hibernate.Session;
import mm.com.gic.ces.base.common.HibernateUtil;
import org.hibernate.Query;

public class ExperienceService {
	public void deleteByExperienceId(String examId) {
	    Session session = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();

	        Query query = session.createQuery("DELETE FROM Experience WHERE exam_Id = :examId");
	        query.setParameter("examId", examId);
	        int deletedCount = query.executeUpdate();

	        session.getTransaction().commit();
	        System.out.println("Deleted rows: " + deletedCount);
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
}
