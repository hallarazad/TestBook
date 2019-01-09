package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.text.html.Option;

import sindhi.quiz.daos.beans.OptionBean;

public class QuestionsDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public QuestionsDao() {
		
	}
	
	public void addQuestion(int testId, String question, ArrayList<OptionBean> options) throws SQLException {
		con = getConnection();
		
		String query = "INSERT INTO questions (question, test_id) VALUES (?, ?)";
		pst = con.prepareStatement(query);
		pst.setString(1, question);
		pst.setInt(2, testId);
		pst.executeUpdate();
		
		try {
			if (pst != null) {
				pst.close();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		pst = con.prepareStatement("SELECT question_id FROM questions ORDER BY question_id DESC LIMIT 1");
		//pst.setString(1, question);
		rs = pst.executeQuery();
		rs.next();
		int questionId = rs.getInt("question_id");
		
		System.out.println("Question ID = " + questionId);
		
		pst.close();
		
		for(int i=0; i<options.size(); i++) {
			
			OptionBean option = new OptionBean();
			option = options.get(i);
			
			pst = con.prepareStatement("INSERT INTO options (option_text, is_correct, question_id) VALUES (?, ?, ?)");
			pst.setString(1, option.getText());
			pst.setBoolean(2, option.isIs_selected());
			pst.setInt(3, questionId);
			pst.executeUpdate();
			
			pst.close();
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
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
