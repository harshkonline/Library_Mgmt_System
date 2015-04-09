package Actions;

import java.sql.SQLException;

import javax.naming.NamingException;

import Bean.BookInventoryBean;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteBookAction extends ActionSupport {
	String book_id, book_name;

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String bookId) {
		book_id = bookId;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String bookName) {
		book_name = bookName;
	}

	public String execute() {
		BookInventoryBean bean = new BookInventoryBean();
		try {
			bean.deleteBookDetails(Integer.parseInt(getBook_id()));
			return SUCCESS;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ERROR;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if (book_id.length() == 0 && book_name.length() == 0) {
			addFieldError("book_id", getText("bookid.req"));
			addFieldError("book_name", getText("bookname.req"));
		}

	}
}
