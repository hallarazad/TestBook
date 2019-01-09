package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sindhi.quiz.daos.beans.FriendRequestBean;

public class FriendRequestDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public FriendRequestDao() {
		
	}
	
	public void friendRequestsSeen(int userId) throws SQLException {
		con = getConnection();
		
		pst = con.prepareStatement("UPDATE friend_requests SET is_seen = 1 WHERE receiver_id = ?");
		pst.setInt(1, userId);
		pst.executeUpdate();
	}
	
	public int getFriendRequestCount(int userId) throws SQLException {
		con = getConnection();
		int count = 0;
		
		pst = con.prepareStatement("SELECT COUNT(friend_request_id) count FROM friend_requests WHERE receiver_id = ? AND is_seen = 0");
		pst.setInt(1, userId);
		rs = pst.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt("count");
			System.out.println("Count = " +count);
			System.out.println("DB Count = " + rs.getInt("count"));
			
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
		
		return count;
	}
	
	public String acceptRequest(int friend_request_id) throws SQLException {
		con = getConnection();
		
		String msg = "You are now friends";
		
		pst = con.prepareStatement("SELECT * FROM friend_requests WHERE friend_request_id = ?");
		pst.setInt(1, friend_request_id);
		rs = pst.executeQuery();
		
		int adder_id;
		int added_id;
		
		if(rs.next()) {
			
			adder_id = rs.getInt("sender_id");
			added_id = rs.getInt("receiver_id");
		
			rs.close();
			
			pst = con.prepareStatement("SELECT COUNT(friends_id) count FROM friends WHERE adder_user_id = ? AND added_user_id = ?");
			pst.setInt(1, added_id);
			pst.setInt(2, added_id);
			rs = pst.executeQuery();
			
			if(rs.next() && rs.getInt("count") == 0) {
				
				rs.close();
			
				pst = con.prepareStatement("INSERT INTO friends (adder_user_id, added_user_id, date_time) VALUES (?, ?, NOW())");
				pst.setInt(1, added_id);
				pst.setInt(2, added_id);
				pst.executeUpdate();
							
			} else {
				
				msg = "You are already friends!";
			}
			
			pst = con.prepareStatement("DELETE FROM friend_requests WHERE friend_request_id = ?");
			pst.setInt(1, friend_request_id);
			pst.executeUpdate();
		
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
		
		return msg;
		
	}
	
	public ArrayList<FriendRequestBean> getFriendRequests(int userId) throws SQLException {
		con = getConnection();
		
		pst = con.prepareStatement("SELECT * FROM friend_requests WHERE receiver_id = ? ORDER BY is_seen ASC, send_datetime DESC");
		pst.setInt(1, userId);
		rs = pst.executeQuery();
		
		ResultSet rsTemp = null;
		
		ArrayList<FriendRequestBean> friendRequests = new ArrayList<FriendRequestBean>();
		
		while(rs.next()) {
			FriendRequestBean obj = new FriendRequestBean();
			
			obj.setFriend_request_id(rs.getInt("friend_request_id"));
			obj.setSender_id(rs.getInt("sender_id"));
			obj.setReceiver_id(rs.getInt("receiver_id"));
			obj.setDate_time(rs.getString("send_datetime"));
			obj.setIs_seen(rs.getBoolean("is_seen"));
			
			pst = con.prepareStatement("SELECT user_id, username, fullname, picurl FROM users WHERE user_id = ?");
			pst.setInt(1, obj.getSender_id());
			rsTemp = pst.executeQuery();
			
			if(rsTemp.next()) {
				obj.setSender_fullname(rsTemp.getString("fullname"));
				obj.setSender_picurl(rsTemp.getString("picurl"));
				obj.setSender_username(rsTemp.getString("username"));
			}
			
			friendRequests.add(obj);
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
		
		return friendRequests;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
