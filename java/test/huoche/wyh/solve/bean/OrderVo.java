package test.huoche.wyh.solve.bean;

public class OrderVo extends Order{
	//订票人姓名
	private String username;
	//身份证号
	private String cardid;
	//车次
	private String tnum;
	//起始站
	private String ssite;
	//终点站
	private String esite;
	//发车时间
	private String stime;
	
	private String typeC;
	
	
	
	
	public String getTypeC() {
		return typeC;
	}
	public void setTypeC(String typeC) {
		this.typeC = typeC;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public String getSsite() {
		return ssite;
	}
	public void setSsite(String ssite) {
		this.ssite = ssite;
	}
	public String getEsite() {
		return esite;
	}
	public void setEsite(String esite) {
		this.esite = esite;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	
	
}
