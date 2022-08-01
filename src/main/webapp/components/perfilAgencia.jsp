<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
<fmt:bundle basename="message">
    <div class="header-container">
        <div class="texto-inicio">
            <h1>
                <fmt:message key="agencia.saudacao"/> ${sessionScope.usuarioLogado.nome},
            </h1>
            <p>
                <fmt:message key="agencia.text"/>
            </p>
        </div>
        <a href="adicionarPacote.jsp" type="submit" class="botao-adicionarpacote">
            <fmt:message key="agencia.addPacote"/>
        </a>
    </div>
    
    <div class="pacotes-container">
        <c:choose>
            <c:when test="${param.vigente == '1'}">
                <a type="submit" class="botao-pacotesvigentes" href="?">
                    <fmt:message key="agencia.pacotes"/>
                </a>
            </c:when>
            <c:otherwise>
                <a type="submit" class="botao-pacotesvigentes" href="?vigente=1">
                    <fmt:message key="agencia.pacotesVigentes"/>
                </a>
            </c:otherwise>
        </c:choose>
        <jsp:include page="listaPacotes.jsp">
            <jsp:param name="comprar" value="false" />
        </jsp:include>
    </div>
</fmt:bundle>
</main>
