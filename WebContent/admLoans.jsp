<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="bean.Loans" %>
<%@page import="bean.UserDetails" %>
<%@page import="bean.UserDetails"%>
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
	List<Loans> list = dao.Logic.getLoanApps("WHERE status='Pending'");
	request.setAttribute("list", list);
	%>
	
	<div class="navigator">
		<a href="admCreate.jsp">Admin Add</a>
		<a href="admView.jsp">Admin View</a>
		<a href="admEdit.jsp">Admin Edit</a>
		<a href="admLocks.jsp">Admin Unlock</a>
		<a id="currenttab" href="admLoans.jsp">Admin Loans</a>
		<a href="custApplyLoan.jsp">Customer Loans</a>
		<a href="admCCs.jsp">Admin CCs</a>
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
				<th>Loan Type</th>
				<th>Amount (After Interest)</th>
				<th>To Account</th>
				<th>Status</th>
				<th>Date Applied</th>
			</tr> 
			<c:forEach items="${ list }" var="loan">
				<tr>
					<td>${loan.getuID()}</td>
					
					<c:set var="uID" value="${loan.getuID()}"/>
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
									
					<c:set var="item" value="${loan.getLoanID()}"/>	
					<c:if test="${fn:contains(item, 'carr')}"><td>DBS Car Loan (${loan.getLoanID()})</td></c:if>			
					<c:if test="${fn:contains(item, 'pers')}"><td>DBS Personal Loan (${loan.getLoanID()})</td></c:if>
					<c:if test="${fn:contains(item, 'cash')}"><td>DBS Cash Loan (${loan.getLoanID()})</td></c:if>
					<c:if test="${fn:contains(item, 'prop')}"><td>DBS Property Loan (${loan.getLoanID()})</td></c:if>
					<c:if test="${fn:contains(item, 'comm')}"><td>DBS Commercial Loan (${loan.getLoanID()})</td></c:if>

					<td>${loan.getLoanAmount()}</td>
					<td>${loan.getAccID()}</td>
					<td>${loan.getStatus()}</td>
					<td>${loan.getAppDate()}</td>
					<td><input name="${loan.getLoanID()}" type="checkbox">
						<input name="${loan.getLoanID()}accID" type="hidden" value="${loan.getAccID()}"></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<input type="submit" name="type" value="Approve Loan"><input type="submit" name="type" value="Deny Loan">
	</form>

</body>
</html>