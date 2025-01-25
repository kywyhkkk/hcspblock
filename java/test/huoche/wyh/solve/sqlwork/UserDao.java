package test.huoche.wyh.solve.sqlwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.huoche.wyh.solve.bean.Customer;
import test.huoche.wyh.solve.bean.User;
import test.huoche.wyh.solve.utils.DButil;

public class UserDao {
	
	Connection conn = DButil.getInstance().getConnection();

	public User login(String usercode)throws Exception{
		String sql = "select * from user where usercode=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, usercode);
		rs = pst.executeQuery();
		User user = new User();
		if(rs.next()) {
			user.setUserid(rs.getInt("userid"));
			user.setUsercode(rs.getString("usercode"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getString("status"));
		}
		return user;
	}

	public User getUser(int userid)throws Exception{
		String sql = "select * from customer where userid=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, userid);
		rs = pst.executeQuery();
		User user = new User();
		if(rs.next()) {
			user.setUserid(rs.getInt("userid"));
			user.setCid(rs.getInt("cid"));
			user.setName(rs.getString("name"));
			user.setCardid(rs.getString("cardid"));
			user.setPhone(rs.getString("phone"));
			user.setType(rs.getString("type"));
		}
		return user;
	}

	public List<User> selectAllUser(int cnt1,int cnt2)throws Exception{
		String sql = "SELECT user.userid,cid,usercode,PASSWORD,STATUS,NAME,cardid,phone,TYPE\r\n" + 
				"FROM USER,customer\r\n" + 
				"WHERE user.userid=customer.userid limit ?,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, cnt1);
		pst.setInt(2, cnt2);
		rs = pst.executeQuery();
		List<User> list = new ArrayList<User>();
		User user = null;
		while(rs.next()) {
			user = new User();
			user.setUserid(rs.getInt("userid"));
			user.setCid(rs.getInt("cid"));
			user.setName(rs.getString("name"));
			user.setCardid(rs.getString("cardid"));
			user.setPhone(rs.getString("phone"));
			user.setType(rs.getString("type"));
			user.setStatus(rs.getString("status"));
			user.setUsercode(rs.getString("usercode"));
			user.setPassword(rs.getString("password"));
			list.add(user);
		}
		return list;
	}

	public int getCount()throws Exception{
		String sql = "SELECT COUNT(*) FROM USER,customer WHERE user.userid=customer.userid";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	
	

	public List<User> selectAllUser(int cnt1,int cnt2,String name)throws Exception{
		String sql = "SELECT user.userid,cid,usercode,PASSWORD,STATUS,NAME,cardid,phone,TYPE\r\n" + 
				"FROM USER,customer\r\n" + 
				"WHERE user.userid=customer.userid and customer.name like '%"+name+"%' limit ?,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, cnt1);
		pst.setInt(2, cnt2);
		rs = pst.executeQuery();
		List<User> list = new ArrayList<User>();
		User user = null;
		while(rs.next()) {
			user = new User();
			user.setUserid(rs.getInt("userid"));
			user.setCid(rs.getInt("cid"));
			user.setName(rs.getString("name"));
			user.setCardid(rs.getString("cardid"));
			user.setPhone(rs.getString("phone"));
			user.setType(rs.getString("type"));
			user.setStatus(rs.getString("status"));
			user.setUsercode(rs.getString("usercode"));
			user.setPassword(rs.getString("password"));
			list.add(user);
		}
		return list;
	}

	public int getCount(String name)throws Exception{
		String sql = "SELECT COUNT(*) FROM USER,customer WHERE user.userid=customer.userid and customer.name like '%"+name+"%'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}

	public void deleteUser(int userid)throws Exception{
		String sql = "delete from user where userid="+userid;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.executeUpdate();
	}

	public User getUserById(int userid)throws Exception{
		String sql = "SELECT user.userid,cid,usercode,PASSWORD,STATUS,NAME,cardid,phone,TYPE\r\n" + 
				"FROM USER,customer\r\n" + 
				"WHERE user.userid=customer.userid and user.userid="+userid;
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		User user = new User();
		if(rs.next()) {
			user.setUserid(rs.getInt("userid"));
			user.setCid(rs.getInt("cid"));
			user.setName(rs.getString("name"));
			user.setCardid(rs.getString("cardid"));
			user.setPhone(rs.getString("phone"));
			user.setType(rs.getString("type"));
			user.setStatus(rs.getString("status"));
			user.setUsercode(rs.getString("usercode"));
			user.setPassword(rs.getString("password"));
		}
		return user;
	}

	public void userUpdate(User user)throws Exception{
		String sql = "update customer set phone=?,type=? where cid=?";
		String sql1 = "update user set password=? where userid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, user.getPhone());
		pst.setString(2, user.getType());
		pst.setInt(3, user.getCid());
		pst.executeUpdate();
		PreparedStatement pst1 = null;
		pst1 = conn.prepareStatement(sql1);
		pst1.setString(1, user.getPassword());
		pst1.setInt(2, user.getUserid());
		pst1.executeUpdate();
	}

	public void disabledUser(int userId)throws Exception{
		String sql = "update user set status='000' where userid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, userId);
		pst.executeUpdate();
	}

	public void enabledUser(int userId)throws Exception{
		String sql = "update user set status='0' where userid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, userId);
		pst.executeUpdate();
	}

	public int getUserCode(String usercode)throws Exception{
		String sql = "select count(*) from user where usercode='"+usercode+"'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = -1;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}

	public void reg(Customer customer,User user)throws Exception{
		this.insertUser(user);
		this.insertCustomer(customer, user.getUsercode());
	}
	
	public void insertUser(User user)throws Exception{
		String sql = "insert into user(usercode,password) values(?,?)";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, user.getUsercode());
		pst.setString(2, user.getPassword());
		pst.executeUpdate();
	}
	
	public int getUserId(String usercode)throws Exception{
		String sql = "select userid from user where usercode='"+usercode+"'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = -1;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	
	public void insertCustomer(Customer customer,String usercode)throws Exception{
		System.out.println("rfty");
		String sql = "insert into customer(userid,name,cardid,phone,type) values(?,?,?,?,?)";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, getUserId(usercode));
		pst.setString(2, customer.getName());
		pst.setString(3, customer.getCardid());
		pst.setString(4, customer.getPhone());
		pst.setString(5, customer.getType());
		pst.executeUpdate();
	}
	
}
