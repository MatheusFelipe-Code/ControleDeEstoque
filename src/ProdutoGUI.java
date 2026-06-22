import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Hbox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.List;
import java.sql.Exception;

public class Produto GUI extends Application {
    private ProdutoDao produtoDao;
    private ObservableList<Produto> produtos;
    private TableView<Produto> tableView;
    private TextField nomeInput, quantidadeInput, precoInput;
    private ComboBox<String> statusComboBox;
    private Connection conexaoDB;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage palco) {
        conexaoDB = ConexaoDB.connectar();
        produtoDao = new ProdutoDao(conexaoDB);
        produtos = FXCollections.observableArrayList(produtoDao.listarTodos());

        palco.setTitle("Gerenciamento de Estoque de Produtos");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(v: 10, v1: 10, v2: 10, v3: 10));
        vbox.setSpacing(10);

        HBox nomeProdutoBox = new HBox();
        nomeProdutoBox.setSpacing(10);
        Label nomeLabel = new Label(s: "Produto:");
        nomeInput = new TextField();
        nomeProdutoBox.getChildren().addAll(nomeLabel, nomeInput);

        Hbox quantidadeBox = new Hbox();
        quantidadeBox.setSpacing(10);
        Label quantidadeLabel = new Label(s: "Quantidade:");
        quantidadeInput = new TextField();
        quantidadeBox.getChildren().addAll(quantidadeLabel, quantidadeInput);

        Hbox preocBox = new Hbox();
        precoBox.setSpacing(10);
        Label precoLabel = new Label(s: "Preco:");
        precoInput = new TextField();
        precoBox.getChildren().addAll(precoLabel, precoInput);

        HBox statusBox = new HBox();
        statusBox.setSpacing(10);
        Label statusLabel = new Label(s: "Status:");
        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Estoque Normal", "Estoque Baixo");
        statusBox.getChildrem().addAll(statusLabel, statusComboBox);

        Button addButton = new Button("Adicionar");
        addButton.setOnAction(ActionEvent e -> {
            String preco = precoInput.getText().replace(oldChar: ',', newChar: '.');
            Produto produto = new Produto(nomeInput.getText(),
                    Integer.parseInt(quantidadeInput.getText()),
                    Double.parseDouble(preco),
                    statusComboBox.getValue());
            produtoDao.inserir(produto);
            produtos.setAll(produtoDao.listarTodos());
            limparCampos();
        });

        Button updateButton = new Button("Atualizar");
        updateButton.setOnAction(ActionEvent e -> {
            Produto selectedProduto = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduto != null) {
                selectedProduto.setNome(nomeInput.getText());
                selectedProduto.setQuantidade(Integer.parseInt(quantidadeInput.getText()));
                String preco = precoInput.getText().replace(oldChar: ',', newChar: '.');
                selectedProduto.setPreco(Double.parseDouble(preco));
                selectedProduto.setStatus(statusComboBox.getValue());
                produtoDao.atualizar(selectProduto);
                produtos.setAll(produtoDao.listarTodos());
                limparCampos();
            }
        });


        Button deleteButton = new Button("Excluir");
        deleteButton.setOnAction(ActionEvent e -> {
            Produto selectedProduto = tableView.getSelectionModel(). getSelectedItem();
            if (selectedProduto != null) {
                produtoDao.excluir(selectedProduto.getId());
                produtos.setAll(produtoDao.listarTodos());
                limparCampos();
            }
        });

        Button clearButton = new Button("Limpar");
        clearButton.setOnAction(ActionEvent e -> limparCampos());

        tableView = new TableView();
        tableView.setItems(produtos);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        List<TableColumn<Produto, ?>> columns = List.of(
                criarColuna(title: "ID", property: "id"),
                criarColuna(title: "Produto", property: "nome"),
                criarColuna(title: "Quantidade", property: "quantidade"),
                criarColuna(title: "Preco", property: "preco"),
                criarColuna(title: "Status", property: "status")
        );
        tableView.getColumns().addAll(columns);

        tableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<extends Produto> obs, Produto oldSelection, ProdutonewSelection) -> {
            if (newSelection !=null) {
                nomeInput.setText(newSelection.getNome());
                quantidadeInput.setText(String.valueOf(newSelection.getQuantidade()));
                precoInput.setText(String.valueOf(newSelection.getPreco()));
                statusComboBox.setValue(newSelection.getStatus());
            }
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(addButton, updateButton, deleButton, clearButton);

        vbox.getChildren().addAll(nomeProdutoBox, quantidadeBox, precoBox, statusBox, buttonBox, tableView);

        Scene scene = new Scene(vbox, v: 800, v1: 600);
        scene.getStylesheets().add("styles-produtos.css");
        palco.setScene(scene);
        palco.show();
    }

    @Override
    public void stop() {
        try {
            conexaoDB.close();
        } catch(SQLException e) {
            System.err.println("Erro ao fechar conexão" + e.getMessage());
        }
    }

    private void limparCampos() {
        nomeInput.clear();
        quantidadeInput.clear();
        precoInput.clear();
        statusComboBox.setValue(null);
    }

    private TableColumn<Produto, String> criarColuna(String tile, String property) {
        TableColumn<Produto, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }
}
