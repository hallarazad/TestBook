package sindhi.quiz.web.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;





public class TestAction implements SessionAware {
	private int t;
	private Map<String, Object> session;

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public String execute() {
		if(session.get("user_id") == null) {
			return "login";
		}
		
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
