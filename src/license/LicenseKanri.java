package license;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import dbconnect.DBManager;
/**
 * 資格情報を管理するクラス
 *
 * @author ArakiYuki
 *
 */

public class LicenseKanri {

	/**
	 * license表の件数を+1して返すメソッド
	 *
	 * @return 件数+1
	 * @throws SQLException
	 */
	public static int licenseMaxId() throws SQLException {
		String sql = "select count(*) +1 from license";
		int maxId = 1;
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con
				.prepareStatement(sql);
		ResultSet result = pstm.executeQuery();

		if (result.next()) {
			maxId = result.getInt(1);
			System.out.println(maxId);
		}
		result.close();
		pstm.close();
		con.close();

		return maxId;
	}


/**
 * 資格を登録するメソッド
 *
 * @param licenseName 資格名
 * @param licenseLank 資格ランク
 * @throws SQLException
 */

	public static void positionInsert(String licenseName, int licenseLank) throws SQLException {

		//positionDown(positionLank);//インサートする前に影響範囲のランクを下げる
		String sql = "insert into license(license_lank,license_name) values(?,?)";
		int maxId = licenseMaxId();
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql);
		pstm.setLong(1, maxId);
		pstm.setString(2, licenseName);
		pstm.executeUpdate();

		pstm.close();
		con.close();
	}



}
