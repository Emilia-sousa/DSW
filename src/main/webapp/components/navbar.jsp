<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.ufscar.dc.dsw.domain.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<nav class="navbar-container">
<fmt:bundle basename="message">
    <a href="index.jsp"><img class="logo" src="assets/logo.svg" /></a>

    <c:choose>
        <c:when test="${sessionScope.usuarioLogado != null}">
            <div class="right-side">
                <a href="${pageContext.request.contextPath}/perfil" class="botao-logado">
                    <img src="assets/icons/person-circle.svg"/> ${sessionScope.usuarioLogado.nome}
                </a>
                <a href="${pageContext.request.contextPath}/logout" class="botao-logado">
                    <img src="assets/icons/exit.svg"/> <fmt:message key="nav.sair"/>
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="right-side">
                <a href="login.jsp" class="botao-login">
                    <fmt:message key="nav.login"/>
                </a>
                <a href="cadastro.jsp" class="botao-cadastro">
                    <fmt:message key="nav.cadastro"/>
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</fmt:bundle>
</nav>