package bootcrm.service;

import com.github.pagehelper.PageInfo;

import bootcrm.common.ServerResponse;
import bootcrm.entity.User;
import bootcrm.vo.UserQueryVO;

public interface UserService {

	ServerResponse<User> login(String loginType, String logincode, String password);
	
	ServerResponse<PageInfo<User>> listUser(UserQueryVO userQueryVO);
	
	ServerResponse<User> getUserById(Integer id);

	ServerResponse<String> insertUser(User user);
	
	ServerResponse<User> updateUser(User user);
	
	ServerResponse<String> resetPassword(String oldPassowrd, String newPassword);
	
	ServerResponse<String> deleteUser(Integer id);
	
	ServerResponse<String> batchDeleteUser(Integer[] ids);
	
	ServerResponse<String> disableOrEnableUser(Integer id,String status);
}
