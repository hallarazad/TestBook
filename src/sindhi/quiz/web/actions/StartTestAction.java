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





public class StartTestAction implements SessionAware {
	private Map<String, Object> session;
	private int totalQuestions;
	private int testId;
	

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public String execute() throws SQLException, IOException {
		
		System.out.println("Test Started Successfully! ...");
		
		QuestionRetrieveDao dao = new QuestionRetrieveDao();
		
		QuestionBean question = dao.getFirstQuestion(testId);
		String options = dao.getOptions(question.getQuestion_id());
		
		Vector attemptedQuestionIds = new Vector();
		Vector attemptedOptionIds = new Vector();
//		attemptedQuestionIds.add(question.getQuestion_id());
		
		System.out.println("Total: " + totalQuestions);
		System.out.println("Test: " + testId);
		
		session.put("totalQuestions", totalQuestions);
		session.put("testId", testId);
		session.put("attemptedQuestionIds", attemptedQuestionIds);
		session.put("attemptedOptionIds", attemptedOptionIds);
		session.put("isAlreadyFinished", false);
		
		JSONObject json = new JSONObject();
		StringWriter out = new StringWriter();
		String jsonString = null;
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("UTF-8");
		
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
