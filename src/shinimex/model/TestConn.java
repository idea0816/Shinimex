package shinimex.model;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年1月30日
 *
 */
@SuppressWarnings("serial")
public class TestConn extends HttpServlet {
	Conn conn = new Conn();
	DecimalFormat df = new DecimalFormat("###,###,###,###.##");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
		
		HttpSession session = req.getSession();  

		ServletContext servletContext = getServletConfig().getServletContext();
		// out.println(servletContext.getInitParameter("dataBase"));

		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		conn.rs_title = "SELECT DISTINCT XieXing FROM XXZL";
		conn.Conn_SQL();
		
		ArrayList<String> xiexingList = new ArrayList<String>();
		
		try {
			while (conn.rs.next()) {
//				out.println(conn.rs.getString(1));
//				System.out.println(conn.rs.getString("XieXing"));
				xiexingList.add(conn.rs.getString("XieXing"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        session.setAttribute("xiexingList", xiexingList);  
        resp.sendRedirect("test.jsp");  
	}
}
