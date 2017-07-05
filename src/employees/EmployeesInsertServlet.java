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
 * Servlet implementation class EmployeesInsertServlet
 */
@WebServlet("/EmployeesInsertServlet")
public class EmployeesInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesInsertServlet() {
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

		//add Araki Yuki 17/06/30 begin
		String license[] = request.getParameter("license").split("[,]", 0);
		String licenseId = license[0];
		String licenseName = license[1];
		//add Araki Yuki 17/06/30 end

		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		String addressNumber = request.getParameter("addressNumber");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String phoneNumber = request.getParameter("phoneNumber");
		String dateOfEntering = request.getParameter("dateOfEntering");

		String nameKanzi = seiKanzi+" "+meiKanzi;
		String nameKana = seiKana+" "+meiKana;
		String address = state+","+city+","+address1+","+address2;
		String dateOfEnteringDate = dateOfEntering+"-01";


		try {
			//mod Araki Yuki 17/06/30 begin
			//EmployeesKanri.insertEmployees(nameKanzi, nameKana, birth, sex, addressNumber, address, phoneNumber, deptId, sectionId, positionId, dateOfEnteringDate);
			EmployeesKanri.insertEmployees(nameKanzi, nameKana, birth, sex, addressNumber, address, phoneNumber, deptId, sectionId, positionId, dateOfEnteringDate,licenseId);
			//mod Araki Yuki 17/06/30 end
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder(1000);
		sb.append("氏名："+nameKanzi+"<br>");
		sb.append("シメイ："+nameKana+"<br>");
		sb.append("部署名："+deptName+"<br>");
		sb.append("課名："+sectionName+"<br>");
		sb.append("役職名："+positionName+"<br>");
		//add Araki Yuki 17/06/30 begin
		sb.append("資格："+licenseName+"<br>");
		//add Araki Yuki 17/06/30 end
		sb.append("生年月日："+birth+"<br>");
		sb.append("性別："+sex+"<br>");
		sb.append("〒："+addressNumber+"<br>");
		sb.append("住所："+state+city+address1+address2+"<br>");
		sb.append("TEL："+phoneNumber+"<br>");
		sb.append("入社日："+dateOfEntering+"<br>");
		sb.append("上記の社員データを登録しました！");

		String insertInfo = sb.toString();

		session.setAttribute("insertEmployee", insertInfo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeesInsertTopServlet");
		dispatcher.forward(request, response);


	}

}
