package servlets;

import beans.BeansUsuario;
import dao.DaoUsuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Belmiro-Mungoi
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    DaoUsuario daoUsuario = new DaoUsuario();

    public UsuarioServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String accao = request.getParameter("accao");
            String id = request.getParameter("user");

            if (accao.equals("delete")) {
                daoUsuario.excluir(id);
                RequestDispatcher view
                        = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listarUsuario());
                view.forward(request, response);

            } else if (accao.equals("edit")) {
                BeansUsuario beansUsuario = daoUsuario.consulta(id);
                RequestDispatcher view
                        = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("user", beansUsuario);
                view.forward(request, response);
            } else if (accao.equals("listar")) {
                RequestDispatcher view
                        = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listarUsuario());
                view.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean podeInserir = true;
        String accao = request.getParameter("accao");
        //Cancela e reseta a pag
        if (accao != null && accao.equals("reset")) {
            try {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listarUsuario());
                view.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            String idUsuario = request.getParameter("idUser");
            String nomeComp = request.getParameter("nomeFull");
            String biUsuario = request.getParameter("biUser");
            String fone = request.getParameter("foneUser");
            String nomeUsuario = request.getParameter("nomeUser");
            String senha = request.getParameter("pass");
            BeansUsuario usuario = new BeansUsuario();

            usuario.setIdUser(!idUsuario.isEmpty() ? Integer.parseInt(idUsuario) : null);
            usuario.setNomeCompleto(nomeComp);
            usuario.setBiUser(biUsuario);
            usuario.setTelefone(fone);
            usuario.setNomeUser(nomeUsuario);
            usuario.setSenha(senha);

            if (nomeComp == null || nomeComp.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Nome Completo!");
                podeInserir = false;

            } else if (biUsuario == null || biUsuario.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo BI!");
                podeInserir = false;

            } else if (fone == null || fone.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Telefone!");
                podeInserir = false;

            } else if (nomeUsuario == null || nomeUsuario.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Nome do Usuário!");
                podeInserir = false;

            } else if (senha == null || senha.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Senha!");
                podeInserir = false; 

            } else if (idUsuario == null || idUsuario.isEmpty()
                    && !daoUsuario.validaUsuario(nomeUsuario)) {
                request.setAttribute("msg", "Nome de Usuario ja existe, TENTE OUTRO!!!");
                podeInserir = false;

            } else if (idUsuario == null || idUsuario.isEmpty()
                    && !daoUsuario.validaSenha(senha)) {
                request.setAttribute("msg", "Essa senha ja existe, TENTE OUTRA!!!");
                podeInserir = false;

            } else if (idUsuario == null || idUsuario.isEmpty()
                    && daoUsuario.validaUsuario(nomeUsuario) && podeInserir) {
                daoUsuario.salvarUsuario(usuario);

            } else if (idUsuario != null && !idUsuario.isEmpty()) {

                if (!daoUsuario.validaUsuarioUpdate(nomeUsuario, idUsuario)) {
                    request.setAttribute("msg", "Nome de Usuario ja existe, TENTE OUTRO!!!");
                    podeInserir = false;

                } else if (!daoUsuario.validaSenhaUpdate(senha, idUsuario)) {
                    request.setAttribute("msg", "Essa Senha Já Existe, TENTE OUTRA!!!");
                    podeInserir = false;

                } else if (podeInserir) {
                    daoUsuario.actualiza(usuario);
                }

            }

            if (!podeInserir) {
                request.setAttribute("user", usuario);
            }

            try {
                // Para continuar na mesma pagina apos uma accao
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                // exibir dados na tela
                request.setAttribute("usuarios", daoUsuario.listarUsuario());
                view.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
