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

import benefit.InputCheck;
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
		String url = "department/departmentInsertConfirm.jsp";

		String insertMode = request.getParameter("submitMethod");

		session.setAttribute("insertMode",insertMode);

		switch (NullCheck.nullConvert(insertMode)) {
		//部署登録モードのときの処理
		case DEPT_INSERT_MODE:
			String deptName = request.getParameter("deptName");
			session.setAttribute("textBoxDeptName", deptName);

			boolean deptNameExsists = false;
			try {
				deptNameExsists = DepartmentSectionKanri.deptNameExsists(deptName);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			if(InputCheck.inputMaxTen(deptName)==false||deptNameExsists==false||InputCheck.inputEmpty(deptName)==false){
				if(InputCheck.inputMaxTen(deptName)==false){
					session.setAttribute("errorInsertDeptName10Word", "10文字以上は入力できません！");
				}
				if(deptNameExsists==false){
					session.setAttribute("errorInsertDepartmentExsists", "部署名が重複しています！");
				}
				if(InputCheck.inputEmpty(deptName)==false){
					session.setAttribute("errorInsertDepartmentEmpty", "入力してください！");
				}
				url="DepartmentInsertTopServlet";


			}else{
				session.setAttribute("deptName", deptName);
				request.setAttribute("confirm", deptName+"部を新規に登録する" );
			}

			break;
		//課登録モードのときの処理
		case SECTION_INSERT_MODE:
			String sectionName = request.getParameter("sectionName");
			String dept[] = request.getParameter("deptId").split("[,]", 0);
			String deptId = dept[0];
			String deptNameConfirm = dept[1];

			session.setAttribute("textBoxSectionName", sectionName);
			session.setAttribute("optionMenuDeptId",deptId);



			boolean sectionNameExsists = false;
			try {
				sectionNameExsists = DepartmentSectionKanri.sectionNameExsists(sectionName,deptId);

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			if(InputCheck.inputMaxTen(sectionName)==false||sectionNameExsists==false||InputCheck.inputEmpty(sectionName)==false||deptId.equals("0")){

				if(InputCheck.inputMaxTen(sectionName)==false){
					session.setAttribute("errorInsertSectionName10Word", "10文字以上は入力できません！");
				}
				if(sectionNameExsists==false){
					session.setAttribute("errorInsertSectionExsists", "課名が重複しています！");
				}
				if(InputCheck.inputEmpty(sectionName)==false){
					session.setAttribute("errorInsertSectionEmpty", "入力してください！");
				}
				if(deptId.equals("0")){
					session.setAttribute("errorInsertDepartmentSelected", "部署を選択してください！");
				}

				url="DepartmentInsertTopServlet";
			}else{
				session.setAttribute("sectionName", sectionName);
				session.setAttribute("deptId",deptId);
				session.setAttribute("deptName",deptNameConfirm);
				request.setAttribute("confirm", sectionName+"課を"+deptNameConfirm+"部に新規に登録する" );

			}

			break;
		}



		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
