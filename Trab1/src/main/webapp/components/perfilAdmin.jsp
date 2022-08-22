<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.ufscar.dc.dsw.util.Util" %>

<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

<main>
<fmt:bundle basename="message">
    <div class="texto-inicio">
        <h1>
            Olá ${sessionScope.usuarioLogado.nome}
        </h1>
    </div>
    
	<div>
		<table>
			<caption><fmt:message key="admin.listaClientes"/></caption>
			<tr>
				<th>ID</th>
				<th><fmt:message key="cadastro.nome"/></th>
				<th><fmt:message key="cadastro.cpf"/></th>
				<th><fmt:message key="cadastro.email"/></th>
				<th><fmt:message key="cadastro.sexo"/></th>
				<th><fmt:message key="cadastro.telefone"/></th>
				<th><fmt:message key="cadastro.nascimento"/></th>
				<th><fmt:message key="admin.editar"/></th>
				<th><fmt:message key="admin.remover"/></th>

			</tr>
			<c:forEach var="Cliente" items="${requestScope.listaCliente}">
				<tr>
					<td>${Cliente.id}</td>
					<td>${Cliente.nome}</td>
					<td>${Cliente.cpf}</td>
					<td>${Cliente.email}</td>
					<td>${Cliente.sexo}</td>
					<td>${Cliente.telefone}</td>
                    <td>${Util.convertTimestampToString(Cliente.dataNascimento)}</td>
					<td><a href="admin/edicaoUsuario?id=${Cliente.id}"><fmt:message key="admin.editar"/></a></td>
					<td><a href="admin/remocaoUsuario?id=${Cliente.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este usuário?');"><fmt:message key="admin.remover"/></a></td>

			    </tr>
            </c:forEach>
		</table>
	</div>

	<div>
        <table>
			<caption style="margin-top: 30px;"><fmt:message key="admin.listaAgencias"/></caption>
			<tr>
				<th>ID</th>
				<th><fmt:message key="cadastro.nome"/></th>
				<th><fmt:message key="cadastro.cnpj"/></th>
				<th><fmt:message key="cadastro.email"/></th>
				<th><fmt:message key="admin.editar"/></th>
				<th><fmt:message key="admin.remover"/></th>

			</tr>
			<c:forEach var="Agencia" items="${requestScope.listaAgencia}">
				<tr>
                <td>${Agencia.id}</td>
					<td>${Agencia.nome}</td>
					<td>${Agencia.cnpj}</td>
					<td>${Agencia.email}</td>
                    <td><a href="admin/edicaoUsuario?id=${Agencia.id}"><fmt:message key="admin.editar"/></a></td>
                    <td><a href="admin/remocaoUsuario?id=${Agencia.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este usuário?');"><fmt:message key="admin.remover"/></a></td>
			    </tr>
			</c:forEach>
		</table>
	</div>
</fmt:bundle>
</main>
