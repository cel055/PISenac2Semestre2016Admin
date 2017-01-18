<%-- 
    Document   : criaUsuario
    Created on : 07/02/2013, 23:25:56
    Author     : silvio
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Usuário</title>
        <link rel="stylesheet" type="text/css" href="/PISenac/admin/geral.css" />
    </head>
    <body>
        <div id="geral">
            <h1>Novo Usuário</h1>
            <p id="menuTopo">
                <a href="/PISenac/admin/index.jsp" style="margin: 10px 10px">Home</a>              
                <a href="/PISenac/admin/pesqUsuario.jsp" style="margin:10px 10px">Pesquisar Funcionário</a>
                <a href="/PISenac/ControleUsuario?cmd=pesqUsu" style="margin: 10px 10px">Novo Usuário</a>              
            </p>
            <form id="form" action="/PISenac/ControleUsuario?cmd=salvaUsuario" method="POST">
                <fieldset >
                    <input type="hidden" name="id" value="${idPessoa}" />
                    <legend>Novo Usuário</legend>
                    Nome: ${nome} <br /><br />
                    Perfil:
                    <select name="idPerfil">
                        <option>Selecione uma opção</option>
                        <c:forEach var="perfil" items="${perfis}">
                            <option value="${perfil.id}">${perfil.nome}</option>
                        </c:forEach>
                    </select> <br /><br />
                    Login:<input type="text" name="login" />
                </fieldset>
                <input value="Salvar" type="submit" />
                <input value="Limpar" type="reset" />
            </form>
        </div>
    </body>
</html>
