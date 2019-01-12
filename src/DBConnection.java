import java.sql.*;

public class DBConnection {
	String driverClassName = "com.mysql.jdbc.Driver";
	
	// Use this for local connection
	//String connectionUrl = "jdbc:mysql://localhost:3306/testbook?useUnicode=yes&characterEncoding=UTF-8";
	
	// Use this for Heroku connection
	String connectionUrl = "jdbc:mysql://eu-cdbr-west-02.cleardb.net/heroku_17e7d75201deb87?reconnect=true&useUnicode=yes&characterEncoding=UTF-8";
	
	//String dbuser = "root";
	String dbuser = "bc377c80fc6f77";
	
	//String dbpwd = "";
	String dbpwd = "57642e0f";
	
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