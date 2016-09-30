package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Logic;


@WebServlet("/controllerServlet")
public class controllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static bean.UserDetails POJO = new bean.UserDetails();
    static DecimalFormat decim = new DecimalFormat("0.00");

    // main controller switch
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get type parameter from form
		String type = request.getParameter("type");
		// into switch-case
		switch(type) {		
		// wei yang's switches //
		case "login":							login(request, response);
												break;												
		case "changePass":						changePass(request, response);
												break;										
		case "transfer":						transfer(request, response);
												break;
		case "paybills":						payBills(request, response);
												break;
		case "payLoan":							payLoan(request, response);
												break;
		case "payCC":							try {
													payCC(request, response);
												} catch (ClassNotFoundException | SQLException e) {
													e.printStackTrace();
												}
												break;
		// charles' switches //
		case "Add":								createUsr(request, response);				
												break;		
		case "Update":							updateUsr(request, response);
												break;												
		case "Edit Selected":					editUsr(request, response);
												break;												
		case "Delete Selected":					deleteUsr(request, response);
											    break;		
		case "Unlock":							unlockAccount(request, response);
												break;													
		case "Approve Loan":					approveLoan(request, response);
												break;												
		case "Deny Loan":						denyLoan(request, response);
												break;												
		case "Approve Card":					approveCard(request, response);
												break;		
		case "Deny Card":						denyCard(request, response);
												break;												
		case "Approve Account":					approveAcc(request, response);
												break;			
		case "Deny Account":					denyApp(request, response);
												break;												
		case "Loan":							appLoan(request, response);
												break;													
		case "CC":								appCC(request, response);
												break;												
		case "Acc":								appAcc(request, response);
												break;												
		}
	}
	
	////////////////////////////
	// Wei Yang's Controllers //
	////////////////////////////
	
	private void payCC(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, SQLException {
		String cccombine = request.getParameter("ccNum");
		String accCombine2 = request.getParameter("payingAcc");
		String repayAmt2 = request.getParameter("repayAmt");
		String ccArray[] = cccombine.split(",");
		String accArray[] = accCombine2.split(",");
		String ccNum = ccArray[0];
		String accID = accArray[0];
		String accBalance = accArray[1];

	    PrintWriter out = response.getWriter();
	    
		if(Double.parseDouble(accBalance)>=Double.parseDouble(repayAmt2)) {
		    dao.Logic ref4 = new dao.Logic();
		    ref4.payCC(POJO.getuID(), ccNum, accID, accBalance, repayAmt2);
		    out.println("<script type=\"text/javascript\">");
		    out.println("alert('Successfully payed $"+decim.format(Double.parseDouble(repayAmt2))+" for Creditcard: "+ccNum+" ');");
		 	out.println("location='custPayCC.jsp';");
		 	out.println("</script>");  
		} else {
			out.println("<script type=\"text/javascript\">");
		 	out.println("alert('Insufficicient funds in Account: "+accID+"');");
		 	out.println("location='custPayCC.jsp';");
		 	out.println("</script>");  	
		}
	}

	private void payLoan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String loancombine = request.getParameter("loanID");
		String accCombine = request.getParameter("payingAcc");
		String repayAmt = request.getParameter("repayAmt");
		String array1[] = loancombine.split(",");
		String loanID = array1[0];
		String loanAmt = array1[1];
		try {
		   	PrintWriter out = response.getWriter();
	    	String payingAcc =null;
			String balance = null;
			if(accCombine.substring(0,3).equals("sav")||accCombine.substring(0,3).equals("chk")) {
				//if statement for paying by acc
			 payingAcc = accCombine.substring(0,8);
			 balance = accCombine.substring(8,accCombine.length());
			} else {
				//else statement if paying by CC
				payingAcc = accCombine.substring(0,16);
				balance = accCombine.substring(16,accCombine.length());
			}
			
			if(Double.parseDouble(balance)>=Double.parseDouble(repayAmt)){//check if enough funds in paying account
		    	dao.Logic ref3 = new dao.Logic();
		    	try {
					ref3.payLoans(POJO.getuID(),payingAcc,loanID,repayAmt);
					
					if(payingAcc.substring(0,3).equals("sav")||payingAcc.substring(0,3).equals("chk")) {
						//if statement for paying by acc successful
						out.println("<script type=\"text/javascript\">");
				 		out.println("alert('Successfully repaid $"+decim.format(Double.parseDouble(repayAmt))+" to Loan: "+loanID+"');");
				 		out.println("location='custPayLoanFromAcc.jsp';");
				 		out.println("</script>");  
					} else {
						//if statement for paying by CC successful
						out.println("<script type=\"text/javascript\">");
					 	out.println("alert('Successfully repaid $"+decim.format(Double.parseDouble(repayAmt))+" to Loan: "+loanID+" ');");
					 	out.println("location='custPayLoanFromCC.jsp';");
					 	out.println("</script>");  				
					}
		    	} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			} else {
				//else statement executes if not enough funds
				if(payingAcc.substring(0,3).equals("sav")||payingAcc.substring(0,3).equals("chk")){
					out.println("<script type=\"text/javascript\">");
		 		    out.println("alert('Insufficient funds in Account: "+payingAcc+"');");
		 		    out.println("location='custPayLoanFromAcc.jsp';");
		 		    out.println("</script>");  
				} else {
					out.println("<script type=\"text/javascript\">");
			 	    out.println("alert('Insufficient credit in Credit Card: "+payingAcc+"');");
			 	    out.println("location='custPayLoanFromCC.jsp';");
			 	    out.println("</script>");  				
				}	
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void payBills(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String combine = request.getParameter("payingAcc");
		String billOrg = request.getParameter("billOrg");
		String billAmt = request.getParameter("billAmt");
		String refNum = request.getParameter("refNum");
		PrintWriter out = response.getWriter();
    	String payingAcc =null;
		String balance = null;
		if(combine.substring(0,3).equals("sav")||combine.substring(0,3).equals("chk")){//if statement for paying from account
			payingAcc = combine.substring(0,8);
			balance = combine.substring(8,combine.length());
		} else {//else statement if paying by CC
			 payingAcc = combine.substring(0,16);
			 balance = combine.substring(16,combine.length());	
		}
		if(Double.parseDouble(balance)>=Double.parseDouble(billAmt)){
	    	dao.Logic ref2 = new dao.Logic();
	    	try {
				ref2.payBills(POJO.getuID(),payingAcc,billOrg,billAmt,refNum);
				
				if(payingAcc.substring(0,3).equals("sav")||payingAcc.substring(0,3).equals("chk")){//if statement if paying by account successful
					   out.println("<script type=\"text/javascript\">");
			 		   out.println("alert('Successfully payed bill of $"+decim.format(Double.parseDouble(billAmt))+" to "+billOrg+" "+refNum+"');");
			 		   out.println("location='custPayBillsFromAcc.jsp';");
			 		   out.println("</script>");  
				} else {
					//else statement if paying by CC successful
					out.println("<script type=\"text/javascript\">");
				 	out.println("alert('Successfully payed bill of $"+decim.format(Double.parseDouble(billAmt))+" to "+billOrg+" "+refNum+"');");
				 	out.println("location='custPayBillsFromCC.jsp';");
				 	out.println("</script>");  				
				}
	    	} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}		
		} else {//if statement if paying by account and not enough funds
			if(payingAcc.substring(0,3).equals("sav")||payingAcc.substring(0,3).equals("chk")){
				out.println("<script type=\"text/javascript\">");
	 		    out.println("alert('Insufficient funds in Account: "+payingAcc+"');");
	 		    out.println("location='custPayBillsFromAcc.jsp';");
	 		    out.println("</script>");  
			} else {//else statement if paying by CC and not enough funds
				out.println("<script type=\"text/javascript\">");
		 	   	out.println("alert('Insufficient funds in Credit Card: "+payingAcc+"');");
		 	   	out.println("location='custPayBillsFromCC.jsp';");
		 	   	out.println("</script>");  				
			}
		}
	}

	private void transfer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String combineString = request.getParameter("outgoingAcc");
		String outgoingAcc = combineString.substring(0,8);
		String incomingAcc = request.getParameter("incomingAcc");
		String transferAmt = request.getParameter("transAmt");
		try {
			PrintWriter out = response.getWriter();
			dao.Logic ref = new dao.Logic();
			if(outgoingAcc.equals(incomingAcc)){//do not allow transfer within the same account
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Unable to transfer within same account.');");
		  		out.println("location='custTransfer.jsp';");
		  		out.println("</script>");   
			} else {
				ref.executeTransfer(POJO.getuID(), outgoingAcc, incomingAcc, transferAmt);
				out.println("<script type=\"text/javascript\">");
		  		out.println("alert('Transfer of $"+ (decim.format(Double.parseDouble(transferAmt))) + " from "+outgoingAcc+ " to "+incomingAcc +" successfully processed.');");
		  		out.println("location='custTransfer.jsp';");
		  		out.println("</script>");    		
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void changePass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String newpass = request.getParameter("newpass");
    	dao.Logic ref = new dao.Logic();
    	PrintWriter out = response.getWriter();
    	try {
			ref.changePass(POJO.getuID(), newpass);
	 		out.println("<script type=\"text/javascript\">");
	 		out.println("alert('Password Updated Successfully. Returning to Customer Module');");
	 		out.println("location='custModule.jsp';");
	 		out.println("</script>");    
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String inputID = request.getParameter("username");
		String inputpass = request.getParameter("password");
		try {
	    	dao.Logic ref = new dao.Logic();
	        int result  = ref.authenticate(inputID, inputpass);
	        if(result == 1){//go to adminmodule
	      	RequestDispatcher requestDispatcher; 
	        	requestDispatcher = request.getRequestDispatcher("adminModule.jsp");
	        	requestDispatcher.forward(request, response);
	        }
	        else if(result == 2){//go to custmodule
	        	HttpSession session = request.getSession();
	        	session.setAttribute("uID", inputID);
	        	ref.storeCust2Pojo(POJO, inputID);
//	          	RequestDispatcher requestDispatcher; 
//	        	requestDispatcher = request.getRequestDispatcher("custModulePage.jsp");
//	        	requestDispatcher.forward(request, response);
	        	out.println("<script type=\"text/javascript\">");
	        	out.println("parent.window.open ('custModulePage.jsp','_self',false)");
	  		   	out.println("</script>");    		 
	        }   
	        else if(result == 3){// wrong login details
	 		   out.println("<script type=\"text/javascript\">");
	 		   out.println("alert('Error Login Details!');");
	 		   out.println("location='login.html';");
	 		   out.println("</script>");    		 
	        }
	        else if(result == 4){//account locked
	  		   out.println("<script type=\"text/javascript\">");
	  		   out.println("alert('Account Locked');");
	  		   out.println("location='login.html';");
	  		   out.println("</script>");    		
	        }	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	//////////////////////////
	// Charles' Controllers //
	//////////////////////////

	private void approveAcc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration names = request.getParameterNames();
		
		String acc   = request.getParameter("acc");
		String appID = null;
		String uID   = null;
		while (names.hasMoreElements()) {
			appID = (String) names.nextElement();
		    if (!appID.contains("type") && !appID.contains("uID") && !appID.contains("acc")) {
		    	uID = request.getParameter(appID+"uID");
		    	dao.Logic.approveAcc(appID, uID, acc);
		    }
		}
		RequestDispatcher ref = request.getRequestDispatcher("admAccs.jsp");
		ref.include(request, response);	
		PrintWriter out = response.getWriter();
		out.print("Approved");
	}

	private void denyApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String appID = (String) names.nextElement();
		    if (!appID.contains("type") && !appID.contains("uID")) {
		    	dao.Logic.denyApp(appID);
		    }
		}
		PrintWriter out = response.getWriter();
		RequestDispatcher ref = request.getRequestDispatcher("admAccs.jsp");
		ref.include(request, response);	
		out.print("Denied");
	}

	private void approveCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration names = request.getParameterNames();
		String 		type  = request.getParameter("card");
		while (names.hasMoreElements()) {
			String appID = (String) names.nextElement();
		    if (!appID.contains("type") && !appID.contains("uID") && !appID.contains("card")) {
		    	String uID = request.getParameter(appID+"uID");
		    	dao.Logic.approveCC(appID, uID, type);
		    }
		}
		PrintWriter out = response.getWriter();
		RequestDispatcher ref = request.getRequestDispatcher("admCCs.jsp");
		ref.include(request, response);	
		out.print("Approved");		
	}

	private void denyCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String appID = (String) names.nextElement();
		    if (!appID.contains("type") && !appID.contains("uID")) {
		    	dao.Logic.denyApp(appID);
		    }
		}
		PrintWriter out = response.getWriter();
		RequestDispatcher ref = request.getRequestDispatcher("admCCs.jsp");
		ref.include(request, response);	
		out.print("Denied");		
	}

	private void appAcc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get form parameters
