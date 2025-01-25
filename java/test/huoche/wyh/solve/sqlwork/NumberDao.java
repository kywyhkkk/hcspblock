package test.huoche.wyh.solve.sqlwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.huoche.wyh.solve.bean.Tnumber;
import test.huoche.wyh.solve.bean.VehicleInfo;
import test.huoche.wyh.solve.utils.DButil;

/**
 * 车次管理的dao层
 *
 */
public class NumberDao {
	Connection conn = DButil.getInstance().getConnection();

	public List<Tnumber> selectAll(int cnt1,int cnt2)throws Exception{
		String sql = "select * from tnumber limit "+cnt1+","+cnt2;
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		List<Tnumber> list = new ArrayList<Tnumber>();
		Tnumber tnumber = null;
		while(rs.next()) {
			tnumber = new Tnumber();
			tnumber.setTid(rs.getInt("tid"));
			tnumber.setTnum(rs.getString("tnum"));
			tnumber.setStartsite(rs.getString("startsite"));
			tnumber.setEndsite(rs.getString("endsite"));
			tnumber.setStarttime(rs.getString("starttime"));
			tnumber.setEndtime(rs.getString("endtime"));
			tnumber.setMileage(rs.getInt("mileage"));
			tnumber.setStatus(rs.getString("status"));
			tnumber.setSite(rs.getString("site"));
			list.add(tnumber);
		}
		return list;
	}
	

	public List<Tnumber> selectAll(int cnt1,int cnt2,String name)throws Exception{
		String sql = "select * from tnumber where tnum like '%"+name+"%' limit "+cnt1+","+cnt2;
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		List<Tnumber> list = new ArrayList<Tnumber>();
		Tnumber tnumber = null;
		while(rs.next()) {
			tnumber = new Tnumber();
			tnumber.setTid(rs.getInt("tid"));
			tnumber.setTnum(rs.getString("tnum"));
			tnumber.setStartsite(rs.getString("startsite"));
			tnumber.setEndsite(rs.getString("endsite"));
			tnumber.setStarttime(rs.getString("starttime"));
			tnumber.setEndtime(rs.getString("endtime"));
			tnumber.setMileage(rs.getInt("mileage"));
			tnumber.setStatus(rs.getString("status"));
			tnumber.setSite(rs.getString("site"));
			list.add(tnumber);
		}
		return list;
	}
	

	public int getCountAll(String name)throws Exception{
		String sql = "select count(*) from tnumber where tnum like '%"+name+"%'";
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = 0 ;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	

	public Tnumber getTnumber(int tid)throws Exception{
		String sql = "select * from tnumber where tid="+tid;
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		Tnumber tnumber = new Tnumber();
		while(rs.next()) {
			tnumber.setTid(rs.getInt("tid"));
			tnumber.setTnum(rs.getString("tnum"));
			tnumber.setStartsite(rs.getString("startsite"));
			tnumber.setEndsite(rs.getString("endsite"));
			tnumber.setStarttime(rs.getString("starttime"));
			tnumber.setEndtime(rs.getString("endtime"));
			tnumber.setMileage(rs.getInt("mileage"));
			tnumber.setStatus(rs.getString("status"));
			tnumber.setSite(rs.getString("site"));
		}
		return tnumber;
	}
	
	
	public void insertTnumber(Tnumber tnumber)throws Exception{	
		String s[] = tnumber.getSite().split("-");
				if(!s[0].equals(tnumber.getStartsite())) {
					tnumber.setStartsite(s[0]);
				}
				if(!s[s.length-1].equals(tnumber.getEndsite())) {
					tnumber.setEndsite(s[s.length-1]);
				}
		String sql = "insert into tnumber(tnum,startsite,endsite,starttime,endtime,mileage,site)"
				+ "value(?,?,?,?,?,?,?)";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, tnumber.getTnum());
		pst.setString(2, tnumber.getStartsite());
		pst.setString(3, tnumber.getEndsite());
		pst.setString(4, tnumber.getStarttime());
		pst.setString(5, tnumber.getEndtime());
		pst.setInt(6, tnumber.getMileage());
		pst.setString(7, tnumber.getSite());
		pst.executeUpdate();
		VehicleInfo vehicleInfo = new VehicleInfo();
		vehicleInfo.setTid(this.getTid());
			for(int i=0;i<s.length;i++) {
				for(int j=i+1;j<s.length;j++) {
					vehicleInfo.setSsite(s[i]);
					vehicleInfo.setEsite(s[j]);
					System.out.println(s[i]+"   "+s[j]);
					this.insertPerfectInformationRoute(vehicleInfo);
					int rid = this.getRid(s[i],s[j],vehicleInfo.getTid());
					System.out.println(rid);
					this.insertPerfectInformationPrice(rid);
					this.insertPerfectInformationSeatNum(rid);
				}
			}
	}
	
	

