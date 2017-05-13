package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionKanri {

/**
 *
 * @param request
 */
	public void sessionRemoveAll(HttpServletRequest request){

		HttpSession session = request.getSession();
		session.removeAttribute("strDepartmentSectionBeanList");

	}

}
