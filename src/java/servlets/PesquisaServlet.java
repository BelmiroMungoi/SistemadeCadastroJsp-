package servlets;

import beans.BeansUsuario;
import dao.DaoUsuario;
import java.io.IOException;
import java.util.List;
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
@WebServlet("/PesquisaServlet")
public class PesquisaServlet extends HttpServlet {

    private DaoUsuario daoUsuario = new DaoUsuario();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pesquisa = request.getParameter("pesquisa");

        if (pesquisa != null) {
            List<BeansUsuario> lista = daoUsuario.pesquisaUsuario(pesquisa);
            RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
            request.setAttribute("usuarios",lista);
            view.forward(request, response);

        }
    }

}
