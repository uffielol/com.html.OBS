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
	List<Applications> list = dao.Logic.getApps("WHERE type LIKE '%card%' AND status='Pending'");
	request.setAttribute("list", list);
	%>
	
	<div class="navigator">
		<a href="admCreate.jsp">Admin Add</a>
		<a href="admView.jsp">Admin View</a>
		<a href="admEdit.jsp">Admin Edit</a>
		<a href="admLocks.jsp">Admin Unlock</a>
		<a href="admLoans.jsp">Admin Loans</a>
		<a href="custApplyLoan.jsp">Customer Loans</a>
		<a id="currenttab" href="admCCs.jsp">Admin CCs</a>
		<a href="admAccs.jsp">Admin Accounts</a>
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
			<c:forEach items="${ list }" var="ccApp">
				<tr>
					<td>${ccApp.getuID()}</td>
					
					<c:set var="uID" value="${ccApp.getuID()}"/>
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
									
					<c:set var="item" value="${ccApp.getType()}"/>	
					<c:if test="${fn:contains(item, 'Black')}"><td>DBS Black Visa Signature Card<input type="hidden" name="card" value="black"></td></c:if>			
					<c:if test="${fn:contains(item, 'Altitude')}"><td>DBS Altitude DBS Altitude American Express® Card<input type="hidden" name="card" value="altitude"></td></c:if>
					<c:if test="${fn:contains(item, 'Everyday')}"><td>POSB Everyday MasterCard®<input type="hidden" name="card" value="everyday"></td></c:if>
					<c:if test="${fn:contains(item, 'Safra')}"><td>POSB Safra MasterCard®<input type="hidden" name="card" value="safra"></td></c:if>

					<td>${ccApp.getNotes()}</td>
					<td>${ccApp.getStatus()}</td>
					<td>${ccApp.getAppDate()}</td>
					<td><input name="${ccApp.getAppID()}" type="checkbox">
						<input name="${ccApp.getAppID()}uID" type="hidden" value="${ccApp.getuID()}"></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<input type="submit" name="type" value="Approve Card"><input type="submit" name="type" value="Deny Card">
	</form>

</body>
</html>