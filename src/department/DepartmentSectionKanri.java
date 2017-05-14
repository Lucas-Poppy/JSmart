package department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.DepartmentBean;
import beans.DepartmentSectionBean;

import com.mysql.jdbc.PreparedStatement;

import dbconnect.DBManager;

public class DepartmentSectionKanri {
	/**
	 *
	 */
	private String departmentSectionAllSql = "select dept_id,dept_name,section_id,section_name from department d left join department_section d_s using(dept_id) left join section s using(section_id) order by dept_id,section_id";

	private String departmentAllSql = "select * from department order by dept_id";

	private static String departmentMaxIdSql = "select count(*) +1 from department";

	private static String departmentInsertSql = "insert into department (dept_id,dept_name) values(?,?)";

	private static String deptNameExsistsSql = "select * from department where dept_name = ?";

	private static String sectionNameExsistsSql = "select * from department_section  join section using(section_id) where section_name = ? and dept_id=?";


	private static String sectionMaxIdSql = "select count(*) +1 from section";


	private static String sectionInsertSql = "insert into section (section_id,section_name) values(?,?)";

	private static String departmentSectionInsertSql = "insert into department_section (dept_id,section_id) values(?,?)";



	/**
	 * 登録されている部署と課の情報を全て返すメソッド
	 *
	 * @return DepartmentSectionBeanオブジェクト
	 * @throws SQLException
	 */
	public List<DepartmentSectionBean> allDeptSectionSearch() throws SQLException{
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(departmentSectionAllSql) ;
		ResultSet result = pstm.executeQuery();
		List<DepartmentSectionBean> departmentSectionList = new ArrayList<DepartmentSectionBean>();
		while(result.next()){
			DepartmentSectionBean bean = new DepartmentSectionBean(result);
			departmentSectionList.add(bean);
			}
		result.close();
		pstm.close();
		con.close();

		return departmentSectionList;
	}


	/**
	 * 登録されている部署の情報を全て返すメソッド
	 * @return DepartmentBeanオブジェクト
	 * @throws SQLException
	 */

	public List<DepartmentBean> allDeptSearch() throws SQLException{
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(departmentAllSql) ;
		ResultSet result = pstm.executeQuery();
		List<DepartmentBean> departmentList = new ArrayList<DepartmentBean>();
		while(result.next()){
			DepartmentBean bean = new DepartmentBean(result);
			departmentList.add(bean);
			}
		result.close();
		pstm.close();
		con.close();

		return departmentList;
	}


	/**
	 * department表の件数を+1して返すメソッド
	 * @return 件数+1
	 * @throws SQLException
	 */
	public static int deptMaxId() throws SQLException{
		int maxId = 1;
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(departmentMaxIdSql);
		ResultSet result = pstm.executeQuery();

		if(result.next()){
			maxId=result.getInt(1);
			System.out.println(maxId);
		}
		result.close();
		pstm.close();
		con.close();

		return maxId;
	}

	/**
	 * department表に新しく部署を登録するメソッド
	 *
	 * @param deptName 新しく登録する部署名
	 * @return 何件新規登録されたかを返す
	 * @throws SQLException
	 */


	public static int deptInsert(String deptName) throws SQLException{
		int maxId = deptMaxId();
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(departmentInsertSql);
		pstm.setLong(1, maxId);
		pstm.setString(2, deptName);
		int insertNumber = pstm.executeUpdate();

		pstm.close();
		con.close();

		return insertNumber;
	}

	/**
	 * 部署名が既に存在しているかどうか確かめるメソッド
	 *
	 * @param deptName 部署名
	 * @return 存在するならfalse 存在しないならtrue
	 * @throws SQLException
	 */

	public static boolean deptNameExsists(String deptName) throws SQLException{
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(deptNameExsistsSql);
		pstm.setString(1, deptName);
		ResultSet result = pstm.executeQuery();

		boolean bool = !result.next();
		result.close();
		pstm.close();
		con.close();

		return bool;

	}




	/**
	 * section表の件数を+1して返すメソッド
	 * @return 件数+1
	 * @throws SQLException
	 */
	public static int sectionMaxId() throws SQLException{
		int maxId = 1;
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(sectionMaxIdSql);
		ResultSet result = pstm.executeQuery();

		if(result.next()){
			maxId=result.getInt(1);
		}

		result.close();
		pstm.close();
		con.close();

		return maxId;
	}


	/**
	 * section表に新しく課を登録するメソッド
	 *
	 * @param sectionName 新しく登録する課名
	 * @param deptId 課の属する部署ID
	 * @throws SQLException
	 */


	public static void sectionInsert(String sectionName,String deptId) throws SQLException{
		int maxId = sectionMaxId();
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(sectionInsertSql);
		pstm.setLong(1, maxId);
		pstm.setString(2, sectionName);
		int insertNumber = pstm.executeUpdate();

		if(insertNumber!=0){
			PreparedStatement pstm2=(PreparedStatement) con.prepareStatement(departmentSectionInsertSql);
			pstm2.setString(1, deptId);
			pstm2.setLong(2, maxId);
			pstm2.executeUpdate();
			pstm2.close();
		}
		pstm.close();
		con.close();
	}


	/**
	 * その部署に課名が既に存在するか確認するメソッド
	 * @param sectionName 課名
	 * @param deptId 部署ID
	 * @return 存在するならfalse 存在しないならtrue
	 * @throws SQLException
	 */

	public static boolean sectionNameExsists(String sectionName,String deptId) throws SQLException{
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(sectionNameExsistsSql);
		pstm.setString(1,sectionName);
		pstm.setString(2, deptId);
		ResultSet result = pstm.executeQuery();

		boolean bool = !result.next();
		result.close();
		pstm.close();
		con.close();
		return bool;

	}





}
