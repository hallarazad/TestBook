package sindhi.quiz.web.actions;

import java.sql.SQLException;

import sindhi.quiz.daos.concrete.FollowingDao;

public class FollowUnfollowAction {
	private int followerId;
	private int followedId;
	
	
	
	public int getFollowerId() {
		return followerId;
	}



	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}



	public int getFollowedId() {
		return followedId;
	}



	public void setFollowedId(int followedId) {
		this.followedId = followedId;
	}



	public String follow() throws SQLException {
		FollowingDao dao = new FollowingDao();
		dao.follow(followerId, followedId);
		
		return null;
	}
	
	public String unfollow() throws SQLException {
		
		FollowingDao dao = new FollowingDao();
		dao.unfollow(followerId, followedId);
		
		return null;
	}
}
