package domain;

import java.util.Date;

/*
 * create table products (
	pid varchar(32) not null,
	pname varchar(50)default null,
	pimage varchar(200)default null,
	price double default null,
	vip_price double default null,
	pdate date default null,
	salenum int default null,
	is_hot int default null,
	pdesc varchar(255)default null,
	pflag int default null,
	cid varchar(32)default null,
	primary key(pid),
	CONSTRAINT fk_001 FOREIGN KEY (cid) REFERENCES category(cid)
	)engine=innodb default charset=utf8;
 */
public class Product {
	private String pid;
	private String pname;
	private String pimage;
	private Double price;
	private Double vip_price;
	private Date pdate;
	private String salenum;
	private Integer is_hot;//Integer默认值是null  int默认值是0
	private String pdesc;
	private Integer pflag;
	private String cid;
	//为了购物车增加的属性
	private Integer num;
	//在多的一方放入多的一方的对象 用来表示属于哪个分类
	
	private String cname;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getVip_price() {
		return vip_price;
	}
	public void setVip_price(Double vip_price) {
		this.vip_price = vip_price;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public String getSalenum() {
		return salenum;
	}
	public void setSalenum(String salenum) {
		this.salenum = salenum;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Integer getPflag() {
		return pflag;
	}
	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
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
	
}
