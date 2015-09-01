package shinimex.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年8月18日
 *
 */
@SuppressWarnings("serial")
public class OrderStatus extends HttpServlet {

	Conn conn = new Conn();

	// 取得今年年份
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		ServletContext servletContext = getServletConfig().getServletContext();

		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");

		/**
		 * 2013-01-04 訂單數量加入條件如下: DDZL.DDLB = 'N' 訂單類別為正式訂單 DDZL.DDZT = 'Y'
		 * 訂單狀態為正常 XXZL.lbdh = 'O' 加入判斷模具類別 2015-08-18 加入年份自動取得(所以跨年後要怎麼取得資料???)
		 * 2015-08-19 加入日期格式檢查
		 **/

		// 接單日期格式檢查
		Map<String, String> ddrqCheck = new TreeMap<String, String>();
		conn.rs_title = "SELECT DDBH,DDRQ FROM DDZL WHERE ISDATE(CONVERT(VARCHAR, DDRQ)) <> '1'";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				ddrqCheck.put(conn.rs.getString("DDBH"), conn.rs.getString("DDRQ"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//for each ddrqCheck value
//		for(Object key: ddrqCheck.keySet()){
//			System.out.println(key.toString()+", "+ddrqCheck.get(key));
//		}

		// 訂單交期格式檢查
		Map<String, String> ddjqCheck = new TreeMap<String, String>();
		conn.rs_title = "SELECT DDBH,DDJQ FROM DDZL WHERE ISDATE(CONVERT(VARCHAR, DDJQ)) <> '1'";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				ddjqCheck.put(conn.rs.getString("DDBH"), conn.rs.getString("DDJQ"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 生產日期格式檢查
		Map<String, String> chrqCheck = new TreeMap<String, String>();
		conn.rs_title = "SELECT CHBH,CHRQ FROM DDS WHERE ISDATE(CONVERT(VARCHAR, CHRQ)) <> '1'";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				chrqCheck.put(conn.rs.getString("CHBH"), conn.rs.getString("CHRQ"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		//判斷昃否要修改日期
		if(ddrqCheck.isEmpty() && ddjqCheck.isEmpty() && chrqCheck.isEmpty()){
			RequestDispatcher rd = req.getRequestDispatcher("orderStatus.jsp");
			rd.forward(req, resp);
		}else{
			session.setAttribute("ddrqCheck", ddrqCheck);
			session.setAttribute("ddjqCheck", ddjqCheck);
			session.setAttribute("chrqCheck", chrqCheck);
			resp.sendRedirect("orderDateCheck.jsp");
		}

		// 大底接單量
		conn.rs_title = "SELECT SUM(DDZL.Pairs) FROM DDZL DDZL,XXZL XXZL WHERE DATEPART(YEAR, DDZL.DDRQ) = '"
				+ year
				+ "' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND XXZL.lbdh = 'O' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao GROUP BY DATENAME(MONTH, DDZL.DDRQ),DATEPART(MONTH, DDZL.DDRQ) ORDER BY DATEPART(MONTH, DDZL.DDRQ)";
//		System.out.println(conn.rs_title);

		// 飾片接單量
		conn.rs_title = "SELECT SUM(DDZL.Pairs) FROM DDZL DDZL,XXZL XXZL WHERE DATEPART(YEAR, DDZL.DDRQ) = '"
				+ year
				+ "' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND XXZL.lbdh != 'O' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao GROUP BY DATENAME(MONTH, DDZL.DDRQ),DATEPART(MONTH, DDZL.DDRQ) ORDER BY DATEPART(MONTH, DDZL.DDRQ)";
//		System.out.println(conn.rs_title);

		// 預計交貨單量
		conn.rs_title = "SELECT SUM(Pairs) FROM DDZL WHERE DATEPART(YEAR, DDJQ) = '"
				+ year
				+ "' GROUP BY DATENAME(MONTH, DDJQ),DATEPART(MONTH, DDJQ) ORDER BY DATEPART(MONTH, DDJQ)";
//		System.out.println(conn.rs_title);

		// 實際產能
		conn.rs_title = "SELECT SUM(Qty) FROM DDS WHERE DATEPART(YEAR, CHRQ) = '"
				+ year
				+ "' GROUP BY DATENAME(MONTH, CHRQ),DATEPART(MONTH, CHRQ) ORDER BY DATEPART(MONTH, CHRQ)";
//		System.out.println(conn.rs_title);

		// conn.Conn_SQL();

		// super.doGet(req, resp);
	}

}
