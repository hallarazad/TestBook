package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.concrete.LikesDao;



public class LikesAction implements SessionAware {
	private int historyId;
	private Map<String, Object> session;

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	
	public String like() throws NumberFormatException, SQLException {
		String userId = (String) session.get("user_id");
		LikesDao dao = new LikesDao();
		
		dao.like(Integer.parseInt(userId), historyId);
		
		return null;
	}
	
	public String unlike() throws NumberFormatException, SQLException {
		String userId = (String) session.get("user_id");
		LikesDao dao = new LikesDao();
		
		dao.unlike(Integer.parseInt(userId), historyId);
		
		return null;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;		
	}
}
