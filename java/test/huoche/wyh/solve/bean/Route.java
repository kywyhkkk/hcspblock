package test.huoche.wyh.solve.bean;

public class Route {
    private Integer rid;

    private Integer tid;

    private String ssite;

    private String esite;

    private String stime;

    private String etime;

    private Integer rmileage;
    
    private Integer stoptime;
    

    public Integer getStoptime() {
		return stoptime;
	}

	public void setStoptime(Integer stoptime) {
		this.stoptime = stoptime;
	}

	public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getSsite() {
        return ssite;
    }

    public void setSsite(String ssite) {
        this.ssite = ssite == null ? null : ssite.trim();
    }

    public String getEsite() {
        return esite;
    }

    public void setEsite(String esite) {
        this.esite = esite == null ? null : esite.trim();
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime == null ? null : stime.trim();
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime == null ? null : etime.trim();
    }

    public Integer getRmileage() {
        return rmileage;
    }

    public void setRmileage(Integer rmileage) {
        this.rmileage = rmileage;
    }
}