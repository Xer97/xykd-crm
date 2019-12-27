package bootcrm.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import bootcrm.common.ServerResponse;
import bootcrm.common.SessionUtil;
import bootcrm.entity.Customer;
import bootcrm.entity.Order;
import bootcrm.entity.User;
import bootcrm.mapper.CustomerMapper;
import bootcrm.mapper.OrderMapper;
import bootcrm.mapper.UserMapper;
import bootcrm.service.OrderService;
import bootcrm.vo.OrderQueryVO;
import bootcrm.vo.OrderVO;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderMapper orderMapper;
	private UserMapper userMapper;
	private CustomerMapper customerMapper;

	@Override
	public ServerResponse<String> createOrder(Order order) {
		// 计算缴费金额
		BigDecimal cost = order.getPayType().equals(Customer.MONTH_LEVEL) ? Order.MONTH_COST : Order.YEAR_COST;
		order.setPayment(cost.multiply(new BigDecimal(order.getQuantity())));
		// 计算缴费时间与宽带开始时间
		LocalDateTime nowDateTime = LocalDateTime.now();
		LocalDateTime beginTime;
		Order lastestOrder = orderMapper.getLastestByCustomerId(order.getCustomerId());
		if (Objects.isNull(lastestOrder) || lastestOrder.getExpiryTime().isBefore(nowDateTime)) {
			// 未办理过宽带或宽带已过期,宽带开始时间为现在
			beginTime = nowDateTime;
		} else {
			// 未过期,续费宽带开始时间为最新的到期时间
			beginTime = lastestOrder.getExpiryTime();
		}
		// 计算到期时间
		LocalDateTime expiryTime;
		if (order.getPayType().equals(Customer.MONTH_LEVEL)) {
			expiryTime = beginTime.plusMonths(order.getQuantity());
		} else {
			expiryTime = beginTime.plusYears(order.getQuantity());
		}
		order.setPayTime(nowDateTime);
		order.setExpiryTime(expiryTime);
		order.setOperatorId(SessionUtil.getCurrentUser().getId());
		int rowCount = orderMapper.insert(order);
		if (rowCount < 1) {
			return ServerResponse.createByErrorMessage("操作失败！");
		}
		return ServerResponse.createBySuccessMessage("操作成功！");
	}

	@Override
	public ServerResponse<PageInfo<OrderVO>> listOrder(OrderQueryVO orderQueryVO) {
		PageHelper.startPage(orderQueryVO.getPage(), orderQueryVO.getLimit());
		List<Order> orders = orderMapper.listWithPerCustomer(orderQueryVO);
		PageInfo pageInfo = new PageInfo(orders);
		List<OrderVO> orderVOs = assembleOrderVOs(orders);
		pageInfo.setList(orderVOs);
		return ServerResponse.createBySuccess(pageInfo);
	}

	private List<OrderVO> assembleOrderVOs(List<Order> orders) {
		List<OrderVO> orderVOs = new ArrayList<>();
		orders.forEach(order -> {
			OrderVO orderVO = new OrderVO();
			BeanUtils.copyProperties(order, orderVO);
			orderVO.setPayTime(order.getPayTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			orderVO.setExpiryTime(order.getExpiryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			LocalDateTime now = LocalDateTime.now();
			if (order.getExpiryTime().isBefore(now)) {
				orderVO.setRemark("宽带已过期！");
			} else if (order.getExpiryTime().isBefore(now.plusDays(7))) {
				orderVO.setRemark("宽带即将到期，请及时续费！");
			}
			User operatorUser = userMapper.getByUserId(order.getOperatorId());
			orderVO.setOperator(operatorUser.getUsername());

			orderVOs.add(orderVO);
		});
		return orderVOs;
	}

	@Override
	public ServerResponse<Customer> checkCustomerForOrder(Integer customerId) {
		int customerCount = orderMapper.countOrderByCustomerId(customerId);
		if (customerCount > 0) {
			return ServerResponse.createByErrorMessage("该客户已办理宽带业务！");
		}
		Customer customer = customerMapper.getById(customerId);
		if (Objects.isNull(customer)) {
			return ServerResponse.createByErrorMessage("查询的用户不存在！");
		}
		return ServerResponse.createBySuccess("查询成功！", customer);
	}

	@Override
	public ServerResponse<PageInfo<OrderVO>> getCustomerOrderList(Integer customerId, Integer page, Integer limit) {
		PageHelper.startPage(page, limit);
		List<Order> orders = orderMapper.listByCustomerId(customerId);
		PageInfo pageInfo = new PageInfo(orders);
		List<OrderVO> orderVOs = assembleOrderVOs(orders);
		pageInfo.setList(orderVOs);
		return ServerResponse.createBySuccess(pageInfo);
	}

	@Override
	public ServerResponse<Map<String, BigDecimal>> getSumPayment(Integer customerId) {
		BigDecimal sumPayment = orderMapper.getSumPayment(customerId);
		Map<String, BigDecimal> map = new HashMap<>();
		if (Objects.isNull(sumPayment)) {
			map.put("sumPayment", BigDecimal.ZERO);
		} else {
			map.put("sumPayment", sumPayment);
		}
		return ServerResponse.createBySuccess(map);
	}

	@Autowired
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Autowired
	public void setCustomerMapper(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}
}
