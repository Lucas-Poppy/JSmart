package position;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 役職変更の入力画面を生成するサーブレットクラス
 *
 * Servlet implementation class PositionUpdateInputServlet
 */
@WebServlet("/PositionUpdateInputServlet")
public class PositionUpdateInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositionUpdateInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		/**
		 * 変更ボタンが押された列の役職ID
		 */
		String positionId = request.getParameter("positionId");
		/**
		 * 変更ボタンが押された列の役職名
		 */
		String positionName = request.getParameter("positionName");
		/**
		 * 変更ボタンが押された列の役職手当
		 */
		String positionAllowance = request.getParameter("positionAllowance");

		session.setAttribute("updatePositionId", positionId);
		session.setAttribute("updateOldName", positionName);
		session.setAttribute("updateOldAllowance", positionAllowance);
		session.setAttribute("oldPosition", "旧役職名:"+positionName+"<br>旧役職手当:"+positionAllowance+"円");

		RequestDispatcher dispatcher = request.getRequestDispatcher("position/position_update_input.jsp");
		dispatcher.forward(request, response);




	}

}
