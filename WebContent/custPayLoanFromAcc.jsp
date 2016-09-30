<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@page import="bean.Loans" %>
<%@page import="bean.Accounts" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<script>
function showMin(){
	var combine = document.getElementById('loanid').value;
	var loanarray = combine.split(",");
	var minpay = loanarray[2];
	var a = (parseFloat(minpay)).toFixed(2);
	document.getElementById('minPayment').innerHTML = 'Minimum Monthly Sum: $'+a
	if(parseFloat(minpay)==0){
	    document.getElementById("inputAmt").placeholder = "No Outstanding Amount";
		document.getElementById('inputAmt').disabled = true;
		document.getElementById('submit').innerHTML = "No Outstanding Amount";
		document.getElementById('submit').disabled = true;

	}
	else{
	    document.getElementById("inputAmt").placeholder = "Input Repayment Amount";
		document.getElementById('inputAmt').disabled = false;
		document.getElementById('submit').innerHTML = "Pay Loan";
		document.getElementById('submit').disabled = false;	

		
	}

var input = document.getElementById("inputAmt");
input.setAttribute("max",parseFloat(loanarray[1]).toFixed(2)); 

}

</script>
<meta charset="ISO-8859-1">
<title>Loan Payments</title>

<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);
body{
  font-family: "Roboto", sans-serif;

    background-image: url("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-4777.jpg");

}

.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
      box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 3px 5px 5px 3px rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  width: 100%;
  border: 1;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #F31417;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
    transition: all .2s ease-in-out;
          box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 3px 5px 5px 3px rgba(0, 0, 0, 0.24);
    
}
.form button:hover,.form button:active,.form button:focus {
  background: #b30000;

}
.form .message {
  font-family: "Roboto", sans-serif;
 
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}
.form .message a {
  font-family: "Roboto", sans-serif;

  color: #F31417;
  text-decoration: none;
}
select{
        width:250px;
        margin:10px;
}
</style>
</head>
<body>




<%
String uID =(String)session.getAttribute("uID");
dao.Logic ref = new dao.Logic();
List<Loans> loanlist= ref.getLoans(uID);
request.setAttribute("list1", loanlist); 

List<Accounts> acclist= ref.getAccs(uID);
request.setAttribute("list2", acclist);

%>

<div class="login-page">
  <div class="form">
  <h3>LOAN PAYMENTS</h3>
  
    <form class="login-form" action = "controllerServlet?type=payLoan" method = "post" onsubmit="return confirm('Confirm repayment to Loan?')">
          Repay Loan:<br>
<select id = "loanid" name = "loanID" onchange="showMin()">
<option selected style="text-align: center; display: none;" disabled >-- select --</option>

<c:forEach items="${ list1 }" var="user">
 	<option value="${user.getLoanID()},${user.getLoanAmount()},${user.getMinPayment()}">(${user.getLoanID()}) - Loan Remaining: <fmt:formatNumber value = "${user.getLoanAmount()}" type = "currency"/></option>
</c:forEach>
</select> <br/>
<p></p>

 Pay with Account:<br> 
<select name = "payingAcc">
<option selected style="text-align: center; display: none;" disabled >-- select --</option>

<c:forEach items="${ list2 }" var="user">
    <option value="${user.getAccID()}${user.getBalance()}">(${user.getAccID()}) - Balance: <fmt:formatNumber value = "${user.getBalance()}" type = "currency"/></option>
</c:forEach>

</select> <br/>


<p></p>



<label id = "minPayment"></label>
<p></p>
<input id="inputAmt" type = "number"  step="any" placeholder = "Input Repayment Amount" min="0.01" max="MyVar" name = "repayAmt" required>
<p></p>
<button id = "submit"type ="submit">Pay Loan</button>
<p></p>
      
    </form>
    
  </div>
</div>




</body>
</html>