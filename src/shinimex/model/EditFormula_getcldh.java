package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shinimex.controller.ClzlDatalist;

import com.google.gson.Gson;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年9月22日
 *
 */
@SuppressWarnings("serial")
public class EditFormula_getcldh extends HttpServlet {
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
		
		// cldh 取得點選的配方代號
		String getcldh = req.getParameter("cldh");

		//Get cldj,TotKgs from CLZL
		/*
		conn.rs_title = "SELECT cldj,TotKgs FROM CLZL WHERE cldh = '"+getcldh+"'";
		ArrayList<Double> cldhbasicData = new ArrayList<Double>();
		ArrayList<testClzlA> cldhbasicData = new ArrayList<testClzlA>();
		testClzlA testClzlA = new testClzlA();
		conn.Conn_SQL();
		try {
			while(conn.rs.next()){
				Double cldj = conn.rs.getDouble("cldj");
				Double TotKgs = conn.rs.getDouble("TotKgs");
				cldhbasicData.add(cldj);
				cldhbasicData.add(TotKgs);
				cldhbasicData.add(testClzlA);
				cldhbasicData.add(conn.rs.getString("cldj"));
				cldhbasicData.add(conn.rs.getString("TotKgs"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
//		2015-Sep-05
//		詢問吳文峰:
//			配方基本資料的LIST中配方量%算法是 -- W(膠類)總合重量為100%,其他的再除以W的總和
//			ex:
//			BR-150 + SBR-1502 +3L = 38kg(W總重)
//			ZQ-356 用量 20kg, 配方量 = 20/38*100% = 52.632%
//			所以配方量總合%一定會超過100%
//		USD單價 = 總金額/總配方kg
//		一手重量 = 扣掉Z的總配方重量		
		
		
		//GET lb.cldhz.zwpm.phr.clyl.cldj.amount FOR Formula List
		conn.rs_title = "SELECT clzlsl.lb,clzlsl.cldhz,CLZL.zwpm,clzlsl.phr,clzlsl.clyl,clzlsl.cldj,clzlsl.clyl*clzlsl.cldj amount FROM clzlsl clzlsl,CLZL CLZL WHERE clzlsl.cldh ='"+getcldh+"' AND clzlsl.cldhz = CLZL.cldh";
//		System.out.println(conn.rs_title);
		ClzlDatalist clzlDatalist;
		ArrayList<ClzlDatalist> cldhlistData = new ArrayList<ClzlDatalist>();
		
		conn.Conn_SQL();
		try {
			while(conn.rs.next()){
				String lb = conn.rs.getString("lb");
				String cldhz = conn.rs.getString("cldhz");
				String zwpm = conn.rs.getString("zwpm");
				Double phr = conn.rs.getDouble("phr");
				Double clyl = conn.rs.getDouble("clyl");
				Double listcldj = conn.rs.getDouble("cldj");
				Double amount = conn.rs.getDouble("amount");
				clzlDatalist = new ClzlDatalist(lb, cldhz, zwpm, phr, clyl, listcldj, amount);
				cldhlistData.add(clzlDatalist);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
				
		


				
		
		resp.setContentType("text/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
//		out.printf(gson.toJson(cldhbasicData), gson.toJson(cldhlistData));
		out.print(gson.toJson(cldhlistData));
		out.close();
		
//		super.doPost(req, resp);
	}
}
