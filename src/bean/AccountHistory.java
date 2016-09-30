package bean;

public class AccountHistory {
	// private vars
	private String accID, uID, transDetails, transDate;
	private double withdraw, deposit;
	// getters & setters
	public String getAccID() {
		return accID;
	}
	public void setAccID(String accID) {
		this.accID = accID;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getTransDetails() {
		return transDetails;
	}
	public void setTransDetails(String transDetails) {
		this.transDetails = transDetails;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public double getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}	// end of getters & setters	
}
