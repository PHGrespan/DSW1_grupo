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
				<a href="/<%=contextPath%>/usuarios/cadastro"> 
					<fmt:message key="user.create" />
				</a> 
			</h2>
			<h3><fmt:message key="user.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="user.login" /></th>
					<th><fmt:message key="user.password" /></th>
					<th><fmt:message key="user.isAdm" /></th>
					<th><fmt:message key="user.nome" /></th>
					<th><fmt:message key="user.telefone" /></th>
					<th><fmt:message key="user.sexo" /></th>
					<th><fmt:message key="user.dataNasc" /></th>
					<th><fmt:message key="user.update" /></th>
					<th><fmt:message key="user.delete" /></th>
				</tr>
				<c:forEach var="usuario" items="${requestScope.listaUsuarios}">
					<tr>
						<td><c:out value="${usuario.email}" /></td>
						<td><c:out value="${usuario.senha}" /></td>
						<td><c:out value="${usuario.isAdm}" /></td>
						<td><c:out value="${usuario.nome}" /></td>
						<td><c:out value="${usuario.telefone}" /></td>
						<td><c:out value="${usuario.sexo}" /></td>
						<td><c:out value="${usuario.dataNasc}" /></td>
						<td><p><a
							href="/<%= contextPath %>/usuarios/edicao?id=<c:out value='${usuario.id}' />">
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