package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseBean {

	private String licenseId;
	private String licenseName;
	private String licenseLank;


	/**
	 * resの内容をセットするコンストラクタ
	 * @param res
	 */
	public LicenseBean(ResultSet res){
		try{
			licenseId = res.getString(1);
			licenseName = res.getString(2);
			licenseLank = res.getString(3);

		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * licenseIdを取得します。
	 * @return licenseId
	 */
	public String getLicenseId() {
	    return licenseId;
	}

	/**
	 * licenseIdを設定します。
	 * @param licenseId licenseId
	 */
	public void setLicenseId(String licenseId) {
	    this.licenseId = licenseId;
	}

	/**
	 * licenseNameを取得します。
	 * @return licenseName
	 */
	public String getLicenseName() {
	    return licenseName;
	}

	/**
	 * licenseNameを設定します。
	 * @param licenseName licenseName
	 */
	public void setLicenseName(String licenseName) {
	    this.licenseName = licenseName;
	}

	/**
	 * licenseLankを取得します。
	 * @return licenseLank
	 */
	public String getLicenseLank() {
	    return licenseLank;
	}

	/**
	 * licenseLankを設定します。
	 * @param licenseLank licenseLank
	 */
	public void setLicenseLank(String licenseLank) {
	    this.licenseLank = licenseLank;
	}




}
