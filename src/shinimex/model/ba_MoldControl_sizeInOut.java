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

import com.google.gson.Gson;

import shinimex.controller.MoldData;
import shinimex.controller.MoldData_InOut;


/*
 * @author Eric Chen.,CPY
 * @version Create Time:2016年5月31日
 *
 */

@SuppressWarnings("serial")
public class ba_MoldControl_sizeInOut extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//Get select from WebPage
		String getselect = (req.getParameter("select"));
		
		Conn conn = new Conn();
		Gson gson = new Gson();
		ServletContext servletContext = getServletConfig().getServletContext();
		
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		
		
		//****判斷輸入資料數量
		if(getselect == null){	
			//Get mjbh,size from WebPage
			String mjbh = req.getParameter("mjbh");
			String size = req.getParameter("size");
			
			// Get Mold InOut Record
			//KSYD.DGLB 異動類別	//KSYD.KSDH 異動單號	//KSYD.KSRQ 異動日期	//KSYD.LYDH 廠商  //KSYDS.SH 模具碼  //KSYDS.MSBZ 備註  //KSYDS.SL 入庫數量	//KSYDS.SL1 出庫數量
			conn.rs_title = "SELECT KSYD.DGLB,KSYD.KSDH,KSYD.KSRQ,KSYD.LYDH,KSYDS.SH,KSYDS.MSBZ,KSYDS.SL,KSYDS.SL1 FROM KSYD KSYD,KSYDS KSYDS WHERE KSYD.KSDH = KSYDS.KSDH AND KSYD.ZSDH = '"+size+"' AND KSYDS.CLDH = '"+mjbh+"'";
			ArrayList<MoldData_InOut> moldlist_InOut = new ArrayList<MoldData_InOut>();
			MoldData_InOut moldData_InOut;
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
				
					//異動類別
					String DGLB = null;
					if(conn.rs.getString("DGLB").equals("J")){
						DGLB = "模具入庫";
					}else if(conn.rs.getString("DGLB").equals("K")){
						DGLB = "模具出庫";
					}else if(conn.rs.getString("DGLB").equals("L")){
						DGLB = "模具送修";
					}else if(conn.rs.getString("DGLB").equals("M")){
						DGLB = "模具返修";
					}
					String KSDH = conn.rs.getString("KSDH");//異動單號
					String KSRQ = conn.rs.getString("KSRQ");//異動日期
					String LYDH = conn.rs.getString("LYDH");//廠商
					String SH = conn.rs.getString("SH");//模具碼
					String MSBZ = conn.rs.getString("MSBZ");//備註
					Integer SL = conn.rs.getInt("SL");//入庫數量
					Integer SL1 = conn.rs.getInt("SL1");//出庫數量
					moldData_InOut = new MoldData_InOut(DGLB, KSDH, KSRQ, LYDH, SH, MSBZ, SL, SL1 );
					moldlist_InOut.add(moldData_InOut);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			//Send to Web Page
			resp.setContentType("text/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(moldlist_InOut));
			out.close();
		
		}else{
			//Add New Mold InOut Data
			String select[] = getselect.split(",");	
			
			//取得年月
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			String now = dateFormat.format(cal.getTime());//現在年月日
			String nowMonth = dateFormat.format(cal.getTime()).substring(0, 6);//現在年月
			
			String mjbh = select[0];//模具編號 KSYDS.CLDH
			String size = select[1];//SIZE KSYD.ZSDH
			String newDGLB = select[2];//單據類別 KSYD.DGLB & KSYDS.DGLB
			String newdate_inOut = (select[3]).replace("-", "");//異動日期 KSYD.KSRQ
			String newzszl = select[4];//廠商名稱 KSYD.LYDH
			String newSL = select[5];//數量 KSYDS.SL & KSYDS.SL1 
			String newBZ = select[6];//備註  KSYD.BZ
			String newmoldCode = select[7];//模具碼 KSYDS.SH
			String KSDH = null;//異動單號 KSYD.KSDH & KSYDS.KSDH
			int SL = 0;//入庫 KSYDS.SL
			int SL1 = 0;//出庫 KSYDS.SL1
			int old_mjsl = 0;//舊模具數量 MJZLS.mjsl
			
			//Trans newDGLB
			switch(newDGLB){
				case "模具入庫":
					newDGLB = "J";
					break;
				case "模具出庫":
					newDGLB = "K";
					break;
				case "模具送修":
					newDGLB = "L";
					break;
				case "模具返修":
					newDGLB = "M";
					break;
			}
			
			//Get Last KSYD.KSDH
			conn.rs_title = "SELECT MAX(KSDH) maxKSDH FROM KSYD WHERE KSDH like '"+newDGLB+newdate_inOut.substring(2, 6)+"%'";
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					KSDH = conn.rs.getString("maxKSDH");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(KSDH == null){
				KSDH = newDGLB + nowMonth.substring(2) + "00001";
			}else{
				//ex:K160600001
				int temp = Integer.parseInt(KSDH.substring(5)) + 1;
				KSDH = newDGLB + nowMonth.substring(2) + String.format("%05d", temp);//String.format不足位數補0
			}
			
			//get zszl.zsjc to zszl.zsdh for KSYD.LYDH
			conn.rs_title = "SELECT zsdh FROM zszl WHERE zsjc = '"+newzszl+"'";
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					newzszl = conn.rs.getString("zsdh");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//判斷模具進出
			if(newDGLB == "J" || newDGLB == "M"){//入庫 IN				
				SL = Integer.parseInt(newSL);
			}else{//出庫 OUT
				SL1 = Integer.parseInt(newSL);
			}
			
			//取得原有模具數量
			conn.rs_title = "SELECT mjsl FROM MJZLS WHERE mjbh = '"+mjbh+"' AND size = '"+size+"'";
			conn.Conn_SQL();
			try {
				while (conn.rs.next()) {
					old_mjsl = conn.rs.getInt("mjsl");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//模具轉出時檢查原有數量是否不足以轉出
			if(SL1 > 0){//轉出檢查
				
				old_mjsl = old_mjsl - SL1;
				//模具數量不足以轉出
				if(old_mjsl < 0){
					//Send Error to WebPage
					String getMoldSize = "error";
					//Send MoldSize to Web Page
					resp.setContentType("text/json; charset=UTF-8");
					PrintWriter out = resp.getWriter();
					out.print(gson.toJson(getMoldSize));
					out.close();
				}else{//模具數量可以轉出
					
					//更新模具數量
					conn.rs_title = "UPDATE MJZLS SET mjsl = '"+old_mjsl+"' WHERE mjbh = '"+mjbh+"' AND size = '"+size+"'";
					conn.Conn_Update();
					
					//Insert Data to KSYD
					conn.rs_title = "INSERT INTO KSYD (DGLB,CQDH,KSDH,KSRQ,LYLB,LYDH,BZ,USERID,USERDATE,ZSDH) VALUES('"+newDGLB+"','S02','"+KSDH+"','"+newdate_inOut+"','J','"+newzszl+"','','SUPER','"+now+"','"+size+"')";
					conn.Conn_Update();
					
					//Insert Data to KSYDS
					conn.rs_title = "INSERT INTO KSYDS (DGLB,KSDH,CQDH,SH,CLDH,MSBZ,SL,DJ,GR,SL1,GR1,NY,USERID,USERDATE) VALUES('"+newDGLB+"','"+KSDH+"','S02','"+newmoldCode+"','"+mjbh+"','"+newBZ+"',"+SL+",NULL,NULL,"+SL1+",NULL,'"+nowMonth.substring(2)+"','SUPER','"+now+"')";
					conn.Conn_Update();
					
					//Renew Page
					//*****資料寫入結束後、將新的資料再一次送入Size Round以便更新、這段程式碼是拷貝ba_MoldControl_getData的要注意*****
					//Get Mold's Size Round
					conn.rs_title = "SELECT size,mjsl FROM MJZLS WHERE mjbh = '"+mjbh+"'";
					ArrayList<MoldData> getMoldSize = new ArrayList<MoldData>();
					MoldData MoldSize;
					conn.Conn_SQL();
					try {
						while(conn.rs.next()){
							size = conn.rs.getString("size").replace(" ", "&nbsp;");//加上避免傳到網頁失去空格(&nbsp;)產生錯誤
							Integer mjsl = conn.rs.getInt("mjsl");
							MoldSize = new MoldData("", size, "", "", mjsl, "", "", "");
							getMoldSize.add(MoldSize);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//Send MoldSize to Web Page
					resp.setContentType("text/json; charset=UTF-8");
					PrintWriter out = resp.getWriter();
					out.print(gson.toJson(getMoldSize));
					out.close();
					
				}
			}else{//轉入
				
				//加上模具數量
				old_mjsl = old_mjsl + SL;
				
				//更新模具數量
				conn.rs_title = "UPDATE MJZLS SET mjsl = '"+old_mjsl+"' WHERE mjbh = '"+mjbh+"' AND size = '"+size+"'";
				conn.Conn_Update();
				
				//Insert Data to KSYD
				conn.rs_title = "INSERT INTO KSYD (DGLB,CQDH,KSDH,KSRQ,LYLB,LYDH,BZ,USERID,USERDATE,ZSDH) VALUES('"+newDGLB+"','S02','"+KSDH+"','"+newdate_inOut+"','J','"+newzszl+"','','SUPER','"+now+"','"+size+"')";
				conn.Conn_Update();
				
				//Insert Data to KSYDS
				conn.rs_title = "INSERT INTO KSYDS (DGLB,KSDH,CQDH,SH,CLDH,MSBZ,SL,DJ,GR,SL1,GR1,NY,USERID,USERDATE) VALUES('"+newDGLB+"','"+KSDH+"','S02','"+newmoldCode+"','"+mjbh+"','"+newBZ+"',"+SL+",NULL,NULL,"+SL1+",NULL,'"+nowMonth.substring(2)+"','SUPER','"+now+"')";
				conn.Conn_Update();
				
				//Renew Page
				//*****資料寫入結束後、將新的資料再一次送入Size Round以便更新、這段程式碼是拷貝ba_MoldControl_getData的要注意*****
				//Get Mold's Size Round
				conn.rs_title = "SELECT size,mjsl FROM MJZLS WHERE mjbh = '"+mjbh+"'";
				ArrayList<MoldData> getMoldSize = new ArrayList<MoldData>();
				MoldData MoldSize;
				conn.Conn_SQL();
				try {
					while(conn.rs.next()){
						size = conn.rs.getString("size").replace(" ", "&nbsp;");//加上避免傳到網頁失去空格(&nbsp;)產生錯誤
						Integer mjsl = conn.rs.getInt("mjsl");
						MoldSize = new MoldData("", size, "", "", mjsl, "", "", "");
						getMoldSize.add(MoldSize);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//Send MoldSize to Web Page
				resp.setContentType("text/json; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.print(gson.toJson(getMoldSize));
				out.close();
				
			}
		}
		
		
		
		
//		super.doPost(req, resp);
	}
}
