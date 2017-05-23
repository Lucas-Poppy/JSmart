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
import benefit.IntegerCheck;

/**
 * 役職の変更を完了するサーブレットクラス
 *
 * Servlet implementation class PositionUpdateResultServlet
 */
@WebServlet("/PositionUpdateResultServlet")
public class PositionUpdateResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositionUpdateResultServlet() {
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
		String url ="position/position_update_result.jsp";

		String positionId = (String)session.getAttribute("updatePositionId");
		String newPositionName = request.getParameter("positionName");
		String oldPositionName =(String)session.getAttribute("updateOldName");
		String positionAllowance = request.getParameter("positionAllowance");

		//元の役職名以外の重複チェック
		boolean exsists = false;
		if(!newPositionName.equals(oldPositionName)){
			try {
				exsists =PositionKanri.positionExsists(newPositionName);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}



		//入力チェック
			if(!InputCheck.inputMaxTen(newPositionName)||exsists||!InputCheck.inputEmpty(newPositionName)
					||!InputCheck.inputEmpty(positionAllowance)){

				if(!InputCheck.inputMaxTen(newPositionName)){
					session.setAttribute("errorInsertPositionName10Word", "10文字以上は入力できません！");
				}
				if(exsists){
					session.setAttribute("errorInsertPositionNameExsists", "役職名が重複しています！");
				}
				if(!InputCheck.inputEmpty(newPositionName)||!InputCheck.inputEmpty(positionAllowance)){
					session.setAttribute("errorInsertPositionEmpty", "入力されていない項目があります！");
				}
					url="position/position_update_input.jsp";

					request.setAttribute("inputName", newPositionName);
					request.setAttribute("inputAllowance", positionAllowance);
				}else{
					try {
						System.out.println(positionAllowance);
						System.out.println(positionId);

						/**
						 * 役職の変更処理
						 */
						PositionKanri.positionUpdate(newPositionName, IntegerCheck.convertInteger(positionAllowance), IntegerCheck.convertInteger(positionId));
						request.setAttribute("result", "新役職名:"+newPositionName+"<br>新役職手当:"+positionAllowance+"円");
					} catch (SQLException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}

				}







		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
