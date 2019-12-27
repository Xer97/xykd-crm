package bootcrm.service;

import java.math.BigDecimal;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import bootcrm.common.ServerResponse;
import bootcrm.entity.Customer;
import bootcrm.entity.Order;
import bootcrm.vo.OrderQueryVO;
import bootcrm.vo.OrderVO;

public interface OrderService {

	ServerResponse<String> createOrder(Order order);

	ServerResponse<PageInfo<OrderVO>> listOrder(OrderQueryVO orderQueryVO);

	ServerResponse<PageInfo<OrderVO>> getCustomerOrderList(Integer customerId, Integer page, Integer limit);

	ServerResponse<Map<String, BigDecimal>> getSumPayment(Integer customerId);

	ServerResponse<Customer> checkCustomerForOrder(Integer customerId);

}
