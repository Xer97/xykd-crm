package bootcrm.controller;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
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
import bootcrm.entity.User;
import bootcrm.service.UserService;
import bootcrm.vo.UserQueryVO;

@Controller
@RequestMapping("/user_manage")
public class UserController {

	private UserService userService;

	@RequestMapping("/home_page")
	public String home() {
		return "views/home_admin";
	}

	@RequestMapping("/list_page")
	public String list() {
		return "views/user/list";
	}

	@RequestMapping("/userform_page")
	public String toLogin() {
		return "views/user/userform";
	}

	@GetMapping("/users")
	@ResponseBody
	public ServerResponse<PageInfo<User>> list(UserQueryVO userQueryVO) {
		return userService.listUser(userQueryVO);
	}

	@GetMapping("/user/{id}")
	@ResponseBody
	public ServerResponse<User> get(@PathVariable("id") Integer id) {
		return userService.getUserById(id);
	}

	@PostMapping("/user")
	@ResponseBody
	public ServerResponse<String> add(User user) {
		return userService.insertUser(user);
	}

	@PutMapping("/user")
	@ResponseBody
	public ServerResponse<User> update(User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping("/user/{id}")
	@ResponseBody
	public ServerResponse<String> delete(@PathVariable("id") Integer id) {
		return userService.deleteUser(id);
	}

	@DeleteMapping("/users")
	@ResponseBody
	public ServerResponse<String> batchDelete(@RequestBody Integer[] ids) {
		if (Objects.isNull(ids) || ids.length == 0) {
			return ServerResponse.createByErrorMessage("请求参数有误！");
		}
		return userService.batchDeleteUser(ids);
	}

	@PutMapping("/user/{id}")
	@ResponseBody
	public ServerResponse<String> disableOrEnableUser(@PathVariable("id") Integer id, String status) {
		if (Objects.isNull(id) || StringUtils.isBlank(status)) {
			return ServerResponse.createByErrorMessage("请求参数有误！");
		}
		return userService.disableOrEnableUser(id, status);
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
