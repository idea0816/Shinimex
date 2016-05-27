package shinimex.model;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2016年5月27日
 *
 */

public class ba_MoldControl_getData extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Conn conn = new Conn();
		Gson gson = new Gson();
		ServletContext servletContext = getServletConfig().getServletContext();
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		
		// mjbh 取得點選的模具代號
		String getmjbh = req.getParameter("mjbh");
		
		//Get Mold Data
		conn.rs_title = "SELECT MJZL.mjbh,lbzls.zwsm,kfzl.kfjc,kfzl1.kfjc kfjc1,MJZL.gbbh,MJZL.bz1,MJZL.bz2 FROM MJZL MJZL LEFT JOIN kfzl kfzl ON MJZL.kfdh = kfzl.kfdh LEFT JOIN kfzl kfzl1 ON MJZL.kfdh1 = kfzl1.kfdh LEFT JOIN lbzls lbzls ON MJZL.lbdh = lbzls.lbdh WHERE mjbh = '"+getmjbh+"'";
		
		
		//super.doPost(req, resp);
	}

}
