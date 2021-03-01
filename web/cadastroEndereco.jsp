<%-- 
    Document   : cadastroEndereco
    Created on : Feb 14, 2021, 5:17:25 PM
    Author     : Belmiro-Mungoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Endereco</title>
        <link rel="stylesheet" href="css/cadastroEndereco.css">
    </head>
    <body>
        <header>
            <a href="acessoLiberado.jsp">
                <img src="css/img/logo00.png" height="150px" title="Home">
            </a>
            <a href="index.jsp">Sair</a>
        </header>
        <form action="EnderecoServlet" method="post" autocomplete="off" id="formEndereco"
              >
            <h1>Cadastro de Endereco</h1>
            <center><h2>${msg}</h2></center>
            <div class="field-group">                
                <div class="field">
                    <label for="idEnder">ID</label>
                    <input type="text" readonly="readonly" id="idEnder" name="idEnder" value="${ender.idEnder}">
                </div>

                <div class="field">
                    <label for="idUser1">ID do Usuario</label>
                    <input type="text" readonly="readonly" id="idUser1" name="idUser1" value="${userEsco.idUser}">
                </div>


                <div class="field">
                    <label for="nomeUser">Nome do Usuario</label>
                    <input type="text" readonly="readonly" id="nomeUser" name="nomeUser" value="${userEsco.nomeCompleto}">
                </div>
            </div>
            <div class="field-group">
                <div class="field">
                    <label for="nomeFull">Provincia</label>
                    <input type="text" id="provincia" name="provincia" value="${ender.provincia}">
                </div>

                <div class="field">
                    <label for="biUser">Distrito</label>
                    <input type="text" id="distrito" name="distrito" value="${ender.distrito}">
                </div>                
            </div>    
            
            <div class="field-group">
                <div class="field">
                    <label for="foneUser">Bairro</label>
                    <input type="text" id="bairro" name="bairro" value="${ender.bairro}">
                </div>
                
                <div class="field">
                    <label for="nomeUser">Nr. Telefone</label>
                    <input type="text" id="foneNr" name="foneNr" value="${ender.mobile}">
                </div>

                <div class="field">
                    <label for="pass">Nr. Telefone 2</label>
                    <input type="text" id="foneNr2" name="foneNr2" value="${ender.mobile2}">
                </div>

            </div>
            <button type="submit" id="salvar"
                    onclick="return validarCampos() ? true : false">Salvar</button>
                <button type="submit" id="voltar" onclick="document.getElementById('formEndereco').action
                            = 'EnderecoServlet?accao=voltar'">Voltar</button>
        </form>
    <center>
        <table id="table1">
            <caption>Enderecos Cadastrados</caption>
            <tr>
                <th>ID</th>
                <th>Provincia</th>
                <th>Distrito</th>
                <th>Bairro</th>
                <th>Telefone</th>
                <th>Nome Do Usuario</th>
                <th>Acções</th>
            </tr>
            <c:forEach items="${endereco}" var="ender">
                <tr id="tr2">
                    <td id="td1"><c:out value="${ender.idEnder}"></c:out></td>
                    <td id="td1"><c:out value="${ender.provincia}"></c:out></td>
                    <td id="td1"><c:out value="${ender.distrito}"></c:out></td>
                    <td id="td1"><c:out value="${ender.bairro}"></c:out></td>
                    <td id="td1"><c:out value="${ender.mobile}"></c:out></td>
                    <td id="td1"><c:out value="${ender.nomeCompleto}"></c:out></td>>
                        <td id="td1">
                            <a href="EnderecoServlet?accao=delete&user=${ender.idEnder}">
                            <img src="css/img/delete.png" width="20px" height="20px" title="Exlcuir"></a>
                        <a href="EnderecoServlet?accao=edit&user=${ender.idEnder}">
                            <img src="css/img/edit.png" width="20px" height="20px" title="Editar"></a>

                    </td>
                </tr>
            </c:forEach>

        </table>
    </center>

    <script type="text/javascript">
        function validarCampos() {
            var provincia = document.getElementById("provincia").value;
            var distrito = document.getElementById("distrito").value;
            var bairro = document.getElementById("bairro").value;
            var fone = document.getElementById("foneNr").value;


            if (provincia == '') {
                alert('Preencha o Campo Provincia!!!');
                return false;
            } else if (distrito == '') {
                alert('Preencha o Campo Distrito!!!');
                return false;
            } else if (bairro == '') {
                alert('Preencha o Campo Bairro!!!');
                return false;
            } else if (fone == '') {
                alert('Preencha o Campo Telefone');
                return false;
            } else {
                document.getElementById('formEndereco').submit();
            }
            return true;
        }
    </script>
</body>
</html>
