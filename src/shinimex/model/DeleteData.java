package shinimex.model;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年8月27日
 * @2015-08-31 Add UPDATE
 *
 */
@SuppressWarnings("serial")
public class DeleteData extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Conn conn = new Conn();
		ServletContext servletContext = getServletConfig().getServletContext();
		conn.dataBase = servletContext.getInitParameter("dataBase");
		conn.databaseName = servletContext.getInitParameter("databaseName");
		conn.user = servletContext.getInitParameter("user");
		conn.pw = servletContext.getInitParameter("pw");

		// orderStatus 刪除條件
		String getRules = req.getParameter("orderStatusDel");
		if (getRules != null) {
			String rules[] = getRules.split(",");

			for (String data : rules) {
				switch (data.substring(0, 4)) {
				case "ddrq":
					// DELETE DDZL by DDRQ
					try {
						conn.rs_title = "DELETE FROM DDZL WHERE DDBH = '"
								+ data.substring(4) + "'";
						conn.Conn_Update();
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				case "ddjq":
					// DELETE DDZL by DDJQ
					try {
						conn.rs_title = "DELETE FROM DDZL WHERE DDBH = '"
								+ data.substring(4) + "'";
						conn.Conn_Update();
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				case "chrq":
					// DELETE by CHRQ have 3 tables, DDS & DDSC & DDSCS
					// DELETE DDS
					try {
						conn.rs_title = "DELETE FROM DDS WHERE CHBH = '"
								+ data.substring(4) + "'";
						conn.Conn_Update();
					} catch (Exception e) {
						// TODO: handle exception
					}
					// DELETE DDSC
					try {
						conn.rs_title = "DELETE FROM DDSC WHERE CHBH = '"
								+ data.substring(4) + "'";
						conn.Conn_Update();
					} catch (Exception e) {
						// TODO: handle exception
					}
					// DELETE DDSCS
					try {
						conn.rs_title = "DELETE FROM DDSCS WHERE CHBH = '"
								+ data.substring(4) + "'";
						conn.Conn_Update();
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				}
			}
		}

		// updateDate 更新條件
		String getRules2 = req.getParameter("orderStatusUpdate");
		if (getRules2 != null) {
			String rules2[] = getRules2.split(",");

			for (String updateData : rules2) {
				switch (updateData.substring(0, 4)) {
				case "ddrq":
					String updateDDBH = updateData.substring(4,
							updateData.indexOf(":"));
					String newDDRQ = updateData.substring(
							updateData.indexOf(":") + 1).replace("-", "");

					// Update DDRQ
					try {
						conn.rs_title = "UPDATE DDZL SET DDRQ = '" + newDDRQ
								+ "' WHERE DDBH = '" + updateDDBH + "'";
						conn.Conn_Update();
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				case "ddjq":
					String updateDDBH2 = updateData.substring(4,
							updateData.indexOf(":"));
					String newDDJQ = updateData.substring(
							updateData.indexOf(":") + 1).replace("-", "");

					// Update DDJQ
					try {
						conn.rs_title = "UPDATE DDZL SET DDJQ = '" + newDDJQ
								+ "' WHERE DDBH = '" + updateDDBH2 + "'";
						conn.Conn_Update();
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				case "chrq":
					String CHBH = updateData.substring(4,
							updateData.indexOf(":"));
					String newCHRQ = updateData.substring(
							updateData.indexOf(":") + 1).replace("-", "");

					// Update by CHRQ have 2 tables, DDS & DDSC
					// Update DDS
					try {
						conn.rs_title = "UPDATE DDS SET CHRQ = '" + newCHRQ
								+ "' WHERE CHBH = '" + CHBH + "'";
						conn.Conn_Update();
					} catch (Exception e) {
						// TODO: handle exception
					}
					// Update DDSC
					try {
						conn.rs_title = "UPDATE DDSC SET CHRQ = '" + newCHRQ
								+ "' WHERE CHBH = '" + CHBH + "'";
						conn.Conn_Update();
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				}
			}
		}

		// super.doPost(req, resp);
	}
}
