package dbconnect;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBManager {
	/* ユーザ名 */
	private static String user = "root";
	/* パスワード */
	private static String pass = "";
	/* サーバ名 */
	private static String servername = "jdbc:mysql://localhost/JV34?useUnicode=true&characterEncoding=utf8";
	/* SID */
	private static String drivername = "com.mysql.jdbc.Driver";

	public static Connection getConnection() {
		try {
			Class.forName(drivername);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection(servername, user, pass);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return conn;
	}
}
