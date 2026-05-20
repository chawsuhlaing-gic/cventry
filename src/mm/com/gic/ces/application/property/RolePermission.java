/**
 * CV_A_071_権限設定画面
 * 作成履歴：09/04/2019 Cho Cho Lwin
 * 作成概要：新規作成
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */

package mm.com.gic.ces.application.property;
import java.io.Serializable;

/**
 * 権限設定画面
 */
public class RolePermission implements Serializable{

	private static final long serialVersionUID = 1L;
	String rolePermissionID;      //権限設定ID
    String rolePermissionName;    //権限設定画面名
    
    //権限設定ID
	public String getRolePermissionID() {
		return rolePermissionID;
	}

	public void setRolePermissionID(String rolePermissionID) {
		this.rolePermissionID = rolePermissionID;
	}

	//権限設定画面名
	public String getRolePermissionName() {
		return rolePermissionName;
	}

	public void setRolePermissionName(String rolePermissionName) {
		this.rolePermissionName = rolePermissionName;
	}
}
