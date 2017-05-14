package department;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DepartmentInsertResultServlet
 */
@WebServlet("/DepartmentInsertResultServlet")
public class DepartmentInsertResultServlet extends HttpServlet {
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
    public DepartmentInsertResultServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String insertMode = (String)session.getAttribute("insertMode");
		String deptName = (String)session.getAttribute("deptName");

		switch(insertMode){

			case DEPT_INSERT_MODE:
				try {
					DepartmentSectionKanri.deptInsert(deptName);
					session.setAttribute("deptInsertResult",deptName+"部を登録しました！");
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}



				break;
			case SECTION_INSERT_MODE:
				String sectionName =(String)session.getAttribute("sectionName");
				String deptId = (String)session.getAttribute("deptId");
				try {
					DepartmentSectionKanri.sectionInsert(sectionName,deptId);
					session.setAttribute("deptInsertResult",deptName+"部に"+sectionName+"課を登録しました！");
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}


				break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("department/departmentInsertResult.jsp");
		dispatcher.forward(request, response);
	}

}
