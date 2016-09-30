package bean;

public class CreditCards {
	// private vars
	private String uID, ccNum;
	private double ccLimit, ccToPoints, ccAvailLimit;
	private int	   ccPoints;
	// getters and setters
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getCcNum() {
		return ccNum;
	}
	public void setCcNum(String ccNum) {
		this.ccNum = ccNum;
	}
	public double getCcLimit() {
		return ccLimit;
	}
	public void setCcLimit(double ccLimit) {
		this.ccLimit = ccLimit;
	}
	public double getCcToPoints() {
		return ccToPoints;
	}
	public void setCcToPoints(double ccToPoints) {
		this.ccToPoints = ccToPoints;
	}
	public int getCcPoints() {
		return ccPoints;
	}
	public void setCcPoints(int ccPoints) {
		this.ccPoints = ccPoints;
	}	
	public double getCcAvailLimit() {
		return ccAvailLimit;
	}
	public void setCcAvailLimit(double ccAvailLimit) {
		this.ccAvailLimit = ccAvailLimit;
	}	// end of getters & setters
}
