<%-- 
    Document   : logar
    Created on : 07/02/2013, 21:52:37
    Author     : silvio
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logando Sistema Hotel</title>
    </head>
    <body>
        <div style="margin: 100px auto; width: 300px;">
            <h1>Sistema Hotel Senac</h1>
            <c:if test="${!empty mens}">
                <script type="text/javascript">
                alert('${mens}');
                </script>
            </c:if>
            <form action="ControleUsuario?cmd=logar" method="POST">
                <label>Login:</label>
                <input name="login" type="text" /><br /><br />
                <label>Senha:</label>
                <input name="senha" type="password" /><br /><br />
                <input style="margin-left: 45px;" type="submit" value="Entrar"/>
                <input type="reset" value="Limpar"/>
            </form>
        </div>
    </body>
</html>
