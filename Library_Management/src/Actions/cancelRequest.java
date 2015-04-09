package Actions;

import cancel.cancelRequests;

import com.opensymphony.xwork2.ActionSupport;

public class cancelRequest extends ActionSupport {
	public String execute() {
		cancelRequests requests = new cancelRequests();
		requests.cancelRequest();
		return SUCCESS;
	}
}
