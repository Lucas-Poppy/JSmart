package position;

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

/**
 * 役職登録の確認画面を生成するサーブレットクラス
 *
 * Servlet implementation class PositionInsertConfirmServlet
 */
@WebServlet("/PositionInsertConfirmServlet")
public class PositionInsertConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositionInsertConfirmServlet() {
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
		String url = "position/position_insert_confirm.jsp";


		String positionName = request.getParameter("positionName");
		String positionLank = request.getParameter("positionLank");
		String positionAllowance = request.getParameter("positionAllowance");
		session.setAttribute("textBoxPositionName", positionName);
		session.setAttribute("textBoxPositionLank", positionLank);
		session.setAttribute("textBoxPositionAllowance", positionAllowance);



		//役職の重複チェック
		boolean exsists = true;

		try {
			exsists =PositionKanri.positionExsists(positionName);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//入力チェック
		if(!InputCheck.inputMaxTen(positionName)||exsists||!InputCheck.inputEmpty(positionName)
				||!InputCheck.inputEmpty(positionAllowance)||!InputCheck.inputEmpty(positionLank)){
			//エラーのときの処理

			if(!InputCheck.inputMaxTen(positionName)){
				session.setAttribute("errorInsertPositionName10Word", "10文字以上は入力できません！");
			}
			if(exsists){
				session.setAttribute("errorInsertPositionNameExsists", "役職名が重複しています！");
			}
			if(!InputCheck.inputEmpty(positionName)||!InputCheck.inputEmpty(positionAllowance)||!InputCheck.inputEmpty(positionLank)){
				session.setAttribute("errorInsertPositionEmpty", "入力されていない項目があります！");
			}


			url="PositionInsertTopServlet";
		}else{
			//正常な時の処理
			request.setAttribute("confirm","役職名:"+positionName+"<br>役職ランク:"+positionLank+"番目<br>役職手当:"+positionAllowance+"円");
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
