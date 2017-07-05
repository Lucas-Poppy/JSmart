package license;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import beans.LicenseBean;
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

	public static void licenseInsert(String licenseName, String licenseLank) throws SQLException {

		//positionDown(positionLank);//インサートする前に影響範囲のランクを下げる
		String sql = "insert into license(license_id,license_lank,license_name) values(?,?,?)";
		int maxId = licenseMaxId();
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql);
		pstm.setLong(1, maxId);
		pstm.setString(2,licenseLank);
		pstm.setString(3, licenseName);
		pstm.executeUpdate();

		pstm.close();
		con.close();
	}

	/**
	 * 資格情報をすべて取得するメソッド
	 * @return LicenseBeanオブジェクトのリスト
	 * @throws SQLException
	 */
	public List<LicenseBean> getLicenseAll() throws SQLException{
		String sql = "SELECT license_id,license_name,license_lank FROM license order by 3,1";
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(sql) ;
		ResultSet result = pstm.executeQuery();
		List<LicenseBean> licenseList = new ArrayList<LicenseBean>();
		while(result.next()){
			LicenseBean bean = new LicenseBean(result);
			licenseList.add(bean);
		}
		result.close();
		pstm.close();
		con.close();

		return licenseList;
	}


	public String getLicenseAllString() throws SQLException{
		List<LicenseBean> licenseBeanList = new ArrayList<LicenseBean>();
		licenseBeanList = this.getLicenseAll();
		StringBuilder sb = new StringBuilder(300);

		for (int i = 0; i < licenseBeanList.size(); i++) {
			sb.append("<tr><td>"+licenseBeanList.get(i).getLicenseName()+"</td>");
			sb.append("<td style='width:85px;' class='textbox' Align='right' data-id='"+licenseBeanList.get(i).getLicenseId()+"'>"+licenseBeanList.get(i).getLicenseLank()+"</td>");
			sb.append("</tr>");
		}

		String licenseList = sb.toString();
		return licenseList;
	}

	public static void licenseLankUpdate(String licenseId,String licenseLank)throws SQLException{
		String sql = "update license set license_lank=? where license_id=?";
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql);
		pstm.setString(1,licenseLank);
		pstm.setString(2,licenseId);
		pstm.executeUpdate();

		pstm.close();
		con.close();
	}



}
