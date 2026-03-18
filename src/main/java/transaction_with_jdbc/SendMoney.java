package transaction_with_jdbc;

import java.sql.*;

public class SendMoney {

    public static void sendMoney(int idContaOrigem, int idContaDestino, int valor){

        Connection conn = null;

        try {

            conn = ConnectionJDBC.getConnection();

            conn.setAutoCommit(false);

            String sqlOrigem = "UPDATE accounts SET balance = balance - ? WHERE id = ?;";
            try (PreparedStatement stmtOrigem = conn.prepareStatement(sqlOrigem)){
                stmtOrigem.setInt(1, valor);
                stmtOrigem.setInt(2, idContaOrigem);
                stmtOrigem.executeUpdate();
            }

            String sqlDestino = "UPDATE accounts SET balance = balance + ? WHERE id = ?;";
            try (PreparedStatement stmtDestino = conn.prepareStatement(sqlDestino)){
                stmtDestino.setInt(1, valor);
                stmtDestino.setInt(2, idContaDestino);
                stmtDestino.executeUpdate();
            }

            conn.commit();
            System.out.println("Transferência concluída, valor: R$" + valor);

        } catch (SQLException e) {

            if (conn != null){
                try {
                    conn.rollback();
                    System.out.println("ROLLBACK!");
                } catch (SQLException rollbackex) {
                    rollbackex.printStackTrace();
                }
            }

        } finally {

            if (conn != null){
                try{
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }


    }


}

