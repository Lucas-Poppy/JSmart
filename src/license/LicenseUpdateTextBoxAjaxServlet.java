package license;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LicenseBean;
import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class LicenseUpdateTextBoxAjaxServlet
 */
@WebServlet("/LicenseUpdateTextBoxAjaxServlet")
public class LicenseUpdateTextBoxAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LicenseUpdateTextBoxAjaxServlet() {
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

		String licenseLank =request.getParameter("licenseLank");
		String licenseId =request.getParameter("licenseId");

		/**
		 * 役職の一覧の作成
		 */
		List<LicenseBean> licenseBeanList = new ArrayList<LicenseBean>();
		LicenseKanri licenseKanri = new LicenseKanri();
		try {
			licenseBeanList = licenseKanri.getLicenseAll();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println(e);
		}

		StringBuilder sb = new StringBuilder(300);

		for (int i = 0; i < licenseBeanList.size(); i++) {
			sb.append("<tr><td>"+licenseBeanList.get(i).getLicenseName()+"</td>");
			if(licenseId.equals(licenseBeanList.get(i).getLicenseId())){
				sb.append("<td style='width:85px;' class='textbox' Align='right' data-id='"+licenseBeanList.get(i).getLicenseId()+"'><input type='number' name='licenseLankInput' id='licenseLankInput' style='text-align:right;width:83px;' onkeydown='enter();' value='"+licenseBeanList.get(i).getLicenseLank()+"'></td>");
			}else{
				sb.append("<td style='width:85px;' class='textbox' Align='right' data-id='"+licenseBeanList.get(i).getLicenseId()+"'>"+licenseBeanList.get(i).getLicenseLank()+"</td>");
			}
			sb.append("</tr>");
		}
		response.setContentType("text/html; charset=utf-8");
		String licenseList = sb.toString();
		System.out.println("ajax使用（資格登録）");
		PrintWriter out = response.getWriter();
	    out.println(JSON.encode(licenseList));


	}

}
