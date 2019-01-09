package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompetitionDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public CompetitionDao() {
		
	}
	
	public void saveCompetition(int arranged_by_user_id, List participantsList, int testId, int numberOfQuestions) throws SQLException {
		con = getConnection();
		
		pst = con.prepareStatement("INSERT INTO posts (date_time, post_by_user, post_type) VALUES (NOW(), ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
		pst.setInt(1, arranged_by_user_id);
		pst.setString(2, "competition");
		pst.executeUpdate();
		
		rs = pst.getGeneratedKeys();
		int post_id = 0;
		while(rs.next()) {
			post_id = rs.getInt(1);
		}
		rs.close();
		
		pst = con.prepareStatement("INSERT INTO competitions (test_id, arranged_by_user, post_id, num_of_questions) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		pst.setInt(1,testId);
		pst.setInt(2, arranged_by_user_id);
		pst.setInt(3, post_id);
		pst.setInt(4, numberOfQuestions);
		pst.executeUpdate();
		
		rs = pst.getGeneratedKeys();
		int competitionId = 0;
		while(rs.next()) {
			competitionId = rs.getInt(1);
		}
		rs.close();
		
		participantsList.add(arranged_by_user_id);
		
		for(int i=0; i<participantsList.size(); i++) {
			pst = con.prepareStatement("INSERT INTO competition_users (competition_id, user_id, has_attempted) VALUES (?, ?, 0)");
			pst.setInt(1, competitionId);
			pst.setInt(2, (int)participantsList.get(i));
			pst.executeUpdate();
		}
		
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
