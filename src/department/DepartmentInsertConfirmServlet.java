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
 * 部署登録もしくは課登録をするサーブレットクラス
 *
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

		// 部署登録もしくは課登録をするときのメソッド
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//送り先のurl
		String url = "department/department_insert_confirm.jsp";

		//部署登録モードか課登録モード化を受け取る
		String insertMode = request.getParameter("submitMethod");

		session.setAttribute("insertMode",insertMode);

		switch (NullCheck.nullConvert(insertMode)) {
		//部署登録モードのときの処理
		case DEPT_INSERT_MODE:
			String deptName = request.getParameter("deptName");
			session.setAttribute("textBoxDeptName", deptName);

			boolean deptNameExsists = false;
			//入力された部署名が重複しているかを確認する
			try {
				deptNameExsists = DepartmentSectionKanri.deptNameExsists(deptName);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			//入力チェック
			if(!InputCheck.inputMaxTen(deptName)||!deptNameExsists||!InputCheck.inputEmpty(deptName)){
				if(!InputCheck.inputMaxTen(deptName)){
					session.setAttribute("errorInsertDeptName10Word", "10文字以上は入力できません！");
				}
				if(!deptNameExsists){
					session.setAttribute("errorInsertDepartmentExsists", "部署名が重複しています！");
				}
				if(!InputCheck.inputEmpty(deptName)){
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

			//入力された課が重複しているかどうかを確認する
			try {
				sectionNameExsists = DepartmentSectionKanri.sectionNameExsists(sectionName,deptId);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			//入力チェック
			if(!InputCheck.inputMaxTen(sectionName)||!sectionNameExsists||!InputCheck.inputEmpty(sectionName)||deptId.equals("0")){

				if(!InputCheck.inputMaxTen(sectionName)){
					session.setAttribute("errorInsertSectionName10Word", "10文字以上は入力できません！");
				}
				if(!sectionNameExsists){
					session.setAttribute("errorInsertSectionExsists", "課名が重複しています！");
				}
				if(!InputCheck.inputEmpty(sectionName)){
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
