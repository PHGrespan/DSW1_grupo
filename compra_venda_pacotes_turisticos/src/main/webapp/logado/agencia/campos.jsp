<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${usuario != null}">
				<fmt:message key="user.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="user.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${pacote != null}">
		<input type="hidden" name="nome" value="<c:out value='${nome.cpf}' />" />
	</c:if>
	<tr>
		<td><label for="email"><fmt:message key="pacote.nome" /></label></td>
		<td><input type="text" name="nome" size="45" required value="<c:out value='${pacote.nome}' />" /></td>
	</tr>
	<tr>
		<td><label for="cpf"><fmt:message key="pacote.datapartida" /></label></td>
		<td><input type="text" name="dataPartida" size="45" required value="<c:out value='${pacote.dataPartida}' />" /></td>
	</tr>
	<tr>
		<td><label for="nome"><fmt:message key="pacote.duracao" /></label></td>
		<td><input type="text" name="duracao" size="20" required value="<c:out value='${pacote.duracao}' />" /></td>
	</tr>
	<tr>
		<td><label for="telefone"><fmt:message key="pacote.valor" /></label></td>
		<td><input type="text" name="valor" size="20" required value="<c:out value='${pacote.valor}' />" /></td>
	</tr>
	<tr>
		<td><label for="dataNasc"><fmt:message key="pacote.descricao" /></label></td>
		<td><input type="text" name="descricao" size="20" required value="<c:out value='${pacote.descricao}' />" /></td>
	</tr>
	<tr>
		<td><label for="dataNasc"><fmt:message key="pacote.destinos" /></label></td>
		<td><input type="text" name="destinos" size="20" required value="<c:out value='${pacote.destinos}' />" /></td>
	</tr>
	<tr>
		<td><label for="dataNasc"><fmt:message key="pacote.fotos" /></label></td>
		<td><input type="text" name="fotos" size="20" required value="<c:out value='${pacote.fotos}' />" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>