package bootcrm.vo;

import java.io.Serializable;
import java.util.Objects;

public class UserQueryVO implements Serializable {
	
	private static final long serialVersionUID = 338599468108502100L;
	
	private Integer id;
	private String keyword;
	private String type;
	private String status;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = Objects.equals(type, "") ? null : type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = Objects.equals(status, "") ? null : status;
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
		return "UserQueryVO [id=" + id + ", keyword=" + keyword + ", type=" + type + ", status=" + status + ", page="
				+ page + ", limit=" + limit + "]";
	}
}
