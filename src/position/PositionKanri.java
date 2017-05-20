package position;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.PositionBean;

import com.mysql.jdbc.PreparedStatement;

import dbconnect.DBManager;

public class PositionKanri {

	private static String positionMaxIdSql = "select count(*) +1 from position";

	private static String positionUpdateSql = "update position set position_name=?,position_allowance=? where position_id=?";



	/**
	 * position表の件数を+1して返すメソッド
	 *
	 * @return 件数+1
	 * @throws SQLException
	 */
	public static int positionMaxId() throws SQLException {
		int maxId = 1;
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con
				.prepareStatement(positionMaxIdSql);
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
	 *
	 * @param positionName
	 * @param positionLank
	 * @param position_allowance
	 * @return
	 * @throws SQLException
	 */

	public static int positionInsert(String positionName, int positionLank,
			String position_allowance) throws SQLException {


		positionDown(positionLank);//インサートする前に影響範囲のランクを下げる

		String positionInsertSql = "insert into position (position_id,position_name,position_lank,position_allowance) values(?,?,?,?)";
		int maxId = positionMaxId();
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(positionInsertSql);
		pstm.setLong(1, maxId);
		pstm.setString(2, positionName);
		pstm.setInt(3, positionLank);
		pstm.setString(4, position_allowance);
		int insertNumber = pstm.executeUpdate();

		pstm.close();
		con.close();

		return insertNumber;
	}
/**
 *
 * @param positionLank
 * @throws SQLException
 */
	public static void  positionDown(int positionLank) throws SQLException{
		String positionLankImpactUpdateSql = "update position set position_lank = position_lank + 1 where position_lank <= ? ";

		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(positionLankImpactUpdateSql);

		pstm.setInt(1, positionLank);
		pstm.executeUpdate();
		pstm.close();
		con.close();


	}

	/**
	 * ランクを入れ替えるメソッド
	 * @param positionId
	 * @param changeMethod 指定された役職の役職ランクを下げるか上げるかを判断する文字列 "UP"or"DOWM"
	 * @throws SQLException
	 */
	private final static String DOWN_METHOD = "DOWN";
	private final static String UP_METHOD = "UP";

	public static void positionLankChange(int positionId,String changeMethod) throws SQLException{
		String positionUpLankUpdateSql = "update position set position_lank = position_lank-1 where position_id = ?";
		String positionDownLankUpdateSql = "update position set position_lank = position_lank+1 where position_id = ?";

		Connection con = DBManager.getConnection();
		PreparedStatement pstmLankUp = (PreparedStatement) con.prepareStatement(positionUpLankUpdateSql);
		PreparedStatement pstmLankDown = (PreparedStatement) con.prepareStatement(positionDownLankUpdateSql);
		int positionLank = getPositionLank(positionId);



		switch (changeMethod){
			case DOWN_METHOD:
				int downPositionId = getPositionId(positionLank+1);

				pstmLankUp.setInt(1,downPositionId);
				pstmLankDown.setInt(1,positionId);
				break;
			case UP_METHOD:
				int upPositionId = getPositionId(positionLank-1);

				pstmLankUp.setInt(1,positionId);
				pstmLankDown.setInt(1,upPositionId);
				break;
		}


		pstmLankUp.close();
		pstmLankDown.close();
		con.close();
	}


	/**
	 *役職ランクから役職IDを取り出すメソッド
	 *
	 *
	 */

	public static int getPositionId(int positionLank) throws SQLException{
		String getPositionIdSql = "select position_id from position where position_lank = ?";
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(getPositionIdSql);
		pstm.setInt(1,positionLank);
		ResultSet result =pstm.executeQuery();
		int PositionId = 0;

		if(result.next()){
			PositionId = result.getInt(1);
		}

		return PositionId;


	}


	/**
	 *役職IDから役職ランクを取り出すメソッド
	 *
	 *
	 */

	public static int getPositionLank(int positionId) throws SQLException{
		String getPositionLankSql = "select position_lank from position where position_id = ?";
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(getPositionLankSql);
		pstm.setInt(1,positionId);
		ResultSet result =pstm.executeQuery();
		int PositionLank = 0;

		if(result.next()){
			PositionLank = result.getInt(3);
		}

		return PositionLank;


	}


	public List<PositionBean> allPosition() throws SQLException{

	}



}
