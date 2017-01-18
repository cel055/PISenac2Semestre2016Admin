<%-- 
    Document   : pesqFuncionario
    Created on : 05/02/2013, 23:41:05
    Author     : silvio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisando Usuário</title>
        <link rel="stylesheet" type="text/css" href="/PISenac/admin/geral.css" />
    </head>
    <body>
        <div id="geral">
            <h1>Pesquisando Usuário</h1>
            <div id="menuTopo">
                <a href="/PISenac/admin/index.jsp" style="margin: 10px 10px">Home</a>              
                <a href="/PISenac/admin/pesqUsuario.jsp" style="margin:10px 10px">Pesquisar Funcionário</a>
                <a href="/PISenac/ControleUsuario?cmd=pesqUsu" style="margin: 10px 10px">Novo Usuário</a>              
            </div>
            <form id="form" action="/PISenac/ControleUsuario?cmd=pesq" method="POST">
                <fieldset>
                    <legend>Pesquisa por login</legend>
                    <div >
                        <label>Nome:</label>
                        <input name="nome" type="text" size="35" />
                        <input value="Pesquisar" type="submit" />
                    </div>
                </fieldset>
            </form>

            <br /><br />
            <c:if test="${!empty funcionarios}">
                <table border="1">
                    <tr>
                        <th>Alterar</th>
                        <th>Nome</th>                        
                        <th>CPF</th>                        
                        <th>Login</th>                        
                        <th>E-mail</th>                        
                        <th>Perfil</th>
                        <th>Excluir</th>
                    </tr>
                    <c:forEach var="func" items="${funcionarios}">
                        <tr>
                            <td>
                                <a href="/PISenac/ControleUsuario?cmd=carregaUsu&id=${func.usuario.id}">Alterar</a>
                            </td>
                            <td>
                                ${func.nome}
                            </td>
                            <td>
                                ${func.cpf}
                            </td>
                            <td>
                                ${func.usuario.login}
                            </td>
                            <td>
                                ${func.email}
                            </td>
                            <td>
                                ${func.usuario.perfil.nome}
                            </td>                            
                            <td>
                                <a href="/PISenac/ControleUsuario?cmd=exclui&id=${func.usuario.id}">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </body>
</html>
