/**
 * CV_A_071_権限設定画面
 * 作成履歴：09/04/2019 Cho Cho Lwin
 * 作成概要：新規作成　権限設定処理
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */

package mm.com.gic.ces.application.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 権限設定情報を取得する
 */
@Entity
@Table(name = "tbl_rolesetting")
public class RoleSetting implements Serializable {

	private static final long serialVersionUID = 1L;
	private int role_ID;               //ID
	private int role_EmployeeID;       //社員番号
	private String role_Name;          //名前
	private String role_Password;      //パスワード
	private String role_Permission;    //権限設定リスト
	private String role_Remark;        //備考
	private int del_Flag;              //削除Flag
	private int role_ex_key;           //更新Key
    private String last_updateUser;    //更新ユーザー
    private Date last_updateTime;      //更新日付

    //更新ユーザー
    @Column(name = "LAST_UPDATE_USER",length=150)
	public String getLast_updateUser() {
		return last_updateUser;
	}

	public void setLast_updateUser(String last_updateUser) {
		this.last_updateUser = last_updateUser;
	}

	//更新日付
	@Column(name = "LAST_UPDATE_TIME")
	public Date getLast_updateTime() {
		return last_updateTime;
	}

	public void setLast_updateTime(Date last_updateTime) {
		this.last_updateTime = last_updateTime;
	}

	//ID
	@Id
	@GeneratedValue
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	public int getRole_ID() {
		return role_ID;
	}

	public void setRole_ID(int role_ID) {
		this.role_ID = role_ID;
	}

    //社員番号
	@Column(name = "EMPLOYEE_ID",length=15)
	public int getRole_EmployeeID() {
		return role_EmployeeID;
	}

	public void setRole_EmployeeID(int role_EmployeeID) {
		this.role_EmployeeID = role_EmployeeID;
	}

	//名前
	@Column(name = "ROLE_NAME",length=150)
	public String getRole_Name() {
		return role_Name;
	}

	public void setRole_Name(String role_Name) {
		this.role_Name = role_Name;
	}

	//パスワード
	@Column(name = "ROLE_PASSWORD",length=1024)
	public String getRole_Password() {
		return role_Password;
	}

	public void setRole_Password(String role_Password) {
		this.role_Password = role_Password;
	}

	//権限設定リスト
	@Column(name = "ROLE_PERMISSION",length=50)
	public String getRole_Permission() {
		return role_Permission;
	}

	public void setRole_Permission(String role_Permission) {
		this.role_Permission = role_Permission;
	}

	//備考
	@Column(name = "ROLE_REMARK",length=255)
	public String getRole_Remark() {
		return role_Remark;
	}

	public void setRole_Remark(String role_Remark) {
		this.role_Remark = role_Remark;
	}

	//削除Flag
	@Column(name = "DEL_FLAG", columnDefinition = "int(1) default 0")
	public int getDel_Flag() {
		return del_Flag;
	}

	public void setDel_Flag(int del_Flag) {
		this.del_Flag = del_Flag;
	}
    
	//更新Key
	@Column(name = "ROLE_EX_KEY")
	public int getRole_ex_key() {
		return role_ex_key;
	}

	public void setRole_ex_key(int role_ex_key) {
		this.role_ex_key = role_ex_key;
	}
}