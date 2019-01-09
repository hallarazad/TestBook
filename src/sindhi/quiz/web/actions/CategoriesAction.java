package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import sindhi.quiz.daos.beans.CategoryBean;
import sindhi.quiz.daos.concrete.CategoriesDao;

import com.opensymphony.xwork2.ActionSupport;

public class CategoriesAction extends ActionSupport {
	
	ArrayList<CategoryBean> catagories;

	public String getCategories() throws SQLException {
		
		setCatagories(catagories);
		return "success";
	}

	public ArrayList<CategoryBean> getCatagories() {
		return catagories;
	}

	public void setCatagories(ArrayList<CategoryBean> catagories) throws SQLException {
		CategoriesDao dao = new CategoriesDao();
		this.catagories = dao.getCategories();
	}

}
