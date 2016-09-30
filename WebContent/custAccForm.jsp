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

	<script type="text/javascript">
	function calc(w) {
		var x = document.getElementById("loanAmount").value;
		var y = document.getElementById("loanPeriod");
		var z = y.options[y.selectedIndex].value;
		var a = x * (1 + ((w / 100) * (z / 12)));
		var c = a / z;
		var b = document.getElementById("installment");
		b.firstChild.nodeValue = "$"+c.toFixed(2);
		var d = document.getElementById("fees");
		d.firstChild.nodeValue = "$"+(x*0.02545).toFixed(2);
	}
	</script>

<title>Insert title here</title>
</head>
<body>

	<div class="navigator">
		<a href="admCreate.jsp">Admin Add</a>
		<a href="admView.jsp">Admin View</a>
		<a href="admEdit.jsp">Admin Edit</a>
		<a href="admLocks.jsp">Admin Unlock</a>
		<a href="admLoans.jsp">Admin Loans</a>
		<a href="custApplyLoan.jsp">Customer Loans</a>
		<a href="admCCs.jsp">Admin CCs</a>
		<a href="admAccs.jsp">Admin Accounts</a>
		<a href="custApply.jsp">Customer Apply</a>
	</div>
	
	<% 	
	String type     = null;
	if (request.getParameter("type") != null) {
		type = request.getParameter("type");
	}
	%>
	
	<div class="login-page">
	  	<div class="form">
			<form action="controllerServlet?type=Acc" method="post">
				<p>You are applying for</p>
				<c:set var="acctype" value="<%= type %>"/>	
				<c:if test="${fn:contains(acctype, 'checking')}"><h1>DBS Checking Account</h1><input type="hidden" name="acc" value="DBS Checking Account" style="none"></c:if>			
				<c:if test="${fn:contains(acctype, 'saving')}"><h1>DBS Savings Account</h1><input type="hidden" name="acc" value="DBS Savings Account" style="none"></c:if>
				<br/><br/>
				<b>Promo Code</b>
				<p><input type="number" placeholder="XX-XX00X-00" name="promo" /></p>
				<b>iBanking access and eStatement</b>
				<p class="message">I understand that my new Credit Card/Cashline account will come with iBanking and eStatement facilities. (Not applicable to supplementary applicants and applicants who apply via fax) </p>
				<p class="message"><input type="checkbox" name="something" style="width: 20px;"> iBanking access and eStatement</p>
				<p class="message">I understand that in the absence of an existing Current/Savings account, this Credit Card/Cashline account will be the iBanking Primary Account from which an ibanking Secure Device replacement fee (where applicable) will be debited. We will send your iBanking Secure Device and log in information to your Primary Account's mailing address.</p>
				<p class="message">Note:</p>
				<p class="message">1. DBS iBanking access extends to all your DBS/POSB accounts including joint-alternate accounts, but excluding POSBkids Accounts, joint-all Accounts, DBS Foreign Currency Accounts and Corporate Accounts.</p>
				<p class="message">2. eStatement facility extends to your Credit Card/Cashline account and shall replace all paper statement(s) pertaining to your Credit Card/Cashline account.</p>
				<p class="message">3. For existing iBanking users:</p>
				<p class="message">- iBanking access will be extended to your new Credit Card/Cashline account(s).</p>
				<p class="message">- eStatement facility will be extended to your Credit Card/Cashline accounts if you have registered iBanking Secure Device.</p>
				<br/>
				<b>Terms & Conditions</b>
				<p class="message"><input type="checkbox" name="something" style="width: 20px;">I have read the<a href="" style="border: none;">terms & conditions.</a></p>
				<br/><br/>
				<button type="submit">Submit</button> 
			</form>
   		</div>
	</div>
	
	
</body>
</html>