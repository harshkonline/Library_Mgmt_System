/*  harshad kulkarni 76101*/

package Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginBean {

	public LoginBean() {
	}

	
	public String returnStudent(){
		return "librarian";
	}
	
	public String chkUser(String username, String password) {

		boolean flag = false;
		boolean flag1 = false;
		int cnt = 0;
		InitialContext ic = null;

		DataSource ds = null;
		ResultSet rs = null;
		Statement s = null;
		Connection conn = null;
		String query = "select * from users_300";
		String query1 = "select * from users_300 where user_name='" + username
				+ "'and password='" + password + "' ";
		String uss = "", pww;
		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:/OracleDS");
			conn = ds.getConnection();
			s = conn.createStatement();
			rs = s.executeQuery(query);
		} catch (Exception e) {
			System.out.println("exception" + e);
		}

		try {
			while (rs.next()) {
				uss = rs.getString(2);
				pww = rs.getString(3);
				System.out.println(uss + "" + pww);
				if ((uss.equals(username)) && (pww.equals(password))) /*
																	 * user
																	 * authentication
																	 */
				{

					// out.println("login successful");
					flag = true;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) /*
				 * if user is authenticated check whether he is librarian or
				 * student
				 */
		{

			try {
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:/OracleDS");
				conn = ds.getConnection();
				s = conn.createStatement();
				rs = s.executeQuery(query1);

			} catch (Exception e) {
				System.out.println("exception" + e);
			}
			try {
				while (rs.next()) {
					uss = rs.getString(5);
					if ((uss.equals("Y")))

						// out.println("login successful");
						return "librarian";

					else if (uss.equals("N"))
						return "student";
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return "input"; /*
						 * if user is not an authenticated user then return
						 * error
						 */

	}

}
