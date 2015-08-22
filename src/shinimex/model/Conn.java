package shinimex.model;

import java.sql.*;

import javax.swing.JOptionPane;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年1月30日
 * @承接 2012-June-20 版本、並改寫自 web.xml 讀取資料
 * @承接 2012-June-20 版本、新增Update
 * @在JDK7之後，新增了嘗試關閉資源（try-with-resources）語法、不用自己close
 */
public class Conn {
	String Driver = "net.sourceforge.jtds.jdbc.Driver";
	String dataBase;
	String databaseName;
	String user;
	String pw;
	
	Connection MyConn = null;
	Statement stmt = null;
	String rs_title = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd;

	public void Conn_SQL() {
		try {
			String strDB = "jdbc:jtds:sqlserver://" + dataBase + "/"
					+ databaseName;
			Class.forName(Driver);
			MyConn = DriverManager.getConnection(strDB, user, pw);
			stmt = MyConn.createStatement();
			rs = stmt.executeQuery(rs_title);
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null,
					"ClassNotFound\n" + cnfe.toString(), "Message",
					JOptionPane.WARNING_MESSAGE);
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null,
					"System set error\n" + sqle.toString(), "Message",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void Conn_Update() {
		try {
			String strDB = "jdbc:jtds:sqlserver://" + dataBase + "/"
					+ databaseName;
			Class.forName(Driver);
			MyConn = DriverManager.getConnection(strDB, user, pw);
			stmt = MyConn.createStatement();
			stmt.executeUpdate(rs_title);
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null,
					"ClassNotFound\n" + cnfe.toString(), "Message",
					JOptionPane.WARNING_MESSAGE);
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "System set error\n" + sqle.toString(),
					"Message", JOptionPane.WARNING_MESSAGE);
		}
	}

}
