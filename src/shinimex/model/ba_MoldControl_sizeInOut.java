package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		
		//Get mjbh,size from WebPage
		String getselect = (req.getParameter("select"));
		String select[] = getselect.split(",");		
		
		Conn conn = new Conn();
		Gson gson = new Gson();
		ServletContext servletContext = getServletConfig().getServletContext();
		
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		
		
		//****判斷輸入資料數量
		if(select.length < 3){	
			String mjbh = select[0].replace("%20", " ");//加上避免空格(%20)產生的錯誤
			String size = select[1].replace("%20", " ");//加上避免空格(%20)產生的錯誤
		
		// Get Mold InOut Record
		//KSYD.DGLB 異動類別	//KSYD.KSDH 異動單號	//KSYD.KSRQ 異動日期	//KSYDS.SL 入庫數量	//KSYDS.SL1 出庫數量
		conn.rs_title = "SELECT KSYD.DGLB,KSYD.KSDH,KSYD.KSRQ,KSYDS.SL,KSYDS.SL1 FROM KSYD KSYD,KSYDS KSYDS WHERE KSYD.KSDH = KSYDS.KSDH AND KSYD.ZSDH = '"+size+"' AND KSYDS.CLDH = '"+mjbh+"'";
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
				}
				String KSDH = conn.rs.getString("KSDH");//異動單號
				String KSRQ = conn.rs.getString("KSRQ");//異動日期
				Integer SL = conn.rs.getInt("SL");//入庫數量
				Integer SL1 = conn.rs.getInt("SL1");//出庫數量
				moldData_InOut = new MoldData_InOut(DGLB, KSDH, KSRQ, SL, SL1);
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
			String mjbh = select[0].replace("%20", " ");//加上避免空格(%20)產生的錯誤
			String size = select[1].replace("%20", " ");//加上避免空格(%20)產生的錯誤
			String newDGLB = select[2];
			String newdate_inOut = select[3];
			String newzszl = select[4];
			String newSL = select[5];
			String newBZ = select[6];
			
			System.out.println(mjbh);
			System.out.println(size);
			System.out.println(newDGLB);
			System.out.println(newdate_inOut);
			System.out.println(newzszl);
			System.out.println(newSL);
			System.out.println(newBZ);
			
		}
		
		
		
		
//		super.doPost(req, resp);
	}
}
