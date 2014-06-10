package dao.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import util.PaymentAdvisor;
import util.RegisterType;
import util.UserStateType;
import util.UserType;
import model.Event;
import model.ModifiedUser;
import model.Payment;
import model.RegisterUser;
import model.Staff;
import model.User;
import model.UserDetail;
import dao.daoInterface.UserDao;

public class UserDaoImpl implements UserDao{
	private EntityManagerFactory emf;
	private int overdueMax;
	
	@Override
	public User getUser(String account, String password) {
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("select user from User as user where user.account=?1 and user.password=?2");
		query.setParameter(1, account);
		query.setParameter(2, password);
		
		List<User> users = query.getResultList();
		
		for(User user: users){
			return user;
		}
		
		return null;
	}
	
	@Override
	public User getUser(String requestUserCode) {
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("select user from User as user where user.code=?1");
		query.setParameter(1, requestUserCode);
		
		List<User> users = query.getResultList();
		
		for(User user: users){
			return user;
		}
		
		return null;
	}

	
	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public int getOverdueMax() {
		return overdueMax;
	}

	public void setOverdueMax(int overdueMax) {
		this.overdueMax = overdueMax;
	}


	public RegisterType register(String account, String password, String name, List<UserDetail> details) {
		//检查用户是否存在
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select user from User as user where user.account = ?1 or user.name = ?2");
		query.setParameter(1, account);
		query.setParameter(2, name);
		
		List result = query.getResultList();
		
		//如果已经存在该用户
		if( result.size() > 0 ){
			return RegisterType.EXISTED;
		}
		
		//如果不存在该用户则创建
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		user.setName(name);
		user.setState(UserStateType.INACTIVE);
		user.setCode(generateRandomCode());
		user.setDetails(new HashSet<UserDetail>(details));
		
		//判断用户类型
		if( details.size() > 1 ){
			user.setUserType(UserType.FAMILY);
		}else{
			user.setUserType(UserType.SINGLE);
		}
		
		
		em.persist(user);
		em.flush();
		
		em.getTransaction().commit();
		
		em.close();
		
		return RegisterType.SUCCESS;
	}
	
	@Override
	public RegisterType register(RegisterUser registerUser) {
		return register(registerUser.getEmail(), registerUser.getPassword(),
				registerUser.getName(), registerUser.getAssembledDetails());
	}



	public String generateRandomCode() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<7; i++){
			char c = generateRandomChar();
			sb.append(c);
		}
		
		return sb.toString();
	}


	private char generateRandomChar() {
		Random rand = new Random();
		int charInt = rand.nextInt(10+26);
		
		if( charInt < 10 ){
			charInt += 48;
		}else{
			charInt += 55;
		}
		
		return (char)charInt;
	}


	
	@Override
	public List<User> getAllVaildUsers() {
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("select user from User as user where user.state = 0");
		
		List<User> users = query.getResultList();
		
		
		return users;
	}


	/**
	 * 减少所有活跃用户的剩余时间
	 */
	@Override
	public void decreaseValidTime() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("update User user set user.dayLeft = (user.dayLeft-1) "
				+ "where user.state = 0 or user.state = 2");
		query.executeUpdate();

		em.getTransaction().commit();
	}

	/**
	 * 检查并更新所有应当时间到期的会员
	 */
	@Override
	public void settleUserState() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//处理所有到期应该转换为欠费状态的顾客
		Query query = em.createQuery("update User user set user.state = 2"
				+ " where user.dayLeft=0 and user.state= 0");
		query.executeUpdate();
		
		
		query = em.createQuery("update User user set user.state = 3"
				+ " where user.state= 2 and user.dayLeft <= -"+overdueMax);
		//query.setParameter(1, overdueMax);
		
		query.executeUpdate();

		em.getTransaction().commit();
	}

	
	@Override
	public void recoverMembership(String account) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select user from User as user where user.account=?1");
		query.setParameter(1, account);
		
		List<User> users = query.getResultList();
		
		//如果没找到这个人就返回
		if( users.size() < 1 ){
			System.out.println("查无此人！");
			System.out.println(this);
			return;
		}
		
		User user = users.get(0);
		
		double amount = PaymentAdvisor.advice(user);
		
		//如果未激活则激活，如果欠费则恢复
		if( user.getState() == UserStateType.INACTIVE || user.getState() == UserStateType.OVERDUE){
			user.setState(UserStateType.NORMAL);
		}
	
		
		//记录此次支付信息
		recordPayment(user, amount);
		
		em.flush();

		em.getTransaction().commit();
	}
	
	
	@Override
	public void modifyUser(String account, ModifiedUser modifiedUser) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		User user = getUser(account, modifiedUser.getOldPassword());
		
		if( user == null || !modifiedUser.isMatch()){
			return;
		}
		
		user.setPassword(modifiedUser.getNewPassword());
		
		em.merge(user);
		
		em.getTransaction().commit();
	}
	

	/**
	 * 模拟与银行的接口
	 */
	@Override
	public boolean payForMembership(User user, String creditCardAccount) {
		double amount = PaymentAdvisor.advice(user);
		
		System.out.println( "creditCardAccount: "+creditCardAccount+"-"+amount);
		
		return true;
	}
	
	
	/**
	 * 记录用户此次的支付信息
	 * @param user
	 * @param amount
	 * @return
	 */
	private User recordPayment(User user, double amount) {
		Payment payment = new Payment();
		
		payment.setTime(Calendar.getInstance());
		payment.setAmount(amount);
		
		user.getPayments().add(payment);
		
		return user;
	}

	@Override
	public void cancelUser(String account) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select user from User as user where user.account=?");
		query.setParameter(1, account);
		
		List<User> result = query.getResultList();
		
		if( result.size() > 0 ){
			User user = result.get(0);
			user.setState(UserStateType.INACTIVE);
		}
		
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Staff getStaff(String account, String password) {
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("select staff from Staff as staff where staff.account=? and staff.password=?");
		query.setParameter(1, account);
		query.setParameter(2, password);
		
		List<Staff> result = query.getResultList();
		
		if( result.size() > 0 ){
			Staff staff = result.get(0);
			return staff;
		}
		
		return null;
	}
}
