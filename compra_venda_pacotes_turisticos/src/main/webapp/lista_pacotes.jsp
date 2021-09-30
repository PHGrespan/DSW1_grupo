<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
	<head>
<title><fmt:message key="page.title" /></title>
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="user.welcome" />
			</h1>
			<h3><fmt:message key="users.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="user.login" /></th>
					<th><fmt:message key="user.password" /></th>
					<th><fmt:message key="user.isADM" /></th>
				</tr>
				<c:forEach var="pacote" items="${requestScope.listaPacotes}">
					<tr>
						<td><c:out value="${pacote.nome}" /></td>
						<td><c:out value="${pacote.dataPartida}" /></td>
						<td><c:out value="${pacote.dataChegada}" /></td>
						<td><c:out value="${pacote.valor}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>
</html>