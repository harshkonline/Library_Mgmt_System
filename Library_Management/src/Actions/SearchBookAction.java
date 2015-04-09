package Actions;

import com.opensymphony.xwork2.ActionSupport;

public class SearchBookAction extends ActionSupport{
	String book_name;
	String book_id;
	String author;

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String bookName) {
		book_name = bookName;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String bookId) {
		book_id = bookId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String execute(){
		
		
		return SUCCESS;
	}
}
