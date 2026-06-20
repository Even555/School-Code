package domain;

import java.util.List;

/*
 * create table cart(userID int(11),pid varchar(32),num int
 */
public class Cart<T> {
	private int userID;
	private List<T> data;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
