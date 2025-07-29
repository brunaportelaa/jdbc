package app;

import com.mysql.cj.jdbc.MysqlDataSource;
import dao.DatabaseCRUD;
import datasource.DatasourceFactoryProvider;
import model.Aluno;
import ui.Leitor;
import ui.View;

import javax.sql.DataSource;
import java.sql.*;

public class App {
    public static void main(String[] args) {

        // Instanciando o DataSource
        // Transformar em Singleton
        DataSource dataSource = DatasourceFactoryProvider.createDatasource("mysql");

        // Iniciando a conexão
        try (Connection connection = dataSource.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"))
        ) {

            DatabaseCRUD dbCrud = new DatabaseCRUD(connection);
            boolean continuar = true;
            int idPesquisado;

            System.out.println("Bem vindo ao sistema escolar. Para navegar, digite:\n");

            while (continuar) {
                int opcao = Leitor.lerInt("[1] - Cadastrar aluno\n" +
                        "[2] - Listar todos os alunos\n" +
                        "[3] - Atualizar um aluno pelo ID\n" +
                        "[4] - Excluir um aluno pelo ID\n" +
                        "[5] - Buscar aluno por nome\n" +
                        "[6] - Sair", 1, 5);

                switch (opcao) {
                    case 1:
                        Aluno aluno = View.lerAluno();
                        dbCrud.inserirAluno(aluno);
                        dbCrud.listarTodos();
                        break;
                    case 2:
                        View.exibirResultado(dbCrud.listarTodos());
                        break;
                    case 3:
                        idPesquisado = Leitor.lerInt("Informe o ID que deseja pesquisar:");
                        if (dbCrud.buscarPorId(idPesquisado) == null) {
                            System.out.println("Não existe um aluno com esse ID.");
                            break;
                        } else {
                            Aluno novoAluno = View.lerAluno();
                            dbCrud.atualizaPorId(idPesquisado, novoAluno);
                            dbCrud.listarTodos();
                        }
                        break;
                    case 4:
                        idPesquisado = Leitor.lerInt("Informe o ID que deseja pesquisar:");
                        if (dbCrud.buscarPorId(idPesquisado) == null) {
                            System.out.println("Não existe um aluno com esse ID.");
                            break;
                        } else {
                            dbCrud.excluirPorId(idPesquisado);
                            dbCrud.listarTodos();
                        }
                        break;
                    case 5:
                        String termoPesquisa = Leitor.lerString("Informe o termo a ser pesquisado: ");
                        View.exibirResultado(dbCrud.buscaPorNome(termoPesquisa));
                        break;
                    default:
                        System.out.println("Encerrando a conexão.");
                        continuar = false;
                        break;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
