package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.concrete.UsersDao;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	
	Map<String, Object> session;
	
	private String username;
	private String password;
	
	public void validate() {
		if(getUsername() == null) {
			addFieldError("username", "Please Login First");
		} else if(getUsername().length() == 0) {
			addFieldError("username", "يوزرنيم گھربل آهي");
		}
		
		if(getPassword() == null) {
			
		} else if(getPassword().length() == 0) {
			addFieldError("password", "Password is required");
		}
	}
	
	public String login() {
		UsersDao dao = new UsersDao();
		HashMap sessionMap = null;
		try {
			sessionMap = dao.loginCheck(username, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(sessionMap != null && session != null) {
			
			session.put("login", true);
			session.put("user_id", sessionMap.get("user_id"));
			session.put("username", sessionMap.get("username"));
			session.put("fullname", sessionMap.get("fullname"));
			session.put("email", sessionMap.get("email"));
			session.put("picurl", sessionMap.get("picurl"));
		
			return "success";
		
		} else {
			return "input";
		}
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}

}
