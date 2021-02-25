package servlets;

import beans.BeansEndereco;
import beans.BeansUsuario;
import dao.DaoEndereco;
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
@WebServlet("/EnderecoServlet")
public class EnderecoServlet extends HttpServlet {

    private DaoUsuario dao = new DaoUsuario();
    private DaoEndereco daoEndereco = new DaoEndereco();
    private BeansEndereco endereco = new BeansEndereco();

    public EnderecoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String accao = request.getParameter("accao");
            String user = request.getParameter("user");
            BeansUsuario usuario = dao.consulta(user);

            if (accao.equals("delete")) {
                daoEndereco.excluir(user);
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("/cadastroEndereco.jsp");
                request.setAttribute("endereco", daoEndereco.listarEndereco());
                dispatcher.forward(request, response);

            } else if (accao.equals("edit")) {
                BeansEndereco beansEndereco = daoEndereco.consultaEndereco(user);
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("/cadastroEndereco.jsp");
                request.setAttribute("endereco", daoEndereco.listarEndereco());
                request.setAttribute("ender", beansEndereco);
                dispatcher.forward(request, response);

            } else if (accao.equals("listar")) {
                request.getSession().setAttribute("userEsco", usuario);
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("/cadastroEndereco.jsp");
                request.setAttribute("endereco", daoEndereco.listarEndereco());
                dispatcher.forward(request, response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BeansUsuario usuario = (BeansUsuario) request.getSession().getAttribute("userEsco");
        String idEnder = request.getParameter("idEnder");
        String idUser1 = request.getParameter("idUser1");
        String provincia = request.getParameter("provincia");
        String distrito = request.getParameter("distrito");
        String bairro = request.getParameter("bairro");
        String foneNr = request.getParameter("foneNr");
        String foneNr2 = request.getParameter("foneNr2");

        endereco.setIdEnder(!idEnder.isEmpty() ? Integer.parseInt(idEnder) : null);
        endereco.setEnderecoId(!idUser1.isEmpty() ? Integer.parseInt(idUser1) : null);
        endereco.setProvincia(provincia);
        endereco.setDistrito(distrito);
        endereco.setBairro(bairro);
        endereco.setMobile(foneNr);
        endereco.setMobile2(foneNr2);

        if (idEnder.isEmpty() || idEnder == null) {
            daoEndereco.salvarEndereco(endereco);
            
        } else if(!idEnder.isEmpty() || idEnder == null){
            daoEndereco.actualiza(endereco);
        }

        RequestDispatcher view
                = request.getRequestDispatcher("/cadastroEndereco.jsp");
        request.setAttribute("endereco", daoEndereco.listarEndereco());
        view.forward(request, response);

    }

}
