package employees;

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
 * Servlet implementation class EmployeesUpdateResultServlet
 */
@WebServlet("/EmployeesUpdateResultServlet")
public class EmployeesUpdateResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesUpdateResultServlet() {
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
		String empId = request.getParameter("empId");

		String seiKanzi = request.getParameter("seiKanzi");
		String meiKanzi = request.getParameter("meiKanzi");
		String seiKana = request.getParameter("seiKana");
		String meiKana = request.getParameter("meiKana");



		String dept[] = request.getParameter("dept").split("[,]", 0);
		String deptId = dept[0];
		String deptName = dept[1];

		String section[] = request.getParameter("section").split("[,]", 0);
		String sectionId = section[0];
		String sectionName = section[1];

		String position[] = request.getParameter("position").split("[,]", 0);
		String positionId = position[0];
		String positionName = position[1];


		String sex = request.getParameter("sex");
		String addressNumber = request.getParameter("addressNumber");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String phoneNumber = request.getParameter("phoneNumber");
		String dateOfEntering = request.getParameter("dateOfEntering");
		String dateOfRetire = request.getParameter("dateOfRetire");


		String nameKanzi = seiKanzi+" "+meiKanzi;
		String nameKana = seiKana+" "+meiKana;
		String address = state+","+city+","+address1+","+address2;
		String dateOfEnteringDate =null;
		String dateOfRetireDate = null;

		if(!dateOfEntering.equals("")){
			dateOfEnteringDate = dateOfEntering+"-01";
		}
		if(!dateOfRetire.equals("")){
			dateOfRetireDate = dateOfRetire+"-01";
		}


		try {
			EmployeesKanri.updateEmployees(empId,nameKanzi, nameKana, sex, addressNumber, address, phoneNumber, deptId, sectionId, positionId, dateOfEnteringDate,dateOfRetireDate);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeesSearchTopServlet");
		dispatcher.forward(request, response);

	}

}
