package employees;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import benefit.NullCheck;
import common.OptionMenuCreate;

/**
 * Servlet implementation class EmployeesInsertTopServlet
 */
@WebServlet("/EmployeesInsertTopServlet")
public class EmployeesInsertTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesInsertTopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		OptionMenuCreate optionMenuCreate = new OptionMenuCreate();

		String deptId =NullCheck.nullConvert((String) session.getAttribute("optionMenuDeptId"));
		String positionId =NullCheck.nullConvert((String) session.getAttribute("optionMenuPositionId"));
		//add Araki Yuki 17/06/30 begin
		String licenseId =NullCheck.nullConvert((String) session.getAttribute("optionMenuLicenseId"));
		//add Araki Yuki 17/06/30 end
		/**
		 * 部署のoptionメニューを作成
		 */
		String strDepartmentBeanList = optionMenuCreate.deptOptionMenuCreate(deptId);
		session.setAttribute("strDepartmentBeanList", strDepartmentBeanList);
		/**
		 * 部署のoptionメニューを作成の終了
		 */

		/**
		 *役職のoptionメニューを作成
		 */
		String strPositionBeanList = optionMenuCreate.positionOptionMenuCreate(positionId);
		session.setAttribute("strPositionBeanList", strPositionBeanList);
		/**
		 *役職のoptionメニューを作成の終了
		 */

		//add Araki Yuki 17/06/30 begin
		/**
		 * 資格のoptionメニューの作成
		 */
		String strLicenseBeanList = optionMenuCreate.licenseOptionMenuCreate(positionId);
		session.setAttribute("strLicenseBeanList", strLicenseBeanList);
		/**
		 * 資格のoptionメニューを作成の終了
		 */
		//add Araki Yuki 17/06/30 end
		RequestDispatcher dispatcher = request.getRequestDispatcher("employees/employees_insert.jsp");
		dispatcher.forward(request, response);

	}

}
