package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年3月27日
 *
 */
@SuppressWarnings("serial")
public class pc_Progressxiexing extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Conn conn = new Conn();
		Gson gson = new Gson();
		HashMap<String, String> xiexinglist = new HashMap<String, String>();

		ServletContext servletContext = getServletConfig().getServletContext();

		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		conn.rs_title = "SELECT DISTINCT XieXing FROM DDZL WHERE DDRQ >= '"
				+ req.getParameter("date1").replace("-", "") + "'";
		conn.Conn_SQL();

		try {
			while (conn.rs.next()) {
				xiexinglist.put(conn.rs.getString("XieXing"),
						conn.rs.getString("XieXing"));
			}
			
			conn.MyConn.close();
			conn.rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		resp.setContentType("text/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(gson.toJson(xiexinglist));
		out.close();

		// super.doPost(req, resp);
	}
}
