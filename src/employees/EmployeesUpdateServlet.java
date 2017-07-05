package employees;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.EmployeesBean;
import benefit.NullCheck;
import common.OptionMenuCreate;

/**
 * Servlet implementation class EmployeesUpdateServlet
 */
@WebServlet("/EmployeesUpdateServlet")
public class EmployeesUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesUpdateServlet() {
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
		OptionMenuCreate optionMenuCreate = new OptionMenuCreate();
		String empId = request.getParameter("emp_id");
		System.out.println("empId="+empId);

		EmployeesBean emBean = null;
		EmployeesKanri emKanri = new EmployeesKanri();

		/**
		 * 社員情報の検索
		 */
		try {
			emBean =emKanri.employeesSearchPK(empId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		/**
		 * 検索の終了
		 */
		List<String> UpdateInputList = new ArrayList<String>();


		if(emBean!=null){
			String nameKanzi[] = emBean.getEmpNameKanzi().split(" ",-1);
			String nameKana[] = emBean.getEmpNameKana().split(" ",-1);
			String address[] = emBean.getEmpAddress().split(",",-1);
			UpdateInputList.add(NullCheck.nullConvert(nameKanzi[0]));//0
			UpdateInputList.add(NullCheck.nullConvert(nameKanzi[1]));//1
			UpdateInputList.add(NullCheck.nullConvert(nameKana[0]));//2
			UpdateInputList.add(NullCheck.nullConvert(nameKana[1]));//3
			UpdateInputList.add(NullCheck.nullConvert(emBean.getEmpSex()));//4
			UpdateInputList.add(NullCheck.nullConvert(emBean.getEmpAddressNumber()));//5
			UpdateInputList.add(NullCheck.nullConvert(address[0]));//6
			UpdateInputList.add(NullCheck.nullConvert(address[1]));//7
			UpdateInputList.add(NullCheck.nullConvert(address[2]));//8
			UpdateInputList.add(NullCheck.nullConvert(address[3]));//9
			UpdateInputList.add(NullCheck.nullConvert(emBean.getEmpPhoneNumber()));//10

			String dateOfEntering= emBean.getDateOfEntering();
			if(dateOfEntering!=null){
				dateOfEntering=dateOfEntering.substring(0,dateOfEntering.length()-3);
			}
			String dateOfRetire= emBean.getDateOfRetire();
			if(dateOfRetire!=null){
				dateOfRetire=dateOfRetire.substring(0,dateOfRetire.length()-3);
			}
			UpdateInputList.add(NullCheck.nullConvert(dateOfEntering));//11
			UpdateInputList.add(NullCheck.nullConvert(dateOfRetire));//12
			UpdateInputList.add(empId);//13

			session.setAttribute("UpdateInputList", UpdateInputList);

			String deptId = NullCheck.nullConvert(emBean.getDeptId());
			String sectionId = NullCheck.nullConvert(emBean.getSectionId());
			System.out.println("sectionId="+sectionId);
			String positionId = NullCheck.nullConvert(emBean.getPositionId());


			/**
			 * 部署のoptionメニューを作成
			 */
			String strDepartmentBeanList = optionMenuCreate.deptOptionMenuCreate(deptId);
			session.setAttribute("strDepartmentBeanList", strDepartmentBeanList);
			/**
			 * 部署のoptionメニューを作成の終了
			 */

			/**
			 * 課のoptionメニューを作成
			 */
			String sectionOptionMenu = optionMenuCreate.sectionOptionMenuCreate(deptId,sectionId);
			session.setAttribute("sectionOptionMenu", sectionOptionMenu);
			/**
			 * 課のoptionメニューを作成の終了
			 */



			/**
			 *役職のoptionメニューを作成
			 */
			String strPositionBeanList = optionMenuCreate.positionOptionMenuCreate(positionId);
			session.setAttribute("strPositionBeanList", strPositionBeanList);
			/**
			 *役職のoptionメニューを作成の終了
			 */



			RequestDispatcher dispatcher = request.getRequestDispatcher("employees/employees_update.jsp");
			dispatcher.forward(request, response);

		}



	}

}
