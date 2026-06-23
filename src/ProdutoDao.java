import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
    private final Connection CONEXAO_DB;

    public ProdutoDao(Connection var1) { this.CONEXAO_DB = var1; }

    public void inserir(Produto var1) {
        String var2 = "INSERT INTO produtos (nome_produto, quantidade, preco, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement var3 = this.CONEXAO_DB.prepareStatement(var2)) {
            var3.setString(1, var1.getNome());
            var3.setInt(2, var1.getQuantidade());
            var3.setDouble(3, var1.getPreco());
            var3.setString(4, var1.getStatus());
            var3.executeUpdate();
        } catch (SQLException var8) {
            System.err.println("Erro ao inserir produto; " + var8.getMessage());

        }

    }

    public void excluirTodos() {
        String var1 = "DELETE FROM produtos";

        try (PreparedStatement var2 = this.CONEXAO_DB.prepareStatement(var1)) {
            var2.executeUpdate();
        } catch (SQLException var7) {
            System.err.println("Erro ao deletar produtos; " + var7.getMessage());
        }
    }

    public Produto consultarPorId(int var1) {
        String var2 = "SELECT * FROM produtos WHERE id_produto = ?";

        try {
            Produto var6;
            try (PreparedStatement var3 = this.CONEXAO_DB.prepareStatement(var2)) {
                var3.setInt(1, var1);

                try (ResultSet var4 = var3.executeQuery()) {
                    if (!var4.next()) {
                        return null;
                    }

                    Produto var5 = new Produto();
                    var5.setId(var4.getInt("id_produto"));
                    var5.setNome(var4.getString("nome_produto"));
                    var5.setQuantidade(var4.getInt("quantidade"));
                    var5.setPreco(var4.getDouble("preco"));
                    var5.setStatus(var4.getString("status"));
                    var6 = var5;
                }
            }

            return var6;
        } catch (SQLException var11) {
            System.err.println("Erro ao consultar produto por ID:  " + var11.getMessage());
            return null;
        }
    }

    public void atualizar(Produto var1) {
        String var2 = "UPDATE produtos SET nome_produto = ?, quantidade = ?, preco = ?, status = ?  WHERE id_produto = ?";

        try (PreparedStatement var3 = this.CONEXAO_DB.prepareStatement(var2)) {
            var3.setString(1, var1.getNome());
            var3.setInt(2, var1.getQuantidade());
            var3.setDouble(3, var1.getPreco());
            var3.setString(4, var1.getStatus());
            var3.setInt(5, var1.getId());
            var3.executeUpdate();
        } catch (SQLException var8) {
            System.err.println("Erro ao atualizar produto; " + var8.getMessage());
        }
    }

    public void excluir(int var1) {
        String var2 = "DELETE FROM produtos WHERE id_produto = ?";

        try (PreparedStatement var3 = this.CONEXAO_DB.prepareStatement(var2)) {
            var3.setInt(1, var1);
            var3.executeUpdate();
        } catch (SQLException var8) {
            System.err.println("Erro ao deletar produto; " + var8.getMessage());
        }
    }

    public List<Produto> listarTodos() {
        ArrayList var1 = new ArrayList();
        String var2 = "SELECT * FROM produtos";

        try (
                PreparedStatement var3 = this.CONEXAO_DB.prepareStatement(var2);
                ResultSet var4 = var3.executeQuery();
                ) {
                    while(var4.next()) {
                        Produto var5 = new Produto();
                        var5.setId(var4.getInt("id_produto"));
                        var5.setNome(var4.getString("nome_produto"));
                        var5.setQuantidade(var4.getInt("quantidade"));
                        var5.setPreco(var4.getDouble("preco"));
                        var5.setStatus(var4.getString("status"));
                        var1.add(var5);
                    }

        } catch (SQLException var11) {
            System.err.println("Erro ao listar produtos; " + var11.getMessage());
        }

        return var1;
    }
}