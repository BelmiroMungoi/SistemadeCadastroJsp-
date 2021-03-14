package dao;

import beans.BeansCategoria;
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
public class DaoCategoria {

    private Connection connection;

    public DaoCategoria() {
        connection = SingleConnection.getConnection();
    }

    public List<BeansCategoria> listarCategoria() {
        List<BeansCategoria> lista = new ArrayList<>();
        String sql = "select * from categoria";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BeansCategoria categoria = new BeansCategoria();
                categoria.setIdCat(rs.getInt("idCat"));
                categoria.setNomeCat(rs.getString("nomeCat"));
                lista.add(categoria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
