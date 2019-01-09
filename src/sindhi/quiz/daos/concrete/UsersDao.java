package sindhi.quiz.daos.concrete;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import sindhi.quiz.daos.beans.UserBean;



public class UsersDao implements sindhi.quiz.daos.interfaces.UsersDaoInterface {

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public UsersDao() {
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}
	
	
	@Override
	public void addNewUser(UserBean user) throws SQLException {
		try {
			String query = "INSERT INTO users (username, password, fullname, gender, email) VALUES (?,?,?,?,?)";
			con = getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setString(4, user.getGender());
			pst.setString(5, user.getEmail());
			System.out.println(user.getFullname());
			pst.executeUpdate();
			System.out.println("User added successfully!");
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
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
		}
		
	}

	@Override
	public HashMap loginCheck(String username, String password)
			throws SQLException {
		
		HashMap<String, Object> sessionMap = new HashMap<String, Object>();
		try {
			String query = "SELECT user_id, username, fullname, email, picurl FROM users WHERE username = ? AND password = ?";
			con = getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				sessionMap.put("user_id", rs.getString("user_id"));
				sessionMap.put("username", rs.getString("username"));
				sessionMap.put("fullname", rs.getString("fullname"));
				sessionMap.put("email", rs.getString("email"));
				sessionMap.put("picurl", rs.getString("picurl"));
				
				return sessionMap;
			}
//			System.out.println(rs.getString("fullname"));
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
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
		}
		
		return null;
	}

}
