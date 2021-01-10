package action;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import util_dao.Database_Dao;


public class Login extends ActionSupport {
	private static Database_Dao database_Dao = new Database_Dao();
	private static HttpServletRequest request = ServletActionContext.getRequest();
	
//	action·½·¨
	public String login() {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		boolean result = database_Dao.uselogin(name, password);
		if (result == true) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}

}
