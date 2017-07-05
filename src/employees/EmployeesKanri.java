package employees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

import beans.EmployeesBean;
import benefit.IntegerCheck;
import dbconnect.DBManager;

public class EmployeesKanri {

	/**
	 * 登録されている社員の情報をすべて返すメソッド
	 *
	 * @return EmployeesBeanオブジェクト
	 * @throws SQLException
	 */
	public List<EmployeesBean> employeesSearch(PreparedStatement pstm) throws SQLException {

		Connection con = DBManager.getConnection();
		ResultSet result = pstm.executeQuery();
		List<EmployeesBean> employeesBeanList = new ArrayList<EmployeesBean>();
		while (result.next()) {
			EmployeesBean bean = new EmployeesBean(result);
			employeesBeanList.add(bean);
		}
		result.close();
		pstm.close();
		con.close();

		return employeesBeanList;
	}

	/**
	 * 社員検索のSQLを生成するメソッド
	 *
	 * @param page
	 *            ページングの何ページ目かを表す数値
	 * @param where
	 *            where句を表す文字列を格納したデータ
	 * @return 生成されたSQL
	 * @throws SQLException
	 */
	public static PreparedStatement sqlCreate(int page, List<Map<String, String>> where) throws SQLException {

		StringBuilder sb = new StringBuilder(500);
		sb.append("select * from employees_list_view ");
		sb.append("where 1=1 ");
		for (int i = 0; i < where.size(); i++) {
			sb.append("and " + where.get(i).get("Where列") + " ");
		}
		sb.append("order by 1 ");
		sb.append("LIMIT ?,10");
		String sql = sb.toString();

		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql);
		int limit = 1;
		for (int i = 0; i < where.size(); i++) {
			pstm.setInt(i + 1, IntegerCheck.convertInteger(where.get(i).get("Where値")));
			limit++;
		}
		pstm.setInt(limit, page);
		System.out.println(pstm);

		return pstm;

	}

	/**
	 *
	 * @param dept
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> sqlHashMapDept(String[] dept) throws SQLException {

		Map<String, String> map = new HashMap<String, String>();

		String deptId = dept[0];
		map = new HashMap<String, String>();
		map.put("Where列", "dept_id= ?");
		map.put("Where値", deptId + "");

		return map;
	}

	/**
	 * 社員登録するメソッド
	 *
	 * @param nameKanzi
	 * @param nameKana
	 * @param birth
	 * @param sex
	 * @param addressNumber
	 * @param address
	 * @param phoneNumber
	 * @param deptId
	 * @param sectionId
	 * @param positionId
	 * @param dateOfEntering
	 * @throws SQLException
	 */

	//mod Araki Yuki 17/06/30 begin
//	public static void insertEmployees(String nameKanzi, String nameKana, String birth, String sex,
//			String addressNumber, String address, String phoneNumber, String deptId, String sectionId,
//			String positionId, String dateOfEntering) throws SQLException {
	public static void insertEmployees(String nameKanzi, String nameKana, String birth, String sex,
	String addressNumber, String address, String phoneNumber, String deptId, String sectionId,
	String positionId, String dateOfEntering,String licenseId) throws SQLException {

		String insertSql = "insert into employees(emp_name_kanzi,emp_name_kana,emp_birth,emp_sex,"
				+ "emp_address_number,emp_address,emp_phone_number,dept_id,section_id,position_id,"
				+ "date_of_entering) " + " values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(insertSql,java.sql.Statement.RETURN_GENERATED_KEYS);
		pstm.setString(1, nameKanzi);
		pstm.setString(2, nameKana);
		pstm.setString(3, birth);
		pstm.setString(4, sex);
		pstm.setString(5, addressNumber);
		pstm.setString(6, address);
		pstm.setString(7, phoneNumber);
		pstm.setString(8, deptId);
		pstm.setString(9, sectionId);
		pstm.setString(10, positionId);
		pstm.setString(11, dateOfEntering);
		int resultNumber = pstm.executeUpdate();

		if(resultNumber==1){
			//オートインクリメントのIDを取得
			ResultSet rs = pstm.getGeneratedKeys();
			int primaryKey = 0;
			if(rs.next()){
				primaryKey = rs.getInt(1);
			}
			insertEmployeeLicense(primaryKey,licenseId);
		}

		pstm.close();
		con.close();
	//mod Araki Yuki 17/06/30 end
	}


	//add Araki Yuki 17/06/30 begin
	public static void insertEmployeeLicense(int empId,String licenseId)throws SQLException {
		String sql = "insert into employees_license(emp_id,license_id) values(?,?)";
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql);
		pstm.setInt(1, empId);
		pstm.setString(2, licenseId);
		pstm.executeUpdate();
		pstm.close();
		con.close();

	}
	//add Araki Yuki 17/06/30 end

	/**
	 * 社員情報の変更をするメソッド
	 * @param empId
	 * @param nameKanzi
	 * @param nameKana
	 * @param sex
	 * @param addressNumber
	 * @param address
	 * @param phoneNumber
	 * @param deptId
	 * @param sectionId
	 * @param positionId
	 * @param dateOfEntering
	 * @param dateOfRetire
	 * @throws SQLException
	 */

	public static void updateEmployees(String empId, String nameKanzi, String nameKana, String sex,
			String addressNumber, String address, String phoneNumber, String deptId, String sectionId,
			String positionId, String dateOfEntering, String dateOfRetire) throws SQLException {
		String insertSql = "update employees SET emp_name_kanzi=?,emp_name_kana=?,emp_sex=?,"
				+ " emp_address_number=?,emp_address=?,emp_phone_number=?,dept_id=?,section_id=?,position_id=?,"
				+ " date_of_entering=?,date_of_retire=? " + " where emp_id=?";
		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(insertSql);
		pstm.setString(1, nameKanzi);
		pstm.setString(2, nameKana);
		pstm.setString(3, sex);
		pstm.setString(4, addressNumber);
		pstm.setString(5, address);
		pstm.setString(6, phoneNumber);
		pstm.setString(7, deptId);
		pstm.setString(8, sectionId);
		pstm.setString(9, positionId);
		pstm.setString(10, dateOfEntering);
		pstm.setString(11, dateOfRetire);
		pstm.setString(12, empId);
		pstm.executeUpdate();

		pstm.close();
		con.close();

	}

	/**
	 * 登録されている社員の情報を主キーで検索するメソッド
	 *
	 * @return EmployeesBeanオブジェクト
	 * @throws SQLException
	 */
	public EmployeesBean employeesSearchPK(String empId) throws SQLException {

		String sql = "select * from employees_list_view where emp_id=?";

		Connection con = DBManager.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql);
		pstm.setString(1, empId);
		ResultSet result = pstm.executeQuery();
		EmployeesBean bean = null;
		if (result.next()) {
			bean = new EmployeesBean(result);
		}
		result.close();
		pstm.close();
		con.close();

		return bean;
	}

}
