<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
  <title>Payb.in Transaction Manager</title>
  <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
  <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>

<body>

  <h1>Payb.in Transaction Manager: Last 10 Transactions</h1>

<table class="table table-striped">
<thead>
	<tr>
		<th>Date</th>
		<th>Order ID</th>
		<th>Cardholder Name</th>
		<th>Card Type</th>
		<th>Amount</th>
	</tr>
</thead>	
<c:forEach items="${transactions}" var="transaction">
<tr>
	<td><c:out value="${transaction.transactionDate}" /></td>
	<td><c:out value="${transaction.orderId}" /></td>
	<td><c:out value="${transaction.cardholderName}" /></td>
	<td><c:out value="${transaction.cardType}" /></td>
	<td><c:out value="${transaction.amount}" /> <c:out value="${transaction.currency}" /></td>
</tr>
</c:forEach>
</table>
      

</body>
</html>
