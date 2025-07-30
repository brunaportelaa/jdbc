import dao.AlunoDAOImpl;
import datasource.MySQLDataSourceManager;
import dto.AlunoDTO;
import ui.Leitor;
import ui.View;

import java.sql.*;

public class App {
    public static void main(String[] args) {

        //Iniciando o Data Source
        MySQLDataSourceManager dataSource = new MySQLDataSourceManager("localhost", 3306, "escola");

        try (Connection connection = dataSource.getConnection()) {

            AlunoDAOImpl alunoDAO = new AlunoDAOImpl(connection);
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
                        AlunoDTO aluno = View.lerAluno();
                        alunoDAO.inserirAluno(aluno);
                        alunoDAO.listarTodos();
                        break;
                    case 2:
                        View.exibirResultado(alunoDAO.listarTodos());
                        break;
                    case 3:
                        idPesquisado = Leitor.lerInt("Informe o ID que deseja pesquisar:");
                        if (alunoDAO.buscarPorId(idPesquisado) == null) {
                            System.out.println("Não existe um aluno com esse ID.");
                            break;
                        } else {
                            AlunoDTO novoAluno = View.lerAluno();
                            alunoDAO.atualizaPorId(idPesquisado, novoAluno);
                            alunoDAO.listarTodos();
                        }
                        break;
                    case 4:
                        idPesquisado = Leitor.lerInt("Informe o ID que deseja pesquisar:");
                        if (alunoDAO.buscarPorId(idPesquisado) == null) {
                            System.out.println("Não existe um aluno com esse ID.");
                            break;
                        } else {
                            alunoDAO.excluirPorId(idPesquisado);
                            alunoDAO.listarTodos();
                        }
                        break;
                    case 5:
                        String termoPesquisa = Leitor.lerString("Informe o termo a ser pesquisado: ");
                        View.exibirResultado(alunoDAO.buscaPorNome(termoPesquisa));
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
