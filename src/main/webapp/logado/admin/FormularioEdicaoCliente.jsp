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
    <title><fmt:message key='admin.editar'/> | James Vacation</title>
    <script type="text/javascript">
        var usuario = "<%= session.getAttribute("usuarioLogado")%>";
        if (!(usuario.getTipo().equals("Admin") || usuario === null) {
            window.location.href="index.jsp";
        }
    </script>
    
</head>
<body>
    <div class="cadastro-cliente-container box">
        <a class="botao-voltar" href="javascript:history.go(-1)"><img src="assets/icons/arrow-left.svg" />Voltar</a>
        <c:if test="${mensagens.existeErros}">
            <div class="form-erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>



        <form class="form-cadastro-cliente" action="updateCliente" method="POST" >
            <h1><fmt:message key='admin.editar'/></h1>
            <div class="campos">
                <div class="campo-container">
                    <label for="nome"><fmt:message key="cadastro.nome"/></label>
                    <input class="campo" id="nome" name="nome" type="text" value="${cliente.nome}"/>
                </div>
                <div class="campo-container">
                    <label for="email"><fmt:message key="cadastro.email"/></label>
                    <input class="campo" id="email" name="email" type="text" value="${cliente.email}"/>
                </div>
                <div class="campo-container">
                    <label for="cpf"><fmt:message key="cadastro.cpf"/></label>
                    <input class="campo" id="cpf" name="cpf" type="text" value="${cliente.cpf}"/>
                </div>
                <div class="campo-container">
                    <label for="telefone"><fmt:message key="cadastro.telefone"/></label>
                    <input class="campo" id="telefone" name="telefone" name="telefone" type="text" value="${cliente.telefone}"/>
                </div>
                <div class="campo-container">
                    <label for="sexo"><fmt:message key="cadastro.sexo"/></label>
                    <select class="campo" id="sexo" name="sexo" name="sexo" type="text" >
                        <option value="${cliente.sexo}" selected hidden><fmt:message key="cadastro.selecioneSexo"/></option>
                        <c:choose>
                            <c:when test="${sexo == 'masculino'}">
                                <option value="masculino" selected><fmt:message key="cadastro.masculino"/></option>
                                <option value="feminino"><fmt:message key="cadastro.feminino"/></option>
                            </c:when>
                            <c:when test="${sexo == 'feminino'}">
                                <option value="masculino"><fmt:message key="cadastro.masculino"/></option>
                                <option value="feminino" selected><fmt:message key="cadastro.feminino"/></option>
                            </c:when>
                            <c:otherwise>
                                <option value="masculino"><fmt:message key="cadastro.masculino"/></option>
                                <option value="feminino"><fmt:message key="cadastro.feminino"/></option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
                <div class="campo-container">
                    <label for="data-nascimento"><fmt:message key="cadastro.nascimento"/></label>
                    <input class="campo" id="data-nascimento" name="data-nascimento" type="date" value="${cliente.dataNascimento}"/>
                </div>
                <div class="campo-container">
                    <label for="senha"><fmt:message key="cadastro.senha"/></label>
                    <input class="campo" id="senha" name="senha" type="password"/>
                </div>
                <div class="campo-container">
                    <label for="confirmar-senha"><fmt:message key="cadastro.confirmaSenha"/></label>
                    <input class="campo" id="confirmar-senha" name="confirmar-senha" type="password"/>
                </div>
                <div class="campo-container">
                    <label for="id">ID</label>
                    <input class="campo" id="id" name="id" type="text" readonly="readonly" value="${cliente.id}"/>
                </div>
            </div>
            <input class="submit" type="submit" name="bOK" value="<fmt:message key='admin.editar'/>" />
        </form>
    </div>
</body>
</fmt:bundle>
</html>
