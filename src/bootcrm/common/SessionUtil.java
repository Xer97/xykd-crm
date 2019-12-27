package bootcrm.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bootcrm.entity.User;

public class SessionUtil {
	
	public static final String CURRENT_USER_SESSION = "CURRENT_USER_SESSION";
	
	public static User getCurrentUser() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		return (User)session.getAttribute(CURRENT_USER_SESSION);
	}

}
