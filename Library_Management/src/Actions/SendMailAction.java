package Actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Bean.SendMailBean;

import com.opensymphony.xwork2.ActionSupport;

public class SendMailAction extends ActionSupport{
	Context context = null;
	DataSource dataSource = null;
	Connection connection = null;
	Statement statement = null;
	String query = null;
	ResultSet resultSet = null;
	
	//parameters to be sent to SendMail bean
	String users[]=null;
	String books[]=null;
	
	@Override
	public String execute() throws Exception {		
		getUsers();
		if(users.length==0) {
			System.out.println("\n\n$$$$$$$$$ Not a single user to send mail $$$$$$$$$$$");
			return ERROR;
		}
		sendMails();
		return SUCCESS;
	}
	
	public void getUsers() {
		int i=0;
		prepareDB();
		query="select U.email_id,BI.book_name from users_300 U,books_inventry_300 BI, " +
			  "books_registration_300 BR,books_transaction_300 BT"+
		      " where  ((floor(BT.return_date-sysdate)=1) AND " +
		      "(BT.registration_id=BR.registration_id) AND " +
		      "(BR.book_id=BI.book_id) AND (BR.user_id=U.user_id) AND (BT.status='Y' OR BT.status='y'))";
		System.out.println("\n\nYour query ="+query);
		System.out.println("\n\n\n");
		try {
			resultSet=statement.executeQuery(query);
				
			//find out number of users
			while(resultSet.next()) {			
				i++;
			}
			
			//allocate memory
			users=new String[i];
			books=new String[i];
			i=0;
			
			//fetch email id & book name
			resultSet=statement.executeQuery(query);
			while(resultSet.next()) {
				users[i]=resultSet.getString(1);
				books[i]=resultSet.getString(2);
				i++;
			}
			connection.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMails() {
		
		SendMailBean bean = new SendMailBean();
		
		//send mails one by one to all users whose due date has come
		for (int i = 0; i < users.length; i++) {
			bean.sendMail(users[i], books[i]);
			System.out.println("\n\nMail send to"+users[i]+" for book "+books[i]+"successfully.");
		}
	}
	
	// initializes DB parameters
	public void prepareDB() {
		try {
			// initialize context
			context = new InitialContext();

			// look up for data source's jndi name
			dataSource = (DataSource) context.lookup("java:/OracleDS");

			// get new connection
			connection = dataSource.getConnection();

			// create statement
			statement = connection.createStatement();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
