package bootcrm.vo;

import java.io.Serializable;
import java.util.Objects;

public class CustomerQueryVO implements Serializable{
	private static final long serialVersionUID = -5130880757174535551L;
	
	private Integer id;
	/**
	 * 关键字：name+industry
	 */
	private String keyword;
	private String level;
	/**
	 * 号码：phone+mobile
	 */
	private String phoneNum;
	
	private Integer page;
	private Integer limit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = Objects.equals(keyword, "") ? null : keyword;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = Objects.equals(level, "") ? null : level;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = Objects.equals(phoneNum, "") ? null : phoneNum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "CustomerQueryVO [id=" + id + ", keyword=" + keyword + ", level=" + level + ", phoneNum=" + phoneNum
				+ ", page=" + page + ", limit=" + limit + "]";
	}
}
