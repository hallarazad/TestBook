package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.QuestionBean;
import sindhi.quiz.daos.beans.TimelineBean;
import sindhi.quiz.daos.concrete.FinishTestDao;






public class FinishTestAction implements SessionAware {
	
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
		FinishTestDao dao = new FinishTestDao();
		int total = (int)session.get("totalQuestions");
		System.out.println("Total: " + total);
		int testId = (int)session.get("testId");
		int userId = Integer.parseInt((String)session.get("user_id"));
		Vector selectedOptions = (Vector)session.get("attemptedOptionIds");
		
		this.result = dao.saveResult(total, userId, testId, selectedOptions);
	}


	public ArrayList<QuestionBean> getQuestionsAttempted() {
		return questionsAttempted;
	}


	public void setQuestionsAttempted(ArrayList<QuestionBean> questionsAttempted) throws SQLException {
		FinishTestDao dao = new FinishTestDao();
		Vector questionIds = (Vector)session.get("attemptedQuestionIds");
		this.questionsAttempted = dao.getAttemptedQuestions(questionIds);
	}


	public String execute() throws SQLException {
		
		if(session.get("user_id") == null) {
			return "login";
		}
		
		if(!(boolean)session.get("isAlreadyFinished")) {
			
			System.out.println("Finish Test action started...");
			setResult(result);
			setQuestionsAttempted(questionsAttempted);
			setLocation("result");
			//setLocation("result");
			
			session.remove("attemptedQuestionIds");
			session.remove("attemptedOptionIds");
			session.remove("testId");
			session.remove("totalQuestions");
			
			session.put("isAlreadyFinished", true);
			
			return "success";
		
		} else {
			return "home";
		}
		
		
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
}
