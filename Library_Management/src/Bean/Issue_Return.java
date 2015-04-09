package Bean;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import log4j.log4j;

//for user with librarian privilages
public class Issue_Return {

	// declaration of database connectivity classes
	Connection connection = null;
	InitialContext icContext = null;
	DataSource dataSource = null;
	ResultSet resultSet = null;
	Statement statement = null;

	// check whether student has requested for a book
	// in registration table
	public int[] checkRequest(String user_id) {

		String query = "";
		int i = 0;

		String[] st = null;
		int book_id[] = null;
		Vector vector = new Vector();
		// connect to database
		try {
			icContext = new InitialContext();
			dataSource = (DataSource) icContext.lookup("java:/OracleDS");
			connection = dataSource.getConnection();
			statement = connection.createStatement();

			// query to select books requested by user
			query = "SELECT book_id FROM books_registration_300 WHERE user_id='"
					+ user_id + "'  AND (status='y' OR status='Y')";
			resultSet = statement.executeQuery(query);
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			System.out.println("/n/n/n" + query);
			// take book ids from executed query to return
			/*if(resultSet.isLast()){
				throw java.sql.SQLException;
			}*/
			while (resultSet.next()) {
				System.out.println(i);
				vector.add(resultSet.getString(1));
				// book_id_[i]=Integer.parseInt(resultSet.getString(1));
				i++;
			}

			
			book_id = new int[vector.size()];
			for (int j = 0; j < vector.size(); j++) {
				book_id[j] = Integer.parseInt((String) vector.get(j));
			}
			connection.close();
		} catch (NamingException e) {
			// to be logged
			System.out.println(e.getMessage());

		} catch (SQLException e) {
			// to be logged
			System.out.println(e.getMessage());
		}

		return book_id;
	}

	public boolean issueBook(int book_id) {
		/*
		create table books_transaction_300 (
				transaction_id varchar2(4),
				registration_id varchar2(4),
				issue_date Date,
				return_date Date,
				fine number(4)
				);*/
		int k=0;
		String query = "";
		int i = 0;
		int[] book_id_ = null;
		String registration_id="";
		String[] st = null;
		Vector vector = new Vector();
		
		try {
			icContext = new InitialContext();
			dataSource = (DataSource) icContext.lookup("java:/OracleDS");
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet=null;
			// query to select books requested by user
			query = "SELECT registration_id FROM books_registration_300 WHERE book_id="+book_id+" AND (status='y' OR status='Y')";
			
			resultSet = statement.executeQuery(query);
			resultSet.next();
			registration_id=resultSet.getString(1);
			connection.close();
			
			// take book ids from executed query to return
			query="insert into books_transaction_300 values('"+getNextTransactionId()+"','"+registration_id+"',(SYSDATE),(SYSDATE +14),0,'Y')";
			System.out.println(query+"****************");
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();
		
			//logg the query for debugging
			log4j logger=new log4j();
			logger.newlog("Book issued ("+query+") On "+(new Date()));
			
			k=statement.executeUpdate(query);
			
			System.out.println("-------------"+k);
			query="update books_registration_300 set status='N' where book_id="+book_id;	
			
			
			statement.executeUpdate(query);
			connection.close();
			System.out.println("update reg query ----------"+query);
		} catch (NamingException e) {
			// to be logged
			System.out.println(e.getMessage());

		} catch (SQLException e) {
			// to be logged
			System.out.println(e.getMessage());
		}
		if(k==1)
		return true;
		else
			return false;
	}

	public String returnBook(int book_id) {
		int k=0;
		String query = "";
		int i = 0;
		int[] book_id_ = null;
		String registration_id="";
		Date return_date=null;
		String[] st = null;
		int fine_days;
		int fine=0;
		
		resultSet=null;
		try {
			icContext = new InitialContext();
			dataSource = (DataSource) icContext.lookup("java:/OracleDS");
			connection = dataSource.getConnection();
			statement = connection.createStatement();

			// query to select books requested by user
			query = "SELECT registration_id FROM books_registration_300 WHERE book_id="+book_id+" AND status='N'";
			System.out.println(query+"**********************");
			resultSet = statement.executeQuery(query);
			resultSet.next();
			registration_id=resultSet.getString(1);
			System.out.println(registration_id+"*****************************");
			query="SELECT floor(SYSDATE-return_date) FROM books_transaction_300 WHERE registration_id='"+registration_id+"'";
			resultSet = statement.executeQuery(query);
			resultSet.next();
			fine_days=Integer.parseInt(resultSet.getString(1));
			System.out.println(fine_days+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			
			query="UPDATE books_inventry_300 SET status='Y' WHERE book_id="+book_id;
			
			//logg the query for debugging
			log4j logger=new log4j();
			logger.newlog("Book returned ("+query+") On "+(new Date()));
			
			statement.executeUpdate(query);
			System.out.println(query+"######### set status to y");
			if(fine_days>0) {				
				fine=fine_days*5;
				query="UPDATE books_transaction_300 SET fine="+fine+" AND status='N' WHERE registration_id='"+registration_id+"'";
				System.out.println("&&&&&&&&&&&"+query);
				statement.executeUpdate(query);
				Integer fine_=new Integer(fine);
				connection.close();
				return fine_.toString();
			}
			else {
				/*fine=fine_days*5;*/
				query="UPDATE books_transaction_300 SET status='N' WHERE registration_id='"+registration_id+"'";
				System.out.println("&&&&&&&&&&&"+query);
				statement.executeUpdate(query);				
			}
			
			connection.close();
			return "0";
			
		} catch (NamingException e) {
			// to be logged
			System.out.println(e.getMessage());

		} catch (SQLException e) {
			// to be logged
			System.out.println(e.getMessage());
		}
		return "error";
	}
	public String getNextTransactionId() {
		int maxid = 1000;
		String query="";
		query = "select max(transaction_id) from books_transaction_300";
		try {
			icContext = new InitialContext();
			dataSource = (DataSource) icContext.lookup("java:/OracleDS");
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			resultSet.next();
			maxid = resultSet.getInt(1);
			maxid++;
			//resultSet=null;
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// return next transaction id
		return String.valueOf(maxid);
	}
	// for checking purpose

}
