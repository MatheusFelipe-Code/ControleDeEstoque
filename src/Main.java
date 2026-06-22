import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] var0) {
        try (Connection var1 = ConexaoDB.conectar()) {
            ProdutoDao var2 = new ProdutoDao(var1);
            mostrarProdutos(var2);
            var2.excluirTodos();
            mostraProdutos(var2);
        } catch (Exception var6) {
            System.err.println("Erro geral: " + var6.getMessage());
        }
    }

    private static void mostrarProdutos(ProdutoDao var0) {
        List var1 = var).listarTodos();
        if (var1.isEmpty()) {
            System.out.println("Nenhum produto encontrado. ");
        } else {
            System.out.println("Lista de produtos:");

            for(Produto var3 : var1) {
                PrintStream var10000 = System.out;
                int var10001 = var3.getId();
                var10000.println(var10001 + ": " + var3.getNome() + " - " + var3.getPreco());
            }
        }
    }
}