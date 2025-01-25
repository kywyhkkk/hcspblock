package test.huoche.wyh.solve.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import test.huoche.wyh.solve.bean.Page;
import test.huoche.wyh.solve.bean.Tnumber;
import test.huoche.wyh.solve.bean.User;
import test.huoche.wyh.solve.bean.VehicleInfo;
import test.huoche.wyh.solve.solv.NumberService;

public class NumberController {
	NumberService numberService = new NumberService();

	public String numberList(HttpServletRequest request)throws Exception{
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize =10;
		int totalRecord = numberService.getCountAll();
		Page p = new Page(pageNum,pageSize,totalRecord);
		List<Tnumber> list = numberService.selectAll(p.getIndex(),p.getPageSize());
		request.setAttribute("link", "admin/numberList?pageNum");
		request.setAttribute("page", p);
		request.setAttribute("nList", list);
		return "/WEB-INF/jsp/ht/numberList.jsp";
	}
	
	public String numberListByName(HttpServletRequest request)throws Exception{
		String name = request.getParameter("name");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize =10;
		int totalRecord = numberService.getCountAll(name);
		Page p = new Page(pageNum,pageSize,totalRecord);
		List<Tnumber> list = numberService.selectAll(p.getIndex(),p.getPageSize(),name);
		request.setAttribute("link", "admin/numberListByName?name="+name+"&pageNum");
		request.setAttribute("page", p);
		request.setAttribute("nList", list);
		return "/WEB-INF/jsp/ht/numberList.jsp";
	}
	
	

	public String deleteTnumber(HttpServletRequest request)throws Exception{
		int tid = Integer.parseInt(request.getParameter("tid"));
		numberService.deleteTnumber(tid);
		return numberList(request);
	}

	public String perfectInformation(HttpServletRequest request)throws Exception{
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int tid = Integer.parseInt(request.getParameter("tid"));
		int pageSize =10;
		int totalRecord = numberService.getCountTnumber(tid);
		Page p = new Page(pageNum,pageSize,totalRecord);
		List<VehicleInfo> list = numberService.selectTnumberInfo(tid, p.getIndex(), p.getPageSize());
		request.setAttribute("link", "admin/perfectInformation?tid="+tid+"&pageNum");
		request.setAttribute("page", p);
		request.setAttribute("pList", list);
		return "/WEB-INF/jsp/ht/perfectInformation.jsp";
	}

	public String getPerfectInformation(HttpServletRequest request)throws Exception{
		int tid = Integer.parseInt(request.getParameter("tid"));
		int rid = Integer.parseInt(request.getParameter("rid"));
		VehicleInfo vehicleInfo = numberService.getTnumberInfo(tid, rid);
		request.setAttribute("vehicleInfo", vehicleInfo);
		return "/WEB-INF/jsp/ht/perfectInformationUpdate.jsp";
	}

