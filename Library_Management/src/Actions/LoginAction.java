package Actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import Bean.LoginBean;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	String user_name,password;
	Map<String,Object> sessionMap=null;
	boolean flag=false;
	int cnt=0;;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String execute() {		
		String result="";
		LoginBean bean=new LoginBean();
		//result=bean.chkUser(user_name, password);
		result=bean.returnStudent();
		if(result.equals("input"))
		cnt++;
		if(cnt==1)
		addFieldError("password", getText("password.invalid"));
		sessionMap.put("username", user_name);		
		return result;
	}
	public void validate(){
		
		if(user_name.length()==0)
		addFieldError("user_name", getText("username.req"));
		if(password.length()==0)
		addFieldError("password", getText("password.req"));
	

	}
	@Override
	public void setSession(Map arg0) {
		// TODO Auto-generated method stub
		sessionMap=arg0;
	}	

}
