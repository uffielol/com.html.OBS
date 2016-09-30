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
	String type   = null;
	double interest = 0;
	if (request.getParameter("type") != null) {
		type = request.getParameter("type");
		if (type.equals("carr")) {
			interest = 3.875;
		} else if (type.equals("prop")) {
			interest = 3.255;
		} else if (type.equals("pers")) {
			interest = 3.00;
		} else if (type.equals("cash")) {
			interest = 4.234;
		} else if (type.equals("comm")) {
			interest = 5.155;
		}
	}
	String uID = (String)session.getAttribute("uID");
	List<Accounts> list1 = dao.Logic.getAccs(uID);
	request.setAttribute("list1", list1);
	%>
	
	<div class="login-page">
	  	<div class="form">
			<form action="controllerServlet?type=Loan" method="post">
				<c:set var="loantype" value="<%= type %>"/>	
				<c:if test="${fn:contains(loantype, 'carr')}"><h1>DBS Car Loan</h1><input type="hidden" name="loan" value="carr" style="none"></c:if>			
				<c:if test="${fn:contains(loantype, 'pers')}"><h1>DBS Personal Loan</h1><input type="hidden" name="loan" value="pers" style="none"></c:if>
				<c:if test="${fn:contains(loantype, 'cash')}"><h1>DBS Cash Loan</h1><input type="hidden" name="loan" value="cash" style="none"></c:if>
				<c:if test="${fn:contains(loantype, 'prop')}"><h1>DBS Property Loan</h1><input type="hidden" name="loan" value="prop" style="none"></c:if>
				<c:if test="${fn:contains(loantype, 'comm')}"><h1>DBS Commercial Loan</h1><input type="hidden" name="loan" value="comm" style="none"></c:if>
				<br/><br/>
				<b>Requested Loan Amount ($)</b>
				<p><input type="number" placeholder="Amount" name="loanAmount" id="loanAmount" required /></p>
				<b>Loan Tenure</b>
				<p><select name="loanPeriod" id="loanPeriod" style="text-align: center;" onchange="calc('<%= interest %>')">
					<option selected style="text-align: center; display: none;" disabled >-- select --</option>
					<option value="6" style="text-align: center;">6 months</option>
					<option value="12" style="text-align: center;">12 months</option>
			        <option value="24" style="text-align: center;">24 months</option>
				</select></p>
				<input type="hidden" name="interest" value="<%= interest %>" style="none">
				<b>Interest Rate*</b>
				<p><%= interest %>% p.a.</p>				
				<b>Installment Per Month*</b>
				<pre id="installment"> </pre>
				<p></p>
				<b>Processing Fee</b>
				<pre id="fees"> </pre>
				<b>Loan to be disbursed to</b>
				<p><select name="outgoingAcc">
					<c:forEach items="${ list1 }" var="user">
					    <option value="${user.getAccID()}">(${user.getAccID()}) - Balance: <fmt:formatNumber value = "${user.getBalance()}" type = "currency"/></option>
					</c:forEach>
				</select></p> 
				<b>Terms & Conditions</b>
				<p class="message"><input type="checkbox" name="something" style="width: 20px;">I have read the<a href="" style="border: none;">terms & conditions.</a></p>
				<br/><br/>
				<button type="submit">Submit</button> 
			</form>
   		</div>
	</div>
	
	
</body>
</html>