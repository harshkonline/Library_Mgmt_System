package ReadMail;

//Read Email

import java.util.*;

import javax.mail.Folder;
import javax.mail.*;

	public class ReadEmail
	{
		//	 Read all emails from inbox for a user
	    public static void main(String[] args)
	    {

			//String host = "arltrgsrv.patni.com";
	    	String host = "192.168.224.26";
			String username = "user3@pace.com";
			//String username = "user1@pace.com";
			//String username = "user3@pace.com";
			String password = "passwd@12";

		//	 Create empty properties
			Properties props = new Properties();

		//	 Get session
			Session session = Session.getDefaultInstance(props, null);
			 try
			 {
				//	 Get the store
					Store store = session.getStore("pop3");
					store.connect(host, username, password);

				//	 Get folder
					Folder folder = store.getFolder("INBOX");
					folder.open(Folder.READ_ONLY);

				//	 Get directory
					Message message[] = folder.getMessages();
					System.out.println("You have "+message.length+" message/s in your MailBox\n\n");

					for (int i=0, n=message.length; i<n; i++)
					{
					   System.out.println(i+1 + "\tFrom : " + message[i].getFrom());
					   System.out.println( "\t Subject :" + message[i].getSubject() + "\n\n Message :" + message[i].getContent()+"\n\n");
					   message[i].writeTo(System.out);
					   
					}

			//	 Close connection
				folder.close(false);
				store.close();
			 }
			 catch (Exception mex)
			 {
			            // Prints all nested (chained) exceptions as well
			            mex.printStackTrace();
			 }
		}
	}



