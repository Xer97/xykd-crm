package bootcrm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bootcrm.entity.User;
import bootcrm.vo.UserQueryVO;

public interface UserMapper {

	List<User> list(UserQueryVO userQueryVO);

	User getByLoginTypeAndLogincode(@Param("loginType") String loginType, @Param("logincode") String logincode);

	User getByUserId(Integer userId);
	
	User getByUsername(String username);

	int insert(User user);

	int update(User user);

	int delete(Integer userId);
	
	int batchDelete(List<Integer> ids);

	int updateStatus(@Param("id") Integer id, @Param("status") String status);
}
