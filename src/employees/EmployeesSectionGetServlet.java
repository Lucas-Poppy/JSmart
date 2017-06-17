package employees;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.OptionMenuCreate;
import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class EmployeesSectionGetServlet
 */
@WebServlet("/EmployeesSectionGetServlet")
public class EmployeesSectionGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesSectionGetServlet() {
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

		String deptId = request.getParameter("deptId");
		String sectionOptionMenu = OptionMenuCreate.sectionOptionMenuCreate(deptId);

		response.setContentType("text/html; charset=utf-8");
		System.out.println("ajax使用");
		PrintWriter out = response.getWriter();
	    out.println(JSON.encode(sectionOptionMenu));
	}

}
