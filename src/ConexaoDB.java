import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL_JDBC_PADRAO = "jdbc:sqlite:meu_banco_de_dados.db";

    public static Connection conectar() {
         try {
             return DriverManager.getConnection("jdbc:sqlite:meu_banco_de_dados.db");
         } catch (SQLException var1) {
             System.err.println("Erro ao conectar ao banco de dados: " + var1.getMessage());
             return null;
         }
    }

    public static Connection conectarGenerico(String var0, String var1, String var2) {
        try {
            return DriverManager.getConnection(var0, var1, var2);
        } catch (SQLException var4) {
            System.err.println("Erro ao conectar ao banco de dados: " + var4.getMessage());
            return null;
        }
    }
}