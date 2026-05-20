/**
 * CV_A_013_受験番号出力画面
 * 作成履歴：01/04/2019 Cho Cho Lwin
 * 作成概要：新規作成　受験番号出力処理
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */

package mm.com.gic.ces.base.service.admin;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.base.common.HibernateUtil;

/**
 *データベースにある申請者情報を取る
 */
public class ExamCardService {

	Session session =null;    //セッション

	/**
	 * ...申請者情報をを取る...
	 * @return lstApplicant 申請者のリスト
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<Applicant> selectApplicantList() throws SQLException {
		List<Applicant> lstApplicant = new ArrayList<Applicant>();
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Applicant WHERE del_flag=0");
		lstApplicant = q.list();
		session.getTransaction().commit();
		session.close();
		return lstApplicant;
	}

	/**
	 * ...申請者IDによって申請者情報を取る ...
	 * @param appID 申請者ID
	 * @return　applicant 申請者情報
	 * @throws SQLException
	 */
	public Applicant getApplicant(int appID) throws SQLException {
		Applicant applicant=new Applicant();
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Applicant p WHERE p.del_flag=0 AND p.app_ID=:appID");
		q.setParameter("appID", appID);
		applicant=(Applicant)q.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return applicant;
	}
}
