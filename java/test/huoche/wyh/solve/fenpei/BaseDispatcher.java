package test.huoche.wyh.solve.fenpei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.huoche.wyh.solve.controller.BaseController;

/**
 * Servlet implementation class BaseDispatcher
 */
public class BaseDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final BaseController bc = new BaseController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseDispatcher() {
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
		case "login":
			try {
				traget = bc.login(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "main":
			try {
				traget = bc.main(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "logout":
			try {
				traget = bc.logout(request);
			} catch (Exception e) {e.printStackTrace();}
			break;
		case "about":
			traget = bc.about();
			break;
		case "help":
			traget = bc.help();
			break;
		case "help1":
			traget = bc.help1();
			break;
		case "help2":
			traget = bc.help2();
			break;
		case "help3":
			traget = bc.help3();
			break;
		case "help4":
			traget = bc.help4();
			break;
		case "help5":
			traget = bc.help5();
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
