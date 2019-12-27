package bootcrm.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Order implements Serializable{
	private static final long serialVersionUID = 721214989225461650L;
	
	public static final BigDecimal MONTH_COST = new BigDecimal("39.00");
	public static final BigDecimal YEAR_COST = new BigDecimal("429.00");
	
	private Integer id;
	@NotNull(message="请求参数有误！")
	private Integer customerId;
	@NotBlank(message="客户名不可空白！")
	private String customerName;
	private String payType;
	@NotNull(message="请求参数有误！")
	private Integer quantity;
	private BigDecimal payment;
	private LocalDateTime payTime;
	private LocalDateTime expiryTime;
	private Integer operatorId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
		this.customerName = customerName;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	
	public LocalDateTime getPayTime() {
		return payTime;
	}
	public void setPayTime(LocalDateTime payTime) {
		this.payTime = payTime;
	}
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", customerName=" + customerName + ", payType="
				+ payType + ", quantity=" + quantity + ", payment=" + payment + ", payTime=" + payTime + ", expiryTime="
				+ expiryTime + ", operatorId=" + operatorId + "]";
	}
}