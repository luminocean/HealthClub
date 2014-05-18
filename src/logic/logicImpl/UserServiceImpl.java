package logic.logicImpl;

import java.util.List;

import util.RegisterType;
import dao.daoInterface.IUserDao;
import logic.logicInterface.IUserService;
import model.ModifiedUser;
import model.RegisterUser;
import model.Staff;
import model.User;
import model.UserDetail;

public class UserServiceImpl implements IUserService {
	private IUserDao userDao;
	
	@Override
	public User getUser(String account, String password) {
		User user = userDao.getUser(account, password);
		
		return user;
	}
	
	@Override
	public User getUser(String requestUserCode) {
		User user = userDao.getUser(requestUserCode);
		
		return user;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public RegisterType register(String account, String password, String name, List<UserDetail> details) {
		return userDao.register(account, password, name, details);
	}
	
	@Override
	public RegisterType register(RegisterUser registerUser) {
		return userDao.register(registerUser);
	}

	@Override
	public void settleUserState() {
		userDao.decreaseValidTime();
		userDao.settleUserState();
	}

	
	@Override
	public void recoverMembership(String account) {
		userDao.recoverMembership(account);
	}

	@Override
	public boolean payForMembership(User user, String creditCardAccount) {
		return userDao.payForMembership(user, creditCardAccount);
	}

	@Override
	public void modifyUser(String account, ModifiedUser modifiedUser) {
		userDao.modifyUser(account, modifiedUser);
	}

	@Override
	public void cancelUser(String account) {
		userDao.cancelUser(account);
	}

	@Override
	public Staff getStaff(String account, String password) {
		return userDao.getStaff(account, password);
	}
}
