/**
 * CV_A_071_権限設定画面
 * 作成履歴：09/04/2019 Cho Cho Lwin
 * 作成概要：新規作成　権限設定処理
 * 
 * 更新履歴：06/05/2019 Cho Cho Lwin
 * 更新概要：削除して再登録処理を追加し、メッセージを修正する
 */

package mm.com.gic.ces.application.controller.admin;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.RoleSetting;
import mm.com.gic.ces.application.property.RolePermission;
import mm.com.gic.ces.base.service.admin.RoleSettingService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 権限設定情報を保存し、更新し、削除する
 */
public class RoleSettingAction extends ActionSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	Map<String, Object> mapSession = ActionContext.getContext().getSession();
	HttpServletRequest request = ServletActionContext.getRequest();                    //サーブレットリクエスト
	HttpSession httpSession = request.getSession();                                    //セッション
	private List<RolePermission> lstRolePermission = new ArrayList<RolePermission>();    //権限設定画面名リスト
	private List<String> selectedCheckBoxList = new ArrayList<String>();               //選択されたチェックボックスリスト
	private RoleSetting roleSetting = new RoleSetting();                               //権限設定オブジェクト
	private RoleSettingService roleSettingService = new RoleSettingService();          //権限設定サービス
	private List<RoleSetting> lstRoleSetting = new ArrayList<RoleSetting>();           //権限設定リスト
	private CommonUtility commonUtility = new CommonUtility();
	private String roleID;                                                             //ID
	private int role_EmployeeID;                                                       //社員番号
	private String role_Name;                                                          //名前
	private String role_Password;                                                      //パスワード
	private String confirmPassword;                                                    //パスワード確認
	private String role_Remark;                                                        //備考
	private String role_Permission;                                                    //権限設定画面名
    private int role_ex_key;                                                           //更新Key
    private String btn;                                                                //ボタン名

    /**
	 * 作成履歴：09/04/2019 Cho Cho Lwin
     * 作成概要：新規作成
     * 
     * 更新履歴：06/05/2019 Cho Cho Lwin
     * 更新概要：SQL Queryの後にORDER BYを追加する
     *
	 * ...権限設定情報を取得する...
	 * @return success
	 * @throws IOException 
	 */
	public String getRoleSettingListToShow() throws IOException {
		lstRolePermission = CommonUtility.getRolePermissionChk();
		String rolePermission = "履歴書管理";
		if (rolePermission != null) {
			selectedCheckBoxList = this.getSelectedList(rolePermission);
		}
		lstRoleSetting = roleSettingService
				.getRoleSetting("FROM RoleSetting role WHERE role.del_Flag = 0 "
						+ "ORDER BY role_EmployeeID");
		String errMsg=(String) httpSession.getAttribute("errMsg");
		String successMsg=(String) httpSession.getAttribute("successMsg");
		if(errMsg!=null){
			addActionError(errMsg);
		}
		if(successMsg!=null){
			addActionMessage(successMsg);
		}
		httpSession.setAttribute("errMsg", null);
		httpSession.setAttribute("successMsg", null);
		return "success";
	}

	/**
	 * 作成履歴：09/04/2019 Cho Cho Lwin
     * 作成概要：新規作成
     * 
     * 更新履歴：06/05/2019 Cho Cho Lwin
     * 更新概要：削除して再登録処理を追加し、メッセージを修正する
     *
	 * ...権限設定情報を保存し、更新する...
	 * @return success
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public String saveRoleSetting() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		@SuppressWarnings("static-access")
	    Properties prop = commonUtility.getValue("Message.properties");
		String isDuplicate = "";
		int count = 0;
		boolean duplicate = false;
        if(!getRole_Permission().contains("履歴書管理")){
        	httpSession.setAttribute("successMsg", null);
            httpSession.setAttribute("errMsg", prop.getProperty(Integer.toString(25)));
            return "error";
        } else {
        	httpSession.setAttribute("errMsg", null);
        	lstRoleSetting = roleSettingService.getRoleSetting("FROM RoleSetting role");
        	for (RoleSetting role : lstRoleSetting) {
        		if (role.getRole_EmployeeID() == getRole_EmployeeID()
        				&& roleSetting.getRole_ID() != role.getRole_ID()
        				&& role.getDel_Flag() == 1){
        			isDuplicate = "doubleInsert";
        			break;
        		}
        		if (role.getRole_EmployeeID() == getRole_EmployeeID()
        				&& roleSetting.getRole_ID() != role.getRole_ID()){
        			duplicate = true;
        			break;
        		} else if (role.getRole_EmployeeID() == getRole_EmployeeID()
        				&& roleSetting.getRole_ID() == role.getRole_ID()){
        			duplicate = false;
        			break;
        		} else {
        			count++;
        		}
            }
        	if(count == lstRoleSetting.size()) {
        		if (getRole_Password().equals(getConfirmPassword())) {
					RoleSetting obj_RoleSetting = (RoleSetting) mapSession.get("loggedInadmin");
                	String generatedSecuredPasswordHash = ValidatePwd.generateStorngPasswordHash(getRole_Password());
                    roleSetting = this.setRoleSettingData(roleSetting);
                    roleSetting.setRole_EmployeeID(getRole_EmployeeID());
                    roleSetting.setRole_Password(generatedSecuredPasswordHash);
                    roleSetting.setRole_ex_key(1);
                    roleSetting.setLast_updateUser(obj_RoleSetting.getRole_Name());
                    roleSetting.setLast_updateTime(new Date());
                    roleSettingService.saveRoleSetting(roleSetting);
                    roleSetting = new RoleSetting();
                    httpSession.setAttribute("errMsg",null);
                    httpSession.setAttribute("successMsg", prop.getProperty(Integer.toString(7)));
                    return "success";
                }
        	}
        	if(isDuplicate.equals("doubleInsert")) {
        		roleSetting = roleSettingService.getRoleSettingByEmpID(getRole_EmployeeID());
                if (roleSetting != null) {
                    RoleSetting obj_RoleSetting = (RoleSetting) mapSession.get("loggedInadmin");
                    roleSetting = this.setRoleSettingData(roleSetting);
                    roleSetting.setRole_ex_key(roleSetting.getRole_ex_key()+1);
                    roleSetting.setLast_updateUser(obj_RoleSetting.getRole_Name());
                    roleSetting.setLast_updateTime(new Date());
                    roleSettingService.updateRoleSetting(roleSetting);
                    roleSetting.setRole_ID(0);
                    httpSession.setAttribute("errMsg",null);
                    httpSession.setAttribute("successMsg", prop.getProperty(Integer.toString(7)));
                    return "success";
                }
        	}
        	if(duplicate == false) {
                roleSetting = roleSettingService.getRoleSettingByID(roleSetting.getRole_ID());
                if (roleSetting != null) {
                    if(roleSetting.getRole_ex_key() == getRole_ex_key()){
                    	RoleSetting obj_RoleSetting = (RoleSetting) mapSession.get("loggedInadmin");
                        roleSetting = this.setRoleSettingData(roleSetting);
                        roleSetting.setRole_ex_key(getRole_ex_key()+1);
                        roleSetting.setLast_updateUser(obj_RoleSetting.getRole_Name());
                        roleSetting.setLast_updateTime(new Date());
                        roleSettingService.updateRoleSetting(roleSetting);
                        roleSetting.setRole_ID(0);
                        httpSession.setAttribute("errMsg",null);
                        httpSession.setAttribute("successMsg", prop.getProperty(Integer.toString(1)));
                        return "success";
                    }
                    else {
                        httpSession.setAttribute("successMsg", null);
                        httpSession.setAttribute("errMsg", prop.getProperty(Integer.toString(21)));
                        return "error";
                    }
                } else {
                    roleSetting = new RoleSetting();
                    roleSetting.setRole_ID(0);
                    httpSession.setAttribute("successMsg", null);
                    httpSession.setAttribute("errMsg", prop.getProperty(Integer.toString(20)));
                    return "error";
                }
        	} else {
        		httpSession.setAttribute("successMsg", null);
                httpSession.setAttribute("errMsg", prop.getProperty(Integer.toString(22)));
                return "error";
        	}
        }
    }
	
    /**
     * 作成履歴：06/05/2019 Cho Cho Lwin
     * 作成概要：新規作成
     * 
     * ...権限設定情報を設定する...
     * @param roleSetting
     * @return roleSetting
     */
	public RoleSetting setRoleSettingData(RoleSetting roleSetting){
		roleSetting.setRole_Name(getRole_Name());
        roleSetting.setRole_Remark(getRole_Remark());
        roleSetting.setRole_Permission(getRole_Permission());
        roleSetting.setDel_Flag(0);
        return roleSetting;
	}

	/**
	 * 作成履歴：09/04/2019 Cho Cho Lwin
     * 作成概要：新規作成
     *
	 * ...入力フィールドをクリアする...
	 */
	public void clearTextBox() {
		setRole_Name("");
		setRole_Remark("");
	}

	/**
	 * 作成履歴：09/04/2019 Cho Cho Lwin
     * 作成概要：新規作成
     * 
     * 更新履歴：06/05/2019 Cho Cho Lwin
     * 更新概要：メッセージを修正する
     *
	 * ...権限設定情報を編集する...
	 * @return success
	 * @throws IOException
	 */
	public String editRoleSetting() throws IOException {
		@SuppressWarnings("static-access")
	    Properties prop = commonUtility.getValue("Message.properties");
		roleSetting = roleSettingService.getRoleSettingByID(roleSetting.getRole_ID());
		if (roleSetting == null) {
			roleSetting = new RoleSetting();
			roleSetting.setRole_ID(0);
			getRoleSettingListToShow();
			httpSession.setAttribute("successMsg", null);
			httpSession.setAttribute("errMsg", prop.getProperty(Integer.toString(20)));
			return "error";
		} else {
			String rolePermission = roleSetting.getRole_Permission();
			if (rolePermission != null) {
				selectedCheckBoxList = getSelectedList(rolePermission);
			}
		}
		lstRoleSetting = roleSettingService
				.getRoleSetting("FROM RoleSetting role WHERE role.del_Flag=0 ORDER BY role_EmployeeID");
		lstRolePermission = CommonUtility.getRolePermissionChk();
		return "success";
	}

	/**
	 * 作成履歴：09/04/2019 Cho Cho Lwin
     * 作成概要：新規作成
     * 
     * 更新履歴：06/05/2019 Cho Cho Lwin
     * 更新概要：メッセージを修正する
     *
	 * ...権限設定情報を削除する...
	 * @return success
	 * @throws IOException
	 */
	public String deleteRoleSetting() throws IOException {
		@SuppressWarnings("static-access")
	    Properties prop = commonUtility.getValue("Message.properties");
		roleSetting = roleSettingService.getRoleSettingByID(roleSetting.getRole_ID());
		if (roleSetting == null) {
			roleSetting = new RoleSetting();
			roleSetting.setRole_ID(0);
			httpSession.setAttribute("successMsg", null);
			httpSession.setAttribute("errMsg", prop.getProperty(Integer.toString(23)));
		} else {
			if(roleSetting.getRole_ex_key() == getRole_ex_key()){
				RoleSetting obj_RoleSetting = (RoleSetting) mapSession.get("loggedInadmin");
				roleSetting.setLast_updateUser(obj_RoleSetting.getRole_Name());
                roleSetting.setLast_updateTime(new Date());
				roleSetting.setDel_Flag(1);
				roleSetting.setRole_ex_key(getRole_ex_key()+1);
				roleSettingService.updateRoleSetting(roleSetting);
				httpSession.setAttribute("errMsg",null);
				httpSession.setAttribute("successMsg", prop.getProperty(Integer.toString(8)));
            } else {
            	httpSession.setAttribute("successMsg", null);
    			httpSession.setAttribute("errMsg", prop.getProperty(Integer.toString(24)));
            }
		}
		return "success";
	}

	/**
	 * 作成履歴：09/04/2019 Cho Cho Lwin
     * 作成概要：新規作成
     *
	 * ...チェックされた権限設定画面名を取得する...
	 * @param rolePermission
	 * @return selectedChkBoxList チェックされた権限設定画面名リスト
	 */
	public List<String> getSelectedList(String rolePermission) {
		List<String> selectedChkBoxList = new ArrayList<String>();
		String[] arrStr = rolePermission.split("\\s*,\\s*");
		for (String str : arrStr) {
			selectedChkBoxList.add(str);
		}
		return selectedChkBoxList;
	}

    //ボタン名
    public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}

	//更新Key
    public int getRole_ex_key() {
		return role_ex_key;
	}

	public void setRole_ex_key(int role_ex_key) {
		this.role_ex_key = role_ex_key;
	}

	//社員番号
	public int getRole_EmployeeID() {
		return role_EmployeeID;
	}

	public void setRole_EmployeeID(int role_EmployeeID) {
		this.role_EmployeeID = role_EmployeeID;
	}

	 //名前
	public String getRole_Name() {
		return role_Name;
	}

	public void setRole_Name(String role_Name) {
		this.role_Name = role_Name;
	}

    //パスワード
	public String getRole_Password() {
		return role_Password;
	}

	public void setRole_Password(String role_Password) {
		this.role_Password = role_Password;
	}

    //備考
	public String getRole_Remark() {
		return role_Remark;
	}

	public void setRole_Remark(String role_Remark) {
		this.role_Remark = role_Remark;
	}
	
    //権限設定画面名リスト
	public String getRole_Permission() {
		return role_Permission;
	}

	public void setRole_Permission(String role_Permission) {
		this.role_Permission = role_Permission;
	}

    //選択されたチェックボックスリスト
	public List<String> getSelectedCheckBoxList() {
		return selectedCheckBoxList;
	}

	public void setSelectedCheckBoxList(List<String> selectedCheckBoxList) {
		this.selectedCheckBoxList = selectedCheckBoxList;
	}

    //ID
	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

    //権限設定リスト
	public List<RoleSetting> getLstRoleSetting() {
		return lstRoleSetting;
	}

	public void setLstRoleSetting(List<RoleSetting> lstRoleSetting) {
		this.lstRoleSetting = lstRoleSetting;
	}

    //パスワード確認
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

    //権限設定オブジェクト
	public RoleSetting getRoleSetting() {
		return roleSetting;
	}

	public void setRoleSetting(RoleSetting roleSetting) {
		this.roleSetting = roleSetting;
	}

    //権限設定画面
	public List<RolePermission> getLstRolePermission() {
		return lstRolePermission;
	}

	public void setLstRolePermission(List<RolePermission> lstRolePermission) {
		this.lstRolePermission = lstRolePermission;
	}
}
