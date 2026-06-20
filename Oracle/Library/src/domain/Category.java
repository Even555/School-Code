package domain;

import java.util.List;

public class Category {
	private String cid;
	private String cname;
	private List<Product> list;
	private int psize=0;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public int getPsize() {
		return psize;
	}
	public void setPsize(int psize) {
		this.psize = psize;
	}
}
