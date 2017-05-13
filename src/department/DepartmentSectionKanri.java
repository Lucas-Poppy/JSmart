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
	private String departmentSectionAllSql = "select * from department d left join department_section d_s using(dept_id) left join section s using(section_id) order by dept_id,section_id";

	private String departmentAllSql = "select * from department order by dept_id";

	private String departmentMaxIdSql = "select count(*) +1 from department group by dept_id";

	private String departmentUpdateSql = "insert into Order_tbl values(?,sysdate,?)";



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

		return departmentList;
	}


	/**
	 * department表の件数を+1して返すメソッド
	 * @return 件数+1
	 * @throws SQLException
	 */
	public int deptMaxId() throws SQLException{
		int maxId = 1;
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(departmentMaxIdSql);
		ResultSet result = pstm.executeQuery();

		if(result.next()){
			maxId=result.getInt(1);
		}

		return maxId;
	}




	public int deptInsert(String deptName) throws SQLException{
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(departmentUpdateSql);


		return 0;
	}


}
