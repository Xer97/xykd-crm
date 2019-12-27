package bootcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import bootcrm.common.ServerResponse;
import bootcrm.entity.Customer;
import bootcrm.service.CustomerService;
import bootcrm.vo.CustomerQueryVO;
import bootcrm.vo.CustomerVO;

@Controller
@RequestMapping("customer_manage")
public class CustomerController {

	private CustomerService customerService;

	@RequestMapping("/home_page")
	public String home() {
		return "views/home";
	}
	@RequestMapping("/list_page")
	public String list() {
		return "views/campusbroadband/customer_list";
	}
	@RequestMapping("/form_page")
	public String toLogin() {
		return "views/campusbroadband/customer_form";
	}
	
	
	
	@GetMapping("/customers")
	@ResponseBody
	public ServerResponse<PageInfo<CustomerVO>> list(CustomerQueryVO customerQueryVO) {
		return customerService.list(customerQueryVO);
	}

	@GetMapping("/customer/{id}")
	@ResponseBody
	public ServerResponse<Customer> get(@PathVariable("id") Integer id) {
		return customerService.getCustomerById(id);
	}
	

	@PostMapping("/customer")
	@ResponseBody
	public ServerResponse<String> add(CustomerVO customerVO) {
		return customerService.insertCustomer(customerVO);
	}

	@PutMapping("/customer")
	@ResponseBody
	public ServerResponse<String> update(CustomerVO customerVO) {
		return customerService.updateCustomer(customerVO);
	}

	@DeleteMapping("/customer/{id}")
	@ResponseBody
	public ServerResponse<String> delete(@PathVariable("id") Integer id) {
		return customerService.deleteCustomer(id);
	}
	
	@DeleteMapping("/customers")
	@ResponseBody
	public ServerResponse<String> batchDelete(@RequestBody Integer[] ids){
		return customerService.batchDeleteCustomer(ids);
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
