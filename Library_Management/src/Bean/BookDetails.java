package Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookDetails {
	
	public String[] getBookDetails(int book_id){
		
		
		// declaration of database connectivity classes
		Connection connection = null;
		InitialContext icContext = null;
		DataSource dataSource = null;
		ResultSet resultSet = null;
		Statement statement = null;
		String bookDetails[]=null;
		
		String query=null;
		query="SELECT * FROM books_inventry_300 WHERE book_id="+book_id;
		
			try {
				icContext = new InitialContext();
				dataSource = (DataSource) icContext.lookup("java:/OracleDS");
				connection = dataSource.getConnection();
				statement = connection.createStatement();
				resultSet=statement.executeQuery(query);
				resultSet.next();
				int cols=resultSet.getMetaData().getColumnCount();				
				bookDetails = new String[cols];
				if(resultSet.getString(1).length()==0)
				{
					String []no_books=new String[1];
					no_books[0]="$NO_BOOK$";
					return no_books;
				}
				for (int i = 0; i <cols; i++) {
				
					bookDetails[i]=resultSet.getString(i+1);
				}
				return bookDetails;
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
			
	}
}
