package employees;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;

import beans.EmployeesBean;
import benefit.IntegerCheck;
import benefit.NullCheck;

/**
 * Servlet implementation class EmployeesSearchServlet
 */
@WebServlet("/EmployeesSearchServlet")
public class EmployeesSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesSearchServlet() {
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

	private final String DEPT_MODE="dept";

	private final String POSITION_MODE="position";

	private final String SENIORTY_MODE="seniority";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		//絞り込み検索の何を使うかを受け取る
		String[] searchConditions = request.getParameterValues("searchConditions");
		//何ページ目を表示するかの取得
		int page = IntegerCheck.convertInteger(request.getParameter("page"));
		System.out.println(page);
		if(page<0){
			page=0;
		}
		session.setAttribute("page", page+"");
		List<Map<String,String>> where = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		if(searchConditions!=null){
			for(int i=0;i<searchConditions.length;i++){

				if(DEPT_MODE.equals(searchConditions[i])){
					session.setAttribute("deptCheck", "checked");
					String dept[] = request.getParameter("dept").split("[,]", 0);
					String deptId = dept[0];
					map = new HashMap<String,String>();
					map.put("Where列", "dept_id= ?");
					map.put("Where値", deptId+"");
					where.add(map);
					session.setAttribute("optionMenuDeptId", deptId);

				}

				if(POSITION_MODE.equals(searchConditions[i])){
					session.setAttribute("positionCheck", "checked");
					String position[] = request.getParameter("position").split("[,]", 0);
					String positionId = position[0];
					map = new HashMap<String,String>();
					map.put("Where列", "position_id = ?");
					map.put("Where値", positionId+"");
					where.add(map);
					session.setAttribute("optionMenuPositionId", positionId);
				}

				if(SENIORTY_MODE.equals(searchConditions[i])){
					session.setAttribute("seniorityCheck", "checked");
					String seniorityFirst = request.getParameter("seniorityFirst");
					String senioritySecond = request.getParameter("senioritySecond");
					session.setAttribute("seniorityFirst", seniorityFirst);
					session.setAttribute("senioritySecond", senioritySecond);
					int first =IntegerCheck.convertInteger(seniorityFirst);
					int second =IntegerCheck.convertInteger(senioritySecond);


					if(second<=0){
						senioritySecond="999";
					}
					if(first<=0){
						senioritySecond="1";
					}
					if(first>second){
						seniorityFirst="1";
					}

					map = new HashMap<String,String>();
					map.put("Where列", "TIMESTAMPDIFF(YEAR, date_of_entering,CURDATE()) +1 >= ?");
					map.put("Where値", seniorityFirst+"");
					map = new HashMap<String,String>();
					map.put("Where列", "TIMESTAMPDIFF(YEAR, date_of_entering,CURDATE()) +1 <= ?");
					map.put("Where値", senioritySecond+"");
					where.add(map);


				}
			}
		}
		List<EmployeesBean> emBeanList = new ArrayList<EmployeesBean>();
		EmployeesKanri emKanri = new EmployeesKanri();

		/**
		 * 社員情報の検索
		 */
		try {
			PreparedStatement pstm = EmployeesKanri.sqlCreate(page, where);
			emBeanList =emKanri.employeesSearch(pstm);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		/**
		 * 検索の終了
		 */

		/**
		 * 社員一覧の作成
		 */
		StringBuilder sb = new StringBuilder(2000);
		for (int i = 0; i < emBeanList.size(); i++) {

			String dateOfEntering = emBeanList.get(i).getDateOfEntering();
			String dateOfRetire = emBeanList.get(i).getDateOfRetire();

			if(dateOfEntering!=null){
				dateOfEntering=dateOfEntering.substring(0,dateOfEntering.length()-3);
			}else{
				dateOfEntering="";
			}

			if(dateOfRetire!=null){
				dateOfRetire=dateOfRetire.substring(0,dateOfRetire.length()-3);
			}else{
				dateOfRetire="";
			}
			sb.append("<tr><input type='hidden' name='empId' class='empId' value='"+emBeanList.get(i).getEmpId()+"'>");
			sb.append("<td>" + emBeanList.get(i).getEmpNameKanzi() + "</td>");
			sb.append("<td>" + emBeanList.get(i).getEmpNameKana() + "</td>");
			sb.append("<td>" + emBeanList.get(i).getDeptName()+ "</td>");
			sb.append("<td>" + emBeanList.get(i).getSectionName() + "</td>");
			sb.append("<td>" + emBeanList.get(i).getPositionName() + "</td>");
			sb.append("<td>" + emBeanList.get(i).getEmpSex() + "</td>");
			sb.append("<td>" + emBeanList.get(i).getEmpBirth() + "</td>");
			sb.append("<td>" + emBeanList.get(i).getEmpAddressNumber() + "</td>");
			sb.append("<td>" + emBeanList.get(i).getEmpAddress().replaceAll(",", "") + "</td>");
			sb.append("<td>" + emBeanList.get(i).getEmpPhoneNumber() + "</td>");
			sb.append("<td>" + dateOfEntering + "</td>");
			sb.append("<td>" + dateOfRetire + "</td>");
			//add Araki Yuki 17/07/07 begin
			sb.append("<td>" + NullCheck.nullConvert(emBeanList.get(i).getLicenseName()) + "</td>");
			//add Araki Yuki 17/07/07 end
			sb.append("<td><Button type='button' class='change'>変更</Button></td>");
			sb.append("</tr>");
		}
		String strEmployeesBeanList = sb.toString();

		session.setAttribute("strEmployeesBeanList", strEmployeesBeanList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeesSearchTopServlet");
		dispatcher.forward(request, response);





	}

}
