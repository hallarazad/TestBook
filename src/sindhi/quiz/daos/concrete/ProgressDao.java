package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public ProgressDao() {
		
	}
	
	public int getSpecialProgress(int user_id, int value, int type) throws SQLException {
		con = getConnection();
		
		String query = null;
		if(type == 1) {
			query = "SELECT avg(score) as progress FROM test_history WHERE user_id = ? AND test_id IN ((SELECT test_id FROM tests WHERE sub_catagory_id IN (SELECT sub_catagory_id FROM sub_catagories WHERE catagory_id = ?)))";
		} else if(type == 2) {
			query = "SELECT avg(score) as progress FROM test_history WHERE user_id = ? AND test_id IN ((SELECT test_id FROM tests WHERE sub_catagory_id = ?))";
		} else if(type == 3) {
			query = "SELECT avg(score) as progress FROM test_history WHERE user_id = ? AND test_id = ?";
		}
		pst = con.prepareStatement(query);
		pst.setInt(1, user_id);
		pst.setInt(2, value);
		rs = pst.executeQuery();
		
		int progress = 0;
		
		if(rs.next()) {
		
			progress = (int)rs.getFloat("progress");
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
		
		System.out.println(progress);
		return progress;
	}
	
	public int getProgress(String user_id) throws SQLException {
		con = getConnection();
		int userid = Integer.parseInt(user_id);
		String query = "SELECT avg(score) as progress FROM test_history WHERE user_id = ?";
		pst = con.prepareStatement(query);
		pst.setInt(1, userid);
		rs = pst.executeQuery();
		rs.next();
		
		int progress = (int)rs.getFloat("progress");
		
		
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
		
		System.out.println(progress);
		return progress;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
