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
			<h2>
				&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/agencia/cadastro"> 
					<fmt:message key="user.create" />
				</a> 
			</h2>
			<h3><fmt:message key="user.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="agencia.email" /></th>
					<th><fmt:message key="agencia.senha" /></th>
					<th><fmt:message key="agencia.cnpj" /></th>
					<th><fmt:message key="agencia.nome" /></th>
					<th><fmt:message key="agencia.desc" /></th>
					<th><fmt:message key="user.update" /></th>
					<th><fmt:message key="user.delete" /></th>
				</tr>
				<c:forEach var="agencia" items="${requestScope.listaAgencia}">
					<tr>
						<td><c:out value="${agencia.email}" /></td>
						<td><c:out value="${agencia.senha}" /></td>
						<td><c:out value="${agencia.cnpj}" /></td>
						<td><c:out value="${agencia.nome}" /></td>
						<td><c:out value="${agencia.descricao}" /></td>
						<td><p>
								<a href="/<%= contextPath %>/agencia/edicao?id=<c:out value='${agencia.cnpj}' />">
									<fmt:message key="user.update" />
								</a>
							</p> 
						</td>
						
						<td>
                            <p>
                                <a href="/<%= contextPath %>/usuarios/remocao?id=<c:out value='${usuario.id}' />">
									<fmt:message key="user.delete" />
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