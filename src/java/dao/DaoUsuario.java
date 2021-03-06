package dao;

import beans.BeansUsuario;
import connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Belmiro-Mungoi
 */
public class DaoUsuario {

    private Connection connection;

    public DaoUsuario() {
        connection = SingleConnection.getConnection();
    }

    // Metodo responsavel por gravar os dados inseridos
    public void salvarUsuario(BeansUsuario usuario) {
        try {
            String sql = "insert into usuario(nomeCompleto, biUser, telefoneUser, "
                    + "nomeUser, senhaUser, imagem, curriculo, contentType, contentTypeCv,"
                    + " imageMin, activo, perfil) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, usuario.getNomeCompleto());
            pst.setString(2, usuario.getBiUser());
            pst.setString(3, usuario.getTelefone());
            pst.setString(4, usuario.getNomeUser());
            pst.setString(5, usuario.getSenha());
            pst.setString(6, usuario.getImagem());
            pst.setString(7, usuario.getCurriculo());
            pst.setString(8, usuario.getContentType());
            pst.setString(9, usuario.getContentTypeCv());
            pst.setString(10, usuario.getImagemMini());
            pst.setBoolean(11, usuario.isActivo());
            pst.setString(12, usuario.getPerfil());
            pst.execute();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }

    }

    /**
     * Metodo responsavel pela pesquisa do usuario
     * @param pesquisa: recebe o nome a ser pesquisado
     * @return consultaUsuario(sql): traz os dados do usuario que foi pesquisado 
     */
    public List<BeansUsuario> pesquisaUsuario(String pesquisa) {
        String sql = "select *from usuario where nomeUser <> 'admin'"
                + " and nomeCompleto like '%" + pesquisa + "%'";
        return consultaUsuario(sql);
    }

    //Metodos responsavel em carregar os dados da bd  
    public List<BeansUsuario> listarUsuario() {
        String sql = "select *from usuario where nomeUser <> 'admin' ";
        return consultaUsuario(sql);
    }

