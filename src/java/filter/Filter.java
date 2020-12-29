package filter;

import connection.SingleConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Belmiro-Mungoi
 */
@WebFilter(urlPatterns = {"/*"})
public class Filter implements javax.servlet.Filter {

    private Connection connection;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        connection = SingleConnection.getConnection();// connecta a base de dados
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);// Verifica as requisicoes e interecepta
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //ex.printStackTrace();
            }

        }
    }

    @Override
    public void destroy() {

    }

}