	public String perfectInformationUpdate(HttpServletRequest request)throws Exception{
		int tid = Integer.parseInt(request.getParameter("tid"));
		int rid = Integer.parseInt(request.getParameter("rid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		int sid = Integer.parseInt(request.getParameter("sid"));
		String stime = request.getParameter("stime");
		String etime = request.getParameter("etime");
		String runtime = request.getParameter("runtime");
		int softsleeper = Integer.parseInt(request.getParameter("softsleeper"));
		int hardsleeper = Integer.parseInt(request.getParameter("hardsleeper"));
		int hardseat = Integer.parseInt(request.getParameter("hardseat"));
		double softsleeperprice = Double.parseDouble(request.getParameter("softsleeperprice"));
		double hardsleeperprice = Double.parseDouble(request.getParameter("hardsleeperprice"));
		double hardseatprice = Double.parseDouble(request.getParameter("hardseatprice"));
		int rmileage = Integer.parseInt(request.getParameter("rmileage"));
		int stoptime = Integer.parseInt(request.getParameter("stoptime"));
		VehicleInfo vehicleInfo = new VehicleInfo();
		vehicleInfo.setSid(sid);
		vehicleInfo.setTid(tid);
		vehicleInfo.setRid(rid);
		vehicleInfo.setPid(pid);
		vehicleInfo.setRuntime(runtime);
		vehicleInfo.setStime(stime);
		vehicleInfo.setEtime(etime);
		vehicleInfo.setSoftsleeper(softsleeper);
		vehicleInfo.setHardsleeper(hardsleeper);
		vehicleInfo.setHardseat(hardseat);
		vehicleInfo.setSoftsleeperprice(softsleeperprice);
		vehicleInfo.setHardsleeperprice(hardsleeperprice);
		vehicleInfo.setHardseatprice(hardseatprice);
		vehicleInfo.setRmileage(rmileage);
		vehicleInfo.setStoptime(stoptime);
		numberService.perfectInformationUpdate(vehicleInfo);
		return perfectInformation(request);
	}
	

	public String getNumber(HttpServletRequest request)throws Exception{
		int tid = Integer.parseInt(request.getParameter("tid"));
		Tnumber t = numberService.getTnumber(tid);
		request.setAttribute("t", t);
		return "/WEB-INF/jsp/ht/numberUpdate.jsp";
	}

	public String numberUpdate(HttpServletRequest request)throws Exception{
		int tid = Integer.parseInt(request.getParameter("tid"));
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String startsite = request.getParameter("startsite");
		String endsite = request.getParameter("endsite");
		int mileage = Integer.parseInt(request.getParameter("mileage"));
		String site = request.getParameter("site");
		Tnumber t = new Tnumber();
		t.setTid(tid);
		t.setEndsite(endsite);
		t.setEndtime(endtime);
		t.setMileage(mileage);
		t.setSite(site);
		t.setStartsite(startsite);
		t.setStarttime(starttime);
		numberService.updateTnumber(t);
		return numberList(request);
		
	}

	public String numberInsert(HttpServletRequest request)throws Exception{
		return "/WEB-INF/jsp/ht/numberInsert.jsp";
	}
	

	public String numberAdd(HttpServletRequest request)throws Exception{
		String tnum = request.getParameter("tnum");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String startsite = request.getParameter("startsite");
		String endsite = request.getParameter("endsite");
		int mileage = Integer.parseInt(request.getParameter("mileage"));
		String site = request.getParameter("site");
		Tnumber t = new Tnumber();
		t.setTnum(tnum);
		t.setEndsite(endsite);
		t.setEndtime(endtime);
		t.setMileage(mileage);
		t.setSite(site);
		t.setStartsite(startsite);
		t.setStarttime(starttime);
		numberService.insertTnumber(t);
		return numberList(request);
	}

	public String updateRouteStatusQy(HttpServletRequest request)throws Exception{
		int tid = Integer.parseInt(request.getParameter("tid"));
		numberService.updateRouteStatusQy(tid);
		return numberList(request);
	}
	

	public String updateRouteStatusZt(HttpServletRequest request)throws Exception{
		int tid = Integer.parseInt(request.getParameter("tid"));
		numberService.updateRouteStatusZt(tid);
		return numberList(request);
	}

	public String main() {
		return "/WEB-INF/jsp/qt/main.jsp";
	}
	
	public String searchTnumber(HttpServletRequest request)throws Exception{
		String ssite = request.getParameter("ssite");
		String esite = request.getParameter("esite");
		String startTime = request.getParameter("startTime");
		List<VehicleInfo> list1 = numberService.searchTnumber(ssite, esite,startTime);
		String ss = "0";
		List<VehicleInfo> list = new ArrayList<VehicleInfo>();
		if(request.getSession(false).getAttribute("user1")!=null&&request.getSession(false).getAttribute("user2")!=null) {
			ss="1";
			User user = ((User)request.getSession().getAttribute("user1"));
			List<Integer> li = numberService.Tnum(user.getUserid());
			Map<Integer, Integer> mp = new HashMap<>();
			for(int a:li){
				mp.put(a,1);
			}

			for(VehicleInfo vehicleInfo:list1){
				if(!mp.containsKey(vehicleInfo.getTid())){
					list.add(vehicleInfo);
				}
			}

//			for(VehicleInfo vehicleInfo:list1) {
//				int flag = 0;
//				for(int a:li) {
//					if(vehicleInfo.getTid()==a) {
//						flag=1;
//						break;
//					}
//				}
//				if(flag==0) {
//					list.add(vehicleInfo);
//				}
//
//			}
		}else {
			for(VehicleInfo vehicleInfo:list1) {
					list.add(vehicleInfo);
			}
		}
		request.setAttribute("ss",ss);
		request.setAttribute("list",list);
		request.setAttribute("startTime",startTime);
		request.setAttribute("ssite",ssite);
		request.setAttribute("esite",esite);
		request.setAttribute("sum",list.size());
		request.setAttribute("flag","1");
		return "/WEB-INF/jsp/qt/search.jsp";
	}

}
