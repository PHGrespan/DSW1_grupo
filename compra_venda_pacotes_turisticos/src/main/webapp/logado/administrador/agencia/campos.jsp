<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${agencia != null}">
				<fmt:message key="user.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="agencia.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${agencia != null}">
		<input type="hidden" name="cnpj" value="<c:out value='${agencia.cnpj}' />" />
	</c:if>
	<tr>
		<td><label for="email"><fmt:message key="agencia.login" /></label></td>
		<td><input type="text" name="email" size="45" required value="<c:out value='${agencia.email}' />" /></td>
	</tr>
	<tr>
		<td><label for="senha"><fmt:message key="agencia.password" /></label></td>
		<td><input type="text" name="senha" size="20" required value="<c:out value='${agencia.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="nome"><fmt:message key="agencia.nome" /></label></td>
		<td><input type="text" name="nome" size="20" required value="<c:out value='${agencia.nome}' />" /></td>
	</tr>
	<tr>
		<td><label for="descricao"><fmt:message key="agencia.descricao" /></label></td>
		<td><input type="text" name="descricao" size="20" required value="<c:out value='${agencia.descricao}' />" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>