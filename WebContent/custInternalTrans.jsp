<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@page import="bean.Accounts" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function availBal(){
var combine = document.getElementById('outgoingAcc').value;
var accarray = combine.split(",");
var balance = parseFloat(accarray[1]);


if(balance==0){
    document.getElementById("inputAmt").placeholder = "No Remaining Funds";
	document.getElementById('inputAmt').disabled = true;
	document.getElementById('submit').innerHTML = "No Remaining Funds";
	document.getElementById('submit').disabled = true;

}
else{
    document.getElementById("inputAmt").placeholder = "Input Transfer Amount";
	document.getElementById('inputAmt').disabled = false;
	document.getElementById('submit').innerHTML = "Transfer Funds";
	document.getElementById('submit').disabled = false;	

	
	
}

 var input = document.getElementById("inputAmt");
 
 input.setAttribute("max",parseFloat(balance).toFixed(2)); 

}



</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Internal Transfer</title>
<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);
body{
    background-image: url("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-4777.jpg");
  font-family: "Roboto", sans-serif;

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
List<Accounts> acclist= ref.getAccs(uID);
request.setAttribute("list1", acclist);
%>

<div class="login-page">
  <div class="form">
  <h3>INTERNAL TRANSFER</h3>
  
    <form class="login-form" action = "controllerServlet?type=transfer" method = "post" onsubmit="return confirm('Confirm Transfer?')">
          Transfer from<br>

<select id = "outgoingAcc" name = "outgoingAcc" onchange="availBal()">
<option selected style="text-align: center; display: none;" disabled >-- select --</option>

<c:forEach items="${ list1 }" var="user">
    <option value="${user.getAccID()},${user.getBalance()}">(${user.getAccID()}) - Balance: <fmt:formatNumber value = "${user.getBalance()}" type = "currency"/></option>
</c:forEach>
</select> <br/>
<p></p>

 to Account<br> 
<select name = "incomingAcc">
<option selected style="text-align: center; display: none;" disabled >-- select --</option>

<c:forEach items="${ list1 }" var="user">
    <option value="${user.getAccID()}">(${user.getAccID()}) - Balance: <fmt:formatNumber value = "${user.getBalance()}" type = "currency"/></option>
</c:forEach>
</select> <br/>
<p></p>
Transfer Amount<br><br>
<input id="inputAmt" type = "number"  step="any" placeholder = "Input Transfer Amount" min = "0.01" max="MyVar" name = "transAmt" required>
<p></p>
<button id = "submit" type ="submit">Transfer Funds</button>
<p></p>
      
    </form>
    
  </div>
</div>




</body>
</html>


