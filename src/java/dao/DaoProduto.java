package dao;

import beans.BeansProduto;
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
public class DaoProduto {

    private Connection connection;

    public DaoProduto() {
        connection = SingleConnection.getConnection();
    }

    //Metodo responsavel pela insercao de dados na bd
    public void salvarProduto(BeansProduto produto) {
        try {
            String sql = "insert into produto(nomeProd, quantProd, valorProd) values(?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, produto.getNomeProd());
            pst.setInt(2, produto.getQuantProd());
            pst.setFloat(3, produto.getValorProd());
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

    // Metodo responsavel por buscar os produto e setar na tabela
    public List<BeansProduto> listarProduto() {
        List<BeansProduto> lista = new ArrayList<BeansProduto>();
        try {
            String sql = "select *from produto";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BeansProduto produto = new BeansProduto();
                produto.setIdProd(rs.getLong("idProd"));
                produto.setNomeProd(rs.getString("nomeProd"));
                produto.setQuantProd(rs.getInt("quantProd"));
                produto.setValorProd(rs.getFloat("valorProd"));
                lista.add(produto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // Metodo responsavel pela actualizacao de dados.
    public void actualiza(BeansProduto produto) {
        try {
            String sql = "update produto set nomeProd = ?, quantProd = ?, valorProd = ?"
                    + "where idProd = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, produto.getNomeProd());
            pst.setInt(2, produto.getQuantProd());
            pst.setFloat(3, produto.getValorProd());
            pst.setLong(4, produto.getIdProd());
            pst.execute();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Metodo que busca dados para a actualizacao 
    public BeansProduto consulta(String id) {
        try {
            String sql = "select *from produto where idProd = '" + id + "'";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                BeansProduto beansProduto = new BeansProduto();
                beansProduto.setIdProd(rs.getLong("idProd"));
                beansProduto.setNomeProd(rs.getString("nomeProd"));
                beansProduto.setQuantProd(rs.getInt("quantProd"));
                beansProduto.setValorProd(rs.getFloat("valorProd"));
                return beansProduto;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Metodo que deleta dados
    public void excluir(String id) {
        try {
            String sql = "delete from produto where idProd = '"+id+"'";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.execute();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    /*Metodo responsavel pela validacao do nome de produto
    *O metodo ira verificar se ja existe algum produto com o mesmo nome
    *Caso exista nao ira gravar os dados
    *caso na ira gravar
     */
    public boolean validarProduto(String nomeProd) {
        try {
            String sql = "select count(1) as qtd from produto where nomeProd = '"+nomeProd+"'";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("qtd") <= 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //Metodo responsavel pela validacao na hora da actualizacao
    public boolean validarProdutoUpdate(String nomeProd, String idProd) {
        try {
            String sql = "select count(1) as qtd from produto where nomeProd = '"+nomeProd+"' and idProd <>"+idProd;
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("qtd") <= 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
