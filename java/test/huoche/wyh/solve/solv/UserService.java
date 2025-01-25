package test.huoche.wyh.solve.solv;

import java.util.List;

import test.huoche.wyh.solve.bean.Customer;
import test.huoche.wyh.solve.bean.User;
import test.huoche.wyh.solve.sqlwork.UserDao;

public class UserService {
	
	UserDao userDao = new UserDao();
	public User getUser(int userid)throws Exception{
		return userDao.getUser(userid);
	}
	public User login(String usercode)throws Exception{
		return userDao.login(usercode);
	}
	public List<User> selectAllUser(int cnt1,int cnt2)throws Exception{
		return userDao.selectAllUser(cnt1, cnt2);
	}
	public int getCount()throws Exception{
		return userDao.getCount();
	}
	public void deleteUser(int userid)throws Exception{
		userDao.deleteUser(userid);
	}
	public void disabledUser(int userid)throws Exception{
		userDao.disabledUser(userid);
	}

	public void enabledUser(int userid)throws Exception{
		userDao.enabledUser(userid);
	}

	public User getUserById(int userid)throws Exception{
		return userDao.getUserById(userid);
	}
	
	public void userUpdate(User user)throws Exception{
		userDao.userUpdate(user);
	}
	public void reg(Customer customer, User user)throws Exception{
		userDao.reg(customer, user);
	}
	public int getUserCode(String usercode)throws Exception{
		return userDao.getUserCode(usercode);
	}
	public List<User> selectAllUser(int cnt1,int cnt2,String name)throws Exception{
		return userDao.selectAllUser(cnt1, cnt2, name);
	}
	public int getCount(String name)throws Exception{
		return userDao.getCount(name);
	}
}
