package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import sindhi.quiz.daos.beans.OptionBean;
import sindhi.quiz.daos.beans.QuestionBean;
import sindhi.quiz.daos.beans.TimelineBean;

public class FinishTestDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public FinishTestDao() {
		
	}
	
	public ArrayList<QuestionBean> getAttemptedQuestions(Vector attemptedQuestions) throws SQLException {
		
		con = getConnection();
		
		ArrayList<QuestionBean> questions = new ArrayList<QuestionBean>();
		
		for(int i=0; i<attemptedQuestions.size(); i++) {
			
			QuestionBean question = new QuestionBean();
			
			pst = con.prepareStatement("SELECT * FROM questions WHERE question_id = ?");
			System.out.println("Question ID = " + (int)attemptedQuestions.get(i));
//			System.out.println("Vector Size: " + attemptedQuestions.size());
			pst.setInt(1, (int)attemptedQuestions.get(i));
			rs = pst.executeQuery();
			
			if(rs.next()) {
			
				question.setQuestion(rs.getString("question"));
				question.setQuestion_id(rs.getInt("question_id"));
				
				pst.close();
				rs.close();
			
			
			
				ArrayList<OptionBean> options = new ArrayList<OptionBean>();
				
				pst = con.prepareStatement("SELECT * FROM options WHERE question_id = ?");
				pst.setInt(1, question.getQuestion_id());
				rs = pst.executeQuery();
				
				while(rs.next()) {
					OptionBean option = new OptionBean();
					
					option.setIs_correct(rs.getBoolean("is_correct"));
					option.setText(rs.getString("option_text"));
					
					options.add(option);
				}
				
				question.setOptions(options);
				
				questions.add(question);
			
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
		
		return questions;
	}
	
	public TimelineBean saveResult(int totalAttempted, int userId, int testId, Vector selectedOptions) throws SQLException {
		System.out.println("Save result method...");
		
		con = getConnection();
		
		pst = con.prepareStatement("INSERT INTO posts (date_time, post_by_user, post_type) VALUES (NOW(), ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
		pst.setInt(1, userId);
		pst.setString(2, "test");
		pst.executeUpdate();
		
		rs = pst.getGeneratedKeys();
		int post_id = 0;
		while(rs.next()) {
			post_id = rs.getInt(1);
		}
		rs.close();
		
		int correctCount = 0;
		
		for(int i=0; i<selectedOptions.size(); i++) {
			
			pst = con.prepareStatement("SELECT * FROM options WHERE option_id = ? AND is_correct = 1");
			pst.setInt(1, (int)selectedOptions.get(i));
			rs = pst.executeQuery();
			
			if(rs.next()) {
				System.out.println("Option " + i + " is correct!");
				correctCount++;
			}
			
			pst.close();
			rs.close();
			
		}
		
		pst = con.prepareStatement("INSERT INTO test_history (total_attempted, correct_answers, date_time, is_public, test_id, user_id, post_id) VALUES (?,?,NOW(),1,?,?,?)");
		pst.setInt(1, totalAttempted);
		pst.setInt(2, correctCount);
		pst.setInt(3, testId);
		pst.setInt(4, userId);
		pst.setInt(5, post_id);
	
		pst.executeUpdate();
		pst.close();
		
		String query = "SELECT history.history_id, history.total_attempted, history.correct_answers, history.wrong_answers, history.score, history.date_time as history_date_time, history.test_id, history.is_public, (SELECT temp_tests.test_name FROM tests as temp_tests WHERE temp_tests.test_id = history.test_id) as test_name, (SELECT temp_tests.sub_catagory_id FROM tests as temp_tests WHERE temp_tests.test_id = history.test_id) as sub_cat_id, (SELECT temp_sub_catagories.sub_catagory_name FROM sub_catagories as temp_sub_catagories WHERE temp_sub_catagories.sub_catagory_id = sub_cat_id) as sub_catagory_name, (SELECT temp_sub_catagories.catagory_id FROM sub_catagories as temp_sub_catagories WHERE temp_sub_catagories.sub_catagory_id = sub_cat_id) as cat_id, (SELECT temp_catagories.catagory_name FROM catagories as temp_catagories WHERE temp_catagories.catagory_id = cat_id) as catagory_name FROM test_history as history WHERE history.user_id = ? ORDER BY history.history_id DESC LIMIT 1";
		
		pst = con.prepareStatement(query);
		pst.setInt(1, userId);
		rs = pst.executeQuery();
		rs.next();
		
		TimelineBean obj = new TimelineBean();
		
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
		
		return obj;
	
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
