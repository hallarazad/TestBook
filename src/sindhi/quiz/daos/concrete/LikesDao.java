package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sindhi.quiz.daos.beans.FollowerBean;

@SuppressWarnings("unchecked")
public class LikesDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public LikesDao() {
		
	}
	
	public boolean isLiked(int postId, int userId) throws SQLException {
		
		con = getConnection();
		
		pst = con.prepareStatement("SELECT COUNT(like_id) count FROM likes WHERE user_id = ? AND post_id = ?");
		pst.setInt(1, userId);
		pst.setInt(2, postId);
		
		rs = pst.executeQuery();
		rs.next();
		
		boolean check = false;
		
		if(rs.getInt("count") > 0) {
			check = true;
		}
		
//		System.out.println("isLiked = " + check);
		
		
		return check;
	}
	

public ArrayList getLikeDetails(int postId, int userId) throws SQLException {
		
		con = getConnection();
		
		pst = con.prepareStatement("SELECT username, fullname, picurl, user_id FROM users WHERE user_id IN (SELECT user_id FROM likes WHERE post_id = ?)");
		pst.setInt(1, postId);
		
		
		rs = pst.executeQuery();
		
		ArrayList<FollowerBean> likeDetailsList = new ArrayList();
		ProfileDao dao = new ProfileDao();
		while(rs.next()) {
			FollowerBean obj = new FollowerBean();
			
			obj.setFullname(rs.getString("fullname"));
			obj.setPicurl(rs.getString("picurl"));
			obj.setUserid(Integer.toString(rs.getInt("user_id")));
			obj.setUsername(rs.getString("username"));
			obj.setFollowed(dao.isFollowed(Integer.parseInt(obj.getUserid()), userId));
			likeDetailsList.add(obj);
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
		
		return likeDetailsList;
		
	}
	
	public int getLikesCount(int postId) throws SQLException {
		
		con = getConnection();
		
		pst = con.prepareStatement("SELECT COUNT(like_id) count FROM likes WHERE post_id = ?");
		pst.setInt(1, postId);
		
		
		rs = pst.executeQuery();
		rs.next();
		
		int count = rs.getInt("count");
		
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
		
		return count;
		
	}
	
	public void like(int userId, int postId) throws SQLException {
		
		
		con = getConnection();
		
		boolean isLiked = isLiked(postId, userId);
//		System.out.println(isLiked);
		
		//if the user is not followed then follow
		if(!isLiked) {
			
			String query = "INSERT INTO likes (datetime, user_id, post_id) VALUES (NOW(), ?, ?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			pst.setInt(2, postId);
			
			
			System.out.println(pst.toString());
			
			pst.executeUpdate();
			
//			System.out.println("Follow code: " + pst.executeUpdate());
		
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
	

	
	public void unlike(int userId, int postId) throws SQLException {
		
		con = getConnection();
		
		
		boolean isFollowed = isLiked(postId, userId);
		
		//if the user is followed then unfollow
		if(isFollowed) {
			
			String query = "DELETE FROM likes WHERE user_id = ? AND post_id = ?";
			pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			pst.setInt(2, postId);
			
			pst.executeUpdate();
			
//			System.out.println("Unfollow code: " + pst.executeUpdate());
				
			
		
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
