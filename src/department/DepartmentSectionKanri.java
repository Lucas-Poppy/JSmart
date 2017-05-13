package department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.DpartmentSectionBean;

import com.mysql.jdbc.PreparedStatement;

import dbconnect.DBManager;

public class DepartmentSectionKanri {
	/**
	 *
	 */
	private String departmentSectionAllSql = "select * from department d left join department_section d_s using(dept_id) left join section s using(section_id)";


	/**
	 * 登録されている部署と課の情報を全て返すメソッド
	 *
	 * @return DpartmentSectionBeanオブジェクト
	 * @throws SQLException
	 */
	public List<DpartmentSectionBean> allSearch() throws SQLException{
		Connection con=DBManager.getConnection();
		PreparedStatement pstm=(PreparedStatement) con.prepareStatement(departmentSectionAllSql) ;
		ResultSet result = pstm.executeQuery();
		List<DpartmentSectionBean> productslist = new ArrayList<DpartmentSectionBean>();
		while(result.next()){
			DpartmentSectionBean bean = new DpartmentSectionBean(result);
			productslist.add(bean);
			}

		return productslist;
	}
}
