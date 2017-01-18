<%-- 
    Document   : index
    Created on : 05/02/2013, 20:43:29
    Author     : silvio
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Senac - Seja bem vindo!</title>
        <link rel="stylesheet" type="text/css" href="/PISenac/admin/geral.css" />
    </head>
    <body>
        <div id="geral">
            <div id="topo" >
                <div id="usuario">Olá ${funcionario.nome}! <br />
                    <div style="text-align: center; width: 100%">SISTEMA HOTEL SENAC</div>
                </div>
            </div>
            <h1>Menu</h1>
            <c:if test="${!empty msg}">
                <script type="text/javascript">
                    alert('${msg}');
                </script>
            </c:if>
            <ul>
                <li><a href="/PISenac/admin/index.jsp">Home</a></li>
                <li><a href="/PISenac/admin/pesqFuncionario.jsp">Pesquisar Funcionário</a></li>
                <li><a href="/PISenac/admin/pesqUsuario.jsp">Pesquisar Usuário</a></li>
                <li><a href="/PISenac/ControleUsuario?cmd=sair">Sair</a></li>
            </ul>
        </div>
    </body>
</html>
