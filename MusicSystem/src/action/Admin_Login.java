package action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import util_dao.Database_Dao;

public class Admin_Login extends ActionSupport {
	
	private static HttpServletRequest request = ServletActionContext.getRequest();
	private static Database_Dao database_Dao = new Database_Dao();
	
	public String admin_login() {
		String Name = request.getParameter("Name");
		String Password = request.getParameter("Password");
		boolean result = database_Dao.adminlogin(Name, Password);
		if (result == true) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
}
