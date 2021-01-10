package action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import user.User;
import util_dao.Database_Dao;

public class Add_User extends ActionSupport implements ModelDriven<User> {

	private static User user = new User();
	private static Database_Dao Database_Dao = new Database_Dao();
	private static HttpServletResponse response = ServletActionContext.getResponse();
	private static PrintWriter out;
	
//	�����ύ�����ݱ��浽ʵ������
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
//	�����ݿ��������Ϣ
	public void add() {
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Database_Dao.addinfo(user);
			response.sendRedirect("registersuccess.html");
		} catch (Exception e) {
			// TODO: handle exception
			out.print("ע��ʧ��");
		}
	}
	
}
