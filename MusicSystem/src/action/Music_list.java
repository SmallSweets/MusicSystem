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
//	����һ���б�          �б��е�ֵΪMusic����ļ���
	private static Database_Dao database_Dao = new Database_Dao();
	private static HttpServletResponse response = ServletActionContext.getResponse();
	private static PrintWriter out;
	
	public void show() {
//		������ҳ�����ʽ
		response.setContentType("text/html;charset=utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		�������ҳ����Ϣ�͸�ʽ  ���б����ʽ�������ҳ
		out.println("<table border='1'>");
			out.println("<tr>");
				out.println("<td>���</td>");
				out.println("<td>��������</td>");
				out.println("<td>����</td>");
				out.println("<td>���ʱ��</td>");
				out.println("<td>���ŵ�ַ</td>");
				out.println("<td>����</td>");
			out.println("</tr>");
//		�б���ΪMusic����ļ���
		List<Music> musics = database_Dao.allmusic();
//		����Music���󼯺�
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
					out.println("<a href='update_music.jsp'><button>����</button></a>");
					out.println("<a href='deletemusic?id="+music.getId()+"'><button>ɾ��</button></a>");
				out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}
}
