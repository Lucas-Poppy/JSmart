package department;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DepartmentSectionBean;
import benefit.NullCheck;
import common.OptionMenuCreate;

/**
 * 部署課登録の画面を生成するサーブレットクラス
 *
 * Servlet implementation class DepartmentInsertTopServlet
 */
@WebServlet("/DepartmentInsertTopServlet")
public class DepartmentInsertTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepartmentInsertTopServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String deptId =NullCheck.nullConvert((String) session.getAttribute("optionMenuDeptId"));

		/**
		 * 部署と課の一覧の作成
		 */
		List<DepartmentSectionBean> departmentSectionBeanList = new ArrayList<DepartmentSectionBean>();
		DepartmentSectionKanri dsKanri = new DepartmentSectionKanri();
		try {
			departmentSectionBeanList = dsKanri.allDeptSectionSearch();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println(e);
		}

		StringBuilder sb = new StringBuilder(300);
		String ka = "課";
		for (int i = 0; i < departmentSectionBeanList.size(); i++) {

			if(departmentSectionBeanList.get(i).getSectionName()==null||departmentSectionBeanList.get(i).getSectionName().equals("")){
				ka="";
			}else{
				ka="課";
			}
			sb.append("<tr><td>" + departmentSectionBeanList.get(i).getDeptName() + "部</td>");
			sb.append("<td>" + NullCheck.nullConvert(departmentSectionBeanList.get(i).getSectionName()) +ka+ "</td></tr>");
		}
		String strDepartmentSectionBeanList = sb.toString();

		session.setAttribute("strDepartmentSectionBeanList", strDepartmentSectionBeanList);
		/**
		 * 部署と課の一覧の作成の終了
		 */

		/**
		 * 部のoptionメニューを作成
		 */
		String strDepartmentBeanList = OptionMenuCreate.deptOptionMenuCreate(deptId);
		/**
		 * 部のoptionメニューを作成の終了
		 */

		session.setAttribute("strDepartmentBeanList", strDepartmentBeanList);



		RequestDispatcher dispatcher = request.getRequestDispatcher("department/department_insert.jsp");
		dispatcher.forward(request, response);

	}

}
