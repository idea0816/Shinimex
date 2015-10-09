package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shinimex.controller.ClzlDatalist;
import shinimex.controller.ClzlDatalist_cldhz;

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
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		// 取得今年年份
		Calendar cal = Calendar.getInstance();
		String yyMM = (dateFormat.format(cal.getTime())).substring(2, 6);
		
		// cldh 取得點選的配方代號
		String getcldh = req.getParameter("cldh");
		// 取得所有原物料 && 庫存量不為 0
		String allcldhofA = req.getParameter("check");
		//取得要新增的原物料資料
		String addcldhz = req.getParameter("addcldhz");
		
		//判斷順序 allcldhofA -> addcldhz -> getcldh
		// 取得所有原物料 && 庫存量不為 0
		if(allcldhofA != null){
			//Get cldh,zwpm,cldj,YYSL For 原物料
			//conn.rs_title = "SELECT CLZL.cldh,CLZL.zwpm,CLZL.cldj,KSYJ.YYSL FROM CLZL CLZL,KSYJ KSYJ WHERE CLZL.cldh = KSYJ.CLDH AND KSYJ.YYSL <> '0' AND KSYJ.NY = '"+yyMM+"' AND CLZL.cllb = 'A' ORDER BY CLZL.cldh";
			conn.rs_title = "SELECT CLZL.cldh,CLZL.zwpm,CLZL.cldj,KSYJ.YYSL FROM CLZL CLZL,KSYJ KSYJ WHERE CLZL.cldh = KSYJ.CLDH AND KSYJ.YYSL <> '0' AND KSYJ.NY = '1509' AND CLZL.cllb = 'A' ORDER BY CLZL.cldh";
			
			//傳遞 GSON
			ArrayList<ClzlDatalist_cldhz> cldhlistData = new ArrayList<ClzlDatalist_cldhz>();
			ClzlDatalist_cldhz clzlDatalist_cldhz;
			conn.Conn_SQL();
			try {
				while(conn.rs.next()){
					String cldh = conn.rs.getString("cldh");
					String zwpm = conn.rs.getString("zwpm");
					Double cldj = conn.rs.getDouble("cldj");
					Double YYSL = conn.rs.getDouble("YYSL");
					clzlDatalist_cldhz = new ClzlDatalist_cldhz(cldh, zwpm, cldj, YYSL);
					cldhlistData.add(clzlDatalist_cldhz);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//send to Web Page
			resp.setContentType("text/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(cldhlistData));
			out.close();			
		}else if(addcldhz != null){
//			2015-Sep-05
//			詢問吳文峰:
//				配方基本資料的LIST中配方量%算法是 -- W(膠類)總合重量為100%,其他的再除以W的總和
//				ex:
//				BR-150 + SBR-1502 +3L = 38kg(W總重)
//				ZQ-356 用量 20kg, 配方量 = 20/38*100% = 52.632%
//				所以配方量總合%一定會超過100%
//			USD單價 = 總金額/總配方kg
//			一手重量 = 扣掉Z的總配方重量	
			
			//CLZL 中修改 cldj.TotKgs.USERDATE
			//clzlsl VN下.新增一筆資料 
			//clzlsz W.X.Y.Z下新增一筆資料
			
			//要新增的4個主要資料-lb.amounts.cldhz.cldh
			String add[] = addcldhz.split(",");
			double wKgs = 0;//W重
			double zKgs = 0;//Z重
			double totKgs = 0;//Total 重
			
			if(add[0].equals("W")){
				wKgs += Double.parseDouble(add[1]);
			}
			
			
			conn.rs_title = "SELECT lb,cldhz,clyl,phr,cldj FROM clzlsl WHERE cldh = '"+add[3]+"'";
			conn.Conn_SQL();
			try {
				while(conn.rs.next()){
					if(conn.rs.getString("lb").equals("W")){
						wKgs += conn.rs.getDouble("clyl");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(wKgs);
			
//			System.out.println(add[0]);
//			System.out.println(add[1]);
//			System.out.println(add[2]);
//			System.out.println(add[3]);
			
		}else{
			//GET lb.cldhz.zwpm.phr.clyl.cldj.amount FOR Formula List
			conn.rs_title = "SELECT clzlsl.lb,clzlsl.cldhz,CLZL.zwpm,clzlsl.phr,clzlsl.clyl,clzlsl.cldj,clzlsl.clyl*clzlsl.cldj amount FROM clzlsl clzlsl,CLZL CLZL WHERE clzlsl.cldh ='"+getcldh+"' AND clzlsl.cldhz = CLZL.cldh";
			//傳遞 GSON
			ArrayList<ClzlDatalist> cldhlistData = new ArrayList<ClzlDatalist>();
			ClzlDatalist clzlDatalist;
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
			
			//send to Web Page
			resp.setContentType("text/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(cldhlistData));
			out.close();
		}

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
		
		/*
		//GET lb.cldhz.zwpm.phr.clyl.cldj.amount FOR Formula List
		conn.rs_title = "SELECT clzlsl.lb,clzlsl.cldhz,CLZL.zwpm,clzlsl.phr,clzlsl.clyl,clzlsl.cldj,clzlsl.clyl*clzlsl.cldj amount FROM clzlsl clzlsl,CLZL CLZL WHERE clzlsl.cldh ='"+getcldh+"' AND clzlsl.cldhz = CLZL.cldh";
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
		*/
		
				
		


				
		
//		resp.setContentType("text/json; charset=UTF-8");
//		PrintWriter out = resp.getWriter();
////		out.printf(gson.toJson(cldhbasicData), gson.toJson(cldhlistData));
//		out.print(gson.toJson(cldhlistData));
//		out.close();
		
//		super.doPost(req, resp);
	}
}