	public void updateTnumber(Tnumber tnumber)throws Exception{	
		boolean pd = this.pdSite(tnumber.getSite(), tnumber.getTid());
		String site = tnumber.getSite();
		String s[] = site.split("-");
		if(!s[0].equals(tnumber.getStartsite())) {
			tnumber.setStartsite(s[0]);
		}
		if(!s[s.length-1].equals(tnumber.getEndsite())) {
			tnumber.setEndsite(s[s.length-1]);
		}
		String sql = "update tnumber set startsite=?,endsite=?,starttime=?,endtime=?,mileage=?"
				+ ",site=? where tid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, tnumber.getStartsite());
		pst.setString(2, tnumber.getEndsite());
		pst.setString(3, tnumber.getStarttime());
		pst.setString(4, tnumber.getEndtime());
		pst.setInt(5, tnumber.getMileage());
		pst.setString(6, tnumber.getSite());
		pst.setInt(7, tnumber.getTid());
		this.deletePerfectInformation(tnumber.getTid());
		pst.executeUpdate();
		VehicleInfo vehicleInfo = new VehicleInfo();
		vehicleInfo.setTid(tnumber.getTid());
		if(pd) {
			for(int i=0;i<s.length;i++) {
				for(int j=i+1;j<s.length;j++) {
					vehicleInfo.setSsite(s[i]);
					vehicleInfo.setEsite(s[j]);
					this.insertPerfectInformationRoute(vehicleInfo);
					int rid = this.getRid(s[i], s[j],tnumber.getTid());
					this.insertPerfectInformationPrice(rid);
					this.insertPerfectInformationSeatNum(rid);
				}
			}
		}
		
		
		
	}

	public boolean pdSite(String site,int tid)throws Exception{
		String sql = "select site from tnumber where tid="+tid;
		String s = "";
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		if(rs.next()) {
			s = rs.getString(1);
		}
		if(site.equals(s)) {
			//站点信息并未改变
			return false;
		}
		return true;
	}
	
	

	public int getRid(String s1,String s2,int tid)throws Exception{
		String sql = "select rid from route where ssite='"+s1+"' and esite='"+s2+"' and tid="+tid;
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int rid = 0;
		if(rs.next()) {
			rid = rs.getInt(1);
		}
		return rid;
	}

	public int getTid()throws Exception{
		String sql = "SELECT  MAX(tid) FROM tnumber";
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int tid = 0;
		if(rs.next()) {
			tid = rs.getInt(1);
		}
		return tid;
	}

	public void deletePerfectInformation(int tid)throws Exception{
		String sql = "delete from route where tid="+tid;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.executeUpdate();

	}