//		String uID = POJO.getuID();
		String uID   = "usr00006";
		String acc   = request.getParameter("acc");
		String promo = request.getParameter("promo");
		// execute logic
		dao.Logic.newApp(uID, acc, "PROMO$"+promo);
		// redirect
		PrintWriter out = response.getWriter();
		RequestDispatcher ref = request.getRequestDispatcher("custApply.jsp");
		ref.include(request, response);	
		out.print("Success");
	}

	private void appCC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get form parameters
//		String uID = POJO.getuID();
		String uID   = "usr00006";
		String card  = request.getParameter("card");
		String limit = request.getParameter("ccLimit");		
		// execute logic
		dao.Logic.newApp(uID, card, "CLIMIT$"+limit);
		// redirect
		PrintWriter out = response.getWriter();
		RequestDispatcher ref = request.getRequestDispatcher("custApply.jsp");
		ref.include(request, response);	
		out.print("Success");
	}

	private void denyLoan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String loanID = (String) names.nextElement();
		    if (!loanID.contains("type") && !loanID.contains("accID")) {
		    	String accID = request.getParameter(loanID+"accID");
		    	dao.Logic.denyLoan(loanID);
		    }
		}
		PrintWriter out = response.getWriter();
		RequestDispatcher ref = request.getRequestDispatcher("admLoans.jsp");
		ref.include(request, response);	
		out.print("Denied");	
	}

	private void appLoan(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// get form parameters
//		String uID = POJO.getuID();
		String uID = "usr00006";
//		String accID   = request.getParameter("outgoingAcc");
		String accID = "chk00003";
		String interest = request.getParameter("interest");
		String loanAmt = request.getParameter("loanAmount");
		String period  = request.getParameter("loanPeriod");
		String type    = request.getParameter("loan");
		// execute logic
		dao.Logic.newLoan(uID, accID, loanAmt, period, type, interest);
		// redirect
		PrintWriter out = response.getWriter();
		RequestDispatcher ref = request.getRequestDispatcher("custApplyLoan.jsp");
		ref.include(request, response);	
		out.print("Success");
	}

	private void approveLoan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String loanID = (String) names.nextElement();
		    if (!loanID.contains("type") && !loanID.contains("accID")) {
		    	String accID = request.getParameter(loanID+"accID");
		    	dao.Logic.approveLoan(loanID, accID);
		    }
		}
		PrintWriter out = response.getWriter();
		RequestDispatcher ref = request.getRequestDispatcher("admLoans.jsp");
		ref.include(request, response);	
		out.print("Approved");
	}

	private void unlockAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String uID = (String) names.nextElement();
		    if (!uID.contains("type")) {
		    	dao.Logic.updateLock(uID);
		    }
		}
		RequestDispatcher ref = request.getRequestDispatcher("admLocks.jsp");
		ref.forward(request, response);		
	}

	private void deleteUsr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String uID = (String) names.nextElement();
		    if (!uID.contains("type")) {
		    	dao.Logic.deleteUser(uID);
		    }
		}
		RequestDispatcher ref = request.getRequestDispatcher("admEdit.jsp");
		ref.forward(request, response);		
	}

	private void editUsr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bean.UserDetails selected = null;
		List<bean.CreditCards> ccList = null;
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String uID = (String) names.nextElement();
			if (!uID.contains("type")) {
				selected = dao.Logic.getUser(uID);
				ccList   = dao.Logic.getCCs(uID);
			}
		}
		request.setAttribute("selected", selected);
		request.setAttribute("ccList", ccList);
		RequestDispatcher ref = request.getRequestDispatcher("admEditFields.jsp");
		ref.include(request, response);
	}

	private void updateUsr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get form parameters
		String uID      = request.getParameter("uID");
		String name     = request.getParameter("name");
		String pass     = request.getParameter("password");
		String init     = request.getParameter("init");
		String email    = request.getParameter("email");
		String telNo    = request.getParameter("telNo");
		String address  = request.getParameter("address");
		String ccNum    = request.getParameter("ccNum");
		String ccLimit  = request.getParameter("ccLimit");
		String ccPoints = request.getParameter("ccPoints");
		// create objs
		bean.UserDetails u = new bean.UserDetails();
		bean.CreditCards c = new bean.CreditCards();
		// populate objs
		u.setuID(uID);
		u.setPassword(pass);
		u.setInit(Integer.parseInt(init));
		u.setName(name);
		u.setEmail(email);
		u.setTelNo(telNo);
		u.setAddress(address);
		c.setCcNum(ccNum);
		c.setCcLimit(Double.parseDouble(ccLimit));
		c.setCcPoints(Integer.parseInt(ccPoints));
		// update DB
		dao.Logic.updateUser(u);
		dao.Logic.updateCC(c);
		// return to admin page
		RequestDispatcher ref = request.getRequestDispatcher("admEdit.jsp");
		ref.forward(request, response);	
	}

	public static void createUsr(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// get form parameters
		String uID     = request.getParameter("uID");
		String name    = request.getParameter("name");
		String email   = request.getParameter("email");
		String telNo   = request.getParameter("phone");
		String address = request.getParameter("address");
		String ccLimit = request.getParameter("ccLimit");
		// format credit limit
		Double ccLimit2 = Double.parseDouble(ccLimit);
		DecimalFormat df = new DecimalFormat("#0.00");	
		String ccLimit3 = df.format(ccLimit2);
		// create new UserDetals object
		bean.UserDetails u = new bean.UserDetails();
		// set vars
		u.setuID(uID);
		u.setName(name);
		u.setEmail(email);
		u.setTelNo(telNo);
		u.setAddress(address);
		// insert into DB
		dao.Logic.newCustomer(u);
		dao.Logic.newAcc(u.getuID(), "sav");
		dao.Logic.newCC(u.getuID(), ccLimit3, "everyday");
		// return to admin page
		RequestDispatcher ref = request.getRequestDispatcher("adminModule.jsp");
		ref.forward(request, response);	
	}	// end of create()
	
}
