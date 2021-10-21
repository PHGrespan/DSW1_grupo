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
				<fmt:message key="adm.welcome" />
			</h1>
			<a href="${pageContext.request.contextPath}/index.jsp">
				<fmt:message key="exit.link" />
			</a>
			<br/>
			<br/>
			<h2>
				<a href="/<%=contextPath%>/adm/usuario/cadastro"> 
					<fmt:message key="user.create" />
				</a> 
				<h3><fmt:message key="user.list" /></h3>
			<br/>
			<table border="1">
				<tr>
					<th><fmt:message key="user.login" /></th>
					<th><fmt:message key="user.password" /></th>
					<th><fmt:message key="user.cpf" /></th>
					<th><fmt:message key="user.isAdm" /></th>
					<th><fmt:message key="user.nome" /></th>
					<th><fmt:message key="user.telefone" /></th>
					<th><fmt:message key="user.sexo" /></th>
					<th><fmt:message key="user.dataNasc" /></th>
				</tr>
				<c:forEach var="usuario" items="${requestScope.listaUsuarios}">
					<tr>
						<td><c:out value="${usuario.email}" /></td>
						<td><c:out value="${usuario.senha}" /></td>
						<td><c:out value="${usuario.cpf}" /></td>
						<td><c:out value="${usuario.isAdm}" /></td>
						<td><c:out value="${usuario.nome}" /></td>
						<td><c:out value="${usuario.telefone}" /></td>
						<td><c:out value="${usuario.sexo}" /></td>
						<td><c:out value="${usuario.dataNasc}" /></td>
						<td><p><a
							href="/<%= contextPath %>/adm/usuario/edicao?cpf=<c:out value='${usuario.cpf}' />">
								<fmt:message key="global.update" />
								</a>
							</p> 
						</td>
						<td>
                            <p>
                                <a href="/<%= contextPath %>/adm/usuario/remocao?cpf=<c:out value='${usuario.cpf}' />">
									<fmt:message key="global.delete" />
								</a>
							</p>
						</td>
					</tr>
				</c:forEach>
			</table>
			<h2>
				<a href="/<%=contextPath%>/adm/agencia/cadastro"> 
					<fmt:message key="agencia.create" />
				</a> 
				<h3><fmt:message key="agencia.list" /></h3>
				<br/>
				<table border="1">
					<tr>
						<th><fmt:message key="agencia.login" /></th>
						<th><fmt:message key="agencia.password" /></th>
						<th><fmt:message key="agencia.cnpj" /></th>
						<th><fmt:message key="agencia.nome" /></th>
						<th><fmt:message key="agencia.descricao" /></th>
					</tr>
					<c:forEach var="agencia" items="${requestScope.listaAgencias}">
						<tr>
							<td><c:out value="${agencia.email}" /></td>
							<td><c:out value="${agencia.senha}" /></td>
							<td><c:out value="${agencia.cnpj}" /></td>
							<td><c:out value="${agencia.nome}" /></td>
							<td><c:out value="${agencia.descricao}" /></td>
							<td><p><a
								href="/<%= contextPath %>/adm/agencia/edicao?cnpj=<c:out value='${agencia.cnpj}' />">
									<fmt:message key="global.update" />
									</a>
								</p> 
							</td>
							<td>
								<p>
									<a href="/<%= contextPath %>/adm/agencia/remocao?cnpj=<c:out value='${agencia.cnpj}' />">
										<fmt:message key="global.delete" />
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