	public void insertPerfectInformationRoute(VehicleInfo vehicleInfo)throws Exception{
		String sql = "insert into route(tid,ssite,esite) values(?,?,?)";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, vehicleInfo.getTid());
		pst.setString(2, vehicleInfo.getSsite());
		pst.setString(3, vehicleInfo.getEsite());
		pst.executeUpdate();
		
	}
	

	public void insertPerfectInformationSeatNum(int rid)throws Exception{
		String sql = "insert into seatnum(rid) values(?)";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, rid);
		pst.executeUpdate();
		
	}

	public void insertPerfectInformationPrice(int rid)throws Exception{
		String sql = "insert into price(rid) values(?)";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, rid);
		pst.executeUpdate();
		
	}
	

	public int getCountAll()throws Exception{
		String sql = "select count(*) from tnumber";
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = 0 ;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}

	public int getCountTnumber(int tid)throws Exception{
		String sql = "select count(*) from route where tid="+tid;
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = 0 ;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	

	public List<VehicleInfo> selectTnumberInfo(int tid,int cnt1,int cnt2)throws Exception{
		String sql = "SELECT ssite,esite,stime,etime,rmileage,runtime,stoptime,softsleeper,hardsleeper,hardseat,softsleeperprice,hardsleeperprice,hardseatprice,p.pid,r.rid,s.sid,t.tid\r\n" + 
				"FROM tnumber t,seatnum s,route r,price p\r\n" + 
				"WHERE t.tid=r.tid AND r.rid=s.rid AND r.rid=p.rid and t.tid="+tid+" limit "+cnt1+","+cnt2;
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		List<VehicleInfo> list = new ArrayList<VehicleInfo>();
		VehicleInfo vehicleInfo = null;
		while(rs.next()) {
			vehicleInfo = new VehicleInfo();
			vehicleInfo.setSsite(rs.getString("ssite"));
			vehicleInfo.setEsite(rs.getString("esite"));
			vehicleInfo.setStime(rs.getString("stime"));
			vehicleInfo.setEtime(rs.getString("etime"));
			vehicleInfo.setRuntime(rs.getString("runtime"));
			vehicleInfo.setRmileage(rs.getInt("rmileage"));
			vehicleInfo.setStoptime(rs.getInt("stoptime"));
			vehicleInfo.setSoftsleeper(rs.getInt("softsleeper"));
			vehicleInfo.setHardsleeper(rs.getInt("hardsleeper"));
			vehicleInfo.setHardseat(rs.getInt("hardseat"));
			vehicleInfo.setSoftsleeperprice(rs.getDouble("softsleeperprice"));
			vehicleInfo.setHardsleeperprice(rs.getDouble("hardsleeperprice"));
			vehicleInfo.setHardseatprice(rs.getDouble("hardseatprice"));
			vehicleInfo.setTid(rs.getInt("tid"));
			vehicleInfo.setPid(rs.getInt("pid"));
			vehicleInfo.setRid(rs.getInt("rid"));
			vehicleInfo.setSid(rs.getInt("sid"));
			list.add(vehicleInfo);
		}
		return list;
	}
	

	public void deleteTnumber(int tid)throws Exception{
		String sql = "delete from tnumber where tid="+tid;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.executeUpdate();
	}
	

	public VehicleInfo getTnumberInfo(int tid,int rid)throws Exception{
		String sql = "SELECT ssite,esite,stime,etime,rmileage,stoptime,runtime,softsleeper,hardsleeper,hardseat,softsleeperprice,hardsleeperprice,hardseatprice,p.pid,r.rid,s.sid,t.tid\r\n" + 
				"FROM tnumber t,seatnum s,route r,price p\r\n" + 
				"WHERE t.tid=r.tid AND r.rid=s.rid AND r.rid=p.rid and t.tid="+tid+" and r.rid="+rid;
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		VehicleInfo vehicleInfo = new VehicleInfo();
		if(rs.next()) {
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
			vehicleInfo.setSoftsleeperprice(rs.getDouble("softsleeperprice"));
			vehicleInfo.setHardsleeperprice(rs.getDouble("hardsleeperprice"));
			vehicleInfo.setHardseatprice(rs.getDouble("hardseatprice"));
			vehicleInfo.setTid(rs.getInt("tid"));
			vehicleInfo.setPid(rs.getInt("pid"));
			vehicleInfo.setRid(rs.getInt("rid"));
			vehicleInfo.setSid(rs.getInt("sid"));
		}
		return vehicleInfo;
	}
	

	public void perfectInformationUpdate(VehicleInfo vehicleInfo)throws Exception{
		this.priceUpdate(vehicleInfo);
		this.routeUpdate(vehicleInfo);
		this.seatUpdate(vehicleInfo);
	}

	public void priceUpdate(VehicleInfo vehicleInfo)throws Exception{
		String sql = "update price set softsleeperprice=?,hardsleeperprice=?,hardseatprice=? where pid=? and rid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setDouble(1, vehicleInfo.getSoftsleeperprice());
		pst.setDouble(2, vehicleInfo.getHardsleeperprice());
		pst.setDouble(3, vehicleInfo.getHardseatprice());
		pst.setInt(4, vehicleInfo.getPid());
		pst.setInt(5, vehicleInfo.getRid());
		pst.executeUpdate();
	}

	public void routeUpdate(VehicleInfo vehicleInfo)throws Exception{
		String sql = "update route set stime=?,etime=?,rmileage=?,stoptime=?,runtime=? where tid=? and rid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, vehicleInfo.getStime());
		pst.setString(2, vehicleInfo.getEtime());
		pst.setInt(3, vehicleInfo.getRmileage());
		pst.setInt(4, vehicleInfo.getStoptime());
		pst.setString(5, vehicleInfo.getRuntime());
		pst.setInt(6, vehicleInfo.getTid());
		pst.setInt(7, vehicleInfo.getRid());
		
		pst.executeUpdate();
	}

	public void seatUpdate(VehicleInfo vehicleInfo)throws Exception{
		String sql = "update seatnum set softsleeper=?,softsleeper1=?,hardsleeper=?,hardsleeper1=?,hardseat=?,hardseat1=? where sid=? and rid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, vehicleInfo.getSoftsleeper());
		pst.setInt(2, vehicleInfo.getSoftsleeper());
		pst.setInt(3, vehicleInfo.getHardsleeper());
		pst.setInt(4, vehicleInfo.getHardsleeper());
		pst.setInt(5, vehicleInfo.getHardseat());
		pst.setInt(6, vehicleInfo.getHardseat());
		pst.setInt(7, vehicleInfo.getSid());
		pst.setInt(8, vehicleInfo.getRid());
		pst.executeUpdate();
	}
	

	public void updateRouteStatusQy(int tid)throws Exception{
		String sql = "update tnumber set status=1 where tid="+tid;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.executeUpdate();
	}

	public void updateRouteStatusZt(int tid)throws Exception{
		String sql = "update tnumber set status=0 where tid="+tid;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.executeUpdate();
	}
	
	
	public List<VehicleInfo> searchTnumber(String ssite,String esite,String time) throws SQLException{
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
			String startTime = rs.getString("stime");
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			String systime = df.format(new Date());
			String sysdate = df1.format(new Date());
			int res=systime.compareTo(startTime);
			int res1=sysdate.compareTo(time);
			if(res1==0) {
				if(res>0||res==0) {
					continue;
				}
				else {			
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
			if(res1<0){
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
	

	public List<Integer> Tnum(int userid)throws Exception{
		String sql = "select tid from `order` where userid="+userid;
		ResultSet rs = null;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		List<Integer> list = new ArrayList<Integer>();
		int cnt = 0;
		while(rs.next()) {
			list.add(rs.getInt(1));
		}
		return list;
	}
	
}
