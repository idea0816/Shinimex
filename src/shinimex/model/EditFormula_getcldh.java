package shinimex.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		ServletContext servletContext = getServletConfig().getServletContext();
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");
		
		// cldh 取得點選的配方代號
		String getcldh = req.getParameter("cldh");
		int sum_amount = 0;
		int sum_clyl = 0;
		conn.rs_title = "SELECT clzlsl.lb,clzlsl.cldhz,CLZL.zwpm,clzlsl.phr,clzlsl.clyl,clzlsl.cldj,clzlsl.clyl*clzlsl.cldj amount FROM clzlsl clzlsl,CLZL CLZL WHERE clzlsl.cldh ='"+getcldh+"' AND clzlsl.cldhz = CLZL.cldh";
//		System.out.println(conn.rs_title);
		
//		2015-Sep-05
//		詢問吳文峰:
//			配方基本資料的LIST中配方量%算法是 -- W(膠類)總合重量為100%,其他的再除以W的總和
//			ex:
//			BR-150 + SBR-1502 +3L = 38kg(W總重)
//			ZQ-356 用量 20kg, 配方量 = 20/38*100% = 52.632%
//			所以配方量總合%一定會超過100%
//		USD單價 = 總金額/總配方kg
//		一手重量 = 扣掉Z的總配方重量

//		conn.Conn_SQL();
		
		try {
			//lb.cldhz.zwpm.phr.clyl.cldj.amount.
			while(conn.rs.next()){
				if(!(conn.rs.getString("lb")).equals("Z")){
//					System.out.println(conn.rs.getString("lb"));
				}else{}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
				
//		super.doPost(req, resp);
	}
}
