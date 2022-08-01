<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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
    <div class="cadastro-container box">
        <a class="botao-voltar" href="javascript:history.go(-1)"><img src="assets/icons/arrow-left.svg" /><fmt:message key="cadastro.voltar"/></a>
        <img class="logo" src="assets/logo.svg" />
        <h1><fmt:message key="cadastro.title"/></h1>
        <div class="opcoes-usuario">
            <div class="opcao">
                <img src="assets/icons/airplane.svg"/>
                <a href="cadastroAgencia.jsp"><fmt:message key="cadastro.agencia"/></a>
            </div>
            <div class="opcao">
                <img src="assets/icons/person.svg"/>
                <a href="cadastroCliente.jsp"><fmt:message key="cadastro.cliente"/></a>
            </div>
        </div>
    </div>
</body>
</fmt:bundle>
</html>
