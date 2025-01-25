package test.huoche.wyh.solve.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import test.huoche.wyh.solve.bean.Customer;
import test.huoche.wyh.solve.bean.Order;
import test.huoche.wyh.solve.bean.OrderVo;
import test.huoche.wyh.solve.bean.Page;
import test.huoche.wyh.solve.bean.User;
import test.huoche.wyh.solve.bean.VehicleInfo;
import test.huoche.wyh.solve.solv.NumberService;
import test.huoche.wyh.solve.solv.OrderService;

/**
 * 订单controller
 *
 */
public class OrderController {
	
	OrderService os = new OrderService();
	
	public String order(HttpServletRequest request)throws Exception{
		return "/WEB-INF/jsp/qt/order.jsp";
	}

	public String random() {
		Random ran=new Random();
		int a=ran.nextInt(99999999);
		int b=ran.nextInt(99999999);
		long l=a*10000000L+b;
		String num=String.valueOf(l);
		return num;
	}

	public String userOrder(HttpServletRequest request)throws Exception{
		
		if(request.getSession(false).getAttribute("user1")!=null&&request.getSession(false).getAttribute("user2")!=null) {

			int tid = Integer.parseInt(request.getParameter("tid"));
			int rid = Integer.parseInt(request.getParameter("rid"));
			String type = request.getParameter("type");
			Customer customer = (Customer) request.getSession().getAttribute("user2");
			String oid = random();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String datetime = df.format(new Date());// new Date()为获取当前系统时间
			String startTime = request.getParameter("startTime");
			Order order = new Order();
			order.setOid(oid);
			order.setRid(rid);
			order.setTid(tid);
			order.setType(type);
			if(os.checkdp(type,rid)==0){
				return this.orderInfo(request);
			}
			order.setUserid(customer.getUserid());
			order.setTime(datetime);
			order.setStartTime(startTime);
			order.setNum(os.getSeatNum(type, rid));
			order.setPrice(os.getPrice(type, rid));

			if(os.pdOrder(customer.getUserid(), tid)!=0) {
				return this.orderInfo(request);
			}
			os.order(order);
		}else {
			return "/WEB-INF/jsp/qt/login.jsp";
		}
		return this.orderInfo(request);
	}

	public String orderInfo(HttpServletRequest request)throws Exception{
		if(request.getSession(false).getAttribute("user1")!=null&&request.getSession(false).getAttribute("user2")!=null) {
			Customer customer = (Customer) request.getSession().getAttribute("user2");
			List<OrderVo> list = os.getOrderList(customer.getUserid());
			request.setAttribute("oList", list);
			return "/WEB-INF/jsp/qt/order.jsp";
		}else {
			return "/WEB-INF/jsp/qt/login.jsp";
		}
		
	}

	public String refund(HttpServletRequest request)throws Exception{
		int rid = Integer.parseInt(request.getParameter("rid"));
		String type = request.getParameter("type");
		String oid = request.getParameter("oid");
		os.refund(oid, type, rid);
		return orderInfo(request);
	}
	
	String abc = "";
	int rid1 = 0;
	String type1="";
	String oid1 = "";
	NumberService numberService = new NumberService();

	public String change(HttpServletRequest request)throws Exception{
		int rid = Integer.parseInt(request.getParameter("rid"));
		String type = request.getParameter("type");
		String f = request.getParameter("f");
		String oid = request.getParameter("oid");
		String ssite = request.getParameter("ssite");
		String esite = request.getParameter("esite");
		String startTime = request.getParameter("startTime");
		List<VehicleInfo> list1 = null;
		String startTime_1 = "";
		if("0".equals(f)) {
			abc = startTime;
			DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(d.parse(startTime));
			c.add(Calendar.DAY_OF_MONTH, 1);
			startTime_1 = d.format(c.getTime());
			list1 = os.searchTnumber(ssite, esite,abc, startTime_1);
			rid1 = rid;
			type1 = type;
			oid1 = oid;
		}else {
			list1 = os.searchTnumber(ssite, esite,abc, startTime);
		}
		
		String ss = "0";
		List<VehicleInfo> list = new ArrayList<VehicleInfo>();
		if(request.getSession(false).getAttribute("user1")!=null&&request.getSession(false).getAttribute("user2")!=null) {
			ss="1";
		}
		request.setAttribute("rid",rid);
		request.setAttribute("type",type);
		request.setAttribute("oid",oid);
		request.setAttribute("ss",ss);
		request.setAttribute("list",list1);
		if("0".equals(f)) {
			request.setAttribute("startTime",startTime_1);
		}else {
			request.setAttribute("startTime",startTime);
		}
		
		request.setAttribute("ssite",ssite);
		request.setAttribute("esite",esite);
		request.setAttribute("sum",list.size());
		request.setAttribute("flag","0");
		return "/WEB-INF/jsp/qt/searchChange.jsp";
	}


	public String changeDpH(HttpServletRequest request)throws Exception{
		os.refund(oid1, type1, rid1);
		if(request.getSession(false).getAttribute("user1")!=null&&request.getSession(false).getAttribute("user2")!=null) {
			int tid = Integer.parseInt(request.getParameter("tid"));
			int rid = Integer.parseInt(request.getParameter("rid"));
			String type = request.getParameter("type");
			Customer customer = (Customer) request.getSession().getAttribute("user2");
			String oid = random();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datetime = df.format(new Date());
			String startTime = request.getParameter("startTime");
			Order order = new Order();
			order.setOid(oid);
			order.setRid(rid);
			order.setTid(tid);
			order.setType(type);
			order.setUserid(customer.getUserid());
			order.setTime(datetime);
			order.setStartTime(startTime);
			order.setNum(os.getSeatNum(type, rid));
			order.setPrice(os.getPrice(type, rid));
			if(os.pdOrder(customer.getUserid(), tid)!=0) {
				return this.orderInfo(request);
			}
			os.order(order);
		}else {
			return "/WEB-INF/jsp/qt/login.jsp";
		}
		return this.orderInfo(request);
	}

	public String qupiao(HttpServletRequest request)throws Exception{
		String oid = request.getParameter("oid");
		os.qupiao(oid);
		return orderInfo(request);
	}
	

	public String orderList(HttpServletRequest request)throws Exception{
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize =10;
		int totalRecord = os.getOrderCount();
		Page p = new Page(pageNum,pageSize,totalRecord);
		List<OrderVo> list = os.orderList(p.getIndex(), p.getPageSize());
		request.setAttribute("link", "admin/orderList?pageNum");
		request.setAttribute("page", p);
		request.setAttribute("oList", list);
		return "/WEB-INF/jsp/ht/orderList.jsp";
	}
	

	public String orderListByName(HttpServletRequest request)throws Exception{
		String name = request.getParameter("name");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize =10;
		int totalRecord = os.getOrderCount(name);
		Page p = new Page(pageNum,pageSize,totalRecord);
		List<OrderVo> list = os.orderList(p.getIndex(), p.getPageSize(),name);
		request.setAttribute("link", "admin/orderListByName?name="+name+"&pageNum");
		request.setAttribute("page", p);
		request.setAttribute("oList", list);
		return "/WEB-INF/jsp/ht/orderList.jsp";
	}
	

	public String deleteOrder(HttpServletRequest request)throws Exception{
		String oid = request.getParameter("oid");
		os.deleteOrder(oid);
		return orderList(request);
	}

	public String deleteOrderByUser(HttpServletRequest request)throws Exception{
		User user = (User) request.getSession().getAttribute("user1");
		String oid = request.getParameter("oid");
		os.deleteOrderByUser(oid,user.getUserid());
		return orderInfo(request);
	}
}
