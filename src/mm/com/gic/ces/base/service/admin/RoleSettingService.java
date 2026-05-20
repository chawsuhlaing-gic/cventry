/**
 * CV_A_071_権限設定画面
 * 作成履歴：09/04/2019 Cho Cho Lwin
 * 作成概要：新規作成　権限設定処理
 * 
 * 更新履歴：06/05/2019 Cho Cho Lwin
 * 更新概要：社員番号によって権限設定情報を取得する	処理を追加する
 */

package mm.com.gic.ces.base.service.admin;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mm.com.gic.ces.application.model.RoleSetting;
import mm.com.gic.ces.base.common.HibernateUtil;

/**
 * 権限設定サービスをアクセスする
 */
public class RoleSettingService {
	
	Session session = null;
	Transaction tx = null;
	
	/**
	 * ...権限設定情報を保存する...
	 * @param roleSetting 権限設定オブジェクト
	 */
	public void saveRoleSetting(RoleSetting roleSetting) {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(roleSetting);
        session.getTransaction().commit();
        session.close();
	}

	/**
	 *  ...権限設定情報を取得する...
	 * @param sqlQuery Query文
	 * @return lstRoleSetting 権限設定リスト
	 */
	@SuppressWarnings("unchecked")
	public List<RoleSetting> getRoleSetting(String sqlQuery){
        session = HibernateUtil.getSessionFactory().openSession();
        List<RoleSetting> lstRoleSetting = new ArrayList<RoleSetting>();
        session.beginTransaction();
        Query q = session.createQuery(sqlQuery);
        q.setLockMode("role", LockMode.PESSIMISTIC_WRITE);
        lstRoleSetting = q.list();
        session.getTransaction().commit();
        session.close();
        return lstRoleSetting;
	}

	/**
	 * ...RoleIDによって権限設定情報を取得する...
	 * @param roleID ID
	 * @return roleSetting 権限設定オブジェクト
	 */
	public RoleSetting getRoleSettingByID(int roleID){
        session = HibernateUtil.getSessionFactory().openSession();
        RoleSetting roleSetting = new RoleSetting();
        session.beginTransaction();
        Query q = session.createQuery("FROM RoleSetting role WHERE role.del_Flag=0 AND role.role_ID=:roleID");
        q.setParameter("roleID", roleID);
        q.setLockMode("role", LockMode.PESSIMISTIC_WRITE);
        roleSetting = (RoleSetting) q.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return roleSetting;
	}
	
	/**
	 * ...社員番号によって権限設定情報を取得する...
	 * @param roleID ID
	 * @return roleSetting 権限設定オブジェクト
	 */
	public RoleSetting getRoleSettingByEmpID(int employeeID){
        session = HibernateUtil.getSessionFactory().openSession();
        RoleSetting roleSetting = new RoleSetting();
        session.beginTransaction();
        Query q = session.createQuery("FROM RoleSetting role WHERE role.del_Flag = 1 AND role.role_EmployeeID=:employeeID");
        q.setParameter("employeeID", employeeID);
        roleSetting = (RoleSetting) q.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return roleSetting;
	}

	/**
	 * ...権限設定情報を更新する...
	 * @param roleSetting 権限設定オブジェクト
	 */
	public void updateRoleSetting(RoleSetting roleSetting){
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(roleSetting);
		session.getTransaction().commit();
		session.close();
	}
}