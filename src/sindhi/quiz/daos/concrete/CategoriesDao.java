package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sindhi.quiz.daos.beans.CategoryBean;

public class CategoriesDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public CategoriesDao() {
		
	}
	
	public ArrayList<CategoryBean> getCategories() throws SQLException {
		
		con = getConnection();
		ArrayList<CategoryBean> catagories = new ArrayList<CategoryBean>();
		
		pst = con.prepareStatement("SELECT * FROM catagories");
		rs = pst.executeQuery();
		
		while(rs.next()) {
			
			CategoryBean obj = new CategoryBean();
			
			obj.setCategory_id(rs.getInt("catagory_id"));
			obj.setCategory_name(rs.getString("catagory_name"));
			
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
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}
}
