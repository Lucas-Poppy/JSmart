package position;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import beans.PositionBean;
import dbconnect.DBManager;

public class PositionKanri {





	/**
	 * position表の件数を+1して返すメソッド
	 *
	 * @return 件数+1
	 * @throws SQLException
	 */
	public static int positionMaxId() throws SQLException {
		String positionMaxIdSql = "select count(*) +1 from position";
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
	 *新しく役職を登録するメソッド
	 *
	 * @param positionName　役職名
	 * @param positionLank　役職ランク
	 * @param position_allowance　役職手当
	 * @return
	 * @throws SQLException
	 */

	public static void positionInsert(String positionName, int positionLank,
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
		pstm.executeUpdate();

		pstm.close();
		con.close();
	}


/**
 *	入力された役職ランクよりも下にすでに役職がある場合に下の役職ランクを1つ繰り下げるメソッド
 *
 * @param positionLank 役職ランク
 * @throws SQLException
 */
	public static void  positionDown(int positionLank) throws SQLException{
		String positionLankImpactUpdateSql = "update position set position_lank = position_lank + 1 where position_lank >= ? ";

		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(positionLankImpactUpdateSql);

		pstm.setInt(1, positionLank);
		pstm.executeUpdate();
		pstm.close();
		con.close();


	}

	/**
	 * ランクを入れ替えるメソッド
	 * @param positionId 役職を一意に識別するID
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
		pstmLankUp.executeUpdate();
		pstmLankDown.executeUpdate();



		pstmLankUp.close();
		pstmLankDown.close();
		con.close();
	}



	/**
	 * 役職ランクから役職IDを取り出すメソッド
	 * @param positionLank 役職ランクを表す数値 低ければ上位
	 * @return 役職を一意に識別するID
	 * @throws SQLException
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
	 * 役職IDから役職ランクを取り出すメソッド
	 * @param positionId 役職を一意に識別するID
	 * @return 役職ランクを表す数値 低ければ上位
	 * @throws SQLException
	 */

	public static int getPositionLank(int positionId) throws SQLException{
		String getPositionLankSql = "select position_lank from position where position_id = ?";
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(getPositionLankSql);
		pstm.setInt(1,positionId);
		ResultSet result =pstm.executeQuery();
		int PositionLank = 0;

		if(result.next()){
			PositionLank = result.getInt(1);
		}

		return PositionLank;


	}

/**
 * 登録されている役職の情報を全て返すメソッド
 *
 * @return PositionBeanオブジェクト
 * @throws SQLException
 */
	public List<PositionBean> getAllPosition() throws SQLException{
		String getAllPositionSql = "select position_id,position_name,position_lank,position_allowance from position order by 3";
			Connection con=DBManager.getConnection();
			PreparedStatement pstm=(PreparedStatement) con.prepareStatement(getAllPositionSql) ;
			ResultSet result = pstm.executeQuery();
			List<PositionBean> positionList = new ArrayList<PositionBean>();
			while(result.next()){
				PositionBean bean = new PositionBean(result);
				positionList.add(bean);
				}
			result.close();
			pstm.close();
			con.close();

			return positionList;
	}

/**
 * 入力された役職名が存在するかどうか確認するメソッド
 * @param positionName 役職名
 * @return 存在すればtrueを存在しなければfalseを返す
 */
	public static boolean positionExsists(String positionName) throws SQLException{
		String positionExsistsSql ="select * from position where position_name = ?";
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(positionExsistsSql) ;
		pstm.setString(1, positionName);
		ResultSet result = pstm.executeQuery();

		boolean exsists = false;

		if(result.next()){
			exsists=true;
		}
		result.close();
		pstm.close();
		con.close();

		return exsists;

	}


	/**
	 * 役職IDで指定された役職の登録情報を変更するメソッド
	 *
	 * @param positionName 新しく指定された役職の名前
	 * @param positionAllowance 新しく指定された役職手当の金額
	 * @param positionId 変更する役職を指定する一意に識別するID
	 * @throws SQLException
	 */

	public static void positionUpdate(String positionName,int positionAllowance,int positionId) throws SQLException{
		String positionUpdateSql = "update position set position_name=?,position_allowance=? where position_id=?";
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(positionUpdateSql) ;
		pstm.setString(1, positionName);
		pstm.setInt(2, positionAllowance);
		pstm.setInt(3, positionId);

		pstm.executeUpdate();
		pstm.close();
		con.close();

	}


}
