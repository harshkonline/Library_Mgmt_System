package Actions;

import Bean.RegistrationBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class registerRequestAction extends ActionSupport{
	String book_name;
	String user_name;
	public String execute() {
		user_name=(String)ActionContext.getContext().getSession().get("username");
		RegistrationBean bean=new RegistrationBean();
		return(bean.registerBook(user_name,book_name));		
		 
	}
	
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String bookName) {
		book_name = bookName;
	}
}
