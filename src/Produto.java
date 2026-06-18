

public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private double preco;
    private String status;

    public Produto(String var1, int var2, double var3, String var5) {
        this.nome = var1;
        this.quantidade = var2;
        this.preco = var3;
        this.status = var5;
    }

    public Produto() {
    }

    public int getId() { return this.id; }

    public void setId(int var1) { this.id = var1; }

    public String getNome() { return this.nome; }

    public void setNome(String var1) { this.nome = var1; }

    public int getQuantidade() { return this.quantidade; }

    public void setQuantidade(int var1) { this.quantidade = var1; }

    public double getPreco() { return this.preco; }

    public void setPreco(double var1) { this.preco = var1; }

    public String getStatus() { return this.status; }

    public void setStatus(String var1) { this.status = var1; }
}