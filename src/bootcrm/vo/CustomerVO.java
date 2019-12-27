package bootcrm.vo;

import java.io.Serializable;

public class CustomerVO implements Serializable{
	private static final long serialVersionUID = 4632374767384035363L;
	
	private Integer id;
	private String name;
	private String industry;
	private String level;
	private String phone;
	private String mobile;
	private String zipcode;
	private String address;
	private String creator;
	private String createTime;
	
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "CustomerVO [id=" + id + ", name=" + name + ", industry=" + industry + ", level=" + level + ", phone="
				+ phone + ", mobile=" + mobile + ", zipcode=" + zipcode + ", address=" + address + ", creator="
				+ creator + ", createTime=" + createTime + "]";
	}
}
