package servlets;

import dao.DaoLogin;
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
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DaoLogin daoUsuario = new DaoLogin();

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {
            String nome = request.getParameter("nome");
            String pass = request.getParameter("pass");

            if (daoUsuario.validarLogin(nome, pass)) {
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("acessoLiberado.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("msgs", "Usuário ou Senha Inválidos!!!");
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
