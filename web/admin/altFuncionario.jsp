<%-- 
    Document   : cadFuncionario
    Created on : 05/02/2013, 20:58:58
    Author     : silvio
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Funcionário</title>
       <link rel="stylesheet" type="text/css" href="/PISenac/admin/geral.css" /> 
    </head>
    <body>
        <jsp:useBean class="br.com.hotelProf.bean.Funcionario" id="funcionario" scope="request" />
        <div id="geral">
            <h1>Cadastrando Funcionário</h1>
            <p id="menuTopo">
                <a href="/PISenac/admin/index.jsp" style="margin:10px 10px">Home</a>
                <a href="/PISenac/admin/pesqFuncionario.jsp" style="margin:10px 10px">Pesquisar Funcionário</a>
                <a href="../crudFuncionario?cmd=cadFu" style="margin: 10px 10px">Cadastro Funcionário</a>              
            </p>
            <form id="form" action="/PISenac/crudFuncionario?cmd=alterar" method="POST">
                <fieldset class="fieldsetEsquerdo">
                    <legend>Dados Pessoais</legend>
                    <div class="formataFormulario">
                        <input type="hidden" name="id" value="${funcionario.id}"/>
                        <label>Nome:</label>
                        <input name="nome" type="text" value="${funcionario.nome}" size="35" />
                        <label>CPF:</label>
                        <input name="cpf" type="text" value="${funcionario.cpf}" /> 
                        <label>RG:</label>
                        <input name="rg" type="text" value="${funcionario.rg}"/> 
                        <label>Telefone:</label>
                        <input name="telefone" type="text" value="${funcionario.telefone}"/> 
                        <label>Celular:</label>
                        <input name="celular" type="text" value="${funcionario.celular}"/> 
                        <label>E-mail:</label>
                        <input name="email" type="text" size="35" value="${funcionario.email}"/> 
                        <label>Nascimento:</label>
                        <input type="text" name="nascimento" id="nascimento" value="<fmt:formatDate value='${funcionario.nascimento }' pattern='dd/MM/yyyy'/>"/>
                        <label>Salário:</label>
                        <input name="salario" type="text" value="${funcionario.salario}"/> 
                    </div>
                    <label>Sexo:</label>
                    <input name="sexo" type="radio" value="Masculino"/>Masculino
                    <input name="sexo" type="radio" value="Feminino"/>Feminino
                </fieldset>


                <fieldset class="fieldsetDireito">
                    <legend>Localização</legend>
                    <div class="formataFormulario">
                        <label>Rua:</label>
                        <input name="rua" type="text" size="40" value="${endereco.rua}"/> <br />
                        <label>Bairro:</label>
                        <input name="bairro" type="text" size="25" value="${endereco.bairro}" /> <br />
                        <label>Cidade:</label>
                        <input name="cidade" type="text" size="40" value="${endereco.cidade}"/> <br />
                        <label>Estado:</label>
                        <input name="estado" type="text" size="25" value="${endereco.estado}"/> <br />
                        <label>Sigla:</label>
                        <input name="sigla" type="text" size="10" value="${endereco.sigla}" /> <br />
                        <label>CEP:</label>
                        <input name="cep" type="text" value="${endereco.cep}" /> <br />
                        <label>Pais:</label>
                        <input name="pais" type="text"  value="${endereco.pais}"/> <br />
                    </div>
                </fieldset>
                <fieldset class="limpaFloat">
                    <legend>Profissional</legend>
                    Função:
                    <select name="idFuncao">                        
                        <option value="${funcionario.funcao.id}">${funcionario.funcao.tipo}</option>
                        <c:forEach var="funcao" items="${funcoes}">
                            <option value="${funcao.id}">${funcao.tipo}</option>
                        </c:forEach>
                    </select>
                    Turno:
                    <select name="idTurno" >
                        <option value="${funcionario.turno.id}">${funcionario.turno.nome}</option>
                        <c:forEach var="turno" items="${turnos}">
                            <option value="${turno.id}">${turno.nome}</option>
                        </c:forEach>
                    </select>
                </fieldset>
                <input value="Salvar" type="submit" />
                <input value="Limpar" type="reset" />
            </form>
        </div>
    </body>
</html>
