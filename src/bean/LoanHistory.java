package bean;

public class LoanHistory {
	// private vars
	private String loanID, uID, transDetails, transDate;
	private double repayment;
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
	public double getRepayment() {
		return repayment;
	}
	public void setRepayment(double repayment) {
		this.repayment = repayment;
	}	// end of getters & setters
}
