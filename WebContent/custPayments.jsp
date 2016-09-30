<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Payments</title>
<script>
function showBill(){
	
	
	document.getElementById('payments').style.display='none'
		document.getElementById('billpayments').style.display='block'

}

function showLoan(){
	
	document.getElementById('payments').style.display='none'
		document.getElementById('loanpayments').style.display='block'
	
}

</script>
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
  border: 0;
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

</style>
</head>
<body>
<div class="login-page">
  <div class="form">
	
	<div id="payments">
	  <p>PAYMENTS</p>
	    <form action = "custPayCC.jsp" method = "post">
	
    <button type = "submit">Credit Card Payment</button>
    </form>
    <p></p>
    
    <button onclick = "showLoan()">Loan Payment</button>
    <p></p>
    
    <button onclick="showBill()">Bills Payment</button>
    <p></p>
    </div>
    
    <div id = "billpayments"  style="display:none">
      <p>BILL PAYMENTS</p> 
    <form action = "custPayBillsFromCC.jsp" method = "post">
    <button type = "submit">Pay With Credit Card</button>
    </form>
    <p></p>
    
  	<form action = "custPayBillsFromAcc.jsp" method = "post">
    <button type = "submit">Pay From Account</button>
    </form>
    <p></p>
    </div>
    
    
     <div id = "loanpayments"  style="display:none">
      <p>LOAN PAYMENTS</p>

    <form action = "custPayLoanFromCC.jsp" method = "post">
    <button type = "submit">Pay With Credit Card</button>
    </form>
    <p></p>
    
  <form action = "custPayLoanFromAcc.jsp" method = "post">
    <button type = "submit">Pay From Account</button>
    </form>
    <p></p>
    </div>
  
  </div>
</div>
</body>
</html>