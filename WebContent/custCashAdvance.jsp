<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@page import="bean.Accounts" %>
<%@page import="bean.CreditCards" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<script>
function showAvailLimit(){
var combine = document.getElementById('ccNum').value;
var ccarray = combine.split(",");
var maxCashAdvance = parseFloat(ccarray[1]);
var a = maxCashAdvance.toFixed(2);
document.getElementById('maxCashAdvance').innerHTML = 'Maximum Cash Advance Amount: <br>$'+a


if(maxCashAdvance==0){
    document.getElementById("inputAmt").placeholder = "No Remaining Credit";
	document.getElementById('inputAmt').disabled = true;
	document.getElementById('submit').innerHTML = "No Remaining Credit";
	document.getElementById('submit').disabled = true;

}
else{
    document.getElementById("inputAmt").placeholder = "Input Cash Advance Amount";
	document.getElementById('inputAmt').disabled = false;
	document.getElementById('submit').innerHTML = "Deposit Cash Advance";
	document.getElementById('submit').disabled = false;	

	
	
}

 var input = document.getElementById("inputAmt");
 
 input.setAttribute("max",parseFloat(maxCashAdvance).toFixed(2)); 

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

List<CreditCards> CClist= ref.getCCs(uID);
request.setAttribute("list1", CClist);

List<Accounts> accList= ref.getAccs(uID);
request.setAttribute("list2", accList); 

%>

<div class="login-page">
  <div class="form">
  <h3>CASH ADVANCE</h3>
  
    <form class="login-form" action = "controllerServlet?type=payCC" method = "post" onsubmit="return confirm('Confirm Cash Advance?')">
          Choose Credit Card for Cash Advance:<br>
          
<select id = "ccNum" name = "ccNum" onchange="showAvailLimit()" required>
<option selected style="text-align: center; display: none;" disabled >-- select --</option>

<c:forEach items="${ list1 }" var="user">
    <option value="${user.getCcNum()},${user.getCcAvailLimit()}">${user.getCcNum()}</option>
</c:forEach>
</select> <br/>
<p></p>

 Deposit into Account:<br> 
<select name = "depositAcc" required>
<option selected style="text-align: center; display: none;" disabled >-- select --</option>

<c:forEach items="${ list2 }" var="user">
    <option value="${user.getAccID()}">(${user.getAccID()}) - Balance: <fmt:formatNumber value = "${user.getBalance()}" type = "currency"/></option>
</c:forEach>

</select> <br/>


<p></p>



<label id = "maxCashAdvance"></label>
<p></p>
<input id="inputAmt" type = "number"  step="any" placeholder = "Input Payment Amount" min = "0.01" max="MyVar" name = "depositAmt" required>
<p></p>
<button id = "submit" type ="submit">Deposit Cash Advance</button>
<p></p>
      
    </form>
    
  </div>
</div>




</body>
</html>