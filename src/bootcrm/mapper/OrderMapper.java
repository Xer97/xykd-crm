package bootcrm.mapper;

import java.math.BigDecimal;
import java.util.List;

import bootcrm.entity.Order;
import bootcrm.vo.OrderQueryVO;

public interface OrderMapper {
	
	List<Order> listWithPerCustomer(OrderQueryVO orderQueryVO);
	
	List<Order> listByCustomerId(Integer customerId);
	
	Order getLastestByCustomerId(Integer customerId);
	
	int insert(Order order);
	
	int countOrderByCustomerId(Integer customerId);
	
	BigDecimal getSumPayment(Integer customerId);

}
