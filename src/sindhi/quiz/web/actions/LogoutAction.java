package sindhi.quiz.web.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {
	
	Map<String, Object> session;
	
	public String logout() {
		
		if(session.containsKey("login")) {
			session.clear();
		}
		
		return "success";
		
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}

}
