package Actions;

import java.util.ArrayList;

import org.apache.bsf.util.Bean;

import Bean.BookDetails;

import com.opensymphony.xwork2.ActionSupport;

public class showBookModifyAction extends ActionSupport {
	int book_id;
	ArrayList bookDetails;
	String book_name;
	String author1;
	String author2;
	String publisher;
	String year_of_publication;
	String status;

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

	public ArrayList getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(ArrayList bookDetails) {
		this.bookDetails = bookDetails;
	}

	public String execute() {
		ArrayList list = null;
		String details[] = null;
		BookDetails Bean = new BookDetails();
		details = Bean.getBookDetails(book_id);
		try {
			details[0].equals("$NO_BOOKS$");

		} catch (NullPointerException ex) {
			return ERROR;
		}

		setBook_id(Integer.parseInt(details[0]));
		setBook_name(details[1]);
		setAuthor1(details[2]);
		setAuthor2(details[3]);
		setPublisher(details[4]);
		setYear_of_publication(details[5]);
		setStatus(details[6]);

		setBookDetails(list);
		return SUCCESS;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
	
	/*	if( book_name.length()==0)
			addFieldError("book_name", getText("bookname.req"));
		

		if(author1.length()==0)
			addFieldError("author1", getText("author1.req"));
		

		if( publisher.length()==0)
			addFieldError("publisher", getText("publisher.req"));
		

		if( year_of_publication.length()==0)
			addFieldError("year_of_publication", getText("year_of_publication.req"));
		*/
		
		if( book_id<=0)
			addFieldError("book_id", getText("book_id.req"));
		
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int bookId) {
		book_id = bookId;
	}

}
