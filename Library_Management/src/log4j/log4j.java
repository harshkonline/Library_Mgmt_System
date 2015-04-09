package log4j;
import java.io.IOException;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class log4j{
 static Logger log = Logger.getLogger("log4j");
 	public void newlog(String msg)
	 {
	  Logger log= Logger.getLogger("log4j");
	 SimpleLayout layout=new SimpleLayout ();
	 FileAppender appender=null;
	try {
			appender=new FileAppender(layout,"E:\\project_workspace\\Library_Management\\Library_Log.txt",true); 
			log.addAppender(appender);
			log.setLevel(Level.DEBUG);
			log.info(msg);
			
			} 
	catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				log.shutdown();
			}
		 
	
		
		 
		 
	 }



		
}
