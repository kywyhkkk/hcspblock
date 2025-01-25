package test.huoche.wyh.solve.controller;

import javax.servlet.http.HttpServletRequest;

import test.huoche.wyh.solve.bean.User;
import test.huoche.wyh.solve.solv.UserService;

public class BaseController {
	UserService us = new UserService();
	
	public String login(HttpServletRequest request)throws Exception{
		return "/WEB-INF/jsp/ht/login.jsp";
	}

	public String main(HttpServletRequest request)throws Exception{
		String usercode = request.getParameter("usercode");
		String password = request.getParameter("password");
		User user1 = us.login(usercode);
		if(null!=user1.getPassword()||"".equals(user1.getPassword())) {
			User user2 = us.getUser(user1.getUserid());
			if(password.equals(user1.getPassword())) {
				if("0".equals(user1.getStatus())) {
					String msg = "用户名或密码错误！";
					request.setAttribute("msg", msg);
					return "/WEB-INF/jsp/ht/login.jsp";
				}else if("1".equals(user1.getStatus())){
					request.getSession().setAttribute("user1", user1);
					return "/WEB-INF/jsp/ht/main.jsp";
				}
			}else {
				String msg = "用户名或密码错误！";
				request.setAttribute("msg", msg);
				return "/WEB-INF/jsp/ht/login.jsp";
			}
		}
		String msg = "用户名或密码错误！";
		request.setAttribute("msg", msg);
		return "/WEB-INF/jsp/ht/login.jsp";
	}
	
	public String logout(HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return "/WEB-INF/jsp/qt/main.jsp";
	}
	
	public String about() {
		return "/WEB-INF/jsp/qt/about.jsp";
	}
	public String help() {
		return "/WEB-INF/jsp/qt/help.jsp";
	}
	public String help1() {
		return "/WEB-INF/jsp/qt/help1.jsp";
	}
	public String help2() {
		return "/WEB-INF/jsp/qt/help2.jsp";
	}
	public String help3() {
		return "/WEB-INF/jsp/qt/help3.jsp";
	}
	public String help4() {
		return "/WEB-INF/jsp/qt/help4.jsp";
	}
	public String help5() {
		return "/WEB-INF/jsp/qt/help5.jsp";
	}
}
