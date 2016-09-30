<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="bean.Loans" %>
<%@ page import="bean.UserDetails" %>
<%@ page import="bean.Accounts" %>
<%@ page import="dao.Logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<%
	
	%>

	<div class="formwide">
		<h3 style="text-align: left">Welcome Back Somebody</h3>
		<p class="message" style="text-align: left">Your last login was 05:58 PM on Thursday 29th September 2016 (Singapore)</p>
	</div>
	
	<div class="formwide">
		<h1 style="text-align: left;">Deposits</h1>
		<table>
			<tr><td><p>DBS Savings Account</p><p style="color: gray;">sav00001</p></td><td><p style="color: gray;">Last Transaction Recorded</p><p>-$50 POS Mr Teh Tarik</p></td><td><p>$500</p><p style="color: gray;">Available Balance</p></td></tr>
			<tr><td><p>DBS Checking Account</p><p style="color: gray;">chk00003</p></td><td><p style="color: gray;">Last Transaction Recorded</p><p>-$70 POS 7-11</p></td><td><p>$2500</p><p style="color: gray;">Available Balance</p></td></tr>
		</table>
	</div>
	
	<div class="formwide">
		<h1 style="text-align: left;">Cards</h1>
		<table>
			<tr><td><p>DBS MasterCard Platinum</p><p style="color: gray;">5482783928360092</p></td><td><p style="color: gray;">Min Payment Due</p><p>$50.72 by 13 Oct 2016</p></td><td><p>$900</p><p style="color: gray;">Outstanding Amount</p></td></tr>
			<tr><td><p>DBS Altitude Visa Card</p><p style="color: gray;">4599827355662300</p></td><td><p style="color: gray;">Min Payment Due</p><p>$150.02 by 23 Oct 2016</p></td><td><p>$900</p><p style="color: gray;">Outstanding Amount</p></td></tr>
		</table>
	</div>
	
	<div class="formwide">
		<h1 style="text-align: left;">Loans</h1>
		<table>
			<tr><td><p>DBS Property Cashline</p><p style="color: gray;">prop61281</p></td><td><p style="color: gray;">Min Payment Due</p><p>$802.44 by 4 Oct 2016</p></td><td><p>$25900</p><p style="color: gray;">Outstanding Balance</p></td></tr>
		</table>	
	</div>

</body>
</html>