package dao.daoInterface;

import java.util.List;

import util.RegisterType;
import model.ModifiedUser;
import model.RegisterUser;
import model.Staff;
import model.User;
import model.UserDetail;

public interface UserDao {
	public User getUser(String account, String password);
	
	public User getUser(String requestUserCode);

	public RegisterType register(String account, String password, String name, List<UserDetail> details);
	
	public RegisterType register(RegisterUser registerUser);

	public List<User> getAllVaildUsers();

	public void decreaseValidTime();

	public void settleUserState();

	public void recoverMembership(String account);

	public boolean payForMembership(User user, String creditCardAccount);

	public void modifyUser(String account, ModifiedUser modifiedUser);

	public void cancelUser(String account);

	public Staff getStaff(String email, String password);
}
