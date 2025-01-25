package test.huoche.wyh.solve.sqlwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.huoche.wyh.solve.bean.Order;
import test.huoche.wyh.solve.bean.OrderVo;
import test.huoche.wyh.solve.bean.VehicleInfo;
import test.huoche.wyh.solve.utils.DButil;

/*
 * 订单dao层
 */
public class OrderDao {

	Connection conn = DButil.getInstance().getConnection();

	public void order(Order order)throws Exception{
//		conn.setAutoCommit(false);
//		PreparedStatement checkst = conn.prepareStatement("select * from order where oid = ?");
//		checkst.setString(1,order.getOid());
//		ResultSet rs = checkst.executeQuery();
//		if(rs.next()){
//			conn.rollback();
//			conn.setAutoCommit(true);
//			throw new SQLException();
//		}
		String sql = "insert into `order`(oid,userid,type,num,tid,rid,time,startTime,price)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, order.getOid());
		pst.setInt(2, order.getUserid());
		pst.setString(3, order.getType());
		pst.setInt(4, order.getNum());
		pst.setInt(5, order.getTid());
		pst.setInt(6, order.getRid());
		pst.setString(7, order.getTime());
		pst.setString(8, order.getStartTime());
		pst.setDouble(9, order.getPrice());
		pst.executeUpdate();
//		conn.setAutoCommit(true);
//		conn.commit();
	}
	
	
	public double getPrice(String type,int rid) throws SQLException {
		String t = "";
		if("2".equals(type)||"1".equals(type)) {
			t = "hardseatprice";
		}else if("3".equals(type)) {
			t = "hardsleeperprice";
		}else if("4".equals(type)) {
			t = "softsleeperprice";
		}
		String sql = "select "+t+" from price where rid="+rid;
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		double cnt = 0;
		if(rs.next()) {
			cnt = rs.getDouble(1);
		}
		return cnt;
	}

