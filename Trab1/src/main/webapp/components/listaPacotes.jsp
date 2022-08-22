<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.ufscar.dc.dsw.util.Util" %>

<div class="lista-pacotes">
<fmt:bundle basename="message">
    <c:forEach var="pacote" items="${listaPacotes}">
        <div class="pacote">
            <div class="pacote-image" style="background-image: url('${pacote.fotos[0].url}');">
                <c:forEach var="foto" items="${pacote.fotos}">
                    <img src="${foto.url}"/>
                </c:forEach>
            </div>
            <div class="pacote-content">
                <div class="pacote-dados">
                    <strong>${pacote.destino.cidade}</strong>
                    <p> <fmt:message key="listapacote.datapartida"/> </p>
                    <p> ${Util.convertTimestampToString(pacote.dataPartida)} </p>
                </div>
                <c:choose>
                    <c:when test="${param.comprar}">
                        <div class="pacote-compra">
                            <span><fmt:message key="listapacote.moeda"/> ${pacote.valor}</span>
                            <a href="pacote?id=${pacote.id}"> <fmt:message key="listapacote.comprar"/> </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="pacote-ver">
                            <a href="pacote?id=${pacote.id}"> <fmt:message key="listapacote.detalhes"/> </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</fmt:bundle>
</div>
