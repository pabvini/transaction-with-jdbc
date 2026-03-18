package transaction_with_jdbc;

import java.sql.*;

public class ConnectionJDBC {

    public static final String URL = "jdbc:postgresql://localhost:5432/NOME_DO_SEU_BANCO";
    public static final String USER = "postgres";
    public static final String PASSWORD = "SUA_SENHA_AQUI";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
