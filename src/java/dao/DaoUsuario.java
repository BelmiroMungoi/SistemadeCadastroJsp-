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
                    + "nomeUser, senhaUser) values(?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, usuario.getNomeCompleto());
            pst.setString(2, usuario.getBiUser());
            pst.setString(3, usuario.getTelefone());
            pst.setString(4, usuario.getNomeUser());
            pst.setString(5, usuario.getSenha());
            pst.execute();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }

    }

    //Metodos responsavel em carregar os dados da bd  
    public List<BeansUsuario> listarUsuario() {
        List<BeansUsuario> lista = new ArrayList<BeansUsuario>();
        try {
            String sql = "select *from usuario";
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
            String sql = "delete from usuario where idUser = '" + id + "'";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.execute();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }
    }

    //Metodo responsavel em buscar dados na base de dados
    public BeansUsuario consulta(String id) {
        try {
            String sql = "select *from usuario where idUser = '" + id + "'";
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
            String sql = "update usuario set nomeCompleto = ?, biUser = ?,"
                    + "telefoneUser = ?, nomeUser = ?, senhaUser = ? where idUser = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, usuario.getNomeCompleto());
            pst.setString(2, usuario.getBiUser());
            pst.setString(3, usuario.getTelefone());
            pst.setString(4, usuario.getNomeUser());
            pst.setString(5, usuario.getSenha());
            pst.setInt(6, usuario.getIdUser());
            pst.execute();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }

    }
}
