package action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import util_dao.Database_Dao;

public class Delete_Music extends ActionSupport {
	
	private static HttpServletRequest request = ServletActionContext.getRequest();
	private static Database_Dao database_Dao = new Database_Dao();
	
	public String delete() {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			database_Dao.delete(id);
			System.out.println("É¾³ý³É¹¦");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return ERROR;
		}
	}
}
