


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {

	public Connection getConnection() {
		Connection conn = null;
		try {
			String userName = "root";
			String password = "";
			String url = "jdbc:mysql://localhost/money";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, userName, password);
			/*
			 * DriverManager : là một class quản lý danh sách các Driver (database drivers). ... 
			 * Thông thường ứng dụng Java sẽ không giao tiếp trực tiếp với class này mà thông 
			 * qua DriverManager. Connection : 
			 * là một interface cung cấp tất cả các method cần thiết cho việc giao tiếp với database
			 */
			System.out.println("Ok");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}


}