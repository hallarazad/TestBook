import java.sql.*;

public class DBConnection {
	String driverClassName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/testbook?useUnicode=yes&characterEncoding=UTF-8";
	String dbuser = "root";
	String dbpwd = "";
	
	private static DBConnection dbConnection = null;
	
	private DBConnection() {
		System.out.println("in constructor");
		try {
			Class.forName(driverClassName);
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
//		System.out.println("in get connection");
		Connection con = null;
		con = DriverManager.getConnection(connectionUrl,dbuser,dbpwd);
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