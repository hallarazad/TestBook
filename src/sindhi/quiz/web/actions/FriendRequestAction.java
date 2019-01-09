package sindhi.quiz.web.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.concrete.FriendRequestDao;

public class FriendRequestAction implements SessionAware {
	
	private int friend_request_id;
	private Map<String, Object> session;
	
	public int getFriend_request_id() {
		return friend_request_id;
	}

	public void setFriend_request_id(int friend_request_id) {
		this.friend_request_id = friend_request_id;
	}

	public String acceptRequest() throws SQLException, IOException {
		System.out.println("Add request accepted! ID = " + friend_request_id);
		FriendRequestDao dao = new FriendRequestDao();
		String msg = dao.acceptRequest(friend_request_id);
		ServletActionContext.getResponse().getWriter().write(msg);
		return null;
	}
	
	
	
	
	public String friendRequestsSeen() throws NumberFormatException, SQLException {
		FriendRequestDao dao = new FriendRequestDao();
		dao.friendRequestsSeen(Integer.parseInt((String) session.get("user_id")));
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
