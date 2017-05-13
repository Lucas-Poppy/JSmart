package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DpartmentSectionBean {

	private String deptId;
	private String deptName;
	private String sectionId;
	private String sectionName;

	public DpartmentSectionBean(ResultSet res){
		try{
			deptId = res.getString(1);
			deptName = res.getString(2);
			sectionId = res.getString(3);
			sectionName = res.getString(4);
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

	/**
	 * 課IDを返すメソッド
	 * @return 課ID
	 */
	public String getSectionId() {
		return sectionId;
	}
	/**
	 * 課IDをセットするメソッド
	 * @param sectionId 課を一意に識別する番号
	 */
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	/**
	 * 課名を返すメソッド
	 * @return 課名
	 */
	public String getSectionName() {
		return sectionName;
	}
	/**
	 * 課名をセットするメソッド
	 * @param sectionName 課名
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}


}
