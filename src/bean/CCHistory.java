package bean;

public class CCHistory {
	// private vars
	private String ccNum, uID, transDetails, transDate;
	private double debit, credit;
	// getters & setters
	public String getCcNum() {
		return ccNum;
	}
	public void setCcNum(String ccNum) {
		this.ccNum = ccNum;
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
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}	// end of getters & setters
}
