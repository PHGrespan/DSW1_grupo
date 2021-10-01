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
				<fmt:message key="agencia.welcome" />
			</h1>
			<a href="${pageContext.request.contextPath}/index.jsp">
				<fmt:message key="exit.link" />
			</a>
			<br/>
			<br/>
			<h2>
				<a href="/<%=contextPath%>/agencia/cadastro"> 
					<fmt:message key="agencia.criar_pacotes" />
				</a> 
				<h3><fmt:message key="agencia.listaPacotes" /></h3>
			<br/>
			<table border="1">
				<tr>
					<th><fmt:message key="pacote.nome" /></th>
					<th><fmt:message key="agencia.cnpj" /></th>
					<th><fmt:message key="pacote.datapartida" /></th>
					<th><fmt:message key="pacote.duracao" /></th>
					<th><fmt:message key="pacote.valor" /></th>
					<th><fmt:message key="pacote.descricao" /></th>
					<th><fmt:message key="pacote.destinos" /></th>
					<th><fmt:message key="pacote.fotos" /></th>
				</tr>
				<c:forEach var="pacotes" items="${requestScope.listaPacotes}">
					<tr>
						<td><c:out value="${pacotes.nome}" /></td>
						<td><c:out value="${pacotes.agencia.cnpj}" /></td>
						<td><c:out value="${pacotes.dataPartida}" /></td>
						<td><c:out value="${pacotes.duracao}" /></td>
						<td><c:out value="${pacotes.valor}" /></td>
						<td><c:out value="${pacotes.descricao}" /></td>
						<td><c:out value="${pacotes.destinos}" /></td>
						<td><c:out value="${pacotes.fotos}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>