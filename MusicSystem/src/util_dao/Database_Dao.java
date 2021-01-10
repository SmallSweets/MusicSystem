package util_dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import user.User;

public class Database_Dao {
	private static User user = new User();
	
//	1.创建configuration对象
//	获取src目录下的核心配置文件hibernate.cfg.xml 将文件放到创建的configuration对象中 也可以概括为 加载核心配置文件
//	获取的核心配置文件是固定的 如果核心配置文件名称与要求的不一致 则无法获取
	private static Configuration configuration = new Configuration().configure();
	
//	2.创建sessionFactory对象
//	读取映射文件和实体类  并创建数据库
	private static SessionFactory sessionFactory = configuration.buildSessionFactory();
	
//	3.创建session对象
//	执行SQL的增删改查
	private static Session session = sessionFactory.openSession();
	
//	4.开启事务
//	获取事务对象 可以提交事务或回滚事务
	private static org.hibernate.Transaction tx = session.beginTransaction();
	
//	5.操作数据库
//	添加用户
	public void addinfo(User user) {
		this.user = new User();
		this.user.setName(user.getName());
		this.user.setPassword(user.getPassword());
		this.user.setAge(user.getAge());
		this.user.setAddress(user.getAddress());
		this.user.setSex(user.getSex());
		this.user.setPhone(user.getPhone());
//		调用session的save方法实现添加
		session.save(this.user);
		
//		6.提交事务
		tx.commit();
		
//		开启事务  使用户能够提交其他事务
		session.beginTransaction();
		
//		7.关闭资源
//		session.close();
//		sessionFactory.close();
	}
	
//	登录用
	public boolean uselogin(String name,String password) {
		boolean result = true;
//		从数据库中查询信息 通过实体类操作数据库
		Criteria userCriteria = session.createCriteria(User.class);
//		查询条件
		userCriteria.add(Restrictions.and(Restrictions.eq("name", name),Restrictions.and(Restrictions.eq("password", password))));
//		将查询到的用户对象转换为列表形式
		List<User> users = userCriteria.list();
		try {
//			当users不为空时 说明满足查询条件 数据库中存在该用户  当数据不满足查询条件时 users为空 所以执行此代码会报错
			users.get(0).getName();
			result = true;
//			但出现错误时执行此部分代码 说明数据库中没有该用户
		}catch (Exception e) {
			result = false;
			// TODO: handle exception
		}
		
//      开启一个新的事务Transaction
//        session.beginTransaction();
        
//		6.提交事务
		tx.commit();
		
//		提交事务后 再次开启事务 用户可以进行多次提交  在此处不开启事务  用户只能提交一次用户名和密码
		session.beginTransaction();
		
		return result;
	}
}
