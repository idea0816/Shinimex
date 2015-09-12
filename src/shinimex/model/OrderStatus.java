package shinimex.model;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Conn conn = new Conn();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		// 取得今年年份
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		// 取得今天日期加6個月
		cal.add(Calendar.MONTH, 6);
		// checkMaxDate -
		// 最大日期供檢查日期格式是否正確.2015-09-03詢問ZOE接到訂單後最晚在2個月後結單、這裡設定6個月較保險
		String checkMaxDate = dateFormat.format(cal.getTime());

		HttpSession session = req.getSession();

		ServletContext servletContext = getServletConfig().getServletContext();

		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");

		DecimalFormat df = new DecimalFormat("###,###,###,###.##");// 數字百分位設定

		/**
		 * 2013-01-04 訂單數量加入條件如下: DDZL.DDLB = 'N' 訂單類別為正式訂單 DDZL.DDZT = 'Y'
		 * 訂單狀態為正常 XXZL.lbdh = 'O' 加入判斷模具類別 
		 * 2015-08-18 加入年份自動取得(所以跨年後要怎麼取得資料???)
		 * 2015-08-19 加入日期格式檢查 2015-09-03 加入日期不合理檢查(大於現在2個月)
		 **/

		// 接單日期格式檢查
		Map<String, String> ddrqCheck = new TreeMap<String, String>();
		conn.rs_title = "SELECT DDBH,DDRQ FROM DDZL WHERE ISDATE(CONVERT(VARCHAR, DDRQ)) <> '1' OR DDRQ > '"
				+ checkMaxDate + "'";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				ddrqCheck.put(conn.rs.getString("DDBH"),
						conn.rs.getString("DDRQ"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 訂單交期格式檢查
		Map<String, String> ddjqCheck = new TreeMap<String, String>();
		conn.rs_title = "SELECT DDBH,DDJQ FROM DDZL WHERE ISDATE(CONVERT(VARCHAR, DDJQ)) <> '1' OR DDJQ > '"
				+ checkMaxDate + "'";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				ddjqCheck.put(conn.rs.getString("DDBH"),
						conn.rs.getString("DDJQ"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 生產日期格式檢查
		Map<String, String> chrqCheck = new TreeMap<String, String>();
		conn.rs_title = "SELECT CHBH,CHRQ FROM DDS WHERE ISDATE(CONVERT(VARCHAR, CHRQ)) <> '1' OR CHRQ > '"
				+ checkMaxDate + "'";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				chrqCheck.put(conn.rs.getString("CHBH"),
						conn.rs.getString("CHRQ"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 判斷昃否要修改日期
		if (ddrqCheck.isEmpty() && ddjqCheck.isEmpty() && chrqCheck.isEmpty()) {
			// Complete orderStatus
			ArrayList<String> outsoles = new ArrayList<String>();// 大底
			ArrayList<String> pieces = new ArrayList<String>();// 飾片
			ArrayList<String> delivery = new ArrayList<String>();// 交貨
			ArrayList<String> capacity = new ArrayList<String>();// 產能

			// 大底接單量
			conn.rs_title = "SELECT SUM(DDZL.Pairs) outsoles FROM DDZL DDZL,XXZL XXZL WHERE DATEPART(YEAR, DDZL.DDRQ) = '"
					+ year
					+ "' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND XXZL.lbdh = 'O' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao GROUP BY DATENAME(MONTH, DDZL.DDRQ),DATEPART(MONTH, DDZL.DDRQ) ORDER BY DATEPART(MONTH, DDZL.DDRQ)";
			int i_outsoles = 0;// 補足12個月
			Double sumoutsoles = 0.0;// 合計
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					sumoutsoles += conn.rs.getDouble("outsoles");
					outsoles.add(String.valueOf(df.format(conn.rs
							.getDouble("outsoles"))));
					i_outsoles += 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 補足12個月
			while (i_outsoles != 12) {
				outsoles.add("");
				i_outsoles += 1;
			}
			outsoles.add(String.valueOf(df.format(sumoutsoles)));

			// 飾片接單量
			conn.rs_title = "SELECT SUM(DDZL.Pairs) pieces FROM DDZL DDZL,XXZL XXZL WHERE DATEPART(YEAR, DDZL.DDRQ) = '"
					+ year
					+ "' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND XXZL.lbdh != 'O' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao GROUP BY DATENAME(MONTH, DDZL.DDRQ),DATEPART(MONTH, DDZL.DDRQ) ORDER BY DATEPART(MONTH, DDZL.DDRQ)";
			int i_pieces = 0;// 補足12個月
			Double sumpieces = 0.0;// 合計
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					sumpieces += conn.rs.getDouble("pieces");
					pieces.add(String.valueOf(df.format(conn.rs
							.getDouble("pieces"))));
					i_pieces += 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 補足12個月
			while (i_pieces != 12) {
				pieces.add("");
				i_pieces += 1;
			}
			pieces.add(String.valueOf(df.format(sumpieces)));

			// 預計交貨單量
			conn.rs_title = "SELECT SUM(Pairs) delivery FROM DDZL WHERE DATEPART(YEAR, DDJQ) = '"
					+ year
					+ "' GROUP BY DATENAME(MONTH, DDJQ),DATEPART(MONTH, DDJQ) ORDER BY DATEPART(MONTH, DDJQ)";
			int i_delivery = 0;// 補足12個月
			Double sumdelivery = 0.0;// 合計
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					sumdelivery += conn.rs.getDouble("delivery");
					delivery.add(String.valueOf(df.format(conn.rs
							.getDouble("delivery"))));
					i_delivery += 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 補足12個月
			while (i_delivery != 12) {
				delivery.add("");
				i_delivery += 1;
			}
			delivery.add(String.valueOf(df.format(sumdelivery)));

			// 實際產能
			conn.rs_title = "SELECT SUM(Qty) capacity FROM DDS WHERE DATEPART(YEAR, CHRQ) = '"
					+ year
					+ "' GROUP BY DATENAME(MONTH, CHRQ),DATEPART(MONTH, CHRQ) ORDER BY DATEPART(MONTH, CHRQ)";
			int i_capacity = 0;// 補足12個月
			Double sumcapacity = 0.0;// 合計
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					sumcapacity += conn.rs.getDouble("capacity");
					capacity.add(String.valueOf(df.format(conn.rs
							.getDouble("capacity"))));
					i_capacity += 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 補足12個月
			while (i_capacity != 12) {
				capacity.add("");
				i_capacity += 1;
			}
			capacity.add(String.valueOf(df.format(sumcapacity)));

			session.setAttribute("outsoles", outsoles);
			session.setAttribute("pieces", pieces);
			session.setAttribute("delivery", delivery);
			session.setAttribute("capacity", capacity);
			resp.sendRedirect("orderStatus.jsp");
			// RequestDispatcher rd =
			// req.getRequestDispatcher("orderStatus.jsp");
			// rd.forward(req, resp);
		} else {
			session.setAttribute("ddrqCheck", ddrqCheck);
			session.setAttribute("ddjqCheck", ddjqCheck);
			session.setAttribute("chrqCheck", chrqCheck);
			resp.sendRedirect("orderDateCheck.jsp");
		}

		// super.doGet(req, resp);
	}

}
