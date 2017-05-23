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

import benefit.IntegerCheck;

/**
 * 役職登録の結果画面を生成するサーブレットクラス
 *
 * Servlet implementation class PositionInsertResultServlet
 */
@WebServlet("/PositionInsertResultServlet")
public class PositionInsertResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositionInsertResultServlet() {
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
		String positionName =(String) session.getAttribute("textBoxPositionName");
		String positionLank =(String) session.getAttribute("textBoxPositionLank");
		String positionAllowance =(String) session.getAttribute("textBoxPositionAllowance");
		try {
			PositionKanri.positionInsert(positionName, IntegerCheck.convertInteger(positionLank), positionAllowance);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("result", "役職名:"+positionName+"<br>役職ランク:"+positionLank+"番目<br>役職手当:"+positionAllowance+"円");
		RequestDispatcher dispatcher = request.getRequestDispatcher("position/position_insert_result.jsp");
		dispatcher.forward(request, response);

	}

}
