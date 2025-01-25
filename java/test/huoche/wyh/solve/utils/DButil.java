package test.huoche.wyh.solve.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DButil {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private DButil() {
		
	}
	
	private static DButil instance = new DButil();
	public static  DButil getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		String url="jdbc:mysql://localhost:3306/trainticket?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String userName="root";
//		String passWord=",ki89ol.";
		String passWord="123456";

		try {
			return DriverManager.getConnection(url, userName, passWord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	
	public void colseConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
