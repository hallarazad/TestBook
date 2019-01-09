package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sindhi.quiz.daos.beans.CategoryBean;
import sindhi.quiz.daos.beans.FollowerBean;
import sindhi.quiz.daos.beans.ProfileSummary;
import sindhi.quiz.daos.beans.UserBean;

public class ProfileDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public ProfileDao() {
		
	}
	
	public int getFriendsCount(int userId) throws SQLException {
		con = getConnection();
		
		pst = con.prepareStatement("SELECT COUNT(friends_id) count FROM friends WHERE adder_user_id = ? OR added_user_id = ?");
		pst.setInt(1, userId);
		pst.setInt(2, userId);
		rs = pst.executeQuery();
		int count = 0;
		if(rs.next()) {
			count = rs.getInt("count");
		}
		return count;
	}
	
	public ArrayList<CategoryBean> getTests(int profileUserId) throws SQLException {
		con = getConnection();
		ArrayList<CategoryBean> list = new ArrayList<CategoryBean>();
		pst = con.prepareStatement("SELECT c.catagory_id, c.catagory_name  FROM catagories c JOIN sub_catagories s, test_history th, tests t WHERE c.catagory_id = s.catagory_id AND s.sub_catagory_id = t.sub_catagory_id AND t.test_id = th.test_id AND th.user_id = ? GROUP BY c.catagory_id");
		pst.setInt(1, profileUserId);
		rs = pst.executeQuery();
		
		while(rs.next()) {
			CategoryBean test = new CategoryBean();
			test.setCategory_id(rs.getInt("catagory_id"));
			test.setCategory_name(rs.getString("catagory_name"));
			list.add(test);
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
		
		return list;
	}
	
	public ProfileSummary getSummary(int profileUserId, int currentUserId) throws SQLException {
		con = getConnection();
		
		//set basic profile info
		pst = con.prepareStatement("SELECT a.email, a.username, a.picurl, a.fullname, a.user_id FROM users a WHERE user_id = ?");
		pst.setInt(1, profileUserId);
		
		ProfileSummary summary = new ProfileSummary();
		
		rs = pst.executeQuery();
		rs.next();
		
		summary.setFullname(rs.getString("fullname"));
		summary.setPicurl(rs.getString("picurl"));
		summary.setUserid(Integer.parseInt(rs.getString("user_id")));
		summary.setEmail(rs.getString("email"));
		
		
		
		//set profile details
		pst = con.prepareStatement("SELECT * FROM user_profile WHERE user_id = ?");
		pst.setInt(1, profileUserId);
		rs = pst.executeQuery();
		if(rs.next()) {
			summary.setAboutMe(rs.getString("about_me"));
			summary.setFacebook(rs.getString("facebook_link"));
		}
		
		
		//set followers count
		pst = con.prepareStatement("SELECT count(follower_id) as followers FROM followers WHERE follower_id = ?");
		pst.setInt(1, profileUserId);
		rs = pst.executeQuery();
		if(rs.next()) {
//			System.out.println("Number of Followers: " + rs.getInt("followers"));
			summary.setFollowersCount(rs.getInt("followers"));
		}
		
		
		//set following count
		pst = con.prepareStatement("SELECT count(follower_id) as following FROM followers WHERE user_id = ?");
		pst.setInt(1, profileUserId);
		rs = pst.executeQuery();
		if(rs.next()) {
//			System.out.println("Number of Following: " + rs.getInt("following"));
			summary.setFollowingCount(rs.getInt("following"));
		}
		
		
		//set following count
		pst = con.prepareStatement("SELECT count(test_id) as tests FROM test_history WHERE user_id = ?");
		pst.setInt(1, profileUserId);
		rs = pst.executeQuery();
		if(rs.next()) {
//			System.out.println("Number of Following: " + rs.getInt("tests"));
			summary.setTestsCount(rs.getInt("tests"));
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
				
		return summary;
	}
	
	public boolean isFollowed(int profileUserId, int currentUserId) throws SQLException {
		con = getConnection();
		
		
		String query = "SELECT COUNT(user_id) as count FROM followers WHERE user_id = "+currentUserId+" AND follower_id = " + profileUserId;
		System.out.println("Query = " + query);
		
		pst = con.prepareStatement("SELECT COUNT(user_id) as count FROM followers WHERE user_id = ? AND follower_id = ?");
		pst.setInt(1, currentUserId);
		pst.setInt(2, profileUserId);
		
		rs = pst.executeQuery();
		
		boolean check = false;
		
		rs.next();

		//if the count is greater then 0 it means the user is followed
		System.out.println("Count: " + rs.getInt("count"));
		if(rs.getInt("count") > 0) {
			check = true;
		}
		
		System.out.println("isFollowed = " + check);
		
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
		
		return check;
	}
	
	public ArrayList<UserBean> getFriends(int userId) throws SQLException {
		
//		System.out.println("get followers, Profile userid: " + profileUserId + " Current User: " + currentUserId);
		
		con = getConnection();
		pst = con.prepareStatement("SELECT picurl, fullname, user_id FROM users WHERE user_id IN ((SELECT added_user_id FROM friends WHERE adder_user_id = ?)) OR user_id IN ((SELECT adder_user_id FROM friends WHERE added_user_id = ?)) ORDER BY fullname ASC");
		pst.setInt(1, userId);
		pst.setInt(2, userId);
		rs = pst.executeQuery();
		
		ArrayList<UserBean> friendsList = new ArrayList<UserBean>();
		
		while(rs.next()) {
			UserBean obj = new UserBean();
			obj.setFullname(rs.getString("fullname"));
			obj.setUser_id(rs.getInt("user_id"));
//			obj.setUsername(rs.getString("username"));
			obj.setPicurl(rs.getString("picurl"));
			
			friendsList.add(obj);
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
		
		return friendsList;
	}
	
	public ArrayList getFollowers(int profileUserId, int currentUserId) throws SQLException {
		
//		System.out.println("get followers, Profile userid: " + profileUserId + " Current User: " + currentUserId);
		
		con = getConnection();
		pst = con.prepareStatement("SELECT a.picurl, a.fullname, a.username, a.user_id FROM users a JOIN followers b ON a.user_id = b.user_id WHERE b.follower_id = ? ORDER BY b.date_time DESC");
		pst.setInt(1, profileUserId);
		rs = pst.executeQuery();
		
		ArrayList<FollowerBean> followersList = new ArrayList<FollowerBean>();
		ResultSet rsTemp;
		while(rs.next()) {
			FollowerBean obj = new FollowerBean();
			obj.setFullname(rs.getString("fullname"));
			obj.setUserid(rs.getString("user_id"));
			obj.setUsername(rs.getString("username"));
			obj.setPicurl(rs.getString("picurl"));
			
//			System.out.println("Follower ID: " + obj.getUserid());
			
			pst = con.prepareStatement("SELECT user_id FROM followers WHERE user_id = ? AND follower_id = ?");
			pst.setInt(1, currentUserId);
			pst.setInt(2, Integer.parseInt(obj.getUserid()));
			
			rsTemp = pst.executeQuery();
			if(rsTemp.next()) {
				obj.setFollowed(true);
//				System.out.println("isFollowed = " + obj.getIsFollowed());
			}
			
			followersList.add(obj);
			
			
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
		
		return followersList;
	}
	
public ArrayList getFollowing(int profileUserId, int currentUserId) throws SQLException {
		
//		System.out.println("Profile userid: " + profileUserId + " Current User: " + currentUserId);
		
		con = getConnection();
		pst = con.prepareStatement("SELECT a.picurl, a.fullname, a.username, a.user_id FROM users a JOIN followers b ON a.user_id = b.follower_id WHERE b.user_id = ? ORDER BY b.date_time DESC");
		pst.setInt(1, profileUserId);
		rs = pst.executeQuery();
		
		ArrayList<FollowerBean> followingList = new ArrayList<FollowerBean>();
		ResultSet rsTemp;
		while(rs.next()) {
			FollowerBean obj = new FollowerBean();
			obj.setFullname(rs.getString("fullname"));
			obj.setUserid(rs.getString("user_id"));
			obj.setUsername(rs.getString("username"));
			obj.setPicurl(rs.getString("picurl"));
			
//			System.out.println("Following ID: " + obj.getUserid());
			
			pst = con.prepareStatement("SELECT user_id FROM followers WHERE user_id = ? AND follower_id = ?");
			pst.setInt(1, currentUserId);
			pst.setInt(2, Integer.parseInt(obj.getUserid()));
			
			rsTemp = pst.executeQuery();
			if(rsTemp.next()) {
				obj.setFollowed(true);
//				System.out.println("isFollowed = " + obj.getIsFollowed());
			}
			
			followingList.add(obj);
			
			
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
		
		return followingList;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
