package cancel;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class cancelRequests {
	public void cancelRequest() {
		String query = "";

		Connection connection = null;
		InitialContext icContext = null;
		DataSource dataSource = null;
		ResultSet resultSet = null;
		Statement statement = null;
		String bookid1[] = null;
		int id_size=0;
		try {
			icContext = new InitialContext();
			dataSource = (DataSource) icContext.lookup("java:/OracleDS");
			connection = dataSource.getConnection();
			statement = connection.createStatement();

			// to update book status in books_inventry			
			query = "select book_id from books_registration_300 where (sysdate-registration_date)>=5 AND (status='y' OR status='Y')";
			resultSet=statement.executeQuery(query);
			while(resultSet.next()) {
				id_size++;
			}
			id_size=0;
			bookid1=new String[id_size];
			resultSet=statement.executeQuery(query);
			while(resultSet.next()) {
				bookid1[id_size]=resultSet.getString(1);
			}
			//update status
			for (int i = 0; i < bookid1.length; i++) {
				query="update books_inventry_300 set status='Y' where book_id="+Integer.parseInt(bookid1[i]);
				statement.executeUpdate(query);
			}
			
			
			
			// now delete old requests
			query = " delete from books_registration_300 where (sysdate-registration_date)>=5 AND (status='y' OR status='Y')";
			statement.executeUpdate(query);
			connection.close();
		} catch (NamingException e) {
			// to be logged
			System.out.println(e.getMessage());

		} catch (SQLException e) {
			// to be logged
			System.out.println(e.getMessage());
		}
	}
}
