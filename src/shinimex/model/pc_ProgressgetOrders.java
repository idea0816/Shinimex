package shinimex.model;

import java.io.IOException;
import java.sql.SQLException;
//import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.google.gson.Gson;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年3月30日
 *
 */
@SuppressWarnings("serial")
public class pc_ProgressgetOrders extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Conn conn = new Conn();
		//Gson gson = new Gson();
		//HashMap<String, String> KHDHlist = new HashMap<String, String>();

		ServletContext servletContext = getServletConfig().getServletContext();

		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");

		// 取得條件值->
		// 鞋型Xiexing, 訂單起迄日date1&date2, 鞋廠代號KHDH
		// 另加上 訂單類別DDLB=N(正式訂單), 訂單狀態DDZT=Y(正常)
		// 得到資料->
		// 訂單編號DDBH, 接單日DDRQ, 客戶POKHPO, ARTICLE, 色號SheHao, 訂單交期DDJQ, 生產上線日SCRQ, 雙數Pairs, 型體說明XXZL.YSSM, 迴轉數XXZL.HZS, 模具編號XXZL.mjbh
		if (req.getParameter("date2") != "") {
			conn.rs_title = "SELECT DDZL.DDBH,DDZL.DDRQ,DDZL.KHPO,DDZL.ARTICLE,DDZL.SheHao,DDZL.DDJQ,DDZL.SCRQ,DDZL.Pairs,XXZL.YSSM,XXZL.HZS,XXZL.mjbh FROM DDZL DDZL,XXZL XXZL WHERE DDZL.XieXing = '"
					+ req.getParameter("xiexing") + "' AND DDZL.DDRQ >= '"
					+ req.getParameter("date1") + "' AND DDZL.DDRQ <= '"
					+ req.getParameter("date2") + "' AND DDZL.KHDH = '"
					+ req.getParameter("KHDH")
					+ "' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao";
		} else {
			conn.rs_title = "SELECT DDZL.DDBH,DDZL.DDRQ,DDZL.KHPO,DDZL.ARTICLE,DDZL.SheHao,DDZL.DDJQ,DDZL.SCRQ,DDZL.Pairs,XXZL.YSSM,XXZL.HZS,XXZL.mjbh FROM DDZL DDZL,XXZL XXZL WHERE DDZL.XieXing = '"
					+ req.getParameter("xiexing") + "' AND DDZL.DDRQ >= '"
					+ req.getParameter("date1") + "' AND DDZL.KHDH = '"
					+ req.getParameter("KHDH")
					+ "' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao";
		}

		conn.Conn_SQL();

		try {
			while (conn.rs.next()) {
				System.out.println(conn.rs.getString("DDBH"));
				
				conn.rs_title ="select count(*) from DDZLS where DDBH = '"+conn.rs.getString("DDBH")+"' and Qty is not null";
				conn.Conn_SQL();
				System.out.println(conn.rs.getString(1));
				
				// KHDHlist.put(conn.rs.getString("kfjc"),
				// conn.rs.getString("KHDH"));
			}

			conn.MyConn.close();
			conn.rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// super.doPost(req, resp);
	}
}
