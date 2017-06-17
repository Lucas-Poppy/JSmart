package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * EmployeesBeanクラス
 *
 * @author ohs55018
 *
 */
public class EmployeesBean {

	private String empId;
	private String empNameKanzi;
	private String empNameKana;
	private String empBirth;
	private String empSex;
	private String empAddressNumber;
	private String empAddress;
	private String empPhoneNumber;
	private String deptId;
	private String sectionId;
	private String positionId;
	private String dateOfEntering;
	private String dateOfRetire;
	private String deptName;
	private String sectionName;
	private String positionName;

	/**
	 * データを格納するコンストラクタ
	 * @param res ResultSet
	 */


	public EmployeesBean(ResultSet res){
		try {
			empId = res.getString(1);
			empNameKanzi = res.getString(2);
			empNameKana = res.getString(3);
			empBirth = res.getString(4);
			empSex = res.getString(5);
			setEmpAddressNumber(res.getString(6));
			empAddress = res.getString(7);
			empPhoneNumber = res.getString(8);
			deptId = res.getString(9);
			sectionId = res.getString(10);
			positionId = res.getString(11);
			dateOfEntering = res.getString(12);
			dateOfRetire = res.getString(13);
			deptName = res.getString(14);
			sectionName = res.getString(15);
			positionName = res.getString(16);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}



	/**
	 * empIdを取得します。
	 * @return empId
	 */
	public String getEmpId() {
	    return empId;
	}



	/**
	 * empIdを設定します。
	 * @param empId empId
	 */
	public void setEmpId(String empId) {
	    this.empId = empId;
	}



	/**
	 * empSeiを取得します。
	 * @return empSei
	 */
	public String getEmpNameKanzi() {
	    return empNameKanzi;
	}



	/**
	 * empSeiを設定します。
	 * @param empSei empSei
	 */
	public void setEmpNameKanzi(String empSei) {
	    this.empNameKanzi = empSei;
	}



	/**
	 * empMeiを取得します。
	 * @return empMei
	 */
	public String getEmpNameKana() {
	    return empNameKana;
	}



	/**
	 * empMeiを設定します。
	 * @param empMei empMei
	 */
	public void setEmpNameKana(String empMei) {
	    this.empNameKana = empMei;
	}



	/**
	 * empBirthを取得します。
	 * @return empBirth
	 */
	public String getEmpBirth() {
	    return empBirth;
	}



	/**
	 * empBirthを設定します。
	 * @param empBirth empBirth
	 */
	public void setEmpBirth(String empBirth) {
	    this.empBirth = empBirth;
	}



	/**
	 * empSexを取得します。
	 * @return empSex
	 */
	public String getEmpSex() {
	    return empSex;
	}



	/**
	 * empSexを設定します。
	 * @param empSex empSex
	 */
	public void setEmpSex(String empSex) {
	    this.empSex = empSex;
	}



	/**
	 * empAddressを取得します。
	 * @return empAddress
	 */
	public String getEmpAddress() {
	    return empAddress;
	}



	/**
	 * empAddressを設定します。
	 * @param empAddress empAddress
	 */
	public void setEmpAddress(String empAddress) {
	    this.empAddress = empAddress;
	}



	/**
	 * empPhoneNumberを取得します。
	 * @return empPhoneNumber
	 */
	public String getEmpPhoneNumber() {
	    return empPhoneNumber;
	}



	/**
	 * empPhoneNumberを設定します。
	 * @param empPhoneNumber empPhoneNumber
	 */
	public void setEmpPhoneNumber(String empPhoneNumber) {
	    this.empPhoneNumber = empPhoneNumber;
	}



	/**
	 * deptIdを取得します。
	 * @return deptId
	 */
	public String getDeptId() {
	    return deptId;
	}



	/**
	 * deptIdを設定します。
	 * @param deptId deptId
	 */
	public void setDeptId(String deptId) {
	    this.deptId = deptId;
	}



	/**
	 * sectionIdを取得します。
	 * @return sectionId
	 */
	public String getSectionId() {
	    return sectionId;
	}



	/**
	 * sectionIdを設定します。
	 * @param sectionId sectionId
	 */
	public void setSectionId(String sectionId) {
	    this.sectionId = sectionId;
	}



	/**
	 * positionIdを取得します。
	 * @return positionId
	 */
	public String getPositionId() {
	    return positionId;
	}



	/**
	 * positionIdを設定します。
	 * @param positionId positionId
	 */
	public void setPositionId(String positionId) {
	    this.positionId = positionId;
	}



	/**
	 * dateOfEnteringを取得します。
	 * @return dateOfEntering
	 */
	public String getDateOfEntering() {
	    return dateOfEntering;
	}



	/**
	 * dateOfEnteringを設定します。
	 * @param dateOfEntering dateOfEntering
	 */
	public void setDateOfEntering(String dateOfEntering) {
	    this.dateOfEntering = dateOfEntering;
	}



	/**
	 * dateOfRetireを取得します。
	 * @return dateOfRetire
	 */
	public String getDateOfRetire() {
	    return dateOfRetire;
	}



	/**
	 * dateOfRetireを設定します。
	 * @param dateOfRetire dateOfRetire
	 */
	public void setDateOfRetire(String dateOfRetire) {
	    this.dateOfRetire = dateOfRetire;
	}



	/**
	 * deptNameを取得します。
	 * @return deptName
	 */
	public String getDeptName() {
	    return deptName;
	}



	/**
	 * deptNameを設定します。
	 * @param deptName deptName
	 */
	public void setDeptName(String deptName) {
	    this.deptName = deptName;
	}



	/**
	 * sectionNameを取得します。
	 * @return sectionName
	 */
	public String getSectionName() {
	    return sectionName;
	}



	/**
	 * sectionNameを設定します。
	 * @param sectionName sectionName
	 */
	public void setSectionName(String sectionName) {
	    this.sectionName = sectionName;
	}



	/**
	 * positionNameを取得します。
	 * @return positionName
	 */
	public String getPositionName() {
	    return positionName;
	}



	/**
	 * positionNameを設定します。
	 * @param positionName positionName
	 */
	public void setPositionName(String positionName) {
	    this.positionName = positionName;
	}

	/**
	 * empAddressNumberを取得します。
	 * @return empAddressNumber
	 */
	public String getEmpAddressNumber() {
		return empAddressNumber;
	}

	/**
	 * empAddressNumberを設定します。
	 * @param empAddressNumber empAddressNumber
	 */

	public void setEmpAddressNumber(String empAddressNumber) {
		this.empAddressNumber = empAddressNumber;
	}

}
