package domain;

import java.util.Date;
import java.util.List;

public class Order {
	private String oid;
	private Integer userID;
	private Double sumprice;//订单总价
	private String ordertime;
	private String address;
	private Integer isvip;//是否优惠购买
	private Integer orderstate;//订单状态 
	//0未付款 1已付款 2已发货 3已完成
	private List<OrderItem> list;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Double getSumprice() {
		return sumprice;
	}
	public void setSumprice(Double sumprice) {
		this.sumprice = sumprice;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String dateString) {
		this.ordertime = dateString;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsvip() {
		return isvip;
	}
	public void setIsvip(Integer isvip) {
		this.isvip = isvip;
	}
	public Integer getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(Integer orderstate) {
		this.orderstate = orderstate;
	}
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	
}
