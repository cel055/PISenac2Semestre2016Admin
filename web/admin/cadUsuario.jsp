<%-- 
    Document   : cadFuncionario
    Created on : 05/02/2013, 20:58:58
    Author     : silvio
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Usuário</title>
        <link rel="stylesheet" type="text/css" href="/PISenac/admin/geral.css" /> 
    </head>
    <body>
        <div id="geral">
            <h1>Cadastrando Usuário</h1>
            <p id="menuTopo">
                <a href="/PISenac/admin/index.jsp" style="margin: 10px 10px">Home</a>              
                <a href="/PISenac/admin/pesqUsuario.jsp" style="margin:10px 10px">Pesquisar Funcionário</a>
                <a href="/PISenac/ControleUsuario?cmd=pesqUsu" style="margin: 10px 10px">Novo Usuário</a>              
            </p>
            <form id="form" action="../ControleUsuario?cmd=pesqUsu" method="POST">
                <fieldset>
                    <legend>Pesquisa funcionário por nome</legend>
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
                        <th>Nome</th>                        
                        <th>CPF</th>
                        <th>E-mail</th>
                        <th>Criar Usuário</th>
                    </tr>
                    <c:forEach var="func" items="${funcionarios}">
                        <tr>
                            <td>
                                ${func.nome}
                            </td>
                            <td>
                                ${func.cpf}
                            </td>                            
                            <td>
                                ${func.email}
                            </td>                            
                            <td>
                                <a href="/PISenac/ControleUsuario?cmd=criaUsuario&id=${func.id}&nome=${func.nome}">Criar Usuário</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>



        </div>
    </body>
</html>
