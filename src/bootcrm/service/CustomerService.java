package bootcrm.service;

import com.github.pagehelper.PageInfo;

import bootcrm.common.ServerResponse;
import bootcrm.entity.Customer;
import bootcrm.vo.CustomerQueryVO;
import bootcrm.vo.CustomerVO;

public interface CustomerService {

	ServerResponse<PageInfo<CustomerVO>> list(CustomerQueryVO customerQueryVO);
	
	ServerResponse<Customer> getCustomerById(Integer id);
	
	ServerResponse<Customer> getCustomerByName(String name);

	ServerResponse<String> insertCustomer(CustomerVO customerVO);
	
	ServerResponse<String> updateCustomer(CustomerVO customerVO);
	
	ServerResponse<String> deleteCustomer(Integer id);
	
	ServerResponse<String> batchDeleteCustomer(Integer[] ids);
	
}
