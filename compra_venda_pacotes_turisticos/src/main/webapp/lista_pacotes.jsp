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
				<fmt:message key="pacote.principal" />
			</h1>
			<a href="${pageContext.request.contextPath}/index.jsp">
				<fmt:message key="exit.link" />
			</a>
			<br/>
			<br/>
			<br/>
			<a href="${pageContext.request.contextPath}/pacotes">
				<fmt:message key="lista.pacotes" />
			</a>
			<h3><fmt:message key="pacote.list" /></h3>
			<form method="post" action="${pageContext.request.contextPath}/pacotes/filtrar">
				<table>
					<td>
						<select name="tipo">
							<option value="agencia" ${filtro.tipo == "agencia" ? 'selected="selected"' : ''}><fmt:message key="global.agencia" /></option>
							<option value="destinos" ${filtro.tipo == "destinos" ? 'selected="selected"' : ''}><fmt:message key="pacote.destinos" /></option>
							<option value="data" ${filtro.tipo == "data" ? 'selected="selected"' : ''}><fmt:message key="pacote.datapartida" /></option>
						</select>
					</td>
					<td><input type="text" name="filtro" size="45" required value="<c:out value='${filtro.filtro}' />" /></td>
					<td colspan="2"> 
						<input type="submit" name="bOK" value="<fmt:message key="global.filtro" />">
					</td>
				</table>
			</form>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="pacote.nome" /></th>
					<%-- <th><fmt:message key="agencia.cnpj" /></th> --%>
					<th><fmt:message key="pacote.datapartida" /></th>
					<th><fmt:message key="pacote.duracao" /></th>
					<th><fmt:message key="pacote.valor" /></th>
					<th><fmt:message key="pacote.descricao" /></th>
					<th><fmt:message key="pacote.destinos" /></th>
					<th><fmt:message key="pacote.fotos" /></th>
					<th><fmt:message key="user.compra" /></th>
					
				</tr>
				<c:forEach var="pacote" items="${requestScope.listaPacotes}">
					<tr>
						<td><c:out value="${pacote.nome}" /></td>
						<%-- <td><c:out value="${pacote.cnpj}" /></td> --%>
						<td><c:out value="${pacote.dataPartida}" /></td>
						<td><c:out value="${pacote.duracao}" /></td>
						<td><c:out value="${pacote.valor}" /></td>
						<td><c:out value="${pacote.descricao}" /></td>
						<td><c:out value="${pacote.destinos}" /></td>
						<td><c:out value="${pacote.fotos}" /></td>
						<td>
							<p>
								<a href="/<%= contextPath %>/cliente/compra?nome=<c:out value='${pacote.nome}' />">
									<fmt:message key="user.compra" />
								</a>
							</p> 
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>
</html>