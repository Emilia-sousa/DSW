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
    <title> <fmt:message key="index.title" /> | James Vacation</title>
</head>
<body>
    <div class="home-container box">
        <jsp:include page="components/navbar.jsp" />
        <main>
            <div class="hero-container">
                <p>
                    <fmt:message key="index.text"/> 
                </p>
                <img src="assets/hero.png" />
            </div>
            <div class="pacotes-container">
                <form id="form" class="filtro" action="home" method="POST">
                    <input type="text" class="campo" name="destino" placeholder="<fmt:message key='index.destino'/>" value="${destino}" style="width: 350px;" />
                    <input type="text" class="campo" name="agencia" placeholder="<fmt:message key='index.agencia'/>" value="${agencia}" style="width: 350px;" />
                    <input type="text" class="campo" name="data-partida" placeholder="<fmt:message key='index.data'/>" value="${dataPartida}" onfocus="(this.type='date')" style="width: 250px;"/>
                    <input type="submit" id="submit" class="botao-filtrar" value="<fmt:message key='index.filtro'/>"/>
                </form>
                <jsp:include page="components/listaPacotes.jsp">
                    <jsp:param name="comprar" value="true" />
                </jsp:include>
            </div>
        </main>
    </div>

    <script type="text/javascript">
        const submit = document.getElementById("submit");
        const paths = location.pathname.split("/");
        if (paths[paths.length - 1] !== "home") {
            submit.click();
        }
    </script>
</body>

</fmt:bundle>
</html>
