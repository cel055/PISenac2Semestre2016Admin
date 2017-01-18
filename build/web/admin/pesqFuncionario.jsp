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
        <title>Pesquisando Funcionário</title>
        <link rel="stylesheet" type="text/css" href="/PISenac/admin/geral.css" />
    </head>
    <body>
        <div id="geral">
            <h1>Pesquisando Funcionário</h1>
            <p id="menuTopo">
                <a href="/PISenac/admin/index.jsp" style="margin:10px 10px">Home</a>
                <a href="/PISenac/admin/pesqFuncionario.jsp" style="margin:10px 10px">Pesquisar Funcionário</a>
                <a href="../crudFuncionario?cmd=cadFu" style="margin: 10px 10px">Cadastro Funcionário</a>              
            </p>
            <form id="form" action="../crudFuncionario?cmd=pesq" method="POST">
                <fieldset>
                    <legend>Dados Pessoais</legend>
                    <div >
                        <label>Nome:</label>
                        <input name="nome"  type="text" size="35" />
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
                        <th>Telefone</th>
                        <th>Celular</th>
                        <th>E-mail</th>
                        <th>Excluir</th>
                    </tr>
                    <c:forEach var="fun" items="${funcionarios}">
                        <tr>
                            <td>
                                <a href="/PISenac/crudFuncionario?cmd=carregaFunc&id=${fun.id}">Alterar</a>
                            </td>
                            <td>
                                ${fun.nome}
                            </td>
                            <td>
                                ${fun.cpf}
                            </td>
                            <td>
                                ${fun.telefone}
                            </td>
                            <td>
                                ${fun.celular}
                            </td>
                            <td>
                                ${fun.email}
                            </td>
                            <td>
                                <a href="/PISenac/crudFuncionario?cmd=exclui&id=${fun.id}">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </body>
</html>
