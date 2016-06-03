package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import shinimex.controller.MoldData;


/*
 * @author Eric Chen.,CPY
 * @version Create Time:2016年5月26日
 */

@SuppressWarnings("serial")
public class ba_MoldControl extends HttpServlet {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Conn conn = new Conn();
		Gson gson = new Gson();
		ServletContext servletContext = getServletConfig().getServletContext();
		
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		
		String mjbh_choice = req.getParameter("mjbh_choice");//mjbh_choice
		String KHDH_choice = req.getParameter("KHDH_choice");//KHDH_choice
		
		// Mold(全部模具列表)
		ArrayList<MoldData> moldList = new ArrayList<MoldData>();
		MoldData moldData;
		HashSet<String> getallkfjc = new HashSet<String>();//所有客戶、使用HashSet可排除重複資料
		TreeSet<String> allkfjc = new TreeSet<String>(getallkfjc);  
		allkfjc.comparator();//HashSet 排序
		int summjsl = 0;
		
		// 列出所有模具資料
		if((mjbh_choice == null && KHDH_choice == null) || (mjbh_choice == "" && KHDH_choice == "") ){
			conn.rs_title = "SELECT MJZL.mjbh,MJZL.lbdh,kfzl.kfjc,kfzl1.kfjc kfjc1,sum(MJZLS.mjsl) mjsl,MJZL.gbbh FROM MJZL MJZL LEFT JOIN kfzl kfzl ON MJZL.kfdh = kfzl.kfdh LEFT JOIN kfzl kfzl1 ON MJZL.kfdh1 = kfzl1.kfdh LEFT JOIN MJZLS MJZLS ON MJZL.mjbh = MJZLS.mjbh GROUP BY MJZL.mjbh,MJZL.lbdh,kfzl.kfjc,kfzl1.kfjc,MJZL.gbbh";
			
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					String mjbh = conn.rs.getString("mjbh");//模具編號
					String lbdh = conn.rs.getString("lbdh");//模具類型
					String kfjc = conn.rs.getString("kfjc");//客戶簡稱
					allkfjc.add(conn.rs.getString("kfjc"));
					String kfjc1 = conn.rs.getString("kfjc1");//鞋廠簡稱
					Integer mjsl = conn.rs.getInt("mjsl");//模具數量
					summjsl = summjsl + conn.rs.getInt("mjsl");//模具數量總合
					String gbbh = conn.rs.getString("gbbh");//鞋碼國別
					moldData = new MoldData(mjbh, lbdh, kfjc, kfjc1, mjsl, gbbh, "", "");
					moldList.add(moldData);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(mjbh_choice == null && KHDH_choice == null){
				//Send Data Original
				session.setAttribute("moldList", moldList);
				session.setAttribute("summjsl", summjsl);
				session.setAttribute("allkfjc", allkfjc);
				resp.sendRedirect("ba_MoldControl.jsp");
			}else{
				//Send moldList & summjsl & allkfjc
				Map SendData = new HashMap();
				SendData.put(0, moldList);
				SendData.put(1, summjsl);
				SendData.put(2, allkfjc);
				
				//Send to Web Page for append
				resp.setContentType("text/json; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				//JSON 只能傳遞一組數列,所以用Map把多個數列組合起來一起傳
				out.print(gson.toJson(SendData));
				out.close();
			}
			
			
		//模具選擇後列表	
		}else if(KHDH_choice == ""){
			conn.rs_title = "SELECT MJZL.mjbh,MJZL.lbdh,kfzl.kfjc,kfzl1.kfjc kfjc1,sum(MJZLS.mjsl) mjsl,MJZL.gbbh FROM MJZL MJZL LEFT JOIN kfzl kfzl ON MJZL.kfdh = kfzl.kfdh LEFT JOIN kfzl kfzl1 ON MJZL.kfdh1 = kfzl1.kfdh LEFT JOIN MJZLS MJZLS ON MJZL.mjbh = MJZLS.mjbh WHERE MJZL.mjbh = '"+mjbh_choice+"' GROUP BY MJZL.mjbh,MJZL.lbdh,kfzl.kfjc,kfzl1.kfjc,MJZL.gbbh";
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					String mjbh = conn.rs.getString("mjbh");//模具編號
					String lbdh = conn.rs.getString("lbdh");//模具類型
					String kfjc = conn.rs.getString("kfjc");//客戶簡稱
					allkfjc.add(conn.rs.getString("kfjc"));
					String kfjc1 = conn.rs.getString("kfjc1");//鞋廠簡稱
					Integer mjsl = conn.rs.getInt("mjsl");//模具數量
					summjsl = summjsl + conn.rs.getInt("mjsl");//模具數量總合
					String gbbh = conn.rs.getString("gbbh");//鞋碼國別
					moldData = new MoldData(mjbh, lbdh, kfjc, kfjc1, mjsl, gbbh, "", "");
					moldList.add(moldData);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//Send moldList & summjsl & allkfjc
			Map SendData = new HashMap();
			SendData.put(0, moldList);
			SendData.put(1, summjsl);
			SendData.put(2, allkfjc);
			
			//Send to Web Page
			resp.setContentType("text/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			//JSON 只能傳遞一組數列,所以用Map把多個數列組合起來一起傳
			out.print(gson.toJson(SendData));
			out.close();
		//客戶選擇後列表
		}else{
			conn.rs_title = "SELECT MJZL.mjbh,MJZL.lbdh,kfzl.kfjc,kfzl1.kfjc kfjc1,sum(MJZLS.mjsl) mjsl,MJZL.gbbh FROM MJZL MJZL LEFT JOIN kfzl kfzl ON MJZL.kfdh = kfzl.kfdh LEFT JOIN kfzl kfzl1 ON MJZL.kfdh1 = kfzl1.kfdh LEFT JOIN MJZLS MJZLS ON MJZL.mjbh = MJZLS.mjbh WHERE kfzl.kfjc = '"+KHDH_choice+"' GROUP BY MJZL.mjbh,MJZL.lbdh,kfzl.kfjc,kfzl1.kfjc,MJZL.gbbh";
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					String mjbh = conn.rs.getString("mjbh");//模具編號
					String lbdh = conn.rs.getString("lbdh");//模具類型
					String kfjc = conn.rs.getString("kfjc");//客戶簡稱
					allkfjc.add(conn.rs.getString("kfjc"));
					String kfjc1 = conn.rs.getString("kfjc1");//鞋廠簡稱
					Integer mjsl = conn.rs.getInt("mjsl");//模具數量
					summjsl = summjsl + conn.rs.getInt("mjsl");//模具數量總合
					String gbbh = conn.rs.getString("gbbh");//鞋碼國別
					moldData = new MoldData(mjbh, lbdh, kfjc, kfjc1, mjsl, gbbh, "", "");
					moldList.add(moldData);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//Send moldList & summjsl & allkfjc
			Map SendData = new HashMap();
			SendData.put(0, moldList);
			SendData.put(1, summjsl);
			SendData.put(2, allkfjc);
			
			//Send to Web Page
			resp.setContentType("text/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			//JSON 只能傳遞一組數列,所以用Map把多個數列組合起來一起傳
			out.print(gson.toJson(SendData));
			out.close();
		}
		
		//super.doGet(req, resp);
	}
}
