package dao;

import connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Belmiro-Mungoi
 */
public class DaoLogin {

    private Connection connection;

    public DaoLogin() {
        connection = SingleConnection.getConnection();
    }

    public boolean validarLogin(String nomeUsuario, String senha) throws Exception {
        String sql = "select * from usuario where nomeUser = '" + nomeUsuario
                + "' and senhaUser = '" + senha + "'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return true; // Caso existe um usuario
        } else {
            return false; // Nao existe o usuario com os dados inseridos
        }
    }
}
