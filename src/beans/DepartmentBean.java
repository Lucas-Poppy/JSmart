package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 部署のBeanクラス
 * @author Araki
 *
 */

public class DepartmentBean {
	private String deptId;
	private String deptName;

	/**
	 * フィールドに値をセットしていくコンストラクタ
	 * @param res
	 */
	public DepartmentBean(ResultSet res){
		try{
			deptId = res.getString(1);
			deptName = res.getString(2);

		}catch(SQLException e){
			e.printStackTrace();
		}
	}


	/**
	 * 部署IDを返すメソッド
	 * @return 部署ID
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * 部署IDをセットするメソッド
	 * @param deptId 部署を一意に識別する番号
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * 部署名を返すメソッド
	 * @return 部署名
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 部署名をセットするメソッド
	 * @param deptName 部署名
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
