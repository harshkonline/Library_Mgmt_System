package Actions;

import java.util.ArrayList;

import Bean.Issue_Return;

import com.opensymphony.xwork2.ActionSupport;

public class issueAction extends ActionSupport {

	String user_id, button, book_id;
	ArrayList book_id_list;

	public ArrayList getBook_id_list() {
		return book_id_list;
	}

	public void setBook_id_list(ArrayList bookIdList) {
		book_id_list = bookIdList;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String userId) {
		user_id = userId;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String execute() {

		Issue_Return bean = new Issue_Return();

		if (button.equals("Check Request")) {
			int book_id[] = null;
			if (user_id.length() > 0) {

				book_id = bean.checkRequest(user_id);

				ArrayList list = new ArrayList();

				for (int j = 0; j < book_id.length; j++) {
					Integer i = new Integer(book_id[j]);
					list.add(i.toString());
					// book_id_list.add(new Integer(book_id[j]).toString());
				}
				setBook_id_list(list);
				return "requestChecked";
			} else {
				return ERROR;
			}
		} else if (button.equals("Issue Book")) {
			boolean flag = false;
			System.out.println("-----------In issue book");
			try {
				flag = bean.issueBook(Integer.parseInt(book_id));
				System.out.println("-----------In try");
			} catch (NumberFormatException e) {
				return "error";
			}
			if (flag == false)
				return "error";
			return SUCCESS;
		}
		return SUCCESS;
	}

	public void validate() {
		if (button.equals("Check Request")) {
			if (user_id.length() == 0)
				addFieldError("user_id", getText("userid.req"));
		}
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String bookId) {
		book_id = bookId;
	}
}
