package shinimex.model;

import shinimex.controller.*;

import java.io.IOException;
import java.text.DateFormat;
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
 * @author Eric Chen.,CPY
 * @version Create Time:2015年9月7日
 * 2015-09-10  自動更新歷史單價(先檢查clzl_dj.yyyymm if <> now, 取得現在月份-1 add to clzl_dj)
 * 2015-09-10  自動更新歷史單價(如果今日 >= 20 && clzl_dj沒有本月單價.則將本月的寫入歷史單價中)
 */
@SuppressWarnings("serial")
public class EditFormula extends HttpServlet {
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

		// 取得年月
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String now = dateFormat.format(cal.getTime());// 現在年月日
		// String nowYear = dateFormat.format(cal.getTime()).substring(0,
		// 4);//現在年
		String nowMonth = dateFormat.format(cal.getTime()).substring(0, 6);// 現在年月
		String nowDate = dateFormat.format(cal.getTime()).substring(6, 8);// 現在日
		cal.add(Calendar.MONTH, -1);// 現在月份-1
		String beforenowMonth = dateFormat.format(cal.getTime())
				.substring(0, 6);// 前一個月

		// 列出所有配方 VN
		conn.rs_title = "SELECT cldh,zwpm,ywpm,cldj FROM CLZL WHERE cldh like 'VN%'";

