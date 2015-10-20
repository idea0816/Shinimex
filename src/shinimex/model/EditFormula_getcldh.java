package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shinimex.controller.ClzlDatalist;
import shinimex.controller.ClzlDatalist_NDU;
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
		String yyyyMMdd = (dateFormat.format(cal.getTime()));//Today
		
		//數字百分位設定
		DecimalFormat d = new DecimalFormat("###,###,###,###.##");
		
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
			conn.rs_title = "SELECT CLZL.cldh,CLZL.zwpm,CLZL.cldj,KSYJ.YYSL FROM CLZL CLZL,KSYJ KSYJ WHERE CLZL.cldh = KSYJ.CLDH AND KSYJ.YYSL <> '0' AND KSYJ.NY = '"+yyMM+"' AND CLZL.cllb = 'A' ORDER BY CLZL.cldh";
			
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
			
			//要新增的4個主要資料-lb.amounts.cldj.cldhz.cldh
			String add[] = addcldhz.split(",");
			double oldwKgs = 0;//old_W重
			double newwKgs = 0;//new_W重
			double oldzKgs = 0;//old_Z重
			double newzKgs = 0;//new_Z重
			double oldtotKgs = 0;//old_Total(含Z) 重
			double newtotKgs = 0;//new_Total(含Z) 重
			double oldtotPrice = 0;//old_Total Price
			double newtotPrice = 0;//new_Total Price
			double newcldj = 0;//新的配方單價
			double newTotKgsnoz = 0;//新的一手重量(不含Z)
			
			//先將舊的配方表讀下來
			ClzlDatalist_NDU old_clzlsl;
			ArrayList<ClzlDatalist_NDU> old_clzlsl_array = new ArrayList<ClzlDatalist_NDU>();
			
			conn.rs_title = "SELECT lb,cldhz,clyl,phr,cldj,clyl*cldj price FROM clzlsl WHERE cldh = '"+add[4]+"'";
			conn.Conn_SQL();
			try {
				while(conn.rs.next()){
					if(conn.rs.getString("lb").equals("W")){
						oldwKgs += conn.rs.getDouble("clyl");
					}else if(conn.rs.getString("lb").equals("Z")){
						oldzKgs += conn.rs.getDouble("clyl");
					}
					String lb = conn.rs.getString("lb");
					String cldhz = conn.rs.getString("cldhz");
					Double clyl = conn.rs.getDouble("clyl");
						oldtotKgs += conn.rs.getDouble("clyl");
					Double phr = conn.rs.getDouble("phr");
					Double cldj = conn.rs.getDouble("cldj");
					Double price = conn.rs.getDouble("price");
						oldtotPrice += conn.rs.getDouble("price");
					old_clzlsl = new ClzlDatalist_NDU(lb, cldhz, phr, clyl, cldj, price);
					old_clzlsl_array.add(old_clzlsl);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			2015-Sep-05
//			詢問吳文峰:
//				配方基本資料的LIST中phr算法是 -- W(膠類)總合重量為100%,其他的再除以W的總和
//				ex:
//				BR-150 + SBR-1502 +3L = 38kg(W總重)
//				ZQ-356 用量 20kg, 配方量 = 20/38*100% = 52.632%
//				所以配方量總合%一定會超過100%
//			USD單價 = 總金額/總配方kg
//			一手重量 = 扣掉Z的總配方重量	
			
			//加入新增的原物料計算
			if(add[0].equals("W")){
				newwKgs = Double.parseDouble(add[1]) + oldwKgs;
				newzKgs = oldzKgs;
			}else if(add[0].equals("Z")){
				newzKgs = Double.parseDouble(add[1]) + oldzKgs;
				newwKgs = oldwKgs;
			}else{
				newwKgs = oldwKgs;
				newzKgs = oldzKgs;
			}
			newtotKgs = Double.parseDouble(add[1]) + oldtotKgs;
			newtotPrice = Double.parseDouble(add[1]) * Double.parseDouble(add[2]) + oldtotPrice;
			newcldj = newtotPrice / newtotKgs;
			newTotKgsnoz = newtotKgs - newzKgs;
			
			//CLZL 中修改 cldj.TotKgs.USERDATE
			conn.rs_title = "UPDATE CLZL SET cldj = '"+newcldj+"',TotKgs = '"+newTotKgsnoz+"',USERDATE='"+yyyyMMdd+"' WHERE cldh = '"+add[4]+"'";
			conn.Conn_Update();
			
			//update new phr to old cldhz
			double setnewphr = 0;
			for(int i = 0; i < old_clzlsl_array.size(); i++){
				if(old_clzlsl_array.get(i).getLb().equals("W")){
					setnewphr = Double.valueOf(d.format(old_clzlsl_array.get(i).getClyl() / newwKgs * 100));
				}else{
					setnewphr = Double.valueOf(d.format(old_clzlsl_array.get(i).getClyl() / newwKgs * 100));
				}
				//Update newphr to old cldhz of clzlsl
				conn.rs_title = "UPDATE clzlsl SET phr = '"+setnewphr+"' WHERE cldh = '"+add[4]+"' AND cldhz = '"+old_clzlsl_array.get(i).getCldhz()+"'";
				conn.Conn_Update();
				//Update newphr to old cldhz of clzlsz
				String cldhforclzlsz = old_clzlsl_array.get(i).getLb() + add[4].substring(1);
				conn.rs_title = "UPDATE clzlsz SET phr = '"+setnewphr+"' WHERE cldh = '"+cldhforclzlsz+"' AND cldhz = '"+old_clzlsl_array.get(i).getCldhz()+"'";
				conn.Conn_Update();
			}
			//clzlsl VN下.新增資料  //要新增的4個主要資料-lb.amounts.cldj.cldhz.cldh
			setnewphr = Double.valueOf(d.format(Double.valueOf(add[1]) / newwKgs * 100));
			conn.rs_title = "INSERT INTO clzlsl VALUES('"+add[4]+"','"+add[0]+"','"+add[3]+"','"+add[1]+"','"+setnewphr+"','"+add[2]+"','SUPER','"+yyyyMMdd+"')";
			conn.Conn_Update();
			//clzlsz W.X.Y.Z下新增一筆資料
			String cldhforclzlsz = add[0] + add[4].substring(1);
			conn.rs_title = "INSERT INTO clzlsz VALUES('"+cldhforclzlsz+"','"+add[0]+"','"+add[3]+"','"+add[1]+"','"+setnewphr+"','"+add[2]+"','SUPER','"+yyyyMMdd+"')";
			conn.Conn_Update();
			
			
			//*****資料寫入結束後、將新的資料再一次送入配方明細頁面以便更新、這段程式碼是拷貝下面的要注意*****
			conn.rs_title = "SELECT clzlsl.lb,clzlsl.cldhz,CLZL.zwpm,clzlsl.phr,clzlsl.clyl,clzlsl.cldj,clzlsl.clyl*clzlsl.cldj amount FROM clzlsl clzlsl,CLZL CLZL WHERE clzlsl.cldh ='"+add[4]+"' AND clzlsl.cldhz = CLZL.cldh";
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

//		super.doPost(req, resp);
	}
}
