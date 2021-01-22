package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import music.Music;
import util_dao.Database_Dao;

public class Music_list extends ActionSupport {
//	返回一个列表          列表中的值为Music对象的集合
	private static Database_Dao database_Dao = new Database_Dao();
	private static HttpServletResponse response = ServletActionContext.getResponse();
	private static PrintWriter out;
	
	public void show() {
//		设置网页编码格式
		response.setContentType("text/html;charset=utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		输出到网页的信息和格式  以列表的形式输出到网页
		out.println("<table border='1'>");
			out.println("<tr>");
				out.println("<td>编号</td>");
				out.println("<td>歌曲名称</td>");
				out.println("<td>歌手</td>");
				out.println("<td>添加时间</td>");
				out.println("<td>播放地址</td>");
				out.println("<td>操作</td>");
			out.println("</tr>");
//		列表中为Music对象的集合
		List<Music> musics = database_Dao.allmusic();
//		遍历Music对象集合
		for (Music music:musics) {
			out.print("<tr>");
				out.println("<td>");
					out.println(music.getId());
				out.println("</td>");
				out.println("<td>");
					out.println(music.getName());
				out.println("</td>");
				out.println("<td>");
					out.println(music.getSinger());
				out.println("</td>");
				out.println("<td>");
					out.println(music.getTime());
				out.println("</td>");
				out.println("<td>");
					out.println(music.getUrl());
				out.println("</td>");
				out.println("<td>");
					out.println("<a href='update_music.jsp'><button>更新</button></a>");
					out.println("<a href='deletemusic?id="+music.getId()+"'><button>删除</button></a>");
				out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}
}
