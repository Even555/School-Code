package domain;

import java.util.Date;

public class Reply {
	private Integer comid;
	private Integer userID;
	private String content;
	private Date time;
	private Integer isAdmin;
	public Integer getComid() {
		return comid;
	}
	public void setComid(Integer comid) {
		this.comid = comid;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
