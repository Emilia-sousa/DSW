<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<fmt:bundle basename="message">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="login.title"/> | James Vacation</title>
</head>
<body>
    <c:set var="session" scope = "session" />
    <div class="login-container box">
        <a class="botao-voltar" href="javascript:history.go(-1)"><img src="assets/icons/arrow-left.svg" /><fmt:message key="cadastro.voltar" /></a>
        <img class="logo" src="assets/logo.svg" />
        <c:if test="${mensagens.existeErros}">
            <div class="form-erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <form class="form-login" action="login" method="POST">
            <h1><fmt:message key="login.title"/> </h1>
            <input type="text" name="email" class="campo" placeholder="<fmt:message key='login.email'/>" value="${email}"/>
            <input type="password" name="senha" class="campo" placeholder="<fmt:message key='login.senha'/>" />
            <input type="submit" name="bOK" value="<fmt:message key='login.botao'/>"/>
            <a href="cadastro.jsp"><fmt:message key='cadastro.title'/></a>
        </form>
    </div>
</body>
</fmt:bundle>
</html>
