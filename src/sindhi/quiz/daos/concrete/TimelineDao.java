package sindhi.quiz.daos.concrete;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sindhi.quiz.daos.beans.TimelineBean;
import sindhi.quiz.daos.beans.UserBean;

public class TimelineDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public TimelineDao() {
		
	}
	
	
	public void makeItPublic(int historyId) throws SQLException {
		
		con = getConnection();
		
		String query = "UPDATE test_history SET is_public = 1 WHERE history_id = ?";
		pst = con.prepareStatement(query);
		pst.setInt(1, historyId);
		pst.executeUpdate();
		
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
	
public void makeItPrivate(int historyId) throws SQLException {
		
		con = getConnection();
		
		String query = "UPDATE test_history SET is_public = 0 WHERE history_id = ?";
		pst = con.prepareStatement(query);
		pst.setInt(1, historyId);
		pst.executeUpdate();
		
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
	
	
	
	public String getUserId(String username) throws SQLException {
		
//		System.out.println("GetUserId: " + username);
		
		con = getConnection();
		
		pst = con.prepareStatement("SELECT user_id FROM users WHERE username = ?");
		pst.setString(1, username);
		rs = pst.executeQuery();
		rs.next();	
		
		String userId = rs.getString("user_id");
		
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
		
		return userId;
		
	}
	
	public ArrayList getTimelineData(String user_id, int from) throws SQLException {
//		System.out.println("User Id: " + user_id);
		
		con = getConnection();
		
		ArrayList<TimelineBean> timelineList = new ArrayList<TimelineBean>();
			
			ResultSet rsPosts = null;
			pst = con.prepareStatement("SELECT * FROM posts WHERE post_by_user IN ((SELECT follower_id FROM followers WHERE user_id = ?)) OR post_by_user = ? OR post_by_user IN ((SELECT added_user_id FROM friends WHERE adder_user_id = ?)) OR post_by_user IN ((SELECT adder_user_id FROM friends WHERE added_user_id = ?)) ORDER BY date_time DESC");
			pst.setInt(1, Integer.parseInt(user_id));
			pst.setInt(2, Integer.parseInt(user_id));
			pst.setInt(3, Integer.parseInt(user_id));
			pst.setInt(4, Integer.parseInt(user_id));
			rsPosts = pst.executeQuery();
			
			while(rsPosts.next()) {
		
				TimelineBean obj = new TimelineBean();
				LikesDao dao = new LikesDao();
				
				obj.setPost_id(rsPosts.getInt("post_id"));
				obj.setIs_liked(dao.isLiked(obj.getPost_id(), Integer.parseInt(user_id)));
				obj.setLikesCount(dao.getLikesCount(obj.getPost_id()));
				
				System.out.println("Type: " + rsPosts.getString("post_type"));
				
				if(rsPosts.getString("post_type").equals("test")) {
//					System.out.println("Type is test");
					//preparing query
					String timelineQuery = "SELECT history.history_id, history.total_attempted, history.correct_answers, history.wrong_answers, history.score, history.date_time as history_date_time, history.test_id, history.is_public, (SELECT temp_tests.test_name FROM tests as temp_tests WHERE temp_tests.test_id = history.test_id) as test_name, (SELECT temp_tests.sub_catagory_id FROM tests as temp_tests WHERE temp_tests.test_id = history.test_id) as sub_cat_id, (SELECT temp_sub_catagories.sub_catagory_name FROM sub_catagories as temp_sub_catagories WHERE temp_sub_catagories.sub_catagory_id = sub_cat_id) as sub_catagory_name, (SELECT temp_sub_catagories.catagory_id FROM sub_catagories as temp_sub_catagories WHERE temp_sub_catagories.sub_catagory_id = sub_cat_id) as cat_id, (SELECT temp_catagories.catagory_name FROM catagories as temp_catagories WHERE temp_catagories.catagory_id = cat_id) as catagory_name, history.user_id, (SELECT temp_users.fullname FROM users as temp_users WHERE temp_users.user_id = history.user_id) as fullname, (SELECT temp_users.username FROM users as temp_users WHERE temp_users.user_id = history.user_id) as username, (SELECT temp_users.picurl FROM users as temp_users WHERE temp_users.user_id = history.user_id) as picurl FROM test_history as history WHERE (history.user_id IN (SELECT follower_id FROM followers WHERE user_id = ?) OR history.user_id = ?) AND history.post_id = ? ";
					String pieceForAjaxLoad = " AND history_id < ? ORDER BY history.date_time DESC LIMIT 10";
					if(from != -1) {
						timelineQuery += pieceForAjaxLoad;
					}
					else {
						timelineQuery += "ORDER BY history.date_time DESC LIMIT 10";
					}
					pst = con.prepareStatement(timelineQuery);
					pst.setInt(1, Integer.parseInt(user_id));
					pst.setInt(2, Integer.parseInt(user_id));
					pst.setInt(3, rsPosts.getInt("post_id"));
					if(from != -1)
						pst.setInt(4, from);
					
//					System.out.println("Query: " + pst.toString());
					
					
					
					
					
					rs = pst.executeQuery();
					
					
					
					if(rs.next()) {
						
						
						
						obj.setHistory_id(rs.getInt("history_id"));
						obj.setTotal_attempted(rs.getInt("total_attempted"));
						obj.setCorrect_answers(rs.getInt("correct_answers"));
						obj.setWrong_answers(rs.getInt("wrong_answers"));
						obj.setScore(rs.getFloat("score"));
						obj.setDate_time(rs.getString("history_date_time"));
						obj.setTest_id(rs.getInt("test_id"));
						obj.setTest_name(rs.getString("test_name"));
						obj.setSub_category_id(rs.getInt("sub_cat_id"));
						obj.setSub_category_name(rs.getString("sub_catagory_name"));
						obj.setCategory_id(rs.getInt("cat_id"));
						obj.setCategory_name(rs.getString("catagory_name"));
						obj.setUser_id(rs.getInt("user_id"));
						obj.setUser_name(rs.getString("fullname"));
						obj.setPic_url(rs.getString("picurl"));
						obj.setIs_public(rs.getBoolean("is_public"));
						obj.setUsername(rs.getString("username"));
						
						obj.setPost_id(rsPosts.getInt("post_id"));
						obj.setPost_type("test");
						
		//				System.out.println("Liked: " + obj.isIs_liked() + "  HistoryId: " + obj.getHistory_id());
		//				System.out.println("Likes: " + obj.getLikesCount());
						
						timelineList.add(obj);
						
		//				System.out.println(obj.getUsername());
					} 
				
				} //end of if type test
				else if(rsPosts.getString("post_type").equals("competition")) {
					
					
					pst = con.prepareStatement("SELECT * FROM competitions WHERE post_id = ?");
					pst.setInt(1, rsPosts.getInt("post_id"));
					rs = pst.executeQuery();
					
					
					if(rs.next()) { //if there is a competition with the post_id
						
						obj.setTest_id(rs.getInt("test_id"));
						obj.setUser_id(rs.getInt("arranged_by_user"));
						obj.setDate_time(rsPosts.getString("date_time"));
						obj.setNum_of_questions(rs.getInt("num_of_questions"));
					
						obj.setPost_type("competition");	
						obj.setCompetition_id(rs.getInt("competition_id"));
						
						pst = con.prepareStatement("SELECT * FROM tests WHERE test_id = ?");
						pst.setInt(1, obj.getTest_id());
						ResultSet rsTemp = pst.executeQuery();
						
						if(rsTemp.next()) { //if there is a test with that test_id
							obj.setSub_category_id(rsTemp.getInt("sub_catagory_id"));
							obj.setTest_name(rsTemp.getString("test_name"));
							//rsTemp.close();
						}
						
						
						
						pst = con.prepareStatement("SELECT * FROM sub_catagories WHERE sub_catagory_id = ?");
						pst.setInt(1, obj.getSub_category_id());
						rsTemp = pst.executeQuery();
						
						if(rsTemp.next()) { //if there is a subcatagory with that sub_category_id
							obj.setSub_category_name(rsTemp.getString("sub_catagory_name"));
							obj.setCategory_id(rsTemp.getInt("catagory_id"));
							//rsTemp.close();
						}
						
						pst = con.prepareStatement("SELECT * FROM catagories WHERE catagory_id = ?");
						pst.setInt(1, obj.getCategory_id());
						rsTemp = pst.executeQuery();
						
						if(rsTemp.next()) { //if there is a catagory with the catagory_id
							obj.setCategory_name(rsTemp.getString("catagory_name"));
							//rsTemp.close();
						}
						
						pst = con.prepareStatement("SELECT username, user_id, fullname, picurl FROM users WHERE user_id = ?");
						pst.setInt(1, obj.getUser_id());
						rsTemp = pst.executeQuery();
						
						if(rsTemp.next()) { //if there is a user with the user_id
							obj.setUser_name(rsTemp.getString("fullname"));
							obj.setUsername(rsTemp.getString("username"));
							obj.setPic_url(rsTemp.getString("picurl"));
							//rsTemp.close();
						}
						
						pst = con.prepareStatement("SELECT u.user_id, u.username, u.fullname, u.picurl, c.has_attempted FROM users u, competition_users c WHERE u.user_id IN ((SELECT user_id FROM competition_users WHERE competition_id = ?)) AND u.user_id = c.user_id GROUP BY u.user_id ORDER BY c.has_attempted DESC");
						pst.setInt(1, rs.getInt("competition_id"));
						rsTemp = pst.executeQuery();
						
						ArrayList<UserBean> competitionUsers = new ArrayList<UserBean>();
						
						while(rsTemp.next()) {
							
							
							UserBean user = new UserBean();
							
							user.setUser_id(rsTemp.getInt("user_id"));
							user.setFullname(rsTemp.getString("fullname"));
							user.setUsername(rsTemp.getString("username"));
							user.setPicurl(rsTemp.getString("picurl"));
							user.setHas_attempted(rsTemp.getBoolean("has_attempted"));
							
							System.out.println("Participant: " + user.getFullname());
							
							//System.out.println(user.getFullname() + " has " + user.isHas_attempted());
							
							ResultSet rsTempTwo = null;
							
							if(user.isHas_attempted()) {
								
								System.out.println(user.getFullname() + " has attempted");
								
								pst = con.prepareStatement("SELECT * FROM test_history WHERE user_id = ? AND competition_id = ?");
								pst.setInt(1, user.getUser_id());
								pst.setInt(2, obj.getCompetition_id());
//								System.out.println(pst.toString());
								rsTempTwo = pst.executeQuery();
								
								if(rsTempTwo.next()) {
									user.setCorrect_answers(rsTempTwo.getInt("correct_answers"));
									user.setWrong_answers(rsTempTwo.getInt("wrong_answers"));
									user.setScore(rsTempTwo.getFloat("score"));
//									System.out.println(user.getFullname() + "'s Score: " + user.getScore());
									user.setDate_time(rsTempTwo.getString("date_time"));
								}
								
								rsTempTwo.close();
							}
							
							competitionUsers.add(user);
							
							//rsTemp.close();
							
						}//end of competition_users while
						
						//sorting of users according to there score
						
						
						obj.setCompetition_users(competitionUsers);
						
					
					} //if competition is available with the post_id
					
					timelineList.add(obj);
				
				}//end of if type is competition
			
			} //end of while
		
		
		
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
		
		return timelineList;
	}
	
	
public ArrayList getTestRecord(String user_id, int from) throws SQLException {
		
		con = getConnection();
		
		ArrayList<TimelineBean> recordList = new ArrayList<TimelineBean>();
		
			//preparing query
			String timelineQuery = "SELECT history.history_id, history.total_attempted, history.correct_answers, history.wrong_answers, history.score, history.date_time as history_date_time, history.test_id, history.is_public, (SELECT test_name FROM tests as temp_tests WHERE temp_tests.test_id = history.test_id) as test_name, (SELECT sub_catagory_id FROM tests as temp_tests WHERE temp_tests.test_id = history.test_id) as sub_catagory_id, (SELECT sub_catagory_name FROM sub_catagories as temp_sub_catagories WHERE temp_sub_catagories.sub_catagory_id = (SELECT sub_catagory_id FROM tests as temp_tests WHERE temp_tests.test_id = history.test_id)) as sub_catagory_name, (SELECT catagory_id FROM sub_catagories WHERE catagory_id = (SELECT sub_catagory_id FROM tests as temp_tests WHERE temp_tests.test_id = history.test_id)) as catagory_id, (SELECT catagory_name FROM catagories WHERE catagory_id = (SELECT catagory_id FROM sub_catagories WHERE catagory_id = (SELECT sub_catagory_id FROM tests as temp_tests WHERE temp_tests.test_id = history.test_id))) as catagory_name, history.user_id, (SELECT fullname FROM users as temp_users WHERE temp_users.user_id = history.user_id) as fullname, (SELECT username FROM users as temp_users WHERE temp_users.user_id = history.user_id) as username, (SELECT picurl FROM users as temp_users WHERE temp_users.user_id = history.user_id) as picurl FROM test_history as history WHERE history.user_id = ? AND history_id > ? ORDER BY history.date_time DESC LIMIT 15";
			pst = con.prepareStatement(timelineQuery);
			pst.setInt(1, Integer.parseInt(user_id));
			pst.setInt(2, from);
			
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				TimelineBean obj = new TimelineBean();
				
				obj.setHistory_id(rs.getInt("history_id"));
				obj.setTotal_attempted(rs.getInt("total_attempted"));
				obj.setCorrect_answers(rs.getInt("correct_answers"));
				obj.setWrong_answers(rs.getInt("wrong_answers"));
				obj.setScore(rs.getFloat("score"));
				obj.setDate_time(rs.getString("history_date_time"));
				obj.setTest_id(rs.getInt("test_id"));
				obj.setTest_name(rs.getString("test_name"));
				obj.setSub_category_id(rs.getInt("sub_catagory_id"));
				obj.setSub_category_name(rs.getString("sub_catagory_name"));
				obj.setCategory_id(rs.getInt("catagory_id"));
				obj.setCategory_name(rs.getString("catagory_name"));
				obj.setUser_id(rs.getInt("user_id"));
				obj.setUser_name(rs.getString("fullname"));
				obj.setPic_url(rs.getString("picurl"));
				obj.setIs_public(rs.getBoolean("is_public"));
				obj.setUsername(rs.getString("username"));
				recordList.add(obj);
				
//				System.out.println(obj.getUsername());
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
		
		return recordList;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}
}
