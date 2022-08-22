<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<main>
<fmt:bundle basename="message">
    <div class="texto-inicio">
        <h1>
            <fmt:message key="cliente.saudacao"/> ${sessionScope.usuarioLogado.nome},
        </h1>
        <p>
            <fmt:message key="cliente.texto"/>
        </p>
    </div>
    
    <div class="pacotes-container">
        <jsp:include page="listaPacotes.jsp">
            <jsp:param name="comprar" value="false" />
        </jsp:include>
    </div>
</fmt:bundle>
</main>
