package bean;

public class Loans {
	//private vars
	private String loanID, uID, accID, status, appDate;
	private Double loanAmount, minPayment;
	private int	   loanPeriod;
	// getters & setters
	public String getLoanID() {
		return loanID;
	}
	public void setLoanID(String loanID) {
		this.loanID = loanID;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getAccID() {
		return accID;
	}
	public void setAccID(String accID) {
		this.accID = accID;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}	
	public Double getMinPayment() {
		return minPayment;
	}
	public void setMinPayment(Double minPayment) {
		this.minPayment = minPayment;
	}
	public int getLoanPeriod() {
		return loanPeriod;
	}
	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}	// end of getters & setters
}
