package Actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.opensymphony.xwork2.ActionSupport;

public class GenerateIdAction extends ActionSupport {
	int book_id=0;
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int bookId) {
		book_id = bookId;
	}
	public String execute() {
		
		
		InitialContext ic=null;
		
		  DataSource ds=null;
		  ResultSet rs=null;
		  Statement s=null;
		  Connection conn=null;
		  
		  String query="select max(book_id) from books_inventry_300";
		  try
			{
				ic=new InitialContext(); 			
				ds=(DataSource)ic.lookup("java:/OracleDS");
				conn=ds.getConnection(); 
				s = conn.createStatement();
		       rs = s.executeQuery(query);
		       
		       
		       while(rs.next())
		       {
		    	    book_id=rs.getInt(1);
		    	   
		       }
		      setBook_id(++book_id);
		       }
			catch(Exception e)
			{
				System.out.println("exception"+e); 
			}
				
		  
		  	return SUCCESS;
	}
	
	

}
