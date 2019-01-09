package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.beans.SubcategoryBean;
import sindhi.quiz.daos.concrete.TestsRetrieveDao;





public class CategoryAction implements SessionAware {
	private int c;
	private ArrayList<SubcategoryBean> subcategoriesList;
	private Map<String, Object> session;
	
	private String location;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public ArrayList<SubcategoryBean> getSubcategoriesList() {
		return subcategoriesList;
	}

	public void setSubcategoriesList(ArrayList<SubcategoryBean> subcategoriesList) throws SQLException {
		TestsRetrieveDao dao = new TestsRetrieveDao();
		//this.subcategoriesList = dao.getSubcategories(c);
	}

	public String getSubcategories() throws SQLException {
		
		if(session.get("user_id") == null) {
			return "login";
		}
		
		setLocation("category");
		setSubcategoriesList(subcategoriesList);
		
		return "success";
	}

	@Override
	public void setSession(Map session) {
		
		this.session = session;
		
	}
}
