package domain;
/*
 * create table orderitem(
 * oid varchar(32) not null,
 * pid varchar(32)not null,
 * num int,
 * itemsum int,
 * price int,
 * CONSTRAINT fk_005 FOREIGN KEY (pid) REFERENCES products(pid))
 */
public class OrderItem {
	private String oid;
	private String pid;//产品id
	private Integer num;//数量
	private Double xiaoji;//小计
	private Double price;//单价
	private String pimage;
	private String pname;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getXiaoji() {
		return xiaoji;
	}
	public void setXiaoji(Double xiaoji) {
		this.xiaoji = xiaoji;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
}
