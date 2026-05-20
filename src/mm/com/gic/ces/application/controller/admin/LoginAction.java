/**
 * 
 * 作成履歴：10/04/2019 Thet Ngon Tun
 * 作成概要：フォーム初期化処理 ,ログインボタン処理 
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */

package mm.com.gic.ces.application.controller.admin;
import java.util.Map;
import java.util.Properties;

import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.interceptor.AuthenticationInterceptor;
import mm.com.gic.ces.application.model.RoleSetting;
import mm.com.gic.ces.base.service.admin.LoginService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
	private RoleSetting loggedInadmin;  			//ログインユーザー
	private String username;						//ユーザー名
	private String password;						//パスワード	
	static Map<String, Object> mapSession;			//セッション
	AuthenticationInterceptor auth;					//インターセプター
	CommonUtility comUtility= new CommonUtility();

	/**
	 * 入力したを社員番号とパスワードを確認
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String AdminLogin() throws Exception {
		
		String status="";
		LoginService adminService = new LoginService();
		mapSession = ActionContext.getContext().getSession();
		auth=new AuthenticationInterceptor();
		logout();
		auth.isTimeout=false;	
	
		if (adminService != null) {			
			loggedInadmin = adminService.checkAdminlogin(username,
					password);			
			if (loggedInadmin != null) {
				mapSession.put("loggedInadmin", loggedInadmin);
				mapSession.put("errorMessage",null);
				status="success";
			}
			else{
				Properties prop = comUtility.getValue("Message.properties");
				String errorMsg = prop.getProperty(Integer.toString(14));
				mapSession.remove("errorMessage");
				mapSession.put("errorMessage",errorMsg);
				status="failure";
			}		
		}
		return status;
	}
	
	/**
	 * 画面を フォーム初期化に設定する
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String Login() throws Exception{		

		mapSession = ActionContext.getContext().getSession();
		auth=new AuthenticationInterceptor();
		if(auth.isTimeout==true ){
			Properties prop = comUtility.getValue("Message.properties");
			String errorMsg = prop.getProperty(Integer.toString(13));
			addActionError(errorMsg);
			
		}else{
			if(mapSession.get("errorMessage")!=null || !(mapSession.get("errorMessage")!="")){
				String errorMessage =(String) mapSession.get("errorMessage");
				addActionError(errorMessage);
				mapSession.put("errorMessage",null);
			}		
			loggedInadmin=(RoleSetting) mapSession.get("loggedInadmin");
			if(loggedInadmin!=null){
				mapSession.put("loggedInadmin", null);
				mapSession.remove("loggedInadmin");
			}
		}
		return "login";
	}

	/**
	 * ログアウトしてセッションを削除する
	 * @return success
	 * @throws Exception
	 */
	public String logout() throws Exception {
		mapSession = ActionContext.getContext().getSession();
		mapSession.put("loggedInadmin", null);
		mapSession.remove("loggedInadmin");
		mapSession.put("errorMessage",null);
		return "logout";
	}
	
	
	//ログインユーザー
	public RoleSetting getLoggedInadmin() {
		return loggedInadmin;
	}

	public void setLoggedInadmin(RoleSetting loggedInadmin) {
		this.loggedInadmin = loggedInadmin;
	}

	//ユーザー名
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//パスワード
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
