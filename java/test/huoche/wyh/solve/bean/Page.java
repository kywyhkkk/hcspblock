package test.huoche.wyh.solve.bean;

/**
 * 分页处理po�?
 * @author GXP
 *
 */
public class Page {
	
	//当前页是多少;
	private int pageNum;
	//每页显示的条�?;
	private int pageSize;
	 //总的记录条数。查询数据库得到的数�?;
	private int totalRecord;
	
	private int totalPage;//总页数，通过计算得到; 
	
	private int index;
	
	//�?始索引，也就是从第几行开始拿数据，有了startIndex和pageSize
	//就知道了limit的两个参数了
	/*private int startIndex;
	//通过计算得到，分页显示的页数�?
	private int start;
	
	private int end;*/
	//构�?�方法初始化对象;
	//private List<T> list;
	public Page(int pageNum,int pageSize,int totalRecord) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
	
		this.index = (pageNum-1)*pageSize;
		
		
		//totalPage计算总页�?
		if(totalRecord!=0) {
			if(totalRecord%pageSize==0) {
				//整除的情况下就不�?要进行多余的分页�?
				this.totalPage = totalRecord/pageSize;
			}else {
				//不整除的情况下多加一�?;
				this.totalPage = (totalRecord/pageSize)+1;
			}
		}else {
			this.totalPage = 1;
		}
		
	}
		/*//计算�?始的索引;
		this.startIndex = (pageNum-1)*pageSize;
		
		this.start = 1;
		this.end = 6;
		
		
		//显示页数的算�?
		if(totalPage<=6) {
			this.end = this.totalPage;
		}else {
			//总页数大�?6
			this.start = pageNum-2;
			this.end = pageNum+2;
			
			if(start<0) {
				//排除为第�?页和第二页的情况
				this.start = 1;
				this.end = 6;
			}
			if(end > this.totalPage) {
				this.end = totalPage;
				this.start = end - 6;
			}
		}
	}*/
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public int getPageNum() {
		return pageNum;
	}
	

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/*public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}*/
	/*public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}*/
	
	
}
