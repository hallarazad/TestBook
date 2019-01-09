package sindhi.quiz.web.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.UserBean;
import sindhi.quiz.daos.concrete.ProfileDao;






public class LoadFriendsAction implements SessionAware {
	
	private Map<String, Object> session;
	
	public String execute() throws SQLException, IOException {
		
		int userId = Integer.parseInt((String)session.get("user_id"));
		
		ProfileDao dao = new ProfileDao();
		ArrayList<UserBean> friendsList = dao.getFriends(userId);
		
		String friendsStr = "";
		UserBean user = new UserBean();
		for(int i=0; i<friendsList.size(); i++) {
			user = friendsList.get(i);
			friendsStr += "<option value=\""+user.getUser_id()+"\" data-img-src=\""+user.getPicurl()+"\">"+user.getFullname()+"</option>";
		}
		
		
		ServletActionContext.getResponse().getWriter().write(friendsStr);
		
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

}
