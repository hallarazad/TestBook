package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.TestBean;
import sindhi.quiz.daos.concrete.TestsRetrieveDao;





public class SubcategoryAction implements SessionAware {
	private int sc;
	private ArrayList<TestBean> testsList;
	private Map<String, Object> session;
	
	private String location;
	
	public int getSc() {
		return sc;
	}

	public void setSc(int sc) {
		this.sc = sc;
	}

	public ArrayList<TestBean> getTestsList() {
		return testsList;
	}

	public void setTestsList(ArrayList<TestBean> testsList) throws SQLException {
		TestsRetrieveDao dao = new TestsRetrieveDao();
		this.testsList = dao.getTests(sc);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	

	public String getTests() throws SQLException {
		
		if(session.get("user_id") == null) {
			return "login";
		}
		
		setTestsList(testsList);
		setLocation("subcategory");
		
		return "success";
	}

	@Override
	public void setSession(Map session) {
		
		this.session = session;
		
	}
}
