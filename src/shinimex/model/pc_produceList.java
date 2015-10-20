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

import shinimex.controller.produceList;

import com.google.gson.Gson;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年10月12日
 *
 */
@SuppressWarnings("serial")
public class pc_produceList extends HttpServlet {
	
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
		
		//傳遞 GSON
		ArrayList<produceList> produceListData = new ArrayList<produceList>();
		produceList produceList = new produceList();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		// 取得今年年份
		Calendar cal = Calendar.getInstance();
		String yyyyMMdd = (dateFormat.format(cal.getTime()));//Today
		
		String getscanCode = req.getParameter("scanCode");
		//從jQuery傳來的字串如果有空格會變成%20、將其還原成空格
		String scanCode = getscanCode.replaceAll("%20", " ");
		String Xiexing = scanCode.substring(0, 5);
		String SheHao = scanCode.substring(5, 9);
		String Size = scanCode.substring(9);
		int oktosave = 0;//若是0則生產雙數＞訂單雙數、不為0則正常
		String idNo = null;//確認DDSCS.CHBH 編號
		
		//取得訂單雙數 和 生產雙數 以便確認是否合理
		conn.rs_title = "SELECT SUM(Pairs) sumPairs,SUM(QtySC) sumQtySC FROM DDZL WHERE DDRQ >= '20150101' AND Xiexing like '%"+Xiexing+"' AND SheHao like '%"+SheHao+"' GROUP BY Xiexing,SheHao";
//		System.out.println(conn.rs_title);
		conn.Conn_SQL();
		try {
			while(conn.rs.next()){
				if(conn.rs.getDouble("sumPairs") > conn.rs.getDouble("sumQtySC")){
					oktosave +=1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//確認訂單雙數 ＞ 生產雙數、若生產雙數已超過訂單雙數則送出警告、若正常則確認CHBH編號
		if(oktosave != 0){
			//確認DDSCS.CHBH 編號
			conn.rs_title = "SELECT TOP 1 * FROM DDSCS WHERE CHBH LIKE '999999%' ORDER BY CHBH DESC";
			conn.Conn_SQL();
			try {
				if(conn.rs.next()){
					String no = String.valueOf(Integer.parseInt(conn.rs.getString("CHBH").substring(6)) + 1);
					//將取得的序號補足成4位數
					idNo = "999999"+String.format("%04d", Long.parseLong(no));
				}else{
					idNo = "9999990001";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			//將條碼資料寫入DDSCS
			conn.rs_title = "INSERT DDSCS VALUES ('"+idNo+"','S02','"+Size+"','"+Xiexing+SheHao+"',10,'SUPER','"+yyyyMMdd+"')";
//			System.out.println(conn.rs_title);
			conn.Conn_Update();
			
			conn.rs_title = "SELECT * FROM DDSCS WHERE CHBH LIKE '999999%' AND USERDATE = '"+yyyyMMdd+"' ORDER BY CHBH DESC";
			conn.Conn_SQL();
			try {
				while(conn.rs.next()){
					String getXiexing = conn.rs.getString("DDBH").substring(0, 5);
					String getSheHao = conn.rs.getString("DDBH").substring(5);
					String getSize = conn.rs.getString("CC");
					Double getQty = conn.rs.getDouble("Qty");
//					System.out.println(getXiexing+","+getSheHao+","+getSize+","+getQty);
					produceList = new produceList(getXiexing, getSheHao, getSize, getQty);
					produceListData.add(produceList);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			System.out.println("notOK");
		}
		
		
		
		
		
		//send to Web Page
		resp.setContentType("text/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(gson.toJson(produceListData));
		out.close();
		
//		super.doPost(req, resp);
	}
}
