package shinimex.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年8月27日
 *
 */
@SuppressWarnings("serial")
public class DeleteData extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//orderStatus 刪除條件
		String getRules = req.getParameter("orderStatusDel");
		String rules[] = getRules.split(",");
		
		
		for(String data:rules){
			
			switch(data.substring(0, 4)){
			case "ddrq":
				System.out.println(data.substring(4));
				break;
			case "ddjq":
				System.out.println(data.substring(4));
				break;
			case "chrq":
				System.out.println(data.substring(4));
				break;
			}
		}
		
				
		
//		super.doPost(req, resp);
	}
}
