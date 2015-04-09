package Actions;

import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;

import log4j.log4j;

import Bean.BookInventoryBean;

import com.opensymphony.xwork2.ActionSupport;

public class BookModifyAction extends ActionSupport {
	int book_id;
	String book_name;
	String author1;
	String author2;
	String publisher;
	String year_of_publication;
	
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

		public String execute(){
			BookInventoryBean Bean = new BookInventoryBean();
			try {
				Bean.modifyBookDetails(book_id,book_name,author1,author2,publisher,year_of_publication);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log4j logger=new log4j();
			String message="Book modified:Book ID:"+book_id+",Book Name:"+book_name+"' Author:"+author1
			+" On "+(new Date());
			logger.newlog(message);
			return SUCCESS;
		}
		
		@Override
		public void validate() {
			// TODO Auto-generated method stub
			if( book_id<=0){
				addFieldError("book_id", getText("bookid.req"));
			
		}
		}
}
