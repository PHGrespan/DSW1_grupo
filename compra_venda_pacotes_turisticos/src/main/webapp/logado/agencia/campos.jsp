<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${usuario != null}">
				<fmt:message key="users.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="users.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${agencia != null}">
		<input type="hidden" name="id" value="<c:out value='${agencia.id}' />" />
	</c:if>
	<tr>
		<td><label for="nome"><fmt:message key="cliente.nome" /></label></td>
		<td><input type="text" name="nome" size="20" required value="<c:out value='${agencia.nome}' />" /></td>
	</tr>
	<tr>
		<td><label for="email"><fmt:message key="user.email" /></label></td>
		<td><input type="text" name="email" size="20" required value="<c:out value='${agencia.email}' />" /></td>
	</tr>
	<tr>
		<td><label for="senha"><fmt:message key="user.senha" /></label></td>
		<td><input type="text" name="senha" size="20" required value="<c:out value='${agencia.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="isAdm"><fmt:message key="user.isAdm" />
		</label></td>
		<td>
			<select name="isAdm">
				<option value="ADM" ${agencia.isAdm == ADM ? 'selected="selected"' : ''}>ADM</option>
				<option value="CLI" ${agencia.isAdm == CLI ? 'selected="selected"' : ''}>CLI</option>
				<option value="AGE" ${agencia.isAdm == AGE ? 'selected="selected"' : ''}>AGE</option>
			</select>			
		</td>
	</tr>
	
	<tr>
		<td><label for="cnpj"><fmt:message key="user.cnpj" /></label></td>
		<td><input type="text" name="cnpj" size="45" required value="<c:out value='${agencia.cnpj}' />" /></td>
	</tr>
	<tr>
		<td><label for="descricao"><fmt:message key="agencia.desc" /></label></td>
		<td><input type="text" name="descricao" size="20" required value="<c:out value='${agencia.descricao}' />" /></td>
	</tr>
	
	<tr>
		<td colspan="2" align="center"><input type="submit" value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>