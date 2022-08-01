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
    <title><fmt:message key="addPacote.title"/> | James Vacation</title>
</head>
<body>
    <div class="cadastro-cliente-container box">
        <a class="botao-voltar" href="javascript:history.go(-1)"><img src="assets/icons/arrow-left.svg" /><fmt:message key="cadastro.voltar"/></a>
        <c:if test="${mensagens.existeErros}">
            <div class="form-erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <form class="form-cadastro-cliente" action="cadastroPacote" method="POST" enctype="multipart/form-data">
            <h1><fmt:message key="addPacote.title"/></h1>
            <div class="campos">
                <div class="campo-container">
                    <label for="cidade"><fmt:message key="pacote.cidade"/></label>
                    <input class="campo" id="cidade" name="cidade" type="text" value="${cidade}"/>
                </div>
                <div class="campo-container">
                    <label for="estado"><fmt:message key="pacote.estado"/></label>
                    <input class="campo" id="estado" name="estado" type="text" value="${estado}"/>
                </div>
                <div class="campo-container">
                    <label for="pais"><fmt:message key="pacote.pais"/></label>
                    <input class="campo" id="pais" name="pais" type="text" value="${pais}"/>
                </div>
                <div class="campo-container">
                    <label for="data-partida"><fmt:message key="pacote.dataPartida"/></label>
                    <input class="campo" id="data-partida" name="data-partida" type="date" value="${dataPartida}"/>
                </div>
                <div class="campo-container">
                    <label for="valor"><fmt:message key="pacote.valor"/></label>
                    <input class="campo" id="valor" name="valor" type="valor" value="${valor}"/>
                </div>
                <div class="campo-container">
                    <label for="duracao"><fmt:message key="pacote.duracao"/></label>
                    <input class="campo" id="duracao" name="duracao-dias" type="number" value="${duracaoDias}"/>
                </div>
                <div class="campo-container">
                    <label for="descricao"><fmt:message key="pacote.descricao"/></label>
                    <input type="file" id="descricao" name="descricao" value="${descricao}"/>
                </div>
                <div class="campo-container">
                    <label for="fotos"><fmt:message key="addPacote.fotos"/></label>
                    <input type="file" id="fotos" name="fotos" multiple />
                </div>
            </div>
            <input class="submit" type="submit" name="bOK" value="<fmt:message key='addPacote.botao'/>" />
        </form>
    </div>
</body>
</fmt:bundle>
</html>
