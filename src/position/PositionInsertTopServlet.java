package position;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.PositionBean;
import benefit.NullCheck;
import net.arnx.jsonic.JSON;

/**
 * 役職登録のTOP画面を生成するサーブレットクラス
 *
 * Servlet implementation class PositionInsertTopServlet
 */
@WebServlet("/PositionInsertTopServlet")
public class PositionInsertTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositionInsertTopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String ajaxFlag = request.getParameter("ajaxFlag");
		String positionLank =NullCheck.nullConvert((String) session.getAttribute("textBoxPositionLank"));



		/**
		 * 役職の一覧の作成
		 */
		List<PositionBean> positionBeanList = new ArrayList<PositionBean>();
		PositionKanri positionKanri = new PositionKanri();
		try {
			positionBeanList = positionKanri.getAllPosition();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println(e);
		}

		StringBuilder sb = new StringBuilder(300);
		String buttonHtml = "";
		for (int i = 0; i < positionBeanList.size(); i++) {
			if(i==0){
				buttonHtml="<Button class='DOWN'>↓</Button>";
			}else if(i==positionBeanList.size()-1){
				buttonHtml="<Button class='UP'>↑</Button>";
			}else{
				buttonHtml="<Button class='UP'>↑</Button><Button class='DOWN'>↓</Button>";
			}
			sb.append("<tr><td>"+positionBeanList.get(i).getPositionName()+"</td>");
			sb.append("<td>"+positionBeanList.get(i).getPositionLank()+"</td>");
			sb.append("<td>"+positionBeanList.get(i).getPositionAllowance()+"円</td>");
			sb.append("<td style='width:80px'>"+buttonHtml+"</td>");
			sb.append("<td>"
				+"<form action='PositionUpdateInputServlet' method='POST'>"
				+"<input type='hidden' name='positionId' value='"+positionBeanList.get(i).getPositionId()+"'>"
				+"<input type='hidden' name='positionName' value='"+positionBeanList.get(i).getPositionName()+"'>"
				+"<input type='hidden' name='positionAllowance' value='"+positionBeanList.get(i).getPositionAllowance()+"'>"

				+"<input type='submit' value='変更'>"
				+"</form>"
			    +"</td></tr>");
		}
		String strPositionBeanList = sb.toString();

		session.setAttribute("strPositionBeanList", strPositionBeanList);
		/**
		 * 役職の作成の終了
		 */


		/**
		 * 役職ランクのオプションメニューの作成
		 */
		StringBuilder sb2 = new StringBuilder(300);
		String selected;
		for (int i = 0; i < positionBeanList.size(); i++) {

			if(positionLank.equals(positionBeanList.get(i).getPositionLank())){
				selected="selected";
			}else{
				selected="";
			}

			sb2.append("<option value='"+positionBeanList.get(i).getPositionLank()+"'"+selected+">"+positionBeanList.get(i).getPositionLank()+"</option>");
		}

		if(positionLank.equals((positionBeanList.size()+1)+"")){
			selected="selected";
		}else{
			selected="";
		}

		sb2.append("<option value='"+(positionBeanList.size()+1)+"'"+selected+">"+(positionBeanList.size()+1)+"</option>");
		String strPositionLankList = sb2.toString();
		session.setAttribute("strPositionLankList", strPositionLankList);

		/**
		 * 役職ランクのオプションメニューの作成の終了
		 */



		response.setContentType("text/html; charset=utf-8");

		if(ajaxFlag!=null){
			System.out.println("ajax使用（役職登録）");
			PrintWriter out = response.getWriter();
		    out.println(JSON.encode(strPositionBeanList));

		}else{
			System.out.println("ajax未使用（役職登録）");
			RequestDispatcher dispatcher = request.getRequestDispatcher("position/position_insert.jsp");
			dispatcher.forward(request, response);
		}








	}

}
