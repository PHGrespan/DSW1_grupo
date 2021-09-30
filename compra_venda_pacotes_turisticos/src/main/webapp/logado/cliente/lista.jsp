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
			<a href="${pageContext.request.contextPath}/logout.jsp">
				<fmt:message key="exit.link" />
			</a>
			<br/>
			<br/>
			<h2>
				<a href="/<%=contextPath%>/pacotes/"> 
					<fmt:message key="user.compra" />
				</a> 
				<h3><fmt:message key="user.listaPacotes" /></h3>
			<br/>
			<table border="1">
				<tr>
					<th><fmt:message key="user.cpf" /></th>
					<th><fmt:message key="pacote.nome" /></th>
					<th><fmt:message key="pacote.datapartida" /></th>
					<th><fmt:message key="pacote.duracao" /></th>
					<th><fmt:message key="pacote.valor" /></th>
					<th><fmt:message key="pacote.descricao" /></th>
					<th><fmt:message key="pacote.destinos" /></th>
					<th><fmt:message key="pacote.fotos" /></th>
				</tr>
				<c:forEach var="compra" items="${requestScope.listaPacotes}">
					<tr>
						<td><c:out value="${compra.cliente.cpf}" /></td>
						<td><c:out value="${compra.pacote.nome}" /></td>
						<td><c:out value="${compra.pacote.dataPartida}" /></td>
						<td><c:out value="${compra.pacote.duracao}" /></td>
						<td><c:out value="${compra.pacote.valor}" /></td>
						<td><c:out value="${compra.pacote.descricao}" /></td>
						<td><c:out value="${compra.pacote.destinos}" /></td>
						<td><c:out value="${compra.pacote.fotos}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>