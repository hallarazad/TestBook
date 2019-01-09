package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RankDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public RankDao() {
		
	}
	
	public int getRank(int user_id) throws SQLException {
		con = getConnection();
		
		String query = "SELECT user_id, avg(score) as overallScore, count(history_id) as noOfTests FROM test_history WHERE user_id = ? OR user_id IN ((SELECT added_user_id FROM friends WHERE adder_user_id = ?)) OR user_id IN ((SELECT adder_user_id FROM friends WHERE added_user_id = ?)) GROUP BY user_id ORDER BY noOfTests DESC, score DESC";
		System.out.println("RankDao UserID = " + user_id);
		
		pst = con.prepareStatement(query);
		pst.setInt(1, user_id);
		pst.setInt(2, user_id);
		pst.setInt(3, user_id);
		rs = pst.executeQuery();
		int rankCounter = 1;
		
		while(rs.next()) {
			
			if(user_id != rs.getInt("user_id")) {
				rankCounter++;
				continue;
			} else {
				break;
			}
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
		
		return rankCounter;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
