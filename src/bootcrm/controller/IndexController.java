package bootcrm.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bootcrm.common.ServerResponse;
import bootcrm.common.SessionUtil;
import bootcrm.entity.User;
import bootcrm.service.UserService;

@Controller
public class IndexController {

	private UserService userService;

	@RequestMapping("/console_page")
	public String toLogin() {
		return "views/home/console";
	}

	@RequestMapping("/info_page")
	public String info() {
		return "views/user/info";
	}

	@RequestMapping("/password_page")
	public String password() {
		return "views/user/password";
	}

	@GetMapping("/user/self")
	@ResponseBody
	public ServerResponse<User> getSelf() {
		return userService.getUserById(SessionUtil.getCurrentUser().getId());
	}

	@PutMapping("/user/self")
	@ResponseBody
	public ServerResponse<User> updateSelf(@Validated User user) {
		user.setId(SessionUtil.getCurrentUser().getId());
		return userService.updateUser(user);
	}

	@PutMapping("/user/password")
	@ResponseBody
	public ServerResponse<String> resetPassword(String oldPassword, String newPassword) {
		if (StringUtils.isAnyBlank(oldPassword, newPassword)) {
			return ServerResponse.createByErrorMessage("请求参数有误！");
		}
		return userService.resetPassword(oldPassword, newPassword);
	}

	@PostMapping("/login")
	@ResponseBody
	public ServerResponse<User> login(String loginType, String logincode, String password, HttpSession session) {
		if (StringUtils.isAnyBlank(loginType, logincode, password)) {
			return ServerResponse.createByErrorMessage("请求参数有误！");
		}
		ServerResponse<User> serverResponse = userService.login(loginType, logincode, password);
		if (serverResponse.isSuccess()) {
			session.setAttribute(SessionUtil.CURRENT_USER_SESSION, serverResponse.getData());
		}
		return serverResponse;
	}

	@GetMapping("/logout")
	@ResponseBody
	public ServerResponse<String> logout(HttpSession session) {
		session.removeAttribute(SessionUtil.CURRENT_USER_SESSION);
		return ServerResponse.createBySuccessMessage("退出成功！");
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
