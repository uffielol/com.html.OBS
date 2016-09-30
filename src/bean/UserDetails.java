package bean;

public class UserDetails {
	//private vars
	private String uID, password, name, address, email, telNo, lastLogin, secQuest, secAns;
	private int init;
	// getters & setters
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}	
	public int getInit() {
		return init;
	}
	public void setInit(int init) {
		this.init = init;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getSecQuest() {
		return secQuest;
	}
	public void setSecQuest(String secQuest) {
		this.secQuest = secQuest;
	}
	public String getSecAns() {
		return secAns;
	}
	public void setSecAns(String secAns) {
		this.secAns = secAns;
	}	// end of getters & setters
}
