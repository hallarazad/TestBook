package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.CategoryBean;
import sindhi.quiz.daos.concrete.TestsRetrieveDao;





public class ChooseTestAction implements SessionAware {
	
	private ArrayList<CategoryBean> categories;
	private String location;
	private Map<String, Object> session;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<CategoryBean> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<CategoryBean> categories) throws SQLException {
		TestsRetrieveDao dao = new TestsRetrieveDao();
		int user_id = Integer.parseInt((String)this.session.get("user_id"));
		this.categories = dao.getCategories(user_id);
	}

	public String execute() throws SQLException {
		setCategories(categories);
		setLocation("choosetest");
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
