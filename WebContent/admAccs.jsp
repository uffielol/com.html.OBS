<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="bean.Loans" %>
<%@page import="bean.UserDetails" %>
<%@page import="bean.Applications"%>
<%@page import="dao.Logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<%
	List<Applications> list = dao.Logic.getApps("WHERE type LIKE '%accoun%' AND status='Pending'");
	request.setAttribute("list", list);
	%>
	
	<div class="navigator">
		<a href="admCreate.jsp">Admin Add</a>
		<a href="admView.jsp">Admin View</a>
		<a href="admEdit.jsp">Admin Edit</a>
		<a href="admLocks.jsp">Admin Unlock</a>
		<a href="admLoans.jsp">Admin Loans</a>
		<a href="custApplyLoan.jsp">Customer Loans</a>
		<a href="admCCs.jsp">Admin CCs</a>
		<a id="currenttab" href="admAccs.jsp">Admin Accounts</a>
		<a href="custApply.jsp">Customer Apply</a>
	</div>
	
	<form action="controllerServlet">       
		<table>
			<tr style="text-align: left">
				<th>User ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone</th>
				<th>CC Type</th>
				<th>Notes</th>
				<th>Status</th>
				<th>Date Applied</th>
			</tr> 
			<c:forEach items="${ list }" var="accApp">
				<tr>
					<td>${accApp.getuID()}</td>
					
					<c:set var="uID" value="${accApp.getuID()}"/>
					<%
					String uID = (String)pageContext.getAttribute("uID");   
					bean.UserDetails u = dao.Logic.getUser(uID); 
					String name  = u.getName();
					String email = u.getEmail();
					String phone = u.getTelNo();
					%> 
					
					<td><%= name %></td>
					<td><%= email %></td>
					<td><%= phone %></td>
									
					<c:set var="item" value="${accApp.getType()}"/>			
					<c:if test="${fn:contains(item, 'Checking')}"><td>DBS Checking Account<input type="hidden" name="acc" value="sav"></td></c:if>
					<c:if test="${fn:contains(item, 'Savings')}"><td>DBS Savings Account<input type="hidden" name="acc" value="chk"></td></c:if>
					
					<td>${accApp.getNotes()}</td>
					<td>${accApp.getStatus()}</td>
					<td>${accApp.getAppDate()}</td>
					<td><input name="${accApp.getAppID()}" type="checkbox">
						<input name="${accApp.getAppID()}uID" type="hidden" value="${accApp.getuID()}"></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<input type="submit" name="type" value="Approve Account"><input type="submit" name="type" value="Deny Account">
	</form>

</body>
</html>