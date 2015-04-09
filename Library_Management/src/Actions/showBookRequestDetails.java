package Actions;

import java.util.ArrayList;

import Bean.RegistrationBean;

import com.opensymphony.xwork2.ActionSupport;

public class showBookRequestDetails extends ActionSupport{
	String search_by;
	String search_value;
	
	ArrayList search_by_list =new ArrayList();
	
	public String execute() {
		RegistrationBean bean=new RegistrationBean();
		String books[]=null;
		String book;
		
		
		ArrayList list=new ArrayList();
		
		//if radio button not selected then search by book name
		try {
		    if(search_by.equals(null)) {
			   search_by="1";
		    }		    
		} catch(NullPointerException ex) {
			search_by="1";
		}
		if(search_by.equals("1")) {
			books=bean.SearchByBookName(search_value);
			for (int i = 0; i < books.length; i++) {
				list.add(books[i]);
			}
			System.out.println("----- Search By book Name -----");
		}
		else if(search_by.equals("2")) {
			book=bean.searchById(search_value);
				list.add(book);		
			System.out.println("----- Search By book ID -----");
		}
		else if(search_by.equals("3")) {
			books=bean.SearchByAuthorName(search_value);
			for (int i = 0; i < books.length; i++) {
				list.add(books[i]);
			}
			System.out.println("----- Search By author Name -----");
		}
		setSearch_by_list(list);
		return SUCCESS;
	}
	public ArrayList getSearch_by_list() {
		return search_by_list;
	}

	public void setSearch_by_list(ArrayList searchByList) {
		search_by_list = searchByList;
	}
	
	public String getSearch_by() {
		return search_by;
	}
	public void setSearch_by(String searchBy) {
		search_by = searchBy;
	}
	public String getSearch_value() {
		return search_value;
	}
	public void setSearch_value(String searchValue) {
		search_value = searchValue;
	}
}
