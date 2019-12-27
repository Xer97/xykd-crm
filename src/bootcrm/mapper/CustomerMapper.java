package bootcrm.mapper;

import java.util.List;

import bootcrm.entity.Customer;
import bootcrm.vo.CustomerQueryVO;

public interface CustomerMapper {

	Customer getById(Integer id);
	
	Customer getByName(String name);
	
	List<Customer> list(CustomerQueryVO customerQueryVO);
	
	int insert(Customer customer);
	
	int update(Customer customer);
	
	int delete(Integer id);
	
	int batchDelete(Integer[] ids);
	
}
