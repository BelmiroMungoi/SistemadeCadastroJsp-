package dao;

import beans.BeansEndereco;
import connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Belmiro-Mungoi
 */
public class DaoEndereco {

    private Connection connection;

    public DaoEndereco() {
        connection = SingleConnection.getConnection();
    }

    //Metodo resposanvel por inserir um novo endereco
    public void salvarEndereco(BeansEndereco endereco) {
        try {
            String sql = "insert into endereco(enderecoId, provincia, distrito, telefone,"
                    + "telefone2, bairro) values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, endereco.getEnderecoId());
            ps.setString(2, endereco.getProvincia());
            ps.setString(3, endereco.getDistrito());
            ps.setString(4, endereco.getMobile());
            ps.setString(5, endereco.getMobile2());
            ps.setString(6, endereco.getBairro());
            ps.execute();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
        }

    }

    //Metodo resposanvel pela busca de endereco
    public List<BeansEndereco> listarEndereco() {
        List<BeansEndereco> lista = new ArrayList<BeansEndereco>();
        try {
            String sql = "select * from endereco inner join usuario on enderecoId = idUser";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BeansEndereco endereco = new BeansEndereco();
                endereco.setIdEnder(rs.getInt("idEndereco"));
                endereco.setProvincia(rs.getString("provincia"));
                endereco.setDistrito(rs.getString("distrito"));
                endereco.setMobile(rs.getString("telefone"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setNomeCompleto(rs.getString("nomeCompleto"));
                lista.add(endereco);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    //Metodo responsavel pela actualizacao de dados
    public void actualiza(BeansEndereco endereco) {
        try {
            String sql = "update endereco set provincia = ?, distrito = ?, telefone = ?,"
                    + "telefone2 = ?, bairro = ? where idEndereco = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, endereco.getProvincia());
            ps.setString(2, endereco.getDistrito());
            ps.setString(3, endereco.getMobile());
            ps.setString(4, endereco.getMobile2());
            ps.setString(5, endereco.getBairro());
            ps.setInt(6, endereco.getIdEnder());
            ps.execute();
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

    //Metodo responsavel pela exclusao de dados
    public void excluir(String id) {
        try {
            String sql = "delete from endereco where idEndereco = '" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
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

    //Metodo resposanvel por fazer uma consulta na base de dados
    public BeansEndereco consultaEndereco(String id) {
        try {
            String sql = "select * from endereco where idEndereco = '" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BeansEndereco endereco = new BeansEndereco();
                endereco.setIdEnder(rs.getInt("idEndereco"));
                endereco.setProvincia(rs.getString("provincia"));
                endereco.setDistrito(rs.getString("distrito"));
                endereco.setMobile(rs.getString("telefone"));
                endereco.setMobile2(rs.getString("telefone2"));
                endereco.setBairro(rs.getString("bairro"));
                return endereco;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