	public int getSeatSum(String type,int rid) throws SQLException {
		String t = "";
		if("2".equals(type)) {
			t = "hardseat";
		}else if("3".equals(type)) {
			t = "hardsleeper";
		}else if("4".equals(type)) {
			t = "softsleeper";
		}
		String sql = "select "+t+" from seatnum where rid="+rid;
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
	public int checkdp(String type,int rid)throws Exception{
		int tmp=getSeatNum(type,rid)-1;
		int res=getSeatSum(type,rid);
		System.out.println(tmp);
		System.out.println(res);
		if(tmp>=res){
			return 0;
		}else{
			return 1;
		}
	}
	
	public int getSeatNum(String type,int rid)throws Exception{
		String t = "";
		if("2".equals(type)) {
			t = "hardseat1";
		}else if("3".equals(type)) {
			t = "hardsleeper1";
		}else if("4".equals(type)) {
			t = "softsleeper1";
		}
		String sql = "select "+t+" from seatnum where rid="+rid;
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		int f = getSeatSum(type,rid)-cnt+1;
		String sql1 = "update seatnum set "+t+"="+(cnt-1)+" where rid="+rid;
		PreparedStatement pst1 = null;
		pst1 = conn.prepareStatement(sql1);
		pst1.executeUpdate();
		return f;
		
	}
	public int pdOrder(int userid,int tid)throws Exception{
		String sql = "select count(*) from `order` where userid="+userid+" and tid="+tid;
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



	public int pdOrder(int tid)throws Exception{
		String sql = "select count(*) from `order` where tid="+tid;
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

	public List<OrderVo> getOrderList(int userid)throws Exception{
		String sql = "SELECT c.userid,c.name,c.type,cardid,o.type,o.num,o.rid,tnum,ssite,esite,stime,oid,o.time,o.startTime,o.price,order_status \r\n" + 
				"FROM customer c,tnumber t,route r,price p,`order` o \r\n" + 
				"WHERE t.tid=r.tid AND r.rid=p.rid  AND r.rid=o.rid AND c.userid=o.userid AND c.userid="+userid;
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		OrderVo orderVo = null;
		List<OrderVo> list = new ArrayList<OrderVo>();
		while(rs.next()) {
			orderVo = new OrderVo();
			orderVo.setUserid(rs.getInt("userid"));
			orderVo.setUsername(rs.getString("name"));
			orderVo.setTypeC(rs.getString(3));
			orderVo.setCardid(rs.getString("cardid"));
			orderVo.setType(rs.getString(5));
			orderVo.setNum(rs.getInt("num"));
			orderVo.setRid(rs.getInt("rid"));
			orderVo.setTnum(rs.getString("tnum"));
			orderVo.setSsite(rs.getString("ssite"));
			orderVo.setEsite(rs.getString("esite"));
			orderVo.setStime(rs.getString("stime"));
			orderVo.setOid(rs.getString("oid"));
			orderVo.setTime(rs.getString("time"));
			orderVo.setStartTime(rs.getString("startTime"));
			orderVo.setPrice(rs.getDouble("price"));
			orderVo.setOrder_status(rs.getString("order_status"));
			list.add(orderVo);
		}
		return list;
	}

	public void refund(String oid,String type,int rid)throws Exception{
		
		String sql2 = "delete from `order` where oid="+oid;
		PreparedStatement pst2= null;
		pst2 = conn.prepareStatement(sql2);
		pst2.executeUpdate();
		String t = "";
		if("2".equals(type)) {
			t = "hardseat1";
		}else if("3".equals(type)) {
			t = "hardsleeper1";
		}else if("4".equals(type)) {
			t = "softsleeper1";
		}
		String sql = "select "+t+" from seatnum where rid="+rid;
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = 0; //120 120    120-120+1；120-119+1
		if(rs.next()) {
			cnt = rs.getInt(t);
		}
		String sql1 = "update seatnum set "+t+"="+(cnt+1)+" where rid="+rid;
		PreparedStatement pst1 = null;
		pst1 = conn.prepareStatement(sql1);
		pst1.executeUpdate();
	}

	public List<VehicleInfo> searchTnumber(String ssite,String esite,String time,String startTime) throws SQLException{
		String sql = "SELECT tnumber.tid,tnumber.tnum,tnumber.startsite,tnumber.endsite,tnumber.starttime,tnumber.endtime,route.*,seatnum.sid,seatnum.softsleeper,seatnum.hardsleeper,seatnum.hardseat,\r\n" + 
				"price.pid,price.softsleeperprice,price.hardseatprice,price.hardsleeperprice,seatnum.softsleeper1,seatnum.hardsleeper1,seatnum.hardseat1 \r\n" + 
				"FROM route,price,seatnum,tnumber\r\n" + 
				"WHERE tnumber.tid=route.tid AND route.rid=price.rid AND route.rid=seatnum.rid AND STATUS=1\r\n" + 
				"AND ssite='"+ssite+"' AND esite='"+esite+"';";
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		List<VehicleInfo> list = new ArrayList<VehicleInfo>();
		VehicleInfo vehicleInfo = null;
		while(rs.next()) {
			String sysdate = startTime;
			int res1=sysdate.compareTo(time);
			if(res1>0) {		
					vehicleInfo = new VehicleInfo();
					vehicleInfo.setTnum(rs.getString("tnum"));
					vehicleInfo.setStartsite(rs.getString("startsite"));
					vehicleInfo.setEndsite(rs.getString("endsite"));
					vehicleInfo.setRuntime(rs.getString("runtime"));
					vehicleInfo.setSsite(rs.getString("ssite"));
					vehicleInfo.setEsite(rs.getString("esite"));
					vehicleInfo.setStime(rs.getString("stime"));
					vehicleInfo.setEtime(rs.getString("etime"));
					vehicleInfo.setRmileage(rs.getInt("rmileage"));
					vehicleInfo.setStoptime(rs.getInt("stoptime"));
					vehicleInfo.setSoftsleeper(rs.getInt("softsleeper"));
					vehicleInfo.setHardsleeper(rs.getInt("hardsleeper"));
					vehicleInfo.setHardseat(rs.getInt("hardseat"));
					vehicleInfo.setSoftsleeper1(rs.getInt("softsleeper1"));
					vehicleInfo.setHardsleeper1(rs.getInt("hardsleeper1"));
					vehicleInfo.setHardseat1(rs.getInt("hardseat1"));
					vehicleInfo.setSoftsleeperprice(rs.getDouble("softsleeperprice"));
					vehicleInfo.setHardsleeperprice(rs.getDouble("hardsleeperprice"));
					vehicleInfo.setHardseatprice(rs.getDouble("hardseatprice"));
					vehicleInfo.setTid(rs.getInt("tid"));
					vehicleInfo.setPid(rs.getInt("pid"));
					vehicleInfo.setRid(rs.getInt("rid"));
					vehicleInfo.setSid(rs.getInt("sid"));
					list.add(vehicleInfo);
				}
		}
		return list;
	}
	public void qupiao(String oid)throws Exception{
		String sql = "update `order` set order_status=1 where oid="+oid;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.executeUpdate();
	}

	public List<OrderVo> orderList(int cnt1,int cnt2)throws Exception{
		String sql = "SELECT c.userid,c.name,c.type,cardid,o.type,o.num,o.rid,tnum,ssite,esite,stime,oid,o.time,o.startTime,o.price,order_status \r\n" + 
				"FROM customer c,tnumber t,route r,price p,`order` o \r\n" + 
				"WHERE t.tid=r.tid AND r.rid=p.rid  AND r.rid=o.rid AND c.userid=o.userid limit ?,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, cnt1);
		pst.setInt(2, cnt2);
		rs = pst.executeQuery();
		OrderVo orderVo = null;
		List<OrderVo> list = new ArrayList<OrderVo>();
		while(rs.next()) {
			orderVo = new OrderVo();
			orderVo.setUserid(rs.getInt("userid"));
			orderVo.setUsername(rs.getString("name"));
			orderVo.setTypeC(rs.getString(3));
			orderVo.setCardid(rs.getString("cardid"));
			orderVo.setType(rs.getString(5));
			orderVo.setNum(rs.getInt("num"));
			orderVo.setRid(rs.getInt("rid"));
			orderVo.setTnum(rs.getString("tnum"));
			orderVo.setSsite(rs.getString("ssite"));
			orderVo.setEsite(rs.getString("esite"));
			orderVo.setStime(rs.getString("stime"));
			orderVo.setOid(rs.getString("oid"));
			orderVo.setTime(rs.getString("time"));
			orderVo.setStartTime(rs.getString("startTime"));
			orderVo.setPrice(rs.getDouble("price"));
			orderVo.setOrder_status(rs.getString("order_status"));
			list.add(orderVo);
		}
		return list;
	}
	
	public int getOrderCount()throws Exception {
		String sql = "select count(*) from `order`";
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
	

	public List<OrderVo> orderList(int cnt1,int cnt2,String name)throws Exception{
		String sql = "SELECT c.userid,c.name,c.type,cardid,o.type,o.num,o.rid,tnum,ssite,esite,stime,oid,o.time,o.startTime,o.price,order_status \r\n" + 
				"FROM customer c,tnumber t,route r,price p,`order` o \r\n" + 
				"WHERE t.tid=r.tid AND r.rid=p.rid  AND r.rid=o.rid AND c.userid=o.userid and tnum like '%"+name+"%'"+" limit ?,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, cnt1);
		pst.setInt(2, cnt2);
		rs = pst.executeQuery();
		OrderVo orderVo = null;
		List<OrderVo> list = new ArrayList<OrderVo>();
		while(rs.next()) {
			orderVo = new OrderVo();
			orderVo.setUserid(rs.getInt("userid"));
			orderVo.setUsername(rs.getString("name"));
			orderVo.setTypeC(rs.getString(3));
			orderVo.setCardid(rs.getString("cardid"));
			orderVo.setType(rs.getString(5));
			orderVo.setNum(rs.getInt("num"));
			orderVo.setRid(rs.getInt("rid"));
			orderVo.setTnum(rs.getString("tnum"));
			orderVo.setSsite(rs.getString("ssite"));
			orderVo.setEsite(rs.getString("esite"));
			orderVo.setStime(rs.getString("stime"));
			orderVo.setOid(rs.getString("oid"));
			orderVo.setTime(rs.getString("time"));
			orderVo.setStartTime(rs.getString("startTime"));
			orderVo.setPrice(rs.getDouble("price"));
			orderVo.setOrder_status(rs.getString("order_status"));
			list.add(orderVo);
		}
		return list;
	}
	
	public int getOrderCount(String name)throws Exception {
		String sql = "select count(*) from `order` o,tnumber t where o.tid=t.tid and tnum like '%"+name+"%'";
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
	

	public void deleteOrder(String oid)throws Exception{
		String sql2 = "delete from `order` where oid="+oid;
		PreparedStatement pst2= null;
		pst2 = conn.prepareStatement(sql2);
		pst2.executeUpdate();
	}
	public void deleteOrderByUser(String oid,int userid)throws Exception{
		String sql2 = "delete from `order` where oid="+oid+" and userid="+userid;
		PreparedStatement pst2= null;
		pst2 = conn.prepareStatement(sql2);
		pst2.executeUpdate();
	}
}
