package test.huoche.wyh.solve.solv;

import java.sql.SQLException;
import java.util.List;

import test.huoche.wyh.solve.bean.Order;
import test.huoche.wyh.solve.bean.OrderVo;
import test.huoche.wyh.solve.bean.VehicleInfo;
import test.huoche.wyh.solve.sqlwork.OrderDao;

public class OrderService {
	
	OrderDao od = new OrderDao();
	
	public void order(Order order)throws Exception{
		od.order(order);
	}
	public int getSeatNum(String type,int rid)throws Exception{
		return od.getSeatNum(type,rid);
	}
	public double getPrice(String type,int rid) throws SQLException {
		return od.getPrice(type, rid);
	}
	public List<OrderVo> getOrderList(int userid)throws Exception{
		return od.getOrderList(userid);
	}
	public int pdOrder(int userid,int tid)throws Exception{
		return od.pdOrder(userid, tid);
	}
	public int checkdp(String type,int rid)throws Exception{
		return od.checkdp(type, rid);
	}
	public void refund(String oid,String type,int rid)throws Exception{
		od.refund(oid, type, rid);
	}
	public List<VehicleInfo> searchTnumber(String ssite, String esite, String time, String startTime) throws SQLException{
		return od.searchTnumber(ssite, esite, time,startTime);
	}
	public void qupiao(String oid)throws Exception{
		od.qupiao(oid);
	}
	public List<OrderVo> orderList(int cnt1,int cnt2)throws Exception{
		return od.orderList(cnt1,cnt2);
	}
	public int getOrderCount()throws Exception {
		return od.getOrderCount();
	}
	public void deleteOrder(String oid)throws Exception{
		od.deleteOrder(oid);
	}
	public void deleteOrderByUser(String oid,int userid)throws Exception{
		od.deleteOrderByUser(oid, userid);
	}
	public List<OrderVo> orderList(int cnt1,int cnt2,String name)throws Exception{
		return od.orderList(cnt1, cnt2, name);
	}
	public int getOrderCount(String name)throws Exception {
		return od.getOrderCount(name);
	}
}
