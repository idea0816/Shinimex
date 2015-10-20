package shinimex.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import shinimex.controller.produceList2;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年10月12日
 *
 */
@SuppressWarnings("serial")
public class pc_shipmentList extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Gson gson2 = new Gson();
		
		//傳遞 GSON
		ArrayList<produceList2> shipmentListData = new ArrayList<produceList2>();
		produceList2 produceList = new produceList2();
		
		String getscanCode2 = req.getParameter("scanCode2");
		String Xiexing2 = getscanCode2.substring(0, 6);
		String SheHao2 = getscanCode2.substring(6, 11);
		String Size2 = getscanCode2.substring(11);
		Double qty2 = 10.0;
		
		System.out.println(Xiexing2+","+SheHao2+","+Size2);
		if(Xiexing2.equals("JO-466")){
			System.out.println("OK");
			produceList = new produceList2(Xiexing2, SheHao2, Size2, qty2);
			shipmentListData.add(produceList);
			
			//send to Web Page
			resp.setContentType("text/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(gson2.toJson(shipmentListData));
			out.close();
		}else{
			//send to Web Page
			resp.setContentType("text/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(gson2.toJson(shipmentListData));
			out.close();
		}
		
		
		
		
//		super.doPost(req, resp);
	}
}
