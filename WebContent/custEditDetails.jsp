<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="bean.UserDetails" %>
<%@ page import="bean.Accounts" %>
<%@ page import="bean.CreditCards" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<%
	/*  String 		uID = (String)session.getAttribute("uID");*/
	UserDetails u   = dao.Logic.getUser("usr00003");
	request.setAttribute("u", u);	
	%>

	<br/><br/>  
	
	<form action="controllerServlet">
		<table>
			<tr style="text-align: left">
				<th>Name</th>
				<th>Password</th>
				<th>Address</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Security Question</th>
				<th>Security Answer</th>
			</tr> 
			<tr>
				<td><input type="text" value="${u.getName()}" name="name"></td>
				<td><input type="text" value="${u.getPassword()}" name="password"></td>
				<td><input type="text" value="${u.getAddress()}" name="address" style="width: 350px;"></td>
				<td><input type="text" value="${u.getEmail()}" name="email"></td>
				<td><input type="text" value="${u.getTelNo()}" name="telNo">
				<td><input type="text" value="${u.getSecQuest()}" name="secQ">
				<td><input type="text" value="${u.getSecAns()}" name="secAns">
				<td><input type="hidden" value="${u.getLastLogin()}" name="lastLogin">
					<input type="hidden" value="${u.getInit()}" name="init">
					<input type="hidden" value="${u.getuID()}" name="uID"></td>
			</tr>
		</table>
	
		<br/><br/> 
		
		<input type="submit" name="type" value="Update">
	</form>

	<div class="login-page">
	  	<div class="form">
			<form action="" method="post">
				<h1>Update Details</h1>
				<br/><br/>
				<b>Name</b>
				<p><input type="text" value="${u.getName()}" name="name"></p>
				<b>Address</b>
				<p><input type="text" value="${u.getAddress()}" name="address"></p>
				<b>Email</b>
				<p><input type="text" value="${u.getEmail()}" name="email"></p>
				<b>Phone</b>
				<p><input type="text" value="${u.getTelNo()}" name="telNo"></p>
				<b>Security Question</b>
				<c:set var="ifSecQ" value="${u.getSecQuest()}"/>	
				<c:if test="${fn:contains(ifSecQ, 'born')}"><p><select name="secQ" style="text-align: center;"><option selected value="Where were you born?"  style="text-align: center;">Where were you born?</option><option value="What is your favorite book?" style="text-align: center;">What is your favorite book?</option><option value="When did you graduate High School?" style="text-align: center;">When did you graduate High School?</option><option value="Who is Carmen Sandiego?" style="text-align: center;">Who is Carmen Sandiego?</option><option value="Where is Waldo?" style="text-align: center;">Where's Waldo?</option><option value="How do you touch your toes?" style="text-align: center;">How do you touch your toes?</option></select></p></c:if>			
				<c:if test="${fn:contains(ifSecQ, 'favorite')}"><p><select name="secQ" style="text-align: center;"><option value="Where were you born?"  style="text-align: center;">Where were you born?</option><option selected value="What is your favorite book?" style="text-align: center;">What is your favorite book?</option><option value="When did you graduate High School?" style="text-align: center;">When did you graduate High School?</option><option value="Who is Carmen Sandiego?" style="text-align: center;">Who is Carmen Sandiego?</option><option value="Where is Waldo?" style="text-align: center;">Where's Waldo?</option><option value="How do you touch your toes?" style="text-align: center;">How do you touch your toes?</option></select></p></c:if>
				<c:if test="${fn:contains(ifSecQ, 'graduate')}"><p><select name="secQ" style="text-align: center;"><option value="Where were you born?"  style="text-align: center;">Where were you born?</option><option value="What is your favorite book?" style="text-align: center;">What is your favorite book?</option><option selected value="When did you graduate High School?" style="text-align: center;">When did you graduate High School?</option><option selected value="Who is Carmen Sandiego?" style="text-align: center;">Who is Carmen Sandiego?</option><option value="Where is Waldo?" style="text-align: center;">Where's Waldo?</option><option value="How do you touch your toes?" style="text-align: center;">How do you touch your toes?</option></select></p></c:if>
				<c:if test="${fn:contains(ifSecQ, 'Sandiego')}"><p><select name="secQ" style="text-align: center;"><option value="Where were you born?"  style="text-align: center;">Where were you born?</option><option value="What is your favorite book?" style="text-align: center;">What is your favorite book?</option><option value="When did you graduate High School?" style="text-align: center;">When did you graduate High School?</option><option value="Who is Carmen Sandiego?" style="text-align: center;">Who is Carmen Sandiego?</option><option value="Where is Waldo?" style="text-align: center;">Where's Waldo?</option><option value="How do you touch your toes?" style="text-align: center;">How do you touch your toes?</option></select></p></c:if>
				<c:if test="${fn:contains(ifSecQ, 'Waldo')}"><p><select name="secQ" style="text-align: center;"><option value="Where were you born?"  style="text-align: center;">Where were you born?</option><option value="What is your favorite book?" style="text-align: center;">What is your favorite book?</option><option value="When did you graduate High School?" style="text-align: center;">When did you graduate High School?</option><option value="Who is Carmen Sandiego?" style="text-align: center;">Who is Carmen Sandiego?</option><option selected value="Where is Waldo?" style="text-align: center;">Where's Waldo?</option><option value="How do you touch your toes?" style="text-align: center;">How do you touch your toes?</option></select></p></c:if>
				<c:if test="${fn:contains(ifSecQ, 'toes')}"><p><select name="secQ" style="text-align: center;"><option value="Where were you born?"  style="text-align: center;">Where were you born?</option><option value="What is your favorite book?" style="text-align: center;">What is your favorite book?</option><option value="When did you graduate High School?" style="text-align: center;">When did you graduate High School?</option><option value="Who is Carmen Sandiego?" style="text-align: center;">Who is Carmen Sandiego?</option><option value="Where is Waldo?" style="text-align: center;">Where's Waldo?</option><option selected value="How do you touch your toes?" style="text-align: center;">How do you touch your toes?</option></select></p></c:if>
				
				<b>Security Answer</b>
				<p><input type="text" value="${u.getSecAns()}" name="secAns"></p>
				<br/><br/>
				<button type="submit">Submit</button> 
			</form>
   		</div>
	</div>

</body>
</html>