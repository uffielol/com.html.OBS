<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="bean.UserDetails" %>
<%@page import="bean.Accounts" %>
<%@page import="bean.CreditCards" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<%
	List<UserDetails> list = dao.Logic.getLockedUsers();
	request.setAttribute("list", list);
	%>

	        
    <div class="navigator">
		<a href="admCreate.jsp">Admin Add</a>
		<a href="admView.jsp">Admin View</a>
		<a href="admEdit.jsp">Admin Edit</a>
		<a id="currenttab" href="admLocks.jsp">Admin Unlock</a>
		<a href="admLoans.jsp">Admin Loans</a>
		<a href="custApplyLoan.jsp">Customer Loans</a>
		<a href="admCCs.jsp">Admin CCs</a>
		<a href="admAccs.jsp">Admin Accounts</a>
		<a href="custApply.jsp">Customer Apply</a>
	</div>
     
     <br/><br/>   
 
 	<form action="controllerServlet">       
		<table>
			<tr style="text-align: left">
				<th>User ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone</th>
			</tr> 
			<c:forEach items="${ list }" var="user">
				<tr>
					<td>${user.getuID()}</td>
					<td>${user.getName()}</td>
					<td>${user.getEmail()}</td>
					<td>${user.getTelNo()}</td>
					<td><input name="${user.getuID()}" type="checkbox"></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<input type="submit" name="type" value="Unlock">
	</form>

</body>
</html>