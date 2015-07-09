package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年7月9日
 *
 */
@SuppressWarnings("serial")
public class StaffLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();		
		String userName = req.getParameter("username");
		String passWord = req.getParameter("password");
		String auth_uN = null;
		String auth_pW = null;
		
		Conn conn = new Conn();
		ServletContext servletContext = getServletConfig().getServletContext();
		
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		conn.rs_title = "SELECT USERID,PWD FROM USERS WHERE USERID = '"+userName+"'";
		conn.Conn_SQL();
		
		try {
			while(conn.rs.next()){
				auth_uN = conn.rs.getString("USERID");
				auth_pW = conn.rs.getString("PWD");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if(auth_uN != null){
			if(auth_pW.equals(passWord)){
				RequestDispatcher rd = req.getRequestDispatcher("jsp_Frame.html");
				rd.forward(req, resp);
			}else{
				out.print("<p>Sorry, Your Password was Error</p>");
				RequestDispatcher rd = req.getRequestDispatcher("StaffLogin.jsp");
				rd.forward(req, resp);
			}
		}else{
			out.print("<p>Sorry, Your UserName was Error</p>");
			RequestDispatcher rd = req.getRequestDispatcher("StaffLogin.jsp");
			rd.forward(req, resp);
		}
		
//		super.doPost(req, resp);
	}
}
