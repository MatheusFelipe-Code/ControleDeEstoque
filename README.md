# 📦 Sistema de Gerenciamento de Estoque

Um sistema desktop para controlo de inventário desenvolvido em Java. Este projeto permite o registo, atualização, leitura e eliminação (CRUD) de produtos num stock, utilizando uma interface gráfica moderna e armazenamento persistente.

## 🚀 Tecnologias Utilizadas

* **Java 17+**: Linguagem principal do projeto.
* **JavaFX**: Biblioteca para a construção da interface gráfica (GUI).
* **SQLite**: Base de dados relacional leve para persistência local dos dados.
* **Padrão DAO (Data Access Object)**: Arquitetura utilizada para separar a lógica de negócio do acesso à base de dados, garantindo um código mais limpo e manutenível.

## ✨ Funcionalidades

- [x] Conexão automática com base de dados SQLite (`ConexaoDB`).
- [x] Criação automática da tabela de produtos na primeira execução (`CriadorDeTabela`).
- [x] **Adicionar Produto**: Inserção de nome, quantidade, preço e status.
- [x] **Atualizar Produto**: Edição de dados de produtos já existentes.
- [x] **Excluir Produto**: Remoção de itens do stock.
- [x] **Listagem em Tempo Real**: Tabela (`TableView`) que atualiza automaticamente após cada operação.

## 🛠️ Como executar o projeto

1. Clone este repositório no seu computador:
   ```bash
   git clone [https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git](https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git)
