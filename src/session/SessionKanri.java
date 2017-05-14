package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionKanri {


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
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");
		session.removeAttribute("");




	}

}
