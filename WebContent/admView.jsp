<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="bean.UserDetails" %>
<%@page import="bean.Accounts" %>
<%@page import="bean.CreditCards" %>
<%@page import="bean.AccountHistory" %>
<%@page import="bean.CCHistory" %>
<%@page import="bean.LoanHistory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<%
	List<UserDetails> list1 = dao.Logic.getUsers();
	request.setAttribute("list1", list1);
	List<Accounts> list2 = dao.Logic.getAccounts();
	request.setAttribute("list2", list2);
	List<CreditCards> list3 = dao.Logic.getCC();
	request.setAttribute("list3", list3);
	List<AccountHistory> list4 = dao.Logic.getAccHistory(";");
	request.setAttribute("list4", list4);
	List<CCHistory> list5 = dao.Logic.getCCHistory(";");
	request.setAttribute("list5", list5);
	List<LoanHistory> list6 = dao.Logic.getLoanHistory(";");
	request.setAttribute("list6", list6);
	%>
        
    <div class="navigator">
		<a href="admCreate.jsp">Admin Add</a>
		<a id="currenttab" href="admView.jsp">Admin View</a>
		<a href="admEdit.jsp">Admin Edit</a>
		<a href="admLocks.jsp">Admin Unlock</a>
		<a href="admLoans.jsp">Admin Loans</a>
		<a href="custApplyLoan.jsp">Customer Loans</a>
		<a href="admCCs.jsp">Admin CCs</a>
		<a href="admAccs.jsp">Admin Accounts</a>
		<a href="custApply.jsp">Customer Apply</a>
	</div>
     
     <br/><br/>   
        
	<table>
		<tr style="text-align: left">
			<th>User ID</th>
			<th>Password</th>
			<th>Init</th>
			<th>Name</th>
			<th>Address</th>
			<th>Email</th>
			<th>Phone</th>
		</tr> 
		<c:forEach items="${ list1 }" var="user">
			<tr>
				<td>${user.getuID()}</td>
				<td>${user.getPassword()}</td>
				<td>${user.getInit()}</td>
				<td>${user.getName()}</td>
				<td>${user.getAddress()}</td>
				<td>${user.getEmail()}</td>
				<td>${user.getTelNo()}</td>
			</tr>
		</c:forEach>
	</table>
	
	<br/><br/>
	
	<table>
		<tr style="text-align: left">
			<th>User ID</th>
			<th>Account ID</th>
			<th>Balance</th>
		</tr> 
		<c:forEach items="${ list2 }" var="accounts">
			<tr>
				<td>${accounts.getuID()}</td>
				<td>${accounts.getAccID()}</td>
				<td>${accounts.getBalance()}</td>
			</tr>
		</c:forEach>
	</table>
	
	<br/><br/>
	
	<table>
		<tr style="text-align: left">
			<th>User ID</th>
			<th>Credit Card Number</th>
			<th>Credit Card Limit</th>
			<th>Current Spent Pool</th>
			<th>Points</th>
		</tr> 
		<c:forEach items="${ list3 }" var="cc">
			<tr>
				<td>${cc.getuID()}</td>
				<td>${cc.getCcNum()}</td>
				<td>${cc.getCcLimit()}</td>
				<td>${cc.getCcToPoints()}</td>
				<td>${cc.getCcPoints()}</td>
			</tr>
		</c:forEach>
	</table>
		
	<br/><br/>
	
	<table>
		<tr style="text-align: left">
			<th>Loan ID</th>
			<th>User ID</th>
			<th>Transaction Details</th>
			<th>Transaction Date</th>
			<th>Repayment Amount</th>
		</tr> 
		<c:forEach items="${ list6 }" var="loanHist">
			<tr>
				<td>${loanHist.getLoanID()}</td>
				<td>${loanHist.getuID()}</td>
				<td>${loanHist.getTransDetails()}</td>
				<td>${loanHist.getTransDate()}</td>
				<td>${loanHist.getRepayment()}</td>
			</tr>
		</c:forEach>
	</table>
		
	<br/><br/>
	
	<table>
		<tr style="text-align: left">
			<th>Credit Card Number</th>
			<th>User ID</th>
			<th>Debit</th>
			<th>Credit</th>
			<th>Transaction Details</th>
			<th>Transaction Date</th>
		</tr> 
		<c:forEach items="${ list5 }" var="ccHist">
			<tr>
				<td>${ccHist.getCcNum()}</td>
				<td>${ccHist.getuID()}</td>
				<td>${ccHist.getDebit()}</td>
				<td>${ccHist.getCredit()}</td>
				<td>${ccHist.getTransDetails()}</td>
				<td>${ccHist.getTransDate()}</td>
			</tr>
		</c:forEach>
	</table>
		
	<br/><br/>
	
	<table>
		<tr style="text-align: left">
			<th>Account ID</th>
			<th>User ID</th>
			<th>Withdraw</th>
			<th>Deposit</th>
			<th>Transaction Details</th>
			<th>Transaction Date</th>
		</tr> 
		<c:forEach items="${ list4 }" var="accHist">
			<tr>
				<td>${accHist.getAccID()}</td>
				<td>${accHist.getuID()}</td>
				<td>${accHist.getWithdraw()}</td>
				<td>${accHist.getDeposit()}</td>
				<td>${accHist.getTransDetails()}</td>
				<td>${accHist.getTransDate()}</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>