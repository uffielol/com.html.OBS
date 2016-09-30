<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@page import="dao.Logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<div class="navigator">
		<a id="currenttab" href="admCreate.jsp">Admin Add</a>
		<a href="admView.jsp">Admin View</a>
		<a href="admEdit.jsp">Admin Edit</a>
		<a href="admLocks.jsp">Admin Unlock</a>
		<a href="admLoans.jsp">Admin Loans</a>
		<a href="custApplyLoan.jsp">Customer Loans</a>
		<a href="admCCs.jsp">Admin CCs</a>
		<a href="admAccs.jsp">Admin Accounts</a>
		<a href="custApply.jsp">Customer Apply</a>
	</div>        
	      
	<br/><br/>
	
	<form action="controllerServlet">
		<table>
			<tr>    
				<td>ID</td>
				<td>
					<input type="text" name="disabled" value="<%= dao.Logic.autoGenUsrID() %>" disabled>
					<input type="hidden" name="uID" value="<%= dao.Logic.autoGenUsrID() %>">
				</td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="email" name="email" required></td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><input type="number" name="phone" required></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" name="address" required></td>
			</tr>
			<tr>
				<td>Credit Limit</td>
				<td><input type="number" name="ccLimit" required></td>
			</tr>
		</table>
		<br>
		<input type="submit" name="type" value="Add">
	</form>

<!--  1. create account with credit card details  -->   
<!--  2. update /delete customer details  -->
<!--  3. unlock locked accounts  -->
<!--  4.approve/reject loan  -->
</body>
</html>