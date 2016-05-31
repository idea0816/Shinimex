package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2016年5月31日
 *
 */

@SuppressWarnings("serial")
public class ba_MoldControl_getzszl extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Conn conn = new Conn();
		Gson gson = new Gson();
		HashMap<String, String> zszllist = new HashMap<String, String>();
		
		ServletContext servletContext = getServletConfig().getServletContext();
		
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		
		conn.rs_title = "SELECT zsdh,zsjc FROM zszl";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				zszllist.put(conn.rs.getString("zsdh"), conn.rs.getString("zsjc"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Send to Web Page
		resp.setContentType("text/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(gson.toJson(zszllist));
		out.close();
		
//		super.doPost(req, resp);
	}
}
