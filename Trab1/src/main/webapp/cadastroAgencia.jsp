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
    <title><fmt:message key="cadastro.title"/> | James Vacation</title>
    <script type="text/javascript">
        var usuario = "<%= session.getAttribute("usuarioLogado")%>";
        if (usuario !== "null") {
            window.location.href="index.jsp";
        }
    </script>
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
        <form class="form-cadastro-cliente" action="cadastroAgencia" method="POST" >
            <h1><fmt:message key="cadastro.title"/></h1>
            <div class="campos">
                <div class="campo-container">
                    <label for="nome"><fmt:message key="cadastro.nome"/></label>
                    <input class="campo" id="nome" name="nome" type="text" value="${nome}"/>
                </div>
                <div class="campo-container">
                    <label for="email"><fmt:message key="cadastro.email"/></label>
                    <input class="campo" id="email" name="email" type="email" value="${email}"/>
                </div>
                <div class="campo-container">
                    <label for="cnpj"><fmt:message key="cadastro.cnpj"/></label>
                    <input class="campo" id="cnpj" name="cnpj" type="text" value="${cnpj}"/>
                </div>
                <div class="campo-container">
                    <label for="senha"><fmt:message key="cadastro.senha"/></label>
                    <input class="campo" id="senha" name="senha" type="password"/>
                </div>
                <div class="campo-container">
                    <label for="descricao"><fmt:message key="cadastro.descricao"/></label>
                    <textarea class="campo" id="descricao" name="descricao" type="text">${descricao}</textarea>
                </div>
                <div class="campo-container">
                    <label for="confirmar-senha"><fmt:message key="cadastro.confirmaSenha"/></label>
                    <input class="campo" id="confirmar-senha" name="confirmar-senha" type="password"/>
                </div>
            </div>
            <input class="submit" type="submit" name="bOK" value="<fmt:message key='cadastro.cadastrar'/>" />
        </form>
    </div>
</body>
</fmt:bundle>
</html>
