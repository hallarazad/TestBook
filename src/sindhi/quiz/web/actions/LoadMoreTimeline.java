package sindhi.quiz.web.actions;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONObject;

import sindhi.quiz.daos.beans.TimelineBean;
import sindhi.quiz.daos.concrete.TimelineDao;

public class LoadMoreTimeline implements SessionAware {
	
	private int from;
	Map<String, Object> session;
	
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public String loadMoreTimeline() throws IOException {
		
		System.out.println("**************Start***************");
		System.out.println("From = " + from);
		
		JSONObject json = new JSONObject();
		StringWriter out = new StringWriter();
		String jsonString = null;
		HttpServletResponse res = ServletActionContext.getResponse();
		ArrayList timelineList = new ArrayList();
		
		if(from > 0) {
			
			//get logged user id
			String userId = (String) session.get("user_id");
			
			
			//get timeline data from database
			TimelineDao dao = new TimelineDao();
			try {
				timelineList = dao.getTimelineData(userId, from);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Timeline Size = " + timelineList.size());
			
			TimelineBean obj = new TimelineBean();
			String posts = "";
			
			boolean check = false;
			
			//render posts html
			for(int i=0; i<timelineList.size(); i++) {
				
				obj = (TimelineBean)timelineList.get(i);
				
				  
				  if(check) { posts += "<div class='tl-entry'>"; } 
				  else { posts += "<div class='tl-entry left'>"; }
				  
				  check = !check;
				  
					  posts += "<div class='tl-time'>"
							   		  +"<abbr style='border-bottom: 0' class='timeago' title=\""+obj.getDate_time()+"\"></abbr>"
							  +"</div>";
					  posts += "<div class=\"tl-icon bg-success\"><img src=\"" + obj.getPic_url() +"\" alt=\"\"></div>";
					  
					  posts += "<div class=\"panel tl-body\">";
						  posts += " <a href=\"profileAction.action?u="+obj.getUsername()+"\">";
						  
						  if(obj.getUser_id() == Integer.parseInt(userId)) {
							  posts += "توهان";
						  } else {
							  posts += obj.getUser_name() + "</a>";
						  }
						  
						  posts += "<a href=\"category.action?c="+obj.getCategory_id()+"\"> " + obj.getCategory_name() + "</a> > ";
						  posts += "<a href=\"subcategory.action?sc="+obj.getSub_category_id()+"\"> " + obj.getSub_category_name() + " </a> >";
						  posts += "<a href=\"\"> " + obj.getTest_name() + " </a>";
						  
						  posts += " ٽيسٽ ڏني جنهن ۾";
						  
						  if(obj.getUser_id() == Integer.parseInt(userId)) {
							  posts += "توهان";
						  } else {
							  posts += "هن";
						  }
						  
						  posts += "<div class=\"well well-sm\" style=\"margin: 10px 0 0 0;\">";
						  	posts += "<ul class=\"nav nav-pills\">";
						  		posts += "<li class=\"active\"><a href=\"#\">مڪمل<span class=\"label label-warning integers\">"+ obj.getTotal_attempted() +"</span></a></li>";
						  		posts += "<li class=\"active\" ><a style=\"background-color:#36a766;\" href=\"#\">صحيح<span class=\"label label-warning integers\">"+obj.getCorrect_answers()+"</span></a></li>";
						  		posts += "<li class=\"active\" ><a style=\"background-color:#d54848;\" href=\"#\">غلط<span class=\"label label-warning integers\">"+ obj.getWrong_answers() +"</span></a></li>";
						  		posts += "<li class=\"active\" ><a style=\"background-color:#3156be;\" href=\"#\">ڪاميابي<span class=\"label label-warning integers\">"+ obj.getScore() +"%</span></a></li>";
						  		
						  	posts += "</ul>";
					      posts += "</div>";
					      posts += "<span class=\"label label-tag\" style=\"margin-top: 10px;\">";
						      posts += "<span id=\"likeSpan"+obj.getHistory_id()+"\">";
							      if(obj.isIs_liked()) {
							    	  posts += "<a href=\"javascript: return null;\" onclick=\"unlike("+obj.getHistory_id()+")\" id=\"unlike"+obj.getHistory_id()+"\" style=\"color: white;\" class=\"btn-label icon fa fa-thumbs-up\"></a>";
							      } else {
							    	  posts += "<a href=\"javascript: return null;\" onclick=\"like("+obj.getHistory_id()+")\" id=\"like"+obj.getHistory_id()+"\" style=\"color: white;\" class=\"btn-label icon fa fa-thumbs-o-up\"></a>";
							      }
						      posts += "</span>";
						      posts += "&nbsp;&nbsp;<a id=\"likes"+obj.getHistory_id()+"\" value=\""+obj.getHistory_id()+"\" href=\"javascript: return null;\" onclick=\"likeDetails("+obj.getHistory_id()+")\" class=\"integers numbers\" style=\"color: white\">"+obj.getLikesCount()+"</a>";
					      posts += "</span>";
					  posts += "</div>";
					  posts += "</div>";
					  
					  System.out.println("History Id = " + obj.getHistory_id());
					  
			} //end of for loop
			
			int lastId = 0;		//variable for storing lastId
			
			if(timelineList.size() > 0) {	//checking if the list is not empty
				TimelineBean last = (TimelineBean)timelineList.get(timelineList.size()-1);
				lastId = last.getHistory_id();
			}
			
			
			
			json.put("posts", posts);
			json.put("from", lastId);
			
			
			json.writeJSONString(out);
			jsonString = out.toString();
			
			
		} else {
			json.put("posts", "");
			json.put("from", "0");
		}
		
		System.out.println(jsonString);
		
		res.setCharacterEncoding("UTF-8");
		if(jsonString != null)
			res.getWriter().write(jsonString);
		
		System.out.println("**************End***************");
		return null;
	}

	@Override
	public void setSession(Map session) {
		
		this.session = session;
	}

}
