package bootcrm.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import bootcrm.common.ServerResponse;
import bootcrm.common.SessionUtil;
import bootcrm.entity.Customer;
import bootcrm.entity.User;
import bootcrm.mapper.CustomerMapper;
import bootcrm.mapper.UserMapper;
import bootcrm.service.CustomerService;
import bootcrm.vo.CustomerQueryVO;
import bootcrm.vo.CustomerVO;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerMapper customerMapper;
	private UserMapper userMapper;
	
	@Override
	public ServerResponse<Customer> getCustomerById(Integer id) {
		Customer customer = customerMapper.getById(id);
		if (Objects.isNull(customer)) {
			ServerResponse.createByErrorMessage("目标客户不存在！");
		}
		return ServerResponse.createBySuccess(customer);
	}
	

	@Override
	public ServerResponse<Customer> getCustomerByName(String name) {
		Customer customer = customerMapper.getByName(name);
		if (Objects.isNull(customer)) {
			ServerResponse.createByErrorMessage("目标客户不存在！");
		}
		return ServerResponse.createBySuccess("查询成功！",customer);
	}
	

	@Override
	public ServerResponse<String> insertCustomer(CustomerVO customerVO) {
		User currentUser = SessionUtil.getCurrentUser();
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerVO, customer);
		customer.setCreateTime(LocalDateTime.now());
		// 获取当前登录用户，设置createId和userId
		Integer currentUserId = currentUser.getId();
		customer.setUserId(currentUserId);
		customer.setCreateId(currentUserId);
		int rowCount = customerMapper.insert(customer);
		if (rowCount < 1) {
			return ServerResponse.createByErrorMessage("新增客户失败！");
		}
		return ServerResponse.createBySuccessMessage("添加新客户成功！");
	}

	@Override
	public ServerResponse<String> updateCustomer(CustomerVO customerVO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerVO, customer);
		int rowCount = customerMapper.update(customer);
		if (rowCount < 1) {
			return ServerResponse.createByErrorMessage("修改客户信息失败！");
		}
		return ServerResponse.createBySuccessMessage("更新客户信息成功！");
	}

	@Override
	public ServerResponse<String> deleteCustomer(Integer id) {
		int rowCount = customerMapper.delete(id);
		if (rowCount < 1) {
			return ServerResponse.createByErrorMessage("删除客户失败！");
		}
		return ServerResponse.createBySuccessMessage("成功删除客户！");
	}
	
	@Override
	public ServerResponse<PageInfo<CustomerVO>> list(CustomerQueryVO customerQueryVO) {
		PageHelper.startPage(customerQueryVO.getPage(), customerQueryVO.getLimit());
		List<Customer> list = customerMapper.list(customerQueryVO);
		PageInfo pageInfo = new PageInfo(list);
		List<CustomerVO> customerVOs = assembleCustomerVOs(list);
		pageInfo.setList(customerVOs);
		return ServerResponse.createBySuccess(pageInfo);
	}
	
	private List<CustomerVO> assembleCustomerVOs(List<Customer> customers) {
		List<CustomerVO> list = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerVO customerVO = new CustomerVO();
			BeanUtils.copyProperties(customer, customerVO);
			User creator = userMapper.getByUserId(customer.getCreateId());
			customerVO.setCreator(creator.getUsername());
			customerVO.setCreateTime(customer.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			list.add(customerVO);
		}
		return list;
	}
	
	@Override
	public ServerResponse<String> batchDeleteCustomer(Integer[] ids) {
		int rowCount = customerMapper.batchDelete(ids);
		if(rowCount != ids.length) {
			return ServerResponse.createByErrorMessage("批量删除未完成！");
		}
		return ServerResponse.createBySuccessMessage("批量删除成功！");
	}

	@Autowired
	public void setCustomerMapper(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

}
