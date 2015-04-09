package Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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

	//connect to DB
	Connection connection = null;
	InitialContext icContext = null;
	DataSource ds = null;
	ResultSet resultSet = null;
	Statement statement = null;

	
	//
	public BookInventoryBean() {

	}

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

	// add books details
	public void addBook(int book_id, String book_name, String author1,
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
		query = "SELECT MAX(book_id) FROM books_inventry_300";
		resultSet = statement.executeQuery(query);
		resultSet.next();

		setBook_id(resultSet.getInt(1));
		resultSet = statement.executeQuery(query);

		query = "INSERT into books_inventry_300 values";

		query += "(" + book_id + ",'" + book_name + "'," + author1 + ","
				+ author2 + ",'" + publisher + "','" + year_of_publication
				+ "','" + status + "')";
		System.out.println(query);
		resultSet = statement.executeQuery(query);
		System.out.println("db conn");
		resultSet = statement.executeQuery("commit");
		connection.close();

	}

	// modify Book Details
	/*
	 * public void modifyBookDetails(int book_id) throws NamingException,
	 * SQLException{ }}
	 */
	
	// delete the bookdetails from database 
	
	public void deleteBookDetails(int book_id) throws NamingException, SQLException{
		
		icContext = new InitialContext();
		ds = (DataSource) icContext.lookup("java:/OracleDS");
		connection = ds.getConnection();
		statement = connection.createStatement();
		
		query= "SELECT book_name FROM books_inventry_300 WHERE book_id="+book_id;
		resultSet = statement.executeQuery(query);
		System.out.println(resultSet+" Book deleted");
		
		
		query = "DELETE FROM books_inventry_300 WHERE book_id="+book_id;
				
		resultSet = statement.executeQuery("commit");
		connection.close();
	}
}
