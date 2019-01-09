package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.FriendRequestBean;
import sindhi.quiz.daos.concrete.FriendRequestDao;
import sindhi.quiz.daos.concrete.ProfileDao;
import sindhi.quiz.daos.concrete.ProgressDao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class ProgressAction implements SessionAware {
	private int progress;
	Map<String, Object> session;
	ArrayList<FriendRequestBean> friendRequests;
	private int friendRequestCount;
	private int friendsCount;
	
	public int getFriendsCount() {
		return friendsCount;
	}

	public void setFriendsCount(int friendsCount) throws NumberFormatException, SQLException {
		ProfileDao dao = new ProfileDao();
		this.friendsCount = dao.getFriendsCount(Integer.parseInt((String) session.get("user_id")));
	}

	public int getFriendRequestCount() {
		return friendRequestCount;
	}

	public void setFriendRequestCount(int friendRequestCount) throws NumberFormatException, SQLException {
		FriendRequestDao dao = new FriendRequestDao();
		this.friendRequestCount = dao.getFriendRequestCount(Integer.parseInt((String) session.get("user_id")));
	}

	public ArrayList<FriendRequestBean> getFriendRequests() {
		return friendRequests;
	}

	public void setFriendRequests(ArrayList<FriendRequestBean> friendRequests) throws NumberFormatException, SQLException {
		FriendRequestDao dao = new FriendRequestDao();
		this.friendRequests = dao.getFriendRequests(Integer.parseInt((String) session.get("user_id")));
	}

	public String executeProgress() throws SQLException {
		String location = null;
		ValueStack stack = ActionContext.getContext().getValueStack();
		location = stack.findString("location");
		System.out.println("progress: " + location);
		setProgress(progress);
		setFriendsCount(friendsCount);
		setFriendRequestCount(friendRequestCount);
		setFriendRequests(friendRequests);
		return location;
	}
	
	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) throws SQLException {
		String user_id = (String) session.get("user_id");
		ProgressDao dao = new ProgressDao();
		this.progress = dao.getProgress(user_id);
	}

	
}
