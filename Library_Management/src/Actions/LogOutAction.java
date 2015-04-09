package Actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogOutAction extends ActionSupport implements SessionAware{

	Map<String, Object> session = null;
	@Override
	public String execute() {
		session=ActionContext.getContext().getSession();
		session.remove("username");
		return SUCCESS;
	}
	public void setSession(Map arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}

}
