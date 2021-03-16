package servlets;

import beans.BeansProduto;
import dao.DaoCategoria;
import dao.DaoProduto;
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
@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {

    DaoProduto daoProduto = new DaoProduto();
    DaoCategoria daoCategoria = new DaoCategoria();

    public ProdutoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accao = request.getParameter("accao") != null ? request.getParameter("accao") : "listar";
        String id = request.getParameter("prod");

        RequestDispatcher view
                = request.getRequestDispatcher("/cadastroProduto.jsp");
        request.setAttribute("categorias", daoCategoria.listarCategoria());
        if (accao.equals("delete")) {
            daoProduto.excluir(id);
            request.setAttribute("produtos", daoProduto.listarProduto());

        } else if (accao.equals("edit")) {
            BeansProduto beansProduto = daoProduto.consulta(id);
            request.setAttribute("prod", beansProduto);
            request.setAttribute("produtos", daoProduto.listarProduto());

        } else if (accao.equals("listar")) {
            request.setAttribute("produtos", daoProduto.listarProduto());

        }
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accao = request.getParameter("accao");

        if (accao != null && accao.equals("reset")) {
            RequestDispatcher view
                    = request.getRequestDispatcher("/cadastroProduto.jsp");
            request.setAttribute("produtos", daoProduto.listarProduto());
            request.setAttribute("categorias", daoCategoria.listarCategoria());
            view.forward(request, response);
        } else {

            String idProd = request.getParameter("idProd");
            String nomeProd = request.getParameter("nomeProd");
            String quantProd = request.getParameter("quantProd");
            String valorProd = request.getParameter("valorProd");
            String categoriaId = request.getParameter("categorias");

            BeansProduto produto = new BeansProduto();
            produto.setIdProd(!idProd.isEmpty() ? Long.parseLong(idProd) : null);
            produto.setNomeProd(nomeProd);
            produto.setCategoriaId(Integer.parseInt(categoriaId));

            if (quantProd != null && !quantProd.isEmpty()) {
                produto.setQuantProd(Integer.parseInt(quantProd));

            }
            if (valorProd != null && !valorProd.isEmpty()) {
                String valor = valorProd.replaceAll("\\,", "");
                //valor = valor.replaceAll("\\", ".");
                produto.setValorProd(Float.parseFloat(valor));

            }
            boolean podeInserir = true;

            if (nomeProd == null || nomeProd.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Nome do Produto!");
                podeInserir = false;

            } else if (quantProd == null || quantProd.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Quantidade!");
                podeInserir = false;

            } else if (valorProd == null || valorProd.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Valor!");
                podeInserir = false;

            } else if (idProd == null || idProd.isEmpty()
                    && !daoProduto.validarProduto(nomeProd)) {
                request.setAttribute("msg", "Nome de Produto ja Existe!!!");
                podeInserir = false;

            } else if (idProd == null || idProd.isEmpty()
                    && daoProduto.validarProduto(nomeProd) && podeInserir) {
                daoProduto.salvarProduto(produto);

            } else if (idProd != null && !idProd.isEmpty()) {

                if (!daoProduto.validarProdutoUpdate(nomeProd, idProd)) {
                    request.setAttribute("msg", "Nome de Produto ja Existe!!!");
                    podeInserir = false;

                } else if (podeInserir) {
                    daoProduto.actualiza(produto);
                }

            }

            if (!podeInserir) {
                request.setAttribute("prod", produto);
            }

            try {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");// para continuar na mesma pagina
                request.setAttribute("produtos", daoProduto.listarProduto());// para exibir os dados na pag  
                request.setAttribute("categorias", daoCategoria.listarCategoria());
                view.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
