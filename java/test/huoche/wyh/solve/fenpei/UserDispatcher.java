package test.huoche.wyh.solve.fenpei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.huoche.wyh.solve.controller.NumberController;
import test.huoche.wyh.solve.controller.OrderController;
import test.huoche.wyh.solve.controller.UserController;

/**
 * Servlet implementation class UserDispatcher
 */
public class UserDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserController uc = new UserController();
	private static final NumberController nc = new NumberController();
	private static final OrderController oc = new OrderController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		String myUrl = url.substring(url.lastIndexOf("/")+1);
		String traget="";
		switch(myUrl) {
		case "main":
			traget = nc.main();
			break;
		case "searchTnumber":
			try {
				traget = nc.searchTnumber(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "login":
			traget = uc.login();
			break;
		case "reg":
			traget = uc.reg();
			break;
		case "regForm":
			try {
				traget = uc.regForm(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "main1":
			try {
				traget = uc.main(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "logout":
			traget = uc.logout(request);
			break;
		case "success":
			traget = uc.success(request);
			break;
		case "order":
			try {
				traget = oc.order(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "userOrder":
			try {
				traget = oc.userOrder(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "orderInfo":
			try {
				traget = oc.orderInfo(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "refund":
			try {
				traget = oc.refund(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "change":
			try {
				traget = oc.change(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "changeDpH":
			try {
				traget = oc.changeDpH(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "qupiao":
			try {
				traget = oc.qupiao(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "personalInfo":
			try {
				traget = uc.userInfo(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "userUpdate":
			try {
				traget = uc.userUpdateInfo(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "deleteOrder":
			try {
				traget = oc.deleteOrderByUser(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		}
		if(traget.endsWith("jsp")) {
			request.getRequestDispatcher(traget).forward(request, response);
		}else {
			response.sendRedirect(traget);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
