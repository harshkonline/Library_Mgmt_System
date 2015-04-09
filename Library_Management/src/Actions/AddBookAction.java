package Actions;

import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;

import log4j.log4j;

import Bean.BookInventoryBean;

import com.opensymphony.xwork2.ActionSupport;

public class AddBookAction extends ActionSupport {
	String book_name, author1, author2, publisher, year_of_publication;
	int book_id;
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
	public String execute() {
		BookInventoryBean bean=new BookInventoryBean();
		try {
			bean.addBook(book_name, author1, author2, publisher, year_of_publication, "Y");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log4j logger=new log4j();
		String message="Book added:Book ID:"+book_id+",Book Name:"+book_name+"' Author:"+author1
		+" On "+(new Date());
		logger.newlog(message);
		return SUCCESS;
	}
	
	public void validate()
	{
		
		/*if( book_name.length()==0)
			addFieldError("book_name", getText("bookname.req"));
		

		if(author1.length()==0)
			addFieldError("author1", getText("username.req"));
		

		if( publisher.length()==0)
			addFieldError("publisher", getText("username.req"));
		

		if( year_of_publication.length()==0)
			addFieldError("year_of_publication", getText("username.req"));
		*/
		
	}
}