    /**
    * Metodo responsavel por executar o sql para a listagem e pesquisa de dados de usuario
    * @param sql: recebe o sql a ser executado
    * @return lista: retorna uma lista de usuarios que estao no banco
    */
    private List<BeansUsuario> consultaUsuario(String sql) {
        List<BeansUsuario> lista = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BeansUsuario usuario = new BeansUsuario();
                usuario.setIdUser(rs.getInt("idUser"));
                usuario.setNomeCompleto(rs.getString("nomeCompleto"));
                usuario.setBiUser(rs.getString("biUser"));
                usuario.setTelefone(rs.getString("telefoneUser"));
                usuario.setNomeUser(rs.getString("nomeUser"));
                usuario.setSenha(rs.getString("senhaUser"));
                //usuario.setImagem(rs.getString("imagem"));
                usuario.setCurriculo(rs.getString("curriculo"));
                usuario.setContentType(rs.getString("contentType"));
                usuario.setContentTypeCv(rs.getString("contentTypeCv"));
                usuario.setImagemMini(rs.getString("imageMin"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuario.setPerfil(rs.getString("perfil"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    //Metodo responsavel em excluir dados
    public void excluir(String id) {
        try {
            String sql = "delete from usuario where idUser = " + id;
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.execute();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                ex.printStackTrace();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }
    }

    //Metodo responsavel em buscar dados na base de dados
    public BeansUsuario consulta(String id) {
        try {
            String sql = "select *from usuario where idUser = '" + id + "' and nomeUser <> 'admin'";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                BeansUsuario beansUsuario = new BeansUsuario();
                beansUsuario.setIdUser(rs.getInt("idUser"));
                beansUsuario.setNomeCompleto(rs.getString("nomeCompleto"));
                beansUsuario.setBiUser(rs.getString("biUser"));
                beansUsuario.setTelefone(rs.getString("telefoneUser"));
                beansUsuario.setNomeUser(rs.getString("nomeUser"));
                beansUsuario.setSenha(rs.getString("senhaUser"));
                beansUsuario.setImagem(rs.getString("imagem"));
                beansUsuario.setCurriculo(rs.getString("curriculo"));
                beansUsuario.setContentType(rs.getString("contentType"));
                beansUsuario.setContentTypeCv(rs.getString("contentTypeCv"));
                beansUsuario.setImagemMini(rs.getString("imageMin"));
                beansUsuario.setActivo(rs.getBoolean("activo"));
                beansUsuario.setPerfil(rs.getString("perfil"));
                return beansUsuario;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*Metodo responsavel pela validacao do nome de usuario
    *O metodo ira verificar se ja existe algum usuario com o mesmo nome
    *Caso exista nao ira gravar os dados
    *caso na ira gravar
     */
    public boolean validaUsuario(String nomeUser) {
        try {
            String sql = "select count(1) as qtd from usuario where nomeUser = '" + nomeUser + "'";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("qtd") <= 0;// retorna true caso nao exista o user 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //Metodo responsavel pela validaceo de usuario na actualizacao 
    public boolean validaUsuarioUpdate(String nomeUser, String id) {
        try {
            String sql = "select count(1) as qtd from usuario where nomeUser = '" + nomeUser + "' and idUser <>" + id;
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("qtd") <= 0;// retorna true caso nao exista o user 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /*Metodo responsavel pela validacao da senha
    *Verifica se ja existe a mesma senha na bd
    *Caso sim nao ira salvar
    *Caso nao salva
     */
    public boolean validaSenha(String senhaUser) {
        try {
            String sql = "select count(1) as qtd from usuario where senhaUser = '" + senhaUser + "'";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("qtd") <= 0;// retorna true caso nao exista o user 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //Metodo responsavel pela validacao da senha na actualizcao
    public boolean validaSenhaUpdate(String senhaUser, String id) {
        try {
            String sql = "select count(1) as qtd from usuario where senhaUser = '" + senhaUser + "' and idUser <>" + id;
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("qtd") <= 0;// retorna true caso nao exista o user 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //metodo responsavel pela actualizacao de dados 
    public void actualiza(BeansUsuario usuario) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" update usuario set nomeCompleto = ?, biUser = ?, ");
            sql.append(" telefoneUser = ?, nomeUser = ?, senhaUser = ?, activo = ?, perfil = ? ");
            if (usuario.isUpdateImage()) {
                sql.append(" ,imagem = ? ");
            }
            if (usuario.isUpdatePdf()) {
                sql.append(" ,curriculo = ? ");
            }
            if (usuario.isUpdateImage()) {
                sql.append(" ,contentType = ? ");
            }
            if (usuario.isUpdatePdf()) {
                sql.append(" ,contentTypeCv = ? ");
            }
            if (usuario.isUpdateImage()) {
                sql.append(" ,imageMin = ? ");
            }
            sql.append(" where idUser = " + usuario.getIdUser());
            PreparedStatement pst = connection.prepareStatement(sql.toString());
            pst.setString(1, usuario.getNomeCompleto());
            pst.setString(2, usuario.getBiUser());
            pst.setString(3, usuario.getTelefone());
            pst.setString(4, usuario.getNomeUser());
            pst.setString(5, usuario.getSenha());
            pst.setBoolean(6, usuario.isActivo());
            pst.setString(7, usuario.getPerfil());
            if (usuario.isUpdateImage()) {
                pst.setString(8, usuario.getImagem());
            } else if (usuario.isUpdatePdf()) {
                pst.setString(8, usuario.getCurriculo());
            }

            if (usuario.isUpdatePdf() && usuario.isUpdateImage()) {
                pst.setString(9, usuario.getCurriculo());
            } else if (usuario.isUpdateImage()) {
                pst.setString(9, usuario.getContentType());
            }

            if (usuario.isUpdateImage() && usuario.isUpdatePdf()) {
                pst.setString(10, usuario.getContentType());
            } else if (usuario.isUpdatePdf()) {
                pst.setString(9, usuario.getContentTypeCv());
            }

            if (usuario.isUpdatePdf() && usuario.isUpdateImage()) {
                pst.setString(11, usuario.getContentTypeCv());
            } else if (usuario.isUpdateImage()) {
                pst.setString(10, usuario.getImagemMini());
            }

            if (usuario.isUpdateImage() && usuario.isUpdatePdf()) {
                pst.setString(12, usuario.getImagemMini());
            }
            pst.execute();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                ex.printStackTrace();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }

    }
}
