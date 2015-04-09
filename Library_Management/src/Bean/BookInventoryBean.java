package Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import log4j.log4j;

public class BookInventoryBean {

	// declaration of attributes
	int book_id;
	String book_name;
	String author1;
	String author2;
	String publisher;
	String year_of_publication;
	String status;
	String query = "";

	// connect to DataBase
	Connection connection = null;
	InitialContext icContext = null;
	DataSource ds = null;
	ResultSet resultSet = null;
	Statement statement = null;

	//default constructor
	public BookInventoryBean() {

	}
	
	//setter-getter for book details
	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int bookId) {
		book_id = bookId;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String bookName) {
		book_name = bookName;
	}

	public String getAuthor1() {
		return author1;
	}

	public void setAuthor1(String author1) {
		this.author1 = author1;
	}

	public String getAuthor2() {
		return author2;
	}

	public void setAuthor2(String author2) {
		this.author2 = author2;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear_of_publication() {
		return year_of_publication;
	}

	public void setYear_of_publication(String yearOfPublication) {
		year_of_publication = yearOfPublication;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// add books details into Database
	public void addBook(String book_name, String author1,
			String author2, String publisher, String year_of_publication,
			String status) throws NamingException, SQLException {

		String query = "";
		Connection connection = null;
		InitialContext icContext = null;
		DataSource ds = null;
		ResultSet resultSet = null;
		Statement statement = null;

		icContext = new InitialContext();
		ds = (DataSource) icContext.lookup("java:/OracleDS");
		connection = ds.getConnection();
		statement = connection.createStatement();
		
		//Gets the heightest bookid 
		query = "SELECT MAX(book_id) FROM books_inventry_300";
		resultSet = statement.executeQuery(query);
		resultSet.next();

		setBook_id(resultSet.getInt(1)+1);
	//	resultSet = statement.executeQuery(query);
		
		query= "SELECT book_id FROM books_inventry_300";
		resultSet = statement.executeQuery(query);
		System.out.println(resultSet);
		
		//Query to insert the books details in database
		query = "INSERT into books_inventry_300 values";
		query += "(" + book_id + ",'" + book_name + "','" + author1 + "','"
				+ author2 + "','" + publisher + "','" + year_of_publication
				+ "','" + status + "')";
		
		System.out.println(query);
		resultSet = statement.executeQuery(query);
		
		//logg the query for debugging
		log4j logger=new log4j();
		logger.newlog("Book added ("+query+") On "+(new Date()));
		resultSet = statement.executeQuery("commit");
		connection.close();								//closing connection

	}

	

	
	// modify Book Details

	public boolean modifyBookDetails(int book_id,String book_name,String author1,String author2,
			String publisher,String year_of_publication) throws NamingException,
			SQLException {
		String query = "";
		Connection connection = null;
		InitialContext icContext = null;
		DataSource ds = null;
		ResultSet resultSet = null;
		Statement statement = null;

		icContext = new InitialContext();
		ds = (DataSource) icContext.lookup("java:/OracleDS");
		connection = ds.getConnection();
		statement = connection.createStatement();
		
		try 
	    {
			
			
			PreparedStatement ps=connection.prepareStatement("select * from books_inventry_300 where book_id=?");
			ps.setInt(1,book_id);
			ResultSet rs=ps.executeQuery();
			
			/* this was given to ensure not null condition
			 * while(rs.next())
			{
				if(book_name.equals(""))
					book_name=rs.getString(2);
					
				if(author1.equals(""))
					author1=rs.getString(3);
				
				if(author2.equals(""))
					author2=rs.getString(4);

					
				if(publisher.equals(""))
					publisher=rs.getString(5);	
						
				if(year_of_publication.equals(""))
					year_of_publication=rs.getString(6);
				
			}*/
			query="update books_inventry_300 set book_id=?,book_name=?,author1=?,author2=?,publisher=?,year_of_publication=?,status=? where book_id=?";
			PreparedStatement ps2=connection.prepareStatement(query);
			ps2.setInt(1,book_id);
			ps2.setString(2,book_name);
			ps2.setString(3,author1);
			ps2.setString(4,author2);
			ps2.setString(5,publisher);
			ps2.setString(6,year_of_publication);
			ps2.setString(7,"Y");
			ps2.setInt(8,book_id);
			//logg the query for debugging
			log4j logger=new log4j();
			logger.newlog("Book modified ("+query+") On "+(new Date()));
			
			
			int i=ps2.executeUpdate();
			if(i>0)
			{
				connection.close();
				return true;
			}
			connection.close();
		}
	    catch (SQLException e) 
	    {
			
			e.printStackTrace();
		}
	    return false;
		
	}

	
	
	// delete the book details from database
	public void deleteBookDetails(int book_id) throws NamingException,
			SQLException {

		icContext = new InitialContext();
		ds = (DataSource) icContext.lookup("java:/OracleDS");
		connection = ds.getConnection();
		statement = connection.createStatement();
		System.out.println(book_id);
			
		/*	Gives name of book
		 * 
		 * 
		 query="SELECT book_name FROM books_inventry_300 WHERE book_id="+book_id;
		 resultSet = statement.executeQuery(query);
		 System.out.println(resultSet+" Book deleted");*/

		//Query for delete details of book using bookid
		query = "DELETE FROM books_inventry_300 WHERE book_id=" + book_id;
		resultSet = statement.executeQuery(query);

		//logg the query for debugging
		log4j logger=new log4j();
		logger.newlog("Book deleted ("+query+") On "+(new Date()));
		
		resultSet = statement.executeQuery("commit");	
		connection.close();								//closing connection
	}
}
