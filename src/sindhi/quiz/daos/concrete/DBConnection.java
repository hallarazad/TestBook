package sindhi.quiz.daos.concrete;
import java.sql.*;

public class DBConnection {
	String driverClassName = "com.mysql.jdbc.Driver";
	
	
	
	// Heroku
	String connectionUrl = "jdbc:mysql://eu-cdbr-west-02.cleardb.net/heroku_17e7d75201deb87";
	String unicode = "?useUnicode=yes&characterEncoding=UTF-8&reconnect=true";
	String dbuser = "bc377c80fc6f77";
	String dbpwd = "57642e0f";
	
	// Local
	/*
	 * String connectionUrl = "jdbc:mysql://localhost:3306/testbook"; String unicode
	 * = "?useUnicode=yes&characterEncoding=UTF-8&reconnect=true"; String dbuser =
	 * "root"; String dbpwd = "";
	 */
	
	private static DBConnection dbConnection = null;
	
	private DBConnection() {
		
		try {
			Class.forName(driverClassName);
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
//		System.out.println("in get connection");
		Connection con = null;
		con = DriverManager.getConnection(connectionUrl+unicode,dbuser, dbpwd);
		return con;
		
	}
	
	public static DBConnection getInstance() {
//		System.out.println("in get instance");
		
		if(dbConnection == null) {
			dbConnection = new DBConnection();
		}
		
		return dbConnection;
	}
}