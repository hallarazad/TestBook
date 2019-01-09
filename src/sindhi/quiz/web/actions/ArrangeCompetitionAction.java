package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.concrete.CompetitionDao;





public class ArrangeCompetitionAction implements SessionAware {
	
	private Map<String, Object> session;
	private List<Integer> selectedFriends = new ArrayList<Integer>();
	private int testId;
	private int numberOfQuestions;

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public List getSelectedFriends() {
		return selectedFriends;
	}

	public void setSelectedFriends(List selectedFriends) {
		this.selectedFriends = selectedFriends;
	}
	
	public String execute() throws SQLException {
		
		int userId = Integer.parseInt((String)session.get("user_id"));
		
		CompetitionDao dao = new CompetitionDao();
		
		dao.saveCompetition(userId, selectedFriends, testId, numberOfQuestions);
		
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

}
