package license;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LicenseBean;
import benefit.NullCheck;

/**
 * Servlet implementation class LicenseInsertTopServlet
 */
@WebServlet("/LicenseInsertTopServlet")
public class LicenseInsertTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LicenseInsertTopServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String licenseLank =NullCheck.nullConvert((String) session.getAttribute("textBoxLicenseLank"));

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
			sb.append("<td Align='right'>"+licenseBeanList.get(i).getLicenseLank()+"</td>");
			sb.append("</tr>");
		}

		String licenseList = sb.toString();
		session.setAttribute("licenseList", licenseList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("license/license_insert.jsp");
		dispatcher.forward(request, response);


	}

}
