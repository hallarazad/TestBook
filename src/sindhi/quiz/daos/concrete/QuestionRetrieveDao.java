package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import sindhi.quiz.daos.beans.QuestionBean;

public class QuestionRetrieveDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public QuestionRetrieveDao() {
		
	}
	
	public QuestionBean getQuestion(int testId, Vector attemptedQuestionIds) throws SQLException {
		con = getConnection();
		
		String query = "SELECT * FROM questions WHERE test_id = ? AND question_id NOT IN (";
		query += attemptedQuestionIds.get(0);
		
		System.out.println("First element in vector: " + attemptedQuestionIds.get(0));
		
		for(int i=1; i<attemptedQuestionIds.size(); i++) {
			System.out.println("Vector For Loop: " + attemptedQuestionIds.get(i));
			query += "," + attemptedQuestionIds.get(i);
		}
		
		query += ") ORDER BY RAND() LIMIT 1";
		
		System.out.println("Query = " + query);
		
		QuestionBean obj = new QuestionBean();
		
		try {
		
		pst = con.prepareStatement(query);
		pst.setInt(1, testId);
		rs = pst.executeQuery();
		
		System.out.println("Question not found.. test = " + testId);
		
		if(rs.next()) {
			
			System.out.println("Question found..");
			
			System.out.println("Question: " + rs.getString("question"));
		
			obj = new QuestionBean();
			
			obj.setQuestion(rs.getString("question"));
			obj.setQuestion_id(rs.getInt("question_id"));
		}
		
		}catch(Exception e) {
			e.printStackTrace();
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
		
		return obj;
		
	}
	
	public QuestionBean getFirstQuestion(int testId) throws SQLException {
		con = getConnection();
		
		pst = con.prepareStatement("SELECT * FROM questions WHERE test_id = ? ORDER BY RAND() LIMIT 1");
		pst.setInt(1, testId);
		rs = pst.executeQuery();
		
		rs.next();
		
		QuestionBean obj = new QuestionBean();
		
		obj.setQuestion(rs.getString("question"));
		obj.setQuestion_id(rs.getInt("question_id"));
		
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
	
	public String getOptions(int questionId) throws SQLException {
		con = getConnection();
		
		pst = con.prepareStatement("SELECT * FROM options WHERE question_id = ? ORDER BY RAND()");
		pst.setInt(1, questionId);
		rs = pst.executeQuery();
		
		String options = "";
		
		while(rs.next()) {
			options += "<div class=\"form-group w-icon\">";
				options += "<label class=\"radio\">";
					options += "<input type=\"radio\" value=\""+rs.getInt("option_id")+"\" name=\"selectedOption\" class=\"px required\">";
					options += "<span class=\"lbl\">"+rs.getString("option_text")+"</span>";
				options += "</label>";
			options += "</div>";
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
		
		return options;
	}
	
	
	
	
	
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
