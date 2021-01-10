package action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import music.Music;
import util_dao.Database_Dao;

public class Add_Music extends ActionSupport implements ModelDriven<Music> {
	private Database_Dao database_Dao = new Database_Dao();
	private Music music = new Music();
	private HttpServletResponse response = ServletActionContext.getResponse();
	
	@Override
	public Music getModel() {
		// TODO Auto-generated method stub
		return music;
	}
	
	public void add() {
		try {
			database_Dao.addmusic(music);
//			response.sendRedirect("");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
