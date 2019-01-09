package sindhi.quiz.web.actions;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONObject;

import sindhi.quiz.daos.beans.QuestionBean;
import sindhi.quiz.daos.concrete.QuestionRetrieveDao;






public class LoadQuestionAction implements SessionAware {
	
	private int selectedOption;
	private int questionId;
	private int total;
	private int attempted;
	private int testId;
	
	
	
	public int getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(int selectedOption) {
		this.selectedOption = selectedOption;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
//		System.out.println("Set Method Argument: " + questionId);
//		System.out.println("Set Method This Before Set: " + this.questionId);
		this.questionId = questionId;
//		System.out.println("Set Method This After Set: " + this.questionId);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAttempted() {
		return attempted;
	}

	public void setAttempted(int attempted) {
		this.attempted = attempted;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	private Map<String, Object> session;
	
	public String execute() throws SQLException, IOException {
		
		System.out.println("Question request accepted..");
		
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("UTF-8");
		
		QuestionBean question = new QuestionBean();
		QuestionRetrieveDao dao = new QuestionRetrieveDao();
		
		Vector<Integer> attemptedOptionIds = (Vector<Integer>)session.get("attemptedOptionIds");
		attemptedOptionIds.add(selectedOption);
		
		Vector attemptedQuestionIds = (Vector)session.get("attemptedQuestionIds");
		attemptedQuestionIds.add(questionId);
		
		for(int i=0; i<attemptedQuestionIds.size(); i++) {
			System.out.println("Vector Loop in action class: " + attemptedQuestionIds.get(i));
		}
		
		System.out.println("Question ID at action class: " + questionId);
		
		
		session.put("attemptedQuestionIds", attemptedQuestionIds);
		session.put("attemptedOptionIds", attemptedQuestionIds);
		
		if(attempted == total) {
			res.getWriter().write("finish");
			return null;
		}
		
		question = dao.getQuestion(testId, attemptedQuestionIds);
		String options = dao.getOptions(question.getQuestion_id());
	
		JSONObject json = new JSONObject();
		StringWriter out = new StringWriter();
		String jsonString = null;
		
		
		json.put("questionId", question.getQuestion_id());
		json.put("question", question.getQuestion());
		json.put("options", options);
		
		json.writeJSONString(out);
		jsonString = out.toString();
		
		res.getWriter().write(jsonString);
			
		
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
