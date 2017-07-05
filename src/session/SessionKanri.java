package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * セッションを管理するクラス
 *
 * @author araki
 *
 */
public class SessionKanri {


	/**
	 * セッションを全てRemoveするメソッド
	 * @param request
	 */
	public static void sessionRemoveAll(HttpServletRequest request){

		HttpSession session = request.getSession();
		session.removeAttribute("strDepartmentSectionBeanList");
		session.removeAttribute("strDepartmentBeanList");
		session.removeAttribute("deptName");
		session.removeAttribute("deptId");
		session.removeAttribute("errorInsertDeptName10Word");
		session.removeAttribute("errorInsertSectionName10Word");
		session.removeAttribute("errorInsertDepartmentSelected");
		session.removeAttribute("errorInsertDepartmentExsists");
		session.removeAttribute("errorInsertSectionExsists");
		session.removeAttribute("errorInsertSectionEmpty");
		session.removeAttribute("errorInsertDepartmentEmpty");
		session.removeAttribute("deptInsertResult");
		session.removeAttribute("textBoxDeptName");
		session.removeAttribute("textBoxSectionName");
		session.removeAttribute("strPositionBeanList");
		session.removeAttribute("textBoxPositionName");
		session.removeAttribute("textBoxPositionLank");
		session.removeAttribute("textBoxPositionAllowance");
		session.removeAttribute("errorInsertPositionName10Word");
		session.removeAttribute("errorInsertPositionNameExsists");
		session.removeAttribute("errorInsertPositionEmpty");
		session.removeAttribute("strPositionLankList");
		session.removeAttribute("updatePositionId");
		session.removeAttribute("oldPosition");
		session.removeAttribute("updateOldName");
		session.removeAttribute("updateOldAllowance");
		session.removeAttribute("strEmployeesBeanList");
		session.removeAttribute("deptCheck");
		session.removeAttribute("positionCheck");
		session.removeAttribute("seniorityCheck");
		session.removeAttribute("optionMenuPositionId");
		session.removeAttribute("optionMenuDeptId");
		session.removeAttribute("insertEmployee");
		session.removeAttribute("licenseList");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");




	}

	/**
	 * エラーメッセージのセッションをRemoveするメソッド
	 * @param request
	 */
	public static void sessionRemoveError(HttpServletRequest request){
		HttpSession session = request.getSession();

		session.removeAttribute("errorInsertDeptName10Word");
		session.removeAttribute("errorInsertSectionName10Word");
		session.removeAttribute("errorInsertDepartmentSelected");
		session.removeAttribute("errorInsertDepartmentExsists");
		session.removeAttribute("errorInsertSectionExsists");
		session.removeAttribute("errorInsertSectionEmpty");
		session.removeAttribute("errorInsertDepartmentEmpty");
		session.removeAttribute("errorInsertPositionName10Word");
		session.removeAttribute("errorInsertPositionNameExsists");
		session.removeAttribute("errorInsertPositionEmpty");
	}

}
