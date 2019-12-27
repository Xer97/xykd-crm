package bootcrm.vo;

import java.util.Objects;

public class OrderQueryVO {

	private Integer customerId;
	private String customerName;
	private String payType;
	
	private Integer page;
	private Integer limit;
	
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = Objects.equals(customerName, "") ? null : customerName;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = Objects.equals(payType, "") ? null : payType;
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
		return "OrderQueryVO [customerId=" + customerId + ", customerName=" + customerName + ", payType=" + payType
				+ ", page=" + page + ", limit=" + limit + "]";
	}
}
