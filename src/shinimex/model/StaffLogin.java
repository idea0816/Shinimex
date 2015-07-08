package shinimex.model;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年7月8日
 *
 */
public class StaffLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Conn conn = new Conn();
		ServletContext servletContext = getServletConfig().getServletContext();
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		
		String userName = req.getParameter("username");
		String passWord = req.getParameter("password");
		
		
		
		
//		super.doPost(req, resp);
	}
}
