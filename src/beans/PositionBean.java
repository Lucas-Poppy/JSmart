package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionBean {

	private String positionId;
	private String positionName;
	private String positionLank;
	private String positionAllowance;//役職手当

	public PositionBean(ResultSet res){
		try{
			positionId = res.getString(1);
			positionName = res.getString(2);
			positionLank = res.getString(3);
			positionAllowance = res.getString(4);


		}catch(SQLException e){
			e.printStackTrace();
		}
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
	 * positionLankを取得します。
	 * @return positionLank
	 */
	public String getPositionLank() {
	    return positionLank;
	}

	/**
	 * positionLankを設定します。
	 * @param positionLank positionLank
	 */
	public void setPositionLank(String positionLank) {
	    this.positionLank = positionLank;
	}

	/**
	 * positionAllowanceを取得します。
	 * @return positionAllowance
	 */
	public String getPositionAllowance() {
	    return positionAllowance;
	}

	/**
	 * positionAllowanceを設定します。
	 * @param positionAllowance positionAllowance
	 */
	public void setPositionAllowance(String positionAllowance) {
	    this.positionAllowance = positionAllowance;
	}
}
