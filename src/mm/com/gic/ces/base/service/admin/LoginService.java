/**
 * 
 * 新規作成 :2019/04/10 Thet Ngon Tun
 * 作成概要 ：Login Service
 * 
 * 更新履歴：
 * 更新概要：
 *
 */

package mm.com.gic.ces.base.service.admin;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import mm.com.gic.ces.application.controller.admin.ValidatePwd;
import mm.com.gic.ces.application.model.RoleSetting;
import mm.com.gic.ces.base.common.HibernateUtil;

public class LoginService {
	/***
	 * 入力したを社員番号とパスワードを確認tする
	 * @param ユーザー名
	 * @param パスワード
	 * @return ログインユーザー
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public RoleSetting checkAdminlogin(String username,String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		RoleSetting loggedInAdmin=null;
		if(username.length()<9){
			Session session=HibernateUtil.getSessionFactory().openSession();
			Transaction transaction=null;
			try{
				transaction=session.beginTransaction();
				Query queryResult =session.createQuery("FROM RoleSetting WHERE del_Flag=0 AND role_EmployeeID=:username ");
				queryResult.setParameter("username", Integer.parseInt(username));
				if(queryResult.list().size()>0){
					RoleSetting role=(RoleSetting) queryResult.uniqueResult();
					if(ValidatePwd.validatePassword(password,role.getRole_Password() ))
						loggedInAdmin=role;
				}
				transaction.commit();
			}catch (HibernateException e){
				if(transaction != null)
					transaction.rollback();
				e.printStackTrace();
			}finally{
				session.close();
			}
		}
		return loggedInAdmin;		
	}	
}
