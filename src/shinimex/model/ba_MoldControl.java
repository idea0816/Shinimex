package shinimex.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shinimex.controller.MoldData;


/*
 * @author Eric Chen.,CPY
 * @version Create Time:2016年5月26日
 */

@SuppressWarnings("serial")
public class ba_MoldControl extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Conn conn = new Conn();
		ServletContext servletContext = getServletConfig().getServletContext();
		
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		
		// 列出所有模具資料
		conn.rs_title = "SELECT MJZL.mjbh,MJZL.lbdh,kfzl.kfjc,kfzl1.kfjc kfjc1,sum(MJZLS.mjsl) mjsl,MJZL.gbbh FROM MJZL MJZL LEFT JOIN kfzl kfzl ON MJZL.kfdh = kfzl.kfdh LEFT JOIN kfzl kfzl1 ON MJZL.kfdh1 = kfzl1.kfdh LEFT JOIN MJZLS MJZLS ON MJZL.mjbh = MJZLS.mjbh GROUP BY MJZL.mjbh,MJZL.lbdh,kfzl.kfjc,kfzl1.kfjc,MJZL.gbbh";
		
		// Mold(全部模具列表)
		ArrayList<MoldData> moldList = new ArrayList<MoldData>();
		MoldData moldData;
		int summjsl = 0;		
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				String mjbh = conn.rs.getString("mjbh");//模具編號
				String lbdh = conn.rs.getString("lbdh");//模具類型
				String kfjc = conn.rs.getString("kfjc");//客戶簡稱
				String kfjc1 = conn.rs.getString("kfjc1");//鞋廠簡稱
				Integer mjsl = conn.rs.getInt("mjsl");//模具數量
				summjsl = summjsl + conn.rs.getInt("mjsl");//模具數量總合
				String gbbh = conn.rs.getString("gbbh");//鞋碼國別
				moldData = new MoldData(mjbh, lbdh, kfjc, kfjc1, mjsl, gbbh);
				moldList.add(moldData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//Send Data
		session.setAttribute("moldList", moldList);
		session.setAttribute("summjsl", summjsl);
		resp.sendRedirect("ba_MoldControl.jsp");
		//super.doGet(req, resp);
	}
}
