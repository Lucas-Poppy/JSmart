package department;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import benefit.NullCheck;

/**
 * Servlet implementation class DepartmentInsertConfirmServlet
 */
@WebServlet("/DepartmentInsertConfirmServlet")
public class DepartmentInsertConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 部署登録モードを表す定数フィールド
	 */
	private final String DEPT_INSERT_MODE = "dept";
	/**
	 * 課登録モードを表す定数フィールド
	 */
	private final String SECTION_INSERT_MODE = "section";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepartmentInsertConfirmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String insertMode = request.getParameter("submitMethod");

		switch (NullCheck.nullConvert(insertMode)) {
		//部署登録モードのときの処理
		case DEPT_INSERT_MODE:
			String deptName = request.getParameter("deptName");
			

			break;
		//課登録モードのときの処理
		case SECTION_INSERT_MODE:

			break;
		}

	}

}
