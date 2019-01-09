package sindhi.quiz.daos.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SampleDao {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public SampleDao() {
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		return conn;
	}

}
