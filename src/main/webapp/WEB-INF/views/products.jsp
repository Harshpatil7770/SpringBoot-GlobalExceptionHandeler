<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="menu.jsp" />
	<hr />

	<table border="1" align="center">
		<thead>
			<tr>
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Price</th>
				<th>Description</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.productId }</td>
					<td>${product.productName }</td>
					<td>${product.price }</td>
					<td>${product.description }</td>
					<td><a href="./findById?productId=${product.productId}">Update</a></td>
					<td><a href="./deleteProduct?productId=${product.productId}">Delete</a></td>
				</tr>

			</c:forEach>

		</tbody>

	</table>





</body>
</html>