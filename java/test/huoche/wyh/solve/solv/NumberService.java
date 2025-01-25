package test.huoche.wyh.solve.solv;

import java.sql.SQLException;
import java.util.List;

import test.huoche.wyh.solve.bean.Tnumber;
import test.huoche.wyh.solve.bean.VehicleInfo;
import test.huoche.wyh.solve.sqlwork.NumberDao;

public class NumberService {
	NumberDao numberDao = new NumberDao();
	
	public List<Tnumber> selectAll(int cnt1, int cnt2)throws Exception{
		return numberDao.selectAll(cnt1,cnt2);
	}
	public List<VehicleInfo> selectTnumberInfo(int tid, int cnt1, int cnt2)throws Exception{
		return numberDao.selectTnumberInfo(tid,cnt1,cnt2);
	}
	public int getCountAll()throws Exception{
		return numberDao.getCountAll();
	}
	public int getCountTnumber(int tid)throws Exception{
		return numberDao.getCountTnumber(tid);
	}
	public void deleteTnumber(int tid)throws Exception{
		numberDao.deleteTnumber(tid);
	}
	public VehicleInfo getTnumberInfo(int tid,int rid)throws Exception{
		return numberDao.getTnumberInfo(tid, rid);
	}
	public void perfectInformationUpdate(VehicleInfo vehicleInfo)throws Exception{
		numberDao.perfectInformationUpdate(vehicleInfo);
	}
	public void updateTnumber(Tnumber tnumber)throws Exception{	
		numberDao.updateTnumber(tnumber);
	}
	public Tnumber getTnumber(int tid)throws Exception{
		return numberDao.getTnumber(tid);
	}
	public void insertTnumber(Tnumber tnumber)throws Exception{	
		numberDao.insertTnumber(tnumber);
	}
	public void updateRouteStatusQy(int tid)throws Exception{
		numberDao.updateRouteStatusQy(tid);
	}
	public void updateRouteStatusZt(int tid)throws Exception{
		numberDao.updateRouteStatusZt(tid);
	}
	
	public List<VehicleInfo> searchTnumber(String ssite,String esite,String time) throws SQLException{
		return numberDao.searchTnumber(ssite, esite,time);
	}
	public List<Integer> Tnum(int userid)throws Exception{
		return numberDao.Tnum(userid);
	}
	public List<Tnumber> selectAll(int cnt1,int cnt2,String name)throws Exception{
		return numberDao.selectAll(cnt1, cnt2, name);
	}
	public int getCountAll(String name)throws Exception{
		return numberDao.getCountAll(name);
	}
}
