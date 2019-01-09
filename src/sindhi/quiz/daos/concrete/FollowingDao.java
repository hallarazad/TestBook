package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowingDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public FollowingDao() {
		
	}
	
	public boolean isFollowed(int profileUserId, int currentUserId) throws SQLException {
		
		System.out.println("isFollowed method of dao called...");
		
		pst = con.prepareStatement("SELECT user_id FROM followers WHERE user_id = ? AND follower_id = ?");
		pst.setInt(1, currentUserId);
		pst.setInt(2, profileUserId);
		
		rs = pst.executeQuery();
		
		boolean check = false;
		
		if(rs != null || rs.next()) {
			check = true;
		}
		
		System.out.println("isFollowed = " + check);
		
		
		return check;
	}
	
	public void follow(int userId, int followerId) throws SQLException {
		
		System.out.println("Follow method of dao called...");
		
		con = getConnection();
		
		ProfileDao dao = new ProfileDao();
		
		System.out.println("User ID in Dao: " + userId);
		
		boolean isFollowed = dao.isFollowed(followerId, userId);
//		System.out.println(isFollowed);
		
		//if the user is not followed then follow
		if(!isFollowed) {
			
			System.out.println("Follow method; if the specified user is not followed then follow...");
			
			String query = "INSERT INTO followers (date_time, user_id, follower_id) VALUES (NOW(), ?, ?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			pst.setInt(2, followerId);
			
//			pst.executeUpdate();
			
			System.out.println("Follow code: " + pst.executeUpdate());
		
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
	}
	
	public void unfollow(int userId, int followerId) throws SQLException {
		
		System.out.println("Unfollow method of dao called...");
		
		con = getConnection();
		
		ProfileDao dao = new ProfileDao();
		
		boolean isFollowed = dao.isFollowed(followerId, userId);
		
		//if the user is followed then unfollow
		if(isFollowed) {
			
			System.out.println("Unfollow method; if the specified user is followed then unfollow...");
		
			String query = "DELETE FROM followers WHERE user_id = ? AND follower_id = ?";
			pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			pst.setInt(2, followerId);
			
			System.out.println("Unfollow code: " + pst.executeUpdate());
				
			
		
		}
		
		try {
			
			if (con != null) {
				con.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
