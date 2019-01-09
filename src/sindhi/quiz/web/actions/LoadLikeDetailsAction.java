package sindhi.quiz.web.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.FollowerBean;
import sindhi.quiz.daos.concrete.LikesDao;





public class LoadLikeDetailsAction implements SessionAware {
	private int historyId;
	private Map<String, Object> session;

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	
	public String loadLikeDetails() throws SQLException, IOException {
		LikesDao dao = new LikesDao();
		int userId = Integer.parseInt((String) session.get("user_id"));
		ArrayList<FollowerBean> likesList = dao.getLikeDetails(historyId, userId);
		String text = "";
		FollowerBean obj = new FollowerBean();
		for(int i=0; i<likesList.size(); i++){
			
			obj = likesList.get(i);
			
			text += "<div class=\"bg-panel liker\">";
				text += "<a href=\""+obj.getUsername()+"\">";
					text += "<span>";
						text += "<img class=\"rounded\" src=\""+obj.getPicurl()+"\"/> &nbsp;";
						text += obj.getFullname();
					text += "</span>";
				text += "</a>";
				
				if(Integer.parseInt(obj.getUserid()) != userId) {
				
					text += "<span id=\"btnSpan\">";
								if(obj.getIsFollowed())
									text += "<button id=\"unfollow"+obj.getUserid()+"\" onclick=\"unfollowLikebox("+obj.getUserid()+")\" class=\"btn btn-xs btn-success\"><span class=\"fa fa-check-circle\"></span>&nbsp;&nbsp;Unfollow</button>";
								else
									text += "<button id=\"follow"+obj.getUserid()+"\" onclick=\"followLikebox("+obj.getUserid()+")\" class=\"btn btn-xs btn-default\"><span class=\"fa fa-check-circle-o\"></span>&nbsp;&nbsp;Follow</button>";
					text += "</span>";
				}
			text += "</div>";
		}
		
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().write(text);
		
		return null;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
	}
}
