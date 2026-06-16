import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class CriadorDeTabela {

    public static void main(String[] args) {
        try (Connection conexao = ConexaoDB.conectar();
            Statement stmt = conexao.createStatement()) {

            String comandosSql = "CREATE TABLE IF NOT EXISTS produtos (" +
                    "id_produto INTEGER PRIMARY KEY," +
                    "nome_produto TEXT NOT NULL," +
                    "quantidade INTEGER," +
                    "preco REAL," +
                    "status TEXT" +
                    ");";
            System.out.println( comandosSql );

            stmt.execute(comandosSql);

            System.out.println("Tabela 'Produtos' criada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao criar a Tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
}