package bootcrm.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import bootcrm.common.ServerResponse;
import bootcrm.common.SessionUtil;
import bootcrm.entity.User;
import bootcrm.mapper.UserMapper;
import bootcrm.service.UserService;
import bootcrm.vo.UserQueryVO;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	@Override
	public ServerResponse<User> login(String loginType, String logincode, String password) {
		User loginUser = userMapper.getByLoginTypeAndLogincode(loginType, logincode);
		if (Objects.isNull(loginUser) || !Objects.equals(loginUser.getPassword(), password)) {
			return ServerResponse.createByErrorMessage("登录失败！用户名或密码错误！");
		}
		if (loginUser.getStatus().equals(User.USER_DISABLE_STATUS)) {
			return ServerResponse.createByErrorMessage("登录失败！账号已被禁用，请联系管理员！");
		}
		loginUser.setPassword("");
		return ServerResponse.createBySuccess("登录成功！", loginUser);
	}

	@Override
	public ServerResponse<PageInfo<User>> listUser(UserQueryVO userQueryVO) {
		PageHelper.startPage(userQueryVO.getPage(), userQueryVO.getLimit());
		List<User> list = userMapper.list(userQueryVO);
		return ServerResponse.createBySuccess(new PageInfo<>(list));
	}

	@Override
	public ServerResponse<User> getUserById(Integer id) {
		User user = userMapper.getByUserId(id);
		if (Objects.isNull(user)) {
			return ServerResponse.createByErrorMessage("目标用户不存在！");
		}
		return ServerResponse.createBySuccess(user);
	}

	@Override
	public ServerResponse<String> insertUser(User user) {
		if (!logincodeNoExist(user.getLogincode())) {
			return ServerResponse.createByErrorMessage("登录名已存在！");
		}
		if(!usernameNoExist(user.getUsername())) {
			return ServerResponse.createByErrorMessage("用户名已存在！");
		}
		user.setStatus(User.USER_NORMAL_STATUS);
		int rowCount = userMapper.insert(user);
		if (rowCount < 1) {
			return ServerResponse.createByErrorMessage("新增用户失败！");
		}
		return ServerResponse.createBySuccessMessage("添加新用户成功！");
	}

	/**
	 * 检查登录名是否已存在
	 * 
	 * @param logincode
	 * @return
	 */
	private boolean logincodeNoExist(String logincode) {
		User user = userMapper.getByLoginTypeAndLogincode(null, logincode);
		if (Objects.isNull(user)) {
			return true;
		}
		return false;
	}
	
	private boolean usernameNoExist(String username) {
		User user = userMapper.getByUsername(username);
		if (Objects.isNull(user)) {
			return true;
		}
		return false;
	}

	@Override
	public ServerResponse<User> updateUser(User user) {
		if(!usernameNoExist(user.getUsername())) {
			return ServerResponse.createByErrorMessage("用户名已存在！");
		}
		int rowCount = userMapper.update(user);
		if (rowCount < 1) {
			return ServerResponse.createByErrorMessage("修改用户信息失败！");
		}
		return ServerResponse.createBySuccess("修改用户信息成功", userMapper.getByUserId(user.getId()));
	}

	@Override
	public ServerResponse<String> resetPassword(String oldPassowrd, String newPassword) {
		Integer currUserId = SessionUtil.getCurrentUser().getId();
		User currUser = userMapper.getByUserId(currUserId);
		if(!Objects.equals(oldPassowrd, currUser.getPassword())){
			return ServerResponse.createByErrorMessage("原密码不正确！");
		}
		User user = new User();
		user.setId(currUser.getId());
		user.setPassword(newPassword);
		int rowCount = userMapper.update(user);
		if (rowCount < 1) {
			return ServerResponse.createByErrorMessage("修改用户密码失败！");
		}
		return ServerResponse.createBySuccessMessage("修改用户密码成功");
	}

	@Override
	public ServerResponse<String> deleteUser(Integer id) {
		User user = userMapper.getByUserId(id);
		if(user.getType().equals(User.ADMIN_TYPE)) {
			return ServerResponse.createByErrorMessage("删除用户失败！管理员账号不可删除！");
		}
		int rowCount = userMapper.delete(id);
		if (rowCount < 1) {
			return ServerResponse.createByErrorMessage("删除用户失败！");
		}
		return ServerResponse.createBySuccessMessage("删除用户成功！");
	}

	@Override
	public ServerResponse<String> disableOrEnableUser(Integer id, String status) {
		int rowCount = userMapper.updateStatus(id, status);
		if (rowCount < 1) {
			return ServerResponse.createByErrorMessage("修改用户状态失败！");
		}
		return ServerResponse.createBySuccess();
	}

	@Override
	public ServerResponse<String> batchDeleteUser(Integer[] ids) {
		List<Integer> list = Arrays.stream(ids)
				.filter(id -> userMapper.getByUserId(id).getType().equals(User.USER_TYPE))
				.collect(Collectors.toList());
		if(list == null || list.isEmpty()) {
			return ServerResponse.createByErrorMessage("批量删除未完成！(注意：系统管理员不能删除！)");
		}
		int rowCount = userMapper.batchDelete(list);
		if (rowCount != ids.length) {
			return ServerResponse.createByErrorMessage("批量删除未完成！(注意：系统管理员不能删除！)");
		}
		return ServerResponse.createBySuccessMessage("批量删除成功！");
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
