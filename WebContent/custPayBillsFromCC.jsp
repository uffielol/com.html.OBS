<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@page import="bean.CreditCards" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Payments</title>

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
%>

<div class="login-page">
  <div class="form">
  <h3>BILL PAYMENTS</h3>
  
    <form class="login-form" action = "controllerServlet?type=paybills" method = "post" onsubmit="return confirm('Confirm Billing?')">
          Pay with Credit Card:<br>

<select name = "payingAcc">
<option selected style="text-align: center; display: none;" disabled >-- select --</option>

<c:forEach items="${ list1 }" var="user">
    <option value="${user.getCcNum()}${user.getCcAvailLimit()}">(${user.getCcNum()}) - Credit Limit: <fmt:formatNumber value = "${user.getCcAvailLimit()}" type = "currency"/></option>
</c:forEach>
</select> <br/>
<p></p>

 to Billing Organization<br> 
<select name = "billOrg">
<option selected style="text-align: center; display: none;" disabled >-- select --</option>

    <option value="Singtel">Singtel</option>
    <option value="Starhub">Starhub</option>
    <option value="M1">M1</option>
    <option value="SP Services">SP Services</option>
    <option value="HDB">HDB</option>

</select> <br/>
<p></p>
Account Reference Number:<br><br>
<input type = "text"  placeholder = "Input Reference Number" name = "refNum" required>
<p></p>
Billing Amount<br><br>
<input type = "number"  step="any" placeholder = "Input Transfer Amount" name = "billAmt"  min = "0.01" required>
<p></p>
<button type ="submit">Pay Bill</button>
<p></p>
      
    </form>
    
  </div>
</div>




</body>
</html>