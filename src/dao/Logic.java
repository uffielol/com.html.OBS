package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class Logic {
	
	////////////////////
	// Charles' Logic //
	////////////////////
	
	// method to INSERT new customer into user_details TABLE
	public static void newCustomer(bean.UserDetails u) {
		// vars
		Connection c	  = null;	
		String 	   newUsr = "INSERT INTO user_details VALUES (?, ?, ?, ?, ?, ?, ?)";
		// prepare INSERT statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			PreparedStatement pt = c.prepareStatement(newUsr);
			pt.setString(1, u.getuID());
			pt.setString(2, genPass(u.getName()));
			pt.setInt(3, 1);
			pt.setString(4, u.getName());
			pt.setString(5, u.getAddress());
			pt.setString(6, u.getEmail());
			pt.setString(7, u.getTelNo());
			// execute INSERT statement
			pt.executeUpdate();
			pt.close();			
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}	
	}	// end of newCustomer()

	// method to INSERT new account into accounts TABLE
	public static void newAcc(String uID, String type) {
		// vars
		Connection c	  = null;	
		String 	   newAcc = "INSERT INTO accounts VALUES (?, ?, ?)";
		// prepare INSERT statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			PreparedStatement pt = c.prepareStatement(newAcc);
			pt.setString(1, autoGenAccID(type));
			pt.setString(2, uID);		
			pt.setDouble(3, 0.00);
			// execute INSERT statement
			pt.executeUpdate();
			pt.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}	
	}	// end of newAcc()
	
	// method to INSERT new account into accounts TABLE
	public static String newAccReturn(String uID, String type, double amount) {
		// vars
		Connection c	  = null;	
		String 	   newAcc = "INSERT INTO accounts VALUES (?, ?, ?)";
		String 	   accID  = autoGenAccID(type);
		// prepare INSERT statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			PreparedStatement pt = c.prepareStatement(newAcc);
			pt.setString(1, accID);
			pt.setString(2, uID);		
			pt.setDouble(3, amount);
			// execute INSERT statement
			pt.executeUpdate();
			pt.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		return accID;	
	}	// end of newAccReturn()
	
	// method to INSERT new credit card into credit_cards TABLE
	public static void newCC(String uID, String ccLimit3, String type) {
		// vars
		Connection c	    = null;	
		String	   firstTwo = null;
		String 	   newCC    = "INSERT INTO credit_cards VALUES (?, ?, ?, ?, ?, ?)";
		// determine first 2 digits
		if (type.equals("black")) {
			firstTwo = "45";
		} else if (type.equals("altitude")) {
			firstTwo = "54";
		} else if (type.equals("everyday")) {
			firstTwo = "53";
		} else if (type.equals("safra")) {
			firstTwo = "51";
		} 
		// prepare INSERT statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			PreparedStatement pt = c.prepareStatement(newCC);
			pt.setString(1, uID);
			pt.setString(2, autoGenCCNum(firstTwo));
			pt.setString(3, ccLimit3);
			pt.setString(4, ccLimit3);
			pt.setDouble(5, 0.00);
			pt.setInt(6, 0);
			// execute INSERT statement
			pt.executeUpdate();
			pt.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}		
	}	// end of newCC()
	
	// method to INSERT new credit card into credit_cards TABLE
	public static String newCCReturn(String uID, String ccLimit3, String type) {
		// vars
		Connection c	    = null;	
		String	   firstTwo = null;
		String 	   newCC    = "INSERT INTO credit_cards VALUES (?, ?, ?, ?, ?, ?)";
		// determine first 2 digits
		if (type.equals("black")) {
			firstTwo = "45";
		} else if (type.equals("altitude")) {
			firstTwo = "54";
		} else if (type.equals("everyday")) {
			firstTwo = "53";
		} else if (type.equals("safra")) {
			firstTwo = "51";
		} 
		String ccNum = autoGenCCNum(firstTwo);
		// prepare INSERT statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			PreparedStatement pt = c.prepareStatement(newCC);
			pt.setString(1, uID);
			pt.setString(2, ccNum);
			pt.setString(3, ccLimit3);
			pt.setString(4, ccLimit3);
			pt.setDouble(5, 0.00);
			pt.setInt(6, 0);
			// execute INSERT statement
			pt.executeUpdate();
			pt.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		return ccNum;		
	}	// end of newCCReturn()
	
	// method to INSERT new loan into loans TABLE
	public static void newLoan(String uID, String accID, String loanAmt, String period, String type, String interest) {
		// vars
		Connection c	  = null;	
		String 	   newCC = "INSERT INTO loans (loanID, uID, accID, loanAmount, minPayment, loanPeriod, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
		// calculate stuff
		Double loanAmount = Double.parseDouble(loanAmt) * (1 + ((Double.parseDouble(interest) / 100) * (Double.parseDouble(period) / 12)));
		Double minPay     = loanAmount / Double.parseDouble(period);
		// prepare INSERT statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			PreparedStatement pt = c.prepareStatement(newCC);
			pt.setString(1, autoGenLoanID(type));
			pt.setString(2, uID);
			pt.setString(3, accID);
			pt.setDouble(4, loanAmount);
			pt.setDouble(5, minPay);
			pt.setInt(6, Integer.parseInt(period));
			pt.setString(7, "Pending");
			// execute INSERT statement
			pt.executeUpdate();
			pt.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}	
	}	// end of newLoan()	
	
	public static void newApp(String uID, String type, String notes) {
		// vars
		Connection c	  = null;	
		String 	   newApp = "INSERT INTO applications (uID, type, status, notes) VALUES (?, ?, ?, ?)";
		// prepare INSERT statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			PreparedStatement pt = c.prepareStatement(newApp);
			pt.setString(1, uID);
			pt.setString(2, type);
			pt.setString(3, "Pending");
			pt.setString(4, notes);
			// execute INSERT statement
			pt.executeUpdate();
			pt.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	// method to SELECT all customers from user_details TABLE + store into ArrayList<UserDetails> 
	public static List<bean.UserDetails> getUsers() {
		// vars
		Connection c = null;
		List<bean.UserDetails> usrList  = new ArrayList<>();
		String select = "SELECT * FROM user_details";
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an UserDetails object
			while(rs.next()) {
				// create new UserDetails object and populate vars
				bean.UserDetails u = new bean.UserDetails();
				u.setuID(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setInit(rs.getInt(3));
				u.setName(rs.getString(4));
				u.setAddress(rs.getString(5));
			    u.setEmail(rs.getString(6));
			    u.setTelNo(rs.getString(7));
			    u.setLastLogin(rs.getString(8));
			    u.setSecQuest(rs.getString(9));
			    u.setSecAns(rs.getString(10));
				// store populated object into ArrayList<UserDetails>
			    usrList.add(u);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// remove admin from the ArrayList<> 
//		usrList.remove(0);
//		usrList.remove(0);
		// return the UserDetails ArrayList<> 
		return usrList;		
	}	//end of getUsers()
	
	// method to SELECT all accounts from accounts TABLE + store into ArrayList<Accounts> 
	public static List<bean.Accounts> getAccounts() {
		// vars
		Connection c = null;
		List<bean.Accounts> accList  = new ArrayList<>();
		String select = "SELECT *  FROM accounts";
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an Accounts object
			while(rs.next()) {
				// create new Accounts object and populate vars
				bean.Accounts a = new bean.Accounts();
				a.setAccID(rs.getString(1));
				a.setuID(rs.getString(2));
				a.setBalance(rs.getDouble(3));
				// store populated object into ArrayList<Accounts>
			    accList.add(a);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the Accounts ArrayList<> 
		return accList;		
	}	//end of getAccounts()	
	
	// method to SELECT all applications from the applications TABLE WHERE (type)
	public static List<bean.Applications> getApps(String where) {
		// vars
		Connection c = null;
		List<bean.Applications> appList  = new ArrayList<>();
		String select = "SELECT * FROM applications "+where;
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an Applications object
			while(rs.next()) {
				// create new Loans object and populate vars
				bean.Applications app = new bean.Applications();
				app.setAppID(rs.getInt(1));
				app.setuID(rs.getString(2));
				app.setType(rs.getString(3));
				app.setStatus(rs.getString(4));
				app.setNotes(rs.getString(5));
				app.setAppDate(rs.getString(6));
				// store populated object into ArrayList<Applications>
				appList.add(app);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the Applications ArrayList<> 
		return appList;	
	}	// end of getApps()
	
	// method to SELECT all loan applications from loans TABLE + store into ArrayList<Loans> 
	public static List<bean.Loans> getLoanApps(String where) {
		// vars
		Connection c = null;
		List<bean.Loans> loanList  = new ArrayList<>();
		String select = "SELECT * FROM loans "+where;
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an Loans object
			while(rs.next()) {
				// create new Loans object and populate vars
				bean.Loans loan = new bean.Loans();
				loan.setLoanID(rs.getString(1));
				loan.setuID(rs.getString(2));
				loan.setAccID(rs.getString(3));
				loan.setLoanAmount(rs.getDouble(4));
				loan.setMinPayment(rs.getDouble(5));
				loan.setLoanPeriod(rs.getInt(6));
				loan.setStatus(rs.getString(7));
				loan.setAppDate(rs.getString(8));
				// store populated object into ArrayList<Loans>
				loanList.add(loan);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the Loans ArrayList<> 
		return loanList;	
	}	// end of getLoanApps()

	// method to SELECT all credit cards from credit_cards TABLE + store into ArrayList<CreditCards> 
	public static List<bean.CreditCards> getCC() {
		// vars
		Connection c = null;
		List<bean.CreditCards> ccList  = new ArrayList<>();
		String select = "SELECT *  FROM credit_cards";
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an CreditCards object
			while(rs.next()) {
				// create new CreditCards object and populate vars
				bean.CreditCards cc = new bean.CreditCards();
				cc.setuID(rs.getString(1));
				cc.setCcNum(rs.getString(2));
				cc.setCcLimit(rs.getDouble(3));
				cc.setCcAvailLimit(rs.getDouble(4));
				cc.setCcToPoints(rs.getDouble(5));
				cc.setCcPoints(rs.getInt(6));
				// store populated object into ArrayList<CreditCards>
			    ccList.add(cc);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the CreditCards ArrayList<> 
		return ccList;		
	}	//end of getCC()
	
	// method to SELECT a single customer from user_details TABLE using uID 
	public static bean.UserDetails getUser(String uID) {
		// vars
		Connection c = null;
		bean.UserDetails u = new bean.UserDetails();
		String select = "SELECT * FROM user_details WHERE uID='"+uID+"'";
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an UserDetails object
			while(rs.next()) {				
				u.setuID(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setInit(rs.getInt(3));
				u.setName(rs.getString(4));
				u.setAddress(rs.getString(5));
			    u.setEmail(rs.getString(6));
			    u.setTelNo(rs.getString(7));
			    u.setLastLogin(rs.getString(8));
			    u.setSecQuest(rs.getString(9));
			    u.setSecAns(rs.getString(10));
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		return u;		
	}	//end of getUser(String)
	
	// method to SELECT all accounts of a single customer from the accounts TABLE using uID 
	public static List<bean.Accounts> getAccs(String uID) {
		// vars
		Connection c = null;
		List<bean.Accounts> accList  = new ArrayList<>();
		String select = "SELECT *  FROM accounts WHERE uID='"+uID+"'";
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an Accounts object
			while(rs.next()) {
				// create new Accounts object and populate vars
				bean.Accounts acc = new bean.Accounts();
				acc.setAccID(rs.getString(1));
				acc.setuID(rs.getString(2));
				acc.setBalance(rs.getDouble(3));
				// store populated object into ArrayList<Accounts>
				accList.add(acc);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the Accounts ArrayList<> 
		return accList;			
	}	//end of getAccs()
	
	// method to SELECT an account from the accounts TABLE using accID 
	public static bean.Accounts getAcc(String accID) {
		// vars
		Connection c = null;
		bean.Accounts acc = new bean.Accounts();
		String select = "SELECT * FROM accounts WHERE accID='"+accID+"'";
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an account object
			while(rs.next()) {
				acc.setAccID(rs.getString(1));
				acc.setuID(rs.getString(2));
				acc.setBalance(rs.getDouble(3));
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the Accounts ArrayList<> 
		return acc;			
	}	//end of getAcc()
	
	// method to SELECT all credit cards of a single customer from the credit_cards TABLE using uID 
	public static List<bean.CreditCards> getCCs(String uID) {
		// vars
		Connection c = null;
		List<bean.CreditCards> ccList  = new ArrayList<>();
		String select = "SELECT *  FROM credit_cards WHERE uID='"+uID+"'";
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an CreditCards object
			while(rs.next()) {
				// create new CreditCards object and populate vars
				bean.CreditCards cc = new bean.CreditCards();
				cc.setuID(rs.getString(1));
				cc.setCcNum(rs.getString(2));
				cc.setCcLimit(rs.getDouble(3));
				cc.setCcAvailLimit(rs.getDouble(4));
				cc.setCcToPoints(rs.getDouble(5));
				cc.setCcPoints(rs.getInt(6));
				// store populated object into ArrayList<CreditCards>
			    ccList.add(cc);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the CreditCards ArrayList<> 
		return ccList;			
	}	//end of getCCs()
	
	// method to SELECT all customers from user_details TABLE that are locked out
	public static List<bean.UserDetails> getLockedUsers() {
		// vars
		Connection c = null;
		List<bean.UserDetails> usrList  = new ArrayList<>();
		String select = "SELECT * FROM user_details WHERE init >= 3";
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an UserDetails object
			while(rs.next()) {	
				bean.UserDetails u = new bean.UserDetails();
				u.setuID(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setInit(rs.getInt(3));
				u.setName(rs.getString(4));
				u.setAddress(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setTelNo(rs.getString(7));
				usrList.add(u);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		return usrList;		
	}	//end of getLockedUsers()
	
	// method to SELECT all account history from account_history TABLE
	public static List<bean.AccountHistory> getAccHistory(String where) {
		// vars
		Connection c = null;
		List<bean.AccountHistory> appList  = new ArrayList<>();
		String select = "SELECT * FROM account_history "+where;
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an AccountHistory object
			while(rs.next()) {
				// create new Loans object and populate vars
				bean.AccountHistory appHist = new bean.AccountHistory();
				appHist.setAccID(rs.getString(1));
				appHist.setuID(rs.getString(2));
				appHist.setWithdraw(rs.getDouble(3));
				appHist.setDeposit(rs.getDouble(4));
				appHist.setTransDetails(rs.getString(5));
				appHist.setTransDate(rs.getString(6));
				// store populated object into ArrayList<AccountHistory>
				appList.add(appHist);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the AccountHistory ArrayList<> 
		return appList;	
	}	// end of getAccHistory()

	// method to SELECT all credit card history from cc_history TABLE
	public static List<bean.CCHistory> getCCHistory(String where) {
		// vars
		Connection c = null;
		List<bean.CCHistory> ccList  = new ArrayList<>();
		String select = "SELECT * FROM cc_history "+where;
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an CCHistory object
			while(rs.next()) {
				// create new Loans object and populate vars
				bean.CCHistory ccHist = new bean.CCHistory();
				ccHist.setCcNum(rs.getString(1));
				ccHist.setuID(rs.getString(2));
				ccHist.setDebit(rs.getDouble(3));
				ccHist.setCredit(rs.getDouble(4));
				ccHist.setTransDetails(rs.getString(5));
				ccHist.setTransDate(rs.getString(6));
				// store populated object into ArrayList<CCHistory>
				ccList.add(ccHist);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the CCHistory ArrayList<>
		return ccList;	
	}	// end of getCCHistory()

	// method to SELECT all loan history from loan_history TABLE
	public static List<bean.LoanHistory> getLoanHistory(String where) {
		// vars
		Connection c = null;
		List<bean.LoanHistory> loanList  = new ArrayList<>();
		String select = "SELECT * FROM loan_history "+where;
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an LoanHistory object
			while(rs.next()) {
				// create new Loans object and populate vars
				bean.LoanHistory loanHist = new bean.LoanHistory();
				loanHist.setLoanID(rs.getString(1));
				loanHist.setuID(rs.getString(2));
				loanHist.setTransDetails(rs.getString(3));
				loanHist.setRepayment(rs.getDouble(4));
				loanHist.setTransDate(rs.getString(5));
				// store populated object into ArrayList<LoanHistory>
				loanList.add(loanHist);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// return the LoanHistory ArrayList<>
		return loanList;	
	}	// end of getLoanHistory()
	
	// method to UPDATE customer details in the user_details TABLE
	public static void updateUser(bean.UserDetails u) {
		// vars
		Connection 	c	   = null;
		String 		update = "UPDATE user_details SET pass = ?, init = ?, name = ?, address = ?, email = ?, telNo = ? WHERE uID = '"+u.getuID()+"'";
		// prepare UPDATE statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			PreparedStatement pt = c.prepareStatement(update);
			pt.setString(1, u.getPassword());
			pt.setInt(2, u.getInit());
			pt.setString(3, u.getName());
			pt.setString(4, u.getAddress());
			pt.setString(5, u.getEmail());
			pt.setString(6, u.getTelNo());
			// execute UPDATE statement
			pt.executeUpdate();			
			pt.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}	// end of updateUser()
	
	// EDIT THIS
	// method to UPDATE credit card details in the credit_cards TABLE using credit card number
	public static void updateCC(bean.CreditCards cc) {
		// vars
		Connection 	c	   		 = null;
		String 		update = "UPDATE credit_cards SET ccLimit = ?, ccPoints = ? WHERE ccNum = '"+cc.getCcNum()+"'";
		// prepare UPDATE statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			PreparedStatement pt = c.prepareStatement(update);
			pt.setDouble(1, cc.getCcLimit());
			pt.setInt(2, cc.getCcPoints());
			// execute UPDATE statement
			pt.executeUpdate();			
			pt.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}	// end of updateCC()
	
	// method to UPDATE customer lock status in the user_details TABLE
	public static void updateLock(String uID) {
		// vars
		Connection 	c	   		 = null;
		String 		update = "UPDATE user_details SET init = 1 WHERE uID = '"+uID+"'";
		// prepare UPDATE statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement();
			// execute UPDATE statement
			st.executeUpdate(update);			
			st.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}	// end of updateLock()
	
	// method to DELETE customer from DB using uID
	public static void deleteUser(String uID) {
		// vars
		Connection 	c	   = null;
		String 		delete = "DELETE FROM user_details WHERE uID = '"+uID+"'";
		// prepare UPDATE statement + prepare Connection  
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement();
			// execute UPDATE statement
			st.executeUpdate(delete);			
			st.close();		
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}	// end of deleteUser
	
	// method to generate usrID
	public static String autoGenUsrID() {
		// get all UserDetails and store into an ArrayList<bean.UserDetails>
		List<bean.UserDetails> usrList = getUsers();
		// get the last object of the ArrayList<bean.UserDetails>
		bean.UserDetails getLast = usrList.get(usrList.size()-1);
		// get the uID of the LAST object in the ArrayList<bean.UserDetails>
		String lastUID = getLast.getuID();
		// break the uID retrieved into usr-00000
		String numberHalf = lastUID.substring(3, lastUID.length());
		// cast numberHalf to Integer
		int currentCount = Integer.parseInt(numberHalf);
		// increment currentCount by 1
		int newCount = currentCount + 1;
		// create newNumHalf String
		String newNumHalf = String.valueOf(newCount);
		// add zeros if needed to the newNumHalf
		if (newNumHalf.length() < 5) {           
			int numToAdd = 5 - newNumHalf.length();
	        if (numToAdd == 4) {
	        	newNumHalf = "0000"+newNumHalf;
	        } else if (numToAdd == 3) {
	        	newNumHalf = "000"+newNumHalf;
	        } else if (numToAdd == 2) {
	        	newNumHalf = "00"+newNumHalf;
	        } else if (numToAdd == 1) {
	        	newNumHalf = "0"+newNumHalf;
	        } 
		}
		// create newUID
		String newUID = "usr"+newNumHalf;
		// return newUID
		return newUID;	
	}	// end of autoGenUsrID()	
	
	// method to generate accID
	private static String autoGenAccID(String type) {
		// vars
		Connection c  = null;
		String select = "SELECT accID FROM accounts WHERE accID LIKE '"+type+"%' ORDER BY accID DESC LIMIT 1";
		String lastAccID = null;
		String newAccID  = null;
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an UserDetails object
			while(rs.next()) {
				lastAccID = rs.getString(1);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
//		// if empty
//		if (lastAccID.trim().equals("") || lastAccID.trim() == null) {
//			newAccID = type+"00001";
//		} else {
			// break the accID retrieved into xxx-00000
			String numberHalf = lastAccID.substring(3, lastAccID.length());
			// cast numberHalf to Integer
			int currentCount = Integer.parseInt(numberHalf);
			// increment currentCount by 1
			int newCount = currentCount + 1;
			// create newNumHalf String
			String newNumHalf = String.valueOf(newCount);
			// add zeros if needed to the newNumHalf
			if (newNumHalf.length() < 5) {           
				int numToAdd = 5 - newNumHalf.length();
		        if (numToAdd == 4) {
		        	newNumHalf = "0000"+newNumHalf;
		        } else if (numToAdd == 3) {
		        	newNumHalf = "000"+newNumHalf;
		        } else if (numToAdd == 2) {
		        	newNumHalf = "00"+newNumHalf;
		        } else if (numToAdd == 1) {
		        	newNumHalf = "0"+newNumHalf;
		        } 
			}
			// create newAccID
			newAccID = type+newNumHalf;
//		}
		// return newAccID
		return newAccID;	
	}	// end of autoGenAccID()
	
	// method to generate credit card numbers
	private static String autoGenCCNum(String firstTwo) {
		// firstHalf
		String conc = firstTwo+"00000000000000";
		long firstHalf = Long.parseLong(conc);
		// return var
		String newCCNum = null;
		// get all CreditCards and store into an ArrayList<CreditCards>
		List<bean.CreditCards> ccList = getCC();
		// generate random 14 digits
		long lastHalf = (long) (Math.random() * 100000000000000L);
		// add 52 to the start of 14 digit number
		long newNum = firstHalf + lastHalf;
		// if ccList empty
		if (ccList.isEmpty()) {
			newCCNum = newNum+"";
		} else {
			for (bean.CreditCards cc : ccList) {
				while (Long.parseLong(cc.getCcNum()) == newNum) {
					lastHalf = (long) (Math.random() * 100000000000000L);
					newNum = firstHalf + lastHalf;
				}
			}
			newCCNum = newNum+"";
		}
		return newCCNum;
	}	// end of autoGenCCNum()
	
	// method to generate password from First+Last Name
	private static String genPass(String name) {
		// split name
		String a = null;
		String b = null;
		StringTokenizer st = new StringTokenizer(name, " ");
		if (st.countTokens() <= 2) {
			a = st.nextToken();
			b = st.nextToken();
		}			
		// get first letter of First Name
		String c = a.substring(0, 1);
		// get last letter of First Name
		String d = a.substring(a.length()-1, a.length());
		// get everything between first and last of First Name
		String f = a.substring(1, a.length()-1);
		// get first letter of Last Name
		String g = b.substring(0, 1);	
		// generate random 2 digit number
		Random randomGenerator = new Random();
		   int randomInt = randomGenerator.nextInt(100);
		// swap first and last letter of First Name + first letter of Last Name + 2 random digits
		String h = d+f+c+g+randomInt;
		// return generated pass 
		return h;
	}	// end of genPass()
	
	// method to generate loanIDs
	public static String autoGenLoanID(String type) {
		// vars
		Connection c  = null;
		String select = "SELECT loanID FROM loans WHERE loanID LIKE '"+type+"%' ORDER BY loanID DESC LIMIT 1";
		String lastLoanID = null;
		String newLoanID  = null;
		// SELECT statement + prepare Connection 
		try {
			c = dbconnection.MyConnection.prepareConnection();
			Statement st = c.createStatement(); 
			// execute SELECT statement + pass return into a ResultSet  
			ResultSet rs = st.executeQuery(select);  
			// iterate through the ResultSet and store into an UserDetails object
			while(rs.next()) {
				lastLoanID = rs.getString(1);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		// if empty
//		if (lastLoanID.isEmpty()) {
//			newLoanID = type+"00001";
//		} else {
			// break the lastLoanID retrieved into xxxx-00000
			String numberHalf = lastLoanID.substring(4, lastLoanID.length());
			// cast numberHalf to Integer
			int currentCount = Integer.parseInt(numberHalf);
			// increment currentCount by 1
			int newCount = currentCount + 1;
			// create newNumHalf String
			String newNumHalf = String.valueOf(newCount);
			// add zeros if needed to the newNumHalf
			if (newNumHalf.length() < 5) {           
				int numToAdd = 5 - newNumHalf.length();
				if (numToAdd == 4) {
					newNumHalf = "0000"+newNumHalf;
				} else if (numToAdd == 3) {
					newNumHalf = "000"+newNumHalf;
				} else if (numToAdd == 2) {
					newNumHalf = "00"+newNumHalf;
				} else if (numToAdd == 1) {
					newNumHalf = "0"+newNumHalf;
				} 
			}
			// create newAccID
			newLoanID = type+newNumHalf;
//		}	
		// return newAccID
		return newLoanID;
	}	// end of autoGenLoanID()
	
	// method to approve loans UPDATE loans, accounts INSERT loan_history, account_history TABLES
	public static void approveLoan(String loanID, String accID) {
		// vars
		Connection c = null;
		double interest = 0.00;
		if (loanID.contains("carr")) {
			interest = 3.875;
		} else if (loanID.contains("prop")) {
			interest = 3.255;
		} else if (loanID.contains("pers")) {
			interest = 3.00;
		} else if (loanID.contains("comm")) {
			interest = 5.155;
		} else if (loanID.contains("cash")) {
			interest = 4.234;
		}
		List<bean.Loans> listLoans = getLoanApps("WHERE loanID='"+loanID+"'");
		bean.Loans loan = null;
		for (bean.Loans l : listLoans) {
			loan = l;
		}
		bean.Accounts acc = getAcc(accID);
		try {
			// prepare UPDATE statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute UPDATE1 
			Statement st = c.createStatement();
			st.executeUpdate("UPDATE loans SET status = 'Approved' WHERE loanID = '"+loanID+"'");				
			// close connection
			st.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}	
		// calc new balance
		double r = interest / 100;
		int period = loan.getLoanPeriod();
		double t = (double)period/12;
		double principal = loan.getLoanAmount() / (1 + (r * t));
		int prin = (int) principal;
		double entry  = prin*0.97455;
		double newBal = acc.getBalance() + entry;		
		try {
			// prepare UPDATE statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute UPDATE1 
			Statement st = c.createStatement();
			// execute UPDATE2 
			st.executeUpdate("UPDATE accounts SET balance = '"+newBal+"' WHERE accID = '"+accID+"'");
			// close connection
			st.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}			
		try {
			// prepare UPDATE statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute INSERT1
			PreparedStatement pt = c.prepareStatement("INSERT INTO account_history (accID, uID, deposit, transDetails) VALUES (?, ?, ?, ?)");
			pt.setString(1, accID);
			pt.setString(2, loan.getuID());
			pt.setDouble(3, entry);
			pt.setString(4, "LOAN DEBIT - "+loanID);
			pt.executeUpdate();	
			// close connection
			pt.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}					
		try {
			// prepare UPDATE statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute INSERT1
			PreparedStatement pt = c.prepareStatement("INSERT INTO loan_history (loanID, uID, transDetails) VALUES (?, ?, ?)");
			pt.setString(1, loanID);
			pt.setString(2, loan.getuID());
			pt.setString(3, "LOAN APPROVED - "+loanID+" - $"+entry);
			pt.executeUpdate();
			// close connection
			pt.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}			
	}	// end of approveLoan()
	
	// method to deny loans UPDATE loans TABLE
	public static void denyLoan(String loanID) {
		// vars
		Connection c = null;
		try {
			// prepare UPDATE statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute UPDATE1 
			Statement st = c.createStatement();
			st.executeUpdate("UPDATE loans SET status = 'Denied' WHERE loanID = '"+loanID+"'");				
			// close connection
			st.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}		
	}	// end of denyLoan()
	
	// method to deny applications UPDATE applications TABLE
	public static void denyApp(String appID) {
		// vars
		Connection c = null;
		try {
			// prepare UPDATE statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute UPDATE1 
			Statement st = c.createStatement();
			st.executeUpdate("UPDATE applications SET status = 'Denied' WHERE appID = '"+appID+"'");				
			// close connection
			st.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}			
	}	// end of denyApp()
	
	// method to approve CCs UPDATE applications & INSERT cc_history TABLE
	public static void approveCC(String appID, String uID, String type) {
		// vars
		Connection c = null;
		try {
			// prepare UPDATE statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute UPDATE
			Statement st = c.createStatement();
			st.executeUpdate("UPDATE applications SET status = 'Approved' WHERE appID = '"+appID+"'");				
			// close connection
			st.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}	
		// get limit from notes
		List<bean.Applications> list = getApps("WHERE appID = '"+appID+"'");
		String notes = null;
		for (bean.Applications a : list) {
			notes = a.getNotes();
		}
		String ccLimit = notes.substring(7, notes.length());
		String ccNum = newCCReturn(uID, ccLimit, type);
		try {
			// prepare INSERT statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute UPDATE
			Statement st = c.createStatement();
			st.executeUpdate("INSERT INTO cc_history (ccNum, uID, transDetails) VALUES ("+ccNum+", '"+uID+"', 'CREDIT CARD APPROVED')");				
			// close connection
			st.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}	// end of approveCC()

	// method to approve accounts UPDATE applications & INSERT account_history TABLE
	public static void approveAcc(String appID, String uID, String type) {
		// vars
		Connection c = null;
		try {
			// prepare UPDATE statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute UPDATE
			Statement st = c.createStatement();
			st.executeUpdate("UPDATE applications SET status = 'Approved' WHERE appID = '"+appID+"'");				
			// close connection
			st.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}	
		// get promo from notes
		List<bean.Applications> list = getApps("WHERE appID = '"+appID+"'");
		String notes = null;
		for (bean.Applications a : list) {
			notes = a.getNotes();
		}
		// calc deposit amt
		String promo = notes.substring(6, notes.length());
		double depos = 0.00;
		if (!promo.trim().isEmpty() && promo.trim() != null) {
			depos = Double.parseDouble(promo);
		} 
		// create new account
		String accID = newAccReturn(uID, type, depos);
		try {
			// prepare INSERT statement + prepare Connection
			c = dbconnection.MyConnection.prepareConnection();				
			// execute UPDATE
			Statement st = c.createStatement();
			st.executeUpdate("INSERT INTO account_history (accID, uID, deposit, transDetails) VALUES ('"+accID+"', '"+uID+"', "+depos+",'ACCOUNT CREATED')");				
			// close connection
			st.close();		;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}		
	}	// end of approveAcc()

	/////////////////////
	// Wei Yangs Logic //
	/////////////////////
	
	public int authenticate(String inputID, String inputpass) throws ClassNotFoundException, SQLException {
		int init = 0;
		int flag = 3;
		String dbpass = null;
		try{
		Connection conn = dbconnection.MyConnection.prepareConnection();
		String getinit = "SELECT init FROM user_details WHERE uID = (?)";
		PreparedStatement pst1= conn.prepareStatement(getinit);
		pst1.setString(1,inputID);
		ResultSet rs = pst1.executeQuery();
		while(rs.next()){
			  init =  rs.getInt(1);
		}
		
		if(init==0){//check admin login details
			String getpass = "SELECT pass FROM user_details WHERE uID = (?)";
			PreparedStatement pst2= conn.prepareStatement(getpass);
			pst2.setString(1,inputID);
			ResultSet rs2 = pst2.executeQuery();
			while(rs2.next()){
				   dbpass =  rs2.getString(1);
		}
			  if(dbpass.equals(inputpass)){
					flag=1;
					}
			  
			  else{	  
				  flag=3;
			  }
			
		}
		
		else if(init==1 || init==2 || init==3){//check customer login details
			String getpass = "SELECT pass FROM user_details WHERE uID = (?)";
			PreparedStatement pst2= conn.prepareStatement(getpass);
			pst2.setString(1,inputID);
			ResultSet rs2 = pst2.executeQuery();
			while(rs2.next()){
				   dbpass =  rs2.getString(1);
		}
			  if(dbpass.equals(inputpass)){
					flag=2;
					}		  
			  else{
				init = init+1;
				String updateInit = "UPDATE user_details SET init =? WHERE uID = ?";
				PreparedStatement pstA= conn.prepareStatement(updateInit);
				pstA.setInt(1,init);
				pstA.setString(2,inputID);
				pstA.executeUpdate();
			  }			  
		}
		
		else{
			
			flag=4;//init more than 4
		}

		conn.close();

		return flag;

	}
		catch (Exception e){ //in case of both wrong id and pass return flag =3
			
			return flag;		
		}
	}
	
	public void storeCust2Pojo(bean.UserDetails POJO, String inputID) throws SQLException, ClassNotFoundException{
		
		//	storing customer details into pojo once logged in
		Connection conn = dbconnection.MyConnection.prepareConnection();
		String getAllDetails = "SELECT * FROM user_details WHERE uID = (?)";
		PreparedStatement pst1= conn.prepareStatement(getAllDetails);
		pst1.setString(1,inputID);
		ResultSet rs1 = pst1.executeQuery();
		while(rs1.next()){
			   POJO.setuID(rs1.getString(1));
			   POJO.setPassword(rs1.getString(2));
			   POJO.setName(rs1.getString(4));
			   POJO.setAddress(rs1.getString(5));
			   POJO.setEmail(rs1.getString(6));
			   POJO.setTelNo(rs1.getString(7));
			   POJO.setLastLogin(rs1.getString(8));
			   POJO.setSecQuest(rs1.getString(9));
			   POJO.setSecAns(rs1.getString(10));
			   

				}
	}
	
	public void changePass(String ID, String newpass ) throws ClassNotFoundException, SQLException{
		Connection conn = dbconnection.MyConnection.prepareConnection();
		String updatePass = "UPDATE user_details SET pass =? WHERE uID = ?";
		PreparedStatement pst= conn.prepareStatement(updatePass);
		pst.setString(1,newpass);
		pst.setString(2, ID);
		pst.executeUpdate();
		pst.close();

	}
		
	public List<bean.Loans> getLoans(String uID) throws ClassNotFoundException, SQLException {
        // vars
		equalizeMinLoan(uID);//call method to equalize min loan amt
        Connection c = null;
        List<bean.Loans> loanList  = new ArrayList<>();
        // SELECT statement + prepare Connection
        try {
            c = dbconnection.MyConnection.prepareConnection();
            Statement st = c.createStatement();
            // execute SELECT statement + pass return into a ResultSet  
            String select = "SELECT *  FROM loans WHERE uID='"+uID+"' AND status='Approved' ";
            ResultSet rs = st.executeQuery(select);  
            // iterate through the ResultSet and store into an CreditCards object
            while(rs.next()) {
                // create new CreditCards object and populate vars
                bean.Loans loan = new bean.Loans();
                loan.setLoanID(rs.getString(1));
                loan.setuID(rs.getString(2));
                loan.setAccID(rs.getString(3));
                loan.setLoanAmount(rs.getDouble(4));
                loan.setMinPayment(rs.getDouble(5));
                loan.setStatus(rs.getString(7));
                loan.setAppDate(rs.getString(8));
                // store populated object into ArrayList<CreditCards>
                loanList.add(loan);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        // return the CreditCards ArrayList<>
        return loanList;        
    }    //end of getCC()

	public void equalizeMinLoan(String uID) throws ClassNotFoundException, SQLException{
		//this method equalizes the remaining loan amount to the minimum loan payment
        List<Double> loanAmt  = new ArrayList<>();
        List<Double> minPay  = new ArrayList<>();

		Connection conn = dbconnection.MyConnection.prepareConnection();
		//draw the loan amount from DB
		String drawLoanAmt = "Select loanAmount from loans WHERE uID = ?";//get outgoing account balance first
		PreparedStatement pst= conn.prepareStatement(drawLoanAmt);
		pst.setString(1,uID);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			 loanAmt.add(rs.getDouble(1));
		}
		
		//draw the minloan amt and update it to the loan amount
		String drawminPay = "Select minPayment from loans WHERE uID = ?";//get outgoing account balance first
		PreparedStatement pst1= conn.prepareStatement(drawminPay);
		pst1.setString(1,uID);
		ResultSet rs1 = pst1.executeQuery();
		while(rs1.next()){
			 minPay.add(rs1.getDouble(1));
		}
		for(int i = 0; i<loanAmt.size();i++){
			
			if(loanAmt.get(i)<minPay.get(i)){//checks condition if loan amt<minloan payment, set minloan payment=loan amt
				String updateLoanAmt = "UPDATE loans SET minPayment =? WHERE loanAmount = ?";
				PreparedStatement pst2= conn.prepareStatement(updateLoanAmt);
				pst2.setDouble(1,loanAmt.get(i));
				pst2.setDouble(2,loanAmt.get(i));
				pst2.executeUpdate();
				pst2.close();

			}
			
		}
		
		
		
	}
		
	public void executeTransfer(String uID, String outgoing, String incoming, String transAmt) throws ClassNotFoundException, SQLException{
		//execute funds transfer.this method is used for both internal and external funds transfer
		int result = 0;
		Connection conn = dbconnection.MyConnection.prepareConnection();
		double transAmtDbl = Double.parseDouble(transAmt); 
		double balance = 0;
		String incominguID = null; //this string stores the uid of the account receiving money
		try{
			String checkBal = "Select balance from accounts WHERE accID = ?";//get outgoing account balance first
			PreparedStatement pst1= conn.prepareStatement(checkBal);
			pst1.setString(1,outgoing);
			ResultSet rs1 = pst1.executeQuery();
			while(rs1.next()){
				 balance = rs1.getDouble(1);
			}
		
			

			if(balance>=transAmtDbl)
			{//do below if enough money for funds transfer
			//updating balances in account table
		
				
			String updateOutBal = "UPDATE accounts SET balance = balance - ? WHERE accID = ?";
			PreparedStatement pst2= conn.prepareStatement(updateOutBal);
			pst2.setDouble(1,transAmtDbl);
			pst2.setString(2, outgoing);
			pst2.executeUpdate();
			pst2.close();
		
			String updateInBal = "UPDATE accounts SET balance = balance + ? WHERE accID = ?";
			PreparedStatement pst3= conn.prepareStatement(updateInBal);
			pst3.setDouble(1,transAmtDbl);
			pst3.setString(2, incoming);
			pst3.executeUpdate();
			pst3.close();	
	
			//inserting record into account history
			String updateAccHistoryWithdraw = "INSERT INTO account_history (accID,uID,withdraw,transDetails) VALUES (?,?,?,?);";
			PreparedStatement pst4= conn.prepareStatement(updateAccHistoryWithdraw);
			pst4.setString(1,outgoing);
			pst4.setString(2,uID);
			pst4.setDouble(3,transAmtDbl);
			pst4.setString(4,"Transfer to "+incoming);
			pst4.executeUpdate();
			pst4.close();

			String queryIncomingUid = "SELECT uID from accounts where accID = ?";
			//this query only executes for transferal to an account within our DB.For external bank accounts incominguid remains null
			PreparedStatement pst5= conn.prepareStatement(queryIncomingUid);
			pst5.setString(1,incoming);
			ResultSet rs5 = pst5.executeQuery();
			while(rs5.next()){
				 incominguID = rs5.getString(1);
			}
			//this update only executes for transferal to an account within our DB.
			String updateAccHistoryDeposit = "INSERT INTO account_history (accID,uID,deposit,transDetails) VALUES (?,?,?,?);";
			PreparedStatement pst6= conn.prepareStatement(updateAccHistoryDeposit);
			pst6.setString(1,incoming);
			pst6.setString(2,incominguID);
			pst6.setDouble(3,transAmtDbl);
			pst6.setString(4,"Transfer from "+outgoing);
			pst6.executeUpdate();
			pst6.close();
			

			
			}
		

		}
		catch (MySQLIntegrityConstraintViolationException e){
			//catch the exception for cases when transferring to an external bank acc not in our DB
		}
	
		
	
	}
	
	public void payBills(String uID, String payingAcc, String billOrg, String billAmt, String refNum) throws NumberFormatException, SQLException, ClassNotFoundException{
		//method excutes paying of bills.
		//method is used by both paying through acc as well as paying through CC
		Connection conn = dbconnection.MyConnection.prepareConnection();
		Double billAmtDbl = Double.parseDouble(billAmt);
		if(payingAcc.substring(0,3).equals("sav")||payingAcc.substring(0,3).equals("chk")){
			//this if statement runs if paying by account
			String updateOutBal = "UPDATE accounts SET balance = balance - ? WHERE accID = ?";
			PreparedStatement pst1= conn.prepareStatement(updateOutBal);
			pst1.setDouble(1,billAmtDbl);
			pst1.setString(2, payingAcc);
			pst1.executeUpdate();
			pst1.close();
			
			String updateAccHistoryWithdraw = "INSERT INTO account_history (accID,uID,withdraw,transDetails) VALUES (?,?,?,?);";
			PreparedStatement pst2= conn.prepareStatement(updateAccHistoryWithdraw);
			pst2.setString(1,payingAcc);
			pst2.setString(2,uID);
			pst2.setDouble(3,billAmtDbl);
			pst2.setString(4,"Payed Bills to "+billOrg+ " Ref. No: "+refNum);
			pst2.executeUpdate();
			pst2.close();

		}
		else{
			//this else statement runs if paying by CC
			String updateCcAvailLimit = "UPDATE credit_cards SET ccAvailLimit = ccAvailLimit - ? WHERE ccNum = ?";
			PreparedStatement pst3= conn.prepareStatement(updateCcAvailLimit);
			pst3.setDouble(1,billAmtDbl);
			pst3.setString(2, payingAcc);
			pst3.executeUpdate();
			pst3.close();
			
			
			String updateCcHistoryDebit = "INSERT INTO cc_history (ccNum,uID,debit,transDetails) VALUES (?,?,?,?);";
			PreparedStatement pst4= conn.prepareStatement(updateCcHistoryDebit);
			pst4.setString(1,payingAcc);
			pst4.setString(2,uID);
			pst4.setDouble(3,billAmtDbl);
			pst4.setString(4,"Payed Bills to "+billOrg+ " Ref. No: "+refNum);
			pst4.executeUpdate();
			pst4.close();

			
		}
		String updateBills = "INSERT INTO bills (uID,billOrg,amount,debitAcc) VALUES (?,?,?,?);";
		//insert pay bill record into Bills
		PreparedStatement pst5= conn.prepareStatement(updateBills);
		pst5.setString(1,uID);
		pst5.setString(2,billOrg+ " Ref. No: "+refNum);
		pst5.setDouble(3,billAmtDbl);
		pst5.setString(4,payingAcc);
		pst5.executeUpdate();
		pst5.close();
		
		
		
	}
	
	public void payLoans(String uID, String payingAcc, String loanID, String repayAmt) throws ClassNotFoundException, SQLException{
		
		Connection conn = dbconnection.MyConnection.prepareConnection();
		Double repayAmtDbl = Double.parseDouble(repayAmt);
		if(payingAcc.substring(0,3).equals("sav")||payingAcc.substring(0,3).equals("chk")){
			// this if statement executes if paying loans by account
			String updateOutBal = "UPDATE accounts SET balance = balance - ? WHERE accID = ?";
			PreparedStatement pst1= conn.prepareStatement(updateOutBal);
			pst1.setDouble(1,repayAmtDbl);
			pst1.setString(2, payingAcc);
			pst1.executeUpdate();
			pst1.close();
			
			String updateAccHistoryWithdraw = "INSERT INTO account_history (accID,uID,withdraw,transDetails) VALUES (?,?,?,?);";
			PreparedStatement pst2= conn.prepareStatement(updateAccHistoryWithdraw);
			pst2.setString(1,payingAcc);
			pst2.setString(2,uID);
			pst2.setDouble(3,repayAmtDbl);
			pst2.setString(4,"Repayment to Loan "+loanID);
			pst2.executeUpdate();
			pst2.close();
		}
		
		
		else{
			//this else executes if paying by CC
			String updateCcAvailLimit = "UPDATE credit_cards SET ccAvailLimit = ccAvailLimit - ? WHERE ccNum = ?";
			PreparedStatement pst3= conn.prepareStatement(updateCcAvailLimit);
			pst3.setDouble(1,repayAmtDbl);
			pst3.setString(2, payingAcc);
			pst3.executeUpdate();
			pst3.close();
			
			
			String updateCcHistoryDebit = "INSERT INTO cc_history (ccNum,uID,debit,transDetails) VALUES (?,?,?,?);";
			PreparedStatement pst4= conn.prepareStatement(updateCcHistoryDebit);
			pst4.setString(1,payingAcc);
			pst4.setString(2,uID);
			pst4.setDouble(3,repayAmtDbl);
			pst4.setString(4,"Repayment to Loan "+loanID);
			pst4.executeUpdate();
			pst4.close();

			
		}
		//updating loan and loan history
		String updateLoans = "UPDATE loans SET loanAmount = loanAmount - ? WHERE loanID = ?";
		PreparedStatement pst5= conn.prepareStatement(updateLoans);
		pst5.setDouble(1,repayAmtDbl);
		pst5.setString(2, loanID);
		pst5.executeUpdate();
		pst5.close();
		
		String updateLoanHistory = "INSERT INTO loan_history (loanID,uID,transDetails,repayment) VALUES (?,?,?,?);";
		PreparedStatement pst6= conn.prepareStatement(updateLoanHistory);
		pst6.setString(1,loanID);
		pst6.setString(2,uID);
		pst6.setString(3,"Repayment of Loan from "+payingAcc);
		pst6.setDouble(4,repayAmtDbl);
		pst6.executeUpdate();
		pst6.close();
		
		}

	public void payCC(String uID, String ccNum, String accID, String accBalance, String repayAmt2) throws ClassNotFoundException, SQLException {
		Connection conn = dbconnection.MyConnection.prepareConnection();
		Double repayAmtDbl = Double.parseDouble(repayAmt2);
		
		//update account balance
		String updateOutBal = "UPDATE accounts SET balance = balance - ? WHERE accID = ?";
		PreparedStatement pst1= conn.prepareStatement(updateOutBal);
		pst1.setDouble(1,repayAmtDbl);
		pst1.setString(2, accID);
		pst1.executeUpdate();
		pst1.close();
		
		//update account history
		String updateAccHistoryWithdraw = "INSERT INTO account_history (accID,uID,withdraw,transDetails) VALUES (?,?,?,?);";
		PreparedStatement pst2= conn.prepareStatement(updateAccHistoryWithdraw);
		pst2.setString(1,accID);
		pst2.setString(2,uID);
		pst2.setDouble(3,repayAmtDbl);
		pst2.setString(4,"Payment to Credit Card "+ccNum);
		pst2.executeUpdate();
		pst2.close();
		
		//update ccAvailLimit
		String updateCcAvailLimit = "UPDATE credit_cards SET ccAvailLimit = ccAvailLimit + ? WHERE ccNum = ?";
		PreparedStatement pst3= conn.prepareStatement(updateCcAvailLimit);
		pst3.setDouble(1,repayAmtDbl);
		pst3.setString(2, ccNum);
		pst3.executeUpdate();
		pst3.close();
		
		//update CChistory, insert into credit coloumn
		String updateCcHistoryCredit = "INSERT INTO cc_history (ccNum,uID,credit,transDetails) VALUES (?,?,?,?);";
		PreparedStatement pst4= conn.prepareStatement(updateCcHistoryCredit);
		pst4.setString(1,ccNum);
		pst4.setString(2,uID);
		pst4.setDouble(3,repayAmtDbl);
		pst4.setString(4,"Payment from Account "+accID);
		pst4.executeUpdate();
		pst4.close();
	}
	
}
