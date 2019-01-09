package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sindhi.quiz.daos.beans.CategoryBean;
import sindhi.quiz.daos.beans.SubcategoryBean;
import sindhi.quiz.daos.beans.TestBean;

public class TestsRetrieveDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public TestsRetrieveDao() {
		
	}
	
public ArrayList<CategoryBean> getCategories(int user_id) throws SQLException {
	
		System.out.println("UserID: " + user_id);
		
		con = getConnection();
		ArrayList<CategoryBean> catagories = new ArrayList<CategoryBean>();
		
		pst = con.prepareStatement("SELECT * FROM catagories");
		rs = pst.executeQuery();
		
		ProgressDao dao = new ProgressDao();
		
		while(rs.next()) {
			
			CategoryBean obj = new CategoryBean();
			
			obj.setCategory_id(rs.getInt("catagory_id"));
			obj.setCategory_name(rs.getString("catagory_name"));
//			obj.setUser_progress(dao.getSpecialProgress(user_id, obj.getCategory_id(), 1));
			
//			System.out.println("Category: " + obj.getCategory_name());
			
			obj.setSub_categories(getSubcategories(obj.getCategory_id(), user_id));
			
			catagories.add(obj);
		}
		
		try {
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return catagories;
		
	}
	
	public ArrayList<SubcategoryBean> getSubcategories(int category_id, int user_id) throws SQLException {
		con = getConnection();
		
		ArrayList<SubcategoryBean> subcategoriesList = new ArrayList<SubcategoryBean>();
		
		String query = "SELECT * FROM sub_catagories WHERE catagory_id = ?";
		pst = con.prepareStatement(query);
		pst.setInt(1, category_id);
		ResultSet tmprs = pst.executeQuery();
		
		ProgressDao dao = new ProgressDao();
		
		while(tmprs.next()) {
			SubcategoryBean obj = new SubcategoryBean();
			
			obj.setSub_category_id(tmprs.getInt("sub_catagory_id"));
			obj.setSub_category_name(tmprs.getString("sub_catagory_name"));
			obj.setLogo_url(tmprs.getString("logo_url"));
//			obj.setUser_progress(dao.getSpecialProgress(user_id, obj.getSub_category_id(), 2));
			
			subcategoriesList.add(obj);
			
		}
		
		try {
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return subcategoriesList;
	}
	
	public ArrayList<TestBean> getTests(int subcategory_id) throws SQLException {
		con = getConnection();
		
		ArrayList<TestBean> testsList = new ArrayList<TestBean>();
		
		String query = "SELECT * FROM tests WHERE sub_catagory_id = ?";
		pst = con.prepareStatement(query);
		pst.setInt(1, subcategory_id);
		rs = pst.executeQuery();
		
		while(rs.next()) {
			TestBean obj = new TestBean();
			
			obj.setTest_id(rs.getInt("test_id"));
			obj.setTest_name(rs.getString("test_name"));
			obj.setLogo_url(rs.getString("logo_url"));
			
			testsList.add(obj);
			
		}
		
		try {
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return testsList;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