		// Formula(全部配方列表)
		ArrayList<ClzlData> clzlList = new ArrayList<ClzlData>();
		ClzlData clzlData;
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				String cldh = conn.rs.getString("cldh");
				String zwpm = conn.rs.getString("zwpm");
				String ywpm = conn.rs.getString("ywpm");
				Double cldj = conn.rs.getDouble("cldj");
				clzlData = new ClzlData(cldh, zwpm, ywpm, cldj);
				clzlList.add(clzlData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 取得第一個配方送到頁面去當預設畫面
		conn.rs_title = "SELECT cldh,lb,cldhz,clyl,phr,cldj FROM clzlsl WHERE cldh ='"
				+ clzlList.get(0).getCldh() + "'";

		// private String cldh;
		// private String lb;
		// private String cldhz;
		// private Double clyl;
		// private Double phr;
		// private Double cldj;

		System.out.println(conn.rs_title);

		// for(ClzlData clzlData1:clzlList){
		// System.out.println(clzlData1.getCldh()+","+clzlData1.getZwpm()+","+clzlData1.getYwpm()+","+clzlData1.getCldj());
		// }

		// =====背景更新程式 Start=====

		// ***歷史單價更新 Start***
		// 檢查clzl_dj是否有本月歷史單價
		conn.rs_title = "SELECT COUNT(*) FROM clzl_dj WHERE yyyymm = '"
				+ nowMonth + "'";
		int checknowMonth = 0;
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				checknowMonth = conn.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (checknowMonth == 0) {
			// 檢查clzl_dj是否有本月-1歷史單價
			conn.rs_title = "SELECT COUNT(*) FROM clzl_dj WHERE yyyymm = '"
					+ beforenowMonth + "'";
			int checkbeforenowMonth = 0;
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					checkbeforenowMonth = conn.rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (checkbeforenowMonth == 0) {
				// 將目前資料庫中的原枓 & 配方單價當做前一個月歷史單價寫入 clzl_dj
				conn.rs_title = "INSERT INTO clzl_dj SELECT cldh,'"
						+ beforenowMonth
						+ "',cldj FROM CLZL WHERE cldj <> 0 and cllb = 'A' or cldh like 'VN%'";
				conn.Conn_Update();
			} else {
			}
			// 將nowDate String 轉為 Integer
			int int_nowDate = Integer.parseInt(nowDate);
			// 如果現在日期在每個月20號後.將目前資料庫中的原料 & 配方單價寫入 clzl_dj
			if (int_nowDate > 20) {
				conn.rs_title = "INSERT INTO clzl_dj SELECT cldh,'"
						+ nowMonth
						+ "',cldj FROM CLZL WHERE cldj <> 0 and cllb = 'A' or cldh like 'VN%'";
				conn.Conn_Update();
			} else {
			}
		} else {
		}
		// ***歷史單價更新 End***

		// ***檢查VN有無WXYZ Start***
		// 取得所有配方編號
		ArrayList<String> V_cldh = new ArrayList<String>();
		conn.rs_title = "SELECT cldh FROM CLZL WHERE cldh like 'VN%'";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				V_cldh.add(conn.rs.getString("cldh"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 檢查每個配方的WXYZ是否資料完整
		// 2015-09-11 這部份檢查應該只用一次而已.在程惑上線後把這部份取消掉.避免讀取速度過慢
		for (String checkV_cldh : V_cldh) {
			conn.rs_title = "SELECT COUNT(*) FROM CLZL WHERE cldh LIKE '%"
					+ checkV_cldh.substring(1) + "%' AND cllb <> 'V'";
			conn.Conn_SQL();

			try {
				while (conn.rs.next()) {
					// 2015-09-11 資料庫檢查都是全缺.所以直接用 <4 來判斷.補入WXYZ資料
					if (conn.rs.getInt(1) < 4) {
						// Add W to CLZL
						conn.rs_title = "INSERT INTO CLZL (cldh,cllb,dwbh,zsdh,USERID,USERDATE) VALUES('"
								+ "W"
								+ checkV_cldh.substring(1)
								+ "','W','KGS',0,'SUPER','" + now + "')";
						conn.Conn_Update();
						// Add X to CLZL
						conn.rs_title = "INSERT INTO CLZL (cldh,cllb,dwbh,zsdh,USERID,USERDATE) VALUES('"
								+ "X"
								+ checkV_cldh.substring(1)
								+ "','X','KGS',0,'SUPER','" + now + "')";
						conn.Conn_Update();
						// Add Y to CLZL
						conn.rs_title = "INSERT INTO CLZL (cldh,cllb,dwbh,zsdh,USERID,USERDATE) VALUES('"
								+ "Y"
								+ checkV_cldh.substring(1)
								+ "','Y','KGS',0,'SUPER','" + now + "')";
						conn.Conn_Update();
						// Add Z to CLZL
						conn.rs_title = "INSERT INTO CLZL (cldh,cllb,dwbh,zsdh,USERID,USERDATE) VALUES('"
								+ "Z"
								+ checkV_cldh.substring(1)
								+ "','Z','KGS',0,'SUPER','" + now + "')";
						conn.Conn_Update();
						// ADD W of V to clzlsz
						conn.rs_title = "INSERT INTO clzlsz(cldh,lb,cldhz,USERID,USERDATE) VALUES('"
								+ checkV_cldh
								+ "','W','"
								+ "W"
								+ checkV_cldh.substring(1)
								+ "','SUPER','"
								+ now + "')";
						conn.Conn_Update();
						// ADD X of V to clzlsz
						conn.rs_title = "INSERT INTO clzlsz(cldh,lb,cldhz,USERID,USERDATE) VALUES('"
								+ checkV_cldh
								+ "','X','"
								+ "X"
								+ checkV_cldh.substring(1)
								+ "','SUPER','"
								+ now + "')";
						conn.Conn_Update();
						// ADD Y of V to clzlsz
						conn.rs_title = "INSERT INTO clzlsz(cldh,lb,cldhz,USERID,USERDATE) VALUES('"
								+ checkV_cldh
								+ "','Y','"
								+ "Y"
								+ checkV_cldh.substring(1)
								+ "','SUPER','"
								+ now + "')";
						conn.Conn_Update();
						// ADD Z of V to clzlsz
						conn.rs_title = "INSERT INTO clzlsz(cldh,lb,cldhz,USERID,USERDATE) VALUES('"
								+ checkV_cldh
								+ "','Z','"
								+ "Z"
								+ checkV_cldh.substring(1)
								+ "','SUPER','"
								+ now + "')";
						conn.Conn_Update();
					} else {
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ***檢查VN有無WXYZ End***

		// ***Update clzlsl.cldj && clzlsl.cldj && CLZL.cldj of VN Start***
		// 檢查今天更新過單價了沒有?修件是在clzlsl.USERDATE == now
		conn.rs_title = "SELECT TOP 1 USERDATE FROM clzlsl ORDER BY USERDATE DESC";
		conn.Conn_SQL();
		try {
			while (conn.rs.next()) {
				// 這裡有一個新的not equals用法
				if (!conn.rs.getString(1).equals(now)) {
					// Update clzlsl 中的所有原物料單價
					conn.rs_title = "UPDATE clzlsl SET clzlsl.cldj = CLZL.cldj,USERID = 'SUPER',USERDATE = '"
							+ now
							+ "' FROM CLZL WHERE clzlsl.cldhz = CLZL.cldh and clzlsl.cldh like 'VN%'";
					conn.Conn_Update();
					// Update clzlsz 中的所有原物料單價
					conn.rs_title = "UPDATE clzlsz SET clzlsz.cldj = CLZL.cldj,USERID = 'SUPER',USERDATE = '"
							+ now
							+ "' FROM CLZL WHERE clzlsz.cldhz = CLZL.cldh and clzlsz.cldh like 'WN%' or clzlsz.cldh like 'XN%' or clzlsz.cldh like 'YN%' or clzlsz.cldh like 'ZN%'";
					conn.Conn_Update();
					// Update CLZL 中的所有 VN 單價
					for (String checkV_cldh : V_cldh) {
						conn.rs_title = "UPDATE CLZL SET CLZL.cldj = (SELECT SUM(clzlsl.clyl*clzlsl.cldj)/SUM(clzlsl.clyl) FROM clzlsl WHERE clzlsl.cldh = '"
								+ checkV_cldh
								+ "'),USERID = 'SUPER',USERDATE = '"
								+ now
								+ "' WHERE CLZL.cldh ='" + checkV_cldh + "'";
						conn.Conn_Update();
					}
				} else {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ***Update clzlsl.cldj && clzlsl.cldj && CLZL.cldj of VN End***

		// =====背景更新程式 End=====

		session.setAttribute("clzlList", clzlList);
		resp.sendRedirect("editFormula.jsp");
		// super.doGet(req, resp);
	}
}
