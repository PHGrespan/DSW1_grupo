<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<fmt:bundle basename="message">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="page.title" /></title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
			String contextPath = request.getContextPath().replace("/", "");
		%>
        <p>
            <a href="/<%=contextPath%>/log.jsp"> 
			    <fmt:message key="login.cliente" />
            </a> 
        </p>
        <br>
        <p>
            <a href="/<%=contextPath%>/log_agencia.jsp"> 
                <fmt:message key="login.agencia" />
            </a>
        </p>
        <br>
        <p>
            <a href="/<%=contextPath%>/pacotes"> 
                <fmt:message key="lista.pacotes" />
            </a>
        </p>
    </body>
</fmt:bundle>
</html>