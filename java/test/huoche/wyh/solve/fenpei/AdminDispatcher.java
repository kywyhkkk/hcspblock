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
 * Servlet implementation class AdminDispatcher
 */
public class AdminDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserController uc = new UserController();
	private static final NumberController nc = new NumberController();
	private static final OrderController oc = new OrderController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDispatcher() {
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
		case "userList":
			try {
				traget = uc.userList(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "disabledUser":
			try {
				traget = uc.disabledUser(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "enabledUser":
			try {
				traget = uc.enabledUser(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "deleteUser":
			try {
				traget = uc.deleteUser(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "goUserUpdate":
			try {
				traget = uc.goUserUpdate(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "userUpdate":
			try {
				traget = uc.userUpdate(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "numberList":
			try {
				traget = nc.numberList(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "deleteTnumber":
			try {
				traget = nc.deleteTnumber(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "perfectInformation":
			try {
				traget = nc.perfectInformation(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "getPerfectInformation":
			try {
				traget = nc.getPerfectInformation(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "perfectInformationUpdate":
			try {
				traget = nc.perfectInformationUpdate(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "getNumber":
			try {
				traget = nc.getNumber(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "numberUpdate":
			try {
				traget = nc.numberUpdate(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "numberInsert":
			try {
				traget = nc.numberInsert(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "numberAdd":
			try {
				traget = nc.numberAdd(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "qy":
			try {
				traget = nc.updateRouteStatusQy(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "zt":
			try {
				traget = nc.updateRouteStatusZt(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "orderList":
			try {
				traget = oc.orderList(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "deleteOrder":
			try {
				traget = oc.deleteOrder(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "numberListByName":
			try {
				traget = nc.numberListByName(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "orderListByName":
			try {
				traget = oc.orderListByName(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "userListByName":
			try {
				traget = uc.userListByName(request);
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
