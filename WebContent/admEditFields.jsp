<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="admEdit.jsp" />

	<br/><br/>  
	
	<form action="controllerServlet">
		<table>
			<tr style="text-align: left">
				<th>Name</th>
				<th>Password</th>
				<th>Address</th>
				<th>Email</th>
				<th>Phone</th>
			</tr> 
			<tr>
				<td><input type="text" value="${selected.getName()}" name="name"></td>
				<td><input type="text" value="${selected.getPassword()}" name="password"></td>
				<td><input type="text" value="${selected.getAddress()}" name="address" style="width: 350px;"></td>
				<td><input type="text" value="${selected.getEmail()}" name="email"></td>
				<td><input type="text" value="${selected.getTelNo()}" name="telNo">
					<input type="hidden" value="${selected.getInit()}" name="init">
					<input type="hidden" value="${selected.getuID()}" name="uID"></td>
			</tr>
		</table>
	
		<br/><br/> 
		
		<table>
			<tr style="text-align: left">
				<th>Credit Card Number</th>
				<th>Limit</th>
				<th>Points</th>
			</tr>
			<c:forEach items="${ ccList }" var="ccs"> 
				<tr>
					<td>${ccs.getCcNum()}</td>
					<td><input type="text" value="${ccs.getCcLimit()}" name="ccLimit"></td>
					<td><input type="text" value="${ccs.getCcPoints()}" name="ccPoints">	
						<input type="hidden" value="${ccs.getCcNum()}" name="ccNum"></td>
				</tr>
			</c:forEach>
		</table>
		
		<br/><br/> 
		
		<input type="submit" name="type" value="Update">
	</form>

</body>
</html>