package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shinimex.controller.MoldData;

import com.google.gson.Gson;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2016年5月27日
 *
 */

@SuppressWarnings("serial")
public class ba_MoldControl_getData extends HttpServlet {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		String getmjbh = (req.getParameter("mjbh")).replace("%20", " ");//加上避免空格(%20)產生的錯誤
		
		//Get Mold Data
		conn.rs_title = "SELECT MJZL.mjbh,lbzls.zwsm,kfzl.kfjc,kfzl1.kfjc kfjc1,MJZL.gbbh,MJZL.bz1,MJZL.bz2 FROM MJZL MJZL LEFT JOIN kfzl kfzl ON MJZL.kfdh = kfzl.kfdh LEFT JOIN kfzl kfzl1 ON MJZL.kfdh1 = kfzl1.kfdh LEFT JOIN lbzls lbzls ON MJZL.lbdh = lbzls.lbdh WHERE mjbh = '"+getmjbh+"'";
		ArrayList<MoldData> getMoldDetail = new ArrayList<MoldData>();
		MoldData MoldDatalist;
		conn.Conn_SQL();
		try {
			while(conn.rs.next()){
				String mjbh = conn.rs.getString("mjbh");
				String zwsm = conn.rs.getString("zwsm");
				String kfjc = conn.rs.getString("kfjc");
				String kfjc1 = conn.rs.getString("kfjc1");
				String gbbh = conn.rs.getString("gbbh");
				String bz1 = conn.rs.getString("bz1");
				String bz2 = conn.rs.getString("bz2");
				MoldDatalist = new MoldData(mjbh, zwsm, kfjc, kfjc1, 0, gbbh, bz1, bz2);
				getMoldDetail.add(MoldDatalist);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Get Mold's Size Round
		conn.rs_title = "SELECT size,mjsl FROM MJZLS WHERE mjbh = '"+getmjbh+"'";
		ArrayList<MoldData> getMoldSize = new ArrayList<MoldData>();
		MoldData MoldSize;
		conn.Conn_SQL();
		try {
			while(conn.rs.next()){
				String size = conn.rs.getString("size").replace(" ", "&nbsp;");//加上避免傳到網頁失去空格(&nbsp;)產生錯誤
				Integer mjsl = conn.rs.getInt("mjsl");
				MoldSize = new MoldData("", size, "", "", mjsl, "", "", "");
				getMoldSize.add(MoldSize);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Send MoldDetail & MoldSize
		Map SendData = new HashMap();
		SendData.put(0, getMoldDetail);
		SendData.put(1, getMoldSize);
		
		//Send to Web Page
		resp.setContentType("text/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		//JSON 只能傳遞一組數列,所以用Map把多個數列組合起來一起傳
		out.print(gson.toJson(SendData));
		out.close();
		//super.doPost(req, resp);
	}

}
