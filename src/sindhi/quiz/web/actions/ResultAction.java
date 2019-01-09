package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.QuestionBean;
import sindhi.quiz.daos.beans.TimelineBean;
import sindhi.quiz.daos.concrete.FinishTestDao;






public class ResultAction implements SessionAware {
	
	private Map<String, Object> session;
	
	private TimelineBean result;
	private ArrayList<QuestionBean> questionsAttempted;
	
	private String location;
	
	
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public TimelineBean getResult() {
		return result;
	}


	public void setResult(TimelineBean result) throws SQLException {
		this.result = (TimelineBean)session.get("result");
	}


	public ArrayList<QuestionBean> getQuestionsAttempted() {
		return questionsAttempted;
	}


	public void setQuestionsAttempted(ArrayList<QuestionBean> questionsAttempted) throws SQLException {
		this.questionsAttempted = (ArrayList<QuestionBean>)session.get("questionsAttempted");
	}


	public String execute() throws SQLException {
		
		if(session.get("user_id") == null) {
			return "login";
		}
		
		if(session.get("result") == null) {
			System.out.println("Result action...");
			setResult(result);
			setQuestionsAttempted(questionsAttempted);
			System.out.println("Score = " + result.getScore());
			
			setLocation("result");
		}
		return "success";
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
}
