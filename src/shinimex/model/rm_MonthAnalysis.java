package shinimex.model;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

/*
 * @author P.Y Chen.,CPY
 * @version Create Time:2016年7月12日
 *
 */

@SuppressWarnings("serial")
public class rm_MonthAnalysis extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Conn conn = new Conn();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		// orderStatus 取得選擇年
		String choiceyear = req.getParameter("choiceyear");
		// 取得今年年份
		Calendar cal = Calendar.getInstance();
		int year;
		int nowyear;
		if(choiceyear == null){
			year = cal.get(Calendar.YEAR);
			nowyear = cal.get(Calendar.YEAR);
		}else{
			year = Integer.parseInt(choiceyear);
			nowyear = cal.get(Calendar.YEAR);
		}
		
		HttpSession session = req.getSession();
		
		ServletContext servletContext = getServletConfig().getServletContext();
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		
		DecimalFormat df = new DecimalFormat("###,###,###,###.##");// 數字百分位設定
		
		//原料前5名
		ArrayList<String> material_top1 = new ArrayList<String>();// 原料TOP1
		ArrayList<String> material_top2 = new ArrayList<String>();// 原料TOP2
		ArrayList<String> material_top3 = new ArrayList<String>();// 原料TOP3
		ArrayList<String> material_top4 = new ArrayList<String>();// 原料TOP4
		ArrayList<String> material_top5 = new ArrayList<String>();// 原料TOP5
		
		int i_top = 1;//Top 計數器
		conn.rs_title = "SELECT TOP 5 KSYDS.CLDH,CLZL.zwpm,sum(KSYDS.SL1) Out FROM KSYD KSYD,KSYDS KSYDS,CLZL CLZL WHERE KSYD.KSRQ LIKE '"+year+"%' AND KSYD.DGLB = 'O' AND KSYD.KSDH = KSYDS.KSDH AND KSYDS.CLDH like 'A%' AND KSYDS.CLDH = CLZL.cldh GROUP BY KSYDS.CLDH,CLZL.zwpm ORDER BY Out DESC";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				switch(i_top){
					case 1:
						material_top1.add(conn.rs.getString("CLDH"));
						material_top1.add(conn.rs.getString("zwpm"));
						material_top1.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
					case 2:
						material_top2.add(conn.rs.getString("CLDH"));
						material_top2.add(conn.rs.getString("zwpm"));
						material_top2.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
					case 3:
						material_top3.add(conn.rs.getString("CLDH"));
						material_top3.add(conn.rs.getString("zwpm"));
						material_top3.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
					case 4:
						material_top4.add(conn.rs.getString("CLDH"));
						material_top4.add(conn.rs.getString("zwpm"));
						material_top4.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
					case 5:
						material_top5.add(conn.rs.getString("CLDH"));
						material_top5.add(conn.rs.getString("zwpm"));
						material_top5.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
				}
				i_top += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//get material_top clzl_dj data
		for(int i = 1; i <= 5; i++){
			switch(i){
				case 1:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+material_top1.get(0)+"'";
					break;
				case 2:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+material_top2.get(0)+"'";
					break;
				case 3:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+material_top3.get(0)+"'";
					break;
				case 4:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+material_top4.get(0)+"'";
					break;
				case 5:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+material_top5.get(0)+"'";
					break;
			}
			
			conn.Conn_SQL();
			int month = 0;//用於檢查月份
			int checkmonth = 1;//確認月份數量
			double avgnum = 0;//平均月份數(有價格月份的才平均、排除掉價格為0)
			double sumcldj = 0;//價格總和
			try {
				while (conn.rs.next()) {
					switch(i){
						case 1:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								material_top1.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									material_top1.add("");
								}
								material_top1.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
						case 2:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								material_top2.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									material_top2.add("");
								}
								material_top2.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
						case 3:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								material_top3.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									material_top3.add("");
								}
								material_top3.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
						case 4:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								material_top4.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									material_top4.add("");
								}
								material_top4.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
						case 5:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								material_top5.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									material_top5.add("");
								}
								material_top5.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//補齊12個月 & 平圴單價
			switch(i){
				case 1:
					checkmonth -= 1;
					while (checkmonth != 12) {
						material_top1.add("");
						checkmonth += 1;
					}
					material_top1.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
				case 2:
					checkmonth -= 1;
					while (checkmonth != 12) {
						material_top2.add("");
						checkmonth += 1;
					}
					material_top2.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
				case 3:
					checkmonth -= 1;
					while (checkmonth != 12) {
						material_top3.add("");
						checkmonth += 1;
					}
					material_top3.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
				case 4:
					checkmonth -= 1;
					while (checkmonth != 12) {
						material_top4.add("");
						checkmonth += 1;
					}
					material_top4.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
				case 5:
					checkmonth -= 1;
					while (checkmonth != 12) {
						material_top5.add("");
						checkmonth += 1;
					}
					material_top5.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
			}
		}
		//移除原料編號
		material_top1.remove(0);
		material_top2.remove(0);
		material_top3.remove(0);
		material_top4.remove(0);
		material_top5.remove(0);
		
		//配方前5名
		ArrayList<String> formula_top1 = new ArrayList<String>();// 配方TOP1
		ArrayList<String> formula_top2 = new ArrayList<String>();// 配方TOP2
		ArrayList<String> formula_top3 = new ArrayList<String>();// 配方TOP3
		ArrayList<String> formula_top4 = new ArrayList<String>();// 配方TOP4
		ArrayList<String> formula_top5 = new ArrayList<String>();// 配方TOP5
		
		int j_top = 1;//Top 計數器
		conn.rs_title = "SELECT TOP 5 KSYDS.CLDH,CLZL.zwpm,sum(KSYDS.SL1) Out FROM KSYD KSYD,KSYDS KSYDS,CLZL CLZL WHERE KSYD.KSRQ like '"+year+"%' AND KSYD.DGLB = 'O' AND KSYD.KSDH = KSYDS.KSDH AND KSYDS.CLDH like 'VN%' AND KSYDS.CLDH = CLZL.cldh GROUP BY KSYDS.CLDH,CLZL.zwpm ORDER BY Out DESC";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				switch(j_top){
					case 1:
						formula_top1.add(conn.rs.getString("CLDH"));
						formula_top1.add(conn.rs.getString("zwpm"));
						formula_top1.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
					case 2:
						formula_top2.add(conn.rs.getString("CLDH"));
						formula_top2.add(conn.rs.getString("zwpm"));
						formula_top2.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
					case 3:
						formula_top3.add(conn.rs.getString("CLDH"));
						formula_top3.add(conn.rs.getString("zwpm"));
						formula_top3.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
					case 4:
						formula_top4.add(conn.rs.getString("CLDH"));
						formula_top4.add(conn.rs.getString("zwpm"));
						formula_top4.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
					case 5:
						formula_top5.add(conn.rs.getString("CLDH"));
						formula_top5.add(conn.rs.getString("zwpm"));
						formula_top5.add(String.valueOf(df.format(conn.rs.getDouble("Out"))));
						break;
				}
				j_top += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//get formula_top clzl_dj data
		for(int i = 1; i <= 5; i++){
			switch(i){
				case 1:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+formula_top1.get(0)+"'";
					break;
				case 2:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+formula_top2.get(0)+"'";
					break;
				case 3:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+formula_top3.get(0)+"'";
					break;
				case 4:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+formula_top4.get(0)+"'";
					break;
				case 5:
					conn.rs_title = "SELECT * FROM clzl_dj WHERE yyyymm LIKE '"+year+"%' AND cldh = '"+formula_top5.get(0)+"'";
					break;
			}
			
			conn.Conn_SQL();
			int month = 0;//用於檢查月份
			int checkmonth = 1;//確認月份數量
			double avgnum = 0;//平均月份數(有價格月份的才平均、排除掉價格為0)
			double sumcldj = 0;//價格總和
			try {
				while (conn.rs.next()) {
					switch(i){
						case 1:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								formula_top1.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									formula_top1.add("");
								}
								formula_top1.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
						case 2:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								formula_top2.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									formula_top2.add("");
								}
								formula_top2.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
						case 3:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								formula_top3.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									formula_top3.add("");
								}
								formula_top3.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
						case 4:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								formula_top4.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									formula_top4.add("");
								}
								formula_top4.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
						case 5:
							//檢查月份
							month = Integer.parseInt((conn.rs.getString("yyyymm")).substring(4));
							if(month == checkmonth){
								formula_top5.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth+=1;
							}else{
								for(int j = checkmonth; j < month; j++){
									//System.out.println(j);
									formula_top5.add("");
								}
								formula_top5.add(String.valueOf(df.format(conn.rs.getDouble("cldj"))));
								avgnum += 1;
								sumcldj = sumcldj + conn.rs.getDouble("cldj");
								checkmonth = month + 1;
							}
							break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//補齊12個月 & 平圴單價
			switch(i){
				case 1:
					checkmonth -= 1;
					while (checkmonth != 12) {
						formula_top1.add("");
						checkmonth += 1;
					}
					formula_top1.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
				case 2:
					checkmonth -= 1;
					while (checkmonth != 12) {
						formula_top2.add("");
						checkmonth += 1;
					}
					formula_top2.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
				case 3:
					checkmonth -= 1;
					while (checkmonth != 12) {
						formula_top3.add("");
						checkmonth += 1;
					}
					formula_top3.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
				case 4:
					checkmonth -= 1;
					while (checkmonth != 12) {
						formula_top4.add("");
						checkmonth += 1;
					}
					formula_top4.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
				case 5:
					checkmonth -= 1;
					while (checkmonth != 12) {
						formula_top5.add("");
						checkmonth += 1;
					}
					formula_top5.add(String.valueOf(df.format(sumcldj / avgnum)));//加入平均單價
					break;
			}
		}
		//移除原料編號
		formula_top1.remove(0);
		formula_top2.remove(0);
		formula_top3.remove(0);
		formula_top4.remove(0);
		formula_top5.remove(0);
		
		session.setAttribute("year", year);
		session.setAttribute("material_top1", material_top1);
		session.setAttribute("material_top2", material_top2);
		session.setAttribute("material_top3", material_top3);
		session.setAttribute("material_top4", material_top4);
		session.setAttribute("material_top5", material_top5);
		session.setAttribute("formula_top1", formula_top1);
		session.setAttribute("formula_top2", formula_top2);
		session.setAttribute("formula_top3", formula_top3);
		session.setAttribute("formula_top4", formula_top4);
		session.setAttribute("formula_top5", formula_top5);
		resp.sendRedirect("rm_MonthAnalysis.jsp");
		//super.doGet(req, resp);
	}
}
