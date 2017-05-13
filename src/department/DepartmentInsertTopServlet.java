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

/**
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
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		List<DepartmentSectionBean> departmentSectionBeanList = new ArrayList<DepartmentSectionBean>();
		DepartmentSectionKanri dsKanri = new DepartmentSectionKanri();
		try {
			departmentSectionBeanList = dsKanri.allDeptSectionSearch();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println(e);
		}

		StringBuilder sb = new StringBuilder(300);
		for(int i=0;i<departmentSectionBeanList.size();i++){
			sb.append("<tr><td>"+departmentSectionBeanList.get(i).getDeptName()+"</td>");
			sb.append("<td>"+departmentSectionBeanList.get(i).getSectionName()+"</td></tr>");
		}
		String strDepartmentSectionBeanList = sb.toString();
		session.setAttribute("strDepartmentSectionBeanList", strDepartmentSectionBeanList);

		RequestDispatcher dispatcher=request.getRequestDispatcher("department/departmentInsert.jsp");
		dispatcher.forward(request,response);

	}

}
