package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.TimelineBean;
import sindhi.quiz.daos.concrete.TimelineDao;

import com.opensymphony.xwork2.ActionSupport;


public class GetTimelineAction extends ActionSupport implements SessionAware {
	
	Map<String, Object> session;
	ArrayList<TimelineBean> timelineList;
	int lastHistoryId;
	
	public int getLastHistoryId() {
		return lastHistoryId;
	}

	public void setLastHistoryId(int lastHistoryId) {
		if(timelineList.size() > 0)
			this.lastHistoryId = timelineList.get(timelineList.size()-1).getHistory_id();
	}

	private String location;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<TimelineBean> getTimelineList() {
		return timelineList;
	}

	public void setTimelineList(ArrayList<TimelineBean> timelineList) throws SQLException {
		if(session.get("user_id") != null) {
			TimelineDao dao = new TimelineDao();
			String user_id = (String) session.get("user_id");
			
			this.timelineList = dao.getTimelineData(user_id, -1);
		}
	}

	public String executeTimeline() throws SQLException {
		if(session.get("user_id") == null) {
			return "login";
		}
		
		
		TimelineBean obj = new TimelineBean();
		setTimelineList(timelineList);
		setLocation("timeline");
		setLastHistoryId(lastHistoryId);
		
		System.out.println("Last Id: " + lastHistoryId);
		
		return "success";
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}

}
