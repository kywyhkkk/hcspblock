package test.huoche.wyh.solve.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import test.huoche.wyh.solve.bean.Customer;
import test.huoche.wyh.solve.bean.Page;
import test.huoche.wyh.solve.bean.User;
import test.huoche.wyh.solve.solv.UserService;

public class UserController {
	
	UserService us = new UserService();
	/**
	 * 分页显示用户的所有信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String userList(HttpServletRequest request)throws Exception{
		String pm = request.getParameter("pageNum");
		int pageNum = 1;
		if (pm != null & !pm.isEmpty()) {
			pageNum = Integer.parseInt(pm);
		}
		int pageSize =10;
		int totalRecord = us.getCount();
		Page p = new Page(pageNum,pageSize,totalRecord);
		List<User> list = us.selectAllUser(p.getIndex(), p.getPageSize());
		request.setAttribute("link", "admin/userList?pageNum");
		request.setAttribute("page", p);
		request.setAttribute("uList", list);
		return "/WEB-INF/jsp/ht/userList.jsp";
	}
	
	/**
	 * 分页显示用户的所有信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String userListByName(HttpServletRequest request)throws Exception{
		String name = request.getParameter("name");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize =10;
		int totalRecord = us.getCount(name);
		Page p = new Page(pageNum,pageSize,totalRecord);
		List<User> list = us.selectAllUser(p.getIndex(), p.getPageSize(),name);
		request.setAttribute("link", "admin/userListByName?name="+name+"&pageNum");
		request.setAttribute("page", p);
		request.setAttribute("uList", list);
		return "/WEB-INF/jsp/ht/userList.jsp";
	}
	
	/**
	 * 根据id删除用户信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteUser(HttpServletRequest request)throws Exception{
		int userid = Integer.parseInt(request.getParameter("userid"));
		us.deleteUser(userid);
		return userList(request);
	}

	/**
	 * 禁用用户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String disabledUser(HttpServletRequest request)throws Exception{
		int userid = Integer.parseInt(request.getParameter("userid"));
		us.disabledUser(userid);
		request.setAttribute("pageNum",1);
		return userList(request);
	}

	/**
	 * 解除禁用用户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String enabledUser(HttpServletRequest request)throws Exception{
		int userid = Integer.parseInt(request.getParameter("userid"));
		us.enabledUser(userid);
		request.setAttribute("pageNum",1);
		return userList(request);
	}

	/**
	 * 跳转到修改用户信息界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String goUserUpdate(HttpServletRequest request)throws Exception{
		int userid = Integer.parseInt(request.getParameter("userid"));
		User user = us.getUserById(userid);
		request.setAttribute("user", user);
		return "/WEB-INF/jsp/ht/userUpdate.jsp";
	}
	
	/**
	 * 更新用户信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String userUpdate(HttpServletRequest request)throws Exception{
		int userid = Integer.parseInt(request.getParameter("userid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		String name = request.getParameter("name");
		String cardid = request.getParameter("cardid");
		String phone = request.getParameter("phone");
		String usercode = request.getParameter("usercode");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		User user = new User();
		user.setUserid(userid);
		user.setCid(cid);
		user.setName(name);
		user.setPhone(phone);
		user.setUsercode(usercode);
		user.setPassword(password);
		user.setType(type);
		user.setCardid(cardid);
		us.userUpdate(user);
		return userList(request);
	}
	
	
	public String login() {
		return "/WEB-INF/jsp/qt/login.jsp";
	}
	public String reg() {
		return "/WEB-INF/jsp/qt/reg.jsp";
	}
	String u = "";
	/**
	 * 用户注册信息提交数据库
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String regForm(HttpServletRequest request)throws Exception{
		String usercode  = request.getParameter("usercode");
		String password  = request.getParameter("password");
		String phone  = request.getParameter("phone");
		String name  = request.getParameter("name");
		String cardid  = request.getParameter("cardid");
		String type  = request.getParameter("type");
		int cnt = us.getUserCode(usercode);
		if(cnt!=0&&cnt!=-1) {
			usercode = usercode+((int)(Math.random()*(9999-1000+1))+1000)+"";
		}
		u = usercode;
		User user = new User();
		Customer customer = new Customer();
		user.setUsercode(usercode);
		user.setPassword(password);
		customer.setName(name);
		customer.setCardid(cardid);
		customer.setPhone(phone);
		customer.setType(type);
		us.reg(customer, user);
		return success(request);
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String main(HttpServletRequest request)throws Exception{
		String usercode = request.getParameter("usercode");
		String password = request.getParameter("password");
		User user1 = us.login(usercode);

		if(null!=user1.getPassword()||"".equals(user1.getPassword())) {
			User user2 = us.getUser(user1.getUserid());
			if(password.equals(user1.getPassword())) {
				if("0".equals(user1.getStatus())) {
					request.getSession().setAttribute("user1", user1);
					request.getSession().setAttribute("user2", user2);
					return "/WEB-INF/jsp/qt/main.jsp";
				}else if("1".equals(user1.getStatus())){
					String msg = "用户名或密码错误！";
					request.setAttribute("msg", msg);
					return "/WEB-INF/jsp/qt/login.jsp";
				}else {
					String msg = "用户被禁用，请联系管理员！";
					request.setAttribute("msg", msg);
					return "/WEB-INF/jsp/qt/login.jsp";
				}
			}else {
				String msg = "用户名或密码错误！";
				request.setAttribute("msg", msg);
				return "/WEB-INF/jsp/qt/login.jsp";
			}
		}
		String msg = "用户名或密码错误！";
		request.setAttribute("msg", msg);
		return "/WEB-INF/jsp/qt/login.jsp";
	}
	
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/WEB-INF/jsp/qt/main.jsp";
	}
	
	public String success(HttpServletRequest request) {
		request.setAttribute("msg", u);
		return "/WEB-INF/jsp/qt/success.jsp";
	}

	/**
	 * 个人信息界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String userInfo(HttpServletRequest request)throws Exception{
		if(request.getSession(false).getAttribute("user1")!=null&&request.getSession(false).getAttribute("user2")!=null) {
			User user  = (User) request.getSession().getAttribute("user1");
			User u = us.getUserById(user.getUserid());
			request.setAttribute("u", u);
			return "/WEB-INF/jsp/qt/personalInfo.jsp";
		}else {
			return "/WEB-INF/jsp/qt/login.jsp";
		}
	}
	
	public String userUpdateInfo(HttpServletRequest request)throws Exception{
		int userid = Integer.parseInt(request.getParameter("userid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		String name = request.getParameter("name");
		String cardid = request.getParameter("cardid");
		String phone = request.getParameter("phone");
		String usercode = request.getParameter("usercode");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		User user = new User();
		user.setUserid(userid);
		user.setCid(cid);
		user.setName(name);
		user.setPhone(phone);
		user.setUsercode(usercode);
		user.setPassword(password);
		user.setType(type);
		user.setCardid(cardid);
		us.userUpdate(user);
		return userInfo(request);
	}
	
}
