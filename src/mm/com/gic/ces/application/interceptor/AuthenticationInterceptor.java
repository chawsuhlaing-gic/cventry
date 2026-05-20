/**
 * 
 * 作成履歴：10/04/2019 Thet Ngon Tun
 * 作成概要：セッションがタイムアウト確認
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */

package mm.com.gic.ces.application.interceptor;

import java.util.Map;
import mm.com.gic.ces.application.controller.admin.LoginAction;
import mm.com.gic.ces.application.controller.cv.CVAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

/***
 *ログイン画面とログアウト画面じゃない場合、 セッションがタイムアウトを確認する
 *
 */
@SuppressWarnings("serial")
public class AuthenticationInterceptor extends AbstractInterceptor implements Interceptor {
	public static boolean isTimeout=false;   //セッション時間
	/**
	 * セッションがタイムアウトを確認する
	 */
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		try {	
		Map<String, Object> mapSession = actionInvocation.getInvocationContext().getSession();	
		Object activeAction = actionInvocation.getAction(); 
		if(mapSession.isEmpty() && (!(activeAction instanceof LoginAction) && !(activeAction instanceof CVAction))){	 
			isTimeout=true;
	        return "sessionTimeout";
	    }
		else if(mapSession.isEmpty() && ((activeAction instanceof LoginAction) || (activeAction instanceof CVAction))){
			mapSession = actionInvocation.getInvocationContext().getSession();	
			return actionInvocation.invoke();
		}else {
			isTimeout=false;
		}
			return actionInvocation.invoke();
		} catch (Exception e) {
			return Action.LOGIN;
		}		
	}

}


