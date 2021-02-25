package servlets;

import beans.BeansUsuario;
import dao.DaoUsuario;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author Belmiro-Mungoi
 */
@WebServlet("/UsuarioServlet")
@MultipartConfig
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
                request.setAttribute("usuarios", daoUsuario.listarUsuario());
                view.forward(request, response);
                
            } else if (accao.equals("listar")) {
                RequestDispatcher view
                        = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listarUsuario());
                view.forward(request, response);
                
            } else if (accao.equals("download")) {
                BeansUsuario beansUsuario = daoUsuario.consulta(id);
                
                if (beansUsuario != null) {
                    String type = request.getParameter("type");
                    String contentType = "";
                    byte[] files = null;
                    
                    if (type.equals("imagem")) {
                        contentType = beansUsuario.getContentType();
                        // Converte a img na base64 para um byte[] 
                        files = new Base64().decodeBase64(beansUsuario.getImagem());
                        
                    } else if (type.equals("curriculo")) {
                        contentType = beansUsuario.getContentTypeCv();
                        files = new Base64().decodeBase64(beansUsuario.getCurriculo());
                    }
                    
                    response.setHeader("Content-Disposition", "attachment;filename="
                            + beansUsuario.getNomeCompleto() + "."
                            + contentType.split("\\/")[1]);

                    //Objeto de entrada em byte[]
                    InputStream input = new ByteArrayInputStream(files);
                    //Inicio do codigo response pra o navegador
                    int read = 0;
                    byte[] bs = new byte[1024];
                    OutputStream output = response.getOutputStream();
                    
                    while ((read = input.read(bs)) != -1) {
                        output.write(bs, 0, read);
                    }
                    output.flush();
                    output.close();
                }
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
            
            try {
                //Inicio doCodigo para carregar ficheiros(fotos/pdfs) da base de dados
                if (ServletFileUpload.isMultipartContent(request)) { //Verifica se e um form de upload

                    Part imagem = request.getPart("foto");
                    if (imagem != null && imagem.getInputStream().available() > 0) {
                        String fotoBase64 = new Base64().encodeBase64String(
                                converteStream(imagem.getInputStream()));
                        usuario.setImagem(fotoBase64);
                        usuario.setContentType(imagem.getContentType());
                    } else {
                        usuario.setImagem(request.getParameter("fotoUser"));
                        usuario.setContentType(request.getParameter("content"));
                    }
                    
                    Part curriculo = request.getPart("curriculo");
                    if (curriculo != null && curriculo.getInputStream().available() > 0) {
                        String curriculoBase64 = new Base64().encodeBase64String(
                                converteStream(curriculo.getInputStream()));
                        usuario.setCurriculo(curriculoBase64);
                        usuario.setContentTypeCv(curriculo.getContentType());
                    } else {
                        usuario.setCurriculo(request.getParameter("cvUser"));
                        usuario.setContentTypeCv(request.getParameter("contentCv"));
                    }
                }//Fim do Codigo FileUpload

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

    // Conver
    private byte[] converteStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int read = inputStream.read();
        while (read != -1) {
            outputStream.write(read);
            read = inputStream.read();
        }
        return outputStream.toByteArray();
    }
    
}
