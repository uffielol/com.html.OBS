<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="bean.Loans" %>
<%@ page import="bean.UserDetails" %>
<%@ page import="bean.UserDetails"%>
<%@ page import="dao.Logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<div class="navigator">
		<a href="admCreate.jsp">Admin Add</a>
		<a href="admView.jsp">Admin View</a>
		<a href="admEdit.jsp">Admin Edit</a>
		<a href="admLocks.jsp">Admin Unlock</a>
		<a href="admLoans.jsp">Admin Loans</a>
		<a id="currenttab" href="custApplyLoan.jsp">Customer Loans</a>
		<a href="admCCs.jsp">Admin CCs</a>
		<a href="admAccs.jsp">Admin Accounts</a>
		<a href="custApply.jsp">Customer Apply</a>
	</div>
	
	<form method="post">
		<table>
			<tr>
				<th>Type of Loan</th>
			</tr>
			<tr>
				<td><a href="custLoanForm.jsp?type=carr">DBS Car Loan</a></td>
			</tr>
			<tr>
				<td><a href="custLoanForm.jsp?type=prop">DBS Property Loan</a></td>
			</tr>
			<tr>
				<td><a href="custLoanForm.jsp?type=pers">DBS Personal Loan</a></td>
			</tr>
			<tr>
				<td><a href="custLoanForm.jsp?type=cash">DBS Cash Loan</a></td>
			</tr>
			<tr>
				<td><a href="custLoanForm.jsp?type=comm">DBS Commercial Loan</a></td>
			</tr>
		</table>
	</form>


</body>
</html>