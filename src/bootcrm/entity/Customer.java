package bootcrm.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Customer implements Serializable{
	private static final long serialVersionUID = -9024811444976115913L;
	
	public static final String MONTH_LEVEL = "0";
	public static final String YEAR_LEVEL = "1";
	
	private Integer id;
	private String name;
	private Integer userId;
	private Integer createId;
	private String source;
	private String industry;
	private String level;
	private String linkman;
	private String phone;
	private String mobile;
	private String zipcode;
	private String address;
	private LocalDateTime createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", userId=" + userId + ", createId=" + createId + ", source="
				+ source + ", industry=" + industry + ", level=" + level + ", linkman=" + linkman + ", phone=" + phone
				+ ", mobile=" + mobile + ", zipcode=" + zipcode + ", address=" + address + ", createTime=" + createTime
				+ "]";
	}
}
