package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.CategoryBean;
import sindhi.quiz.daos.beans.FollowerBean;
import sindhi.quiz.daos.beans.ProfileSummary;
import sindhi.quiz.daos.beans.TimelineBean;
import sindhi.quiz.daos.concrete.ProfileDao;
import sindhi.quiz.daos.concrete.ProgressDao;
import sindhi.quiz.daos.concrete.RankDao;
import sindhi.quiz.daos.concrete.TimelineDao;

public class ProfileAction implements SessionAware{
	
	private String location;
	public String u;
	public String userId;
	private Map<String, Object> session;
	public boolean isFollowed;
	
	private ArrayList<TimelineBean> timelineList;
	private ArrayList<CategoryBean> tests;
	private ArrayList<FollowerBean> followingList;
	private ArrayList<FollowerBean> followersList;
	ArrayList<TimelineBean> testRecord;
	
	private ProfileSummary summary;
	private int userProgress;
	private int profileRank;
	int lastHistoryId;
	
	public int getLastHistoryId() {
		return lastHistoryId;
	}

	public void setLastHistoryId(int lastHistoryId) {
		if(timelineList.size() > 0)
			this.lastHistoryId = timelineList.get(timelineList.size()-1).getHistory_id();
	}
	
	
	public int getProfileRank() {
		return profileRank;
	}

	public void setProfileRank(int profileRank) throws SQLException {
		
		RankDao dao = new RankDao();
		this.profileRank = dao.getRank(Integer.parseInt(userId));
		
	}

	public boolean isFollowed() {
		return isFollowed;
	}

	public void setFollowed(boolean isFollowed) throws NumberFormatException, SQLException {
		ProfileDao dao = new ProfileDao();
		int currentUserId = Integer.parseInt((String) session.get("user_id"));
		this.isFollowed = dao.isFollowed(Integer.parseInt(userId), currentUserId);
	}

	public ArrayList<TimelineBean> getTestRecord() {
		return testRecord;
	}

	public void setTestRecord(ArrayList<TimelineBean> testRecord) throws SQLException {
		
			TimelineDao dao = new TimelineDao();
			
			this.testRecord = dao.getTestRecord(userId, 0);
		
	}
	
		
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) throws SQLException {
		TimelineDao dao = new TimelineDao();
		this.userId = dao.getUserId(u);
	}

	public int getUserProgress() {
		return userProgress;
	}

	public void setUserProgress(int userProgress) throws SQLException {
		ProgressDao dao = new ProgressDao();
		this.userProgress = dao.getProgress(userId);
	}

	public ArrayList<CategoryBean> getTests() {
		return tests;
	}

	public void setTests(ArrayList<CategoryBean> tests) throws NumberFormatException, SQLException {
		ProfileDao dao = new ProfileDao();
		this.tests = dao.getTests(Integer.parseInt(userId));
	}

	public ProfileSummary getSummary() {
		return summary;
	}

	public void setSummary(ProfileSummary summary) throws NumberFormatException, SQLException {
		ProfileDao dao = new ProfileDao();
		int currentUserId = Integer.parseInt((String) session.get("user_id"));
		this.summary = dao.getSummary(Integer.parseInt(userId), currentUserId);
	}

	public ArrayList<FollowerBean> getFollowersList() {
		return followersList;
	}

	public void setFollowersList(ArrayList<FollowerBean> followersList) throws NumberFormatException, SQLException {
		ProfileDao dao = new ProfileDao();
		int currentUserId = Integer.parseInt((String) session.get("user_id"));
		this.followersList = dao.getFollowers(Integer.parseInt(userId), currentUserId);
	}

	public ArrayList<FollowerBean> getFollowingList() {
		return followingList;
	}

	public void setFollowingList(ArrayList<FollowerBean> followingList) throws NumberFormatException, SQLException {
		ProfileDao dao = new ProfileDao();
		int currentUserId = Integer.parseInt((String) session.get("user_id"));
		this.followingList = dao.getFollowing(Integer.parseInt(userId), currentUserId);
	}

	public ArrayList<TimelineBean> getTimelineList() {
		return timelineList;
	}

	public void setTimelineList(ArrayList<TimelineBean> timelineList) throws SQLException {
		
			TimelineDao dao = new TimelineDao();
			
			this.timelineList = dao.getTimelineData(userId, -1);
		
	}

	

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String execute() throws SQLException {
		
		if(session.get("user_id") == null) {
			return "login";
		}
		
		System.out.println("U = " + u);
		setUserId(userId);
		setLocation("profile");
		setTimelineList(timelineList);
		setFollowingList(followingList);
		setFollowersList(followersList);
		setSummary(summary);
		setTests(tests);
		setUserProgress(userProgress);
		setTestRecord(testRecord);
		setFollowed(isFollowed);
		setProfileRank(profileRank);
		setLastHistoryId(lastHistoryId);
		
		return "success";
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}



}
