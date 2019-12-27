package bootcrm.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class User implements Serializable{
	private static final long serialVersionUID = 3446595970450632112L;
	
	public static final String USER_NORMAL_STATUS = "0";
	public static final String USER_DISABLE_STATUS = "1";
	
	public static final String ADMIN_TYPE = "0";
	public static final String USER_TYPE = "1";
	
	private Integer id;
	private String logincode;
	@NotBlank(message="用户名不能为空白！")
	private String username;
	private String password;
	private String type;
	private String status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogincode() {
		return logincode;
	}
	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", logincode=" + logincode + ", username=" + username + ", password=" + password
				+ ", type=" + type + ", status=" + status + "]";
	}
}
