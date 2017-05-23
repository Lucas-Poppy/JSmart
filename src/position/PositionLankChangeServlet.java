package position;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import benefit.IntegerCheck;

/**
 * 役職のランクを入れ替えるサーブレットクラス
 *
 * Servlet implementation class PositionLankChangeServlet
 */
@WebServlet("/PositionLankChangeServlet")
public class PositionLankChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositionLankChangeServlet() {
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
		String method = request.getParameter("method");//ランクを下げるのかどうかの方法
		int positionId = IntegerCheck.convertInteger(request.getParameter("positionId"));//変更する対象の役職ID

		System.out.println(method);
		System.out.println(positionId);

		if(positionId!=0){
			try {
				PositionKanri.positionLankChange(positionId, method);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}



	}

}
