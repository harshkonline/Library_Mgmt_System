package lims.lims;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * Statement: Creation of Registration bean for taking user requests
 * Author:
 * Date:
 * Description: This bean will search books_inventry table with book_id, book_name
 *              and author, and then appropriately returns result.
 *              Also it will add registration request to books_registration table 
 */

//This bean will be used by user(student)
public class RegistrationBean {
	String book_id;
	String book_name;
	String author;

	// attributes for working with database
	Context context=null;
	DataSource dataSource=null;
	Connection connection=null;
	Statement statement=null;
	String query=null;
	ResultSet resultSet=null;
	ResultSetMetaData metaData=null;

	// default constructor
	public RegistrationBean() {

	}

	// inserts into books_registration table & returns SUCCESS OR FAILURE
	public String registerBook(String book_name) {
		String months[] = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
				"AUG", "SEP", "OCT", "NOV", "DEC" };
		//prepareDB();
		try {
			// initialize context
			context = new InitialContext();

			// look up for data source's jndi name
			dataSource = (DataSource) context.lookup("java:/OracleDS");

			// get new connection
			connection = dataSource.getConnection();

			// create statement
			statement = connection.createStatement();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		// get userid from session
		String user_id = "1234";

		// get transaction id
		String registration_id = getNextRegistrationId();
	
		// generate date in Oracle's format
		Date d = new Date();
		String date = d.getDay() + "-" + months[d.getMonth()] + "-"
				+ (1900+d.getYear());
		
		int bookid = Integer.parseInt(getBookId(book_name));
	
		query = "insert into books_registration_300 values('" + registration_id
				+ "'," + bookid + ",'" + user_id + "','" + date + "')";
		System.out.println(query);
		try {
			int flag=0;
			flag = statement.executeUpdate(query);
			if (flag == 1)
				return "SUCCESS";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "FAILURE";
	}

	// This function will search book by its book_id
	public String searchById(String book_id) {
		// user defined function for initializing database attributes
		prepareDB();
		String bname;
		query = "select book_name,status from books_inventry_300 where book_id="
				+ book_id;
		try {
			resultSet = statement.executeQuery(query);
			String status = resultSet.getString(2);
			// check whether resultSet is empty or not AND book is available
			if (resultSet.next() && (status.equals("y") || status.equals("Y"))) {
				bname=resultSet.getString(1);;
				connection.close();
				// return the book name of corresponding book
				return bname;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "ERROR";
	}

	//get book_id by book_name
	public String getBookId(String bookName)
	{
		prepareDB();
		String bookid=null;
		query="select book_id from books_inventry_300 where book_name='"+bookName+"' AND status='y'";
		try {
			resultSet=statement.executeQuery(query);
			resultSet.next();			
			bookid=resultSet.getString(1);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return 	bookid;			
	}
	// search book by book_name
	public String[] SearchByBookName(String book_name) {
		String status;
		int i = 0;
		String books[] = null;
		// user defined function for initializing database attributes
		prepareDB();

		query = "select book_name,status from books_inventry_300 where book_name like %"
				+ book_name + "%";
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				status = resultSet.getString(2);
				if (status.equals("y") || status.equals("Y")) {
					books[i++] = resultSet.getString(1);
				}
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return books;
	}

	// search book by book_name
	public String[] SearchByAuthorName(String author) {
		String status;
		int i = 0;
		String books[] = null;
		// user defined function for initializing database attributes
		prepareDB();

		// search books by author1
		query = "select book_name,status from books_inventry_300 where author1 like %"
				+ author + "%";
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				status = resultSet.getString(2);
				if (status.equals("y") || status.equals("Y")) {
					books[i++] = resultSet.getString(1);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// And now search books by author2
		query = "select book_name,status from books_inventry_300 where author2 like %"
				+ author + "%";
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				status = resultSet.getString(2);
				if (status.equals("y") || status.equals("Y")) {
					books[i++] = resultSet.getString(1);
				}
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return books;
	}

	// autogenerate registration_id
	public String getNextRegistrationId() {
		int maxid = 1;
		prepareDB();
		query = "select max(registration_id) from books_registration_300";
		try {
			resultSet = statement.executeQuery(query);
			resultSet.next();
			maxid = resultSet.getInt(1);
			maxid++;
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// return next transaction id
		return String.valueOf(maxid);
	}

	// Getter & Setter methods
	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	// initializes
	public void prepareDB() {
		try {
			// initialize context
			context = new InitialContext();

			// look up for data source's jndi name
			dataSource = (DataSource) context.lookup("java:/OracleDS");

			// get new connection
			connection = dataSource.getConnection();

			// create statement
			statement = connection.createStatement();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
