package bootcrm.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import bootcrm.common.ServerResponse;
import bootcrm.entity.Customer;
import bootcrm.entity.Order;
import bootcrm.service.OrderService;
import bootcrm.vo.OrderQueryVO;
import bootcrm.vo.OrderVO;

@Controller
@RequestMapping("order_manage")
public class OrderController {

	private OrderService orderService;

	@RequestMapping("/list_page")
	public String list() {
		return "views/campusbroadband/order_list";
	}
	@RequestMapping("/form_page")
	public String form() {
		return "views/campusbroadband/order_form";
	}
	@RequestMapping("/history_order_page")
	public String historyOrder() {
		return "views/campusbroadband/history_order";
	}
	
	@PostMapping("order")
	@ResponseBody
	public ServerResponse<String> createOrRenewOrder(@Valid Order order) {
		return orderService.createOrder(order);
	}

	@GetMapping("orders")
	@ResponseBody
	public ServerResponse<PageInfo<OrderVO>> listOrder(OrderQueryVO orderQueryVO) {
		return orderService.listOrder(orderQueryVO);
	}

	@GetMapping("orders/{id}")
	@ResponseBody
	public ServerResponse<PageInfo<OrderVO>> getCustomerOrderList(@PathVariable("id") Integer customerId, Integer page,
			Integer limit) {
		return orderService.getCustomerOrderList(customerId, page, limit);
	}

	@GetMapping("order/check_customer/{customerId}")
	@ResponseBody
	public ServerResponse<Customer> checkCustomer(@PathVariable("customerId") Integer customerId) {
		return orderService.checkCustomerForOrder(customerId);
	}

	@GetMapping("order_sum/{id}")
	@ResponseBody
	public ServerResponse<Map<String, BigDecimal>> getSumPayment(@PathVariable("id") Integer customerId) {
		return orderService.getSumPayment(customerId);
	}

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
