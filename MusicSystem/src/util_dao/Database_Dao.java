package util_dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import music.Music;
import user.User;

public class Database_Dao {
	
//	1.����configuration����
//	��ȡsrcĿ¼�µĺ��������ļ�hibernate.cfg.xml ���ļ��ŵ�������configuration������ Ҳ���Ը���Ϊ ���غ��������ļ�
//	��ȡ�ĺ��������ļ��ǹ̶��� ������������ļ�������Ҫ��Ĳ�һ�� ���޷���ȡ
	private static Configuration configuration = new Configuration().configure();
	
//	2.����sessionFactory����
//	��ȡӳ���ļ���ʵ����  ���������ݿ�
	private static SessionFactory sessionFactory = configuration.buildSessionFactory();
	
//	3.����session����
//	ִ��SQL����ɾ�Ĳ�
	private static Session session = sessionFactory.openSession();
	
//	4.��������
//	��ȡ������� �����ύ�����ع�����
	private static org.hibernate.Transaction tx = session.beginTransaction();
	
//	5.�������ݿ�
//	����û�
	public void addinfo(User inuser) {
		User user = new User();
		user.setName(inuser.getName());
		user.setPassword(inuser.getPassword());
		user.setAge(inuser.getAge());
		user.setAddress(inuser.getAddress());
		user.setSex(inuser.getSex());
		user.setPhone(inuser.getPhone());
//		����session��save����ʵ�����
		session.save(user);
		
//		6.�ύ����
		tx.commit();
		
//		��������  ʹ�û��ܹ��ύ��������
		session.beginTransaction();
		
//		7.�ر���Դ
//		session.close();
//		sessionFactory.close();
	}
	
//	��¼��
	public boolean uselogin(String name,String password) {
		boolean result = true;
//		�����ݿ��в�ѯ��Ϣ ͨ��ʵ����������ݿ�
		Criteria userCriteria = session.createCriteria(User.class);
//		��ѯ����
		userCriteria.add(Restrictions.and(Restrictions.eq("name", name),Restrictions.and(Restrictions.eq("password", password))));
//		����ѯ�����û�����ת��Ϊ�б���ʽ
		List<User> users = userCriteria.list();
		try {
//			��users��Ϊ��ʱ ˵�������ѯ���� ���ݿ��д��ڸ��û�  �����ݲ������ѯ����ʱ usersΪ�� ����ִ�д˴���ᱨ��
			users.get(0).getName();
			result = true;
//			�����ִ���ʱִ�д˲��ִ��� ˵�����ݿ���û�и��û�
		}catch (Exception e) {
			result = false;
			// TODO: handle exception
		}
		
//      ����һ���µ�����Transaction
//        session.beginTransaction();
        
//		6.�ύ����
		tx.commit();
		
//		�ύ����� �ٴο������� �û����Խ��ж���ύ  �ڴ˴�����������  �û�ֻ���ύһ���û���������
		session.beginTransaction();
		
		return result;
	}
	
//	���������Ϣ
	public void addmusic(Music inmusic) {
		Music music = new Music();
		music.setName(inmusic.getName());
		music.setSinger(inmusic.getSinger());
		music.setTime(inmusic.getTime());
		music.setUrl(inmusic.getUrl());
//		����session��save����ʵ�����
		session.save(music);
		
//		6.�ύ����
		tx.commit();
		
//		��������  ʹ�û��ܹ��ύ��������
		session.beginTransaction();
		
//		7.�ر���Դ
//		session.close();
//		sessionFactory.close();
	}
	
//	��ѯ����������Ϣ
	public List<Music> allmusic() {
		Criteria musCriteria = session.createCriteria(Music.class);
		List<Music> musics = musCriteria.list();
		return musics;
	}
	
//	ɾ��������Ϣ
	public void delete(int id) {
//		ʹ��Ψһ��ʶ����ȡҪɾ�������� Ψһ��ʶΪint����
		Music delinfoMusic = session.find(Music.class, id);
//		ɾ������
		session.delete(delinfoMusic);
//		�ύ
		tx.commit();
//		��������
		session.beginTransaction();
	}
}
