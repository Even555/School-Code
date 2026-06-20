package domain;

public class User {
/*
 * create table user(
	uid varchar(30) not null auto_increment,
	username varchar(50) default null,
	password varchar(50) default null,
	realname varchar(50) default null,
	telephone varchar(20) default null,
	state int default null,
	code varchar(50) default null,
	viprole int default null
	primary key(uid)
)ENGINE=InnoDB default charset=utf-8;
 */
	private Integer userID;
	private String username;
	private String password;
	private String realname;
	private String telephone;
	private Integer sysrole;
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getSysrole() {
		return sysrole;
	}
	public void setSysrole(Integer viprole) {
		this.sysrole = viprole;
	}
}
