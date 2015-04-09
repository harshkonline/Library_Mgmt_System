package Actions;

import Bean.Issue_Return;

import com.opensymphony.xwork2.ActionSupport;

public class returnAction extends ActionSupport{
	String fine,book_id;
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String bookId) {
		book_id = bookId;
	}
	public String getFine() {
		return fine;
	}
	public void setFine(String fine) {
		this.fine = fine;
	}
	public String execute() {
		String temp="";
		if(book_id.length()==0){
		//addFieldError("book_id", getText("book_id.req"));
		return INPUT;
		}
		Issue_Return bean = new Issue_Return();
		temp=bean.returnBook(Integer.parseInt(book_id));
		if(temp.equals("error"))
			return ERROR;
		
		else {
			setFine(temp);
			return SUCCESS;
		}
		
	}
	public void validate(){
		
		if(book_id.length()==0)
		addFieldError("book_id", getText("book_id.req"));
		
	}
	
	

}
