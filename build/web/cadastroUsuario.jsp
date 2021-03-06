<%-- 
    Document   : cadastroUsuario
    Created on : Dec 19, 2020, 1:50:30 PM
    Author     : Belmiro-Mungoi
--%>

<%@page import="beans.BeansUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Usuário</title>
        <link rel="stylesheet" href="css/cadastroUsuario.css">

    </head>
    <body>
        <header>
            <a href="acessoLiberado.jsp">
                <img src="css/img/logo00.png" height="150px" title="Home">
            </a>
            <a href="index.jsp">Sair</a>
        </header>
        <form action="UsuarioServlet" method="post" autocomplete="off" id="formUser"
              enctype="multipart/form-data">
            <h1>Cadastro de Usuário</h1>
            <center><h2>${msg}</h2></center>
            <div class="field">
                <label for="id">ID</label>
                <input type="text" readonly="readonly" id="idUser" name="idUser" value="${user.idUser}">
            </div>
            <div class="field-group">
                <div class="field">
                    <label for="nomeFull">Nome Completo</label>
                    <input type="text" id="nomeFull" name="nomeFull" value="${user.nomeCompleto}">
                </div>

                <div class="field">
                    <label for="biUser">BI</label>
                    <input type="text" id="biUser" name="biUser" value="${user.biUser}" maxlength="13">
                </div>
            </div>


            <div class="field-group">

                <div class="field">
                    <label for="foneUser">Telefone</label>
                    <input type="text" id="foneUser" name="foneUser" value="${user.telefone}" maxlength="13">
                </div>

                <div class="field">
                    <label for="nomeUser">Nome do Usuário</label>
                    <input type="text" id="nomeUser" name="nomeUser" value="${user.nomeUser}" maxlength="18">
                </div>

                <div class="field">
                    <label for="pass">Senha</label>
                    <input type="password" id="pass" name="pass" value="${user.senha}" maxlength="10">
                </div>
                <div class="field">
                    <label for="activo">Activo</label>
                    <input type="checkbox" id="activo" name ="activo"
                           <%
                               if (request.getAttribute("user") != null) {
                                   BeansUsuario user = (BeansUsuario) request.getAttribute("user");

                                   if (user.isActivo()) {
                                       out.print(" checked=\"checked\" ");
                                   }
                               }
                           %>       
                           >
                </div>

            </div>    
            <div class="field-group">
                <div class="field">
                    <label for="perfil">Perfil</label>
                    <select id="perfil" name="perfil">
                        <option value="empty">[--Selecione--]</option>  
                        <option value="admin"
                                <%
                                    if (request.getAttribute("user") != null) {
                                        BeansUsuario user = (BeansUsuario) request.getAttribute("user");

                                        if (user.getPerfil().equalsIgnoreCase("administrador")) {
                                            out.print(" selected=\"selected\" ");
                                        }
                                    }
                                %>
                                >Administrador</option>  
                        <option value="funcionario"
                                <%
                                    if (request.getAttribute("user") != null) {
                                        BeansUsuario user = (BeansUsuario) request.getAttribute("user");

                                        if (user.getPerfil().equalsIgnoreCase("funcionario")) {
                                            out.print(" selected=\"selected\" ");
                                        }
                                    }
                                %>
                                >Funcionario</option>
                        <option value="cliente"
                                <%
                                    if (request.getAttribute("user") != null) {
                                        BeansUsuario user = (BeansUsuario) request.getAttribute("user");

                                        if (user.getPerfil().equalsIgnoreCase("cliente")) {
                                            out.print(" selected=\"selected\" ");
                                        }
                                    }
                                %>
                                >Cliente</option>
                        <option value="estudante"
                                <%
                                    if (request.getAttribute("user") != null) {
                                        BeansUsuario user = (BeansUsuario) request.getAttribute("user");

                                        if (user.getPerfil().equalsIgnoreCase("estudante")) {
                                            out.print(" selected=\"selected\" ");
                                        }
                                    }
                                %>
                                >Estudante</option>
                        <option value="docente"
                                <%
                                    if (request.getAttribute("user") != null) {
                                        BeansUsuario user = (BeansUsuario) request.getAttribute("user");

                                        if (user.getPerfil().equalsIgnoreCase("docente")) {
                                            out.print(" selected=\"selected\" ");
                                        }
                                    }
                                %>
                                >Docente</option>
                    </select>
                </div>
                <div class="field">
                    <label for="foto">Foto</label>
                    <input type="file" id="foto" name="foto">
                </div>
                <div class="field">
                    <label for="curriculo">Curriculo</label>
                    <input type="file" id="curriculo" name="curriculo">
                </div>
            </div>    
            <button type="submit" id="salvar"
                    onclick="return validarCampos() ? true : false">Salvar</button>
            <button type="submit" id="canc" 
                    onclick="document.getElementById('formUser').action =
                                    'UsuarioServlet?accao=reset'">Cancelar
            </button>
        </form>
        <form method="post" action="PesquisaServlet" id="formPesquisa">
            <center>
                <div class="field-group" >
                    <div class="field">
                        <input type="text" id="pesquisa" name="pesquisa">
                    </div> 
                    <div class="field">
                        <button type="submit" id="pesquisar">Pesquisar</button>
                    </div>
                </div>
            </center>
        </form>               
    <center>
        <table id="table1">
            <caption>Usuários Cadastrados</caption>
            <tr>
                <th>ID</th>
                <th></th>
                <th></th>
                <th>Nome Completo</th>                
                <th>BI</th>
                <th>Telefone</th>
                <th>Nome Usuário</th>
                <th>Acções</th>
            </tr>
            <c:forEach items="${usuarios}" var="user">
                <tr id="tr2">
                    <td id="td1"><c:out value="${user.idUser}"></c:out></td>
                    <c:if test="${user.imagemMini.isEmpty() == false}"> 
                        <td id="td1"><a href="UsuarioServlet?accao=download&type=imagem&user=${user.idUser}">
                                <img src="<c:out value="${user.imagemMini}">                                         
                                     </c:out>" width="32px" height="32px"></a></td>
                            </c:if>
                            <c:if test="${user.imagemMini == null}">
                        <td><img src="css/img/userIcon.png" width="32px" height="32px"></td>
                        </c:if> 
                        <c:if test="${user.curriculo.isEmpty() == false}"> 
                        <td id="td1"><a href="UsuarioServlet?accao=download&type=curriculo&user=${user.idUser}">                                                               
                                <img src="css/img/pdfIcon.ico" width="35px" height="35px"></a></td>
                            </c:if>
                            <c:if test="${user.curriculo == null}">
                        <td><img src="css/img/pdf.png" width="32px" height="32px"></td>
                        </c:if>        
                    <td id="td1"><c:out value="${user.nomeCompleto}"></c:out></td>
                    <td id="td1"><c:out value="${user.biUser}"></c:out></td>
                    <td id="td1"><c:out value="${user.telefone}"></c:out></td>
                    <td id="td1"><c:out value="${user.nomeUser}"></c:out></td>
                        <td id="td1">
                            <a href="UsuarioServlet?accao=delete&user=${user.idUser}" onclick="return confirm('Deseja Excluir Esse Registo?')">
                            <img src="css/img/delete.png" width="20px" height="20px" title="Exlcuir"></a>
                        <a href="UsuarioServlet?accao=edit&user=${user.idUser}">
                            <img src="css/img/edit.png" width="20px" height="20px" title="Editar"></a>
                        <a href="EnderecoServlet?accao=listar&user=${user.idUser}">
                            <img src="css/img/adresss.png" width="20px" height="20px" title="Add Endereco"></a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </center>
    <script type="text/javascript">
        function validarCampos() {
            var nomeFull = document.getElementById("nomeFull").value;
            var bi = document.getElementById("biUser").value;
            var fone = document.getElementById("foneUser").value;
            var nomeUser = document.getElementById("nomeUser").value;
            var pass = document.getElementById("pass").value;

            if (nomeFull == '') {
                alert('Preencha o Campo Nome Completo!!!');
                return false;
            } else if (bi == '') {
                alert('Preencha o Campo BI!!!');
                return false;
            } else if (fone == '') {
                alert('Preencha o Campo Telefone!!!');
                return false;
            } else if (nomeUser == '') {
                alert('Preencha o Campo Nome de Usuário');
                return false;
            } else if (pass == '') {
                alert('Preencha o Campo Senha');
                return false;
            } else {
                document.getElementById('formUser').submit();
            }
            return true;
        }
    </script>
</body>
</html>
